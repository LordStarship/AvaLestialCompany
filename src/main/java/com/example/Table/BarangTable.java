package com.example.Table;

public class BarangTable {
    private final int id_barang;
    private final GameTable game;
    private final String name_barang;
    private final String email_barang;
    private final String amount_barang;

    public BarangTable(int id_barang, GameTable game, String name_barang, String email_barang, String amount_barang) {
        this.id_barang = id_barang;
        this.game = game;
        this.name_barang = name_barang;
        this.email_barang = email_barang;
        this.amount_barang = amount_barang;
    }

    public int getId_barang() {
        return id_barang;
    }

    public GameTable getGame() {
        return game;
    }

    public String getName_barang() {
        return name_barang;
    }

    public String getEmail_barang() {
        return email_barang;
    }

    public String getAmount_barang() {
        return amount_barang;
    }
}
