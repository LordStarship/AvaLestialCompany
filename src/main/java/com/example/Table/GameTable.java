package com.example.Table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;

public class GameTable {
    private SimpleIntegerProperty id_game = new SimpleIntegerProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty variation_game = new SimpleStringProperty();
    private SimpleStringProperty type_game = new SimpleStringProperty();
    private HBox button_box;

    public GameTable(int id_game, String name_game, String variation_game, String type_game) {
        this.id_game = new SimpleIntegerProperty(id_game);
        this.name_game = new SimpleStringProperty(name_game);
        this.variation_game = new SimpleStringProperty(variation_game);
        this.type_game = new SimpleStringProperty(type_game);

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

    

    public int getId_game() {
        return id_game.get();
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

    public HBox getButton_box() {
        return button_box;
    }
}
