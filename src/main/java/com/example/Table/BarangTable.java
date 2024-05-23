package com.example.Table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BarangTable {
    private SimpleIntegerProperty id_barang = new SimpleIntegerProperty();
    private SimpleStringProperty name_barang = new SimpleStringProperty();
    private SimpleStringProperty email_barang = new SimpleStringProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty variation_game = new SimpleStringProperty();
    private SimpleStringProperty type_game = new SimpleStringProperty();
    private SimpleStringProperty amount_barang = new SimpleStringProperty();
    private HBox button_box;

    public BarangTable(int id_barang, String name_barang, String email_barang, String name_game, String variation_game, String type_game, String amount_barang) {
        this.id_barang = new SimpleIntegerProperty(id_barang);
        this.name_barang = new SimpleStringProperty(name_barang);
        this.email_barang = new SimpleStringProperty(email_barang);
        this.name_game = new SimpleStringProperty(name_game);
        this.variation_game = new SimpleStringProperty(variation_game);
        this.type_game = new SimpleStringProperty(type_game);
        this.amount_barang = new SimpleStringProperty(amount_barang);

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

    public HBox getButton_box() {
        return button_box;
    }
}

