package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PenggunaController {
    @FXML
    private Button penggunaLaporan;
    @FXML
    private Button penggunaTransaksi;
    @FXML
    private Button penggunaBarang;
    @FXML
    private Button penggunaDataGame;
    @FXML
    private Button penggunaPengguna;

    @FXML
    private void buttonPenggunaLaporan(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) penggunaLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonPenggunaTransaksi(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/transaksi.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) penggunaTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonPenggunaBarang(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/barang.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) penggunaBarang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonPenggunaDataGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/data_game.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) penggunaDataGame.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonPenggunaPengguna(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pengguna.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) penggunaPengguna.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
