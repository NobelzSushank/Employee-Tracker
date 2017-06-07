package com.example.nobelz.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupTask extends AppCompatActivity implements Download_data.download_complete {

    public ListView list1;
    public ArrayList<Gtask> gtasks = new ArrayList<Gtask>();
    public ListAdapter1 adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_task);


        list1 = (ListView) findViewById(R.id.list1);
        adapter1 = new ListAdapter1(this);
        list1.setAdapter(adapter1);

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://10.0.2.2/qwer/group_task_info.php");
    }

    @Override
    public void get_data(String data) {

        try {
            JSONArray data_array=new JSONArray(data);

            for (int j = 0 ; j < data_array.length() ; j++)
            {
                JSONObject obj=new JSONObject(data_array.get(j).toString());

                Gtask add=new Gtask();
                add.leader = obj.getString("username");
                add.group1 = obj.getString("groups");
                add.groupTask = obj.getString("group_task");

                gtasks.add(add);

            }

            adapter1.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
