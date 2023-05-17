package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MySubjectsPaneController implements Initializable {
    @FXML
    private TableView<Subject> tableView;
    @FXML private TableColumn<Subject, String> nameColumn;
    @FXML private TableColumn<Subject, Integer> numberOfStudentsColumn;
    @FXML private TableColumn<Subject, String> professorNameColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("subjectName"));
        numberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("numberOfStudents"));
        professorNameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("TeacherName"));

        ObservableList<Subject> observableList = FXCollections.observableList(UserData.getInstance().getStudent().getSubjects());
        tableView.setItems(observableList);
    }
    private void refreshTable() {
        ObservableList<Subject> observableList = FXCollections.observableList(UserData.getInstance().getStudent().getSubjects());
        tableView.setItems(observableList);
    }

    public void onRefresh(ActionEvent actionEvent) {
        refreshTable();
    }

    public void onRemoveButton(ActionEvent actionEvent) {
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if(selectedSubject!= null){
            UserData.getInstance().getStudent().getSubjects().remove(selectedSubject);
            selectedSubject.getStudents().remove(UserData.getInstance().getStudent());
        }
    }

}
