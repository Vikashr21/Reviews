package com.example.reviews.Food.FoodFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.Food.FoodAdapter;
import com.example.reviews.Food.FoodResponse;
import com.example.reviews.Food.RetrofitFood;
import com.example.reviews.Food.TheMealDbApi;
import com.example.reviews.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Random extends Fragment {
    private RecyclerView foodList;
    private FoodAdapter adapter;
    private GridLayoutManager layoutManager;
private boolean isFetchingfood=false;
    public Random() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_random, container, false);

        layoutManager = new GridLayoutManager(this.getActivity(),3);
        foodList =(RecyclerView)root.findViewById(R.id.Foodlist);
        foodList.setLayoutManager(layoutManager);
        setupOnScrollListener();
        getRandom();
        return root;
    }



    public void getRandom(){

        isFetchingfood=true;
        TheMealDbApi api= RetrofitFood.getRetrofitInstance().create(TheMealDbApi.class);
        api.getRandom()
                .enqueue(new Callback<FoodResponse>() {
                    @Override
                    public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                        if(response.isSuccessful())
                        {
                            FoodResponse foodResponse=response.body();
                            if(adapter==null)
                            { adapter=new FoodAdapter(foodResponse.getMeal());
                               foodList.setAdapter(adapter);
                            }else
                                adapter.appendMeals(foodResponse.getMeal());

                        isFetchingfood=false;
                        }

                        else
                            Toast.makeText(getContext(),"Error1",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<FoodResponse> call, Throwable t) {
                        Toast.makeText(getContext(),"Error2",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setupOnScrollListener() {
        final GridLayoutManager manager = new GridLayoutManager(this.getActivity(),3);
       foodList.setLayoutManager(manager);
        foodList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@androidx.annotation.NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingfood) {
                        getRandom();
                    }
                }
            }
        });
    }


}
