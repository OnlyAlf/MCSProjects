package com.example.admin.newapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.Models.Episode;

import Util.BitmapManager;

public class EpisodeActivity extends MyAppCompatActivity {
    static Episode episode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.episode_click);
        episode = getIntent().getBundleExtra("BUNDLE").getParcelable("Episode");
        ImageView iv = findViewById(R.id.episode_image);

        if(episode.getDirectoryPath() == null ||episode.getDirectoryPath().isEmpty()){
            iv.setImageResource(R.drawable.place_holder);

        }else{
            BitmapManager.loadImageFromStorage(episode.getDirectoryPath(), episode.getImdbID(), iv);
        }

        TextView tv = findViewById(R.id.episode_number);
        tv.setText(episode.getTitle());
        TextView tv2 = findViewById(R.id.episode_description2);
        tv2.setText(episode.getPlot());
        TextView tv3 = findViewById(R.id.channel_episode);
        tv3.setText(episode.getReleased());
        TextView tv4 = findViewById(R.id.runtime_episode);
        tv4.setText("Runtime "+episode.getRuntime());
        TextView tv5 = findViewById(R.id.rating_episode);
        tv5.setText("Metascore "+episode.getMetascore()+" ImdbRating "+episode.getImdbRating());
        TextView tv6 = findViewById(R.id.extra_info_episode);
        tv6.setText("Director: "+episode.getDirector()+"\nWriters: "+episode.getWriter()+"\nGenre: "+episode.getGenre()+"\nActors: "+episode.getActors());
        TextView tv7 = findViewById(R.id.clock);
        tv7.setText("Episode "+episode.getEpisode());
    }


    }




