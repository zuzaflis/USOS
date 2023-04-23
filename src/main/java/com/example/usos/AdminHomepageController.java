package com.example.usos;

import com.example.usos.students.Student;
import com.example.usos.students.StudentCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomepageController implements Initializable {

    @FXML
    private Button logOut;
    @FXML private TableView<Student> tableView;
    @FXML private TableColumn<Student,String> nameColumn;
    @FXML private TableColumn<Student,String> lastNameColumn;
    @FXML private TableColumn<Student,Integer> yearColumn;
    @FXML private TableColumn<Student,String> albumColumn;
    @FXML private TableColumn<Student, Double> pointsColumn;
    @FXML private TableColumn<Student, String> studentConditionColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastname"));
        studentConditionColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("studentCondition"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("yearOfBirth"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("albumNumber"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Student,Double>("points"));

        tableView.setItems(generateStudents());
    }
    private ObservableList<Student> generateStudents(){
        ObservableList<Student> students = FXCollections.observableArrayList();

        // Create some sample students
        Student student1 = new Student("John", "Doe", StudentCondition.PRESENT, 1999,  85.0,"123456");
        Student student2 = new Student("Jane", "Doe", StudentCondition.SICK, 2000,  92.5, "654321");
        Student student3 = new Student("Bob", "Smith", StudentCondition.ABSENT, 1998,  78.3,"246810");
        Student student4 = new Student("Alice", "Johnson", StudentCondition.PRESENT, 1997,  91.2,"135790");

        students.addAll(student1,student2,student3,student4);
        return students;
    }
    public void logOut(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene("logIn.fxml",600,400);

    }
}
