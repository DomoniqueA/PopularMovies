package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.IOException;

import static com.example.android.popularmovies.utilities.NetworkUtils.ORDER_MDB_URL;
import static com.example.android.popularmovies.utilities.NetworkUtils.buildImgUrl;
import static com.example.android.popularmovies.utilities.NetworkUtils.buildUrl;
import static com.example.android.popularmovies.utilities.NetworkUtils.getResponseFromHttpUrl;

/**
 * Created by domoniqueasmith on 27/02/2017.
 */

public final class MovieDataJsonUtils {

    private static String imagePath;
    public static String path = imagePath;

    public static StringBuilder getMovieDataFromJson(Context context)
        throws JSONException {

        final String MDB_RESULTS = "results";

        try {
            return new StringBuilder().append(MDB_RESULTS).append(getResponseFromHttpUrl(buildUrl(ORDER_MDB_URL)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ImageView getThumbFromJson()
        throws JSONException {
        picasso.with(context).load(buildImgUrl(path));
        return null;
    }
}
