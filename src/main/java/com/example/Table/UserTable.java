package com.example.Table;

import com.example.PenggunaController;
import com.example.Form.PenggunaForm;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserTable {
    private SimpleIntegerProperty id_user = new SimpleIntegerProperty();
    private SimpleStringProperty name_user = new SimpleStringProperty();
    private SimpleIntegerProperty role_user = new SimpleIntegerProperty();
    private SimpleStringProperty username_user = new SimpleStringProperty();
    private SimpleStringProperty email_user = new SimpleStringProperty();
    private HBox button_box;
    public static UserTable currentInstance;
    private PenggunaController penggunaController;

    public UserTable (int id_user, String name_user, int role_user, String username_user, String email_user) {
        this.id_user = new SimpleIntegerProperty(id_user);
        this.name_user = new SimpleStringProperty(name_user);
        this.role_user = new SimpleIntegerProperty(role_user);
        this.username_user = new SimpleStringProperty(username_user);
        this.email_user = new SimpleStringProperty(email_user);

        Image edit_icon = new Image(getClass().getResourceAsStream("/com/example/img/edit-icon.png"));
        ImageView edit_icon_view = new ImageView(edit_icon);
        edit_icon_view.setFitWidth(20);
        edit_icon_view.setFitHeight(20);
        Button edit_but = new Button();
        edit_but.setGraphic(edit_icon_view);
        edit_but.setStyle("-fx-background-color: #FFA800; -fx-border-width: 10;");
        edit_but.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/edit_pengguna.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent editRoot = fxmlLoader.load();

                Stage editStage = new Stage();
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.initOwner(edit_but.getScene().getWindow());
                Scene scene = new Scene(editRoot);

                scene.getStylesheets().add(css);

                PenggunaForm controller = fxmlLoader.getController();
                controller.setPenggunaTable(this);
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fxml/delete_pengguna.fxml"));
                String css = getClass().getResource("/com/example/css/application.css").toExternalForm();
                Parent deleteRoot = fxmlLoader.load();

                Stage deleteStage = new Stage();
                deleteStage.initStyle(StageStyle.UNDECORATED);
                deleteStage.initModality(Modality.APPLICATION_MODAL);
                deleteStage.initOwner(edit_but.getScene().getWindow());
                Scene scene = new Scene(deleteRoot);

                scene.getStylesheets().add(css);

                PenggunaForm controller = fxmlLoader.getController();
                controller.setPenggunaTable(this);

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

    public void setPenggunaController(PenggunaController penggunaController) {
        this.penggunaController = penggunaController;
    }

    public int getId_user() {
        return id_user.get();
    }

    public String getName_user() {
        return name_user.get();
    }

    public int getRole_user() {
        return role_user.get();
    }

    public String getUsername_user() {
        return username_user.get();
    }

    public String getEmail_user() {
        return email_user.get();
    }

    public HBox getButton_box() {
        return button_box;
    }

    public PenggunaController getPenggunaController() {
        return this.penggunaController;
    }
}
