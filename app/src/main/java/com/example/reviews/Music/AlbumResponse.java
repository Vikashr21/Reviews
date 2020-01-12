package com.example.reviews.Music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumResponse {
@SerializedName("topalbums")
    @Expose
   private TopAlbums albums;

    public TopAlbums getAlbums() {
        return albums;
    }

    public void setAlbums(TopAlbums albums) {
        this.albums = albums;
    }
}
