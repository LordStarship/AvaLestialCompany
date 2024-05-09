package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BarangController {
    @FXML
    private Button barangLaporan;
    @FXML
    private Button barangTransaksi;
    @FXML
    private Button barangBarang;
    @FXML
    private Button barangDataGame;
    @FXML
    private Button barangPengguna;

    @FXML
    private void buttonBarangLaporan(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) barangLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonBarangTransaksi(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/transaksi.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) barangTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonBarangBarang(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/barang.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) barangBarang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonBarangDataGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/data_game.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) barangDataGame.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonBarangPengguna(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pengguna.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) barangLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
