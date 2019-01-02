package com.example.adrien.madrental;

public class RentalDTO {

    public String name = null;
    public String image = null;
    public Integer price = null;
    public String startDate = null;
    public String endDate = null;

    //Constructor
    public RentalDTO(String name, String image, Integer price, String startDate, String endDate)
    {
        this.name = name;
        this.image = image;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
