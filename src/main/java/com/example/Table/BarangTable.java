package com.example.Table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BarangTable {
    private SimpleIntegerProperty id_barang = new SimpleIntegerProperty();
    private SimpleStringProperty name_barang = new SimpleStringProperty();
    private SimpleStringProperty email_barang = new SimpleStringProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty variation_game = new SimpleStringProperty();
    private SimpleStringProperty type_game = new SimpleStringProperty();
    private SimpleStringProperty amount_barang = new SimpleStringProperty();

    public BarangTable(int id_barang, String name_barang, String email_barang, String name_game, String variation_game, String type_game, String amount_barang) {
        this.id_barang = new SimpleIntegerProperty();
        this.name_barang = new SimpleStringProperty();
        this.email_barang = new SimpleStringProperty();
        this.name_game = new SimpleStringProperty();
        this.variation_game = new SimpleStringProperty();
        this.type_game = new SimpleStringProperty();
        this.amount_barang = new SimpleStringProperty();
    }

    public int getId_barang() {
        return id_barang.get();
    }

    public String getName_barang() {
        return name_barang.get();
    }

    public String getEmail_barang() {
        return email_barang.get();
    }

    public String getName_game() {
        return name_game.get();
    }

    public String getVariation_game() {
        return variation_game.get();
    }

    public String getType_game() {
        return type_game.get();
    }

    public String getAmount_barang() {
        return amount_barang.get();
    }
}
