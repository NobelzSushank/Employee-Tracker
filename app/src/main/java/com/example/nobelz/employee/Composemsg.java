package com.example.nobelz.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Composemsg extends AppCompatActivity implements View.OnClickListener {

    private static final String INSERT_URI = "http://10.0.2.2/qwer/msg.php";


    String msgsender;
    private EditText sendSubject;
    private Button sending;
    private EditText sendEmail;
    private EditText receiver;

    public static final String KEY_FROM = "from_sender";
    public static final String KEY_RECEIVER = "to_receiver";
    public static final String KEY_SUBJECT = "mail_subject";
    public static final String KEY_MESSAGE= "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composemsg);
      //  sender = (EditText) findViewById(R.id.etUsername);

        Intent itt=getIntent();
        msgsender=itt.getStringExtra("send_garnee");


        receiver = (EditText)findViewById(R.id.too);
        sendSubject = (EditText)findViewById(R.id.subjects);
        sendEmail = (EditText)findViewById(R.id.emails);

        sending = (Button)findViewById(R.id.send);
        sending.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        sendmsg();

    }

    private void sendmsg() {
        final String from_sender = msgsender.trim();
        final String to_receiver = receiver.getText().toString().trim();
        final String mail_subject = sendSubject.getText().toString().trim();
        final String message = sendEmail.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, INSERT_URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Composemsg.this, "successful", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Composemsg.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_FROM, from_sender);
                params.put(KEY_RECEIVER, to_receiver);
                params.put(KEY_SUBJECT, mail_subject);
                params.put(KEY_MESSAGE, message);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
