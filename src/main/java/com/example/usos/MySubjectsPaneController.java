package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Subject;
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

        tableView.setItems(UserData.getInstance().getStudent().getSubjects());
    }
    private void refreshTable() {
        tableView.setItems(UserData.getInstance().getStudent().getSubjects());
    }

    public void onRefresh(ActionEvent actionEvent) {
        refreshTable();
    }

    public void onRemoveButton(ActionEvent actionEvent) {
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if(selectedSubject!= null){
            UserData.getInstance().getStudent().getSubjects().remove(selectedSubject);
        }
    }

    //------------------------------------
    private void serializeSubject(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("subjects.ser"));
            List<Subject> subjectList=new ArrayList<>(UserData.getInstance().getStudent().getSubjects());
            outputStream.writeObject(subjectList);
            outputStream.close();
            System.out.println("File saved at: " + new File("subjects.ser").getAbsolutePath());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //------------------------------------
    private void deserializeSubjects() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("subjects.ser"));
            List<Subject> subjectList = (List<Subject>) inputStream.readObject();
            inputStream.close();
            UserData.getInstance().getStudent().getSubjects().setAll(subjectList);
            System.out.println("File loaded from: " + new File("subjects.ser").getAbsolutePath());
            for (Subject subject : subjectList) {
                System.out.println(subject);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
}
}
