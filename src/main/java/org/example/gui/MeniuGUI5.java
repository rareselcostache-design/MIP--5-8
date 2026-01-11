package org.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.model.Produs;
import org.example.service.MeniuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class MeniuGUI5 extends Application {

    @Override
    public void start(Stage stage) {
        // === Datele existente din proiect ===
        MeniuService meniuService = new MeniuService();

        // === 0.5p ITERAȚIA 6 - BARA DE MENIU ===
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem exportItem = new MenuItem("Export JSON");
        exportItem.setOnAction(e -> handleExportJson(stage, meniuService));

        MenuItem importItem = new MenuItem("Import JSON");
        importItem.setOnAction(e -> handleImportJson(stage, meniuService));

        fileMenu.getItems().addAll(exportItem, importItem);
        menuBar.getMenus().add(fileMenu);

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

        // === FUNCȚIONALITATE REACTIVĂ ===
        listaProduse.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, vechiProdus, produsNou) -> {
                    if (produsNou != null) {
                        tfNume.setText(produsNou.getNume());
                        tfCategorie.setText(produsNou.getCategorie().getNume());
                        tfDetalii.setText(produsNou.getDetaliiSpecifice());
                        tfPret.setText(String.format("%.2f", produsNou.getPret()));

                        tfPret.textProperty().addListener((o, oldVal, newVal) -> {
                            try {
                                double pretNou = Double.parseDouble(newVal);
                                System.out.println("Preț modificat: " + pretNou + " RON");
                            } catch (NumberFormatException e) {
                                // Ignoră input invalid
                            }
                        });
                    }
                });

        // === LAYOUT CU BARA DE MENIU ===
        BorderPane root = new BorderPane();
        root.setTop(menuBar);          // ✅ Bara de meniu sus
        root.setLeft(listaProduse);    // ✅ Lista în stânga
        root.setCenter(formular);      // ✅ Formular în centru

        Scene scene = new Scene(root, 700, 450);
        stage.setTitle("Meniu Restaurant – La Andrei (Iterația 5 & 6)");
        stage.setScene(scene);
        stage.show();
    }

    // === 0.5p ITERAȚIA 6 - EXPORT JSON ===
    private void handleExportJson(Stage stage, MeniuService meniuService) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export JSON");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        fileChooser.setInitialFileName("meniu_export.json");

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(file, meniuService.getProduse());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("✅ Export reușit");
                alert.setContentText("Meniu exportat în:\n" + file.getAbsolutePath());
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("❌ Eroare la export");
                alert.setContentText("Eroare: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // === 0.5p ITERAȚIA 6 - IMPORT JSON ===
    private void handleImportJson(Stage stage, MeniuService meniuService) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import JSON");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Produs> produse = mapper.readValue(
                        file,
                        mapper.getTypeFactory().constructCollectionType(List.class, Produs.class)
                );

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("✅ Import reușit");
                alert.setContentText("S-au găsit " + produse.size() + " produse în fișierul JSON");
                alert.showAndWait();

                // Aici s-ar insera în baza de date (pentru iterația 6 completă)
                // meniuService.adaugaProduse(produse);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("❌ Eroare la import");
                alert.setContentText("Eroare: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}