package com.example.adrien.madrental;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsAdapter extends SimpleAdapter
{
    private LayoutInflater mInflater;

    public OptionsAdapter (Context context, List<? extends Map<String, ?>> data,
                          int resource, String[] from, int[] to)
    {
        super (context, data, resource, from, to);
        mInflater = LayoutInflater.from (context);

    }

    @Override
    public Object getItem (int position)
    {
        return super.getItem (position);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        //Test if view is created, to not recreate it
        if (convertView == null)
        {
            //Get View Elements
            convertView = mInflater.inflate (R.layout.vehicle_option, null);

            //Get ToggleButton ID
            ToggleButton tb = convertView.findViewById (R.id.saleToggleButton);

            //Set tag to retrieve it later
            tb.setTag ("toggle" + position);

            Log.i("Tag", tb.getTag().toString());

        }


        return super.getView (position, convertView, parent);
    }

}
