package com.example.admin.newapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Season implements Parcelable {

    private List<Episode> episodeList = new ArrayList<>();
    String mTitle;
    String mDescription;
    int mLogo;

    public Season() {

    }

    public Season(String title, String description, int imageId) {
        //THIS es
        this.mTitle = title;
        this.mDescription = description;
        this.mLogo = imageId;
    }


    protected Season(Parcel in) {
        episodeList = in.createTypedArrayList(Episode.CREATOR);
        mTitle = in.readString();
        mDescription = in.readString();
        mLogo = in.readInt();
    }

    public static final Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };

    public int getImage(){
        return mLogo;

    }

    public String getmTitle(){
        return mTitle;

    }

    public String getmDescription(){
        return mDescription;

    }


    public void setmEpisodeList(List<Episode> episodeList ){

        this.episodeList = episodeList;
    }

    public List<Episode> getmEpisodeList(){
        return episodeList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(episodeList);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeInt(mLogo);
    }
}

