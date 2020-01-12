package com.example.reviews.PagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.reviews.Food.FoodFragment.Categories;
import com.example.reviews.Food.FoodFragment.Random;

public class FoodSectionsPagerAdapter extends FragmentStatePagerAdapter {


    public FoodSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){

            case 0: return new Random();
            case 1:return new Categories();

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
        switch (position){
            case 0:return "Random Meals";
            case 1:return "Categories";
        }
        return null;
    }
}
