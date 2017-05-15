package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Domonique A on 3/25/17.
 */

public class FilmResponsePOJO implements Parcelable {

    private final static Creator<FilmResponsePOJO> CREATOR = new Creator<FilmResponsePOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FilmResponsePOJO createFromParcel(Parcel in) {
            FilmResponsePOJO instance = new FilmResponsePOJO();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, MoviePOJO.class.getClassLoader());
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public FilmResponsePOJO[] newArray(int size) {
            return (new FilmResponsePOJO[size]);
        }

    };
    @SerializedName("results")
    @Expose
    public ArrayList<MoviePOJO> results = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MoviePOJO> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}