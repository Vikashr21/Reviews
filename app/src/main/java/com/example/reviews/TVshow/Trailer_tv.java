package com.example.reviews.TVshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer_tv {
    @SerializedName("key")
    @Expose
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

