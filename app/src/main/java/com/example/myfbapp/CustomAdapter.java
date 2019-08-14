package com.example.myfbapp;

//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Item> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent, false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.mName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.mEmail = (TextView) convertView.findViewById(R.id.tvMail);
            viewHolder.mPassword = (TextView) convertView.findViewById(R.id.tvPass);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        Item person = data.get(position);//modify here
        viewHolder.mName.setText(person.getName());//modify here
        viewHolder.mEmail.setText(person.getEmail());//modify here
        viewHolder.mPassword.setText(person.getPassword());//modify here
        return convertView;

    }

    static class ViewHolder {
        TextView mName;
        TextView mEmail;
        TextView mPassword;
    }
}