package com.example;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.example.Connections.*;
import com.example.Form.TransaksiForm;
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
    private Button logoutButton;
    @FXML
    private Button transaksiAdd;
    @FXML
    private TableView<TransaksiTable> transaksiTable;
    @FXML
    private TableColumn<TransaksiTable, Integer> transaksiID;
    @FXML
    private TableColumn<TransaksiTable, Date> transaksiDate;
    @FXML
    private TableColumn<TransaksiTable, String> transaksiName;
    @FXML
    private TableColumn<TransaksiTable, String> transaksiAmount;
    @FXML
    private TableColumn<TransaksiTable, String> transaksiNote;
    @FXML
    private TableColumn<TransaksiTable, HBox> transaksiAction;

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
        transaksiID.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
        transaksiDate.setCellValueFactory(new PropertyValueFactory<>("date_transaksi"));
        transaksiName.setCellValueFactory(new PropertyValueFactory<>("name_barang"));
        transaksiAmount.setCellValueFactory(new PropertyValueFactory<>("amount_transaksi"));
        transaksiNote.setCellValueFactory(new PropertyValueFactory<>("note_transaksi"));
        transaksiAction.setCellValueFactory(new PropertyValueFactory<>("button_box"));
        fetchTransaksiData();

        logoutButton.setOnAction(event -> {
            UserSession.getInstance().clearSession();
            try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            String css = getClass().getResource("css/application.css").toExternalForm();
            Font.loadFont(getClass().getResource("fonts/Montserrat-VariableFont_wght.ttf").toExternalForm(), 24);
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

        transaksiAdd.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/add_transaksi.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();

                Stage editStage = new Stage();
                editStage.initStyle(StageStyle.UNDECORATED);
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.initOwner(transaksiAdd.getScene().getWindow());

                Scene scene = new Scene(editRoot);

                TransaksiForm transaksiForm = fxmlLoader.getController();
                transaksiForm.setTransaksiController(this);

                scene.getStylesheets().add(css);

                editStage.setScene(scene);
                editStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });
    }

    public void refreshTable() {
        transaksiTable.getItems().clear();
        fetchTransaksiData();
    }
    
    private void fetchTransaksiData() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT T.id_transaksi, T.date_transaksi, B.name_barang, T.amount_transaksi, T.note_transaksi " +
                "FROM transaksi T " +
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
                        String name_barang = resultSet.getString("name_barang");
                        String amount_transaksi = resultSet.getString("amount_transaksi");
                        String note_transaksi = resultSet.getString("note_transaksi");
    
                        TransaksiTable transaksiData = new TransaksiTable(id_transaksi, date_transaksi, name_barang, amount_transaksi, note_transaksi);
                        transaksiTable.getItems().add(transaksiData);
                        transaksiData.setTransaksiController(this);
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
