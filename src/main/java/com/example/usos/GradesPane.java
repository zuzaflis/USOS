package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class GradesPane implements Initializable {

    @FXML ListView<Subject>  mySubjects;
    private ChoiceBox<Subject> subjectChoice;

    public void generateGrades(){
        Subject subject7 = new Subject("MMNT", 20,15,"Angelika Chmiel");
        Subject subject8 = new Subject("Algebra", 20,1,"Andrzej Zgred");

        UserData.getInstance().getStudent().addGrade(subject7,5.0,2.0);
        UserData.getInstance().getStudent().addGrade(subject7,4.0,2.5);

    UserData.getInstance().getStudent().getSubjects().add(subject7);

    }

    public void onReset(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        mySubjects.getItems().addAll(UserData.getInstance().getStudent().getSubjects());
    }
}
