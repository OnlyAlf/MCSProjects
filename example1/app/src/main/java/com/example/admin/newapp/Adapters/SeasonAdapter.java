package com.example.admin.newapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.EpisodeActivity;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.R;
import com.example.admin.newapp.SeasonActivity;
import com.example.admin.newapp.ShowActivity;
import com.example.admin.newapp.fragments.ShowFragment;
import com.example.admin.newapp.models.Episode;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.example.admin.newapp.util.MockFactory;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ViewHolder>{

    List<Season> seasonList;
    private final Context context;
    private final boolean twoPane;
    Season mockedSeason = MockFactory.getMockedSeason();

    public SeasonAdapter(List<Season> seasonList, Context context, boolean twoPane) {
        this.context =context;
        this.seasonList = seasonList;
        this.twoPane = twoPane;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView seasonItemTitle;
        public TextView seasonItemDescription;
        public ImageView episodeItemLogo;

        public ViewHolder(View v) {
            super(v);
            seasonItemTitle = (TextView) itemView.findViewById(R.id.showItemTitle);
            seasonItemDescription = (TextView) itemView.findViewById(R.id.showDescription);
            episodeItemLogo = (ImageView) itemView.findViewById(R.id.showItemLogo);

        }
    }
    @NonNull
    @Override
    public SeasonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_show, viewGroup, false);

        return new SeasonAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonAdapter.ViewHolder viewHolder, final int position) {

        Season episode = seasonList.get(position);
        viewHolder.seasonItemTitle.setText(episode.getmTitle());
        viewHolder.seasonItemDescription.setText(episode.getmDescription());
        viewHolder.episodeItemLogo.setImageResource(episode.getImage());

        viewHolder.episodeItemLogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (twoPane) {

                    // This is when you are in landscape
                    Bundle bundle = new Bundle();
                    List<Episode> auxEpisodeList = seasonList.get(position).getmEpisodeList();
                    bundle.putParcelable("Season",seasonList.get(position));
                    ShowFragment fragment = new ShowFragment();
                    fragment.setArguments(bundle);
                    ((ShowActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.show_container, fragment)
                            .commit();

                } else {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Season", seasonList.get(position));
                    Intent intent = new Intent(context, SeasonActivity.class);
                    intent.putExtra("BUNDLE", bundle);
                    context.startActivity(intent);
                }
            }
        });

    }
    @Override
    public int getItemCount () {
        return seasonList.size();
    }
}
