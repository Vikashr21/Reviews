package com.example.reviews;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.Food.FoodAdapter;
import com.example.reviews.Food.FoodResponse;
import com.example.reviews.Food.RetrofitFood;
import com.example.reviews.Food.TheMealDbApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodcategoryActivity extends AppCompatActivity {


    public static String CATEGORY="category";
    RecyclerView foodList;
    private GridLayoutManager layoutManager;
  private   FoodAdapter adapter;
private String cat;

private  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodcategory);
        foodList=findViewById(R.id.Foodlist);
        layoutManager=new GridLayoutManager(FoodcategoryActivity.this,3);
        foodList.setLayoutManager(layoutManager);
        toolbar=(Toolbar) findViewById(R.id.toolbarfoodcategory);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    cat=getIntent().getStringExtra(CATEGORY);

toolbar.setTitle(cat);



    getmeals(cat);

    }

    private void getmeals(final String cat) {

        TheMealDbApi api= RetrofitFood.getRetrofitInstance().create(TheMealDbApi.class);

        api.getmeals(cat)
                .enqueue(new Callback<FoodResponse>() {
                    @Override
                    public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                        if(response.isSuccessful())
                        {
                            FoodResponse foodResponse=response.body();
                            if(foodResponse!=null&&foodResponse.getMeal()!=null)
                            {
                                if(adapter==null)
                                { adapter=new FoodAdapter(foodResponse.getMeal());
                                    foodList.setAdapter(adapter);
                                }else
                                    adapter.appendMeals(foodResponse.getMeal());


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FoodResponse> call, Throwable t) {

                    }
                });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}
