package com.wrlowson.filmtracker;

import java.util.Scanner;


public class Main {
   public static void main(String[] args) {

      Scanner scanner = new Scanner(System.in);

      System.out.print("Which film would you like to look up? ");
      String fileName = scanner.nextLine();

      FilmApi.searchFilm(fileName); }


}



