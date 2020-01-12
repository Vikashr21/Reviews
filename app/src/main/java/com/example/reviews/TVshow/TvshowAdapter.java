package com.example.reviews.TVshow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.R;
import com.example.reviews.TvshowActivity;

import java.util.List;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.TvshowViewHolder> {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

   private List<Tvshows>tvshows;
    public TvshowAdapter(List<Tvshows> tvshows){
        this.tvshows=tvshows;

    }

    @NonNull
    @Override
    public TvshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        return new TvshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowViewHolder holder, int position) {
        holder.bind(tvshows.get(position));

    }

    @Override
    public int getItemCount() {
        return tvshows.size();
    }


    public void appendshows(List<Tvshows> showsToAppend) {
       tvshows.addAll(showsToAppend);
        notifyDataSetChanged();
    }

    public class TvshowViewHolder extends RecyclerView.ViewHolder{

    TextView rating;

    Tvshows tvshows;
        ImageView poster;



        public TvshowViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.thumbnail);
            rating=itemView.findViewById(R.id.rating);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), TvshowActivity.class);
                    intent.putExtra(TvshowActivity.TVSHOW_ID, tvshows.getId());
                    v.getContext().startActivity(intent);
                }
            });


        }

        public void bind(Tvshows tvshows) {

            rating.setText(String.valueOf(tvshows.getVote_average()));

            Glide.with(itemView)
                    .load(IMAGE_BASE_URL+tvshows.getPoster_path())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
            this.tvshows=tvshows;
        }
    }
}
