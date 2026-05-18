package com.wrlowson.filmtracker;

import java.util.ArrayList;
import java.util.List;

public class FilmLibrary {

    private List<Film> ownedFilms;

    public FilmLibrary() {
        ownedFilms = new ArrayList<>();
    }

    public boolean addFilm(Film film) {

        for (Film f : ownedFilms) {
            if (f.getTitle().equalsIgnoreCase(film.getTitle())) {
                return false;
            }
        }
        ownedFilms.add(film);
        return true;
    }

    public void showFilms() {

        if (ownedFilms.isEmpty()) {
            System.out.println("No films in Library.");
            return;
        }

        System.out.println("\nOwned Films:");

        for (Film film : ownedFilms) {
            System.out.println(film);
        }

    }
}
