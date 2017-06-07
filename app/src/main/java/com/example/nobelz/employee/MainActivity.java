package com.example.nobelz.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();

    EditText etUsername, etPassword;
    Button btnLogin;
    String sender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://192.168.100.6/qwer/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        if (response.contains("Check username and/or password.")){
                            Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();
                        }

                            else {

                            if (etPassword.getText().toString().contains("admin")) {
                                sender=etUsername.getText().toString();
                                Intent in = new Intent(MainActivity.this, admin.class);
                                in.putExtra("send_garne",sender);
                                startActivity(in);
                            }else if (etPassword.getText().toString().contains("leader")){
                                sender=etUsername.getText().toString();
                                Intent in = new Intent(MainActivity.this, Leader.class);
                                in.putExtra("send_garne",sender);
                                startActivity(in);
                            }
                            else{
                                sender=etUsername.getText().toString();
                                Intent in = new Intent(MainActivity.this, Member.class);
                                in.putExtra("send_garne",sender);
                                startActivity(in);
                            }
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error While reading data", Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("uname",etUsername.getText().toString());
                        params.put("pword",etPassword.getText().toString());
                        return params;
                    }
                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });

    }
}
