package com.example.morpion2;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class mainController extends Application {
	
	@FXML
    public Button idStart;
    @FXML
    public Button models;
    @FXML
    public Button idSettings;
    @FXML
    public ImageView Title;
    @FXML
    public ImageView startIcon;
    @FXML
    public ImageView modelsIcon;
    @FXML
    public ImageView settingsIcon;

    @FXML
    public AnchorPane idAnchorPane;

    
    
    @FXML
    private void initialize(){


         Image titleImage = new Image("file:resources/images/logo.png");

         ImageView titletView = new ImageView(titleImage);

         titletView.setFitHeight(97.0);
         titletView.setFitWidth(431.0);
         titletView.setX(7.0);
         titletView.setY(34.0);
         titletView.setPreserveRatio(true);

         //idTitlePane.getChildren().add(titletView);

         Image startImage = new Image("file:resources/images/play.png");

         ImageView startView = new ImageView(startImage);

         startView.setFitHeight(23.0);
         startView.setFitWidth(35.0);
         startView.setX(231.0);
         startView.setY(228.0);
         startView.setPreserveRatio(true);

         idStart.setGraphic(startView);

         Image reloadImage = new Image("file:resources/images/modelPic.png");

         ImageView reloadView = new ImageView(reloadImage);

         reloadView.setFitHeight(23.0);
         reloadView.setFitWidth(35.0);
         reloadView.setX(231.0);
         reloadView.setY(298.0);
         reloadView.setPreserveRatio(true);

         models.setGraphic(reloadView);

         Image settingsImage = new Image("file:resources/images/settings.png");

         ImageView settingsView = new ImageView(settingsImage);

         settingsView.setFitHeight(23.0);
         settingsView.setFitWidth(35.0);
         settingsView.setX(231.0);
         settingsView.setY(266.0);
         settingsView.setPreserveRatio(true);

         idSettings.setGraphic(settingsView);

        
     }
    public void startGame(ActionEvent actionEvent) throws IOException {
    
        // Récuperation de la scene
        Parent gameRoot = FXMLLoader.load(getClass().getResource("gameMode.fxml"));
        Scene gameScene = new Scene(gameRoot);

        // Récuperation du stage
        Stage window = (Stage) idStart.getScene().getWindow();

       // Affectaion du scene au stage
        window.setScene(gameScene);
        window.show();
        window.setTitle("Tic-Tac-Toe");
    }
    public void models(ActionEvent actionEvent) throws IOException {
        
        // Récuperation de la scene
        Parent gameRoot = FXMLLoader.load(getClass().getResource("models.fxml"));
        Scene gameScene = new Scene(gameRoot);

        // Récuperation du stage
        Stage window = (Stage) idStart.getScene().getWindow();

       // Affectaion du scene au stage
        window.setScene(gameScene);
        window.show();
        window.setTitle("Models");
    }

    public void settings(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Configuration");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
