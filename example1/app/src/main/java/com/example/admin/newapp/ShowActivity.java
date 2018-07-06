package com.example.admin.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.Adapters.SeasonAdapter;
import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.models.Episode;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.example.admin.newapp.util.MockFactory;

import java.util.List;

public class ShowActivity extends MyAppCompatActivity {

    static Show mockedShow = MockFactory.getMockedShow();
    private RecyclerView.Adapter adapter;
    private boolean twoPane;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.show_click);
        if(findViewById(R.id.show_container) != null){
            twoPane = true;
        }else{
            twoPane = false;
        }



        Show show = getIntent().getBundleExtra("BUNDLE").getParcelable("Show");

        ImageView iv = findViewById(R.id.imageView);
        ImageView iv2 = findViewById(R.id.background_series);
        iv2.setBackground(getDrawable(show.getImage()));
        iv.setBackground(getDrawable(show.getImage()));
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



//    public void activityChange() {
//        ImageButton seriesButton = findViewById(R.id.season_view);
//        seriesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("Season", mockedSeason);
//                Intent intent = new Intent(ShowActivity.this, SeasonActivity.class);
//                intent.putExtra("BUNDLE", bundle);
//                startActivity(intent);
//            }
//        });

    }



