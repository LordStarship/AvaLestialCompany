package com.example.Table;

public class TransaksiTable {
    private final int id_transaksi;
    private final String date_transaksi;
    private final String name_barang;
    private final String amount_barang;
    private final String note_transaksi;

    public TransaksiTable(int id_transaksi, String date_transaksi, String name_barang, String amount_barang, String note_transaksi) {
        this.id_transaksi = id_transaksi;
        this.date_transaksi = date_transaksi;
        this.name_barang = name_barang;
        this.amount_barang = amount_barang;
        this.note_transaksi = note_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public String getDate_transaksi() {
        return date_transaksi;
    }

    public String getName_barang() {
        return name_barang;
    }

    public String getAmount_barang() {
        return amount_barang;
    }

    public String getNote_transaksi() {
        return note_transaksi;
    }
}
