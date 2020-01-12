package com.example.reviews.Music.musicfragment.temp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class tracks {
    @SerializedName("track")
    @Expose
    private List<tracklist> tracklists;

    public List<tracklist> getTracklists() {
        return tracklists;
    }

    public void setTracklists(List<tracklist> tracklists) {
        this.tracklists = tracklists;
    }
}
