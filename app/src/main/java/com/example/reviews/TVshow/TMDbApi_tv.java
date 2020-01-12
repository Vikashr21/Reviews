package com.example.reviews.TVshow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi_tv {


    @GET("popular")
    Call<TvshowResponse> getPopular(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
    @GET("top_rated")
    Call<TvshowResponse> getToprated(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("{tv_id}/videos")
    Call<Trailer_tvResponse>getTrailers(
            @Path("tv_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language


    );
    @GET("{tv_id}/reviews")
    Call<Review_tvResponse> getReviews(
            @Path("tv_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );

    @GET("{tv_id}")
    Call<Tvshows> getShow(
            @Path("tv_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );

}
