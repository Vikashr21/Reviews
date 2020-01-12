package com.example.reviews;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.Music.RetrofitMusic;
import com.example.reviews.Music.lastfmapi;
import com.example.reviews.Music.musicfragment.temp.AlbuminfoResponse;
import com.example.reviews.Music.musicfragment.temp.Albuminfoclass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicActivity extends AppCompatActivity {


   public static String ALBUMINFO="album";
    private ImageView Albumimg;
    private TextView albumTitle;
    private TextView artistname;
    private TextView playcount;
    private TextView Summary;
    private LinearLayout Tracks;
    Toolbar toolbar;
    String artist;
    String album;
    String s[]=new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        toolbar=(Toolbar) findViewById(R.id.toolbar_music);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        s=getIntent().getStringArrayExtra(ALBUMINFO);

             artist=s[1];
            album=s[0];
   //     Toast.makeText(MusicActivity.this,"s[1]: "+s[1],Toast.LENGTH_LONG).show();
        initUI();


        getAlbuminfo( artist, album);
    }

    private void initUI() {
    albumTitle=findViewById(R.id.Albumtitle);

      Albumimg=findViewById(R.id.albumimage);

       artistname=findViewById(R.id.Artistname);

        playcount=findViewById(R.id.playcount);

        Summary=findViewById(R.id.summary);

        Tracks=findViewById(R.id.Trackslayout);



    }

    private void getAlbuminfo(final String artist, final String album) {

        lastfmapi  api= RetrofitMusic.getRetrofitInstance().create(lastfmapi.class);

        api.getAlbuminfo(artist,album)
                .enqueue(new Callback<AlbuminfoResponse>() {
                    @Override
                    public void onResponse(Call<AlbuminfoResponse> call, Response<AlbuminfoResponse> response) {
                        if(response.isSuccessful())
                        {
                          //  Toast.makeText(MusicActivity.this,"1  : "+response.body().getAlbuminfoclasses(),Toast.LENGTH_LONG).show();

                           AlbuminfoResponse albuminfoResponse=response.body();
                            final Albuminfoclass albuminfoclass;
                            if(albuminfoResponse!=null &&albuminfoResponse.getAlbuminfoclasses()!=null)
                            {

                             //   Toast.makeText(MusicActivity.this,"100",Toast.LENGTH_LONG).show();
                                albuminfoclass=albuminfoResponse.getAlbuminfoclasses();
                                albumTitle.setText(albuminfoclass.getName());
                                artistname.setText(albuminfoclass.getArtist());
                                playcount.setText(albuminfoclass.getPlaycount());
                                if(albuminfoclass.getInfoalbums()!=null) {
                                    Summary.setText(albuminfoclass.getInfoalbums().getSummary());
                                }else
                                    findViewById(R.id.summaryLabel).setVisibility(View.GONE);
                                if(!isFinishing())
                                {
                                    Glide.with(MusicActivity.this)
                                            .load(albuminfoclass.getImage().get(3).getUrl())
                                            .into(Albumimg);


                                    for (int i=0;i<albuminfoclass.getTrack().getTracklists().size();i++) {
                                        View parent = getLayoutInflater().inflate(R.layout.thumbnail_trailer, Tracks, false);
                                        ImageView thumbnail = parent.findViewById(R.id.thumbnail);
                                        TextView Trackname=parent.findViewById(R.id.text);


                                        thumbnail.requestLayout();
                                        final int finalI = i;
                                        thumbnail.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(albuminfoclass.getTrack().getTracklists().get(finalI).getUrl()));
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.setPackage("com.android.chrome");
                                                try {
                                                    startActivity(intent);
                                                } catch (ActivityNotFoundException ex) {
                                                    intent.setPackage(null);
                                                    startActivity(intent);
                                                }
                                            }
                                        });

                                        Trackname.setText(albuminfoclass.getTrack().getTracklists().get(i).getName());
                                        Glide.with(MusicActivity.this)
                                                .load(R.drawable.twentytwo)
                                                .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                                                .into(thumbnail);


                                        Tracks.addView(parent);
                                    }


                                }



                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AlbuminfoResponse> call, Throwable t) {

                    }
                });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
