package com.example.reviews.Food.FoodFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.Food.CategoryAdapter;
import com.example.reviews.Food.CategoryResponse;
import com.example.reviews.Food.RetrofitFood;
import com.example.reviews.Food.TheMealDbApi;
import com.example.reviews.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {


    private RecyclerView foodList;
    private CategoryAdapter adapter;
    private GridLayoutManager layoutManager;


    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_random, container, false);

        layoutManager = new GridLayoutManager(this.getActivity(),2
        );
        foodList =(RecyclerView)root.findViewById(R.id.Foodlist);
        foodList.setLayoutManager(layoutManager);
        getCategory();
        return root;}

    public void getCategory(){

        TheMealDbApi api= RetrofitFood.getRetrofitInstance().create(TheMealDbApi.class);
        api.getCategories()
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                        if(response.isSuccessful())
                        {
                           CategoryResponse categoryResponse=response.body();
                            if(adapter==null)
                            { adapter=new CategoryAdapter(categoryResponse.getCategories());
                                foodList.setAdapter(adapter);
                            }else
                                adapter.appendCategory(categoryResponse.getCategories());

                        }
                        else
                            Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
