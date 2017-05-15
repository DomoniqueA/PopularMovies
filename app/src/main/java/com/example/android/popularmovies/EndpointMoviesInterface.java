package com.example.android.popularmovies;

import com.example.android.popularmovies.data.FilmResponsePOJO;
import com.example.android.popularmovies.data.ReviewResponsePOJO;
import com.example.android.popularmovies.data.TrailerResponsePOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Domonique A on 3/25/17.
 */

interface EndpointMoviesInterface {

    @GET("popular?api_key=7c9079d73d6031805301a8391e8ce003")
    Call<FilmResponsePOJO> getPopularMovies();

    @GET("top_rated?api_key=7c9079d73d6031805301a8391e8ce003")
    Call<FilmResponsePOJO> getTopRatedMovies();

    @GET("/{id}/videos?api_key=7c9079d73d6031805301a8391e8ce003")
    Call<TrailerResponsePOJO> getMovieTrailers(@Path("id") String movieID);

    @GET("/{id}/reviews?api_key=7c9079d73d6031805301a8391e8ce003")
    Call<ReviewResponsePOJO> getMovieReviews(@Path("id") String movieID);
}
