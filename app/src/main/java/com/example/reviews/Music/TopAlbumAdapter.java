package com.example.reviews.Music;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reviews.MusicActivity;
import com.example.reviews.R;

import java.util.List;

public class TopAlbumAdapter extends RecyclerView.Adapter<TopAlbumAdapter.TopAlbumViewHolder> {

  List<Album> albums;

    public TopAlbumAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public TopAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        return new TopAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAlbumViewHolder holder, int position) {
        holder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }


    public void appendalbum(List<Album> albumToAppend) {
       albums.addAll(albumToAppend);
        notifyDataSetChanged();
    }


    public class TopAlbumViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView name;
        private TextView artistname;
        Album album;
        public TopAlbumViewHolder(@NonNull View itemView) {


            super(itemView);

            poster=itemView.findViewById(R.id.thumbnail);
            name=itemView.findViewById(R.id.rating);
            artistname=itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), MusicActivity.class);
                    //Toast.makeText(v.getContext(),"s[1]: "+s[1],Toast.LENGTH_LONG).show();

                    intent.putExtra(MusicActivity.ALBUMINFO,new String[]{name.getText().toString(),artistname.getText().toString()});
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Album album) {

            name.setText(album.getName());
            artistname.setText(album.getArtist().getName());
            Glide.with(itemView)
                    .load(album.getImage().get(2).getUrl())
            .into(poster);
            this.album=album;

        }
    }
}
