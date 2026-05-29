package com.wrlowson.filmtracker;

import java.util.List;

public class Film {

    private String title;
    private String year;
    private String genre;
    private String director;
    private List<String> actors;

    public Film(){

    }

    public Film(String title, String year, String genre, String director, List<String> actors) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return """
                Title: %s
                Year: %s
                Genre: %s
                Director: %s
                Actors: %s
                
                """.formatted(title, year, genre, director, actors);
    }

    public String getTitle() {
        return title;
    }



    public String getYear() {
        return year;
    }


}



