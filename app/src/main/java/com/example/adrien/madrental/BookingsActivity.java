package com.example.adrien.madrental;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingsAdapter bookingsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        //Initialize return button in action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Catch IDs
        recyclerView = findViewById(R.id.bookingRecyclerView);

        //Better performances
        recyclerView.setHasFixedSize(true);

        //Layout Manager, define how elements are disposed
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Retrieve Database values
        List<RentalDTO> rentalsList = null;
        rentalsList = RentalDAO.getRentals(this);

        //Adapter
        bookingsAdapter = new BookingsAdapter(rentalsList);
        recyclerView.setAdapter(bookingsAdapter);

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


