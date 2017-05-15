package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by domoniqueasmith on 10/04/2017.
 */

public class TrailerResponsePOJO implements Parcelable {

    public final static Parcelable.Creator<TrailerResponsePOJO> CREATOR = new Creator<TrailerResponsePOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TrailerResponsePOJO createFromParcel(Parcel in) {
            TrailerResponsePOJO instance = new TrailerResponsePOJO();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (TrailerPOJO.class.getClassLoader()));
            return instance;
        }

        public TrailerResponsePOJO[] newArray(int size) {
            return (new TrailerResponsePOJO[size]);
        }

    };
    @SerializedName("results")
    @Expose
    public List<TrailerPOJO> results = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerPOJO> getResults() {
        return results;
    }

    public void setResults(List<TrailerPOJO> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}