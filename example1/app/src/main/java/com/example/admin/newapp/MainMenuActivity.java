package com.example.admin.newapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.Threads.JsonExtractor;
import com.example.admin.newapp.Models.Show;
import com.example.admin.newapp.Util.DatabaseOperations;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainMenuActivity extends MyAppCompatActivity implements InterfaceResults {

    private RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    private ArrayList<Show> showList = new ArrayList<>();
    private boolean twoPane;
    private boolean downloadAll = false;
    SearchView sv;
    String search;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        super.setContentView(R.layout.activity_mainmenu);
        if(findViewById(R.id.show_container) != null){
            twoPane = true;
        }else{
            twoPane = false;
        }

            sv = findViewById(R.id.showSearch);
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {

                        JsonExtractor jsonExtractor = new JsonExtractor(MainMenuActivity.this, MainMenuActivity.this,downloadAll);
                        search = s;
                        jsonExtractor.execute(s);
                        sv.clearFocus();
                        sv.setQuery("", false);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

            showList = DatabaseOperations.getShows(MainMenuActivity.this);
            displayInformationFromAdapter();

    }

    public void displayInformationFromAdapter(){

        if(showList != null && showList.size() > 0) {
            recyclerView = (RecyclerView) findViewById(R.id.showRecyclerView);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ShowAdapter(MainMenuActivity.this,showList, MainMenuActivity.this, twoPane,search);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void displayInformationShow(Show show) {

        if (show != null) {
            ArrayList<Show> auxShowList = new ArrayList<>();
            auxShowList.add(show);
            showList = auxShowList;
            displayInformationFromAdapter();

        } else {

            Toast.makeText(this, "Show does not exist or no Internet Connection!", Toast.LENGTH_SHORT).show();

        }


    }



}

