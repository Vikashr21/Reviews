package com.example.reviews.PagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.reviews.TVshow.Tvfragments.PopularTvFragment;
import com.example.reviews.TVshow.Tvfragments.TopRatedTvFragment;

public class TvSectionsPagerAdapter extends FragmentStatePagerAdapter {

    public TvSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new PopularTvFragment();
            case 1:
                return new TopRatedTvFragment();

        }


        return null;
    }

    @Override
    public int getCount() {
        return 2;
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

        }


        return super.getPageTitle(position);
    }
}
