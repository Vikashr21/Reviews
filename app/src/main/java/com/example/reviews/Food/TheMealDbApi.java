package com.example.reviews.Food;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMealDbApi {


    @GET("random.php")
    Call<FoodResponse> getRandom();

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("search.php")
    Call<FoodResponse> getmeal(@Query("s") String  name);

    @GET("filter.php")
    Call<FoodResponse> getmeals(@Query("c") String category);

}
