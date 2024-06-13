package com.example.Table;

import com.example.DataGameController;
import com.example.Form.DataGameForm;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;

public class GameTable {
    private SimpleIntegerProperty id_game = new SimpleIntegerProperty();
    private SimpleStringProperty name_game = new SimpleStringProperty();
    private SimpleStringProperty variation_game = new SimpleStringProperty();
    private SimpleStringProperty type_game = new SimpleStringProperty();
    private HBox button_box;
    public static GameTable currentInstance;
    private DataGameController dataGameController;

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
        edit_but.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/edit_game.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();

                Stage editStage = new Stage();
                editStage.initStyle(StageStyle.UNDECORATED);
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.initOwner(edit_but.getScene().getWindow());
                Scene scene = new Scene(editRoot);

                scene.getStylesheets().add(css);

                DataGameForm controller = fxmlLoader.getController();
                controller.setDataGameTable(this);
                controller.updateFields();

                editStage.setScene(scene);
                editStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });


        Image del_icon = new Image(getClass().getResourceAsStream("/com/example/img/delete-icon.png"));
        ImageView del_icon_view = new ImageView(del_icon);
        del_icon_view.setFitWidth(20);
        del_icon_view.setFitHeight(20);
        Button del_but = new Button();
        del_but.setGraphic(del_icon_view);
        del_but.setStyle("-fx-background-color: #FF0000; -fx-border-width: 10;");
        del_but.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/delete_game.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent deleteRoot = fxmlLoader.load();

                Stage deleteStage = new Stage();
                deleteStage.initStyle(StageStyle.UNDECORATED);
                deleteStage.initModality(Modality.APPLICATION_MODAL);
                deleteStage.initOwner(edit_but.getScene().getWindow());
                Scene scene = new Scene(deleteRoot);

                scene.getStylesheets().add(css);

                DataGameForm controller = fxmlLoader.getController();
                controller.setDataGameTable(this);

                deleteStage.setScene(scene);
                deleteStage.show();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });

        this.button_box = new HBox(edit_but, del_but);
        this.button_box.setSpacing(10);
        this.button_box.setAlignment(Pos.CENTER);
    }

    public void setDataGameController(DataGameController dataGameController) {
        this.dataGameController = dataGameController;
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

    public DataGameController getDataGameController() {
        return this.dataGameController;
    }
}
