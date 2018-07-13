package com.example.admin.newapp.Models;

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
    private int seasonId;
    private String episodeImdbId;
    private String showImdbId;
    private int totalEpisodes;
    private String directoryPath;
    private int showId;

    private List<Episode> episodeList = new ArrayList<>();

    private boolean favorite;

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

    public int getShowId() {
        return showId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getmSeasonId() {
        return seasonId;
    }

    public String getmEpisodeImdbId() {
        return episodeImdbId;
    }

    public String getSeason() {
        return Season;
    }

    public int getTotalEpisodes() {
        //this.totalEpisodes = episodes.size();
        return totalEpisodes;
    }

    public String getShowImdbId() {
        return showImdbId;
    }

    public String getmDirectoryPath() {
        return directoryPath;
    }

    //Setters for the variables
    public void setTitle(String title) {
        Title = title;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setFavorite(boolean isFavorite) {
        this.favorite = isFavorite;
    }

    public void setmSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public void setShowImdbId(String showImdbId) {
        this.showImdbId = showImdbId;
    }

    public void setmEpisodeImdbId(String episodeImdbID) {
        this.episodeImdbId = episodeImdbID;
    }

    public void setSeason(String season) {
        Season = season;
    }
    //Set episodes after season object is build to avoid null episodes messing up count of actual episodes in list
    public void setTotalEpisodes() {
        this.totalEpisodes = episodes.size();
    }
    //normal overloaded setter
    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public void setmEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public void setmDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public List<Episode> getmEpisodeList() {
        return episodeList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Title);
        parcel.writeString(Season);
        parcel.writeTypedList(episodes);
        parcel.writeInt(seasonId);
        parcel.writeString(episodeImdbId);
        parcel.writeString(showImdbId);
        parcel.writeInt(totalEpisodes);
        parcel.writeString(directoryPath);
        parcel.writeInt(showId);
        parcel.writeTypedList(episodeList);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }

    //Parcelable overrides

    protected Season(Parcel in) {
        Title = in.readString();
        Season = in.readString();
        episodes = in.createTypedArrayList(Episode.CREATOR);
        seasonId = in.readInt();
        episodeImdbId = in.readString();
        showImdbId = in.readString();
        totalEpisodes = in.readInt();
        directoryPath = in.readString();
        showId = in.readInt();
        episodeList = in.createTypedArrayList(Episode.CREATOR);
        favorite = in.readByte() != 0;
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
}

