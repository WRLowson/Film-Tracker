package com.wrlowson.filmtracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilmApp extends Application {

    private FilmLibrary library = new FilmLibrary();

    private TableView<Film> table = new TableView<>();
    private ObservableList<Film> tableData = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        // Load saved library
        library.loadLibrary();
        tableData.addAll(library.getFilms());

        // UI Components
        TextField searchField = new TextField();
        searchField.setPromptText("Enter film title");

        TextField yearField = new TextField();
        yearField.setPromptText("Year (optional)");

        Button searchButton = new Button("Search");
        Button addButton = new Button("Add to Library");
        Button refreshButton = new Button("Show Library");

        Label resultLabel = new Label();

        // Table setup
        TableColumn<Film, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitle()));

        TableColumn<Film, String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getYear()));

        table.getColumns().addAll(titleCol, yearCol);
        table.setItems(tableData);

        Film[] lastSearchResult = new Film[1];

        // SEARCH BUTTON
        searchButton.setOnAction(e -> {

            String title = searchField.getText();
            String yearText = yearField.getText();

            Integer year = null;
            if (!yearText.isEmpty()) {
                year = Integer.parseInt(yearText);
            }

            Film film = FilmApi.searchFilm(title, year);
            lastSearchResult[0] = film;

            if (film != null) {
                resultLabel.setText("Found: " + film.getTitle());
            } else {
                resultLabel.setText("Film not found.");
            }
        });

        // ADD BUTTON
        addButton.setOnAction(e -> {

            Film film = lastSearchResult[0];

            if (film != null) {

                boolean added = library.addFilm(film);

                if (added) {
                    library.saveLibrary();
                    tableData.setAll(library.getFilms());
                    resultLabel.setText("Added to library.");
                } else {
                    resultLabel.setText("Already owned.");
                }
            }
        });

        // REFRESH BUTTON
        refreshButton.setOnAction(e -> {
            tableData.setAll(library.getFilms());
        });

        VBox root = new VBox(10,
                searchField,
                yearField,
                searchButton,
                addButton,
                refreshButton,
                resultLabel,
                table
        );

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Film Tracker");
        stage.show();

        // Save on close
        stage.setOnCloseRequest(e -> library.saveLibrary());
    }

    public static void main(String[] args) {
        launch(args);
    }
}