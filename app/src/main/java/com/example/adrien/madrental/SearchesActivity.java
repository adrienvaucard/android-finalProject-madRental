package com.example.adrien.madrental;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchesAdapter searchesAdapter = null;
    private JSONArray calledJSON;
    private JSONObject forJsonObject;
    private Switch saleSwitch;
    final List<Search> searchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Initialize SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Retrieve User Birth Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(preferences.getString("birthDate", "").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Calculate user's age
        Date today = new Date();
        try {
            today = sdf.parse(sdf.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = today.getTime() - birthDate.getTime();
        final int age = Math.round(diff / 1000/60/60/24/365);


        //Catch IDs
        recyclerView = findViewById(R.id.searchRecyclerView);
        saleSwitch = findViewById(R.id.saleSwitch);

        //Better performances
        recyclerView.setHasFixedSize(true);

        //Layout Manager, define how elements are disposed
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        callJSON(age, false);

        saleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                searchList.clear();
                if (isChecked) {
                    callJSON(age, true);
                }
                else {
                    callJSON(age, false);
                }
            }
        });


    }

    public static boolean isConnected(Context context) {
        //Get Manager
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get Connection state
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    public void callJSON(final Integer age, final Boolean promotion) {
        //Get JSON from webservice
        if (isConnected(SearchesActivity.this)) {

            //Http Client
            AsyncHttpClient client = new AsyncHttpClient();

            //Parameters
            RequestParams requestParams = new RequestParams();
            requestParams.put("agemin", age);
            requestParams.put("promotion", promotion);

            //Call
            client.get("http://s519716619.onlinehome.fr/exchange/madrental/get-vehicules.php", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] response) {
                    String vehiclesList = new String(response);
                    try {
                        //Put Webservice return in local variable
                        calledJSON = new JSONArray(vehiclesList);

                        final Date startDate = new Date(getIntent().getStringExtra("startDate"));
                        final Date endDate = new Date(getIntent().getStringExtra("endDate"));

                        //Iterate on JSONArray to create differents vehicles
                        for (int i = 0; i < calledJSON.length(); i++) {
                            try {
                                forJsonObject = calledJSON.getJSONObject(i);
                                Integer vID = Integer.parseInt(forJsonObject.getString("id"));
                                String vName = forJsonObject.getString("nom");
                                String vImage = forJsonObject.getString("image");
                                Integer vAvailable = Integer.parseInt(forJsonObject.getString("disponible"));
                                Integer vBaseDailyPrice = Integer.parseInt(forJsonObject.getString("prixjournalierbase"));
                                Integer vSale = Integer.parseInt(forJsonObject.getString("promotion"));
                                Integer vAgeMin = Integer.parseInt(forJsonObject.getString("agemin"));
                                String vCO2Category = forJsonObject.getString("categorieco2");
                                JSONArray vEquipments = forJsonObject.getJSONArray("equipements");
                                JSONArray vOptions = forJsonObject.getJSONArray("options");

                                if (age >= vAgeMin) {
                                    if (promotion) {
                                        if (vSale != 0) {
                                            searchList.add(new Search(vID, vName, vImage, vAvailable, vBaseDailyPrice, vSale, vAgeMin, vCO2Category, vEquipments, vOptions, startDate, endDate));
                                        }
                                    }
                                    else {
                                        searchList.add(new Search(vID, vName, vImage, vAvailable, vBaseDailyPrice, vSale, vAgeMin, vCO2Category, vEquipments, vOptions, startDate, endDate));
                                    }
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //Adapter
                        searchesAdapter = new SearchesAdapter(searchList);
                        recyclerView.setAdapter(searchesAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] errorResponse, Throwable e) {
                    Log.e("Error", e.toString());
                }
            });

        }
    }

}
