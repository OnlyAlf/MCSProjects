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
        Button mainButton = findViewById(R.id.main_button_episode);
        backActivity(mainButton);
    }


    }




