package com.example.usos.StudentDashboard;

import com.example.usos.HibernateUtil.HibernateUtil;
import com.example.usos.StudentMethods.Group;
import com.example.usos.StudentMethods.Student;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData implements Serializable {
    private static final UserData INSTANCE = new UserData();
    private String username;
    private String password;
    private Student student;
    private List<Double> grades;
    private List<Group> groups = new ArrayList<>();
    private List<Subject> subjects;
    private List<Subject> subjectWithRatings;

    private UserData() {}

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Subject> getSubjectWithRatings() {
        return subjectWithRatings;
    }

    public void setSubjectWithRatings(List<Subject> subjectWithRatings) {
        this.subjectWithRatings = subjectWithRatings;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
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


    //------------------------------------------------
    public void updateStudent(Student student) {
        UserData.getInstance().setStudent(student);
    }

    //------------------------------------
    public static void loadUserDataFromDatabase(Long student_id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> studentQuery = session.createQuery("FROM Student WHERE student_id = :student_id");
            studentQuery.setParameter("student_id", student_id);
            Student student = studentQuery.uniqueResult();

            if (student != null) {
                UserData.getInstance().setStudent(student);
                UserData.getInstance().getSubjects();
                Hibernate.initialize(student.getSubjects());
                Hibernate.initialize(student.getGrades());
                Hibernate.initialize(student.getGroups());

                Query<Subject> subjectQuery = session.createQuery("SELECT s FROM Subject s JOIN s.students st WHERE st.student_id = :student_id", Subject.class);
                subjectQuery.setParameter("student_id", student_id);
                List<Subject> subjects = subjectQuery.getResultList();

                UserData.getInstance().setSubjects(subjects);

                Query<Subject> query = session.createQuery("SELECT DISTINCT r.subject FROM Rating r", Subject.class);
                List<Subject> subjectList = query.list();
                UserData.getInstance().setSubjectWithRatings(subjectList);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}