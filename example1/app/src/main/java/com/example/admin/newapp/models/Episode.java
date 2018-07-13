package com.example.admin.newapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode implements Parcelable {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Rated")
    @Expose
    private String rated;
    @SerializedName("Released")
    @Expose
    private String released;
    @SerializedName("Season")
    @Expose
    private String season;
    @SerializedName("Episode")
    @Expose
    private String episode;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Poster")
    @Expose
    private String poster;
    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    private boolean favorite;
    private int episodeID;
    private int seasonID;
    private String directoryPath;

    public Episode() {

    }

    //Getters for variables
    public String getTitle() {
        return title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getEpisodeId() {
        return episodeID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getPoster() {
        return poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    //Setters for variables
    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorite(boolean isFavorite) {
        this.favorite = isFavorite;
    }

    public void setSeasonId(int episodeID) {
        this.episodeID = episodeID;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(rated);
        parcel.writeString(released);
        parcel.writeString(season);
        parcel.writeString(episode);
        parcel.writeString(runtime);
        parcel.writeString(genre);
        parcel.writeString(director);
        parcel.writeString(writer);
        parcel.writeString(actors);
        parcel.writeString(plot);
        parcel.writeString(language);
        parcel.writeString(country);
        parcel.writeString(poster);
        parcel.writeString(metascore);
        parcel.writeString(imdbRating);
        parcel.writeString(imdbID);
        parcel.writeString(type);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeInt(episodeID);
        parcel.writeInt(seasonID);
        parcel.writeString(directoryPath);
    }

    protected Episode(Parcel in) {
        title = in.readString();
        year = in.readString();
        rated = in.readString();
        released = in.readString();
        season = in.readString();
        episode = in.readString();
        runtime = in.readString();
        genre = in.readString();
        director = in.readString();
        writer = in.readString();
        actors = in.readString();
        plot = in.readString();
        language = in.readString();
        country = in.readString();
        poster = in.readString();
        metascore = in.readString();
        imdbRating = in.readString();
        imdbID = in.readString();
        type = in.readString();
        favorite = in.readByte() != 0;
        episodeID = in.readInt();
        seasonID = in.readInt();
        directoryPath = in.readString();
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
