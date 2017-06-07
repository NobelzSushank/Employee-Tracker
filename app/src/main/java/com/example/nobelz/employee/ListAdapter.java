package com.example.nobelz.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Adminmanage adminmanage;

    ListAdapter (Adminmanage adminmanage) { this.adminmanage = adminmanage; }

    @Override
    public int getCount() {
        return adminmanage.amanage.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem{
        TextView name;
        TextView group;
        TextView position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)adminmanage.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_amanage, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.group = (TextView) convertView.findViewById(R.id.group);
            holder.position = (TextView) convertView.findViewById(R.id.position);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        holder.name.setText(this.adminmanage.amanage.get(position).name);
        holder.group.setText(this.adminmanage.amanage.get(position).group);
        holder.position.setText(this.adminmanage.amanage.get(position).position);

        return convertView;
    }
}

