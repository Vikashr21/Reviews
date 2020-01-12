package com.example.reviews.TVshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review_tvResponse {
    @SerializedName("results")
    @Expose
    private List<Review_tv> reviews;

    public List<Review_tv> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review_tv> reviews) {
        this.reviews = reviews;
    }
}

