package com.example.adrien.madrental;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        //Assign custom font to App Title
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/BreeSerif-Regular.ttf");
        //appTitle.setTypeface(font);
    }

    public void goToBookings(View view) {
        Intent intent = new Intent(this, BookingsActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToSearch(View view) {

        Intent intent = new Intent(this, SearchesActivity.class);
        startActivity(intent);
    }

}
