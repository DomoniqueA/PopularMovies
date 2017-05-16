package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies.data.FilmResponsePOJO;
import com.example.android.popularmovies.data.MoviePOJO;
import com.example.android.popularmovies.utilities.MovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
    public static final String EXTRA_MOVIEPOJO = "com.example.android.popularmovies.data.MoviePOJO";
    private static final String SAVED_STATE_RAW_JSON = "savedFilms";
    public String sortOrder = "popular";
    public ArrayList<MoviePOJO> films;
    private MovieAdapter mAdapter;
    private FilmResponsePOJO responsePojoSave;
    private SQLiteDatabase fmDB;

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SAVED_STATE_RAW_JSON, responsePojoSave);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MovieAdapter(this, this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SAVED_STATE_RAW_JSON)) {
                FilmResponsePOJO savedResults = savedInstanceState.getParcelable(SAVED_STATE_RAW_JSON);
                if (savedResults != null) {
                    films = (savedResults.results);
                    mAdapter.setFilms(films);
                }
            }
        } else initRetrofitNetworkCalls();

        RecyclerView mRecyclerview = (RecyclerView) findViewById(R.id.movie_thumbs);


        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns(), GridLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(layoutManager);

        mRecyclerview.setHasFixedSize(false);

        mRecyclerview.setAdapter(mAdapter);

    }

    private void initRetrofitNetworkCalls() {
        EndpointMoviesInterface endpointMovies = ApiClient.getClient().create(EndpointMoviesInterface.class);
        if (sortOrder.equals("popular")) {
            endpointMovies.getPopularMovies().enqueue(new Callback<FilmResponsePOJO>() {
                @Override
                public void onResponse(Call<FilmResponsePOJO> call, Response<FilmResponsePOJO> response) {
                    FilmResponsePOJO filmsList = response.body();
                    responsePojoSave = response.body();
                    mAdapter.setFilms(filmsList.results);
                }

                @Override
                public void onFailure(Call<FilmResponsePOJO> call, Throwable t) {
                    Log.d("Main", "onFailure() called with: call = [" + call + "], t = [" + t + "]");

                    Toast noDataToast;
                    String noDataToastMessage = "Check Data Connection and Refresh";
                    noDataToast = Toast.makeText(MainActivity.this, noDataToastMessage, Toast.LENGTH_LONG);
                    noDataToast.show();
                }
            });
        }
        if (sortOrder.equals("top_rated")) {
            endpointMovies.getTopRatedMovies().enqueue(new Callback<FilmResponsePOJO>() {
                @Override
                public void onResponse(Call<FilmResponsePOJO> call, Response<FilmResponsePOJO> response) {
                    FilmResponsePOJO filmsList = response.body();
                    responsePojoSave = response.body();
                    mAdapter.setFilms(filmsList.results);
                }

                @Override
                public void onFailure(Call<FilmResponsePOJO> call, Throwable t) {
                    Log.d("Main", "onFailure() called with: call = [" + call + "], t = [" + t + "]");

                    Toast noDataToast;
                    String noDataToastMessage = "Check Data Connection and Refresh";
                    noDataToast = Toast.makeText(MainActivity.this, noDataToastMessage, Toast.LENGTH_LONG);
                    noDataToast.show();
                }
            });
        }
        if (sortOrder.equals("favourites")) {
            if (fmDB == null) {
                Toast toast;
                String ToastMessage = "Add Favourites To See Them Here";
                toast = Toast.makeText(MainActivity.this, ToastMessage, Toast.LENGTH_LONG);
                toast.show();
            } else {

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Context context = MainActivity.this;
        if (id == R.id.popular_sort) {
            sortOrder = "popular";
            initRetrofitNetworkCalls();

            Toast.makeText(context, getString(R.string.pop_toast), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.top_rated_sort) {
            sortOrder = "top_rated";
            initRetrofitNetworkCalls();

            Toast.makeText(context, getString(R.string.top_toast), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.favourites_sort) {
            sortOrder = "favourites";
            initRetrofitNetworkCalls();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(MoviePOJO clickedMovie) {

        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentStartDetail = new Intent(context, destinationClass);
        intentStartDetail.putExtra(EXTRA_MOVIEPOJO, clickedMovie);
        startActivity(intentStartDetail);
    }

    //initialize database here.
    public void initialiseFavourites() {
        //fmDB = (SQLiteDatabase) FavouriteColumns;
    }
}
