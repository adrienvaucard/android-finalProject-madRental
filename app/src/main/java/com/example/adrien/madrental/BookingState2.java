package com.example.adrien.madrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookingState2 extends AppCompatActivity {

    public Integer vId;
    public String vName;
    public String vImage;
    public Integer vTotalPrice;

    public TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_state2);

        vId = getIntent().getIntExtra("vId", 0);
        vName = getIntent().getStringExtra("vName");
        vImage = getIntent().getStringExtra("vImage");
        vTotalPrice = getIntent().getIntExtra("vTotalPrice", 0);

        totalPrice = findViewById(R.id.totalPriceText);
        totalPrice.setText("Prix final : " + vTotalPrice + " €");


    }
}
