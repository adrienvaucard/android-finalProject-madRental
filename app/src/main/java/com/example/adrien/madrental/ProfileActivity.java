package com.example.adrien.madrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    public EditText firstName;
    public EditText surname;
    public EditText birthDate;
    public Button applyProfileChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Iniitialize SharedPreferences
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        //Catch Ids
        firstName = findViewById(R.id.editTextProfileFirstName);
        surname = findViewById(R.id.editTextProfileName);
        birthDate = findViewById(R.id.editTextProfileBirthDate);
        applyProfileChanges = findViewById(R.id.applyProfileChanges);

        if (preferences.getString("firstName", "") != "" ) {
            firstName.setText(preferences.getString("firstName", ""));
        }

        if (preferences.getString("surname", "") != "" ) {
            surname.setText(preferences.getString("surname", ""));
        }

        if (preferences.getString("birthDate", "") != "" ) {
            birthDate.setText(preferences.getString("birthDate", ""));
        }

        applyProfileChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    date = sdf.parse(birthDate.getText().toString());
                    if (!birthDate.getText().toString().equals(sdf.format(date))) {
                        date = null;
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if (date == null) {
                    Toast.makeText(ProfileActivity.this, "Erreur de format de date", Toast.LENGTH_SHORT).show();
                } else {
                    //Save user informations
                    editor.putString("surname", surname.getText().toString());
                    editor.putString("firstName", firstName.getText().toString());
                    editor.putString("birthDate", birthDate.getText().toString());
                    editor.apply();
                    editor.commit();

                    Toast.makeText(ProfileActivity.this, "Informations enregistrées", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });




    }
}
