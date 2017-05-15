package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.MoviePOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Domonique A on 24/02/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final MovieAdapterOnClickHandler mClickHandler;
    public Context movieContext;
    private ArrayList<MoviePOJO> moviesList = new ArrayList<>();

    public MovieAdapter(Context context, MovieAdapterOnClickHandler clickHandler) {
        movieContext = context;
        mClickHandler = clickHandler;
    }

    public void setFilms(ArrayList<MoviePOJO> results) {
        moviesList.clear();
        moviesList.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForMovieItem = R.layout.movie_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForMovieItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int position) {
        MoviePOJO thisMoviePOJO = moviesList.get(position);
        movieViewHolder.bind(thisMoviePOJO);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(MoviePOJO clickedMovie);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        public final ImageView movieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.movie_thumbnail);
            itemView.setOnClickListener(this);
        }

        void bind(MoviePOJO movie) {
            String imagePath = movie.getPosterPath();
            String imageUrl = ("http://image.tmdb.org/t/p/w780/" + imagePath);
            Picasso.with(movieContext)
                    .load(imageUrl)
                    .placeholder(R.drawable.no_poster_w185)
                    .noFade()
                    .into(movieImageView);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MoviePOJO clickedMovie = moviesList.get(adapterPosition);
            mClickHandler.onClick(clickedMovie);
        }
    }
}