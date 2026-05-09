package com.wrlowson.filmtracker;

import java.util.Scanner;


public class Main {
    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a film title and optionally release year: ");
        String input = scanner.nextLine();
        String filmName;
        Integer year = null;

        String[] parts = input.split(" ");
        String lastPart = parts[parts.length - 1];


        if (lastPart.matches("\\d{4}")) {

            year = Integer.parseInt(lastPart);

            StringBuilder titleBuilder = new StringBuilder();

            for (int i = 0; i < parts.length - 1; i++) {
                titleBuilder.append(parts[i]).append(" ");
            }

            filmName = titleBuilder.toString().trim();
        } else {
            filmName = input;
        }


        FilmApi.searchFilm(filmName, year);

    }

}



