package com.example.Table;

import java.sql.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransaksiTable {
    private SimpleIntegerProperty id_transaksi = new SimpleIntegerProperty();
    private Date date_transaksi;
    private SimpleStringProperty name_barang = new SimpleStringProperty();
    private SimpleStringProperty amount_transaksi = new SimpleStringProperty();
    private SimpleStringProperty note_transaksi = new SimpleStringProperty(); 
        
    public TransaksiTable(int id_transaksi, Date date_transaksi, String name_barang, String amount_transaksi, String note_transaksi) {
        this.id_transaksi = new SimpleIntegerProperty(id_transaksi);
        this.date_transaksi = date_transaksi;
        this.name_barang = new SimpleStringProperty(name_barang);
        this.amount_transaksi = new SimpleStringProperty(amount_transaksi);
        this.note_transaksi = new SimpleStringProperty(note_transaksi);
    }

    public int getId_transaksi() {
        return id_transaksi.get();
    }

    public Date getDate_transaksi() {
        return date_transaksi;
    }

    public String getName_barang() {
        return name_barang.get();
    }

    public String getAmount_transaksi() {
        return amount_transaksi.get();
    }

    public String getNote_transaksi() {
        return note_transaksi.get();
    }
}
