package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TransaksiController {
    @FXML
    private Button transaksiLaporan;
    @FXML
    private Button transaksiTransaksi;
    @FXML
    private Button transaksiBarang;
    @FXML
    private Button transaksiDataGame;
    @FXML
    private Button transaksiPengguna;

    @FXML
    private void buttonTransaksiLaporan(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) transaksiLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonTransaksiTransaksi(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/transaksi.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) transaksiTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonTransaksiBarang(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/barang.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) transaksiBarang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonTransaksiDataGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/data_game.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) transaksiDataGame.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonTransaksiPengguna(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pengguna.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) transaksiPengguna.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
