package com.example.android.popularmovies.utilities;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.example.android.popularmovies.MainActivity;

import java.net.URL;



@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
        if (params.length == 0) {
            return null;
        }

        String location = params[0];
        URL weatherRequestUrl = NetworkUtils.buildUrl(location);

        try {
            String jsonMdbResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl);

            String[] simpleJsonMovieData = MovieDataJsonUtils
                    .getMovieDataFromJson(MainActivity.this, jsonMdbResponse);

            return simpleJsonMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}