package com.example.admin.newapp.util;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.newapp.R;
import com.example.admin.newapp.models.Season;
import com.example.admin.newapp.models.Show;
import com.example.admin.newapp.models.Episode;

import java.util.ArrayList;
import java.util.List;

public class MockFactory implements Parcelable {

    public static Show getMockedShow() {
        Show show = new Show("Friends","friends description","");
        show.setmSeasonList(MockFactory.getMockedSeasonList());
        return show;
    }

    public static Season getMockedSeason() {
        Season season = new Season("Season 1","First Season of Friends");
        season.setmEpisodeList(MockFactory.getMockedEpisodeList());
        return season;
    }

    public static Episode getMockedEpisode() {
        return new Episode("Season 15","The One with the Stoned Guy", "");
    }

    public static List<Show> getMockedShowList() {

        List<Show> showList = new ArrayList<>();
        Show mockShow = new Show("Friends", "friends description", "");
        mockShow.setmSeasonList(MockFactory.getMockedSeasonList());
        showList.add(mockShow);
        showList.add(mockShow);
        showList.add(mockShow);
        showList.add(mockShow);
        showList.add(mockShow);


        return showList;
    }

    public static List<Season> getMockedSeasonList() {

        List<Season> seasonList = new ArrayList<>();
        Season mockedSeason = new Season("Season 1","First Season of Friends");
        mockedSeason.setmEpisodeList(MockFactory.getMockedEpisodeList());
        seasonList.add(mockedSeason);
        seasonList.add(mockedSeason);
        seasonList.add(mockedSeason);
        seasonList.add(mockedSeason);
        seasonList.add(mockedSeason);

        return seasonList;
    }

    public static List<Episode> getMockedEpisodeList() {

        List<Episode> episodeList = new ArrayList<>();
        Episode mockedEpisode = new Episode("Season 15","The One with the Stoned Guy","");
        episodeList.add(mockedEpisode);
        episodeList.add(mockedEpisode);
        episodeList.add(mockedEpisode);
        episodeList.add(mockedEpisode);
        episodeList.add(mockedEpisode);

        return episodeList;
    }

    protected MockFactory(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MockFactory> CREATOR = new Parcelable.Creator<MockFactory>() {
        @Override
        public MockFactory createFromParcel(Parcel in) {
            return new MockFactory(in);
        }

        @Override
        public MockFactory[] newArray(int size) {
            return new MockFactory[size];
        }
    };
}
