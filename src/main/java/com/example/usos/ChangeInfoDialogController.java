package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeInfoDialogController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField yearField;
    @FXML private TextField usernameField;

    @FXML private void initialize() {
        initializeFields();
    }
    private Student student;

    public ChangeInfoDialogController() {}

    public ChangeInfoDialogController(Student student) {
        this.student = student;
    }


    public void initializeFields(){

        if (firstNameField != null) {
            String name = UserData.getInstance().getStudent().getName();
            firstNameField.setText(name);
        }

        if (lastNameField != null) {
            String lastname = UserData.getInstance().getStudent().getLastName();
            lastNameField.setText(lastname);
        }

        if (yearField != null) {
            String year = String.valueOf(UserData.getInstance().getStudent().getYearOfBirth());
            yearField.setText(year);
        }

        if (usernameField != null) {
            String username = UserData.getInstance().getUsername();
            usernameField.setText(username);
        }

    }
    public void setStudent(Student student){
        if (firstNameField != null) {
            String name = firstNameField.getText();
            student.setName(name);
        }

        if (lastNameField != null) {
            String lastName = lastNameField.getText();
            student.setLastName(lastName);
        }

        if (yearField != null) {
            Integer year = Integer.valueOf(yearField.getText());
            student.setYearOfBirth(year);
        }

        if (usernameField != null) {
            String username = usernameField.getText();
            UserData.getInstance().setUsername(username);
        }


       // UserData.getInstance().updateStudent(student);
    }
}
