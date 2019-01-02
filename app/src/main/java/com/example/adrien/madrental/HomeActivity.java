package com.example.adrien.madrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private TextView appTitle;
    private Button bookings;
    private EditText editTextStartReservation;
    private EditText editTextEndReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //catch ID
        appTitle = findViewById(R.id.appTitle);
        bookings = findViewById(R.id.bookings);
        editTextStartReservation = findViewById(R.id.editTextStartReservation);
        editTextEndReservation = findViewById(R.id.editTextEndReservation);
    }

    public void goToBookings(View view) {
        Intent intent = new Intent(this, BookingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.page_slide_horizontal_in,
                R.anim.page_slide_horizontal_out);
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.page_slide_horizontal_in,
                R.anim.page_slide_horizontal_out);
    }

    public void goToSearch(View view ) {

        String startDate = editTextStartReservation.getText().toString();
        String endDate = editTextEndReservation.getText().toString();

        //Initialize SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Create Database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        //Format Dates
        Date date1 = null;
        Date date2 = null;
        Date today = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date1 = sdf.parse(startDate);
            date2 = sdf.parse(endDate);
            today = sdf.parse(sdf.format(today));
            if (!startDate.equals(sdf.format(date1)) || !endDate.equals(sdf.format(date2))) {
                date1 = null;
                date2 = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        //Test if dates match with format required and if age is indicated
        if (date1 == null || date2 == null) {
            Toast.makeText(this, "Erreur de format de date", Toast.LENGTH_SHORT).show();
        }
        else if (preferences.getString("birthDate", "") == "") {
            Toast.makeText(this, "Veuillez indiquer votre date de naissance dans votre profil", Toast.LENGTH_SHORT).show();
        }
        else if (date1.getTime() - today.getTime() < 0 || date2.getTime() - today.getTime() < 0) {
            Toast.makeText(this, "Veuillez rentrer une date à venir", Toast.LENGTH_SHORT).show();
            Log.i("startDate", date1.toString());
            Log.i("today", today.toString());
        }
        else {
            Intent intent = new Intent(this, SearchesActivity.class);
            intent.putExtra("startDate", date1.toString());
            intent.putExtra("endDate", date2.toString());
            startActivity(intent);
            overridePendingTransition(R.anim.page_slide_horizontal_in,
                    R.anim.page_slide_horizontal_out);
        }


    }

}
