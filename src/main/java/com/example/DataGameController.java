package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Table.GameTable;
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
import com.example.Form.DataGameForm;

public class DataGameController {
    @FXML
    private Button dataGameLaporan;
    @FXML
    private Button dataGameTransaksi;
    @FXML
    private Button dataGameBarang;
    @FXML
    private Button dataGameDataGame;
    @FXML
    private Button dataGamePengguna;
    @FXML
    private Button logoutButton;
    @FXML
    private Button dataGameAdd;
    @FXML
    private TableView<GameTable> dataGameTable;
    @FXML
    private TableColumn<GameTable, Integer> dataGameID;
    @FXML
    private TableColumn<GameTable, String> dataGameGame;
    @FXML
    private TableColumn<GameTable, String> dataGameVariation;
    @FXML
    private TableColumn<GameTable, String> dataGameType;
    @FXML
    private TableColumn<GameTable, HBox> dataGameAction;

    @FXML
    private void buttonDataGameLaporan(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) dataGameLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonDataGameTransaksi(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/transaksi.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) dataGameTransaksi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonDataGameBarang(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/barang.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) dataGameBarang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonDataGameDataGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/data_game.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) dataGameDataGame.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonDataGamePengguna(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pengguna.fxml"));
        String css = getClass().getResource("css/application.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = (Stage) dataGamePengguna.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        int id_user = UserSession.getInstance().getLoggedInID();
        if(id_user != 2) {
            dataGamePengguna.setVisible(false);
        }

        dataGameID.setCellValueFactory(new PropertyValueFactory<>("id_game"));
        dataGameGame.setCellValueFactory(new PropertyValueFactory<>("name_game"));
        dataGameVariation.setCellValueFactory(new PropertyValueFactory<>("variation_game"));
        dataGameType.setCellValueFactory(new PropertyValueFactory<>("type_game"));
        dataGameAction.setCellValueFactory(new PropertyValueFactory<>("button_box"));
        fetchDataGameTable();

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

        dataGameAdd.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/add_game.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();

                Stage editStage = new Stage();
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.initOwner(dataGameAdd.getScene().getWindow());

                Scene scene = new Scene(editRoot);
                
                scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (((Node) mouseEvent.getTarget()).getScene() != scene) {
                            editStage.close();
                        }
                    }
                });

                DataGameForm dataGameForm = fxmlLoader.getController();
                dataGameForm.setDataGameController(this);

                scene.getStylesheets().add(css);

                editStage.setScene(scene);
                editStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });
    }

    public void refreshTable() {
        dataGameTable.getItems().clear();
        fetchDataGameTable();
    }

    private void fetchDataGameTable() {
        try (Connection connection = DB.getConnection()) {
            int userID = UserSession.getInstance().getLoggedInID();
            String query =
                "SELECT G.id_game, G.name_game, G.variation_game, G.type_game " +
                "FROM game G " + 
                "WHERE G.id_user = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_game = resultSet.getInt("id_game");
                        String name_game = resultSet.getString("name_game");
                        String variation_game = resultSet.getString("variation_game");
                        String type_game = resultSet.getString("type_game");
        
                        GameTable dataGameData = new GameTable(id_game, name_game, variation_game, type_game);
                        dataGameTable.getItems().add(dataGameData);
                        dataGameData.setDataGameController(this);
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
