package com.andrstudproj.androidappproject;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.Scanner;

public class MenuPage5 extends Fragment {
    //ListView for displaying a list of holidays
    ListView listView;
    //Variable for the current locale
    Locale currentLocale;
    //Instance of HolidaysAdapter to insert Holiday instances/layouts in the ListView
    HolidaysAdapter holidaysAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.menu_page_5_fragment, container, false);
        //Set up this instance's list view as the listOfHolidays ListView in the menu_page_5_fragment xml in the layouts
        //...subdirectory in res(resources)
        listView = (ListView) view.findViewById(R.id.listOfHolidays);
        //If the device's Android software version is larger than/equal to Nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            //Set the current Locale this way
            currentLocale = getResources().getConfiguration().getLocales().get(0);
            //Otherwise
        } else{
            //Set the current Locale this way, as this version was deprecated following the introduction of Nougat
            currentLocale = getResources().getConfiguration().locale;
        }
        //Set up this instance's holidaysAdapter as a new instance, passing it this instance's context and the holiday_layout
        //...from the layout subdirectory in res(resources)
        holidaysAdapter = new HolidaysAdapter(MenuPage5.this.getContext(), R.layout.holiday_layout, currentLocale.getLanguage().toString());
        //holidaysAdapter = new HolidaysAdapter(getActivity().getApplicationContext(), R.layout.holiday_layout, currentLocale.getLanguage().toString());
        //Set the list view's adapter as the holidaysAdapter, allowing all holidays to be listed
        //listView.setAdapter(holidaysAdapter);
        //Return the inflated and edited view
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Call the constructor of the superclass(Fragment)
        super.onViewCreated(view, savedInstanceState);
        //Create an inputStream, which will read the "holidays" JSON file in the "raw" subdirectory in res(resources)
        InputStream inputStream = getResources().openRawResource(R.raw.holidays);
        //Create a scanner, which will utilise the inputstream
        Scanner scanner = new Scanner(inputStream);
        //Create a stringbuilder, which will write Strings
        StringBuilder builder = new StringBuilder();
        //While the scanner can make out a next line
        while (scanner.hasNextLine()) {
            //Append the next line to the String builder
            builder.append(scanner.nextLine());
        }
        //Try statement
        try {
            //Define a root scope from the JSON file as a JSONObject
            JSONObject root = new JSONObject(builder.toString());
            //Define a list of holidays as a JSONArray, retrieving from the root scope the array titled "holidays"
            JSONArray holidays = root.getJSONArray("holidays");
            //For each entry in the holidays JSONArray
            for (int i = 0; i < holidays.length(); i++) {
                //Get the current holiday from the JSONArray by calling the getJSONObject function
                //...which returns the current JSONObject according to the current index passed to it
                JSONObject currentHol = holidays.getJSONObject(i);
                //Define an int for the unique id by getting the integer titled "id" from the current JSONObject
                int id = currentHol.getInt("id");
                //Define a String for the hotel name by getting the String titled "hotelName" from the current JSONObject
                String hotelName = currentHol.getString("hotelName");
                //Define a String for the hotel's country by getting the String titled "city"
                //...from the "location" sub-array from the current JSONObject
                String city = currentHol.getJSONObject("location").getString("city");
                //Define a String for the hotel's country by getting the String titled "country"
                //...from the "location" sub-array from the current JSONObject
                String country = currentHol.getJSONObject("location").getString("country");
                //Define a Double for the hotel price by getting the Double titled "price" from the current JSONObject
                Double price = currentHol.getDouble("price");
                //Define a String for the hotel's image's title by getting the String titled "image" from the current JSONObject
                String image = currentHol.getString("image");
                //Define an int for the hotel starting day by getting the integer titled "day"
                //...from the "startDate" sub-array from the current JSONObject
                int day = currentHol.getJSONObject("startDate").getInt("day");
                //Define a String for the hotel's starting month by getting the String titled "month"
                //...from the "startDate" sub-array from the current JSONObject
                String month = currentHol.getJSONObject("startDate").getString("month");
                //Define an int for the hotel starting year by getting the integer titled "year"
                //...from the "startDate" sub-array from the current JSONObject
                int year = currentHol.getJSONObject("startDate").getInt("year");
                //Define a String for the hotel details by getting the String titled "details" from the current JSONObject
                String details = currentHol.getString("details");
                //Create a Holiday instance by passing all local variables gathered from the current JSONObject
                Holiday toInsert = new Holiday(id, currentLocale.getLanguage().toString(), hotelName, city, country, price, image,
                        day, month, year, details);
                //Add the Holiday instance to the HolidaysAdapter, which will insert to the ListView
                holidaysAdapter.add(toInsert);
            }
            //Close the scanner
            scanner.close();
            //Close the input stream
            inputStream.close();
            //If a JSONException is caught
        } catch (JSONException e) {
            //Print the stackTrace of the exception
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Refresh the holidaysAdapter by calling it's notifyDataSetChanged method
        holidaysAdapter.notifyDataSetChanged();
        //Set the list view's adapter as the holidaysAdapter, allowing all holidays to be listed
        listView.setAdapter(holidaysAdapter);
    }
}
