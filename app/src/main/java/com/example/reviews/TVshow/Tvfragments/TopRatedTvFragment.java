package com.example.reviews.TVshow.Tvfragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.R;
import com.example.reviews.TVshow.RetrofitTv;
import com.example.reviews.TVshow.TMDbApi_tv;
import com.example.reviews.TVshow.TvshowAdapter;
import com.example.reviews.TVshow.TvshowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedTvFragment extends Fragment {


    private RecyclerView tvshowList;
    private TvshowAdapter adapter;
    private static final String LANGUAGE = "en-US";
    private boolean isFetchingMovies=false;
    private int currentPage = 1;
    private GridLayoutManager layoutManager;
    //private List<Genre> movieGenres;
private String Apikey="da76599caff725e6cd4042275923dcfd";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_popular_tv, container, false);
        layoutManager = new GridLayoutManager(this.getActivity(),3);
        tvshowList =(RecyclerView)root.findViewById(R.id.cardlistpopular);
        tvshowList.setLayoutManager(layoutManager);

        setupOnScrollListener();
        getShow(currentPage);

        return root;


    }




    public void getShow(int page)
    {
        isFetchingMovies = true;
        TMDbApi_tv api = RetrofitTv.getRetrofitInstance().create(TMDbApi_tv.class);

        api.getToprated(Apikey, LANGUAGE,page)
                .enqueue(new Callback<TvshowResponse>() {

                    @Override
                    public void onResponse(Call<TvshowResponse> call, Response<TvshowResponse> response) {


                        if (response.isSuccessful()) {

                            TvshowResponse tvshowResponse = response.body();
                            if (tvshowResponse != null && tvshowResponse.getShow() != null) {

                                if(adapter==null) {
                                    adapter = new TvshowAdapter(tvshowResponse.getShow());
                                    tvshowList.setAdapter(adapter);
                                }
                                else{
                                    adapter.appendshows(tvshowResponse.getShow());
                                }

                                currentPage = tvshowResponse.getPage();
                                isFetchingMovies = false;
                            } else {

                                Toast.makeText(getContext(), "Please check your internet connection1.", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection2.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<TvshowResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Please check your internet connection3.", Toast.LENGTH_SHORT).show();

                    }
                });
    }





    private void setupOnScrollListener() {
        final GridLayoutManager manager = new GridLayoutManager(this.getActivity(),3);
        tvshowList.setLayoutManager(manager);
        tvshowList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@androidx.annotation.NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingMovies) {
                        getShow(currentPage + 1);
                    }
                }
            }
        });
    }


}
