package com.example.admin.newapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Show implements Parcelable {

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Plot")
    @Expose
    private String Plot;
    @SerializedName("Poster")
    @Expose
    String Poster;
    @SerializedName("imdbVotes")
    @Expose
    private String imdbRating;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("totalSeasons")
    @Expose
    private String totalSeasons;
    private int showId;
    private String directoryPath;
    private List<Season> seasonList = new ArrayList<>();
    private boolean favorite;

    public Show() {

    }

    public Show(String Title, String Plot, String Poster) {
        //THIS es
        this.Title = Title;
        this.Plot = Plot;
        this.Poster = Poster;
    }


    //Getters for all variables
    public String getImage() {
        return Poster;

    }

    public int getShowId() {
        return showId;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getmTotalSeasons() {
        return totalSeasons;

    }

    public String imdbRating() {
        return imdbRating;

    }

    public String getmTitle() {
        return Title;

    }

    public String getmDirectoryPath() {
        return directoryPath;

    }

    public String getmDescription() {
        return Plot;

    }

    public String getmImdbId() {
        return imdbID;

    }

    public List<Season> getmSeasonList() {
        return seasonList;

    }



    //Setters for all variables
    public String getImdbID() {
        return imdbID;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setFavorite(boolean isFavorite) {
        this.favorite = isFavorite;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setmDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setmSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;

    }

    public String setmTotalSeasons(String totalSeasons) {
        return totalSeasons;

    }

    //Parcelable override methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Title);
        parcel.writeString(Plot);
        parcel.writeString(Poster);
        parcel.writeString(imdbRating);
        parcel.writeString(imdbID);
        parcel.writeString(totalSeasons);
        parcel.writeInt(showId);
        parcel.writeString(directoryPath);
        parcel.writeTypedList(seasonList);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }

    protected Show(Parcel in) {
        Title = in.readString();
        Plot = in.readString();
        Poster = in.readString();
        imdbRating = in.readString();
        imdbID = in.readString();
        totalSeasons = in.readString();
        showId = in.readInt();
        directoryPath = in.readString();
        seasonList = in.createTypedArrayList(Season.CREATOR);
        favorite = in.readByte() != 0;
    }

    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
