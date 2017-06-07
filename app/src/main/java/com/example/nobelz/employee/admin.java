package com.example.nobelz.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class admin extends AppCompatActivity {


    TextView admin_manage, admin_msg, admin_grptask, admin_logout,locationn;
    String senderr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent itt=getIntent();
        senderr=itt.getStringExtra("send_garne");

        admin_manage = (TextView)findViewById(R.id.admin_manage);
        admin_manage.setClickable(true);

        admin_msg = (TextView)findViewById(R.id.admin_msg);
       admin_msg.setClickable(true);

        admin_grptask = (TextView)findViewById(R.id.admin_grptask);
        admin_grptask.setClickable(true);

        admin_logout = (TextView)findViewById(R.id.admin_logout);
        admin_logout.setClickable(true);

        locationn = (TextView)findViewById(R.id.locationn);
        locationn.setClickable(true);


        admin_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(admin.this, Adminmanage.class);
                startActivity(in1);
            }
        });

        admin_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(admin.this, Message.class);
                in2.putExtra("send_garni",senderr);
                startActivity(in2);
            }
        });

        admin_grptask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3 = new Intent(admin.this, GroupTask.class);
                startActivity(in3);
            }
        });

        admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in4 = new Intent(admin.this, MainActivity.class);
                startActivity(in4);
            }
        });

        locationn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in41 = new Intent(admin.this, Location.class);
                in41.putExtra("send_garni",senderr);
                startActivity(in41);
            }
        });

    }
}
