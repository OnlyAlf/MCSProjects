package com.example.admin.newapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
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
    private ArrayList<Show> showList = new ArrayList<>();
    private boolean twoPane;
    SearchView sv;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mainmenu);
        if(findViewById(R.id.show_container) != null){
            twoPane = true;
        }else{
            twoPane = false;
        }

        if(savedInstanceState != null){

            showList = savedInstanceState.getParcelableArrayList("Show");

        }

            sv = findViewById(R.id.showSearch);
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {

                        JsonExtractor jsonExtractor = new JsonExtractor(MainMenuActivity.this, MainMenuActivity.this);
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

            displayInformationFromAdapter();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("Show",showList);
        super.onSaveInstanceState(outState);
    }

    public void displayInformationFromAdapter(){

        if(showList != null && showList.size() > 0) {
            recyclerView = (RecyclerView) findViewById(R.id.showRecyclerView);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ShowAdapter(showList, MainMenuActivity.this, twoPane);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void displayInformation(Show show) {

        if (show != null) {
            ArrayList<Show> auxShowList= new ArrayList<>();
            auxShowList.add(show);
            showList.add(show);
            displayInformationFromAdapter();

        }else{

            Toast.makeText(this,"Show was null",Toast.LENGTH_SHORT).show();

        }






    }


}

