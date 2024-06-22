package com.example.Table;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LaporanTable {
    private SimpleIntegerProperty id_transaksi = new SimpleIntegerProperty();
    private Date date_transaksi;
    private SimpleIntegerProperty id_barang = new SimpleIntegerProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleDoubleProperty amount_transaksi = new SimpleDoubleProperty(); 
    private SimpleStringProperty formatted_amount = new SimpleStringProperty();

    public LaporanTable(int id_transaksi, Date date_transaksi, int id_barang, String name_game, Double amount_transaksi) {
        this.id_transaksi = new SimpleIntegerProperty(id_transaksi);
        this.date_transaksi = date_transaksi;
        this.id_barang = new SimpleIntegerProperty(id_barang);
        this.name_game = new SimpleStringProperty(name_game);
        this.amount_transaksi = new SimpleDoubleProperty(amount_transaksi);
        this.formatted_amount = new SimpleStringProperty(formatAmount(amount_transaksi));
    }

    private String formatAmount(double amount) {
        return "Rp " + String.format("%,.2f", amount);
    }

    public int getId_transaksi() {
        return id_transaksi.get();
    }

    public Date getDate_transaksi() {
        return date_transaksi;
    }

    public int getId_barang() {
        return id_barang.get();
    }

    public String getName_game() {
        return name_game.get();
    }

    public Double getAmount_transaksi() {
        return amount_transaksi.get();
    }

    public String getFormattedAmount() {
        return formatted_amount.get();
    }

    public StringProperty formattedAmountProperty() {
        return formatted_amount;
    }
}   





