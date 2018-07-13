package com.example.admin.newapp.Tables;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "series_guide.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        ShowTable.onCreate(database);
        SeasonTable.onCreate(database);
        EpisodeTable.onCreate(database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        ShowTable.onUpgrade(database, oldVersion, newVersion);
        SeasonTable.onUpgrade(database, oldVersion, newVersion);
        EpisodeTable.onUpgrade(database, oldVersion, newVersion);
    }
}
