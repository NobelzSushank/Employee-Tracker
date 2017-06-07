package com.example.nobelz.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Message extends AppCompatActivity {

    TextView composemsg,inboxmsg,sntmsg;
    String message_sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent itt=getIntent();
        message_sender=itt.getStringExtra("send_garni");
        composemsg = (TextView)findViewById(R.id.composemsg);
        composemsg.setClickable(true);

        inboxmsg = (TextView)findViewById(R.id.inboxmsg);
        inboxmsg.setClickable(true);

        sntmsg = (TextView)findViewById(R.id.sntmsg);
        sntmsg.setClickable(true);

        composemsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in5 = new Intent(Message.this, Composemsg.class);
                in5.putExtra("send_garnee", message_sender);
                startActivity(in5);
            }
        });

        inboxmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in6 = new Intent(Message.this, Inboxmsg.class);
                startActivity(in6);
            }
        });

        sntmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in7 = new Intent(Message.this, Sentmsg.class);
                startActivity(in7);
            }
        });
    }
}
