package com.example.reviews.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.reviews.PagerAdapter.MusicSectionsPagerAdapter;
import com.example.reviews.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_music, container, false);
        View Content=(View)container.getParent();
        appBarLayout=(AppBarLayout)Content.findViewById(R.id.Appbar);
        tabLayout=new TabLayout(this.getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        viewPager=(ViewPager)root.findViewById(R.id.view_pager_movie);
        viewPager.setOffscreenPageLimit(3);
        appBarLayout.addView(tabLayout);
        MusicSectionsPagerAdapter musicSectionsPagerAdapter=new MusicSectionsPagerAdapter(getFragmentManager());
        viewPager.setAdapter(musicSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // Inflate the layout for this fragment
        return root;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        appBarLayout.removeView(tabLayout);
    }
}
