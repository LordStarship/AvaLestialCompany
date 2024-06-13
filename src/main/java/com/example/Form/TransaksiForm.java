package com.example.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import com.example.TransaksiController;
import com.example.Connections.DB;
import com.example.Connections.UserSession;
import com.example.Table.TransaksiTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransaksiForm {
    @FXML
    private DatePicker transaksiDateAdd;
    @FXML
    private TextField transaksiBarangAdd;
    @FXML
    private TextField transaksiAmountAdd;
    @FXML
    private TextField transaksiNoteAdd;
    @FXML
    private Button transaksiOkAdd;
    @FXML
    private DatePicker transaksiDateEdit;
    @FXML
    private TextField transaksiBarangEdit;
    @FXML
    private TextField transaksiAmountEdit;
    @FXML
    private TextField transaksiNoteEdit;
    @FXML
    private Button transaksiOkEdit;
    @FXML
    private Button transaksiOkDelete;

    private static TransaksiForm instance;
    private TransaksiController transaksiController;
    private TransaksiTable transaksiTable;
    private Connection conn;

    public TransaksiForm() {
        instance = this;
        try {
            conn = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TransaksiForm getInstance() {
        return instance;
    }

    public void setTransaksiController(TransaksiController transaksiController) {
        this.transaksiController = transaksiController;
    }

    public void setTransaksiTable(TransaksiTable transaksiTable) {
        this.transaksiTable = transaksiTable;
    }

    public void updateFields() {
        Date sqlDate = transaksiTable.getDate_transaksi();
        LocalDate localDate = sqlDate.toLocalDate();
        transaksiDateEdit.setValue(localDate);
        transaksiBarangEdit.setText(transaksiTable.getName_barang());
        transaksiAmountEdit.setText(transaksiTable.getAmount_transaksi());
        transaksiNoteEdit.setText(transaksiTable.getNote_transaksi());
    } 

    public void handleOkButtonAction(ActionEvent event) {
    try {
        LocalDate temp_date_transaksi = transaksiDateAdd.getValue();
        Date date_transaksi = Date.valueOf(temp_date_transaksi);
        String name_barang = transaksiBarangAdd.getText();
        String amount_transaksi = transaksiAmountAdd.getText();
        String note_transaksi = transaksiNoteAdd.getText();

        TransaksiForm transaksiForm = TransaksiForm.getInstance();
        transaksiForm.insertData(date_transaksi, name_barang, amount_transaksi, note_transaksi);

        if (transaksiController != null) {
            transaksiController.refreshTable();
        }

        Stage stage = (Stage) transaksiOkAdd.getScene().getWindow();
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditButtonAction(ActionEvent event) {
        try {
            int id_transaksi = transaksiTable.getId_transaksi();
            LocalDate temp_date_transaksi = transaksiDateEdit.getValue();
            Date date_transaksi = Date.valueOf(temp_date_transaksi);
            String name_barang = transaksiBarangEdit.getText();
            String amount_transaksi = transaksiAmountEdit.getText();
            String note_transaksi = transaksiNoteEdit.getText();
    
            TransaksiForm transaksiForm = TransaksiForm.getInstance();
            transaksiForm.updateData(id_transaksi, date_transaksi, name_barang, amount_transaksi, note_transaksi);
    
            if (transaksiTable != null) {
                transaksiTable.getTransaksiController().refreshTable();
            }
    
            Stage stage = (Stage) transaksiOkEdit.getScene().getWindow();
            stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void insertData(Date date_transaksi, String name_barang, String amount_transaksi, String note_transaksi) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT id_barang FROM barang WHERE id_user = ? AND name_barang = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setInt(1, id_user);
            checkStmt.setString(2, name_barang);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int id_barang = rs.getInt("id_barang");

                String checkSql = "SELECT * FROM transaksi WHERE id_user = ? AND id_barang = ? AND date_transaksi = ?";
                try (PreparedStatement checkInsertStmt = conn.prepareStatement(checkSql)) {
                    checkInsertStmt.setInt(1, id_user);
                    checkInsertStmt.setInt(2, id_barang);
                    checkInsertStmt.setDate(3, date_transaksi);

                    ResultSet checkRs = checkInsertStmt.executeQuery();

                    if (!checkRs.next()) {
                        String query2 = "INSERT INTO transaksi (id_user, date_transaksi, id_barang, amount_transaksi, note_transaksi) VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement insertStmt = conn.prepareStatement(query2)) {
                            insertStmt.setInt(1, id_user);
                            insertStmt.setDate(2, date_transaksi);
                            insertStmt.setInt(3, id_barang);
                            insertStmt.setString(4, amount_transaksi);
                            insertStmt.setString(5, note_transaksi);
                            insertStmt.executeUpdate();
                        }
                    } else {
                        System.out.println("Data already exists in barang table. Insert operation skipped.");
                    }
                }
            } else {
                System.out.println("Barang details not found in barang table. Insert operation skipped.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(int id_transaksi, Date date_transaksi, String name_barang, String amount_transaksi, String note_transaksi) {
        int id_user = UserSession.getInstance().getLoggedInID();
        String query1 = "SELECT id_barang FROM barang WHERE id_user = ? AND name_barang = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(query1)) {
            checkStmt.setInt(1, id_user);
            checkStmt.setString(2, name_barang);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int id_barang = rs.getInt("id_barang");

                String checkNameSql = "SELECT * FROM transaksi WHERE id_user = ? AND id_barang = ? AND id_transaksi != ?";
                try (PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql)) {
                    checkNameStmt.setInt(1, id_user);
                    checkNameStmt.setInt(2, id_barang);
                    checkNameStmt.setInt(3, id_transaksi);
                
                    ResultSet checkNameRs = checkNameStmt.executeQuery();
                
                    if (checkNameRs.next()) {
                        System.out.println("Transaksi already exists. Update operation skipped.");
                    } else {
                        String query2 = "UPDATE transaksi SET date_transaksi = ?, name_barang = ?, amount_transaksi = ?, note_transaksi = ? WHERE id_user = ? AND id_transaksi = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(query2)) {
                            updateStmt.setDate(1, date_transaksi);
                            updateStmt.setString(2, name_barang);
                            updateStmt.setString(3, amount_transaksi);
                            updateStmt.setString(4, note_transaksi);
                            updateStmt.setInt(5, id_user);
                            updateStmt.setInt(6, id_transaksi);
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

    public void transaksiDeleteTrue() {
        int id_transaksi = transaksiTable.getId_transaksi();
        String deleteSql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id_transaksi);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        if (transaksiTable != null) {
            transaksiTable.getTransaksiController().refreshTable();
        }
    
        Stage stage = (Stage) transaksiOkDelete.getScene().getWindow();
        stage.close();
    }
    
    public void transaksiDeleteFalse() {
        Stage stage = (Stage) transaksiOkDelete.getScene().getWindow();
        stage.close();
    }
}

