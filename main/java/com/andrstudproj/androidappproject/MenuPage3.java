package com.andrstudproj.androidappproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuPage3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the menu_page_3_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        return inflater.inflate(R.layout.menu_page_3_fragment, container, false);
    }
}
