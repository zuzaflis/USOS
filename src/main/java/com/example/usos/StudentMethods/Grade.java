package com.example.usos.StudentMethods;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Grade")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grade_id;
    @Column(name = "value")
    private double value;
    @Column(name = "weight")
    private double weight;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Grade(){}

    public Long getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Long grade_id) {
        this.grade_id = grade_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "  " +
                value + ", ";
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
