package com.example.admin.newapp.Adapters;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.ContentProvider.DatabaseContentProvider;
import com.example.admin.newapp.Interfaces.InterfaceResults;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.Models.Season;
import com.example.admin.newapp.R;
import com.example.admin.newapp.ShowActivity;
import com.example.admin.newapp.Threads.JsonExtractor;
import com.example.admin.newapp.Fragments.ShowFragment;
import com.example.admin.newapp.Models.Show;
import com.example.admin.newapp.Util.DatabaseOperations;

import java.util.List;

import Util.BitmapManager;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{

    List<Show> showList;
    public String search;
    private final Context context;
    private final boolean twoPane;
    Show show;
    InterfaceResults ir;

    public ShowAdapter(InterfaceResults ir,List<Show> showList, Context context, boolean twoPane,String search) {
        this.context = context;
        this.showList = showList;
        this.twoPane = twoPane;
        this.ir = ir;
        this.search = search;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView showItemTitle;
        public TextView showItemDescription;
        public ImageView showItemLogo;
        public Button downloadButton;

        public ViewHolder(View v) {
            super(v);
            showItemTitle = (TextView) itemView.findViewById(R.id.showItemTitleSpecial);
            showItemTitle.setSelected(true);
            showItemDescription = (TextView) itemView.findViewById(R.id.showDescriptionSpecial);
            showItemDescription.setMovementMethod(new ScrollingMovementMethod());
            showItemLogo = (ImageView) itemView.findViewById(R.id.showItemLogoSpecial);
            downloadButton = (Button) itemView.findViewById(R.id.download_show_button);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_special, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        show = showList.get(position);
        viewHolder.showItemTitle.setText(show.getmTitle());
        viewHolder.showItemDescription.setText(show.getmDescription());

        if(show.getmDirectoryPath() == null || show.getmDirectoryPath().isEmpty()){
            viewHolder.showItemLogo.setImageResource(R.drawable.place_holder);
        }else {
            BitmapManager.loadImageFromStorage(show.getmDirectoryPath(), show.getImdbID(), viewHolder.showItemLogo);
        }

        viewHolder.itemView.findViewById(R.id.cardViewSpecial);

        if(showList.get(position).getmSeasonList().size() == 0){

            viewHolder.downloadButton.setText(R.string.download_button_string);

            viewHolder.downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonExtractor jsonExtractor = new JsonExtractor(ir, context,true);
                    jsonExtractor.execute(search);
                    viewHolder.downloadButton.setOnClickListener(null);
                }
            });


        }

        if(showList.get(position).getmSeasonList().size() > 0) {

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    if (twoPane) {

                        // This is when you are in landscape
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Show", showList.get(position));
                        ShowFragment fragment = new ShowFragment();
                        fragment.setArguments(bundle);
                        ((MainMenuActivity) context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.show_container, fragment)
                                .commit();

                    } else {

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Show", showList.get(position));
                        Intent intent = new Intent(context, ShowActivity.class);
                        intent.putExtra("BUNDLE", bundle);
                        context.startActivity(intent);
                    }

                }
            });

        }
    }
        @Override
        public int getItemCount () {

        return showList.size();
    }




    }
