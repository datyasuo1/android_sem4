package com.example.films_app.event;

import com.example.films_app.model.Film;


public class MessageEvent {

    public static class FilmEvent {
        private Film film;

        public FilmEvent(Film film) {
            this.film = film;
        }

        public Film getFilm() {
            return film;
        }

        public void setFilm(Film film) {
            this.film = film;
        }
    }

}
