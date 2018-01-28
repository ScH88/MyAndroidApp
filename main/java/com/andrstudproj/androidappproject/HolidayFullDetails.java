package com.andrstudproj.androidappproject;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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
import java.net.URLEncoder;
import java.util.Locale;

public class HolidayFullDetails extends Fragment {
    //TextView reference variables for the hotel name, city, country, day, month, year, price and details
    private TextView fdHotelName, fdCity, fdCountry, fdDay, fdMonth, fdYear, fdPrice, fdDetails;
    //Button reference variables for the backToList and toMainMenu buttons
    private Button fdBackToList, fdToMainMenu;
    //ImageView reference variable for the image
    private ImageView fdImage;
    //Variable for the current locale
    private Locale currentLocale;

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
        //Set the text of the fdYear reference variable by calling setText and passing it the bundle's hotel year
        fdYear.setText(""+hotelYear);
        //Set the source/URL of the ImageView reference by calling getResources().getIdentifier() to access the image file
        //...with the same name as the bundle's image title value
        //The getIdentifier function first defines the matching value it is searching for, then narrows it to the "drawable"
        //...subdirectory in the res(resources) file, then narrows the search further within the current activity's context's
        //...package name
        fdImage.setImageResource(getResources().getIdentifier(hotelImage, "drawable", getContext().getPackageName()));
        //If the device's Android software version is larger than/equal to Nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            //Set the current Locale this way
            currentLocale = getResources().getConfiguration().getLocales().get(0);
        //Otherwise
        } else{
            //Set the current Locale this way, as this version was deprecated following the introduction of Nougat
            currentLocale = getResources().getConfiguration().locale;
        }
        //If the current device language is Danish, German, Spanish, French, Italian, Dutch, Portugese, Russian or Chinese
        if (currentLocale.getLanguage().toString().equals("de") || currentLocale.getLanguage().toString().equals("es")
                || currentLocale.getLanguage().toString().equals("fr") || currentLocale.getLanguage().toString().equals("it")
                || currentLocale.getLanguage().toString().equals("nl") || currentLocale.getLanguage().toString().equals("pt")
                || currentLocale.getLanguage().toString().equals("ru") || currentLocale.getLanguage().toString().equals("da")
                || currentLocale.getLanguage().toString().equals("zh")) {
            //(Temporary) Set the text of the month TextView as the api_loading_text String object by passing it the....
            //...unique ID pointint to it in the res/resources>strings directory
            fdMonth.setText(getResources().getString(R.string.api_loading_text));
            //(Temporary) Set the text of the details TextView as the api_loading_text String object by passing it the....
            //...unique ID pointint to it in the res/resources>strings directory
            fdDetails.setText(getResources().getString(R.string.api_loading_text));
            //Create a new instance of the GetDetailsXML AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the details TextView
            GetDetailsXML translateDetails = new GetDetailsXML(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the details TextView as the return value of the AsyncTask's onPostExecute function
                    fdDetails.setText((String) returnVal);
                }
            });
            //Create a new instance of the GetMonthXML AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the month TextView
            GetMonthXML translateMonth = new GetMonthXML(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the month TextView as the return value of the AsyncTask's onPostExecute function
                    fdMonth.setText((String)returnVal);
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translateDetails AsyncTask's executeOnExecutor/doInBackground, the former allowing it and the...
                //...other AsyncTasks to execute in unison.
                //Pass the hotelDetails and the current locale's language
                //NOTE: retrieving value from the internet must be done through an AsyncTask, as doing this...
                //...through this class results in a MainThreadException
                translateDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelDetails, currentLocale.getLanguage().toString());
                //Call the translateMonth AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's month...
                //...and the current locale
                translateMonth.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelMonth, currentLocale.getLanguage().toString());
            } else {
                //Call the translateDetails AsyncTask's execute/doInBackground method, passing it the hotel details
                translateDetails.execute(hotelDetails);
                //Call the translateMonth AsyncTask's execute/doInBackground method, passing it the hotel month
                translateMonth.execute(hotelMonth);
            }
        } else {
            //Set the text of the fdDetails reference variable by calling setText and passing it the bundle's hotel details
            fdDetails.setText(hotelDetails);
            //Set the text of the fdMonth reference variable by calling setText and passing it the bundle's hotel month
            fdMonth.setText(hotelMonth);
        }
        //If the current device language is Danish, German, Spanish, French, Italian, Dutch, Portugese, Russian or Chinese
        if (currentLocale.getLanguage().toString().equals("de") || currentLocale.getLanguage().toString().equals("es") || currentLocale.getLanguage().toString().equals("fr")
                || currentLocale.getLanguage().toString().equals("it") || currentLocale.getLanguage().toString().equals("nl") || currentLocale.getLanguage().toString().equals("pt")
                ) {
            //(Temporary) Set the text of the price TextView as the api_loading_text String object by passing it the....
            //...unique ID pointint to it in the res/resources>strings directory
            fdPrice.setText(getResources().getString(R.string.api_loading_text));
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the price TextView as the return value of the AsyncTask's onPostExecute function
                    fdPrice.setText("€" + String.format("%.2f", returnVal));
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelPrice, "EUR");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(hotelPrice, "EUR");
            }
        //Otherwise, if the current locale language is "ru" (Russian)
        } else if (currentLocale.getLanguage().toString().equals("ru")) {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the price TextView as the return value of the AsyncTask's onPostExecute function
                    fdPrice.setText("\u20BD" + String.format("%.2f", returnVal));
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelPrice, "RUB");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(hotelPrice, "RUB");
            }
            //Otherwise, if the current locale language is "da" (Danish)
        } else if (currentLocale.getLanguage().toString().equals("da")) {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the price TextView as the return value of the AsyncTask's onPostExecute function
                    fdPrice.setText("kr" + String.format("%.2f", returnVal));
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelPrice, "DKK");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(hotelPrice, "DKK");
            }
            //Set the price of the fdPrice TextView as t
        //Otherwise, if the current locale language is "zh" (Chinese)
        } else if (currentLocale.getLanguage().toString().equals("zh")) {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the price TextView as the return value of the AsyncTask's onPostExecute function
                    fdPrice.setText("￥" + String.format("%.2f", returnVal));
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double... and the appropriate...
                //...three letter currency zone to convert to.
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hotelPrice, "CNY");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(hotelPrice, "CNY");
            }
         //Otherwise
        } else {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Set the text of the price TextView as the return value of the AsyncTask's onPostExecute function
                    fdPrice.setText("$" + String.format("%.2f", returnVal));
                }
            });
            //If the current Locale is American
            if (currentLocale.getLanguage().equalsIgnoreCase("en_us")){
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(hotelPrice, "USD");
                //Otherwise, if the current Locale is Australian
            } else if (currentLocale.getLanguage().equalsIgnoreCase("en_au")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(hotelPrice, "AUD");
                //Otherwise, if the current Locale is Canadian
            } else if (currentLocale.getLanguage().equalsIgnoreCase("en_ca")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate... three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(hotelPrice, "CAD");
                //Otherwise, if the current Locale is New Zealand
            } else if (currentLocale.getLanguage().equalsIgnoreCase("en_nz")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(hotelPrice, "NZD");
                //Otherwise
            } else {
                //Set the value of the fdPrice TextView as the pound sign followed by the hotelPrice converted to String and...
                //...as part of a String format with "%.2f" to trim the decimals down to 2
                fdPrice.setText("£" + (String.format("%.2f", hotelPrice)));
            }
        }
        //Return the inflated and updated view
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Call the superclass' onViewCreated function
        super.onViewCreated(view, savedInstanceState);
        //Set up an OnClickListener to the BackToList" Button
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
