package com.example.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.DataGameController;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Table.GameTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataGameForm {
    @FXML
    private TextField gameGameAdd;
    @FXML
    private TextField gameVariationAdd;
    @FXML
    private TextField gameTypeAdd;
    @FXML
    private Button gameOkAdd;
    @FXML
    private TextField gameGameEdit;
    @FXML
    private TextField gameVariationEdit;
    @FXML
    private TextField gameTypeEdit;
    @FXML
    private Button gameOkEdit;
    @FXML
    private Button gameOkDelete;

    private static DataGameForm instance;
    private DataGameController dataGameController;
    private GameTable gameTable;
    private Connection conn;

    public DataGameForm() {
        instance = this;
        try {
            conn = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataGameForm getInstance() {
        return instance;
    }

    public void setDataGameController(DataGameController dataGameController) {
        this.dataGameController = dataGameController;
    }

    public void setDataGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public void updateFields() {
        gameGameEdit.setText(gameTable.getName_game());
        gameVariationEdit.setText(gameTable.getVariation_game());
        gameTypeEdit.setText(gameTable.getType_game());
    }    

    public void handleOkButtonAction(ActionEvent event) {
    try {
        String name_game = gameGameAdd.getText();
        String variation_game = gameVariationAdd.getText();
        String type_game = gameTypeAdd.getText();

        DataGameForm dataGameForm = DataGameForm.getInstance();
        dataGameForm.insertData(name_game, variation_game, type_game);

        if (dataGameController != null) {
            dataGameController.refreshTable();
        }

        Stage stage = (Stage) gameOkAdd.getScene().getWindow();
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditButtonAction(ActionEvent event) {
        try {
            int id_game = gameTable.getId_game();
            String name_game = gameGameEdit.getText();
            String variation_game = gameVariationEdit.getText();
            String type_game = gameTypeEdit.getText();
    
            DataGameForm dataGameForm = DataGameForm.getInstance();
            dataGameForm.updateData(id_game, name_game, variation_game, type_game);
    
            if (gameTable != null) {
                gameTable.getDataGameController().refreshTable();
            }
    
            Stage stage = (Stage) gameOkEdit.getScene().getWindow();
            stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void insertData(String name_game, String variation_game, String type_game) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT * FROM game WHERE id_user = ? AND name_game = ? AND variation_game = ? AND type_game = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setInt(1, id_user);
            checkStmt.setString(2, name_game);
            checkStmt.setString(3, variation_game);
            checkStmt.setString(4, type_game);

            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                String query2 = "INSERT INTO game (id_user, name_game, variation_game, type_game) VALUES (?, ?, ?, ?)";

                try (PreparedStatement insertStmt = conn.prepareStatement(query2)) {
                    insertStmt.setInt(1, id_user);
                    insertStmt.setString(2, name_game);
                    insertStmt.setString(3, variation_game);
                    insertStmt.setString(4, type_game);
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Data already exists in data_game table. Insert operation skipped.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(int id_game, String name_game, String variation_game, String type_game) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT * FROM game WHERE name_game = ? AND variation_game = ? AND type_game = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setString(1, name_game);
            checkStmt.setString(2, variation_game);
            checkStmt.setString(3, type_game);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Data game already exists. Update operation skipped.");
            } else {   
                String query2 = "UPDATE game SET name_game = ?, variation_game = ?, type_game = ? WHERE id_user = ? AND id_game = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(query2)) {
                    updateStmt.setString(1, name_game);
                    updateStmt.setString(2, variation_game);
                    updateStmt.setString(3, type_game);
                    updateStmt.setInt(4, id_user);
                    updateStmt.setInt(5, id_game);
                    updateStmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }

    public void dataGameDeleteTrue() {
        int id_game = gameTable.getId_game();
        String deleteSql = "DELETE FROM game WHERE id_game = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id_game);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        if (gameTable != null) {
            gameTable.getDataGameController().refreshTable();
        }
    
        Stage stage = (Stage) gameOkDelete.getScene().getWindow();
        stage.close();
    }
    
    public void dataGameDeleteFalse() {
        Stage stage = (Stage) gameOkDelete.getScene().getWindow();
        stage.close();
    }
}
