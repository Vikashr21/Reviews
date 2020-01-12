package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopAlbums {
    @SerializedName("album")
    @Expose
    private List<Album> albumslist;

    public List<Album> getAlbumslist() {
        return albumslist;
    }

    public void setAlbumslist(List<Album> albumslist) {
        this.albumslist = albumslist;
    }}
