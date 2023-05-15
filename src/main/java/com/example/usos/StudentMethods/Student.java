package com.example.usos.StudentMethods;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student implements Comparable<Student>, Serializable {
    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "student_condition")
    private StudentCondition studentCondition;
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @Column(name = "album_number")
    private String albumNumber;
    @Column(name = "points")
    private double points;
    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects = new ArrayList<>();
    @ManyToMany(mappedBy = "listOfStudents")
    private List<Group> groups = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();

    public void addSubject(Subject subject){
        subjects.add(subject);
    }
    public List<Group> getGroups() {
        return groups;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addGrade(Subject subject, double value, double weight){
        Grade grade = new Grade(value, weight);
        subject.addGrade(grade);
        grades.add(grade);
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
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

    public void addGrade(Subject subject1, Grade grade1) {
        subject1.addGrade(grade1);
        grades.add(grade1);
    }
}


