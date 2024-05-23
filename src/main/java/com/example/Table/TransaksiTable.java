package com.example.Table;

import java.sql.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TransaksiTable {
    private SimpleIntegerProperty id_transaksi = new SimpleIntegerProperty();
    private Date date_transaksi;
    private SimpleStringProperty name_barang = new SimpleStringProperty();
    private SimpleStringProperty amount_transaksi = new SimpleStringProperty();
    private SimpleStringProperty note_transaksi = new SimpleStringProperty(); 
    private HBox button_box;
        
    public TransaksiTable(int id_transaksi, Date date_transaksi, String name_barang, String amount_transaksi, String note_transaksi) {
        this.id_transaksi = new SimpleIntegerProperty(id_transaksi);
        this.date_transaksi = date_transaksi;
        this.name_barang = new SimpleStringProperty(name_barang);
        this.amount_transaksi = new SimpleStringProperty(amount_transaksi);
        this.note_transaksi = new SimpleStringProperty(note_transaksi);

        Image edit_icon = new Image(getClass().getResourceAsStream("/com/example/img/edit-icon.png"));
        ImageView edit_icon_view = new ImageView(edit_icon);
        edit_icon_view.setFitWidth(20);
        edit_icon_view.setFitHeight(20);
        Button edit_but = new Button();
        edit_but.setGraphic(edit_icon_view);
        edit_but.setStyle("-fx-background-color: #FFA800; -fx-border-width: 10;");

        Image del_icon = new Image(getClass().getResourceAsStream("/com/example/img/delete-icon.png"));
        ImageView del_icon_view = new ImageView(del_icon);
        del_icon_view.setFitWidth(20);
        del_icon_view.setFitHeight(20);
        Button del_but = new Button();
        del_but.setGraphic(del_icon_view);
        del_but.setStyle("-fx-background-color: #FF0000; -fx-border-width: 10;");

        this.button_box = new HBox(edit_but, del_but);
        this.button_box.setSpacing(10);
        this.button_box.setAlignment(Pos.CENTER);
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

    public HBox getButton_box() {
        return button_box;
    }
}
