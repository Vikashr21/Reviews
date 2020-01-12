package com.example.reviews.Movies;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.MovieActicity;
import com.example.reviews.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private List<Movie> movies;
    private List<Genre> allGenres;


    public MoviesAdapter(List<Movie> movies, List<Genre> allGenres) {
        this.movies = movies;
        this.allGenres = allGenres;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void appendMovies(List<Movie> moviesToAppend) {
        movies.addAll(moviesToAppend);
        notifyDataSetChanged();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate;
        TextView title;
        TextView rating;
        TextView genres;
        ImageView poster;
        Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.release_date);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.userrating);
            genres = itemView.findViewById(R.id.genre);
            poster = itemView.findViewById(R.id.thumbnail);


    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(), MovieActicity.class);
            intent.putExtra(MovieActicity.MOVIE_ID, movie.getId());
            v.getContext().startActivity(intent);
        }
    });


        }

        public void bind(Movie movie) {
            releaseDate.setText(movie.getReleaseDate().split("-")[0]);
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getRating()));
            genres.setText(getGenres(movie.getGenreIds()));
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
            this.movie = movie;

        }
        private String getGenres(List<Integer> genreIds) {
            List<String> movieGenres = new ArrayList<>();
            for (Integer genreId : genreIds) {
                for (Genre genre : allGenres) {
                    if (genre.getId() == genreId) {
                        movieGenres.add(genre.getName());
                        break;
                    }
                }
            }
            return TextUtils.join(", ", movieGenres);
        }
    }
}