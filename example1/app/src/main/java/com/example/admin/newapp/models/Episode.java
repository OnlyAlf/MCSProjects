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

    public Episode() {

    }

    public Episode(String Title, String Plot, String Poster) {
        //THIS es
        this.Title = Title;
        this.Plot = Plot;
        this.Poster = Poster;
    }

    // getter & setter

    public String getImage(){
        return Poster;

    }

    public String getmTitle(){
        return Title;

    }

    public String getmDescription(){
        return Plot;

    }

    protected Episode(Parcel in) {
        Title = in.readString();
        Plot = in.readString();
        Poster = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Plot);
        dest.writeString(Poster);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
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
