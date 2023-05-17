package com.example.usos;

import com.example.usos.HibernateUtil.HibernateUtil;
import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class MainApp extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

//
//        Subject subject10 = new Subject("Geometria Obliczeniowa", 20, 12, "Maria Kowalska");
//        Subject subject2 = new Subject("Fizyka II", 20, 12, "Jan Nowak");
//        Subject subject3 = new Subject("Programowanie aplikacji użytkowych", 20, 12, "Barbara Nowak");
//        Subject subject4 = new Subject("Statystyka i Analiza Danych", 20, 12, "Tomasz Kaczmarek");
//        Subject subject5 = new Subject("Sieci Komputerowe", 20, 12, "Anna Kowalska");
//        Subject subject6 = new Subject("Nowoczesne Technologie", 20, 12, "Adam Nowak");
//        Subject subject7 = new Subject("MMNT", 20, 15, "Magdalena Kaczmarek");
//        Subject subject8 = new Subject("Algebra", 20, 1, "Karolina Zgred");
//        session.save(subject10);
//        session.save(subject2);
//        session.save(subject3);
//        session.save(subject4);session.save(subject5);
//        session.save(subject6);session.save(subject7);session.save(subject8);
//
//
//        Student student1 = new Student("Zuzannaa", "Flis", StudentCondition.PRESENT,2002, 305.0, "309872");
//        Subject subject1 = new Subject("Statystyka i analiza danych", 20, 15, " Krystyna Dąb");
//        Grade grade1 = new Grade( 4.5, 2.0);
//        List<Student> students = new ArrayList<>();
//        Student student0 = new Student("Aleksander", "Mach", StudentCondition.PRESENT,2002, 305.0, "39872");
//        Student student2 = new Student("Zenon", "Flis", StudentCondition.PRESENT,2002, 305.0, "309872");
//        Student student3 = new Student("Andrei", "Flich", StudentCondition.PRESENT,2002, 305.0, "309872");
//        students.add(student0);
//        students.add(student2);
//        students.add(student3);
//        Group group1 = new Group("grupa 1",students,16);
//
//        Date date = new Date();
//        Rating rating1 = new Rating(3,subject1,date,"Tragedia");
//
//        session.save(student1);
//        session.save(subject1);
//        session.save(grade1);
//        session.save(rating1);
//        session.save(group1);
//
//        subject1.addRating(rating1);
//
//        grade1.setStudent(student1);
//        grade1.setSubject(subject1);
//        student1.addGrade(subject1,grade1);
//        subject1.addGrade(grade1);
//        student1.addSubject(subject1);
//        subject1.addStudent(student1);

       tx.commit();
        session.close();


        stg = stage;
       stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("USOS");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml, int width, int height) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(pane, width, height);
        stg.setScene(scene);
        stg.show();
    }
    public static void main(String[] args) {
        launch();
    }
}