package com.example.admin.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.models.Episode;

public class EpisodeActivity extends MyAppCompatActivity {
    static Episode episode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.episode_click);
        episode = getIntent().getBundleExtra("BUNDLE").getParcelable("Episode");
        ImageView iv = findViewById(R.id.episode_image);
        iv.setBackground(getDrawable(episode.getImage()));
        TextView tv = findViewById(R.id.episode_number);
        tv.setText(episode.getmTitle());
        TextView tv2 = findViewById(R.id.episode_description2);
        tv2.setText(episode.getmDescription());
        Button mainButton = findViewById(R.id.main_button_episode);
        backActivity(mainButton);
    }


    }



