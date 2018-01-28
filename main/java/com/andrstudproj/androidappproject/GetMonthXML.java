package com.andrstudproj.androidappproject;


import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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

//Param 1: What is passed to the doInBackground method
//Param 2: What we want to get while AsyncTask is working
//Param 3: What we want to get as a result (the return value of doInBackground)
//NOTE - Classes that use APIS must extend Asynctask, as doing so in the Fragment/Activity will result...
//...in a MainThreadException
public class GetMonthXML extends AsyncTask<String, Void, String> {
    //
    public CallbackListener listener = null;

    //
    public GetMonthXML(CallbackListener response) {
        listener = response;
    }

    //Function for initializing prior to calling the execute/doInBackground method
    @Override
    protected void onPreExecute() { super.onPreExecute();  }

    @Override
    protected String doInBackground(String... strings) {
        //Define a String for hotel month to translate as the String passed to the parameter, with all spaces replaced...
        //...with "+" in order to be passed to the URL for the API to work
        String hotelMonthToTranslate = strings[0].replace(" ", "+");
        //
        String currentLocale = strings[1];
        //String for storing the translated text to be returned, with default value of ""
        String returnTranslation = "";
        //Try statement
        try {
            //
            String currentLanguage = "";
            //Define the month XML string as the StringBuilder, cast to String
            String xmlStringMonth = getXMLResult("http://newjustin.com/translateit.php?action=xmltranslations&english_words="
                    + hotelMonthToTranslate);
            //Instantiate an XmlPullParserFactory variable by calling the newInstance() method of the XmlPullParserFactory class
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //Ensure that the PullParser produced by the factory will support XML namespaces
            factory.setNamespaceAware(true);
            //Define a new PullParser instance from the factory
            XmlPullParser xpp = factory.newPullParser();
            //Pass the XML String input to the PullParser for parsing (as a new StringReader)
            xpp.setInput(new StringReader(xmlStringMonth));
            //Get the current event type from the PullParser (e.g START_DOCUMENT, END_DOCUMENT)
            int eventType = xpp.getEventType();
            //While loop for checking if the current event type is not the end of document
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //If the current event type is that of a start tag, and the name of the current tag does not equal "translations"
                if (eventType == XmlPullParser.START_TAG && !xpp.getName().equals("translations") && !xpp.getName().equals("translations")) {
                    //If the name of the starting tag equals "chinese"
                    if (xpp.getName().equals("chinese")){
                        //Set the current language string as chinese
                        currentLanguage = "chinese";
                        //If the name of the starting tag equals "danish"
                    } else if (xpp.getName().equals("danish")){
                        //Set the current language string as danish
                        currentLanguage = "danish";
                        //If the name of the starting tag equals "dutch"
                    } else if (xpp.getName().equals("dutch")){
                        //Set the current language string as dutch
                        currentLanguage = "dutch";
                        //If the name of the starting tag equals "french"
                    } else if (xpp.getName().equals("french")){
                        //Set the current language string as french
                        currentLanguage = "french";
                        //If the name of the starting tag equals "german"
                    } else if (xpp.getName().equals("german")){
                        //Set the current language string as german
                        currentLanguage = "german";
                        //If the name of the starting tag equals "italian"
                    } else if (xpp.getName().equals("italian")){
                        //Set the current language string as italian
                        currentLanguage = "italian";
                        //If the name of the starting tag equals "portugese"
                    } else if (xpp.getName().equals("portuguese")){
                        //Set the current language string as portugese
                        currentLanguage = "portuguese";
                        //If the name of the starting tag equals "russian"
                    } else if (xpp.getName().equals("russian")){
                        //Set the current language string as russian
                        currentLanguage = "russian";
                        //If the name of the starting tag equals "spanish"
                    } else if (xpp.getName().equals("spanish")){
                        //Set the current language string as spanish
                        currentLanguage = "spanish";
                    }
                    //Otherwise, if the current event type is now the text between the tags
                } else if (eventType == XmlPullParser.TEXT) {
                    //If the language of the current device is either of the supported languages, and the currentLanguage has a relevant value as follows
                    //If the language of the current device is either of the supported languages, and the currentLanguage has a relevant value as follows
                    if ((currentLocale.equals("zh") && currentLanguage == "chinese")
                            || (currentLocale.equals("da") && currentLanguage == "danish")
                            || (currentLocale.equals("nl") && currentLanguage == "dutch")
                            || (currentLocale.equals("fr") && currentLanguage == "french")
                            || (currentLocale.equals("de") && currentLanguage == "german")
                            || (currentLocale.equals("it") && currentLanguage == "italian")
                            || (currentLocale.equals("pt") && currentLanguage == "portuguese")
                            || (currentLocale.equals("ru") && currentLanguage == "russian")
                            || (currentLocale.equals("es") && currentLanguage == "spanish")) {
                        //Set the returnTranslation value as the text value of the current XMLPullParser
                        returnTranslation = xpp.getText().trim();
                    }
                }
                //Switch to the next element inside the XML document, by telling the XML PullParser to go to the next element
                eventType = xpp.next();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        //Return the retrieved String
        return returnTranslation;
    }

    @Override
    protected void onPostExecute(String month) {
        listener.onFinishResponse(month);
    }

    public String getXMLResult (String url) throws IOException {
        //StringBuilder object - functions as a String but can be modified
        StringBuilder sb = new StringBuilder();
        //Create a HTTPClient instance, which will be used for executing http requests
        DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
        //Create a HttpPost for the month, passing the month string at the end of the url as a parameter to translate
        HttpPost httpPostMonth = new HttpPost(url);
        //Set the header of the month HttpPost as a content type of text/xml
        httpPostMonth.setHeader("content-type", "text/xml");
        //Response from the httpClient's execution of the month HttpPost
        HttpResponse responseMonth = httpClient.execute(httpPostMonth);
        //HttpEntity for storing the month response as an entity
        HttpEntity monthEntity = responseMonth.getEntity();
        //Define the month InputStream as the month HttpEntity's content
        InputStream inputStreamMonth = monthEntity.getContent();
        //BufferedReader instance for the month InputStream
        BufferedReader monthReader = new BufferedReader(new InputStreamReader(inputStreamMonth, "UTF-8"), 8);
        //String for storing the current language, with default value of null
        String currentLanguage = "";
        //String for a current, temporary line with default value of null
        String line;
        //While the current line of the month BufferedReader does not equal null
        while ((line = monthReader.readLine()) != null) {
            //Append the current line to the StringBuilder, and trim it to avoid another piece of text after...
            //....(which being a blank text, will return null and will overwrite the doInBackground's return value
            sb.append(line.trim());
        }
        //Return the StringBuilder, cast to String
        return sb.toString();
    }
}