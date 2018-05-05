package com.mbobiosio.genderfromserver;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<DataObject> {
    private Activity context;
    ArrayList<DataObject> data = null;

    public SpinnerAdapter(Activity context, int resource,
                          ArrayList<DataObject> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        }

        DataObject item = data.get(position);

        if (item != null) { // Parse the data from each object and set it.

            TextView genderDetail = row.findViewById(R.id.item_value);

            if (genderDetail != null) {
                genderDetail.setText(item.getName());
            }

        }

        return row;
    }
}