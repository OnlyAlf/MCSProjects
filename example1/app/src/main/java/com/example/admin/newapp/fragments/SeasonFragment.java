package com.example.admin.newapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.R;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;

public class SeasonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Season season;

    public SeasonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("Season") == true) {

            if(getArguments().getParcelable("Season") == null){

                System.out.println("Did not work");

            }

            season = getArguments().getParcelable("Season");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.season_click, container, false);
        ImageView iv = v.findViewById(R.id.episode_view);
        ImageView iv2 = v.findViewById(R.id.background_episode);
        TextView tv1 = v.findViewById(R.id.header);
        iv.setImageResource(season.getImage());
        iv2.setImageResource(season.getImage());
        return v;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

    }
}
