package com.example.admin.newapp.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.admin.newapp.Tables.DatabaseHelper;
import com.example.admin.newapp.Tables.EpisodeTable;
import com.example.admin.newapp.Tables.SeasonTable;
import com.example.admin.newapp.Tables.ShowTable;

public class DatabaseContentProvider extends ContentProvider {

    private SQLiteDatabase database;
    private static final UriMatcher sUriMatcher;
    static final int SHOW_ID = 20;
    static final int SEASON_ID = 30;
    static final int EPISODE_ID = 40;
    static final String RESOURCE_SHOW = "SHOW";
    static final String RESOURCE_SEASON = "SEASON";
    static final String RESOURCE_EPISODE = "EPISODE";
    static final String AUTHORITY = "com.example.admin.newapp.DatabaseContentProvider";
    public static final Uri CONTENT_URI_SHOW = Uri.parse("content://" + AUTHORITY + "/" + RESOURCE_SHOW + "/");
    public static final Uri CONTENT_URI_SEASON = Uri.parse("content://" + AUTHORITY + "/" + RESOURCE_SEASON+ "/");
    public static final Uri CONTENT_URI_EPISODE = Uri.parse("content://" + AUTHORITY + "/" + RESOURCE_EPISODE+ "/");
    public DatabaseContentProvider() {
    }
    static{
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, RESOURCE_SHOW+ "/", SHOW_ID);
        sUriMatcher.addURI(AUTHORITY, RESOURCE_SEASON+ "/", SEASON_ID);
        sUriMatcher.addURI(AUTHORITY, RESOURCE_EPISODE+ "/", EPISODE_ID);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri result;
        int uriType = sUriMatcher.match(uri);
        long id = 0;
        switch (uriType) {
            case SHOW_ID:
                try {
                    id = database.insertOrThrow(ShowTable.TABLE_NAME, null, values);
                }catch(Exception e){
                    e.printStackTrace();
                }
                result =  Uri.parse(RESOURCE_SHOW + "/" + id);
                break;
            case SEASON_ID:
                id = database.insert(SeasonTable.TABLE_NAME, null, values);
                result = Uri.parse(RESOURCE_SEASON + "/" + id);
                break;
            case EPISODE_ID:
                try{
                    id = database.insertOrThrow(EpisodeTable.TABLE_NAME, null, values);
                    result = Uri.parse(RESOURCE_EPISODE + "/" + id);
                }catch (Exception e){
                    e.printStackTrace();
                    result = Uri.parse(RESOURCE_EPISODE + "/" + -1);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();
        return database != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Cursor cursor;
        int uriType = sUriMatcher.match(uri);
        long id = 0;
        switch (uriType) {
            case SHOW_ID:
                cursor = database.query(ShowTable.TABLE_NAME,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case SEASON_ID:
                cursor = database.query(SeasonTable.TABLE_NAME,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case EPISODE_ID:
                cursor = database.query(EpisodeTable.TABLE_NAME,null,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
