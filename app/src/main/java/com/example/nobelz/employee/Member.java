package com.example.nobelz.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Member extends AppCompatActivity {

    TextView mem_task, mem_message, mem_location;
    String memsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        final Intent memsnd=getIntent();
        memsend=memsnd.getStringExtra("send_garne");

        mem_task = (TextView)findViewById(R.id.mem_task);
        mem_task.setClickable(true);

        mem_message = (TextView)findViewById(R.id.mem_message);
        mem_message.setClickable(true);

        mem_location = (TextView)findViewById(R.id.mem_location);
        mem_location.setClickable(true);



        mem_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inm2 = new Intent(Member.this, Memtask.class);
                startActivity(inm2);
            }
        });


        mem_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inm3 = new Intent(Member.this, Message.class);
                  inm3.putExtra("send_garni",memsnd);
                startActivity(inm3);
            }
        });

        mem_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in41 = new Intent(Member.this, Location.class);
                startActivity(in41);
            }
        });

    }
}
