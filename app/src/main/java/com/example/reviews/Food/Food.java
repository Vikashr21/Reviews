package com.example.reviews.Food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {


    @SerializedName("idMeal")
    @Expose
    private int idMeal;

    @SerializedName("strMeal")
    @Expose
    private String strMeal;

    @SerializedName("strCategory")
    @Expose
    private String strCategoey;

    @SerializedName("strArea")
    @Expose
    private String strArea;
    @SerializedName("strInstructions")
    @Expose
    private String strinstructions;

    @SerializedName("strMealThumb")
    @Expose
    private String thumb;
    @SerializedName("strYoutube")
    @Expose
    private String vedio;


    public String getStrinstructions() {
        return strinstructions;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrCategoey() {
        return strCategoey;
    }

    public void setStrCategoey(String strCategoey) {
        this.strCategoey = strCategoey;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public void setStrinstructions(String strinstructions) {
        this.strinstructions = strinstructions;
    }
}
