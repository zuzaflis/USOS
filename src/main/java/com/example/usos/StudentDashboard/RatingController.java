package com.example.usos.StudentDashboard;

import com.example.usos.HibernateUtil.HibernateUtil;
import com.example.usos.StudentMethods.Rating;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RatingController implements Initializable {
    @FXML
    private TableView<Subject> tableView;
    @FXML private TableColumn<Subject, String> subjectColumn;
    @FXML private TableColumn<Subject, String> teacherColumn;
    @FXML private TextField searchField;
    @FXML private Label textLabel;
    public void onSearchButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("subjectName"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Subject,String>("TeacherName"));
        tableView.setItems(generateSubjects());

        tableView.setOnMouseClicked(event -> {
            onSubjectSelected();
        });

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
    public void onSubjectSelected() {
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            StringBuilder ratingsText = new StringBuilder();
            for (Rating rating : selectedSubject.getRatings()) {
                ratingsText.append("Rate: ").append(rating.getRate()).append("\n");
                ratingsText.append("Date: ").append(rating.getDate()).append("\n");
                ratingsText.append("Additional Info: ").append(rating.getAdditionalInfo()).append("\n");
                ratingsText.append("------------------------------------\n");
            }
            textLabel.setText(ratingsText.toString());
        }
    }
    //----------------------------------------------------------
    private ObservableList<Subject> generateSubjects(){


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Subject> query = session.createQuery("SELECT s FROM Subject s JOIN FETCH s.ratings", Subject.class);

            List<Subject> subjectList = query.list();
            ObservableList<Subject> subjects = FXCollections.observableArrayList(subjectList);
            return subjects;
        } catch (Exception e ){
            e.printStackTrace();
        }

        return null;
    }
//----------------------------------------------------------------------------------

}
