package com.example.Table;


public class LaporanTable {
    private final int id_laporan;
    private final String date_transaksi; 
    private final int id_akun;
    private final String name_game;
    private final String amount_transaksi;

    public LaporanTable(int id_laporan, String date_transaksi, int id_akun, String name_game, String amount_transaksi) {
        this.id_laporan = id_laporan;
        this.date_transaksi = date_transaksi;
        this.id_akun = id_akun;
        this.name_game = name_game;
        this.amount_transaksi = amount_transaksi;
    }

    public int getId_laporan() {
        return id_laporan;
    }

    public String getDate_transaksi() {
        return date_transaksi;
    }

    public int getId_akun() {
        return id_akun;
    }

    public String getName_game() {
        return name_game;
    }

    public String getAmount_transaksi() {
        return amount_transaksi;
    }
}   





