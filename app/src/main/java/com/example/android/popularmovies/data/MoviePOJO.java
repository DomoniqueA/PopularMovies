package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Domonique A on 25/03/2017.
 */


public class MoviePOJO implements Parcelable {

    public final static Parcelable.Creator<MoviePOJO> CREATOR = new Creator<MoviePOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MoviePOJO createFromParcel(Parcel in) {
            MoviePOJO instance = new MoviePOJO();
            instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.overview = ((String) in.readValue((String.class.getClassLoader())));
            instance.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public MoviePOJO[] newArray(int size) {
            return (new MoviePOJO[size]);
        }

    };
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(posterPath);
        dest.writeValue(overview);
        dest.writeValue(releaseDate);
        dest.writeValue(id);
        dest.writeValue(originalTitle);
        dest.writeValue(backdropPath);
        dest.writeValue(voteAverage);
    }

    public int describeContents() {
        return 0;
    }

}

