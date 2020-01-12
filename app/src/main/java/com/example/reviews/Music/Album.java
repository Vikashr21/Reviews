package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private Artist artist;

    @SerializedName("image")
    @Expose
    private List<ImageItem> image;




    public List<ImageItem> getImage() {
        return image;
    }

    public void setImage(List<ImageItem> image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
