package com.example.films_app.model;

import android.graphics.Movie;

import java.util.List;

public class Section {
    private String title;
    private List<Film> listFilm;

    public Section(String title, List<Film> listFilm) {
        this.title = title;
        this.listFilm = listFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Film> getListFilm() {
        return listFilm;
    }

    public void setListFilm(List<Film> listFilm) {
        this.listFilm = listFilm;
    }
}
