package com.example.admin.newapp.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.admin.newapp.ContentProvider.DatabaseContentProvider;
import com.example.admin.newapp.Models.Episode;
import com.example.admin.newapp.Models.Season;
import com.example.admin.newapp.Models.Show;
import com.example.admin.newapp.Tables.EpisodeTable;
import com.example.admin.newapp.Tables.SeasonTable;
import com.example.admin.newapp.Tables.ShowTable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    static ArrayList<Show> showList;
    static ArrayList<Season> seasonList;
    static ArrayList<Episode> episodeList;


    public static void saveShow(Show show, Context context) {
        ContentValues values = new ContentValues();
        values.put(ShowTable.COLUMN_TITLE, show.getmTitle());
        values.put(ShowTable.COLUMN_PLOT, show.getmDescription());
        values.put(ShowTable.COLUMN_IMDBRATING, show.getImdbRating());
        values.put(ShowTable.COLUMN_TOTALSEASONS, show.getmTotalSeasons());
        values.put(ShowTable.COLUMN_FAVORITE, String.valueOf(show.isFavorite()));
        values.put(ShowTable.COLUMN_DIRECTORYPATH, show.getmDirectoryPath());
        values.put(ShowTable.COLUMN_SHOWIMDBID, show.getImdbID());
        values.put(ShowTable.COLUMN_IMDBRATING, show.getImdbRating());
        values.put(ShowTable.COLUMN_TOTALSEASONS, show.getmTotalSeasons());

        // New Consultant
        Uri showURI = context.getContentResolver().insert(DatabaseContentProvider.CONTENT_URI_SHOW, values);
        assert showURI != null;
        String uriPath = showURI.getLastPathSegment();
        show.setShowId(Integer.valueOf(uriPath));

        List<Season> seasonList = show.getmSeasonList();
        for(int i = 0; i < seasonList.size(); i++){
            seasonList.get(i).setShowId(show.getShowId());
            saveSeason(seasonList.get(i),context);
        }

    }

    public static void saveSeason(Season season, Context context) {
        ContentValues values = new ContentValues();
        values.put(SeasonTable.COLUMN_TITLE, season.getTitle());
        values.put(SeasonTable.COLUMN_SHOWID, season.getShowId());
        values.put(SeasonTable.COLUMN_SEASON, season.getSeason());
        values.put(SeasonTable.COLUMN_FAVORITE, String.valueOf(season.isFavorite()));
        values.put(SeasonTable.COLUMN_DIRECTORYPATH, season.getmDirectoryPath());
        values.put(SeasonTable.COLUMN_SHOWIMDBID, season.getShowImdbId());
        values.put(SeasonTable.COLUMN_EPISODEIMDBID, season.getmEpisodeImdbId());
        values.put(SeasonTable.COLUMN_TOTALEPISODES, season.getTotalEpisodes());

        // New Consultant
        Uri seasonURI = context.getContentResolver().insert(DatabaseContentProvider.CONTENT_URI_SEASON, values);
        assert seasonURI != null;
        season.setmSeasonId(Integer.valueOf(seasonURI.getLastPathSegment()));

        for(int i = 0; i < season.getmEpisodeList().size(); i++){
            season.getmEpisodeList().get(i).setSeasonId(season.getmSeasonId());
            saveEpisode(season.getmEpisodeList().get(i),context);
        }
    }

    public static void saveEpisode(Episode episode, Context context) {
        ContentValues values = new ContentValues();
        values.put(EpisodeTable.COLUMN_TITLE, episode.getTitle());
        values.put(EpisodeTable.COLUMN_SEASONID, episode.getEpisodeId());
        values.put(EpisodeTable.COLUMN_SEASON, episode.getSeason());
        values.put(EpisodeTable.COLUMN_FAVORITE, String.valueOf(episode.isFavorite()));
        values.put(EpisodeTable.COLUMN_DIRECTORYPATH, episode.getDirectoryPath());
        values.put(EpisodeTable.COLUMN_YEAR, episode.getYear());
        values.put(EpisodeTable.COLUMN_RATED, episode.getRated());
        values.put(EpisodeTable.COLUMN_RELEASED, episode.getReleased());
        values.put(EpisodeTable.COLUMN_EPISODE, episode.getEpisode());
        values.put(EpisodeTable.COLUMN_RUNTIME, episode.getRuntime());
        values.put(EpisodeTable.COLUMN_GENRE, episode.getGenre());
        values.put(EpisodeTable.COLUMN_DIRECTOR, episode.getDirector());
        values.put(EpisodeTable.COLUMN_WRITER, episode.getWriter());
        values.put(EpisodeTable.COLUMN_ACTORS, episode.getActors());
        values.put(EpisodeTable.COLUMN_PLOT, episode.getPlot());
        values.put(EpisodeTable.COLUMN_LANGUAGE, episode.getLanguage());
        values.put(EpisodeTable.COLUMN_COUNTRY, episode.getCountry());
        values.put(EpisodeTable.COLUMN_METASCORE, episode.getMetascore());
        values.put(EpisodeTable.COLUMN_IMDBRATING, episode.getImdbRating());
        values.put(EpisodeTable.COLUMN_IMDBID, episode.getImdbID());
        values.put(EpisodeTable.COLUMN_TYPE, episode.getType());

        // New Consultant
        Uri episodeURI = context.getContentResolver().insert(DatabaseContentProvider.CONTENT_URI_EPISODE, values);
        assert episodeURI != null;
        episode.setSeasonId(Integer.valueOf(episodeURI.getLastPathSegment()));
    }


    public static ArrayList<Show> getShows(Context context) {

        String[] projection = new String [] {
                null,
        };
        //String selectionClause = null;
        //String[] selectionArgs = { null };
        Cursor cursor = context.getContentResolver().query(
                DatabaseContentProvider.CONTENT_URI_SHOW,
                projection,
                null,
                null,
                null
        );

        if(cursor != null && cursor.getCount() > 0){

            cursor.moveToFirst();
            showList = new ArrayList<>();

            do{
                Show show = new Show();
                show.setShowId(cursor.getInt(cursor.getColumnIndex(ShowTable.COLUMN_ID)));
                show.setTitle(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_TITLE)));
                show.setPlot(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_PLOT)));
                show.setImdbRating(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_IMDBRATING)));
                show.setmTotalSeasons(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_TOTALSEASONS)));
                show.setmDirectoryPath(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_DIRECTORYPATH)));
                show.setFavorite(Boolean.valueOf(cursor.getString((cursor.getColumnIndex(ShowTable.COLUMN_FAVORITE)))));
                show.setImdbID(cursor.getString(cursor.getColumnIndex(ShowTable.COLUMN_SHOWIMDBID)));


                String selectionClauseSeason = SeasonTable.COLUMN_SHOWID +" = ? ";
                String[] selectionArgsSeason = new String[] {String.valueOf(show.getShowId()),};
                Cursor cursorSeason = context.getContentResolver().query(
                        DatabaseContentProvider.CONTENT_URI_SEASON,
                        null,
                        selectionClauseSeason,
                        selectionArgsSeason,
                        null
                );

                if(cursorSeason != null && cursorSeason.getCount()>0) {

                    cursorSeason.moveToFirst();
                    seasonList = new ArrayList<>();
                    do {
                        Season season = new Season();
                        season.setmSeasonId(Integer.valueOf(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_ID))));
                        season.setTitle(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_TITLE)));
                        season.setSeason(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_SEASON)));
                        season.setShowId(Integer.valueOf(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_SHOWID))));
                        season.setShowImdbId(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_SHOWIMDBID)));
                        season.setTotalEpisodes(Integer.valueOf(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_TOTALEPISODES))));
                        season.setmDirectoryPath(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_DIRECTORYPATH)));
                        season.setFavorite(Boolean.valueOf(cursorSeason.getString((cursorSeason.getColumnIndex(SeasonTable.COLUMN_FAVORITE)))));
                        season.setmEpisodeImdbId(cursorSeason.getString(cursorSeason.getColumnIndex(SeasonTable.COLUMN_EPISODEIMDBID)));

                        Uri uriEpisode = DatabaseContentProvider.CONTENT_URI_SEASON;
                        String selectionClauseEpisode = EpisodeTable.COLUMN_SEASONID+" = ?";
                        String[] selectionArgsEpisode = { String.valueOf(season.getmSeasonId())};
                        Cursor cursorEpisode = context.getContentResolver().query(
                                DatabaseContentProvider.CONTENT_URI_EPISODE,
                                null,
                                selectionClauseEpisode,
                                selectionArgsEpisode,
                                null
                        );

                        if(cursorEpisode != null && cursorEpisode.getCount()>0){

                            cursorEpisode.moveToFirst();
                            episodeList = new ArrayList<>();
                            do{

                                Episode episode = new Episode();
                                episode.setSeasonId(Integer.valueOf(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_ID))));
                                episode.setTitle(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_TITLE)));
                                episode.setYear(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_YEAR)));
                                episode.setRated(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_RATED)));
                                episode.setReleased(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_RELEASED)));
                                episode.setSeason(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_SEASON)));
                                episode.setEpisode(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_EPISODE)));
                                episode.setRuntime(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_RUNTIME)));
                                episode.setGenre(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_GENRE)));
                                episode.setDirector(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_DIRECTOR)));
                                episode.setWriter(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_WRITER)));
                                episode.setActors(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_ACTORS)));
                                episode.setPlot(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_PLOT)));
                                episode.setLanguage(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_LANGUAGE)));
                                episode.setCountry(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_COUNTRY)));
                                episode.setMetascore(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_METASCORE)));
                                episode.setImdbRating(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_IMDBRATING)));
                                episode.setImdbID(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_IMDBID)));
                                episode.setType(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_TYPE)));
                                episode.setDirectoryPath(cursorEpisode.getString(cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_DIRECTORYPATH)));
                                episode.setFavorite(Boolean.valueOf(cursorEpisode.getString((cursorEpisode.getColumnIndex(EpisodeTable.COLUMN_FAVORITE)))));
                                episodeList.add(episode);
                            }while(cursorEpisode.moveToNext());
                            seasonList.add(season);
                            season.setEpisodeList(episodeList);
                        }

                    } while (cursorSeason.moveToNext());
                }

            showList.add(show);
            show.setmSeasonList(seasonList);
            }while(cursor.moveToNext());

        }
        return showList;
    }

}
