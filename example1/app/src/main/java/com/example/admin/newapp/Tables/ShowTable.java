package com.example.admin.newapp.Tables;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ShowTable {

    public static final String TABLE_NAME = "Shows";
    public static final String COLUMN_FAVORITE = "Favorite";
    public static final String COLUMN_SHOWIMDBID = "ShowImdbId";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SHOWID = "ShowId";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_DIRECTORYPATH = "DirectoryPath";
    public static final String COLUMN_PLOT = "Plot";
    public static final String COLUMN_IMDBRATING = "ImdbRating";
    public static final String COLUMN_TOTALSEASONS = "TotalSeasons";

    public static final String CREATE_SHOW_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_SHOWID+ " TEXT, "
                    + COLUMN_FAVORITE + " TEXT, "
                    + COLUMN_SHOWIMDBID + " TEXT , "
                    + COLUMN_TITLE + " TEXT , "
                    + COLUMN_DIRECTORYPATH + " TEXT , "
                    + COLUMN_PLOT + " TEXT , "
                    + COLUMN_IMDBRATING + " TEXT , "
                    + COLUMN_TOTALSEASONS + " TEXT "
                    + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_SHOW_TABLE);
    }

    public static void  onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(ShowTable.class.getSimpleName(), "Upgrading database from " + oldVersion +
                                            " to " + newVersion + ", destroying local data.");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
