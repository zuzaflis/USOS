package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SubjectsPaneController implements Initializable {
    @FXML private TableView<Subject> tableView;
    @FXML private TableColumn<Subject, String> nameColumn;
    @FXML private TableColumn<Subject, Integer> numberOfStudentsColumn;
    @FXML private TableColumn<Subject, Integer> maxNumberOfStudentsColumn;
    @FXML private TableColumn<Subject, String> professorNameColumn;
    @FXML private TextField searchField;

    public void onAddSubjects(ActionEvent actionEvent) {
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            UserData.getInstance().getStudent().getSubjects().add(selectedSubject);
            UserData.getInstance().getStudent().addGrade(selectedSubject,0,0);
            selectedSubject.setNumberOfStudents(selectedSubject.getNumberOfStudents()+1);
        }
    }



    //-----------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("subjectName"));
        numberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("numberOfStudents"));
        maxNumberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("maxNumberOfStudents"));
        professorNameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("TeacherName"));
        tableView.setItems(generateSubjects());

        FilteredList<Subject> filteredData = new FilteredList<>(generateSubjects(), b-> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( subject -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(subject.getSubjectName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if( subject.getTeacherName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if( String.valueOf(subject.getNumberOfStudents()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false; //doesn't match
            });
        });
        SortedList<Subject> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);



    }
    //----------------------------------------------------------
    private ObservableList<Subject> generateSubjects(){
        ObservableList<Subject> subjects = FXCollections.observableArrayList();

        Subject subject1 = new Subject("Geometria Obliczeniowa", 20,12,"Andrzej Chmiel");
        Subject subject2 = new Subject("Fizyka II", 20,12,"Andrzej Chmiel");
        Subject subject3 = new Subject("Programowanie aplikacji użytkowych", 20,12,"Andrzej Chmiel");
        Subject subject4 = new Subject("Statystyka i Analiza Danych", 20,12,"Andrzej Chmiel");
        Subject subject5 = new Subject("Sieci Komputerowe", 20,12,"Andrzej Chmiel");
        Subject subject6 = new Subject("Nowoczesne Technologie", 20,12,"Andrzej Chmiel");
        Subject subject7 = new Subject("MMNT", 20,15,"Angelika Chmiel");
        Subject subject8 = new Subject("Algebra", 20,1,"Andrzej Zgred");

        subjects.addAll(subject1,subject2,subject3,subject4,subject5,subject6,subject7,subject8);
        return subjects;
    }


    public void onSearch(ActionEvent actionEvent) {

    }
}
