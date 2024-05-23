package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import com.example.Connections.*;
import com.example.Table.LaporanTable;
import com.example.Table.TransaksiTable;

import java.sql.*;

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
    private TableView<TransaksiTable> transaksiTable;

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

    public void initialize() {
        fetchTransaksiData();
    }

    private void fetchTransaksiData() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT T.id_transaksi, T.date_transaksi, B.name_barang, T.amount_transaksi, T.note_transaksi " +
                "FROM transaksi Table T " +
                "JOIN barang B ON T.id_barang = B.id_barang " +
                "JOIN game G ON B.id_game = G.id_game";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_transaksi = resultSet.getInt("id_transaksi");
                    Date date_transaksi = resultSet.getDate("date_transaksi");
                    String name_barang = resultSet.getString("name_barang");
                    String amount_transaksi = resultSet.getString("amount_transaksi");
                    String note_transaksi = resultSet.getString("note_transaksi");

                    TransaksiTable transaksiData = new TransaksiTable(id_transaksi, date_transaksi, name_barang, amount_transaksi, note_transaksi);
                    transaksiTable.getItems().add(transaksiData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
