package com.andrstudproj.androidappproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HolidaysAdapter extends ArrayAdapter {
    //Variable for the arraylist to store Holiday instances
    List list = new ArrayList();
    //The context of the parent (MenuPage5) page
    final private Context context;

    public HolidaysAdapter(Context context, int resource) {
        //Call the constructor of the superclass(ArrayAdapter), passing it both the context and the resource
        super(context, resource);
        //Set the context of this instance as the one passed to the parameter
        this.context = context;
    }

    public void add(Holiday toInsert) {
        //Call the add function of the superclass, passing it the Holiday object passed to the parameter
        super.add(toInsert);
        //Add the parameter Holiday instance to this instance's list
        list.add(toInsert);
    }

    @Override
    public int getCount() {
        //Return the list size
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //Return the object specified by the position passed to the parameter by calling the get() function on it
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Local variable for the current row view, defined as the convertView passed to the parameter (whether null or not)
        View row = convertView;
        //Instance of the HolidayHolder nested class
        HolidayHolder holder;
        //If the row is null
        if (row == null) {
            //Get the layoutInflater of the current activity by getting this context's layout inflater service, casting it
            //...to the LayoutInflater class
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Redefine the row as an inflated instance of the holiday_layout XML from the layouts subdirectory in the
            //...res(resources) directory, using the parameter's parent(MenuPage5) as the view group
            row = layoutInflater.inflate(R.layout.holiday_layout, parent, false);
            //Define the holder as a new HolidayHolder instance
            holder = new HolidayHolder();
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_name = (TextView) row.findViewById(R.id.h_name);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_city = (TextView) row.findViewById(R.id.h_city);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_country = (TextView) row.findViewById(R.id.h_country);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_price = (TextView) row.findViewById(R.id.h_price);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_day = (TextView) row.findViewById(R.id.h_day);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_month = (TextView) row.findViewById(R.id.h_month);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_year = (TextView) row.findViewById(R.id.h_year);
            //Define the HolidayHolder's name reference value as the "h_name" XML ImageView in the inflated holder/holiday_layout row
            holder.h_image = (ImageView) row.findViewById(R.id.h_image);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_details = (TextView) row.findViewById(R.id.h_details);
            //Define the HolidayHolder's name reference value as the "h_name" XML Button in the inflated holder/holiday_layout row
            holder.h_button = (Button) row.findViewById(R.id.toFullDetails);
            //Set the unique tag Object of the row as the edited HolidayHolder
            row.setTag(holder);
        } else {
            //Set the local HolidayHolder variable as the return value of the row's getTag, casted to the HolidayHolder class
            //Returns the HolidayHolder tagged to the row when the row was previously null
            holder = (HolidayHolder) row.getTag();
        }
        //Define a local Holiday instance as the return value of this instance's getItem function, which
        //...returns the item from the holidays ArrayList according to the position passed to the parameter
        Holiday holiday = (Holiday) this.getItem(position);
        //Set a local hotelName value as the return value of the holiday's getHotelName() function
        //Variables are final in order to be accessed by an upcoming setOnClickListener function
        final String hotelName = holiday.getHotelName();
        //Set a local city value as the return value of the holiday's getCity() function
        final String city = holiday.getCity();
        //Set a local country value as the return value of the holiday's getCountry() function
        final String country = holiday.getCountry();
        //Set a local price value as the return value of the holiday's getPrice() function
        final double price = holiday.getPrice();
        //Set a local day value as the return value of the holiday's getDay() function
        final int day = holiday.getDay();
        //Set a local month value as the return value of the holiday's getMonth() function
        final String month = holiday.getMonth();
        //Set a local year value as the return value of the holiday's getYear() function
        final int year = holiday.getYear();
        //Set a local holImage value as the return value of the holiday's getImage() function
        final String holImage = holiday.getImage();
        //Set a local details value as the return value of the holiday's getDetails() function
        final String details = holiday.getDetails();
        //Set the text of the holder's h_name TextView as the hotel name
        holder.h_name.setText(hotelName);
        //Set the text of the holder's h_city TextView as the city String
        holder.h_city.setText(city);
        //Set the text of the holder's h_country TextView as the country String
        holder.h_country.setText(country);
        //Set the text of the holder's h_price TextView as a £ String followed by a formatted price to limit
        //...the number of decimal places to 2
        holder.h_price.setText("£" + (String.format("%.2f", price)));
        //Set the text of the holder's h_day TextView as the day integer, converted to String
        holder.h_day.setText(Integer.toString(day));
        //Set the text of the holder's h_month TextView as the month String
        holder.h_month.setText(month);
        //Set the text of the holder's h_year TextView as the year integer, converted to String
        holder.h_year.setText(Integer.toString(year));
        //Set the source/URL of the holder's h_image reference by calling getResources().getIdentifier() to access the image file
        //...with the same name as the bundle's image title value
        //The getIdentifier function first defines the matching value it is searching for, then narrows it to the "drawable"
        //...subdirectory in the res(resources) file, then narrows the search further within the current activity's context's
        //...package name
        holder.h_image.setImageResource(context.getResources().getIdentifier(holImage, "drawable", (context.getPackageName())));
        //Set the text of the holder's h_details TextView as the details String
        holder.h_details.setText(details);
        //Set an OnClickListener to the holder's h_button Button reference
        holder.h_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a Bundle to store variables to pass to a new view
                Bundle bundle = new Bundle();
                //
                bundle.putString("HotelName", hotelName);
                bundle.putString("HotelCity", city);
                bundle.putString("HotelCountry", country);
                bundle.putDouble("HotelPrice", price);
                bundle.putInt("HotelDay", day);
                bundle.putString("HotelMonth", month);
                bundle.putInt("HotelYear", year);
                bundle.putString("HotelImage", holImage);
                bundle.putString("HotelDetails", details);
                //Define a FragmentTransaction, in which the (casted to FragmentActivity) context's SupportFragmentManager
                //...will begin a transaction which will allow the replacement of the current view as well as attachment
                //...of arguments to the new page that can be access on view inflation
                FragmentTransaction transact = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                //Create a new instance of HolidayFullDetails, in which the view will not be inflated until the transaction is committed
                HolidayFullDetails fullDetails = new HolidayFullDetails();
                //Pass the Bundle of variables to the HolidayFullDetails instance
                fullDetails.setArguments(bundle);
                //Prepare the FragmentTransaction to replace the content_main CoordinatorLayout in the content_main
                //...XML in the layout resource subdirectory with the fullDetails instance
                transact.replace(R.id.content_main, fullDetails);
                //Now commit the transaction
                transact.commit();
            }
        });
        //Return the inflated and edited row
        return row;
    }

    static class HolidayHolder {
        //TextView references to the hotel name, city, country, price, day, month, year and details
        TextView h_name, h_city, h_country, h_price, h_day, h_month, h_year, h_details;
        //ImageView reference to the hotel image
        ImageView h_image;
        //Button reference to the button
        Button h_button;
    }
}
