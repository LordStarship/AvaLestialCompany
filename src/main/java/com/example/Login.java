package com.example;

import com.example.Connections.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Login {
    @FXML
    private HBox loginPane;
    @FXML
    private BorderPane loginLeftPane;
    @FXML
    private Pane loginRightPane;
    @FXML
    private ImageView loginLogo;
    @FXML
    private Pane loginRightBox;
    @FXML
    private Rectangle loginBottomBox;
    @FXML
    private BorderPane loginBox;
    @FXML
    private Pane loginBoxCenter;
    @FXML 
    private HBox loginBoxLabelTop;
    @FXML
    private Label loginBoxSignIn;
    @FXML
    private Pane loginBoxSignSpacer;
    @FXML
    private VBox loginBoxFill;
    @FXML
    private VBox loginBoxFillTop;
    @FXML
    private Pane loginBoxFillTopSpacer;
    @FXML
    private VBox loginBoxFillTopFill;
    @FXML
    private Label loginTxtEmailUsername;
    @FXML
    private TextField loginEmailUsername;
    @FXML 
    private Label loginTxtPassword;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private HBox loginBoxWrapperForgot;
    @FXML
    private Label loginForgotPassword;
    @FXML
    private VBox loginBoxFillBottom;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginWithAnother;
    @FXML
    private Label loginFacebook;
    @FXML
    private Label loginGoogle;
  

    public void initialize() {
        loginPane.setPrefWidth(1000);
        loginPane.setPrefHeight(600);
        
        loginLeftPane.prefWidthProperty().bind(loginPane.widthProperty().multiply(0.5));
        loginRightPane.prefWidthProperty().bind(loginPane.widthProperty().multiply(0.5));
        loginLeftPane.prefHeightProperty().bind(loginPane.heightProperty());
        loginRightPane.prefHeightProperty().bind(loginPane.heightProperty());

        loginLogo.fitHeightProperty().bind(loginLeftPane.heightProperty().multiply(0.4));
        loginLogo.fitWidthProperty().bind(loginLeftPane.widthProperty().multiply(0.7));

        loginRightBox.prefHeightProperty().bind(loginRightPane.heightProperty());
        loginRightBox.prefWidthProperty().bind(loginRightPane.widthProperty());

        loginBottomBox.heightProperty().bind(loginRightPane.heightProperty().multiply(0.1));
        loginBottomBox.widthProperty().bind(loginRightPane.widthProperty());
        loginBottomBox.layoutXProperty().bind(loginRightPane.widthProperty().subtract(loginBottomBox.widthProperty()).divide(2));
        loginBottomBox.layoutYProperty().bind(loginRightPane.heightProperty().subtract(loginBottomBox.heightProperty()));

        loginBox.prefHeightProperty().bind(loginRightBox.heightProperty().multiply(0.8));
        loginBox.prefWidthProperty().bind(loginRightBox.widthProperty().multiply(0.8));
        loginBox.layoutXProperty().bind(loginRightBox.widthProperty().subtract(loginBox.widthProperty()).divide(2));
        loginBox.layoutYProperty().bind(loginRightBox.heightProperty().subtract(loginBox.heightProperty()).divide(2));
        
        loginBoxLabelTop.prefWidthProperty().bind(loginBoxCenter.widthProperty());
        loginBoxLabelTop.prefHeightProperty().bind(loginBoxCenter.heightProperty().multiply(0.2));
        loginBoxLabelTop.setAlignment(Pos.CENTER);

        loginBoxCenter.prefHeightProperty().bind(loginBox.heightProperty().multiply(0.6));
        loginBoxCenter.prefWidthProperty().bind(loginBox.widthProperty().multiply(0.8));
        
        loginBoxFill.prefHeightProperty().bind(loginBoxCenter.heightProperty().multiply(0.85));
        loginBoxFill.prefWidthProperty().bind(loginBoxCenter.widthProperty());
        loginBoxFill.layoutXProperty().bind(loginBoxCenter.widthProperty().subtract(loginBoxFill.widthProperty()).divide(2));
        loginBoxFill.layoutYProperty().bind(loginBoxCenter.heightProperty().subtract(loginBoxFill.heightProperty()));
        
        loginBoxFillTop.prefHeightProperty().bind(loginBoxFill.heightProperty().multiply(0.9));
        loginBoxFillTop.prefWidthProperty().bind(loginBoxFill.widthProperty());

        loginBoxFillTopSpacer.prefHeightProperty().bind((loginBoxFill).heightProperty().multiply(0.3));

        loginEmailUsername.prefWidthProperty().bind(loginBoxFillTop.widthProperty().multiply(0.8));

        loginBoxFillTopFill.prefHeightProperty().bind(loginBoxFillTop.heightProperty());
        loginBoxFillTopFill.prefWidthProperty().bind(loginBoxFillTop.widthProperty().multiply(0.5));

        loginBoxWrapperForgot.setAlignment(Pos.CENTER_RIGHT);

        loginBoxFillBottom.prefHeightProperty().bind(loginBoxFill.heightProperty().multiply(0.7));
        loginBoxFillBottom.prefWidthProperty().bind(loginBoxFill.widthProperty());
        
        loginBoxFillBottom.setAlignment(Pos.CENTER);
        
        loginButton.prefHeightProperty().bind(loginBoxFillBottom.heightProperty().multiply(0.3));
        loginButton.prefWidthProperty().bind(loginBoxFillBottom.widthProperty().multiply(0.3));

        loginButton.setOnAction(event -> {
            String username = loginEmailUsername.getText();
            String password = loginPassword.getText();
            Popup usernamePopup = createPopup("Please enter your username!");
            Popup passwordPopup = createPopup("Please enter your password!");
            Popup wrongLogin = createPopup("Incorrect username or password!");

            if (username.isEmpty()) {
                usernamePopup.show(loginEmailUsername, 793, 200);
            } else if (password.isEmpty()) {
                passwordPopup.show(loginPassword, 793, 200); 
            } else {
                try {
                    final DB databaseUser = new DB();
                    boolean isAuthenticated = databaseUser.authenticateUser(username, password);
                    if(isAuthenticated) {
                        Parent root = FXMLLoader.load(getClass().getResource("fxml/laporan.fxml"));
                        String css = getClass().getResource("css/application.css").toExternalForm();
                        Font interNormal = Font.loadFont(getClass().getResource("fonts/Inter-VariableFont_slnt,wght.ttf").toExternalForm(), 24);
                        Stage newStage = new Stage();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(css);
                        newStage.setTitle("VertinS");
                        newStage.setScene(scene);
                        Stage oldStage = (Stage) loginButton.getScene().getWindow();
                        oldStage.close();
                        newStage.show();
                    }
                    else {
                        wrongLogin.show(loginEmailUsername, 793, 200); 
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Popup createPopup(String message) {
        Popup popup = new Popup();
        Label label = new Label(message);
        VBox box = new VBox(label);
        box.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 1.25em;");
        popup.getContent().add(box);
        return popup;
    }   
}
