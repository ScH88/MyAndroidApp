package com.andrstudproj.androidappproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuPage2 extends Fragment {
    //AppBarLayout exclusive to this class
    private AppBarLayout appBar;
    //TabLayout exclusive to this class
    private TabLayout tabs;
    //ViewPager exclusive to this class
    private ViewPager pager;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static MainPageFragment newInstance() {
        //Return a new MainPageFragment instance
        return new MainPageFragment();
    }
    //Blank constructor
    public MenuPage2 () {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the menu_page_2_fragment XML view from the layouts subdirectory in the res(resources) file to screen
        //Have this instance's container be the view group
        View view = inflater.inflate(R.layout.menu_page_2_fragment, container, false);
        //View for the page content, defined as the container's parent, casted to View
        View content = (View)container.getParent();
        //Define the appBar as the content's appBar as the AppBarLayout in the content_main xml in the layouts subdirectory in res(resources)
        appBar = (AppBarLayout)content.findViewById(R.id.appbar);
        //Define the tabs variable as a new TabLayout instance, passing it this fragment's host activity
        tabs = new TabLayout(getActivity());
        //Set the text colours of the tabs, starting with the text colour as the colour titled "white"
        //...in the colors xml in the "values" subdirectory in res(resources)
        tabs.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
        //Add the tabs to the appbar
        appBar.addView(tabs);
        //Define this instance's pager as the ViewPager in the menu_page_2_fragment XML in the layout subdirectory in res(resources)
        pager = (ViewPager)view.findViewById(R.id.pager);
        //Define a new instance of the ViewPageAdapter class nested to this instance, passing it the return value
        //...of this fragment's host fragment manager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        //Set the tab pager's adapter as the adapter containing this fragment's host fragment manager
        pager.setAdapter(adapter);
        //set up the tabs TabLayout with the pager
        tabs.setupWithViewPager(pager);
        //Return the inflated and edited view
        return view;
    }

    @Override
    public void onDestroyView() {
        //Call the superclass(Fragment)'s onDestroyView function
        super.onDestroyView();
        //Remove all tabs from the appbar
        appBar.removeView(tabs);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        //Array of Strings for tab page titles
        String titles[] = {"Page 1", "Page 2", "Page 3"};

        public ViewPagerAdapter(FragmentManager fm) {
            //Call the constructor of the superclass(FragmentStatePagerAdapter, passing it the parameter fragment manager
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Switch for the position integer passed to the parameter
            switch(position) {
                //If the position equals 0, return a new instance of MainPageTab1Fragment
                case 0: return new MainPageTab1Fragment();
                //If the position equals 1, return a new instance of MainPageTab2Fragment
                case 1: return new MainPageTab2Fragment();
                //If the position equals 2, return a new instance of MainPageTab3Fragment
                case 2: return new MainPageTab3Fragment();
            }
            //If none of the above conditions are met, return null
            return null;
        }

        @Override
        public int getCount() {
            //Return the length of the titles array
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //Return the title in the array whose index matches the position passed to the parameter
            return titles[position];
        }
    }
}