package org.example.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.model.Produs;
import org.example.repository.ProdusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class MeniuGUI6 extends Application {

    private ProdusRepository repository;
    private TableView<Produs> tableView;
    private ObservableList<Produs> produseObservable;

    @Override
    public void start(Stage stage) {
        repository = new ProdusRepository();

        // === 0.5p - BARA DE MENIU ===
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem exportItem = new MenuItem("Export JSON");
        exportItem.setOnAction(e -> handleExportJson(stage));

        MenuItem importItem = new MenuItem("Import JSON");
        importItem.setOnAction(e -> handleImportJson(stage));

        MenuItem refreshItem = new MenuItem("Refresh");
        refreshItem.setOnAction(e -> refreshTableView());

        fileMenu.getItems().addAll(exportItem, importItem, new SeparatorMenuItem(), refreshItem);
        menuBar.getMenus().add(fileMenu);

        // === 0.3p - TABLEVIEW CU DATE DIN POSTGRESQL ===
        tableView = new TableView<>();
        configureTableView();
        refreshTableView();

        // === FORMULAR DETALII ===
        GridPane formular = createFormular();

        // === LAYOUT ===
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(tableView);
        root.setRight(formular);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("🍽️ Meniu Restaurant - Iterația 6 (PostgreSQL + JSON)");
        stage.setScene(scene);
        stage.show();
    }

    private void configureTableView() {
        // Coloane pentru TableView
        TableColumn<Produs, String> numeCol = new TableColumn<>("Nume");
        numeCol.setCellValueFactory(new PropertyValueFactory<>("nume"));

        TableColumn<Produs, Double> pretCol = new TableColumn<>("Preț (RON)");
        pretCol.setCellValueFactory(new PropertyValueFactory<>("pret"));

        TableColumn<Produs, String> categorieCol = new TableColumn<>("Categorie");
        categorieCol.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        cellData.getValue().getCategorie().getNume()));

        TableColumn<Produs, String> detaliiCol = new TableColumn<>("Detalii");
        detaliiCol.setCellValueFactory(new PropertyValueFactory<>("detaliiSpecifice"));

        tableView.getColumns().addAll(numeCol, pretCol, categorieCol, detaliiCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private GridPane createFormular() {
        GridPane formular = new GridPane();
        formular.setHgap(10);
        formular.setVgap(10);
        formular.setPadding(new javafx.geometry.Insets(10));

        Label infoLabel = new Label("Selectați un produs din tabel\npentru a vedea detalii");
        infoLabel.setStyle("-fx-font-weight: bold;");

        formular.add(infoLabel, 0, 0, 2, 1);

        // Listener pentru selecție în tabel
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Detalii produs");
                info.setContentText(newVal.toString());
                info.showAndWait();
            }
        });

        return formular;
    }

    private void refreshTableView() {
        List<Produs> produse = repository.getAllProduse();
        produseObservable = FXCollections.observableArrayList(produse);
        tableView.setItems(produseObservable);
        System.out.println("✓ Tabela actualizată: " + produse.size() + " produse");
    }

    // === 0.5p - EXPORT JSON ===
    private void handleExportJson(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Meniu ca JSON");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        fileChooser.setInitialFileName("meniu_export.json");

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(file, repository.getAllProduse());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("✅ Export reușit");
                alert.setContentText("Exportat " + repository.getAllProduse().size() + " produse în JSON");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("❌ Eroare la export");
                alert.setContentText("Eroare: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // === 0.5p - IMPORT JSON ===
    private void handleImportJson(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Meniu din JSON");
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

                // Salvează fiecare produs în baza de date
                for (Produs produs : produse) {
                    repository.saveProdus(produs);
                }

                // Refresh UI
                refreshTableView();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("✅ Import reușit");
                alert.setContentText("Importat " + produse.size() + " produse în baza de date\nTabela actualizată!");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("❌ Eroare la import");
                alert.setContentText("Eroare: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @Override
    public void stop() {
        if (repository != null) {
            repository.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}