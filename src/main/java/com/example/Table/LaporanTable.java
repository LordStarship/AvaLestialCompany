package com.example.Table;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LaporanTable {
    private SimpleIntegerProperty id_laporan = new SimpleIntegerProperty();
    private Date date_transaksi;
    private SimpleIntegerProperty id_barang = new SimpleIntegerProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty amount_transaksi = new SimpleStringProperty(); 

    public LaporanTable(int id_laporan, Date date_transaksi, int id_barang, String name_game, String amount_transaksi) {
        this.id_laporan = new SimpleIntegerProperty(id_laporan);
        this.date_transaksi = date_transaksi;
        this.id_barang = new SimpleIntegerProperty(id_barang);
        this.name_game = new SimpleStringProperty(name_game);
        this.amount_transaksi = new SimpleStringProperty(amount_transaksi);
    }

    public int getId_laporan() {
        return id_laporan.get();
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

    public String getAmount_transaksi() {
        return amount_transaksi.get();
    }
}   





