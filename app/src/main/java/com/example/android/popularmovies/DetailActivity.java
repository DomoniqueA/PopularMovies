package com.example.android.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.popularmovies.data.FavMovieDatabase;
import com.example.android.popularmovies.data.FavouriteColumns;
import com.example.android.popularmovies.data.MoviePOJO;
import com.example.android.popularmovies.data.ReviewPOJO;
import com.example.android.popularmovies.data.ReviewResponsePOJO;
import com.example.android.popularmovies.data.TrailerPOJO;
import com.example.android.popularmovies.data.TrailerResponsePOJO;
import com.example.android.popularmovies.utilities.ReviewsAdapter;
import com.example.android.popularmovies.utilities.TrailersAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.popularmovies.MainActivity.EXTRA_MOVIEPOJO;


public class DetailActivity extends AppCompatActivity implements TrailersAdapter.TrailerAdapterOnClickHandler {
    private static final String ARG_COLUMN_COUNT = "column-count";
    public static String mMovieID;
    //public static final String EXTRA_TRAILER_URL
    public MoviePOJO mMovie;
    public TrailerResponsePOJO tResponsePOJOSave;
    public List<TrailerPOJO> trailerPOJOs;
    public ArrayList<ReviewPOJO> reviewPOJOs;
    public RecyclerView mTrailers;
    public RecyclerView mReviews;
    private int mColumnCount = 1;
    private TrailersAdapter tAdapter;
    private ReviewsAdapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mBackdrop = (ImageView) findViewById(R.id.movie_backdrop);
        TextView mTitle = (TextView) findViewById(R.id.original_title);
        ImageView mPoster = (ImageView) findViewById(R.id.movie_thumbnail);
        TextView mOverview = (TextView) findViewById(R.id.overview);
        TextView mVoteAverage = (TextView) findViewById(R.id.vote_average);
        TextView mReleaseDate = (TextView) findViewById(R.id.release_date);
        mTrailers = (RecyclerView) findViewById(R.id.trailersRV);
        mReviews = (RecyclerView) findViewById(R.id.reviewsRV);

        Intent intentStartDetail = getIntent();

        if (intentStartDetail != null) if (intentStartDetail.hasExtra(EXTRA_MOVIEPOJO)) {
            mMovie = intentStartDetail.getParcelableExtra(EXTRA_MOVIEPOJO);

            {
                String imagePath = String.valueOf(mMovie.getBackdropPath());
                String imageUrl = ("http://image.tmdb.org/t/p/w780/" + imagePath);
                Glide.with(this)
                        .load(imageUrl)
                        .into(mBackdrop);
            }

            mTitle.setText(String.valueOf(mMovie.getOriginalTitle()));

            {
                String imagePath = String.valueOf(mMovie.getPosterPath());
                String imageUrl = ("http://image.tmdb.org/t/p/w780/" + imagePath);
                Glide.with(this)
                        .load(imageUrl)
                        .placeholder(R.drawable.no_poster_w185)
                        .into(mPoster);
            }

            mOverview.setMovementMethod(new ScrollingMovementMethod());
            mOverview.setText(String.valueOf(mMovie.getOverview()));

            mVoteAverage.setText(String.valueOf(mMovie.getVoteAverage()));

            mReleaseDate.setText(String.valueOf(mMovie.getReleaseDate()));

            mMovieID = String.valueOf(mMovie.getId());

            initRetrofitTrailerCalls(mMovieID);

            inflateRecyclerViews(getLayoutInflater(), tAdapter);
            inflateRecyclerViews(getLayoutInflater(), rAdapter);

        }
    }

    private void initRetrofitTrailerCalls(String myMovieID) {
        EndpointMoviesInterface endpointMovies = ApiClient.getClient().create(EndpointMoviesInterface.class);

        endpointMovies.getMovieTrailers(myMovieID).enqueue(new Callback<TrailerResponsePOJO>() {
            @Override
            public void onResponse(Call<TrailerResponsePOJO> call, Response<TrailerResponsePOJO> response) {
                TrailerResponsePOJO videoList = response.body();
                trailerPOJOs = (videoList.getResults());
                tAdapter.setTrailers((ArrayList<TrailerPOJO>) videoList.results);
            }

            @Override

            public void onFailure(Call<TrailerResponsePOJO> call, Throwable t) {
                Log.d("Main", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

        endpointMovies.getMovieReviews(myMovieID).enqueue(new Callback<ReviewResponsePOJO>() {
            @Override
            public void onResponse(Call<ReviewResponsePOJO> call, Response<ReviewResponsePOJO> response) {
                ReviewResponsePOJO reviewList = response.body();
                reviewPOJOs = ((ArrayList<ReviewPOJO>) reviewList.getResults());
                rAdapter.setReviews(reviewPOJOs);
            }

            @Override
            public void onFailure(Call<ReviewResponsePOJO> call, Throwable throwable) {

            }
        });

    }


    public View inflateRecyclerViews(LayoutInflater inflater, RecyclerView.Adapter adapter) {
        View view = null;
        if (adapter instanceof TrailersAdapter) {
            view = inflater.inflate(R.layout.trailers_view, mTrailers, false);
        } else if (adapter instanceof ReviewsAdapter) {
            view = inflater.inflate(R.layout.reviews_view, mReviews, false);
        }

        tAdapter = new TrailersAdapter(this);
        rAdapter = new ReviewsAdapter();

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onClick(TrailerPOJO clickedTrailerLink) {

    }

    //This Code Adds the Favourited Movie to the Favourite Movie Database (FavMovieDatabase)
    public void addToFavourites(SQLiteDatabase fmdb) {
        if (fmdb == null) {
            // TODO Replace return with error managemnt (e.g. A Toast to let user know Database not Available)
            return;
        }


        ContentValues cv = new ContentValues();
        cv.put(FavouriteColumns.FID, mMovieID);
        cv.put(FavouriteColumns.TITLE, mMovie.getOriginalTitle());
        cv.put(FavouriteColumns.OVERVIEW, mMovie.getOverview());
        cv.put(FavouriteColumns.POSTER_PATH, mMovie.getPosterPath());
        cv.put(FavouriteColumns.BACKDROP_PATH, mMovie.getBackdropPath());

        try {
            fmdb.beginTransaction();
            fmdb.insert(FavMovieDatabase.FAVOURITES, null, cv);
            fmdb.setTransactionSuccessful();
        } catch (SQLException e) {
            //not good :(
        } finally {
            fmdb.endTransaction();
        }
    }

    ///video link is "https://www.youtube.com/watch?v=" + TrailerPOJO.getKey();
    //e.g. https://www.youtube.com/watch?v=bgeSXHvPoBI
}



