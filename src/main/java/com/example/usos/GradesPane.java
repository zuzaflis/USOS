package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Grade;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class GradesPane implements Initializable {

    @FXML ListView<Subject>  mySubjects;
    @FXML Button sortButton;
    @FXML
    private ChoiceBox<Subject> subjectChoice;

    public void generateGrades(){
        Subject subject7 = new Subject("MMNT", 20,15,"Angelika Chmiel");
        Subject subject8 = new Subject("Algebra", 20,1,"Andrzej Zgred");

        UserData.getInstance().getStudent().addGrade(subject7,5.0,2.0);
        UserData.getInstance().getStudent().addGrade(subject7,4.0,2.5);

        UserData.getInstance().getStudent().addGrade(subject8,4.0,2.0);
        UserData.getInstance().getStudent().addGrade(subject8,5.0,2.5);

        UserData.getInstance().getStudent().getSubjects().add(subject7);
        UserData.getInstance().getStudent().getSubjects().add(subject8);
    }

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

        //mySubjects.getItems().addAll(UserData.getInstance().getStudent().getSubjects());

    }

    public void onSort(ActionEvent actionEvent) {
        //Comparator<Grade> gradeAvgComparator = Comparator.comparing(Subject.getAverageGrade());
        ObservableList<Subject> subjects = mySubjects.getItems();
        FXCollections.sort(subjects, Comparator.comparingDouble(Subject::getAverageGrade).reversed());
    }
}
