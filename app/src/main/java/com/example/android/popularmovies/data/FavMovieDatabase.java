package com.example.android.popularmovies.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = FavMovieDatabase.VERSION)
public final class FavMovieDatabase {

    public static final int VERSION = 1;

    @Table(FavouriteColumns.class)
    public static final String FAVOURITES = "favourites";
}
