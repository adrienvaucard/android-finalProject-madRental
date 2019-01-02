package com.example.adrien.madrental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalDAO {

    public static List<RentalDTO> getRentals(Context context) {
        //Columns which will be return after query
        String[] projection = {BaseContract.RentalContract._ID,
                BaseContract.RentalContract.COLONNE_NAME,
                BaseContract.RentalContract.COLONNE_IMAGE,
                BaseContract.RentalContract.COLONNE_PRICE,
                BaseContract.RentalContract.COLONNE_STARTDATE,
                BaseContract.RentalContract.COLONNE_ENDDATE};


        //Read-Only Access
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //Query
        Cursor cursor = db.query(
                BaseContract.RentalContract.TABLE_RENTALS,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);


        //Create Rentals List
        List<RentalDTO> rentalsList = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    String name = cursor.getString(cursor.getColumnIndex(BaseContract.RentalContract.COLONNE_NAME));
                    String image = cursor.getString(cursor.getColumnIndex(BaseContract.RentalContract.COLONNE_IMAGE));
                    Integer price = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BaseContract.RentalContract.COLONNE_PRICE)));
                    String startDate = cursor.getString(cursor.getColumnIndex(BaseContract.RentalContract.COLONNE_STARTDATE));
                    String endDate = cursor.getString(cursor.getColumnIndex(BaseContract.RentalContract.COLONNE_ENDDATE));

                    //Convert values into object
                    rentalsList.add(new RentalDTO(name, image, price, startDate, endDate));
                    cursor.moveToNext();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }

        return rentalsList;
    }

    //Add Rental in Database
    public static long addRental(Context context, String name, String image, Integer price, String startDate, String endDate)
    {
        //Access Database
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //Values
        ContentValues values = new ContentValues();
        values.put(BaseContract.RentalContract.COLONNE_NAME, name);
        values.put(BaseContract.RentalContract.COLONNE_IMAGE, image);
        values.put(BaseContract.RentalContract.COLONNE_PRICE, price);
        values.put(BaseContract.RentalContract.COLONNE_STARTDATE, startDate);
        values.put(BaseContract.RentalContract.COLONNE_ENDDATE, endDate);

        //add in database
        return db.insert(BaseContract.RentalContract.TABLE_RENTALS, null, values);
    }
}
