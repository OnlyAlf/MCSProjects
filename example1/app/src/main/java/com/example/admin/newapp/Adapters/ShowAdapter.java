package com.example.admin.newapp.Adapters;



import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.R;
import com.example.admin.newapp.SeasonActivity;
import com.example.admin.newapp.ShowActivity;
import com.example.admin.newapp.fragments.ShowFragment;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.example.admin.newapp.util.MockFactory;

import org.w3c.dom.Text;

import java.util.List;

import Util.BitmapManager;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{

    List<Show> showList;
    List<Season> seasonList;
    private final Context context;
    private final boolean twoPane;
    Show mockedShow;

    public ShowAdapter(List<Show> showList, Context context, boolean twoPane) {
        this.context = context;
        this.showList = showList;
        this.twoPane = twoPane;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView showItemTitle;
        public TextView showItemDescription;
        public ImageView showItemLogo;

        public ViewHolder(View v) {
            super(v);
            showItemTitle = (TextView) itemView.findViewById(R.id.showItemTitle);
            showItemTitle.setSelected(true);
            showItemDescription = (TextView) itemView.findViewById(R.id.showDescription);
            showItemDescription.setMovementMethod(new ScrollingMovementMethod());
            showItemLogo = (ImageView) itemView.findViewById(R.id.showItemLogo);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_show, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        Show show = showList.get(position);
        viewHolder.showItemTitle.setText(show.getmTitle());
        viewHolder.showItemDescription.setText(show.getmDescription());

        if(show.getmDirectoryPath() == null || show.getmDirectoryPath().isEmpty()){
            viewHolder.showItemLogo.setImageResource(R.drawable.place_holder);
        }else {
            BitmapManager.loadImageFromStorage(show.getmDirectoryPath(), show.getImdbID(), viewHolder.showItemLogo);
        }

        viewHolder.itemView.findViewById(R.id.cardViewItem);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (twoPane) {

                    // This is when you are in landscape
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Show",showList.get(position));
                    ShowFragment fragment = new ShowFragment();
                    fragment.setArguments(bundle);
                    ((MainMenuActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.show_container, fragment)
                            .commit();

                }else{

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Show", showList.get(position));
                    Intent intent = new Intent(context, ShowActivity.class);
                    intent.putExtra("BUNDLE", bundle);
                    context.startActivity(intent);
                }

            }
        });

    }
        @Override
        public int getItemCount () {

        return showList.size();
    }




    }
