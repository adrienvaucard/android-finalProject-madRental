package com.example.adrien.madrental;

import android.provider.BaseColumns;

public class BaseContract {

    //Private constructor
    private BaseContract() {}

    //Define Rental Table
    public static class RentalContract implements BaseColumns
    {
        public static final String TABLE_RENTALS = "rentals";
        public static final String COLONNE_NAME = "name";
        public static final String COLONNE_IMAGE = "image";
        public static final String COLONNE_PRICE = "price";
        public static final String COLONNE_STARTDATE = "startDate";
        public static final String COLONNE_ENDDATE = "endDate";
    }
}
