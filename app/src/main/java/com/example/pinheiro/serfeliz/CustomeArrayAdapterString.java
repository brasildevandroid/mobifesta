package com.example.pinheiro.serfeliz;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomeArrayAdapterString extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> arrString;
    private LayoutInflater inflater;

    public CustomeArrayAdapterString(Activity context,
                                     ArrayList<String> arrString) {
        super(context, R.layout.single_item,arrString);
        this.context = context;
        this.arrString = arrString;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.single_item, null);
        TextView tv= (TextView)convertView.findViewById(R.id.tv);
        ImageView img = (ImageView)convertView.findViewById(R.id.imageView1);
        tv.setText(arrString.get(position));
        img.setImageResource(R.drawable.bartender);
        return convertView;

    }
}
