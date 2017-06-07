package com.example.nobelz.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter1 extends BaseAdapter{

    GroupTask groupTask;
    ListAdapter1 (GroupTask groupTask) { this.groupTask = groupTask; }

    @Override
    public int getCount() {
        return groupTask.gtasks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem1{
        TextView leader;
        TextView group1;
        TextView grouptask;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem1 holder1 = new ViewHolderItem1();
        if (convertView == null){
            LayoutInflater inflater1 = (LayoutInflater)groupTask.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater1.inflate(R.layout.activity_gtask, null);
            holder1.leader = (TextView) convertView.findViewById(R.id.leader);
            holder1.group1 = (TextView) convertView.findViewById(R.id.group1);
            holder1.grouptask = (TextView) convertView.findViewById(R.id.grouptask);

            convertView.setTag(holder1);
        }
        else{
            holder1 = (ViewHolderItem1) convertView.getTag();
        }

        holder1.leader.setText(this.groupTask.gtasks.get(position).leader);
        holder1.group1.setText(this.groupTask.gtasks.get(position).group1);
        holder1.grouptask.setText(this.groupTask.gtasks.get(position).groupTask);

        return convertView;
    }



}
