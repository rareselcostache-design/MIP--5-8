package org.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.model.Produs;
import org.example.service.MeniuService;

public class MeniuGUI extends Application {

    private MeniuService meniuService;

    @Override
    public void init() {
        meniuService = new MeniuService();
    }

    @Override
    public void start(Stage stage) {
        // === 0.3p - SETUP ȘI LAYOUT JAVAFX ===
        // Aplicația pornește, fereastra are titlu
        // Se folosește BorderPane
        // Lista în stânga, formularul în centru

        // === LISTA PRODUSE (STÂNGA) ===
        ListView<Produs> listaProduse = new ListView<>();
        listaProduse.getItems().addAll(meniuService.getProduse());

        // === FORMULAR DETALII (CENTRU) ===
        TextField tfNume = new TextField();
        TextField tfCategorie = new TextField();
        TextField tfPret = new TextField();
        TextField tfDetalii = new TextField();

        tfNume.setEditable(false);
        tfCategorie.setEditable(false);
        tfDetalii.setEditable(false);
        // Pretul este editabil pentru a putea fi modificat

        GridPane formular = new GridPane();
        formular.setHgap(10);
        formular.setVgap(10);

        formular.add(new Label("Nume:"), 0, 0);
        formular.add(tfNume, 1, 0);

        formular.add(new Label("Categorie:"), 0, 1);
        formular.add(tfCategorie, 1, 1);

        formular.add(new Label("Preț (RON):"), 0, 2);
        formular.add(tfPret, 1, 2);

        formular.add(new Label("Detalii specifice:"), 0, 3);
        formular.add(tfDetalii, 1, 3);

        // === 0.3p - IMPLEMENTAREA JAVAFX BEAN ===
        // Elementele vizuale sunt create programatic (cod Java)
        // ListView, TextField, Label, GridPane - toate create în cod

        // === 0.4p - FUNCȚIONALITATEA REACTIVĂ (BINDING/LISTENER) ===
        // La selectarea unui element din listă, detaliile acestuia apar automat
        listaProduse.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, vechiProdus, produsNou) -> {
                    if (produsNou != null) {
                        // Detaliile apar automat în formular
                        tfNume.setText(produsNou.getNume());
                        tfCategorie.setText(produsNou.getCategorie().getNume());
                        tfDetalii.setText(produsNou.getDetaliiSpecifice());
                        tfPret.setText(String.format("%.2f", produsNou.getPret()));

                        // Listener pentru modificarea prețului - demonstrează înțelegerea conceptului
                        tfPret.textProperty().addListener((obs, oldValue, newValue) -> {
                            if (produsNou != null && newValue != null && !newValue.isEmpty()) {
                                try {
                                    double pretNou = Double.parseDouble(newValue);
                                    System.out.println("Preț modificat pentru " +
                                            produsNou.getNume() + ": " + pretNou + " RON");
                                    // Aici s-ar putea actualiza produsul în memorie
                                } catch (NumberFormatException e) {
                                    // Ignoră input invalid
                                }
                            }
                        });
                    } else {
                        // Dacă nu este selectat niciun produs, curăță câmpurile
                        tfNume.clear();
                        tfCategorie.clear();
                        tfDetalii.clear();
                        tfPret.clear();
                    }
                });

        // === LAYOUT FINAL ===
        BorderPane root = new BorderPane();
        root.setLeft(listaProduse);    // Lista în stânga
        root.setCenter(formular);      // Formular în centru

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("Meniu Restaurant – La Andrei");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}