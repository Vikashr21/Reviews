package com.example.reviews.Music.musicfragment.temp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class infoalbum {
    @SerializedName("published")
@Expose
    private String publishdate;


    @SerializedName("summary")
@Expose
    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
}
