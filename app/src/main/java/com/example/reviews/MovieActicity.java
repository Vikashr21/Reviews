package com.example.reviews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.Movies.Genre;
import com.example.reviews.Movies.GenresResponse;
import com.example.reviews.Movies.Movie;
import com.example.reviews.Movies.RetrofitMovies;
import com.example.reviews.Movies.Review;
import com.example.reviews.Movies.ReviewResponse;
import com.example.reviews.Movies.TMDbApi;
import com.example.reviews.Movies.Trailer;
import com.example.reviews.Movies.TrailerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieActicity extends AppCompatActivity {

    private static final String LANGUAGE = "en-US";

    public static String MOVIE_ID = "movie_id";

    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";

    private ImageView movieBackdrop;
    private TextView movieTitle;
    private TextView movieGenres;
    private TextView movieOverview;
    private TextView movieOverviewLabel;
    private TextView movieReleaseDate;
    private RatingBar movieRating;
    private LinearLayout movieTrailers;
    private LinearLayout movieReviews;
    private int movieId;
    private TextView trailersLabel;
    private TextView reviewsLabel;
    Toolbar toolbar;
    private String Apikey="da76599caff725e6cd4042275923dcfd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);


       // setupToolbar();
toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        initUI();

        getMovie(movieId);
    }



  /*  private void setupToolbar() {

    }*/

    private void initUI() {
        movieBackdrop = findViewById(R.id.movieDetailsBackdrop);
        movieTitle = findViewById(R.id.movieDetailsTitle);
        movieGenres = findViewById(R.id.movieDetailsGenres);
        movieOverview = findViewById(R.id.movieDetailsOverview);
        movieOverviewLabel = findViewById(R.id.summaryLabel);
        movieReleaseDate = findViewById(R.id.movieDetailsReleaseDate);
        movieRating = findViewById(R.id.movieDetailsRating);
        movieTrailers = findViewById(R.id.movieTrailers);
        movieReviews = findViewById(R.id.movieReviews);
        trailersLabel = findViewById(R.id.trailersLabel);
        reviewsLabel = findViewById(R.id.reviewsLabel);
    }




    public void getMovie(int movieId) {
        TMDbApi api= RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);
        api.getMovie(movieId, Apikey, LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            if (movie != null) {
                                movieTitle.setText(movie.getTitle());
                                movieOverviewLabel.setVisibility(View.VISIBLE);
                                movieOverview.setText(movie.getOverview());
                                movieRating.setVisibility(View.VISIBLE);
                                movieRating.setRating(movie.getRating() / 2);
                                getGenres(movie);
                                movieReleaseDate.setText(movie.getReleaseDate());
                                if (!isFinishing()) {
                                    Glide.with(MovieActicity.this)
                                            .load(IMAGE_BASE_URL + movie.getBackdrop())
                                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                                            .into(movieBackdrop);
                                    getTrailers(movie);
                                    getReviews(movie);
                                }
                            } else {
                                finish();
                            }
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        finish();
                    }
                });
    }



    public void getGenres( final Movie movie){
        TMDbApi api = RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);

        api.getGenres(Apikey, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (movie.getGenres() != null) {
                            List<String> currentGenres = new ArrayList<>();
                            for (Genre genre : movie.getGenres()) {
                                currentGenres.add(genre.getName());
                            }
                            movieGenres.setText(TextUtils.join(", ", currentGenres));
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        Toast.makeText(MovieActicity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                    }
                });
    }



    public void getTrailers(Movie movie) {
        TMDbApi api = RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);

        api.getTrailers(movieId,Apikey, LANGUAGE)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {

                                trailersLabel.setVisibility(View.VISIBLE);
                                movieTrailers.removeAllViews();
                                for (final Trailer trailer :trailerResponse.getTrailers()) {
                                    View parent = getLayoutInflater().inflate(R.layout.thumbnail_trailer, movieTrailers, false);
                                    ImageView thumbnail = parent.findViewById(R.id.thumbnail);



                                    thumbnail.requestLayout();
                                    thumbnail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            showTrailer(String.format(YOUTUBE_VIDEO_URL, trailer.getKey()));
                                        }
                                    });
                                    Glide.with(MovieActicity.this)
                                            .load(String.format(YOUTUBE_THUMBNAIL_URL, trailer.getKey()))
                                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                                            .into(thumbnail);



                                    movieTrailers.addView(parent);
                                }




                            } else {
                                trailersLabel.setVisibility(View.GONE);
                            }
                        } else {
                            trailersLabel.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        trailersLabel.setVisibility(View.GONE);
                    }
                });
    }


    public void getReviews(Movie movie) {

       TMDbApi api= RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);
        api.getReviews(movieId, Apikey, LANGUAGE)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                reviewsLabel.setVisibility(View.VISIBLE);
                                movieReviews.removeAllViews();
                                for (Review review : reviewResponse.getReviews()) {
                                    View parent = getLayoutInflater().inflate(R.layout.review, movieReviews, false);
                                    TextView author = parent.findViewById(R.id.reviewAuthor);
                                    TextView content = parent.findViewById(R.id.reviewContent);
                                    author.setText(review.getAuthor());
                                    content.setText(review.getContent());
                                    movieReviews.addView(parent);
                            }
                    }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {

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
