package com.example.usos.StudentMethods;

import com.example.usos.StudentDashboard.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Student implements Comparable<Student>, Serializable {
    private String name;
    private String lastName;
    private StudentCondition studentCondition;
    private Integer yearOfBirth;
    private String albumNumber;
    private double points;
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    private ObservableList<Group> groups = FXCollections.observableArrayList();


    public ObservableList<Group> getGroups() {
        return groups;
    }

    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public void addGrade(Subject subject, double value, double weight){
        Grade grade = new Grade(value, weight);
        subject.addGrade(grade);
    }
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

    public void setStudentCondition(StudentCondition studentCondition) {
        this.studentCondition = studentCondition;
    }

    public StudentCondition getStudentCondition() {
        return studentCondition;
    }

    public double getPoints() {
        return points;
    }

    public String getLastName() {
        return lastName;
    }

    public Student(String name, String lastName, StudentCondition studentCondition, Integer yearOfBirth, double points, String albumNumber) {
        this.name = name;
        this.lastName = lastName;
        this.studentCondition = studentCondition;
        this.yearOfBirth = yearOfBirth;
        this.points = points;
        this.albumNumber= albumNumber;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return name+ " " + lastName;
    }

    @Override
    public int compareTo(Student student) {
        return this.lastName.compareTo(student.lastName); //zwraca 0 jesli sa rowne, ujemne gdy obecny jest mneijszy
    }

}


