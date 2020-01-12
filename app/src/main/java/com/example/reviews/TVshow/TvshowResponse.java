package com.example.reviews.TVshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvshowResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<Tvshows> show;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Tvshows> getShow() {
        return show;
    }

    public void setShow(List<Tvshows> show) {
        this.show = show;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

  }


