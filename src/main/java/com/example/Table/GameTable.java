package com.example.Table;

public class GameTable {
    private final int id_game;
    private final String name_game;
    private final String variation_game;
    private final String type_game;

    public GameTable(int id_game, String name_game, String variation_game, String type_game) {
        this.id_game = id_game;
        this.name_game = name_game;
        this.variation_game = variation_game;
        this.type_game = type_game;
    }

    public int getId_game() {
        return id_game;
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
}
