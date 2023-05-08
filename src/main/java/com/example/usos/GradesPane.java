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

    public void generateGrades(){
        Subject subject7 = new Subject("MMNT", 20,15,"Magdalena Kaczmarek");
        Subject subject8 = new Subject("Algebra", 20,1,"Karolina Zgred");
        Subject subject9 = new Subject("Sieci Komputerowe", 20, 12, "Anna Kowalska");
        Subject subject10 = new Subject("Nowoczesne Technologie", 20, 12, "Adam Nowak");

        UserData.getInstance().getStudent().addGrade(subject7,5.0,2.0);
        UserData.getInstance().getStudent().addGrade(subject7,4.0,2.5);

        UserData.getInstance().getStudent().addGrade(subject8,4.0,2.0);
        UserData.getInstance().getStudent().addGrade(subject8,5.0,2.5);

        UserData.getInstance().getStudent().addGrade(subject9,2.0,3.0);
        UserData.getInstance().getStudent().addGrade(subject9,4.0,2.5);

        UserData.getInstance().getStudent().addGrade(subject10,3.5,2.0);
        UserData.getInstance().getStudent().addGrade(subject10,4.0,3.5);

        UserData.getInstance().getStudent().getSubjects().add(subject7);
        UserData.getInstance().getStudent().getSubjects().add(subject8);
        UserData.getInstance().getStudent().getSubjects().add(subject9);
        UserData.getInstance().getStudent().getSubjects().add(subject10);
    }
    public void deserializeSubjects() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("grades.ser"));
            List<Subject> subjectList = (List<Subject>) inputStream.readObject();
            inputStream.close();
            UserData.getInstance().getStudent().getSubjects().setAll(subjectList);
            System.out.println("File loaded from: " + new File("grades.ser").getAbsolutePath());
            for (Subject subject : subjectList) {
                System.out.println(subject);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void serializeSubjects() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("grades.ser"));
            List<Subject> subjectList=new ArrayList<>(UserData.getInstance().getStudent().getSubjects());
            outputStream.writeObject(subjectList);
            outputStream.close();
            System.out.println("File saved at: " + new File("grades.ser").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onReset(ActionEvent actionEvent) {
        mySubjects.getItems().setAll(UserData.getInstance().getStudent().getSubjects());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       // deserializeSubjects();
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

        //serializeSubjects();
        //mySubjects.getItems().addAll(UserData.getInstance().getStudent().getSubjects());

    }

    public void onSort(ActionEvent actionEvent) {
        //Comparator<Grade> gradeAvgComparator = Comparator.comparing(Subject.getAverageGrade());
        ObservableList<Subject> subjects = mySubjects.getItems();
        FXCollections.sort(subjects, Comparator.comparingDouble(Subject::getAverageGrade).reversed());
    }
}
