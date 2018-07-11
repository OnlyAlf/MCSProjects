package com.example.admin.newapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Episode implements Parcelable {

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Plot")
    @Expose
    private String Plot;
    @SerializedName("Poster")
    @Expose
    String Poster;
    private String directoryPath;

    public Episode() {

    }

    public Episode(String Title, String Plot, String Poster) {
        //THIS es
        this.Title = Title;
        this.Plot = Plot;
        this.Poster = Poster;
    }

    protected Episode(Parcel in) {
        Title = in.readString();
        Plot = in.readString();
        Poster = in.readString();
        directoryPath = in.readString();
    }

    //Getters for all variables
    public String getImage(){
        return Poster;

    }

    public String getmTitle(){
        return Title;

    }

    public String getmDescription(){
        return Plot;

    }

    public String getTitle() {
        return Title;
    }

    public String getPlot() {
        return Plot;
    }

    public String getPoster() {
        return Poster;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    //Setters for all variables
    public void setTitle(String title) {
        Title = title;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
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
        parcel.writeString(directoryPath);
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };


}
