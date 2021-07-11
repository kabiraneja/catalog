package io.codzone.moviecatalogservice.models;

public class InfoServiceModel {
    int movieId;
    String name;

    public InfoServiceModel() {}

    public int getMovieId() {
        return this.movieId;
    }

    public String getName() {
        return this.name;
    }

    public void setMovieId(int mId) {
        this.movieId = mId;
    }

    public void setName(String n) {
        this.name = n;
    }
}
