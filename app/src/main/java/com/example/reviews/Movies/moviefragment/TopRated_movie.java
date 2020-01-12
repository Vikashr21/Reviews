package com.example.reviews.Movies.moviefragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.Movies.Genre;
import com.example.reviews.Movies.GenresResponse;
import com.example.reviews.Movies.MoviesAdapter;
import com.example.reviews.Movies.RetrofitMovies;
import com.example.reviews.Movies.MoviesResponse;
import com.example.reviews.Movies.TMDbApi;
import com.example.reviews.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRated_movie extends Fragment {

    private RecyclerView moviesList;
    private MoviesAdapter adapter;
    LinearLayoutManager layoutManager;
    private static final String LANGUAGE = "en-US";
    private boolean isFetchingMovies=false;
    private int currentPage = 1;
    private List<Genre> movieGenres;
private String Apikey="da76599caff725e6cd4042275923dcfd";
    public TopRated_movie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_top_rated_movie, container, false);
        layoutManager = new LinearLayoutManager(this.getActivity());
        moviesList =(RecyclerView)root.findViewById(R.id.cardlist);
        moviesList.setLayoutManager(layoutManager);

        setupOnScrollListener();
        getGenres();




        return root;
    }


    public void getGenres(){
        TMDbApi api = RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);

        api.getGenres(Apikey, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                movieGenres=genresResponse.getGenres();
                                getMovies(currentPage);
                            } else {
                                Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                    }
                });
    }



    public void getMovies(int page)
    {
        isFetchingMovies = true;
        TMDbApi api = RetrofitMovies.getRetrofitInstance().create(TMDbApi.class);

        api.getTopRatedMovies(Apikey, LANGUAGE, page)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@androidx.annotation.NonNull Call<MoviesResponse> call, @androidx.annotation.NonNull Response<MoviesResponse> response) {


                        if (response.isSuccessful()) {

                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {

                                if(adapter==null) {
                                    adapter = new MoviesAdapter(moviesResponse.getMovies(), movieGenres);
                                    moviesList.setAdapter(adapter);
                                }
                                else{
                                    adapter.appendMovies(moviesResponse.getMovies());
                                }

                                currentPage = moviesResponse.getPage();
                                isFetchingMovies = false;
                            } else {

                                Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                    }
                });
    }





    private void setupOnScrollListener() {
        final LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        moviesList.setLayoutManager(manager);
        moviesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@androidx.annotation.NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingMovies) {
                        getMovies(currentPage + 1);
                    }
                }
            }
        });
    }
}
