package com.example.morpion2;

import java.io.*;

import com.example.morpion2.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class settings extends Application {

    @FXML private TextField FL;
    @FXML private TextField FLr;
    @FXML private TextField FH;

    @FXML private TextField ML;
    @FXML private TextField MLr;
    @FXML private TextField MH;

    @FXML private TextField DL;
    @FXML private TextField DLr;
    @FXML private TextField DH;

    @FXML
    public void initialize() throws InterruptedException, IOException{
        ConfigFileLoader configFileLoader = new ConfigFileLoader();
        configFileLoader.loadConfigFile("src/main/resources/config.txt");

        Config F;
        F = configFileLoader.get("F");

        Config M;
        M = configFileLoader.get("M");

        Config D;
        D = configFileLoader.get("D");


        if(F!=null) {
            FL.setText(String.valueOf(F.hiddenLayerSize));
            FLr.setText(String.valueOf(F.learningRate));
            FH.setText(String.valueOf(F.numberOfhiddenLayers));
        }
        if(M!=null) {
            ML.setText(String.valueOf(M.hiddenLayerSize));
            MLr.setText(String.valueOf(M.learningRate));
            MH.setText(String.valueOf(M.numberOfhiddenLayers));
        }
        if(D!=null) {
            DL.setText(String.valueOf(D.hiddenLayerSize));
            DLr.setText(String.valueOf(D.learningRate));
            DH.setText(String.valueOf(D.numberOfhiddenLayers));
        }

    }

    public void modifier() throws FileNotFoundException {
        String newFL = FL.getText();
        String newFLr = FLr.getText();
        String newFH = FH.getText();

        String newML = ML.getText();
        String newMLr = MLr.getText();
        String newMH = MH.getText();

        String newDL = DL.getText();
        String newDLr = DLr.getText();
        String newDH = DH.getText();

        if(FL.getText()!=null && FLr.getText()!=null && FH.getText()!=null) {
            newFL = FL.getText();
            newFLr = FLr.getText();
            newFH = FH.getText();
        }

        if(ML.getText()!=null && MLr.getText()!=null && MH.getText()!=null) {
            newML = ML.getText();
            newMLr = MLr.getText();
            newMH = MH.getText();
        }

        if(DL.getText()!=null && DLr.getText()!=null && DH.getText()!=null) {
            newDL = DL.getText();
            newDLr = DLr.getText();
            newDH = DH.getText();
        }

        /*
         * Suppression du fichier config
         */
        File ficConf = new File("src/main/resources/config.txt");
        ficConf.delete();

        /*
         * Création du nouveau fichier config pour modifier les valeurs
         */
        PrintWriter w = new PrintWriter("src/main/resources/config.txt");
        w.println("F:"+newFL+":"+newFLr+":"+newFH);
        w.println("M:"+newML+":"+newMLr+":"+newMH);
        w.println("D:"+newDL+":"+newDLr+":"+newDH);

        w.close();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
