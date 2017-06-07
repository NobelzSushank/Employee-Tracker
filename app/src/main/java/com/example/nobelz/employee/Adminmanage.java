package com.example.nobelz.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Adminmanage extends AppCompatActivity implements Download_data.download_complete {



    public ListView list;
    public ArrayList<Amanage> amanage = new ArrayList<Amanage>();
    public com.example.nobelz.employee.ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmanage);

        list = (ListView) findViewById(R.id.list);
        adapter = new com.example.nobelz.employee.ListAdapter(this);
        list.setAdapter(adapter);

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://10.0.2.2/qwer/manage_user_info.php");


    }


    @Override
    public void get_data(String data) {
        try {
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                Amanage add=new Amanage();
                add.name = obj.getString("username");
                add.group = obj.getString("groups");
                add.position = obj.getString("position");

                amanage.add(add);

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
