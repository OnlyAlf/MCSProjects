package com.example.admin.newapp.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.Models.Episode;
import com.example.admin.newapp.Models.Season;
import com.example.admin.newapp.Models.Show;
import com.example.admin.newapp.R;
import com.example.admin.newapp.Util.DatabaseOperations;
import com.google.gson.Gson;

import java.util.ArrayList;

import Util.BitmapManager;
import Util.NetworkOperations;

public class JsonExtractor extends AsyncTask<String, Void, Show> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String API_KEY= "278e2d87";
    String nullError = "Error";
    String networkError = "Network Error, check connection to internet!";
    Bitmap bitMap;
    InterfaceResults interfaceResults;
    Context context;
    private boolean downloadAll;
    String directoryPath;

    public JsonExtractor(InterfaceResults interfaceResults, Context context, boolean downloadAll){

        this.interfaceResults = interfaceResults;
        this.context = context;
        this.downloadAll = downloadAll;

    }


    @Override
    protected Show doInBackground(String... strings) {
        // Making a request to url and getting response
        String url = "http://www.omdbapi.com/?apikey=" + API_KEY + "&type=series&plot=short&t=" + strings[0];
        url = url.replaceAll(" ", "%20");
        Show show;
        String result = NetworkOperations.getJsonFromApi(url);

        if(result.contains("Abort")){
            //Toast.makeText(context, networkError, Toast.LENGTH_SHORT).show();
            return null;
        }
        if (result.contains("Error")) {
            return null;
        }
        Gson gson = new Gson();
        show = gson.fromJson(result, Show.class);
        if (show == null) {
            return null;
        }

        bitMap = BitmapManager.getBitmapFromURL(show.getImage());
        directoryPath = BitmapManager.saveToInternalStorage(bitMap, context, show.getImdbID());
        show.setmDirectoryPath(directoryPath);

        if(!downloadAll){return show;}
        int numSeason = Integer.valueOf(show.getmTotalSeasons());

        ArrayList<Season> seasonList = new ArrayList<>();
        Season season = null;
        String seasonUrl;

        for (int i = 0; i < numSeason; i++) {

            seasonUrl = url + "&season=" + (i + 1);
            result = NetworkOperations.getJsonFromApi(seasonUrl);
            season = gson.fromJson(result, Season.class);
            season.setTotalEpisodes();
            int numEpisodes = season.getTotalEpisodes();
            Episode episode = null;
            String episodeUrl;
            ArrayList<Episode> episodeList = new ArrayList<>();

            for (int j = 0; j < numEpisodes; j++) {

                episodeUrl = seasonUrl + "&episode=" + (j + 1);
                result = NetworkOperations.getJsonFromApi(episodeUrl);
                if (!result.contains("Error")) {
                    episode = gson.fromJson(result, Episode.class);
                    bitMap = BitmapManager.getBitmapFromURL(episode.getPoster());
                    directoryPath = BitmapManager.saveToInternalStorage(bitMap, context, episode.getImdbID());
                    episode.setDirectoryPath(directoryPath);
                    episodeList.add(episode);
                }

            }
            season.setmEpisodeList(episodeList);
            //season.setShowImdbId(show.getImdbID());
            season.setmDirectoryPath(episodeList.get(0).getDirectoryPath());
            season.setmEpisodeImdbId(episodeList.get(0).getImdbID());
            seasonList.add(season);

        }

        show.setmSeasonList(seasonList);

        return show;
    }

    @Override
    protected void onPostExecute(Show show) {
        super.onPostExecute(show);
        interfaceResults.displayInformationShow(show);
        if(downloadAll) {
            DatabaseOperations.saveShow(show, context);
            Toast.makeText(context, "Show Downloaded", Toast.LENGTH_SHORT).show();
        }

    }



}
