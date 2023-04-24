package com.example.usos.StudentDashboard;

import com.example.usos.StudentMethods.Student;

public class UserData {
    private static final UserData INSTANCE = new UserData();


    private String username;
    private String password;
    private Student student;
    private UserData() {}
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserData(Student student) {
        this.student = student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public static UserData getInstance() {
        return INSTANCE;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void updateStudent(Student student) {
        UserData.getInstance().setStudent(student);

    }
}
