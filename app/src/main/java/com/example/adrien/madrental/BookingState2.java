package com.example.adrien.madrental;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class BookingState2 extends AppCompatActivity {

    public Integer vId;
    public String vName;
    public String vImage;
    public Integer vTotalPrice;
    public Date startDate;
    public Date endDate;

    public TextView totalPrice;
    public Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_state2);

        //Initialize return button in action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Retrieve intent values
        vId = getIntent().getIntExtra("vId", 0);
        vName = getIntent().getStringExtra("vName");
        vImage = getIntent().getStringExtra("vImage");
        vTotalPrice = getIntent().getIntExtra("vTotalPrice", 0);
        startDate = new Date(getIntent().getStringExtra("startDate"));
        endDate = new Date(getIntent().getStringExtra("endDate"));

        //Assign price to view
        totalPrice = findViewById(R.id.totalPriceText);
        totalPrice.setText("Prix final : " + vTotalPrice + " €");

        //Event Listener to validate rental
        validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookingState2.this, "Location prise en compte, bonne route !", Toast.LENGTH_SHORT).show();

                RentalDAO.addRental(BookingState2.this, vName, vImage, vTotalPrice, startDate.toString(), endDate.toString());

                Intent intent = new Intent(BookingState2.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.page_slide_vertical_in,
                        R.anim.page_slide_vertical_out);


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.page_slide_horizontal_out,
                R.anim.page_slide_horizontal_back);
    }
}
