package com.example.morpion2;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class ModelsController extends Application {

    @FXML
    private TableView<Model> modelsTable;

    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Model, String> models;
    @FXML
    private TableColumn select;

    private ObservableList<Model> listItems;


    @FXML
    public void initialize(){
        List<File> Listfiles = loadModels();
        listItems = FXCollections.observableArrayList();
        for (File f : Listfiles){
            System.out.println(f.getName());
            listItems.add(new Model(f.getPath(),f.getName()));
        }


        models = new TableColumn<Model, String>("Models");
        select = new TableColumn("");

        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Model, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Model, CheckBox> modelCheckBoxCellDataFeatures) {
                Model model = modelCheckBoxCellDataFeatures.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(model.isSelected());

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {


                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        model.setSelected(new_val);

                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });

        modelsTable.setEditable(true);

        models.setCellValueFactory(new PropertyValueFactory<Model,String>("name"));

        modelsTable.setItems(listItems);
        modelsTable.getColumns().addAll(models,select);

    }

    @FXML
    private void deleteAction(ActionEvent action){
        listItems.forEach((model) -> {
            if(model.isSelected()) {
                try {
                    boolean result = Files.deleteIfExists(Paths.get(model.getPath()));
                    if (result) {
                        System.out.println("Le(s) model(s) est/(sont) supprimé(s)!");

                    } else {
                        System.out.println("Désolé, impossible de supprimer le(s) model(s)!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Stage stage = (Stage) deleteBtn.getScene().getWindow();
            stage.close();

        });

        List<File> Listfiles = loadModels();
        listItems = FXCollections.observableArrayList();
        for (File f : Listfiles){
            System.out.println(f.getName());
            listItems.add(new Model(f.getPath(),f.getName()));
        }

        modelsTable.setItems(listItems);


    }

    public List<File> loadModels(){
        String dirLocation = "src/main/resources/resources/models/";

        try {
            List<File> files = Files.list(Paths.get(dirLocation))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            return files;
        } catch (IOException e) {
            System.out.println("directory doesn't exist");
        }
        return null;
    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}
