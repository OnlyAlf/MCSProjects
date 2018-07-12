package com.example.admin.newapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.R;
import com.example.admin.newapp.SeasonActivity;
import com.example.admin.newapp.models.Season;

import java.util.List;

import Util.BitmapManager;

public class ShowFragmentAdapter extends RecyclerView.Adapter<ShowFragmentAdapter.ViewHolder>{

    List<Season> seasonList;
    private final Context context;

    public ShowFragmentAdapter(List<Season> seasonList, Context context) {
        this.context =context;
        this.seasonList = seasonList;
        }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView seasonItemTitle;
        public TextView seasonItemDescription;
        public ImageView episodeItemLogo;
        public Button downloadButton;

        public ViewHolder(View v) {
            super(v);
            seasonItemTitle = (TextView) itemView.findViewById(R.id.showItemTitle);
            seasonItemDescription = (TextView) itemView.findViewById(R.id.showDescription);
            episodeItemLogo = (ImageView) itemView.findViewById(R.id.showItemLogo);

        }
    }
    @NonNull
    @Override
    public ShowFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_show, viewGroup, false);

        return new ShowFragmentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFragmentAdapter.ViewHolder viewHolder, final int position) {

        Season episode = seasonList.get(position);
        viewHolder.seasonItemTitle.setText(episode.getTitle());
        viewHolder.seasonItemDescription.setText(episode.getSeason());
        if(seasonList.get(position).getmDirectoryPath() == null || seasonList.get(position).getmDirectoryPath().isEmpty()){
            viewHolder.episodeItemLogo.setImageResource(R.drawable.place_holder);
        }else {
            BitmapManager.loadImageFromStorage(seasonList.get(position).getmDirectoryPath(),
                                                seasonList.get(position).getmshowImdbId(),
                                                viewHolder.episodeItemLogo);
        }

        viewHolder.itemView.findViewById(R.id.cardViewItem);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Season", seasonList.get(position));
                    Intent intent = new Intent(context, SeasonActivity.class);
                    intent.putExtra("BUNDLE", bundle);
                    context.startActivity(intent);
                }
        });

    }
    @Override
    public int getItemCount () {
        return seasonList.size();
    }
}
