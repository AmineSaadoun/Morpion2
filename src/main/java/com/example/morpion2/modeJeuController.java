package com.example.morpion2;

import com.example.morpion2.MultiLayerPerceptron;
import com.example.morpion2.SigmoidalTransferFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class modeJeuController {

    @FXML
    public Pane idAnchorPane;
    @FXML
    private Button easyBtn;
    @FXML
    private Button hardBtn;

    @FXML
    private Button mediumBtn;
    public static String fileModel;
    public static MultiLayerPerceptron net;
    public static String SelectedMode;

    @FXML
    public void initialize(){

    }

    public MultiLayerPerceptron getNet() {
        return net;
    }


    public void setNet(MultiLayerPerceptron net) {
        this.net = net;
    }


    public void StartPlaying(String mode) throws IOException {

        FileReader fin = new FileReader("src/main/resources/config.txt");

        BufferedReader bin = new BufferedReader(fin);

        String facile = bin.readLine();
        String moyen = bin.readLine();
        String diffcile = bin.readLine();

        String [] facileSplit = facile.split(":");
        String [] moyenSplit = moyen.split(":");
        String [] difficileSplit = diffcile.split(":");

        int hf = Integer.parseInt(facileSplit[1]);
        double lrf =  new Double(facileSplit[2]);
        int lf = Integer.parseInt(facileSplit[3]);

        int hm = Integer.parseInt(moyenSplit[1]);
        double lrm =  new Double(moyenSplit[2]);
        int lm = Integer.parseInt(moyenSplit[3]);

        int hd = Integer.parseInt(difficileSplit[1]);
        double lrd = new Double(difficileSplit[2]);
        int ld =Integer.parseInt(difficileSplit[3]);

        if (mode.equals("Facile")){
            fileModel = "mlp_"+hf+"_"+lrf+"_"+lf+".srl";
        }
        if (mode.equals("Moyen")) {
            fileModel = "mlp_"+hm+"_"+lrm+"_"+lm+".srl";
        }
        if (mode.equals("Difficile")) {
            fileModel = "mlp_"+hd+"_"+lrd+"_"+ld+".srl";
        }


        if(new File("src/main/resources/resources/models/"+fileModel).exists()){
            System.out.println("Le model existe déjà");
            MultiLayerPerceptron net = MultiLayerPerceptron.load("src/main/resources/resources/models/"+fileModel);
        }

        else{

            int[] layers = new int[lf+2];
            layers[0] = 9;
            for (int i = 0; i < lf; i++){
                layers[i+1] = hf;
            }
            layers[layers.length-1] = 9;

            setNet(new MultiLayerPerceptron(layers, lf, new SigmoidalTransferFunction()));

        }
        bin.close();


    }

    @FXML
    public void EasyBtnActionPerformed(ActionEvent actionEvent) throws IOException {
        SelectedMode = "Facile";
        launchAiTraining(SelectedMode);
    }

    @FXML
    public void MediumBtnActionPerformed(ActionEvent actionEvent) throws IOException {
        SelectedMode = "Moyen";
        launchAiTraining(SelectedMode);
    }

    @FXML
    public void HardBtnActionPerformed(ActionEvent actionEvent) throws IOException {
        SelectedMode = "Difficile";
        launchAiTraining(SelectedMode);
    }

    public void launchAiTraining(String mode) throws IOException {
        if(SelectedMode != null){
            StartPlaying(mode);
            if(!new File("src/main/resources/resources/models/"+fileModel).exists()){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("progBar.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Tic Tac Toe");
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }

}