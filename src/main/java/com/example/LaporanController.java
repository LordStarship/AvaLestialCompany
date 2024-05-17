package com.example;

import java.sql.*;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.Table.LaporanTable;
import com.example.Connections.*;

public class LaporanController {
    @FXML
    private HBox laporanPane;
    @FXML
    private Button laporanLaporan;
    @FXML
    private Button laporanTransaksi;
    @FXML
    private Button laporanBarang;
    @FXML
    private Button laporanDataGame;
    @FXML
    private Button laporanPengguna;
    @FXML
    private TableView<LaporanTable> laporanTable;
    @FXML
    private TableColumn<LaporanTable, Integer> laporanID;
    @FXML
    private TableColumn<LaporanTable, String> laporanDate;
    @FXML
    private TableColumn<LaporanTable, Integer> laporanIDAccount;
    @FXML
    private TableColumn<LaporanTable, String> laporanGame;
    @FXML
    private TableColumn<LaporanTable, String> laporanAmount;

    @FXML
    private void buttonLaporanLaporan(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) laporanLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonLaporanTransaksi(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/transaksi.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) laporanTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonLaporanBarang(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/barang.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) laporanTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonLaporanDataGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/data_game.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) laporanTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonLaporanPengguna(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pengguna.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) laporanTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        fetchLaporanData();
    }

    private void fetchLaporanData() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT L.id_laporan, T.id_transaksi, B.id_barang, G.id_game, T.amount_transaksi" +
                "FROM LaporanTable L " +
                "JOIN TransaksiTable T ON L.id_transaksi = T.id_transaksi " +
                "JOIN BarangTable B ON T.id_barang = B.id_barang " +
                "JOIN GameTable G ON B.id_game = G.id_game";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_laporan = resultSet.getInt("id_laporan");
                    Date date_transaksi = resultSet.getDate("date_transaksi");
                    int id_barang = resultSet.getInt("id_barang");
                    String name_game = resultSet.getString("name_game");
                    String amount_transaksi = resultSet.getString("amount_transaksi");
                    
                    LaporanTable laporanData = new LaporanTable(id_laporan, date_transaksi, id_barang, name_game, amount_transaksi);
                    laporanTable.getItems().add(laporanData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
