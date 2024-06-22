package com.example.Form;

import java.sql.*;
import com.example.BarangController;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Table.BarangTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BarangForm {
    @FXML
    private TextField barangNameAdd;
    @FXML
    private TextField barangEmailAdd;
    @FXML
    private TextField barangGameAdd;
    @FXML
    private TextField barangVariationAdd;
    @FXML
    private TextField barangTypeAdd;
    @FXML
    private TextField barangAmountAdd;
    @FXML
    private Button barangOkAdd;
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
    @FXML
    private Button barangOkEdit;
    @FXML
    private Button barangOkDelete;

    private static BarangForm instance;
    private BarangController barangController;
    private BarangTable barangTable;
    private Connection conn;

    public BarangForm() {
        instance = this;
        try {
            conn = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BarangForm getInstance() {
        return instance;
    }

    public void setBarangController(BarangController barangController) {
        this.barangController = barangController;
    }

    public void setBarangTable(BarangTable barangTable) {
        this.barangTable = barangTable;
    }
    
    public void updateFields() {
        barangNameEdit.setText(barangTable.getName_barang());
        barangEmailEdit.setText(barangTable.getEmail_barang());
        barangGameEdit.setText(barangTable.getName_game());
        barangVariationEdit.setText(barangTable.getVariation_game());
        barangTypeEdit.setText(barangTable.getType_game());
        barangAmountEdit.setText(String.valueOf(barangTable.getAmount_barang()));
    }    

    public void handleOkButtonAction(ActionEvent event) {
    try {
        String name_barang = barangNameAdd.getText();
        String email_barang = barangEmailAdd.getText();
        String name_game = barangGameAdd.getText();
        String variation_game = barangVariationAdd.getText();
        String type_game = barangTypeAdd.getText();
        String amount_barang = barangAmountAdd.getText();

        BarangForm barangForm = BarangForm.getInstance();
        barangForm.insertData(name_barang, email_barang, name_game, variation_game, type_game, amount_barang);

        if (barangController != null) {
            barangController.refreshTable();
        }

        Stage stage = (Stage) barangOkAdd.getScene().getWindow();
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditButtonAction(ActionEvent event) {
    try {
        int id_barang = barangTable.getId_barang();
        String name_barang = barangNameEdit.getText();
        String email_barang = barangEmailEdit.getText();
        String name_game = barangGameEdit.getText();
        String variation_game = barangVariationEdit.getText();
        String type_game = barangTypeEdit.getText();
        String amount_barang = barangAmountEdit.getText();

        BarangForm barangForm = BarangForm.getInstance();
        barangForm.updateData(id_barang, name_barang, email_barang, name_game, variation_game, type_game, amount_barang);

        if (barangTable != null) {
            barangTable.getBarangController().refreshTable();
        }

        Stage stage = (Stage) barangOkEdit.getScene().getWindow();
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertData(String name_barang, String email_barang, String name_game, String variation_game, String type_game, String amount_barang) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT id_game FROM game WHERE id_user = ? AND name_game = ? AND variation_game = ? AND type_game = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setInt(1, id_user);
            checkStmt.setString(2, name_game);
            checkStmt.setString(3, variation_game);
            checkStmt.setString(4, type_game);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int id_game = rs.getInt("id_game");

                String checkSql = "SELECT * FROM barang WHERE id_user = ? AND name_barang = ? AND email_barang = ?";
                try (PreparedStatement checkInsertStmt = conn.prepareStatement(checkSql)) {
                    checkInsertStmt.setInt(1, id_user);
                    checkInsertStmt.setString(2, name_barang);
                    checkInsertStmt.setString(3, email_barang);

                    ResultSet checkRs = checkInsertStmt.executeQuery();

                    if (!checkRs.next()) {
                        String query2 = "INSERT INTO barang (id_user, name_barang, email_barang, id_game, amount_barang) VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement insertStmt = conn.prepareStatement(query2)) {
                            insertStmt.setInt(1, id_user);
                            insertStmt.setString(2, name_barang);
                            insertStmt.setString(3, email_barang);
                            insertStmt.setInt(4, id_game);
                            insertStmt.setString(5, amount_barang);
                            insertStmt.executeUpdate();
                        }
                    } else {
                        System.out.println("Data already exists in barang table. Insert operation skipped.");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Game details not found in game table. Insert operation skipped.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(int id_barang, String name_barang, String email_barang, String name_game, String variation_game, String type_game, String amount_barang) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT id_game FROM game WHERE id_user = ? AND name_game = ? AND variation_game = ? AND type_game = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setInt(1, id_user);
            checkStmt.setString(2, name_game);
            checkStmt.setString(3, variation_game);
            checkStmt.setString(4, type_game);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int id_game = rs.getInt("id_game");

                String checkNameSql = "SELECT * FROM barang WHERE id_user = ? AND name_barang = ? AND id_barang != ?";
                try (PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql)) {
                    checkNameStmt.setInt(1, id_user);
                    checkNameStmt.setString(2, name_barang);
                    checkNameStmt.setInt(3, id_barang);
                
                    ResultSet checkNameRs = checkNameStmt.executeQuery();
                
                    if (checkNameRs.next()) {
                        System.out.println("name_barang already exists. Update operation skipped.");
                    } else {
                        String checkEmailSql = "SELECT * FROM barang WHERE id_user = ? AND email_barang = ? AND id_barang != ?";
                        try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailSql)) {
                            checkEmailStmt.setInt(1, id_user);
                            checkEmailStmt.setString(2, email_barang);
                            checkEmailStmt.setInt(3, id_barang);
                        
                            ResultSet checkEmailRs = checkEmailStmt.executeQuery();
                        
                            if (checkEmailRs.next()) {
                                System.out.println("email_barang already exists. Update operation skipped.");
                            } else {
                                String query3 = "UPDATE barang SET name_barang = ?, email_barang = ?, id_game = ?, amount_barang = ? WHERE id_user = ? AND id_barang = ?";
                                try (PreparedStatement updateStmt = conn.prepareStatement(query3)) {
                                    updateStmt.setString(1, name_barang);
                                    updateStmt.setString(2, email_barang);
                                    updateStmt.setInt(3, id_game);
                                    updateStmt.setString(4, amount_barang);
                                    updateStmt.setInt(5, id_user);
                                    updateStmt.setInt(6, id_barang);
                                    updateStmt.executeUpdate();
                                } catch (SQLException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    } 
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Game details not found in game table. Update operation skipped." + rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void barangDeleteTrue() {
        int id_barang = barangTable.getId_barang();
        String deleteSql = "DELETE FROM barang WHERE id_barang = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id_barang);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        if (barangTable != null) {
            barangTable.getBarangController().refreshTable();
        }
    
        Stage stage = (Stage) barangOkDelete.getScene().getWindow();
        stage.close();
    }
    
    public void barangDeleteFalse() {
        Stage stage = (Stage) barangOkDelete.getScene().getWindow();
        stage.close();
    }
}

