package com.example.reviews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.Food.Food;
import com.example.reviews.Food.FoodResponse;
import com.example.reviews.Food.RetrofitFood;
import com.example.reviews.Food.TheMealDbApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodActivity2 extends AppCompatActivity {

    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";

    public static String FOOD_ID="foodid";
    private ImageView image;
    private   TextView title;
    private TextView area;
    private TextView category;
    private TextView description;
    private TextView descriptionlabel;
    private LinearLayout vedio;
    private TextView vediolabel;
    Toolbar toolbar;

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food2);
        toolbar=findViewById(R.id.toolbarfood);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


name=getIntent().getStringExtra(FOOD_ID);

        initUI();
        getMeal(name);


    }


    private void initUI() {

        image=findViewById(R.id.imagefood);

        title=findViewById(R.id.foodTitle);

        area=findViewById(R.id.foodArea);

        category=findViewById(R.id.foodCategory);

        description=findViewById(R.id.description);

     vedio=findViewById(R.id.foodVedio);
     vediolabel=findViewById(R.id.vedioLabel);
 descriptionlabel=findViewById(R.id.DescriptionLabel);

    }
    private void getMeal(final String  name) {
        final TheMealDbApi api= RetrofitFood.getRetrofitInstance().create(TheMealDbApi.class);
        api.getmeal(name)
                .enqueue(new Callback<FoodResponse>() {
                    @Override
                    public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                        if(response.isSuccessful())
                        {
                            final Food food;
                            FoodResponse meal=response.body();
                            if(meal!=null &&meal.getMeal()!=null)
                            {
                                food=meal.getMeal().get(0);
                                title.setText(food.getStrMeal());
                                area.setText(food.getStrArea());
                                category.setText(food.getStrCategoey());
                                description.setText(food.getStrinstructions());
                                descriptionlabel.setVisibility(View.VISIBLE);
                                if (!isFinishing()) {
                                    Glide.with(FoodActivity2.this)
                                            .load(food.getThumb())
                                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                                    .into(image);




                                    //////////////////////////////////////////

                                   vediolabel.setVisibility(View.VISIBLE);
                                    vedio.removeAllViews();

                                        View parent = getLayoutInflater().inflate(R.layout.thumbnail_trailer, vedio, false);
                                        ImageView thumbnail = parent.findViewById(R.id.thumbnail);



                                        thumbnail.requestLayout();
                                        thumbnail.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(food.getVedio()));
                                                startActivity(intent);
                                            }
                                        });

                                        String x=food.getVedio();
                                        Glide.with(FoodActivity2.this)
                                                .load(String.format(YOUTUBE_THUMBNAIL_URL,x.substring(32,x.length())))
                                                .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                                                .into(thumbnail);


                                        vedio.addView(parent);



//////////////////////////////////////////////////////////////////////////////
                                }



                            }else
                            {
                                Toast.makeText(FoodActivity2.this," error1",Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(FoodActivity2.this," error2",Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<FoodResponse> call, Throwable t) {
                        Toast.makeText(FoodActivity2.this," errorfailer",Toast.LENGTH_LONG).show();
                    }
                });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
