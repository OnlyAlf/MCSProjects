package com.example.admin.newapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.Threads.JsonExtractor;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.example.admin.newapp.util.MockFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainMenuActivity extends MyAppCompatActivity implements InterfaceResults {

    private RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    private List<Show> showList = new ArrayList<>();
    private boolean twoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mainmenu);
        if(findViewById(R.id.show_container) != null){
            twoPane = true;
        }else{
            twoPane = false;
        }

        JsonExtractor jsonExtractor = new JsonExtractor(this,this);
        jsonExtractor.execute("new");

    }

    @Override
    public void displayInformation(Show show) {

        if (show != null) {
            showList.add(show);
            recyclerView = (RecyclerView) findViewById(R.id.showRecyclerView);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ShowAdapter(showList, MainMenuActivity.this, twoPane);
            recyclerView.setAdapter(adapter);

        }else{

            Toast.makeText(this,"Show was null",Toast.LENGTH_SHORT).show();

        }



    }


}

