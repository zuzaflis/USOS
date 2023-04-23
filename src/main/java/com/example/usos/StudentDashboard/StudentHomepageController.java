package com.example.usos.StudentDashboard;

import com.example.usos.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class StudentHomepageController {
    @FXML private Button logOut;
    @FXML private AnchorPane anchorPane;
    @FXML private Label usernameLabel;
    @FXML private Label usernameLabel1;

    //----------------------
    @FXML
    private void changeNodes(String fxml) throws IOException {
        FXMLLoader loader = FXMLLoader.load(getClass().getResource(fxml));
        AnchorPane newAnchorPane = loader.load();
        Node newNode1 = new Label("New label 1");
        anchorPane.getChildren().clear();
        //anchorPane.getChildren().addAll(newNode1);
        anchorPane.getChildren().add(newAnchorPane);

    }
    //-----------------------------------------------------
    public void showGrades(ActionEvent actionEvent) throws IOException {
        changeNodes("/studentGrades.fxml");
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
