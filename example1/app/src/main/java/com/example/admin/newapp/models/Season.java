package com.example.admin.newapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Season implements Parcelable {


    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Season")
    @Expose
    private String Season;
    @SerializedName("Episodes")
    @Expose
    private List<Episode> episodes = null;
    private String showImdbId;
    private int totalEpisodes;
    private String directoryPath;

    private List<Episode> episodeList = new ArrayList<>();

    public Season() {

    }

    public Season(String title, String Season) {
        //THIS es
        this.Title = title;
        this.Season = Season;
    }

    //Getters for the variables
    public String getTitle() {
        return Title;
    }

    public String getmshowImdbId() {
        return showImdbId;
    }

    public String getSeason() {
        return Season;
    }

    public int getTotalEpisodes() {
        this.totalEpisodes = episodes.size();
        return episodes.size();
    }

    public String getmDirectoryPath() {
        return directoryPath;
    }

    //Setters for the variables
    public void setTitle(String title) {
        Title = title;
    }

    public void setmShowImdbId(String imdbID) {
        this.showImdbId = imdbID;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public void setTotalEpisodes() {
        this.totalEpisodes = episodes.size();
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public void setmEpisodeList(List<Episode> episodeList ){
        this.episodeList = episodeList;
    }

    public void setmDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public List<Episode> getmEpisodeList(){
        return episodeList;
    }
    //Parcelable override methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Title);
        parcel.writeString(Season);
        parcel.writeTypedList(episodes);
        parcel.writeString(showImdbId);
        parcel.writeInt(totalEpisodes);
        parcel.writeString(directoryPath);
        parcel.writeTypedList(episodeList);
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

    protected Season(Parcel in) {
        Title = in.readString();
        Season = in.readString();
        episodes = in.createTypedArrayList(Episode.CREATOR);
        showImdbId = in.readString();
        totalEpisodes = in.readInt();
        directoryPath = in.readString();
        episodeList = in.createTypedArrayList(Episode.CREATOR);
    }

}

