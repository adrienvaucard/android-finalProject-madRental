package com.example.adrien.madrental;

import org.json.JSONArray;

import java.util.Date;

public class Search
{
    // Attributes :
    public Integer id;
    public String name;
    public String image;
    public Integer available;
    public Integer baseDailyPrice;
    public Integer sale;
    public Integer ageMin;
    public String co2Category;
    public JSONArray equipments;
    public JSONArray options;
    public Date startDate;
    public Date endDate;

    // Constructor :
    public Search(Integer id, String name, String image, Integer available, Integer baseDailyPrice, Integer sale, Integer ageMin, String co2Category, JSONArray equipments, JSONArray options, Date startDate, Date endDate )
    {
        this.id = id;
        this.name = name;
        this.image = image;
        this.available = available;
        this.baseDailyPrice = baseDailyPrice;
        this.sale = sale;
        this.ageMin = ageMin;
        this.co2Category = co2Category;
        this.equipments = equipments;
        this.options = options;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
