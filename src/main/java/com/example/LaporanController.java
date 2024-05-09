package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle.Control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
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
        // laporanID.setCellValueFactory(new PropertyValueFactory<>("id_laporan"));
        // laporanDate.setCellValueFactory(new PropertyValueFactory<>("date_transaksi"));
        // laporanIDAccount.setCellValueFactory(new PropertyValueFactory<>("id_akun"));
        // laporanGame.setCellValueFactory(new PropertyValueFactory<>("name_game"));
        // laporanAmount.setCellValueFactory(new PropertyValueFactory<>("amount_transaksi"));
        
        // fetchLaporanData();
    }
    private void fetchLaporanData() {
        try (Connection connection = DB.getConnection()) {
            String query = "SELECT L.id_laporan, T.date_transaksi, T.id_akun, T.name_game, T.amount_transaksi " +
                           "FROM LaporanTable L " +
                           "JOIN Transaksi T ON L.id_transaksi = T.id_transaksi";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_laporan = resultSet.getInt("id_laporan");
                    String date_transaksi = resultSet.getString("date_transaksi");
                    int id_akun = resultSet.getInt("id_akun");
                    String name_game = resultSet.getString("name_game");
                    String amount_transaksi = resultSet.getString("amount_transaksi");

                    LaporanTable laporanData = new LaporanTable(id_laporan, date_transaksi, id_akun, name_game, amount_transaksi);
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
