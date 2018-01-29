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
    //Variable for the current locale
    String currentLocale;

    public HolidaysAdapter(Context context, int resource, String currentLocale) {
        //Call the constructor of the superclass(ArrayAdapter), passing it both the context and the resource
        super(context, resource);
        //Set the context of this instance as the one passed to the parameter
        this.context = context;
        //Set the Locale String as the value passed to the parameter
        this.currentLocale = currentLocale;
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
        //Instance of the HolidayHolder nested class
        HolidayHolder holder;
        //If the current row View is null
        if (convertView == null) {
            //Get the layoutInflater of the current activity by getting this context's layout inflater service, casting it
            //...to the LayoutInflater class
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Redefine the row as an inflated instance of the holiday_layout XML from the layouts subdirectory in the
            //...res(resources) directory, using the parameter's parent(MenuPage5) as the view group
            convertView = layoutInflater.inflate(R.layout.holiday_layout, parent, false);
            //Define the holder as a new HolidayHolder instance
            holder = new HolidayHolder();
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_name = (TextView) convertView.findViewById(R.id.h_name);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_city = (TextView) convertView.findViewById(R.id.h_city);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_country = (TextView) convertView.findViewById(R.id.h_country);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_price = (TextView) convertView.findViewById(R.id.h_price);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_day = (TextView) convertView.findViewById(R.id.h_day);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_month = (TextView) convertView.findViewById(R.id.h_month);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_year = (TextView) convertView.findViewById(R.id.h_year);
            //Define the HolidayHolder's name reference value as the "h_name" XML ImageView in the inflated holder/holiday_layout row
            holder.h_image = (ImageView) convertView.findViewById(R.id.h_image);
            //Define the HolidayHolder's name reference value as the "h_name" XML TextView in the inflated holder/holiday_layout row
            holder.h_details = (TextView) convertView.findViewById(R.id.h_details);
            //Define the HolidayHolder's name reference value as the "h_name" XML Button in the inflated holder/holiday_layout row
            holder.h_button = (Button) convertView.findViewById(R.id.toFullDetails);
            //Set the unique tag Object of the row as the edited HolidayHolder
            convertView.setTag(holder);
        } else {
            //Set the local HolidayHolder variable as the return value of the row's getTag, casted to the HolidayHolder class
            //Returns the HolidayHolder tagged to the row when the row was previously null
            holder = (HolidayHolder) convertView.getTag();
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
        //Set the text of the holder's h_day TextView as the day integer, converted to String
        holder.h_day.setText(Integer.toString(day));
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
        //Set the text of the holder's h_month TextView as the month String
        holder.h_month.setText(month);
        //If the current device language is Danish, German, Spanish, French, Italian, Dutch or Portuguese
        if (currentLocale.equals("de") || currentLocale.equals("es") || currentLocale.equals("fr")
                || currentLocale.equals("it") || currentLocale.equals("nl") || currentLocale.equals("pt")
                ) {
            //Set the base Price value as the Euro symbol
            holder.h_price.setText("€" + String.format("%.2f", price));
            //If the current device language is Russian
        } else if (currentLocale.equals("ru")) {
            //Set the base Price value as the Russian Ruble symbol
            holder.h_price.setText("\u20BD" + String.format("%.2f", price));
        //If the current device language is Danish
        } else if (currentLocale.equals("da")) {
            //Set the base Price value as the Danish Krone symbol
            holder.h_price.setText("kr" + String.format("%.2f", price));
            //If the current device language is Chinese
        } else if (currentLocale.equals("zh")) {
            //Set the base Price value as the Chinese Yuan symbol
            holder.h_price.setText("￥" + String.format("%.2f", price));
         //If the Locale string is either American, Canadian, Australian or New Zealand
        } else if (currentLocale.equalsIgnoreCase("en_us") || currentLocale.equalsIgnoreCase("en_ca") || currentLocale.equalsIgnoreCase("en_au")
                || currentLocale.equalsIgnoreCase("en_nz")){
                //Set the base Price value as the Dollar
                holder.h_price.setText("$" + String.format("%.2f", price));
        //Otherwise
        } else {
            //Set the base Price value as the British pound
            holder.h_price.setText("£" + String.format("%.2f", price));
        }
        //Set an OnClickListener to the holder's h_button Button reference
        holder.h_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a Bundle to store variables to pass to a new view
                Bundle bundle = new Bundle();
                //Insert a key/pair containing the hotel name and the tag value of "HotelName" into the bundle
                bundle.putString("HotelName", hotelName);
                //Insert a key/pair containing the hotel city and the tag value of "HotelName" into the bundle
                bundle.putString("HotelCity", city);
                //Insert a key/pair containing the hotel country and the tag value of "HotelName" into the bundle
                bundle.putString("HotelCountry", country);
                //Insert a key/pair containing the hotel price and the tag value of "HotelName" into the bundle
                bundle.putDouble("HotelPrice", price);
                //Insert a key/pair containing the day of the date and the tag value of "HotelName" into the bundle
                bundle.putInt("HotelDay", day);
                //Insert a key/pair containing the month of the date and the tag value of "HotelName" into the bundle
                bundle.putString("HotelMonth", month);
                //Insert a key/pair containing the year of the date and the tag value of "HotelName" into the bundle
                bundle.putInt("HotelYear", year);
                //Insert a key/pair containing the hotel image and the tag value of "HotelName" into the bundle
                bundle.putString("HotelImage", holImage);
                //Insert a key/pair containing the hotel details and the tag value of "HotelName" into the bundle
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
        //Refresh this adapter by calling it's notifyDataSetChanged method
        notifyDataSetChanged();
        //Return the inflated and edited row
        return convertView;
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
