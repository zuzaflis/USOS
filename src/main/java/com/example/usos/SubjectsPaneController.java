package com.example.usos;

import com.example.usos.HibernateUtil.HibernateUtil;
import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Grade;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SubjectsPaneController implements Initializable {
    @FXML private TableView<Subject> tableView;
    @FXML private TableColumn<Subject, String> nameColumn;
    @FXML private TableColumn<Subject, Integer> numberOfStudentsColumn;
    @FXML private TableColumn<Subject, Integer> maxNumberOfStudentsColumn;
    @FXML private TableColumn<Subject, String> professorNameColumn;
    @FXML private TextField searchField;

    //-----------------------------------------------------

    public void onAddSubjects(ActionEvent actionEvent) {
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if (selectedSubject != null && !UserData.getInstance().getStudent().getSubjects().contains(selectedSubject) ) {

            UserData.getInstance().getStudent().getSubjects().add(selectedSubject);
            Grade grade1 = new Grade(0.0,0.0);
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                session.beginTransaction();
                session.saveOrUpdate(UserData.getInstance().getStudent());
                session.saveOrUpdate(selectedSubject);
                session.saveOrUpdate(grade1);
                session.getTransaction().commit();


                UserData.getInstance().getStudent().addGrade(selectedSubject,grade1);

                grade1.setStudent(UserData.getInstance().getStudent());
                grade1.setSubject(selectedSubject);

                selectedSubject.addStudent(UserData.getInstance().getStudent());
                selectedSubject.addGrade(grade1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            selectedSubject.setNumberOfStudents(selectedSubject.getNumberOfStudents()+1);
        }
    }

    //-----------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        nameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("subjectName"));
        numberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("numberOfStudents"));
        maxNumberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("maxNumberOfStudents"));
        professorNameColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("TeacherName"));



        tableView.setItems(generateSubjects());

        FilteredList<Subject> filteredData = new FilteredList<>(generateSubjects(), b-> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( subject -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(subject.getSubjectName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if( subject.getTeacherName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if( String.valueOf(subject.getNumberOfStudents()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false; //doesn't match
            });
        });
        SortedList<Subject> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);



    }
    //----------------------------------------------------------
    private ObservableList<Subject> generateSubjects(){


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Subject> query = session.createQuery("SELECT s FROM Subject s LEFT JOIN FETCH s.students" , Subject.class);
           // Query<Subject> query = session.createQuery("SELECT s FROM Subject s JOIN FETCH s.ratings", Subject.class);
            List<Subject> subjectList = query.list();
            ObservableList<Subject> subjects = FXCollections.observableArrayList(subjectList);
            return subjects;
        } catch (Exception e ){
            e.printStackTrace();
        }

        return null;
    }
//----------------------------------------------------------------------------------


    public void onSearch(ActionEvent actionEvent) {}
}
