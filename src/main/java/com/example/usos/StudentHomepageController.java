package com.example.usos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class StudentHomepageController {
    @FXML private Button logOut;
    @FXML private AnchorPane anchorPane;

    //----------------------
    @FXML
    private void changeNodes(){
        Node newNode1 = new Label("New label 1");
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(newNode1);

    }
    public void showGrades(ActionEvent actionEvent) {
changeNodes();
    }

    //----------------------------------------------------
    public void logOut(ActionEvent event) throws IOException{
        MainApp m = new MainApp();
        m.changeScene("logIn.fxml",600,400);

    }

}
