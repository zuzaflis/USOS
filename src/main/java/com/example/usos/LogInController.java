package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Student;
import com.example.usos.StudentMethods.StudentCondition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    @FXML
    private Button logIn;
    @FXML
    private Label wrongInput;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;



    public void logIn(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        MainApp m = new MainApp();
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            wrongInput.setText("Success");

            m.changeScene("adminHomepage.fxml",1280,800);
        } else if (username.getText().toString().equals("student") && password.getText().toString().equals("student")){
            wrongInput.setText("Success");

            String usernameText = username.getText();
           //String passwordText = password.getText();

            UserData.getInstance().setUsername(usernameText);

            Student student = new Student("Zuzanna", "Flis", StudentCondition.PRESENT,2002, 305.0, "309872");
            UserData.getInstance().setStudent(student);
            GradesPane gradesPane = new GradesPane();
            gradesPane.generateGrades();


            m.changeScene("studentHomepage.fxml",1280,800);
        } else {
            wrongInput.setText("Wrong username or password");
        }
    }
}
