package com.example.admin.newapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Episode implements Parcelable {


    public Episode() {

    }

    public Episode(String title, String description, int imageId) {
        //THIS es
        this.mTitle = title;
        this.mDescription = description;
        this.mLogo = imageId;
    }

    private String mTitle;
    private String mDescription;
    private int mLogo;

    // getter & setter

    public int getImage(){
        return mLogo;

    }

    public String getmTitle(){
        return mTitle;

    }

    public String getmDescription(){
        return mDescription;

    }

    protected Episode(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mLogo = in.readInt();
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeInt(mLogo);
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
