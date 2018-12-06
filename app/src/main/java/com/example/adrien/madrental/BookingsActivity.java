package com.example.adrien.madrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingsAdapter bookingsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        //Catch IDs
        recyclerView = findViewById(R.id.bookingRecyclerView);

        //Better performances
        recyclerView.setHasFixedSize(true);

        //Layout Manager, define how elements are disposed
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // contenu d'exemple :
        final List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("Buy Baguette"));
        bookingList.add(new Booking("Go to Barbershop"));

        //Adapter
        bookingsAdapter = new BookingsAdapter(bookingList);
        recyclerView.setAdapter(bookingsAdapter);
    }
}
