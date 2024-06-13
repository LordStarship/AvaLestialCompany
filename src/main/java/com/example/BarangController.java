package com.example;

import java.sql.*;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Form.BarangForm;
import com.example.Table.BarangTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button logoutButton;
    @FXML
    private Button barangAdd;
    @FXML
    private TableView<BarangTable> barangTable;
    @FXML 
    private TableColumn<BarangTable, Integer> barangID;
    @FXML 
    private TableColumn<BarangTable, String> barangName;
    @FXML 
    private TableColumn<BarangTable, String> barangEmail;
    @FXML 
    private TableColumn<BarangTable, String> barangGame;
    @FXML 
    private TableColumn<BarangTable, String> barangVariation;
    @FXML 
    private TableColumn<BarangTable, String> barangType;
    @FXML 
    private TableColumn<BarangTable, String> barangAmount;
    @FXML 
    private TableColumn<BarangTable, HBox> barangAction;

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

    public void initialize() {
        barangID.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
        barangName.setCellValueFactory(new PropertyValueFactory<>("name_barang"));
        barangEmail.setCellValueFactory(new PropertyValueFactory<>("email_barang"));
        barangGame.setCellValueFactory(new PropertyValueFactory<>("name_game"));
        barangVariation.setCellValueFactory(new PropertyValueFactory<>("variation_game"));
        barangType.setCellValueFactory(new PropertyValueFactory<>("type_game"));
        barangAmount.setCellValueFactory(new PropertyValueFactory<>("amount_barang"));
        barangAction.setCellValueFactory(new PropertyValueFactory<>("button_box"));
        fetchBarangData();

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

        barangAdd.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/add_barang.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();
                Stage editStage = new Stage();
                editStage.initStyle(StageStyle.UNDECORATED);
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.initOwner(barangAdd.getScene().getWindow());
                Scene scene = new Scene(editRoot);
                scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (((Node) mouseEvent.getTarget()).getScene() != scene) {
                            editStage.close();
                        }
                    }
                });

                BarangForm barangForm = fxmlLoader.getController();
                barangForm.setBarangController(this);

                scene.getStylesheets().add(css);
                editStage.setScene(scene);
                editStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });
    }

    public void refreshTable() {
        barangTable.getItems().clear();
        fetchBarangData();
    }

    public void fetchBarangData() {
        try (Connection connection = DB.getConnection()) {
            String query =
                "SELECT B.id_barang, B.name_barang, B.email_barang, G.name_game, G.variation_game, G.type_game, B.amount_barang " +
                "FROM barang B " +
                "JOIN game G ON B.id_game = G.id_game " + 
                "WHERE B.id_user = ?";
            int userID = UserSession.getInstance().getLoggedInID();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_barang = resultSet.getInt("id_barang");
                        String name_barang = resultSet.getString("name_barang");
                        String email_barang = resultSet.getString("email_barang");
                        String name_game = resultSet.getString("name_game");
                        String variation_game = resultSet.getString("variation_game");
                        String type_game = resultSet.getString("type_game");
                        String amount_barang = resultSet.getString("amount_barang");
    
                        BarangTable barangData = new BarangTable(id_barang, name_barang, email_barang, name_game, variation_game, type_game, amount_barang);
                        barangTable.getItems().add(barangData);
                        barangData.setBarangController(this);
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
