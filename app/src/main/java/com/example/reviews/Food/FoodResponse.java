package com.example.reviews.Food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {

    @SerializedName("meals")
    @Expose
    private List<Food> meal;

    public List<Food> getMeal() {
        return meal;
    }

    public void setMeal(List<Food> meal) {
        this.meal = meal;
    }
}
