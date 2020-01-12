package com.example.reviews.TVshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailer_tvResponse {
    @SerializedName("results")
    @Expose
    private List<Trailer_tv> trailers;

    public List<Trailer_tv> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer_tv> trailers) {
        this.trailers = trailers;
    }
}
