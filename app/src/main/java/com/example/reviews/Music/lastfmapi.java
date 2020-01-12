package com.example.reviews.Music;

import com.example.reviews.Music.musicfragment.temp.AlbuminfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface lastfmapi {



    @GET("?method=artist.gettopalbums&api_key=ENTERAPIKEY&format=json")
    Call<AlbumResponse> getimages(@Query("artist") String name);
    @GET("?method=tag.gettopalbums&tag=acoustic&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_acoustic();
    @GET("?method=tag.gettopalbums&tag=Rock&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_rock();
    @GET("?method=tag.gettopalbums&tag=folk&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_folk();
    @GET("?method=tag.gettopalbums&tag=indie+rock&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_indie_rock();
    @GET("?method=tag.gettopalbums&tag=jazz&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_jazz();
    @GET("?method=tag.gettopalbums&tag=rap&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_rap();

    @GET("?method=tag.gettopalbums&tag=hip%20hop&api_key=ENTERAPIKEY&format=json")
    Call<TopAlbumsResponse>getTopalbums_hiphop();
    @GET("?method=album.getinfo&api_key=ENTERAPIKEY&format=json")
    Call<AlbuminfoResponse>getAlbuminfo(
            @Query("artist") String artist,
            @Query("album") String album);




}

