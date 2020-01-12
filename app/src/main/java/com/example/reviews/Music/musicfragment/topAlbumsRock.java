package com.example.reviews.Music.musicfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews.Music.RetrofitMusic;
import com.example.reviews.Music.TopAlbumAdapter;
import com.example.reviews.Music.TopAlbumsResponse;
import com.example.reviews.Music.lastfmapi;
import com.example.reviews.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class topAlbumsRock extends Fragment {


    private RecyclerView musiclist;
    private GridLayoutManager layoutManager;
    private TopAlbumAdapter adapter;


    public topAlbumsRock() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_top_album, container, false);
        layoutManager=new GridLayoutManager(this.getActivity(),3);
        musiclist=root.findViewById(R.id.cardlist);
        musiclist.setLayoutManager(layoutManager);

              getTopAlbums_Rock();


        return root;
    }


    private void getTopAlbums_Rock() {

        lastfmapi api= RetrofitMusic.getRetrofitInstance().create(lastfmapi.class);

        api.getTopalbums_rock()
                .enqueue(new Callback<TopAlbumsResponse>() {
                    @Override
                    public void onResponse(Call<TopAlbumsResponse> call, Response<TopAlbumsResponse> response) {
                        if(response.isSuccessful())
                        {
                            TopAlbumsResponse topAlbumsResponse=response.body();
                            if(topAlbumsResponse!=null&&topAlbumsResponse.getTopAlbums()!=null&&topAlbumsResponse.getTopAlbums().getAlbumslist()!=null){

                                if(adapter==null){

                                    adapter=new TopAlbumAdapter(topAlbumsResponse.getTopAlbums().getAlbumslist());
                                    musiclist.setAdapter(adapter);

                                }else{
                                    adapter.appendalbum(topAlbumsResponse.getTopAlbums().getAlbumslist());
                                }



                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<TopAlbumsResponse> call, Throwable t) {

                    }
                });

    }


}
