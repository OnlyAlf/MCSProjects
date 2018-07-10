package com.example.admin.newapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.R;
import com.example.admin.newapp.models.Episode;

public class EpisodeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Episode episode;

    public EpisodeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("Episode") == true) {

            if(getArguments().getParcelable("Episode") == null){

                System.out.println("Did not work");

            }

            episode = getArguments().getParcelable("Episode");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.episode_click, container, false);
        ImageView iv = v.findViewById(R.id.episode_image);
        TextView tv1 = v.findViewById(R.id.header);
       // iv.setImageResource(episode.getImage());
        tv1.setText(episode.getmTitle());
        return v;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

    }
}
