package com.example.reviews.PagerAdapter;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.reviews.Movies.moviefragment.MostPopular_movie;
import com.example.reviews.Movies.moviefragment.TopRated_movie;
import com.example.reviews.Movies.moviefragment.Upcoming_movie;
import com.example.reviews.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MovieSectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static  int[] TAB_TITLES = new int[]{R.string.latest, R.string.toprated,R.string.upcoming};


    public MovieSectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;

        switch (position)
        {
            case 0:

                fragment=new MostPopular_movie();

                break;

            case 1:
               fragment=  new TopRated_movie();
                break;

            case 2:
                fragment=new Upcoming_movie();
                break;


        }
        return fragment;
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       switch (position)
       {
           case 0:
                return "Popular";


           case 1:
               return "Top Rated";

           case 2:
               return "Upcoming";



       }
       return null;
    }





}