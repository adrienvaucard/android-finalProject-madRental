package com.example.adrien.madrental;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    //Constructor
    public DatabaseHelper(Context context)
    {
        super(context, "rentals.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + BaseContract.RentalContract.TABLE_RENTALS + " ("
                + BaseContract.RentalContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BaseContract.RentalContract.COLONNE_NAME + " TEXT, "
                + BaseContract.RentalContract.COLONNE_IMAGE + " TEXT, "
                + BaseContract.RentalContract.COLONNE_PRICE + " INTEGER, "
                + BaseContract.RentalContract.COLONNE_STARTDATE + " TEXT, "
                + BaseContract.RentalContract.COLONNE_ENDDATE + " TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + BaseContract.RentalContract.TABLE_RENTALS);
        onCreate(db);
    }
}
