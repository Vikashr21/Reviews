package com.example.reviews.Music.musicfragment.temp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbuminfoResponse {

    @SerializedName("album")
    @Expose
    private Albuminfoclass albuminfoclasses;


    public Albuminfoclass getAlbuminfoclasses() {
        return albuminfoclasses;
    }

    public void setAlbuminfoclasses(Albuminfoclass albuminfoclasses) {
        this.albuminfoclasses = albuminfoclasses;
    }
}
