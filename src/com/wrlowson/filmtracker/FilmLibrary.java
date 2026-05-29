package com.wrlowson.filmtracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;



public class FilmLibrary {

    private static final String FILE_NAME = "library.json";
    private List<Film> ownedFilms;

    public void loadLibrary() {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return;
            }

            Gson gson = new Gson();

            Type filmListType = new TypeToken<List<Film>>() {}.getType();

            FileReader reader = new FileReader(file);

            ownedFilms = gson.fromJson(reader, filmListType);

            reader.close();

            if (ownedFilms == null) {
                ownedFilms = new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
    public void saveLibrary() {

        try {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            FileWriter writer = new FileWriter(FILE_NAME);

            gson.toJson(ownedFilms, writer);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
