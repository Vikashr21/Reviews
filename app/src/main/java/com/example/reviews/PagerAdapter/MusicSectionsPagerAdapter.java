package com.example.reviews.PagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.reviews.Music.musicfragment.TopAlbumsfolk;
import com.example.reviews.Music.musicfragment.topAlbum;
import com.example.reviews.Music.musicfragment.topAlbumsRap;
import com.example.reviews.Music.musicfragment.topAlbumsRock;
import com.example.reviews.Music.musicfragment.topAlbumshiphop;
import com.example.reviews.Music.musicfragment.topAlbumsjazz;

public class MusicSectionsPagerAdapter extends FragmentStatePagerAdapter {
    public MusicSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position)
        {
            case 0: return new topAlbumshiphop();
            case 1: return new topAlbumsRock();
            case 2: return new TopAlbumsfolk();
            case 3: return new topAlbumsjazz();
            case 4: return new topAlbumsRap();
            case 5:return new topAlbum();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0: return "Hip Hop";
            case 1: return "Rock";
            case 2: return "Folk";
            case 3: return "Jazz";
            case 4: return "Rap";
            case 5: return "Acoustic";

        }
        return null;
    }
}
