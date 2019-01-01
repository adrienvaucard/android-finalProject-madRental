package com.example.adrien.madrental;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsAdapter extends SimpleAdapter {
    private LayoutInflater mInflater;
    private BookingState1 bookingState1;

    public OptionsAdapter(Context context, List<? extends Map<String, ?>> data,
                          int resource, String[] from, int[] to, BookingState1 bookingActivityState1) {
        super(context, data, resource, from, to);
        mInflater = LayoutInflater.from(context);
        this.bookingState1 = bookingActivityState1;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Test if view is created, to not recreate it
        if (convertView == null) {
            HashMap<String, String> vehicleInfos = (HashMap<String, String>) getItem(position);
            String vID = vehicleInfos.get("id");
            final Integer vPrice = Integer.parseInt(vehicleInfos.get("rawPrice"));

            //Get View Elements
            convertView = mInflater.inflate(R.layout.vehicle_option, null);

            //Get ToggleButton ID
            final Switch tb = convertView.findViewById(R.id.saleToggleButton);

            //Set tag to retrieve it later
            tb.setTag(vID);
            tb.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    if (tb.isChecked()) {
                        Integer newPrice = bookingState1.getTotalPrice() + vPrice;
                        bookingState1.setTotalPrice(newPrice);

                    } else {
                        Integer newPrice = bookingState1.getTotalPrice() - vPrice;
                        bookingState1.setTotalPrice(newPrice);
                    }
                }
            });
        }
        return super.getView(position, convertView, parent);
    }

}
