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
class TranslateStringXml extends AsyncTask<String, Void, String> {

    //Instance of the CallbackListener interface, which allows classes that instantiate an instance of this class...
    //...to get the return value of this class' execute/doInBackground and pass it on to any of it's methods
    public CallbackListener listener = null;

    //
    public TranslateStringXml(CallbackListener response) {
        //Set this instance's CallBackListener value as the one passed to the parameter
        listener = response;
    }

    //Function for initializing prior to calling the execute/doInBackground method
    @Override
    protected void onPreExecute() { super.onPreExecute(); }

    @Override
    protected String doInBackground(String... strings) {
        //Define a String for the String value to translate, getting it from the first of the "String... strings" array passed...
        //...to the parameter, and with all spaces replaced with "+" so the api can work on the whole string properly
        //...with "+" in order to be passed to the URL for the API to work
        String stringToTranslate = (strings[0]).replace(" ", "+");
        //Get the current Locale from the second entry in the "String... strings" array passed to the parameter
        String currentLocale = strings[1];
        //String for storing the return value of the XML/JSON query we will conduct in a bit
        String returnTranslation = "";
        //Try statement
        try {
            //String for storing the current language, with default value of null
            String currentLanguage = "";
            //Define a String from which an XmlPullPaser will read from, by passing the following Url (with the string value...
            //...to translate) to this instance's getXMLResult method
            String stringToRead = getXMLResult("http://newjustin.com/translateit.php?action=xmltranslations&english_words="
                    + stringToTranslate);
            //Instantiate an XmlPullParserFactory variable by calling the newInstance() method of the XmlPullParserFactory class
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //Ensure that the PullParser produced by the factory will support XML namespaces
            factory.setNamespaceAware(true);
            //Define a new PullParser instance from the factory
            XmlPullParser xpp = factory.newPullParser();
            //Pass the XML-string-to-read input to the PullParser for parsing (as a new StringReader)
            xpp.setInput(new StringReader(stringToRead));
            //Get the current event type from the PullParser (e.g START_DOCUMENT, END_DOCUMENT)
            int eventType = xpp.getEventType();
            //While loop for checking if the current event type is not the end of document
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //If the current event type is that of a start tag, and the name of the current tag does not equal "translations"
                if (eventType == XmlPullParser.START_TAG && !xpp.getName().equals("translations")) {
                    //Set the current language string as the name of the current tag the XmlPullParser is reading
                    currentLanguage = xpp.getName();
                //Otherwise, if the current event type is now the text between the tags
                } else if (eventType == XmlPullParser.TEXT) {
                    //If the language of the current device is either of the supported languages, and the currentLanguage has a relevant value as follows
                    if ((currentLocale.equals("zh") && currentLanguage == "chinese")
                            || (currentLocale.equals("da") && currentLanguage.equals("danish"))
                            || (currentLocale.equals("nl") && currentLanguage.equals("dutch"))
                            || (currentLocale.equals("fr") && currentLanguage.equals("french"))
                            || (currentLocale.equals("de") && currentLanguage.equals("german"))
                            || (currentLocale.equals("it") && currentLanguage.equals("italian"))
                            || (currentLocale.equals("pt") && currentLanguage.equals("portuguese"))
                            || (currentLocale.equals("ru") && currentLanguage.equals("russian"))
                            || (currentLocale.equals("es") && currentLanguage.equals("spanish"))) {
                        //Set the return value as the XMLPullParser's current text between the tags, then...
                        //..trimmed in order to remove the left indent, then followed by a full stop
                        returnTranslation = xpp.getText().trim() + ".";
                    }
                }
                //Switch to the next element inside the XML document, by telling the XML PullParser to go to the next element
                eventType = xpp.next();
            }
        //If a MalformedUrlException is caught
        } catch (MalformedURLException e) {
            e.printStackTrace();
         //If a UnsupportedEncodingException is caught
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        //If a ClientProtocolException is caught
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //If an IOException is caught
        } catch (IOException e) {
            e.printStackTrace();
        //If an XmlPullParserException is caught
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        //Return retrieved String, which will be passed to the onPostExecute method
        return returnTranslation;
    }

    @Override
    protected void onPostExecute(String returnVal) {
        //Call the listener's onFinishResponse (Overriden) method, passing it the return value from the doInBackground...
        //...return value and into the method parameter.
        listener.onFinishResponse(returnVal);
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
        //String for a current, temporary line with default value of null
        String line;
        //While the current line of the month BufferedReader does not equal null
        while ((line = monthReader.readLine()) != null) {
            //Append the current line to the StringBuilder, and trim it to avoid another piece of text after...
            //....(which being a blank text, will return null and will overwrite the doInBackground's return value
            sb.append(line.trim());
        }
        return sb.toString();
    }
}