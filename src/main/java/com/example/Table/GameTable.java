package com.example.Table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GameTable {
    private SimpleIntegerProperty id_game = new SimpleIntegerProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty variation_game = new SimpleStringProperty();
    private SimpleStringProperty type_game = new SimpleStringProperty();

    public GameTable(int id_game, String name_game, String variation_game, String type_game) {
        this.id_game = new SimpleIntegerProperty(id_game);
        this.name_game = new SimpleStringProperty(name_game);
        this.variation_game = new SimpleStringProperty(variation_game);
        this.type_game = new SimpleStringProperty(type_game);
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
}
