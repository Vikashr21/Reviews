package com.example.reviews.TVshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tvshows {

    @SerializedName("id")
    @Expose
    private int id;

@SerializedName("name")
    @Expose
    private String name;

@SerializedName("first_air_date")
@Expose
private String releasedate;


@SerializedName("backdrop_path")
@Expose
   private String backdrop_path;


@SerializedName("vote_average")
@Expose
private float vote_average;


@SerializedName("poster_path")
@Expose
    private String poster_path;

    @SerializedName("overview")
    @Expose
    private String overview;


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
