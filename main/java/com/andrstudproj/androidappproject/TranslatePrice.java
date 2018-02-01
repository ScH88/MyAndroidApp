package com.andrstudproj.androidappproject;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Nested class TranslatePrice. Custom subclass of AsyncTask
//Param 1: What is passed to the doInBackground method
//Param 2: What we want to get while AsyncTask is working
//Param 3: What we want to get as a result (the return value of doInBackground)
//NOTE - Classes that use APIS must extend Asynctask, as doing so in the Fragment/Activity will result...
//...in a MainThreadException
public class TranslatePrice extends AsyncTask<Object, Void, Double> {
    //
    public CallbackListener listener = null;

    //
    public TranslatePrice(CallbackListener response) {
        listener = response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //Override the superclass' doInBackground, passing it a String
    @Override
    protected Double doInBackground(Object... objects) {
        //Get the String of the price to be converted from (will be cast to Double later) as the first of the Strings...
        //in the array passed to the parameter
        double priceFrom = (double)objects[0];
        //Get the three letter String for the currency zone to convert to by getting the second of the Strings in...
        //...the array passed to the parameter
        String convertToZone = (String)objects[1];
        //String for storing the resulting value gained from the URL/JSON query
        String result = "";
        //Try Statement
        try {
            //Create a string for the website URL we will retrieve the JSON...
            //...then pass it to the getJSONResult method in order to get the String return value from it's JSON querying
            String siteURL = getJSONResult("https://api.fixer.io/latest?base=GBP&symbols=" + convertToZone);
            //Define the result as a String extracted from the 1st(and only) JSONObject from inside the "rates" array, itself...
            //...from inside the JSONObject returned from the query
            result = new JSONObject(siteURL).getJSONObject("rates").getString(convertToZone);
            //If a JSONException is caught
        }  catch (JSONException e) {
            //Print the stack trace of this exception to console
            e.printStackTrace();
            //If an IOException is caught
        } catch (IOException i) {
            //Print the stack trace of this exception to console
            i.printStackTrace();
        }
        //Now cast both the price to convert from and the returned XML/JSON query value, then mulitply them by each other
        Double translatedValue = priceFrom * Double.parseDouble(result);
        //Return the translated value
        //return currencySymbol + String.format("%.2f", translatedValue);
        return translatedValue;
    }

    @Override
    protected void onPostExecute(Double translatedValue) {
        listener.onFinishResponse(translatedValue);
    }

    public String getJSONResult(String url) throws ClientProtocolException, IOException{
        //StringBuilder object - functions as a String but can be modified
        StringBuilder sb = new StringBuilder();
        //HTTPClient used for executing an oncoming URL method
        HttpClient client = new DefaultHttpClient();
        //Instance of HttpGet - Prepares to retrieve information (in the form of an Entity) as identified by the URL passed to it
        HttpGet httpGet = new HttpGet(url);
        //Instance of HttpResponse - is initialized by the client executing the HttpGet query
        HttpResponse httpResponse = client.execute(httpGet);
        //Get the information from the HttpResponse and store as a HttpEntity object
        HttpEntity entity = httpResponse.getEntity();
        //Create an inputStream using the content of the HttpEntity. This will allow an InputStreamReader/BufferedReader read through it all
        InputStream content = entity.getContent();
        //Create a new BufferedReader instance. This will receive an InputStreamReader that will read from the InputStream just before
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        //String for the current line being read
        String currentLine;
        //While the current line of the BufferedReader is not null, while setting the currentLine String's value as the line
        while ((currentLine = reader.readLine()) != null) {
            //Append the current line to the StringBuilder
            sb.append(currentLine);
        }
        //Return the StringBuilder object
        return sb.toString();
    }
}