package com.example.admin.newapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.Adapters.SeasonAdapter;
import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.Models.Show;

import Util.BitmapManager;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class ShowActivity extends MyAppCompatActivity {

    private RecyclerView.Adapter adapter;
    private boolean twoPane;
    Show show;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.show_click);
        if(this.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            twoPane = true;
        }else{
            twoPane = false;
        }

//        if(savedInstanceState != null){
//
//            show = savedInstanceState.getParcelable("Show");
//
//        }else
        show = getIntent().getBundleExtra("BUNDLE").getParcelable("Show");

        ImageView iv = findViewById(R.id.imageView);
        ImageView iv2 = findViewById(R.id.background_series);
        if(show.getmDirectoryPath() == null ||show.getmDirectoryPath().isEmpty()){
            iv.setImageResource(R.drawable.place_holder);
            iv2.setImageResource(R.drawable.place_holder);

        }else{
            BitmapManager.loadImageFromStorage(show.getmDirectoryPath(), show.getImdbID(), iv);
            BitmapManager.loadImageFromStorage(show.getmDirectoryPath(), show.getImdbID(), iv2);
        }

        TextView tv = findViewById(R.id.status);
        tv.setText(show.getmTitle());
        TextView tv2 = findViewById(R.id.descr);
        tv2.setText(show.getmDescription());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.showRecyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SeasonAdapter(show.getmSeasonList(),ShowActivity.this,twoPane);
        recyclerView.setAdapter(adapter);
        backActivity(findViewById(R.id.main_button));


    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putParcelable("Show",show);
//        super.onSaveInstanceState(outState);
//    }
    }



