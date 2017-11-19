package com.andrstudproj.androidappproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FormSubmitted extends Fragment {
    //Button objects for both the "to form" and "to main menu" buttons in the FormSubmitted view
    Button toForm, toMainMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the form_submitted_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        View view = inflater.inflate(R.layout.form_submitted_fragment, container, false);
        //Define the "toForm" button by referencing the "back_to_form" Button element from the inflated view
        toForm = view.findViewById(R.id.back_to_form);
        //Define the "toMainMenu" button by referencing the "to_main_menu" Button element from the inflated view
        toMainMenu = view.findViewById(R.id.to_main_menu);
        //Define a local Bundle object by calling the getArguments() function to retrieve any bundles passed to this class
        Bundle bundle = getArguments();
        //Local String for username, defined by getting the String under the "UserName" key from the bundle
        String userName = bundle.getString("UserName");
        //Local String for phone, defined by getting the String under the "UserPhone" key from the bundle
        String userPhone = bundle.getString("UserPhone");
        //Local String for email, defined by getting the String under the "UserEmail" key from the bundle
        String userEmail = bundle.getString("UserEmail");
        //String for the base text
        String baseText =getResources().getString(R.string.form_submit_page_message);
        //Redefine the base text variable by replacing the word "INSERTNAMEHERE" with the username
        baseText = baseText.replace("INSERTNAMEHERE", userName);
        //Redefine the base text variable again by replacing the word "INSERTPHONEHERE" with the username
        baseText = baseText.replace("INSERTPHONEHERE", userPhone);
        //Redefine the base text variable again by replacing the word "INSERTEMAILHERE" with the username
        baseText = baseText.replace("INSERTEMAILHERE", userEmail);
        //Locally define the view's TextView by referencing the "form_submit_textMessage" TextView from the inflated view
        TextView message = view.findViewById(R.id.form_submit_textmessage);
        //Set the text value of the TextView by passing it the base text variable to the setText() function
        message.setText(baseText);
        //Return the inflated and updated view
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Call the superclass' onViewCreated function
        super.onViewCreated(view, savedInstanceState);
        //Set up an OnClickListener to the "toFormButton" Button
        toForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the current activity and cast to MainActivity, then call the activity/class' displaySelectedScreen...
                //....function, passing it the unique int for R.id.nav_menu_page_4 to redirect back to the form
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_menu_page_4);
            }
        });
        //Set up an OnClickListener to the "toMainMenu" Button
        toMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the current activity and cast to MainActivity, then call the activity/class' displaySelectedScreen...
                //....function, passing it the unique int for R.id.nav_main_page to redirect to the main page
                ((MainActivity)getActivity()).displaySelectedScreen(R.id.nav_main_page);
            }
        });
    }
}
