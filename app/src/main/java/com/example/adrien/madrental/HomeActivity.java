package com.example.adrien.madrental;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //catch ID
        appTitle = findViewById(R.id.appTitle);

        //Assign custom font to App Title
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/BreeSerif-Regular.ttf");
        //appTitle.setTypeface(font);
    }
}
