package com.example.nobelz.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Leader extends AppCompatActivity {

    TextView leader_task, leader_message, led_location;
    String ledsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);

        Intent inlead=getIntent();
        ledsend=inlead.getStringExtra("send_garne");

        leader_task = (TextView)findViewById(R.id.leader_task);
        leader_task.setClickable(true);


        leader_message = (TextView)findViewById(R.id.leader_message);
        leader_message.setClickable(true);

        led_location = (TextView)findViewById(R.id.led_location);
        led_location.setClickable(true);



        leader_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inl2 = new Intent(Leader.this, Ledtask.class);
                startActivity(inl2);
            }
        });


        leader_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inl3 = new Intent(Leader.this, Message.class);
                inl3.putExtra("send_garni",ledsend);
                startActivity(inl3);
            }
        });

        led_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inl41 = new Intent(Leader.this, Location.class);
                startActivity(inl41);
            }
        });



    }


}
