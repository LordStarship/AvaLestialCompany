package com.example.Table;

public class BarangTable {
    private final int id_barang;
    private final String name_barang;
    private final String email_barang;
    private final String name_game;
    private final String variation_game;
    private final String type_game;
    private final String amount_barang;

    public BarangTable(int id_barang, String name_barang, String email_barang, String name_game, String variation_game, String type_game, String amount_barang) {
        this.id_barang = id_barang;
        this.name_barang = name_barang;
        this.email_barang = email_barang;
        this.name_game = name_game;
        this.variation_game = variation_game;
        this.type_game = type_game;
        this.amount_barang = amount_barang;
    }

    public int getId_barang() {
        return id_barang;
    }

    public String getName_barang() {
        return name_barang;
    }

    public String getEmail_barang() {
        return email_barang;
    }

    public String getName_game() {
        return name_game;
    }

    public String getVariation_game() {
        return variation_game;
    }

    public String getType_game() {
        return type_game;
    }

    public String getAmount_barang() {
        return amount_barang;
    }

}
