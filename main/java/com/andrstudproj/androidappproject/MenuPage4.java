package com.andrstudproj.androidappproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuPage4 extends Fragment {
    //TextView variables referencing the nameValidMssg, phoneValidMssg and mailValidMssg TextViews from the view layout
    TextView nameValidMssg, phoneValidMssg, mailValidMssg;
    //EditText variables referencing the input1, input2 and input3 EditTexts from the view layout
    EditText input1, input2, input3;
    //Button reference for the one in the view layout
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the menu_page_4_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        View view = inflater.inflate(R.layout.menu_page_4_fragment, container, false);
        //Define the name validation message TextView reference as the TextView in the view with the id of nameErrorMssg
        nameValidMssg = (TextView) view.findViewById(R.id.nameErrorMssg);
        //Define the phone validation message TextView reference as the TextView in the view with the id of phoneErrorMssg
        phoneValidMssg = (TextView) view.findViewById(R.id.phoneErrorMssg);
        //Define the email validation message TextView reference as the TextView in the view with the id of mailErrorMssg
        mailValidMssg = (TextView) view.findViewById(R.id.mailErrorMssg);
        //Define the input1 EditText reference as the EditText in the view with the id of nameInput
        input1 = (EditText) view.findViewById(R.id.nameInput);
        //Define the input2 EditText reference as the EditText in the view with the id of phoneInput
        input2 = (EditText) view.findViewById(R.id.phoneInput);
        //Define the input3 EditText reference as the EditText in the view with the id of mailInput
        input3 = (EditText) view.findViewById(R.id.mailInput);
        //Define the Button reference as the Button in the view with the id of formButton
        button = (Button) view.findViewById(R.id.formButton);
        //Return the inflated and edited view
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Call the constructor of the superclass(Fragment)
        super.onViewCreated(view, savedInstanceState);
        //Set an OnClickListener to the button
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //String for the text input value of the input1 (name) EditText
                String nameVal = String.valueOf(input1.getText());
                //String for the text input value of the input2 (phone) EditText
                String phoneVal = String.valueOf(input2.getText());
                //String for the text input value of the input3 (email) EditText
                String emailVal = String.valueOf(input3.getText());
                //Boolean for if the collective of input data is valid
                boolean isValid = true;
                //If the name String value is empty
                if (nameVal.isEmpty()) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input1 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input1.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of nameValidMssg TextView to the String object titled "form_name_is_empty"
                    //...from the strings subdirectory in res(resources)
                    nameValidMssg.setText(getResources().getString(R.string.form_name_is_empty));
                    //Otherwise, if passing the name String value to this class' isNameValid function returns false
                } else if (!isNameValid(nameVal)) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input1 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input1.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of nameValidMssg TextView to the String object titled "form_name_is_invalid"
                    //...from the strings subdirectory in res(resources)
                    nameValidMssg.setText(getResources().getString(R.string.form_name_is_invalid));
                    //Otherwise
                } else {
                    //Set the text value of the nameValidMssg TextView back to null
                    nameValidMssg.setText("");
                    //Set the background of the input1 EditText as the "rounded_corner_edittext" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour back to black
                    input1.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext));
                }
                //If the phone value String is empty
                if (phoneVal.isEmpty()) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input2 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input2.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of phoneValidMssg TextView to the String object titled "form_phone_is_empty"
                    //...from the strings subdirectory in res(resources)
                    phoneValidMssg.setText(getResources().getString(R.string.form_phone_is_empty));
                    //Otherwise, if the phone String value does not match the Pattern's class' Phone matcher
                } else if (!Patterns.PHONE.matcher(phoneVal).matches()) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input2 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input2.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of phoneValidMssg TextView to the String object titled "form_view_is_invalid"
                    //...from the strings subdirectory in res(resources)
                    phoneValidMssg.setText(getResources().getString(R.string.form_phone_is_invalid));
                    //Otherwise
                } else {
                    //Set the text value of the phoneValidMssg TextView back to null
                    phoneValidMssg.setText("");
                    //Set the background of the input2 EditText as the "rounded_corner_edittext" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour back to black
                    input2.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext));
                }
                //If the email string value is empty
                if (emailVal.isEmpty()) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input3 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input3.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of emailValidMssg TextView to the String object titled "form_email_is_empty"
                    //...from the strings subdirectory in res(resources)
                    mailValidMssg.setText(getResources().getString(R.string.form_email_is_empty));
                    //Otherwise, if the email String value does not match the Patterns class' EMAIL_ADDRESS matcher
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailVal).matches()) {
                    //Set the isValid boolean to false
                    isValid = false;
                    //Set the background of the input3 EditText as the "rounded_corner_edittext_required" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour to red
                    input3.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext_required));
                    //Change the text value of emailValidMssg TextView to the String object titled "form_email_is_invalid"
                    //...from the strings subdirectory in res(resources)
                    mailValidMssg.setText(getResources().getString(R.string.form_email_is_invalid));
                    //Otherwise
                } else {
                    //Set the text value of the mailValidMssg TextView back to null
                    mailValidMssg.setText("");
                    //Set the background of the input3 EditText as the "rounded_corner_edittext" drawable
                    //...from the drawables subdirectory in res(resources), changing the border colour back to black
                    input3.setBackground(getResources().getDrawable(R.drawable.rounded_corner_edittext));
                }
                //If the isValid boolean is still true
                if (isValid == true) {
                    //Create a Bundle instance for storing variables and passing them to an oncoming page
                    Bundle bundle = new Bundle();
                    //Insert the name String value in the bundle under the keyname "UserName"
                    bundle.putString("UserName", nameVal);
                    //Insert the phone String value in the bundle under the keyname "UserPhone"
                    bundle.putString("UserPhone", phoneVal);
                    //Insert the email String value in the bundle under the keyname "UserEmail"
                    bundle.putString("UserEmail", emailVal);
                    //Create a FragmentTransaction using this activity's SupportFragmentManager and begin it
                    FragmentTransaction transact = getActivity().getSupportFragmentManager().beginTransaction();
                    //Create a new FormSubmitted instance
                    FormSubmitted submitPage = new FormSubmitted();
                    //Add the Bundle to the new instance prior to committing
                    submitPage.setArguments(bundle);
                    //Prepare the transaction to replace the content_main CoordinatorLayout in the content_main layout
                    //...in the layout subdirectory in res(resources)
                    transact.replace(R.id.content_main, submitPage);
                    //Commit the transaction
                    transact.commit();
                }
            }
        });
    }

    /*
      Function for checking if an inputted name is valid
     */
    private static boolean isNameValid(String name) {
        //String for the name validation regex
        String pattern = "^[\\p{L} .'-]+$";
        //Return a boolean for if the name passed to the parameter matches the regex definition
        return name.matches(pattern);
    }
}
