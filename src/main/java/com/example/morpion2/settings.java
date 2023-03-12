package com.example.morpion2;

import java.io.*;

import com.example.morpion2.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class settings extends Application {

    @FXML private TextField fHiddenLayersSize;
    @FXML private TextField fLearningRate;
    @FXML private TextField fNumberOfHiddenLayers;

    @FXML private TextField mHiddenLayersSize;
    @FXML private TextField mLearningRate;
    @FXML private TextField mNumberOfHiddenLayers;

    @FXML private TextField dHiddenLayersSize;
    @FXML private TextField dLearningRate;
    @FXML private TextField dNumberOfHiddenLayers;

    @FXML
    public void initialize() throws InterruptedException, IOException{
        ConfigFileLoader configFileLoader = new ConfigFileLoader();
        /* à l'appel de cette fonction
         * le fichier config va être lu
         * chaque ligne va être un objet Config
         * La HashMap des config va être créé
         */
        configFileLoader.loadConfigFile("src/main/resources/config.txt");
        /*
         * Affectation des paramètre du niveau facile à la config facile
         * même chose pour le niveau difficile et moyen
         */
        Config facile;
        facile = configFileLoader.get("F");
        Config moyen;
        moyen = configFileLoader.get("M");
        Config difficile;
        difficile = configFileLoader.get("D");

        /*
         * Affectation des valeur des niveaux aux differents textField
         */
        //System.out.print(String.valueOf(facile.hiddenLayerSize));

        if(facile!=null) {
            fHiddenLayersSize.setText(String.valueOf(facile.hiddenLayerSize));
            fLearningRate.setText(String.valueOf(facile.learningRate));
            fNumberOfHiddenLayers.setText(String.valueOf(facile.numberOfhiddenLayers));
        }
        if(moyen!=null) {
            mHiddenLayersSize.setText(String.valueOf(moyen.hiddenLayerSize));
            mLearningRate.setText(String.valueOf(moyen.learningRate));
            mNumberOfHiddenLayers.setText(String.valueOf(moyen.numberOfhiddenLayers));
        }
        if(difficile!=null) {
            dHiddenLayersSize.setText(String.valueOf(difficile.hiddenLayerSize));
            dLearningRate.setText(String.valueOf(difficile.learningRate));
            dNumberOfHiddenLayers.setText(String.valueOf(difficile.numberOfhiddenLayers));
        }

    }

    public void modifier() throws FileNotFoundException {
        String newFhiddenLayersSize = fHiddenLayersSize.getText();
        String newFlearningRate = fLearningRate.getText();
        String newFnumberOfHiddenLayers = fNumberOfHiddenLayers.getText();

        String newMhiddenLayersSize = mHiddenLayersSize.getText();
        String newMlearningRate = mLearningRate.getText();
        String newMnumberOfHiddenLayers = mNumberOfHiddenLayers.getText();

        String newDhiddenLayersSize = dHiddenLayersSize.getText();
        String newDlearningRate = dLearningRate.getText();
        String newDnumberOfHiddenLayers = dNumberOfHiddenLayers.getText();

        if(fHiddenLayersSize.getText()!=null && fLearningRate.getText()!=null && fNumberOfHiddenLayers.getText()!=null) {
            newFhiddenLayersSize = fHiddenLayersSize.getText();
            newFlearningRate = fLearningRate.getText();
            newFnumberOfHiddenLayers = fNumberOfHiddenLayers.getText();
        }

        if(mHiddenLayersSize.getText()!=null && mLearningRate.getText()!=null && mNumberOfHiddenLayers.getText()!=null) {
            newMhiddenLayersSize = mHiddenLayersSize.getText();
            newMlearningRate = mLearningRate.getText();
            newMnumberOfHiddenLayers = mNumberOfHiddenLayers.getText();
        }

        if(dHiddenLayersSize.getText()!=null && dLearningRate.getText()!=null && dNumberOfHiddenLayers.getText()!=null) {
            newDhiddenLayersSize = dHiddenLayersSize.getText();
            newDlearningRate = dLearningRate.getText();
            newDnumberOfHiddenLayers = dNumberOfHiddenLayers.getText();
        }

        /*
         * Suppression du fichier config
         */
        File fileConfig = new File("src/main/resources/config.txt");
        fileConfig.delete();

        /*
         * Création du nouveau fichier config pour modifier les valeurs
         */
        File NewfileConfig = new File("src/main/resources/config.txt");
        PrintWriter writer = new PrintWriter("src/main/resources/config.txt");
        writer.println("F:"+newFhiddenLayersSize+":"+newFlearningRate+":"+newFnumberOfHiddenLayers);
        writer.println("M:"+newMhiddenLayersSize+":"+newMlearningRate+":"+newMnumberOfHiddenLayers);
        writer.println("D:"+newDhiddenLayersSize+":"+newDlearningRate+":"+newDnumberOfHiddenLayers);

        writer.close();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
