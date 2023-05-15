package com.example.usos.StudentMethods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "Subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "max_students")
    private Integer maxNumberOfStudents;
    @Column(name = "number_of_students")
    private Integer numberOfStudents;
    @Column(name = "teachers_name")
    private String TeacherName;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Grade> grades;
    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public Subject(){}
    public void addStudent(Student student){
        students.add(student);
    }
    public Subject(String subjectName, Integer maxNumberOfStudents, Integer numberOfStudents, String teacherName) {
        this.subjectName = subjectName;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.numberOfStudents = numberOfStudents;
        TeacherName = teacherName;
    }

    public Subject(String subjectName, Integer maxNumberOfStudents, Integer numberOfStudents, String teacherName, List<Grade> grades) {
        this.subjectName = subjectName;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.numberOfStudents = numberOfStudents;
        TeacherName = teacherName;
        this.grades = grades;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addGrade(Grade grade){
        if(grades==null){
            grades=new ArrayList<>();
        }
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return subjectName + ": " + getGrades();
    }
    public String getSubjectName() {
        return subjectName;
    }

    public Integer getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setMaxNumberOfStudents(Integer maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public double getAverageGrade() {
        if (!grades.isEmpty()) {
            double avg = 0;
            double weightSum = 0;
            for (Grade grade : grades) {
                avg += grade.getValue() * grade.getWeight();
                weightSum += grade.getWeight();
            }
            return avg / weightSum;
        }
        return 0;
    }

    public List<Student> getStudents() {
        return students;
    }
}
