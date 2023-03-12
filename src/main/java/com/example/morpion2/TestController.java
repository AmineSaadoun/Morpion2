package com.example.morpion2;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static com.example.morpion2.Test.learn;


public class TestController extends Application {
    private Thread progressBarThread;

    @FXML
    private ProgressIndicator pi;


    private Task<Void> task;
    @FXML
    private Button start;

    @FXML
    private TextField tf;

    @FXML
    private ProgressBar progbar;

    public void initialize() throws IOException {
        int size = 9;
        int h = 10;
        double lr = 0.1;
        int l = 1;
    }
    @FXML
    private Task<Void> task1() {
        int size = 9;
        int h = 10;
        double lr = 0.1;
        int l = 1;
        return new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                // TODO Auto-generated method stub
                try {
                    System.out.println();
                    System.out.println("START TRAINING ...");
                    System.out.println();
                    //
                    //       int[] layers = new int[]{ size, 128, 128, size };
                    int[] layers = new int[l+2];
                    layers[0] = size ;
                    for (int i = 0; i < l; i++) {
                        layers[i+1] = h ;
                    }
                    layers[layers.length-1] = size ;
                    //
                    double error = 0.0 ;
                    MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
                    double epochs = 10000000 ;

                    System.out.println("---");
                    System.out.println("Load data ...");
                    HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("src/main/resources/resources/train_dev_test/train.txt");
                    HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("src/main/resources/resources/train_dev_test/dev.txt");
                    HashMap<Integer, Coup> mapTest = Test.loadCoupsFromFile("src/main/resources/resources/train_dev_test/test.txt");
                    System.out.println("---");
                    //TRAINING ...
                    for(int i = 0; i < epochs; i++){

                        Coup c = null ;
                        while ( c == null )
                            c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

                        error += net.backPropagate(c.in, c.out);

                        if ( i % 10000 == 0 ) {
                            updateMessage("Error at step " + i + " is " + (error / (double) i));

                        }
                        updateProgress(i,epochs);


                    }

                    System.out.println("Learning completed!");
                }
                catch (Exception e) {
                    System.out.println("Test.test()");
                    e.printStackTrace();
                    System.exit(-1);
                }
                return null;
            }

        };

    }
    public void startTest() {
        task =  task1();


        progbar.setProgress(0);
        pi.setProgress(0);

        progbar.progressProperty().bind(task.progressProperty());
        pi.progressProperty().bind(task.progressProperty());


        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                tf.setText(t1);
            }
        });

        new Thread(task).start();
    }




    @Override
    public void start(Stage stage) throws Exception {

    }
}
