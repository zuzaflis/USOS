package com.example.usos.StudentDashboard;

import com.example.usos.StudentMethods.Group;
import com.example.usos.StudentMethods.Student;

import java.io.*;
import java.util.List;

public class UserData implements Serializable{
    private static final UserData INSTANCE = new UserData();


    private String username;
    private String password;
    private Student student;
    private List<Double> grades;
    private  List<Group> groups;
    private UserData() {}

    public List<Double> getGrades() {
        return grades;
    }

    public List<Group> getGroups() {
        return groups;
    }


    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

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
    //------------------------------------
    public static void serializeStudent(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.ser"));
            Student student = UserData.getInstance().getStudent();
            outputStream.writeObject(student);
            outputStream.close();
            System.out.println("File saved at: " + new File("student.ser").getAbsolutePath());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //------------------------------------
    public static void deserializeStudent() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.ser"));
            Student student = (Student) inputStream.readObject();
            inputStream.close();
            UserData.getInstance().setStudent(student);
            System.out.println("File loaded from: " + new File("student.ser").getAbsolutePath());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
