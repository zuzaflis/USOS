package com.example.usos.StudentMethods;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectName;
    private Integer maxNumberOfStudents;
    private Integer numberOfStudents;
    private String TeacherName;

    private List<Grade> grades;
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
}
