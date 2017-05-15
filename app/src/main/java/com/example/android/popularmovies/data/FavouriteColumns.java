package com.example.android.popularmovies.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Unique;

/**
 * Created by Domonique A Smith on 14/05/2017.
 */

public interface FavouriteColumns {

    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String FID = "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String TITLE = "title";

    @DataType(DataType.Type.TEXT)
    @Unique
    String OVERVIEW = "overview";

    @DataType(DataType.Type.TEXT)
    @Unique
    String POSTER_PATH = "poster_path";

    @DataType(DataType.Type.TEXT)
    @Unique
    String BACKDROP_PATH = "backdrop_path";
}
