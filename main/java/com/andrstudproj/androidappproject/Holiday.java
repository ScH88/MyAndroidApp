package com.andrstudproj.androidappproject;


import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import java.util.Locale;

public class Holiday {
    //String variables for the hotel name, city, country, image title, month, details and the current language used
    private String hotelName, city, country, image, month, details;
    //Double variable for the price
    private double price;
    //Integer variables for the unique id, day and year
    private int id, day, year;

    public Holiday(int id, String currentLocale, String hotelName, String city, String country, double price, String image, int day, String month,
                   int year, final String details) {
        //Set this instance's id value by passing it the "id" value passed to the parameter
        this.id = id;
        //Set this instance's hotelName value by passing it the "hotelName" value passed to the parameter
        this.hotelName = hotelName;
        //Set this instance's city value by passing it the "city" value passed to the parameter
        this.city = city;
        //Set this instance's country value by passing it the "country" value passed to the parameter
        this.country = country;
        //Set this instance's image value by passing it the "image" value passed to the parameter
        this.image = image;
        //Set this instance's day value by passing it the "day" value passed to the parameter
        this.day = day;
        //Set this instance's year value by passing it the "year" value passed to the parameter
        this.year = year;
        //If the current device language is Danish, German, Spanish, French, Italian, Dutch, Portugese, Russian or Chinese
        if (currentLocale.equals("de") || currentLocale.equals("es") || currentLocale.equals("fr")
                || currentLocale.equals("it") || currentLocale.equals("nl") || currentLocale.equals("pt")
                || currentLocale.equals("da") || currentLocale.equals("ru") || currentLocale.equals("zh")) {
            //Create a new instance of the GetDetailsXML AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the details TextView
            GetDetailsXML translateDetails = new GetDetailsXML(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Call the setDetails method using the return value of the AsyncTask's onPostExecute method
                    setDetails((String)returnVal);
                }
            });
            //Create a new instance of the GetMonthXML AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the month TextView
            GetMonthXML translateMonth = new GetMonthXML(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Call the setMonth method using the return value of the AsyncTask's onPostExecute method
                    setMonth((String)returnVal);
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translateDetails AsyncTask's executeOnExecutor/doInBackground, the former allowing it and the...
                //...other AsyncTasks to execute in unison.
                //Pass the hotelDetails and the current locale's language
                //NOTE: retrieving value from the internet must be done through an AsyncTask class, as doing this...
                //...through this class results in a MainThreadException
                translateDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, details, currentLocale);
                //Call the translateMonth AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's month...
                //...and the current locale
                translateMonth.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, month, currentLocale);
            } else {
                //Call the translateDetails AsyncTask's execute/doInBackground method, passing it the hotel details
                translateDetails.execute(details);
                //Call the translateMonth AsyncTask's execute/doInBackground method, passing it the hotel month
                translateMonth.execute(month);
            }
        //Otherwise
        } else {
            //Set the month of this instance as the month passed to the parameter
            this.month = month;
            //Set the details of this instance as the details passed to the parameter
            this.details = details;
        }
        //If the current device language is Danish, German, Spanish, French, Italian, Dutch, Portugese, Russian or Chinese
        if (currentLocale.equals("de") || currentLocale.equals("es") || currentLocale.equals("fr")
                || currentLocale.equals("it") || currentLocale.equals("nl") || currentLocale.equals("pt")
                ) {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Call the setPrice method using the return value of the AsyncTask's onPostExecute method
                    setPrice((double)returnVal);
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, price, "EUR");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(price, "EUR");
            }
         //Otherwise, if the current locale language is "da" (Danish)
        } else if (currentLocale.equals("da")) {
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Call the setPrice method using the return value of the AsyncTask's onPostExecute method
                    setPrice((double) returnVal);
                }
            });
            //If the current Android software version is greater than/equal to Honeycomb
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //Call the translatePrice AsyncTask's executeOnExecutor/doInBackground, passing it the hotel's price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, price, "DKK");
            } else {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotel price...
                //...and the appropriate three letter currency zone to convert to
                translatePrice.execute(price, "DKK");
            }
        //If the current Locale is American, Australian, Canadian or New Zealand
        } else if (currentLocale.equalsIgnoreCase("en_us") || currentLocale.equalsIgnoreCase("en_au") ||
                currentLocale.equalsIgnoreCase("en_ca") || currentLocale.equalsIgnoreCase("en_nz")){
            //Create a new instance of the TranslatePrice AsyncTask class, passing it an instance of the CallbackListener...
            //...interface class, which will set the text of the price TextView
            TranslatePrice translatePrice = new TranslatePrice(new CallbackListener() {
                //Override onFinishResponse
                @Override
                public void onFinishResponse(Object returnVal) {
                    //Call the setPrice method using the return value of the AsyncTask's onPostExecute method
                    setPrice((double) returnVal);
                }
            });
            //If the current Locale is American
            if (currentLocale.equalsIgnoreCase("en_us")){
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(price, "USD");
                //Otherwise, if the current Locale is Australian
            } else if (currentLocale.equalsIgnoreCase("en_au")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(price, "AUD");
                //Otherwise, if the current Locale is Canadian
            } else if (currentLocale.equalsIgnoreCase("en_ca")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double...
                // and the appropriate... three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(price, "CAD");
                //Otherwise, if the current Locale is New Zealand
            } else if (currentLocale.equalsIgnoreCase("en_nz")) {
                //Call the translatePrice AsyncTask's execute/doInBackground method, passing it the hotelPrice double
                // and the appropriate three letter currency zone to convert to, then call it's execute/doInBackground
                translatePrice.execute(price, "NZD");
            }
        //Otherwise
        } else {
            //Just set this instance's price as the price value passed to the parameter
            this.price = price;
        }
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

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMonth(String month) {
        this.month = month;
    }


}
