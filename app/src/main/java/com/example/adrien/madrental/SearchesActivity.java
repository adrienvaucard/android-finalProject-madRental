package com.example.adrien.madrental;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchesAdapter searchesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Catch IDs
        recyclerView = findViewById(R.id.searchRecyclerView);

        //Better performances
        recyclerView.setHasFixedSize(true);

        //Layout Manager, define how elements are disposed
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // contenu d'exemple :
        final List<Search> searchList = new ArrayList<>();
        searchList.add(new Search("Buy Baguette"));
        searchList.add(new Search("Go to Barbershop"));

        //Adapter
        searchesAdapter = new SearchesAdapter(searchList);
        recyclerView.setAdapter(searchesAdapter);

    }
}
