package com.andrstudproj.androidappproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HolidayFullDetails extends Fragment {
    //TextView reference variables for the hotel name, city, country, day, month, year, price and details
    TextView fdHotelName, fdCity, fdCountry, fdDay, fdMonth, fdYear, fdPrice, fdDetails;
    //Button reference variables for the backToList and toMainMenu buttons
    Button fdBackToList, fdToMainMenu;
    //ImageView reference variable for the image
    ImageView fdImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the holiday_full_details_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        View view = inflater.inflate(R.layout.holiday_full_details_fragment, container, false);
        //Define the "fdHotelName" TextView by referencing the "fd_hotelname" TextView element from the inflated view
        fdHotelName = view.findViewById(R.id.fd_hotelName);
        //Define the "fdCity" TextView by referencing the "fd_city" TextView element from the inflated view
        fdCity = view.findViewById(R.id.fd_city);
        //Define the "fdCountry" TextView by referencing the "fd_country" TextView element from the inflated view
        fdCountry = view.findViewById(R.id.fd_country);
        //Define the "fdDay" TextView by referencing the "fd_day" TextView element from the inflated view
        fdDay = view.findViewById(R.id.fd_day);
        //Define the "fdMonth" TextView by referencing the "fd_month" TextView element from the inflated view
        fdMonth = view.findViewById(R.id.fd_month);
        //Define the "fdYear" TextView by referencing the "fd_year" TextView element from the inflated view
        fdYear = view.findViewById(R.id.fd_year);
        //Define the "fdPrice" TextView by referencing the "fd_price" TextView element from the inflated view
        fdPrice = view.findViewById(R.id.fd_price);
        //Define the "fdDetails" TextView by referencing the "fd_details" TextView element from the inflated view
        fdDetails = view.findViewById(R.id.fd_details);
        //Define the "toForm" ImageView by referencing the "fd_image" ImageView element from the inflated view
        fdImage = view.findViewById(R.id.fd_image);
        //Define the "fdBackToList" Button by referencing the "fd_back_to_list" Button element from the inflated view
        fdBackToList = view.findViewById(R.id.fd_back_to_list);
        //Define the "fdToMainMenu" Button by referencing the "fd_to_main_menu" Button element from the inflated view
        fdToMainMenu = view.findViewById(R.id.fd_to_main_menu);
        //Define a local Bundle object by
        Bundle bundle = getArguments();
        //Define a String for the retrieved hotel name by calling getString on the bundle and passing it the "HotelName" key
        String hotelName = bundle.getString("HotelName");
        //Define a String for the retrieved hotel city by calling getString on the bundle and passing it the "HotelCity" key
        String hotelCity = bundle.getString("HotelCity");
        //Define a String for the retrieved hotel country by calling getString on the bundle and passing it the "HotelCountry" key
        String hotelCountry = bundle.getString("HotelCountry");
        //Define an integer for the retrieved hotel day by calling getString on the bundle and passing it the "HotelDay" key
        int hotelDay = bundle.getInt("HotelDay");
        //Define a String for the retrieved hotel month by calling getString on the bundle and passing it the "HotelMonth" key
        String hotelMonth = bundle.getString("HotelMonth");
        //Define an integer for the retrieved hotel year by calling getString on the bundle and passing it the "HotelYear" key
        int hotelYear = bundle.getInt("HotelYear");
        //Define a String for the retrieved hotel price by calling getString on the bundle and passing it the "HotelPrice" key
        double hotelPrice = bundle.getDouble("HotelPrice");
        //Define a String for the retrieved hotel image title by calling getString on the bundle and passing it the "HotelImage" key
        String hotelImage = bundle.getString("HotelImage");
        //Define a String for the retrieved hotel details by calling getString on the bundle and passing it the "HotelDetails" key
        String hotelDetails = bundle.getString("HotelDetails");
        //Set the text of the fdHotelName reference variable by calling setText and passing it the bundle's hotel name
        fdHotelName.setText(hotelName);
        //Set the text of the fdCity reference variable by calling setText and passing it the bundle's hotel city
        fdCity.setText(hotelCity);
        //Set the text of the fdCountry reference variable by calling setText and passing it the bundle's hotel country
        fdCountry.setText(hotelCountry);
        //Set the text of the fdDay reference variable by calling setText and passing it the bundle's hotel day
        fdDay.setText(""+hotelDay);
        //Set the text of the fdMonth reference variable by calling setText and passing it the bundle's hotel month
        fdMonth.setText(hotelMonth);
        //Set the text of the fdYear reference variable by calling setText and passing it the bundle's hotel year
        fdYear.setText(""+hotelYear);
        //Set the text of the fdPrice reference variable by calling setText and passing it the bundle's hotel price
        //In this case, a pound sign string plus the formatted hotel price value to round the decimal part of the double to 2 places
        fdPrice.setText("£" + (String.format("%.2f", hotelPrice)));
        //Set the source/URL of the ImageView reference by calling getResources().getIdentifier() to access the image file
        //...with the same name as the bundle's image title value
        //The getIdentifier function first defines the matching value it is searching for, then narrows it to the "drawable"
        //...subdirectory in the res(resources) file, then narrows the search further within the current activity's context's
        //...package name
        fdImage.setImageResource(getResources().getIdentifier(hotelImage, "drawable", getContext().getPackageName()));
        //Set the text of the fdDetails reference variable by calling setText and passing it the bundle's hotel details
        fdDetails.setText(hotelDetails);
        //Return the inflated and updated view
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Call the superclass' onViewCreated function
        super.onViewCreated(view, savedInstanceState);
        //Set up an OnClickListener to the "fdBackToList" Button
        fdBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the current activity and cast to MainActivity, then call the activity/class' displaySelectedScreen...
                //....function, passing it the unique int for R.id.nav_menu_page_5 to redirect back to the holiday listing page
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_menu_page_5);
            }
        });
        //Set up an OnClickListener to the "fdToMainMenu" Button
        fdToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the current activity and cast to MainActivity, then call the activity/class' displaySelectedScreen...
                //....function, passing it the unique int for R.id.nav_menu_main_page to redirect back to the main page
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_main_page);
            }
        });
    }
}
