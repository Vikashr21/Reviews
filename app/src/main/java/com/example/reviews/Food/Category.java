package com.example.reviews.Food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
@SerializedName("idCategory")
    @Expose
    private String idcategory;
@SerializedName("strCategory")
@Expose
    private String category;

@SerializedName("strCategoryThumb")
    @Expose
    private String Thumb;

@SerializedName("strCategoryDescription")
    @Expose
    private String discription;


    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumb() {
        return Thumb;
    }

    public void setThumb(String thumb) {
        Thumb = thumb;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}

