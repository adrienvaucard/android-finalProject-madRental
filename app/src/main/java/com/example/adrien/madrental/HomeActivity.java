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
        String regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if (editTextStartReservation.toString().matches(regexp)) {

            editTextStartReservation.setBackgroundResource(R.drawable.edit_text);
            if (editTextEndReservation.toString().matches(regexp)) {

                editTextEndReservation.setBackgroundResource(R.drawable.edit_text);
                Intent intent = new Intent(this, SearchesActivity.class);
                startActivity(intent);
            }
            else {
                editTextEndReservation.setBackgroundResource(R.drawable.edit_text_red);
            }
        }
        else {
            editTextStartReservation.setBackgroundResource(R.drawable.edit_text_red);
        }


    }

}
