package com.andrstudproj.androidappproject;


import android.widget.Button;

public class Holiday {
    //String variables for the hotel name, city, country, image title, month and details
    private String hotelName, city, country, image, month, details;
    //Double variable for the price
    private double price;
    //Integer variables for the unique id, day and year
    private int id, day, year;

    public Holiday(int id, String hotelName, String city, String country, double price, String image, int day, String month,
                   int year, String details) {
        //Set this instance's id value by passing it the "id" value passed to the parameter
        this.id = id;
        //Set this instance's hotelName value by passing it the "hotelName" value passed to the parameter
        this.hotelName = hotelName;
        //Set this instance's city value by passing it the "city" value passed to the parameter
        this.city = city;
        //Set this instance's country value by passing it the "country" value passed to the parameter
        this.country = country;
        //Set this instance's price value by passing it the "price" value passed to the parameter
        this.price = price;
        //Set this instance's image value by passing it the "image" value passed to the parameter
        this.image = image;
        //Set this instance's day value by passing it the "day" value passed to the parameter
        this.day = day;
        //Set this instance's month value by passing it the "month" value passed to the parameter
        this.month = month;
        //Set this instance's year value by passing it the "year" value passed to the parameter
        this.year = year;
        //Set this instance's details value by passing it the "details" value passed to the parameter
        this.details = details;
    }

    public String getHotelName() {
        //Return this instance's hotelName value
        return hotelName;
    }

    public String getCity() {
        //Return this instance's city value
        return city;
    }

    public String getCountry() {
        //Return this instance's country value
        return country;
    }

    public String getImage() {
        //Return this instance's image value
        return image;
    }

    public String getDetails() {
        //Return this instance's details value
        return details;
    }

    public double getPrice() {
        //Return this instance's price value
        return price;
    }

    public int getDay() {
        //Return this instance's day value
        return day;
    }

    public String getMonth() {
        //Return this instance's month value
        return month;
    }

    public int getYear() {
        //Return this instance's year value
        return year;
    }
}
