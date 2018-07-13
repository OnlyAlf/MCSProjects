package com.example.admin.newapp.Tables;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EpisodeTable {

    public static final String TABLE_NAME = "episode";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SEASONID = "SeasonId";
    public static final String COLUMN_FAVORITE = "Favorite";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_RATED = "Rated";
    public static final String COLUMN_RELEASED = "Released";
    public static final String COLUMN_SEASON = "Season";
    public static final String COLUMN_EPISODE = "Episode";
    public static final String COLUMN_RUNTIME = "Runtime";
    public static final String COLUMN_GENRE = "Genre";
    public static final String COLUMN_DIRECTOR = "Director";
    public static final String COLUMN_WRITER = "Writer";
    public static final String COLUMN_ACTORS = "Actors";
    public static final String COLUMN_PLOT = "Plot";
    public static final String COLUMN_LANGUAGE = "Language";
    public static final String COLUMN_COUNTRY = "Country";
    public static final String COLUMN_METASCORE = "Metascore";
    public static final String COLUMN_IMDBRATING = "ImdbRating";
    public static final String COLUMN_IMDBID = "ImdbId";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_DIRECTORYPATH = "DirectoryPath";


    public static final String CREATE_EPISODES_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_FAVORITE + " TEXT, "
                    + COLUMN_TITLE + " TEXT , "
                    + COLUMN_YEAR + " TEXT , "
                    + COLUMN_RATED + " TEXT , "
                    + COLUMN_RELEASED + " TEXT , "
                    + COLUMN_SEASON + " TEXT , "
                    + COLUMN_EPISODE + " TEXT , "
                    + COLUMN_RUNTIME + " TEXT , "
                    + COLUMN_GENRE + " TEXT , "
                    + COLUMN_DIRECTOR + " TEXT , "
                    + COLUMN_WRITER + " TEXT , "
                    + COLUMN_ACTORS + " TEXT , "
                    + COLUMN_PLOT + " TEXT , "
                    + COLUMN_LANGUAGE + " TEXT , "
                    + COLUMN_COUNTRY + " TEXT , "
                    + COLUMN_METASCORE + " TEXT , "
                    + COLUMN_IMDBRATING + " TEXT , "
                    + COLUMN_IMDBID + " TEXT , "
                    + COLUMN_TYPE + " TEXT , "
                    + COLUMN_DIRECTORYPATH + " TEXT , "
                    + COLUMN_SEASONID + " TEXT , "
                    + "FOREIGN KEY("+COLUMN_SEASONID+") REFERENCES "+SeasonTable.TABLE_NAME+"("+SeasonTable.COLUMN_ID+") "
                    + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_EPISODES_TABLE);
    }

    public static void  onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(ShowTable.class.getSimpleName(), "Upgrading database from " + oldVersion +
                " to " + newVersion + ", destroying local data.");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
