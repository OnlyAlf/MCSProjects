package com.example.admin.newapp.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.models.Show;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.URL;

import Util.BitmapManager;

public class JsonExtractor extends AsyncTask<String, Void, Show> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String API_KEY= "278e2d87";
    InterfaceResults interfaceResults;
    Context context;

    public JsonExtractor(InterfaceResults interfaceResults, Context context){

        this.interfaceResults = interfaceResults;
        this.context = context;

    }


    @Override
    protected Show doInBackground(String... strings) {
        // Making a request to url and getting response
        String url = "http://www.omdbapi.com/?apikey="+API_KEY+"&type=series&plot=short&t="+strings[0];
        String result;
        String inputLine;
        Show show = null;

        try {

            URL myUrl = new URL(url);
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();
            InputStreamReader streamReader = new InputStreamReader
                    (connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);

            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
            Gson gson = new Gson();
            show = gson.fromJson(result,Show.class);
            Bitmap bitMap = BitmapManager.getBitmapFromURL(show.getImage());
            String directoryPath = BitmapManager.saveToInternalStorage(bitMap,context, show.getImdbID());
            show.setmDirectoryPath(directoryPath);

        }catch(Exception e){
            e.printStackTrace();
            result = null;
        }

        return show;
    }

    @Override
    protected void onPostExecute(Show show) {
        super.onPostExecute(show);
        interfaceResults.displayInformation(show);
    }



}
