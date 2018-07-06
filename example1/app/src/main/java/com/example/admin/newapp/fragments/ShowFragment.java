package com.example.admin.newapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.Adapters.SeasonAdapter;
import com.example.admin.newapp.Adapters.ShowAdapter;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.R;
import com.example.admin.newapp.SeasonActivity;
import com.example.admin.newapp.ShowActivity;
import com.example.admin.newapp.models.Show;

public class ShowFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Show show;

    public ShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("Show") == true) {

            if(getArguments().getParcelable("Show") == null){

                System.out.println("Did not work");

            }

            show = getArguments().getParcelable("Show");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.show_click, container, false);
        ImageView iv = v.findViewById(R.id.imageView);
        ImageView iv2 = v.findViewById(R.id.background_series);
        TextView tv1 = v.findViewById(R.id.descr);
        TextView tv2 = v.findViewById(R.id.status);
        iv.setImageResource(show.getImage());
        tv1.setText(show.getmDescription());
        tv2.setText(show.getmTitle());
        iv2.setImageResource(show.getImage());
        iv.setImageResource(show.getImage());
        return v;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

    }
}
