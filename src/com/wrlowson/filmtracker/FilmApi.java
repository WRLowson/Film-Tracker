package com.wrlowson.filmtracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class FilmApi {

    private static final String API_KEY = "198316f1";


    public static void searchFilm(String title, Integer year) {

        try {
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

            String urlString = "https://www.omdbapi.com/?t=" + encodedTitle;

            if (year != null) {
                urlString += "&y=" + year;
            }

            urlString += "&apikey=" + API_KEY;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String result = response.toString();

            JsonObject json = JsonParser.parseString(result).getAsJsonObject();

            if ("False".equals(json.get("Response").getAsString())){
                System.out.println("Film not found.");

            }
            else{

                String filmTitle = json.get("Title").getAsString();
                String filmYear = json.get("Year").getAsString();
                String genre = json.get("Genre").getAsString();
                String director = json.get("Director").getAsString();

                String actorSting = json.get("Actors").getAsString();

                List<String> actors = List.of(actorSting.split(", "));

                Film film = new Film(filmTitle, filmYear, genre, director, actors);

                System.out.println(film);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}


