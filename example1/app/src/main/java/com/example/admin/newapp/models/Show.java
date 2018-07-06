package com.example.admin.newapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Show implements Parcelable {

    private String mTitle;
    private String mDescription;
    private int mLogo;
    private List<Season> seasonList = new ArrayList<>();
    // getter & setter

    public Show() {

    }

    public Show(String title, String description, int imageId) {
        //THIS es
        this.mTitle = title;
        this.mDescription = description;
        this.mLogo = imageId;
    }


    protected Show(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mLogo = in.readInt();
        seasonList = in.createTypedArrayList(Season.CREATOR);
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

    public int getImage() {
        return mLogo;

    }

    public String getmTitle() {
        return mTitle;

    }

    public String getmDescription() {
        return mDescription;

    }

    public List<Season> getmSeasonList() {
        return seasonList;

    }

    public void setmSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeInt(mLogo);
        parcel.writeTypedList(seasonList);
    }
}
