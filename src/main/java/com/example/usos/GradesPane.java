package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Grade;
import com.example.usos.StudentMethods.Student;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class GradesPane implements Initializable, Serializable {

    @FXML ListView<Subject>  mySubjects;
    @FXML Button sortButton;
    @FXML
    private ChoiceBox<Subject> subjectChoice;

    public void onReset(ActionEvent actionEvent) {
        mySubjects.getItems().setAll(UserData.getInstance().getStudent().getSubjects());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choice Box
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        subjects.addAll(UserData.getInstance().getStudent().getSubjects());

        mySubjects.getItems().addAll(subjects);


        subjectChoice.setItems(subjects);

        subjectChoice.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
            if(newValue!= null){
                mySubjects.getItems().setAll(newValue);
            } else {
                mySubjects.getItems().clear();
            }
        } );


        mySubjects.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Subject subject, boolean empty) {
                super.updateItem(subject, empty);
                if (empty || subject == null) {
                    setText(null);
                } else {
                    setText(subject.toString());
                    Tooltip tooltip = new Tooltip(String.format("Average grade: %.2f", subject.getAverageGrade()));
                    setTooltip(tooltip);
                }
            }
        });


    }

    public void onSort(ActionEvent actionEvent) {
        ObservableList<Subject> subjects = mySubjects.getItems();
        FXCollections.sort(subjects, Comparator.comparingDouble(Subject::getAverageGrade).reversed());
    }
}
