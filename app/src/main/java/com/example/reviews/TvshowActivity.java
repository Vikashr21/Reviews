package com.example.reviews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.TVshow.RetrofitTv;
import com.example.reviews.TVshow.Review_tv;
import com.example.reviews.TVshow.Review_tvResponse;
import com.example.reviews.TVshow.TMDbApi_tv;
import com.example.reviews.TVshow.Trailer_tv;
import com.example.reviews.TVshow.Trailer_tvResponse;
import com.example.reviews.TVshow.Tvshows;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvshowActivity extends AppCompatActivity {

    private static final String LANGUAGE = "en-US";

    public static String TVSHOW_ID = "movie_id";

    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";

    private ImageView tvBackdrop;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvOverviewLabel;
    private TextView tvReleaseDate;
    private RatingBar tvRating;
    private LinearLayout tvTrailers;
    private LinearLayout tvReviews;

    private TextView trailersLabel;
    private TextView reviewsLabel;
    Toolbar toolbar;
    private int tv_id;
    private String Apikey="ENTER API KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow);

        toolbar=(Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_id=getIntent().getIntExtra(TVSHOW_ID, tv_id);

        initUI();

        getShow(tv_id);
    }

    private void initUI() {
        tvBackdrop = (ImageView) findViewById(R.id.tvDetailsBackdrop);
        tvTitle = findViewById(R.id.TvDetailsTitle);
        tvOverview = findViewById(R.id.tvDetailsOverview);
        tvOverviewLabel = findViewById(R.id.summaryLabel);
        tvReleaseDate = findViewById(R.id.tvDetailsReleaseDate);
        tvRating = findViewById(R.id.tvDetailsRating);
        tvTrailers = findViewById(R.id.tvTrailers);
        tvReviews = findViewById(R.id.tvReviews);
        trailersLabel = findViewById(R.id.trailersLabel_tv);
        reviewsLabel = findViewById(R.id.reviewsLabel_tv);
    }


    public void getShow(final int tv_id) {
        TMDbApi_tv api= RetrofitTv.getRetrofitInstance().create(TMDbApi_tv.class);
        api.getShow(tv_id, Apikey, LANGUAGE)
                .enqueue(new Callback<Tvshows>() {
                    @Override
                    public void onResponse(Call<Tvshows> call, Response<Tvshows> response) {
                        if (response.isSuccessful()) {
                           Tvshows tvshows = response.body();
                            if (tvshows != null) {
                                tvTitle.setText(tvshows.getName());
                                tvOverviewLabel.setVisibility(View.VISIBLE);
                                tvOverview.setText(tvshows.getOverview());
                                tvRating.setVisibility(View.VISIBLE);
                                tvRating.setRating(tvshows.getVote_average() / 2);
                                tvReleaseDate.setText(tvshows.getReleasedate());
                                //tvGenres.setText(String.valueOf(tvshows.getGenres()));
                                if (!isFinishing()) {
                                   Glide.with(TvshowActivity.this)
                                           .load(IMAGE_BASE_URL + tvshows.getBackdrop_path())
                                           .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                                         .into(tvBackdrop);
                                    getTrailers(tvshows);
                                    getReviews(tvshows);
                                }
                            } else {
                                finish();
                            }
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Tvshows> call, Throwable t) {
                        finish();
                    }
                });
    }



   public void getTrailers(Tvshows tvshows) {
        TMDbApi_tv api = RetrofitTv.getRetrofitInstance().create(TMDbApi_tv.class);

        api.getTrailers(tvshows.getId(),Apikey, LANGUAGE)
                .enqueue(new Callback<Trailer_tvResponse>() {
                    @Override
                    public void onResponse(Call<Trailer_tvResponse> call, Response<Trailer_tvResponse> response) {
                        if (response.isSuccessful()) {
                            Trailer_tvResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {

                                trailersLabel.setVisibility(View.VISIBLE);
                                tvTrailers.removeAllViews();
                                for (final Trailer_tv trailer_tv :trailerResponse.getTrailers()) {
                                    View parent = getLayoutInflater().inflate(R.layout.thumbnail_trailer, tvTrailers, false);
                                    ImageView thumbnail = parent.findViewById(R.id.thumbnail);



                                    thumbnail.requestLayout();
                                    thumbnail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            showTrailer(String.format(YOUTUBE_VIDEO_URL, trailer_tv.getKey()));
                                        }
                                    });
                                    Glide.with(TvshowActivity.this)
                                            .load(String.format(YOUTUBE_THUMBNAIL_URL, trailer_tv.getKey()))
                                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                                            .into(thumbnail);


                                    tvTrailers.addView(parent);
                                }




                            } else {
                                trailersLabel.setVisibility(View.GONE);
                            }
                        } else {
                            trailersLabel.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Trailer_tvResponse> call, Throwable t) {
                        trailersLabel.setVisibility(View.GONE);
                    }
                });
    }


    public void getReviews(Tvshows tvshows) {

        TMDbApi_tv api= RetrofitTv.getRetrofitInstance().create(TMDbApi_tv.class);
        api.getReviews(tvshows.getId(), Apikey, LANGUAGE)
                .enqueue(new Callback<Review_tvResponse>() {
                    @Override
                    public void onResponse(Call<Review_tvResponse> call, Response<Review_tvResponse> response) {
                        if (response.isSuccessful()) {
                            Review_tvResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                reviewsLabel.setVisibility(View.VISIBLE);
                                tvReviews.removeAllViews();
                                for (Review_tv review : reviewResponse.getReviews()) {
                                    View parent = getLayoutInflater().inflate(R.layout.review,tvReviews, false);
                                    TextView author = parent.findViewById(R.id.reviewAuthor);
                                    TextView content = parent.findViewById(R.id.reviewContent);
                                    author.setText(review.getAuthor());
                                    content.setText(review.getContent());
                                    tvReviews.addView(parent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Review_tvResponse> call, Throwable t) {

                    }
                });
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void showTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
