package com.example.admin.newapp.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.BuildConfig;
import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.R;
import com.example.admin.newapp.models.Episode;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.URL;
import java.util.ArrayList;

import Util.BitmapManager;
import Util.NetworkOperations;

public class JsonExtractor extends AsyncTask<String, Void, Show> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String API_KEY= "278e2d87";
    InterfaceResults interfaceResults;
    Context context;
    private boolean downloadAll;

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
        if (result.contains("Error")) {
            return null;
        }
        Gson gson = new Gson();
        show = gson.fromJson(result, Show.class);
        if (show == null) {
            return null;
        }

        Bitmap bitMap = BitmapManager.getBitmapFromURL(show.getImage());
        String directoryPath = BitmapManager.saveToInternalStorage(bitMap, context, show.getImdbID());
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
            season.setmDirectoryPath(episodeList.get(0).getDirectoryPath());
            season.setmShowImdbId(episodeList.get(0).getImdbID());
            seasonList.add(season);

        }

        show.setmSeasonList(seasonList);

        return show;
    }

    @Override
    protected void onPostExecute(Show show) {
        super.onPostExecute(show);
        interfaceResults.displayInformationShow(show);
        Toast.makeText(context, "Show Downloaded", Toast.LENGTH_SHORT).show();

    }



}
