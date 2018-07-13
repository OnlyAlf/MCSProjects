package com.example.admin.newapp.Tables;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SeasonTable {

    public static final String TABLE_NAME = "Seasons";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FAVORITE = "Favorite";
    public static final String COLUMN_SEASONID = "SeasonId";
    public static final String COLUMN_SEASON = "Season";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_DIRECTORYPATH = "DirectoryPath";
    public static final String COLUMN_SHOWIMDBID = "ShowImdbId";
    public static final String COLUMN_EPISODEIMDBID = "EpisodeImdbId";
    public static final String COLUMN_TOTALEPISODES = "TotalEpisodes";
    public static final String COLUMN_SHOWID = "ShowId";

    public static final String CREATE_SEASON_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_FAVORITE + " TEXT, "
                    + COLUMN_SHOWID + " TEXT, "
                    + COLUMN_SEASONID + " TEXT, "
                    + COLUMN_SEASON + " TEXT , "
                    + COLUMN_TITLE + " TEXT , "
                    + COLUMN_DIRECTORYPATH + " TEXT , "
                    + COLUMN_SHOWIMDBID + " TEXT , "
                    + COLUMN_EPISODEIMDBID + " TEXT , "
                    + COLUMN_TOTALEPISODES + " TEXT , "
                    + "FOREIGN KEY("+COLUMN_SHOWID+") REFERENCES "+ShowTable.TABLE_NAME+"("+ShowTable.COLUMN_ID+") "
                    + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_SEASON_TABLE);
    }

    public static void  onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(ShowTable.class.getSimpleName(), "Upgrading database from " + oldVersion +
                " to " + newVersion + ", destroying local data.");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
