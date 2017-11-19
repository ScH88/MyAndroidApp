package com.andrstudproj.androidappproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call the onCreate function of the superclass (AppCompatActivity)
        super.onCreate(savedInstanceState);
        //Set the content view of this activity as the activity_main XML file in the layout subdirectory in res(resources)
        setContentView(R.layout.activity_main);
        //Define a toolbar instance as a reference to the toolbar view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set this activity's support action bar as the toolbar
        setSupportActionBar(toolbar);
        //Define a DrawerLayout as a reference to the drawer_layout view
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Define an ActionBarDrawerToggle as a new instance containing this activity, the DrawerLayout,
        //...the toolbar and int references to Strings "open" and "close" in the Strings subdirectory in res(resources)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        //Synchronize the indicator with the state of the linked DrawerLayout after onRestoreInstanceState has occurred.
        toggle.syncState();
        //Define a NavigationView as the nav_vew layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Set the NavigationView's item selected listener as this activity
        navigationView.setNavigationItemSelectedListener(this);
        //Call this activity's displaySelectedScreen function, passing the int ID of "nav_main_page" to redirect
        //...to the main page upon app startup
        displaySelectedScreen(R.id.nav_main_page);
    }

    @Override
    public void onBackPressed() {
        //Define a DrawerLayout as a reference the drawer_layout layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //If the drawer is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //Close the drawer
            drawer.closeDrawer(GravityCompat.START);
            //Otherwise
        } else {
            //Call the onBackPressed() function of the superclass(AppCompatActivity)
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //Return true
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Get the item ID of the MenuItem clicked
        int id = item.getItemId();
        //Return the operation results on the MenuItem
        return super.onOptionsItemSelected(item);
    }

    // Gets the title of the current fragment
    public void onSectionAttached(int number) {
        //Create a switch statement of the number passed to the parameter
        switch (number) {
            //If the number equals 1
            case 1:
                //Set this activity's title as the following String from the strings subdirectory in res(resources)
                mTitle = getString(R.string.main_menu_page_title);
                //Break from the switch statement
                break;
            //If the number equals 2
            case 2:
                //Set this activity's title as the following String from the strings subdirectory in res(resources)
                mTitle = getString(R.string.menu_2_page_title);
                //Break from the switch statement
                break;
            //If the number equals 3
            case 3:
                //Set this activity's title as the following String from the strings subdirectory in res(resources)
                mTitle = getString(R.string.menu_3_page_title);
                //Break from the switch statement
                break;
            //If the number equals 4
            case 4:
                //Set this activity's title as the following String from the strings subdirectory in res(resources)
                mTitle = getString(R.string.menu_4_page_title);
                //Break from the switch statement
                break;
            //If the number equals 5
            case 5:
                //Set this activity's title as the following String from the strings subdirectory in res(resources)
                mTitle = getString(R.string.menu_5_page_title);
                //Break from the switch statement
                break;
        }
    }

    public void displaySelectedScreen(int id) {
        //Local Fragment variable
        Fragment fragment = null;
        //Switch statement for the id integer passed to the parameter
        switch(id) {
            //If the id is that of the unique id of the nav_main_page item in the activity_main_drawer layout in the menu
            //....subdirectory in res(resources)
            case R.id.nav_main_page:
                //Set the local fragment as a new instance of the MainPageFragment class
                fragment = new MainPageFragment();
                //Break from the switch statement
                break;
            //If the id is that of the unique id of the nav_menu_page_2 item in the activity_main_drawer layout in the menu
            //....subdirectory in res(resources)
            case R.id.nav_menu_page_2:
                //Set the local fragment as a new instance of the MenuPage2 class
                fragment = new MenuPage2();
                //Break from the switch statement
                break;
            //If the id is that of the unique id of the nav_menu_page_3 item in the activity_main_drawer layout in the menu
            //....subdirectory in res(resources)
            case R.id.nav_menu_page_3:
                //Set the local fragment as a new instance of the MenuPage3 class
                fragment = new MenuPage3();
                //Break from the switch statement
                break;
            //If the id is that of the unique id of the nav_menu_page_4 item in the activity_main_drawer layout in the menu
            //....subdirectory in res(resources)
            case R.id.nav_menu_page_4:
                //Set the local fragment as a new instance of the MenuPage4 class
                fragment = new MenuPage4();
                //Break from the switch statement
                break;
            //If the id is that of the unique id of the nav_menu_page_5 item in the activity_main_drawer layout in the menu
            //....subdirectory in res(resources)
            case R.id.nav_menu_page_5:
                //Set the local fragment as a new instance of the MenuPage5 class
                fragment = new MenuPage5();
                //Break from the switch statement
                break;
        }
        //If the fragment is not null
        if (fragment != null) {
            //Begin a FragmentTransaction from this activity's SupportFragment Manager that will prepare this activity
            //....to replace it's current view
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //Prepare the FragmentTransaction to replace the content_main CoordinatorLayout in the content_main
            //...XML in the layout resource subdirectory with the local fragment
            ft.replace(R.id.content_main, fragment);
            //Commit the transaction
            ft.commit();
        }
        //Local variable for a reference to the drawer_Layout view
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Have the drawer close
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("deprecation")
    // Changes the title of the page to the current fragments title
    public void restoreActionBar() {
        //Define an actionBar as return value this activity's getSupportActionBar() function
        ActionBar actionBar = getSupportActionBar();
        //Set the navigation bar's navigation mode as standard
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //Allow the action bar to display the title
        actionBar.setDisplayShowTitleEnabled(true);
        //Set the title of the action bar as this activity's current title
        actionBar.setTitle(mTitle);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //Define an ID integer as the item's getItemId() function
        int id = item.getItemId();
        //Call this activity's displaySelectedScreen() function, passing the ID to redirect to it's respective layout/page
        displaySelectedScreen(id);
        //Return true
        return true;
    }
}
