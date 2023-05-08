package com.example.usos.StudentDashboard;

import com.example.usos.GradesPane;
import com.example.usos.MainApp;
import com.example.usos.StudentMethods.Grade;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.usos.GradesPane.serializeSubjects;
import static com.example.usos.StudentDashboard.UserData.serializeStudent;


public class StudentHomepageController implements Initializable {
    @FXML private Button logOut;
    @FXML private AnchorPane anchorPane;
    @FXML private Label usernameLabel;
    @FXML private Label usernameLabel1;
    @FXML private BarChart<String, Double> avgGradesChart;

    //------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String name = UserData.getInstance().getStudent().getName();
        usernameLabel.setText(name);
        usernameLabel1.setText(name);

        List<Subject> subjects = UserData.getInstance().getStudent().getSubjects();
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        for(Subject subject : subjects){
            series.getData().add(new XYChart.Data<>(subject.getSubjectName(),subject.getAverageGrade()));
        }
      avgGradesChart.getData().add(series);

    }
    //----------------------
    @FXML
    private void changeNodes(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane pane = loader.load(new FileInputStream(new File("src/main/resources/" + fxml)));
        Node newNode1 = new Label("New label 1");
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(pane);

    }
    //-----------------------------------------------------
    public void showGrades(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/GradesPane.fxml");
    }

    //---------------------------------------------------
    public void showSubjects(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/SubjectsPane.fxml");
    }
    //-----------------------------------------------
    public void initialize(){
        String username = UserData.getInstance().getUsername();
        usernameLabel.setText(username);
        usernameLabel1.setText(UserData.getInstance().getStudent().getName());
    }
    //----------------------------------------------------
    public void logOut(ActionEvent event) throws IOException{
        MainApp m = new MainApp();
       serializeSubjects();
        m.changeScene("logIn.fxml",600,400);

    }

    public void showGroups(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/groupsPane.fxml");
    }

    public void showSchedule(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/schedulePane.fxml");
    }

    public void showMySubjects(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/mySubjectsPane.fxml");
    }

    public void showMyData(ActionEvent actionEvent) throws IOException {
        changeNodes("com/example/usos/data.fxml");
    }


}
