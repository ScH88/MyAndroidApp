package com.andrstudproj.androidappproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Currency;
import java.util.Locale;

public class MainPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the main_page_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        return inflater.inflate(R.layout.main_page_fragment, container, false);
    }
}