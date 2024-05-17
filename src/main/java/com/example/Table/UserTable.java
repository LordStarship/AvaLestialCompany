package com.example.Table;

public class UserTable {
    private final int id_user;
    private final int id_laporan;
    private final int id_transaksi;
    private final int id_barang;
    private final int id_game;
    private final String name_user;
    private final int role_user;
    private final String password_user;
    private final String username_user;
    private final String email_user;

    public UserTable(int id_user, int id_laporan, int id_transaksi, int id_barang, int id_game,
                     String name_user, int role_user, String password_user, String username_user, String email_user) {
        this.id_user = id_user;
        this.id_laporan = id_laporan;
        this.id_transaksi = id_transaksi;
        this.id_barang = id_barang;
        this.id_game = id_game;
        this.name_user = name_user;
        this.role_user = role_user;
        this.password_user = password_user;
        this.username_user = username_user;
        this.email_user = email_user;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_laporan() {
        return id_laporan;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public int getId_barang() {
        return id_barang;
    }

    public int getId_game() {
        return id_game;
    }

    public String getName_user() {
        return name_user;
    }

    public int getRole_user() {
        return role_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public String getUsername_user() {
        return username_user;
    }

    public String getEmail_user() {
        return email_user;
    }
}
