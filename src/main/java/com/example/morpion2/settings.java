package com.example.morpion2;

import com.example.morpion2.MultiLayerPerceptron;
import com.example.morpion2.SigmoidalTransferFunction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;

public class settings extends Application {

    public static String fileModelFacile;
    public static String fileModelDifficile;

    @FXML
    public Button buttonValider;

    @FXML
    public TextField facileH, facileR, facilenbLayers;

    @FXML
    public TextField moyenH, moyenR, moyennbLayers;

    @FXML
    public TextField difficileH, difficleR, difficilenbLayers;
    @FXML
    public static TextField idColumn;

    @FXML
    public static TextField idRow;

    @FXML
    public Button idSaveButton;

    public MultiLayerPerceptron net;

    @FXML
    public Button manageModelsButton;

    public static int numColumn = 3;

    public static int numRow = 3;

    @FXML private Label labelSubmit;

    @FXML
    public void initialize() throws IOException {
        idSaveButton = new Button();
        labelSubmit = new Label();

        FileReader fin = new FileReader("src/main/resources/resources/config.txt");

        BufferedReader bin = new BufferedReader(fin);

        String facile = bin.readLine();
        String diffcile = bin.readLine();

        String [] facileSplit = facile.split(":");
        String [] difficileSplit = diffcile.split(":");

        int hf = Integer.parseInt(facileSplit[0]);
        double lrf =  new Double(facileSplit[1]);
        int lf = Integer.parseInt(facileSplit[2]);

        int hd = Integer.parseInt(difficileSplit[0]);
        double lrd = new Double(difficileSplit[1]);
        int ld =Integer.parseInt(difficileSplit[2]);

        setDifficileH(hd);
        setDifficilenbLayers(lrd);
        setDifficleR(ld);

        setFacileH(hf);
        setFacilenbLayers(lrf);
        setFacileR(lf);

        bin.close();

        idSaveButton.requestFocus();
    }


    public void setFacileH(int facileH) {
        this.facileH.setText(String.valueOf(facileH));
    }

    public void setFacileR(int facileR) {
        this.facileR.setText(String.valueOf(facileR));
    }

    public void setFacilenbLayers(double facilenbLayers) {
        this.facilenbLayers.setText(String.valueOf(facilenbLayers));
    }

    public void setDifficileH(int difficileH) {
        this.difficileH.setText(String.valueOf(difficileH)) ;
    }

    public void setDifficleR(int difficleR) {
        this.difficleR.setText(String.valueOf(difficleR));
    }

    public void setDifficilenbLayers(double difficilenbLayers) {
        this.difficilenbLayers.setText(String.valueOf(difficilenbLayers));
    }


    public void validerConfig(ActionEvent actionEvent) throws IOException {

        FileWriter fin = new FileWriter("src/main/resources/config.txt");

        BufferedWriter b = new BufferedWriter(fin);

        b.write(Integer.parseInt(facileH.getText())+":"+new Double(facilenbLayers.getText())+":"+Integer.parseInt(facileR.getText()));
        b.newLine();
        b.write(Integer.parseInt(difficileH.getText())+":"+new Double(difficilenbLayers.getText())+":"+Integer.parseInt(difficleR.getText()));
        b.close();
        labelSubmit.setText("Values has been changed ");
    }

    public void ActionSaveDimensions(ActionEvent actionEvent) {
        numColumn = Integer.parseInt(idColumn.getText());
        System.out.println(numColumn);
        numRow = Integer.parseInt(idRow.getText());
        System.out.println(numRow);
    }



    @Override
    public void start(Stage stage) throws Exception {
    }
}