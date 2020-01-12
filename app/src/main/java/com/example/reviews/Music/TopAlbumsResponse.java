package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopAlbumsResponse {
    @SerializedName("albums")
    @Expose
    private TopAlbums topAlbums;

    public TopAlbums getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(TopAlbums topAlbums) {
        this.topAlbums = topAlbums;
    }
}
