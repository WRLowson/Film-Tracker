package com.wrlowson.filmtracker;

import java.util.Scanner;


public class Main {
    static void main(String[] args) {

        FilmLibrary library = new FilmLibrary();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print(
                    "Enter a film title and optionally release year: " +
                            "\ntype 'Quit' to exit: " +
                            "\nType 'show' to show library: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            if (input.equalsIgnoreCase("show")) {
                library.showFilms();

                continue;

            }
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


            Film film = FilmApi.searchFilm(filmName, year);

            if (film != null) {

                System.out.println(film);
                System.out.println("Add to library? (y/n): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("y")) {

                    boolean added = library.addFilm(film);

                    if (added) {
                        System.out.println("Film has been added.");
                    } else {
                        System.out.println("You already own this film.");
                    }


                }
            }
        }

        System.out.println("Goodbye!");

    }
}



