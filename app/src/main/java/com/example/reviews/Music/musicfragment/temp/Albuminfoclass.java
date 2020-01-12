package com.example.reviews.Music.musicfragment.temp;

import com.example.reviews.Music.ImageItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Albuminfoclass {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;

    @SerializedName("image")
    @Expose
    private List<ImageItem> image;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("wiki")
    @Expose
    private infoalbum infoalbums;
    @SerializedName("tracks")
    @Expose
    private tracks track;

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




    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public infoalbum getInfoalbums() {
        return infoalbums;
    }

    public void setInfoalbums(infoalbum infoalbums) {
        this.infoalbums = infoalbums;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public tracks getTrack() {
        return track;
    }

    public void setTrack(tracks track) {
        this.track = track;
    }
}