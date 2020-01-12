package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class Artist {
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
