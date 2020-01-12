package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class ImageItem {
    @SerializedName("#text")
    @Expose
    private String url;

    @Expose
    @SerializedName("size")
    private String size;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
