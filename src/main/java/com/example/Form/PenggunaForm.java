package com.example.Form;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.PenggunaController;
import com.example.Connections.DB;
import com.example.Table.UserTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PenggunaForm {
    @FXML
    private TextField penggunaNameAdd;
    @FXML
    private TextField penggunaRoleAdd;
    @FXML
    private TextField penggunaUsernameAdd;
    @FXML
    private TextField penggunaEmailAdd;
    @FXML
    private Button penggunaOkAdd;
    @FXML
    private TextField penggunaNameEdit;
    @FXML
    private TextField penggunaRoleEdit;
    @FXML
    private TextField penggunaUsernameEdit;
    @FXML
    private TextField penggunaEmailEdit;
    @FXML
    private Button penggunaOkEdit;
    @FXML
    private Button penggunaOkDelete;

    private static PenggunaForm instance;
    private PenggunaController penggunaController;
    private UserTable penggunaTable;
    private Connection conn;

    public PenggunaForm() {
        instance = this;
        try {
            conn = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public static PenggunaForm getInstance() {
        return instance;
    }

    public void setPenggunaController(PenggunaController penggunaController) {
        this.penggunaController = penggunaController;
    }

    public void setPenggunaTable(UserTable penggunaTable) {
        this.penggunaTable = penggunaTable;
    }

    public void updateFields() {
        penggunaNameEdit.setText(penggunaTable.getName_user());
        int role_user = penggunaTable.getRole_user();
        penggunaRoleEdit.setText(Integer.toString(role_user));
        penggunaUsernameEdit.setText(penggunaTable.getUsername_user());
        penggunaEmailEdit.setText(penggunaTable.getEmail_user());
    }   

    public void handleOkButtonAction(ActionEvent event) {
    try {
        String name_pengguna = penggunaNameAdd.getText();
        String temp_role_pengguna = penggunaRoleAdd.getText();
        int role_pengguna = Integer.parseInt(temp_role_pengguna);
        String username_pengguna = penggunaUsernameAdd.getText();
        String email_pengguna = penggunaEmailAdd.getText();

        if (name_pengguna.isEmpty() || temp_role_pengguna.isEmpty() || username_pengguna.isEmpty() || email_pengguna.isEmpty()) {
            System.out.println("One or more fields are empty. Skipping the rest of the method.");
            return;
        }

        PenggunaForm penggunaForm = PenggunaForm.getInstance();
        penggunaForm.insertData(name_pengguna, role_pengguna, username_pengguna, email_pengguna);

        if (penggunaController != null) {
            penggunaController.refreshTable();
        }

        Stage stage = (Stage) penggunaOkAdd.getScene().getWindow();
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditButtonAction(ActionEvent event) {
        try {
            String name_pengguna = penggunaNameEdit.getText();
            String temp_role_pengguna = penggunaRoleEdit.getText();
            int role_pengguna = Integer.parseInt(temp_role_pengguna);
            String username_pengguna = penggunaUsernameEdit.getText();
            String email_pengguna = penggunaEmailEdit.getText();

            if (name_pengguna.isEmpty() || temp_role_pengguna.isEmpty() || username_pengguna.isEmpty() || email_pengguna.isEmpty()) {
                System.out.println("One or more fields are empty. Skipping the rest of the method.");
                return;
            }
    
            PenggunaForm penggunaForm = PenggunaForm.getInstance();
            penggunaForm.updateData(name_pengguna, role_pengguna, username_pengguna, email_pengguna);
    
            if (penggunaTable != null) {
                penggunaTable.getPenggunaController().refreshTable();
            }
    
            Stage stage = (Stage) penggunaOkEdit.getScene().getWindow();
            stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void insertData(String name_pengguna, int role_pengguna, String username_pengguna, String email_pengguna) {
        String query1 = "SELECT * FROM pengguna WHERE name_user = ? OR (username_user = ? OR email_user = ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setString(1, name_pengguna);
            checkStmt.setString(2, username_pengguna);
            checkStmt.setString(3, email_pengguna);

            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                String query2 = "INSERT INTO pengguna (name_user, role_user, username_user, email_user, password_user) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement insertStmt = conn.prepareStatement(query2)) {
                    insertStmt.setString(1, name_pengguna);
                    insertStmt.setInt(2, role_pengguna);
                    insertStmt.setString(3, username_pengguna);
                    insertStmt.setString(4, email_pengguna);
                    try {
                        SecureRandom random = new SecureRandom();
                        String hexString = new BigInteger(64, random).toString(16);
                        System.out.println(hexString);
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] hash = md.digest(hexString.getBytes());
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hash) {
                            sb.append(String.format("%02x", b));
                        } 
                        insertStmt.setString(5, sb.toString());
                        insertStmt.executeUpdate();
                    } catch (NoSuchAlgorithmException e) {
                        System.out.println(e.getMessage());
                    }  
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Data already exists in pengguna table. Insert operation skipped.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(String name_pengguna, int role_pengguna, String username_pengguna, String email_pengguna) {
        String checkNameSql = "SELECT * FROM pengguna WHERE username_user = ?";
        try (PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql)) {
            checkNameStmt.setString(1, username_pengguna);
        
            ResultSet checkNameRs = checkNameStmt.executeQuery();
        
            if (checkNameRs.next()) {
                System.out.println("Username already exists. Update operation skipped.");
            } else {
                String checkEmailSql = "SELECT * FROM pengguna WHERE email_user = ?";
                try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailSql)) {
                    checkEmailStmt.setString(1, email_pengguna);
                
                    ResultSet checkEmailRs = checkEmailStmt.executeQuery();
                
                    if (checkEmailRs.next()) {
                        System.out.println("Email already exists. Update operation skipped.");
                    } else {
                        String query = "UPDATE pengguna SET name_user = ?, role_user = ?, username_user = ?, email_user = ? ";
                        try (PreparedStatement updateStmt = conn.prepareStatement(query)) {
                            updateStmt.setString(1, name_pengguna);
                            updateStmt.setInt(2, role_pengguna);
                            updateStmt.setString(3, username_pengguna);
                            updateStmt.setString(4, email_pengguna);
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
    }

    public void penggunaDeleteTrue() {
        int id_user = penggunaTable.getId_user();
        String deleteSql = "DELETE FROM pengguna WHERE id_user = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id_user);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        if (penggunaTable != null) {
            penggunaTable.getPenggunaController().refreshTable();
        }
    
        Stage stage = (Stage) penggunaOkDelete.getScene().getWindow();
        stage.close();
    }
    
    public void penggunaDeleteFalse() {
        Stage stage = (Stage) penggunaOkDelete.getScene().getWindow();
        stage.close();
    }
}
