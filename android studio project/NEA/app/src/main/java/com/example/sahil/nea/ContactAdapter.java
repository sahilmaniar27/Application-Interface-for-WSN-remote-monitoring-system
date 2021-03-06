package com.example.sahil.nea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sahil on 30-04-2017.
 */
public class ContactAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Contacts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView)row.findViewById(R.id.tx_name);
            contactHolder.tx_location = (TextView)row.findViewById(R.id.tx_location);
            contactHolder.tx_time = (TextView)row.findViewById(R.id.tx_time);
            row.setTag(contactHolder);
        }
        else
        {
            contactHolder = (ContactHolder)row.getTag();
        }

        Contacts contacts = (Contacts)this.getItem(position);
        contactHolder.tx_name.setText(contacts.getName());
        contactHolder.tx_location.setText(contacts.getLocation());
        contactHolder.tx_time.setText(contacts.getTime());


        return row;
    }

    static class ContactHolder
    {
        TextView tx_name,tx_location,tx_time;
    }
}
