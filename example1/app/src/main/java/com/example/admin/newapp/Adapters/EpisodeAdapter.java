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
import com.example.admin.newapp.ShowActivity;
import com.example.admin.newapp.fragments.ShowFragment;
import com.example.admin.newapp.models.Episode;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder>{

    List<Episode> episodeList;
    private final Context context;
    private final boolean twoPane;
    Season mockedSeason;

    public EpisodeAdapter(List<Episode> episodeList, Context context, boolean twoPane) {
        this.context = context;
        this.episodeList = episodeList;
        this.twoPane = twoPane;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView episodeItemTitle;
        public TextView episodeItemDescription;
        public ImageView episodeItemLogo;

        public ViewHolder(View v) {
            super(v);
            episodeItemTitle = (TextView) itemView.findViewById(R.id.showItemTitle);
            episodeItemDescription = (TextView) itemView.findViewById(R.id.showDescription);
            episodeItemLogo = (ImageView) itemView.findViewById(R.id.showItemLogo);

        }
    }
    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_show, viewGroup, false);

        return new EpisodeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder viewHolder, final int position) {

        Episode episode = episodeList.get(position);
        viewHolder.episodeItemTitle.setText(episode.getmTitle());
        viewHolder.episodeItemDescription.setText(episode.getmDescription());
        viewHolder.episodeItemLogo.setImageResource(episode.getImage());

        viewHolder.episodeItemLogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (twoPane) {

                    // This is when you are in landscape
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Episode",episodeList.get(position));
                    ShowFragment fragment = new ShowFragment();
                    fragment.setArguments(bundle);
                    ((MainMenuActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.show_container, fragment)
                            .commit();

                } else {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Episode", episodeList.get(position));
                    Intent intent = new Intent(context, EpisodeActivity.class);
                    intent.putExtra("BUNDLE", bundle);
                    context.startActivity(intent);
                }

            }
        });

    }
    @Override
    public int getItemCount () {
        return episodeList.size();
    }


}
