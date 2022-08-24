package com.example.films_app.model;

import java.util.List;

public class Data {
    List<Film> trending;
    List<Film> hot;
    List<Film> popular;
    List<Film> upcoming;

    public Data(List<Film> trending, List<Film> hot, List<Film> popular, List<Film> upcoming) {
        this.trending = trending;
        this.hot = hot;
        this.popular = popular;
        this.upcoming = upcoming;
    }

    public Data() {
    }

    public List<Film> getTrending() {
        return trending;
    }

    public void setTrending(List<Film> trending) {
        this.trending = trending;
    }

    public List<Film> getHot() {
        return hot;
    }

    public void setHot(List<Film> hot) {
        this.hot = hot;
    }

    public List<Film> getPopular() {
        return popular;
    }

    public void setPopular(List<Film> popular) {
        this.popular = popular;
    }

    public List<Film> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<Film> upcoming) {
        this.upcoming = upcoming;
    }
}
