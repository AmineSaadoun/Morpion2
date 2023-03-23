package com.example.morpion2;

import com.example.morpion2.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class TestController {

    @FXML
    private TextField tf;
    @FXML
    private Button startButton;
    @FXML
    private ProgressBar progbar;

    private Thread progressBarThread;
    private Task<Void> task;

    private String fileModel;
    private MultiLayerPerceptron net;

    private Config config;


    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modeJeu.fxml"));
        Parent root = loader.load();
        modeJeuController controller = loader.getController();
        fileModel = modeJeuController.fileModel;
        System.out.println(fileModel);

        net = controller.getNet();

    }


    public void startTest() {
        startButton.setDisable(true);
        task =  task1();


        progbar.setProgress(0);

        progbar.progressProperty().bind(task.progressProperty());
        progressBarThread =  new Thread(task);
        progressBarThread.start();


        progbar.progressProperty().bind(task.progressProperty());
        task.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progbar.progressProperty().bind(task.progressProperty());
            }
        });
        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(task.getMessage());
                tf.setText(task.getMessage());
            }
        });
    }


    private Task<Void> task1() {
        return new Task<Void>() {

            @Override
            protected Void call() throws Exception {


                int epochs = 1000000;
                
                try {
                    int[] layers = new int[]{ 9, 9, 9};

                    double error = 0.0 ;

                    updateMessage("Learning completed!");

                    // Sauvegarde du model
                    net.save("src/main/resources/resources/models/"+fileModel);

                }
                catch (Exception e) {
                    System.out.println("AI.train()");
                    e.printStackTrace();
                    System.exit(-1);
                }

                return null;
            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }


}