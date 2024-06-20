package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Form.PenggunaForm;
import com.example.Table.UserTable;
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
    private Button logoutButton;
    @FXML
    private Button penggunaAdd;
    @FXML
    private TableView<UserTable> penggunaTable;
    @FXML
    private TableColumn<UserTable, Integer> penggunaID;
    @FXML
    private TableColumn<UserTable, String> penggunaName;
    @FXML
    private TableColumn<UserTable, Integer> penggunaRole;
    @FXML
    private TableColumn<UserTable, String> penggunaUsername;
    @FXML
    private TableColumn<UserTable, String> penggunaEmail;
    @FXML
    private TableColumn<UserTable, HBox> penggunaAction;

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

    public void initialize() {
        penggunaID.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        penggunaName.setCellValueFactory(new PropertyValueFactory<>("name_user"));
        penggunaRole.setCellValueFactory(new PropertyValueFactory<>("role_user"));
        penggunaUsername.setCellValueFactory(new PropertyValueFactory<>("username_user"));
        penggunaEmail.setCellValueFactory(new PropertyValueFactory<>("email_user"));
        penggunaAction.setCellValueFactory(new PropertyValueFactory<>("button_box"));
        fetchPenggunaTable();

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

        penggunaAdd.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/add_pengguna.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();

                Stage editStage = new Stage();
                editStage.initModality(Modality.APPLICATION_MODAL);

                editStage.initOwner(penggunaAdd.getScene().getWindow());

                Scene scene = new Scene(editRoot);

                scene.getStylesheets().add(css);

                PenggunaForm penggunaForm = fxmlLoader.getController();
                penggunaForm.setPenggunaController(this);

                editStage.setScene(scene);
                editStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });
    }

    public void refreshTable() {
        penggunaTable.getItems().clear();
        fetchPenggunaTable();
    }

    private void fetchPenggunaTable() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT * FROM pengguna U ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_user = resultSet.getInt("id_user");
                        String name_user = resultSet.getString("name_user");
                        int role_user = resultSet.getInt("role_user");
                        String username_user = resultSet.getString("username_user");
                        String email_user = resultSet.getString("email_user");
        
                        UserTable userData = new UserTable(id_user, name_user, role_user, username_user, email_user);
                        penggunaTable.getItems().add(userData);
                        userData.setPenggunaController(this);
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
