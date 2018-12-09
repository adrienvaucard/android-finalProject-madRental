package com.example.adrien.madrental;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingState1 extends AppCompatActivity {

    public Integer id;
    public String name;
    public String image;
    public Integer available;
    public Integer baseDailyPrice;
    public Integer sale;
    public Integer ageMin;
    public String co2Category;
    public JSONArray equipments;
    public JSONArray options;

    //Initialize lists of equipments and options
    public List<String> equipmentsList = new ArrayList<String>();
    public ArrayList<HashMap<String, String>> optionsList = new ArrayList<HashMap<String, String>>();

    //Initialize view ids
    public TextView vId;
    public TextView vName;
    public ImageView vImage;
    public TextView vAvailable;
    public TextView vDailyPrice;
    public TextView vSale;
    public TextView vAgeMin;
    public TextView vCategory;
    public ListView vEquipments;
    public ListView vOptions;
    public ConstraintLayout vVehicleInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_state1);

        //Catch ids
        vName = findViewById(R.id.vehicleName);
        vImage = findViewById(R.id.vehicleImage);
        vDailyPrice = findViewById(R.id.dailyPrice);
        vCategory = findViewById(R.id.category);
        vVehicleInfos = findViewById(R.id.vehicleInfos);
        vEquipments = findViewById(R.id.equipments);
        vOptions = findViewById(R.id.options);

        //Get parameters from RecyclerView
        id = getIntent().getIntExtra("searchId", 0);
        name = getIntent().getStringExtra("searchName");
        image = getIntent().getStringExtra("searchImage");
        available = getIntent().getIntExtra("searchAvailable", 0);
        baseDailyPrice = getIntent().getIntExtra("searchBaseDailyPrice", 0);
        sale = getIntent().getIntExtra("searchSale", 0);
        ageMin = getIntent().getIntExtra("searchAgeMin", 0);
        co2Category = getIntent().getStringExtra("searchCo2Category");
        try {
            equipments = new JSONArray(getIntent().getStringExtra("searchEquipments"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            options = new JSONArray(getIntent().getStringExtra("searchOptions"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Set values in Layout
        vName.setText(name);
        Picasso.with(this)
                .load("http://s519716619.onlinehome.fr/exchange/madrental/images/" + image)
                .fit()
                .centerCrop() // ou centerInside()
                .into(vImage);
        vDailyPrice.setText(baseDailyPrice.toString() + " € / jour");
        vCategory.setText(co2Category);


        //Print vehicle equipments names
        for (int i = 0; i < equipments.length(); i++) {
            try {
                equipmentsList.add(equipments.getJSONObject(i).getString("nom"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final ArrayAdapter<String> equipmentsAdapter = new ArrayAdapter<String>(BookingState1.this,
                R.layout.vehicle_equipment, equipmentsList);
        vEquipments.setAdapter(equipmentsAdapter);


        //Print vehicle options
        HashMap<String, String> map;

        for (int i = 0; i < options.length(); i++) {
            try {
                map = new HashMap<String, String>();
                map.put("nom", options.getJSONObject(i).getString("nom") + " : ");
                map.put("prix", options.getJSONObject(i).getString("prix") + " €");
                optionsList.add(map);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        OptionsAdapter optionsAdapter = new OptionsAdapter(BookingState1.this, optionsList,
                R.layout.vehicle_option, new String[]{"nom", "prix"}, new int[]{
                R.id.saleToggleButtonLabel, R.id.saleToggleButtonPrice});
        vOptions.setAdapter(optionsAdapter);


    }

}
