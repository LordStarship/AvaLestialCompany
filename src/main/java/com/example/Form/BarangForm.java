package com.example.Form;

import java.sql.*;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Table.BarangTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class BarangForm {
    @FXML
    private TextField barangNameEdit;
    @FXML
    private TextField barangEmailEdit;
    @FXML
    private TextField barangGameEdit;
    @FXML
    private TextField barangVariationEdit;
    @FXML
    private TextField barangTypeEdit;
    @FXML
    private TextField barangAmountEdit;

    private int id_barang;

    public void initData(int id_barang) {
        this.id_barang = id_barang;
        populateFormFromDatabase();
    }

    private void populateFormFromDatabase() {
        String query = "SELECT name_barang, email_barang, name_game, variation_game, type_game, amount_barang " +
                       "FROM barang B " +
                       "JOIN game G ON B.id_game = G.id_game " +
                       "WHERE B.id_barang = ?";

        try (Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id_barang);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name_barang = resultSet.getString("name_barang");
                    String email_barang = resultSet.getString("email_barang");
                    String name_game = resultSet.getString("name_game");
                    String variation_game = resultSet.getString("variation_game");
                    String type_game = resultSet.getString("type_game");
                    String amount_barang = resultSet.getString("amount_barang");

                    barangNameEdit.setText(name_barang);
                    barangEmailEdit.setText(email_barang);
                    barangGameEdit.setText(name_game);
                    barangVariationEdit.setText(variation_game);
                    barangTypeEdit.setText(type_game);
                    barangAmountEdit.setText(amount_barang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // String updatedName = barangNameEdit.getText();
    // String updatedEmail = barangEmailEdit.getText();
    // String updatedGame = barangGameEdit.getText();
    // String updatedVariation = barangVariationEdit.getText();
    // String updatedType = barangTypeEdit.getText();
    // String updatedAmount = barangAmountEdit.getText();

    // String updateQuery = "UPDATE barang SET name_barang = ?, email_barang = ?, name_game = ?, " +
    //              "variation_game = ?, type_game = ?, amount_barang = ? WHERE id_user = ?";
    // try (Connection connection = DB.getConnection();
    //     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
    //     preparedStatement.setString(1, updatedName);
    //     preparedStatement.setString(2, updatedEmail);
    //     preparedStatement.setString(3, updatedGame);
    //     preparedStatement.setString(4, updatedVariation);
    //     preparedStatement.setString(5, updatedType);
    //     preparedStatement.setString(6, updatedAmount);
    //     preparedStatement.setInt(7, UserSession.getInstance().getLoggedInID());

    //     boolean hasNameGame = (updatedGame != null);
    //     boolean hasVariationGame = (updatedVariation != null);
    //     boolean hasTypeGame = (updatedType != null);

    //     if (!hasNameGame) {
    //         System.out.println("Warning: No data found for 'name_game'.");
    //     }
    //     if (!hasVariationGame) {
    //         System.out.println("Warning: No data found for 'name_game'.");
    //     }
    //     if (!hasTypeGame) {
    //         System.out.println("Warning: No data found for 'name_game'.");
    //     }
        
    //     int rowsAffected = preparedStatement.executeUpdate();
    //     if (rowsAffected > 0) {
    //         System.out.println("Database updated successfully!");
    //     } else {
    //         System.out.println("No records updated.");
    //     }
    // } catch (SQLException e) {
    //     e.printStackTrace();
    // }
}

