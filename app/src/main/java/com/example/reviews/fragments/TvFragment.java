package com.example.reviews.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.reviews.PagerAdapter.TvSectionsPagerAdapter;
import com.example.reviews.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_tv, container, false);
        View Content=(View)container.getParent();
        appBarLayout=(AppBarLayout)Content.findViewById(R.id.Appbar);
        tabLayout=new TabLayout(this.getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        viewPager=(ViewPager)root.findViewById(R.id.view_pager_tv);
        viewPager.setOffscreenPageLimit(3);
        appBarLayout.addView(tabLayout);
       TvSectionsPagerAdapter tvSectionsPagerAdapter=new TvSectionsPagerAdapter(getFragmentManager());
        viewPager.setAdapter(tvSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
return root;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        appBarLayout.removeView(tabLayout);
    }
}
