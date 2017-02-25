package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by domoniqueasmith on 24/02/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private int mNumberItems;

    public MovieAdapter(int numberOfItems) {
        mNumberItems = numberOfItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForMovieItem = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachImmediately = false;

        View view = inflater.inflate(layoutIdForMovieItem, viewGroup, shouldAttachImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieItemThumbView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieItemThumbView = (ImageView) itemView.findViewById(R.id.movie_thumbnail);
        }
    }
}
