package com.example.usos.StudentDashboard;

import com.example.usos.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;

public class StudentHomepageController {
    @FXML private Button logOut;
    @FXML private AnchorPane anchorPane;
    @FXML private Label usernameLabel;
    @FXML private Label usernameLabel1;

    //----------------------
    @FXML
    private void changeNodes() throws IOException {
        Node newNode1 = new Label("New label 1");
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(newNode1);

    }
    //-----------------------------------------------------
    public void showGrades(ActionEvent actionEvent) throws IOException {
        changeNodes();
    }

    //---------------------------------------------------
    public void initialize(){
        String username = UserData.getInstance().getUsername();
        usernameLabel.setText(username);
        usernameLabel1.setText(UserData.getInstance().getStudent().getName());
    }
    //----------------------------------------------------
    public void logOut(ActionEvent event) throws IOException{
        MainApp m = new MainApp();
        m.changeScene("logIn.fxml",600,400);

    }

}
