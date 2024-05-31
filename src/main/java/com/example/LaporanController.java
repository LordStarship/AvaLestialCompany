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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
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
    private Button logoutButton;
    @FXML
    private TableView<LaporanTable> laporanTable;
    @FXML
    private TableColumn<LaporanTable, Integer> laporanID;
    @FXML
    private TableColumn<LaporanTable, Date> laporanDate;
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
        laporanID.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
        laporanDate.setCellValueFactory(new PropertyValueFactory<>("date_transaksi"));
        laporanIDAccount.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
        laporanGame.setCellValueFactory(new PropertyValueFactory<>("name_game"));
        laporanAmount.setCellValueFactory(new PropertyValueFactory<>("amount_transaksi"));
        fetchLaporanData();

        logoutButton.setOnAction(event -> {
            UserSession.getInstance().clearSession();
            try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            String css = getClass().getResource("css/application.css").toExternalForm();
            Font montserratNormal = Font.loadFont(getClass().getResource("fonts/Montserrat-VariableFont_wght.ttf").toExternalForm(), 24);
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            newStage.setFullScreen(true);
            newStage.setScene(scene);
            Stage oldStage = (Stage) logoutButton.getScene().getWindow();
            oldStage.close();
            newStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void fetchLaporanData() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT T.id_transaksi, T.date_transaksi, B.id_barang, G.name_game, T.amount_transaksi " +
                "FROM Transaksi T " +
                "JOIN barang B ON T.id_barang = B.id_barang " +
                "JOIN game G ON B.id_game = G.id_game " +
                "WHERE T.id_user = ?";
            int userID = UserSession.getInstance().getLoggedInID();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_transaksi = resultSet.getInt("id_transaksi");
                        Date date_transaksi = resultSet.getDate("date_transaksi");
                        int id_barang = resultSet.getInt("id_barang");
                        String name_game = resultSet.getString("name_game");
                        String amount_transaksi = resultSet.getString("amount_transaksi");
                        
                        LaporanTable laporanData = new LaporanTable(id_transaksi, date_transaksi, id_barang, name_game, amount_transaksi);
                        laporanTable.getItems().add(laporanData);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
