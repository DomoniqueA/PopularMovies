package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class  MainActivity extends AppCompatActivity {
    private static final int NUM_LIST_ITEMS = 58;
    private MovieAdapter mAdapter;
    private RecyclerView mMovieGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieGrid = (RecyclerView) findViewById(R.id.movie_thumbs);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mMovieGrid.setLayoutManager(layoutManager);

        mMovieGrid.setHasFixedSize(false);

        mAdapter = new MovieAdapter(NUM_LIST_ITEMS);

        mMovieGrid.setAdapter(mAdapter);
    }

}
