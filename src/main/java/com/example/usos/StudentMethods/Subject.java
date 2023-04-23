package com.example.usos.StudentMethods;

public class Subject {
    private String subjectName;
    private Integer maxNumberOfStudents;
    private Integer numberOfStudents;
    private String TeacherName;

    public Subject(String subjectName, Integer maxNumberOfStudents, Integer numberOfStudents, String teacherName) {
        this.subjectName = subjectName;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.numberOfStudents = numberOfStudents;
        TeacherName = teacherName;
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
}
