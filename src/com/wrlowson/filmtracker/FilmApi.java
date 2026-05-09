package com.wrlowson.filmtracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


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

            if (result.contains("\"Response\":\"False\"")) {

                System.out.println("Film not Found");

            } else {

                System.out.println(response);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}


