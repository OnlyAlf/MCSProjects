package com.example.admin.newapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.Adapters.EpisodeAdapter;
import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.models.Season;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class SeasonActivity extends MyAppCompatActivity {
    static Season season;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private boolean twoPane;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.season_click);
        if(this.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE){
            twoPane = true;
        }else{
            twoPane = false;
        }

        season = getIntent().getBundleExtra("BUNDLE").getParcelable("Season");
        ImageView iv = findViewById(R.id.episode_view);
        ImageView iv2 = findViewById(R.id.background_episode);
        //iv.setBackground(getDrawable(season.getImage()));
       // iv2.setBackground(getDrawable(season.getImage()));
        TextView tv = findViewById(R.id.season_number);
        tv.setText(season.getTitle());
        Button mainButton = findViewById(R.id.main_button2);
        recyclerView = (RecyclerView) findViewById(R.id.showRecyclerViewSeasonClick);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EpisodeAdapter(season.getmEpisodeList(),SeasonActivity.this,twoPane);
        recyclerView.setAdapter(adapter);
        backActivity(mainButton);
        //activityChange();


    }

//    public void activityChange() {
//        ImageButton seriesButton = findViewById(R.id.episode_frame);
//        seriesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("Episode", mockedEpisode);
//                Intent intent = new Intent(SeasonActivity.this, EpisodeActivity.class);
//                intent.putExtra("BUNDLE", bundle);
//                startActivity(intent);
//            }
//        });
//
//    }



}
