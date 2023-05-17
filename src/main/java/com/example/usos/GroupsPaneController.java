package com.example.usos;

import com.example.usos.HibernateUtil.HibernateUtil;
import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Group;
import com.example.usos.StudentMethods.Student;
import com.example.usos.StudentMethods.StudentCondition;
import com.example.usos.StudentMethods.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import org.hibernate.Query;
import org.hibernate.Session;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class GroupsPaneController implements Initializable {
    @FXML private ListView<Group> myGroups;
    @FXML private ListView<Group> allGroups;

    @FXML ObservableList<Student> studentGroups;

    public void onAddGroup(ActionEvent actionEvent){

            Student myStudent = UserData.getInstance().getStudent();
        allGroups.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldGroup, newGroup) -> {
            if(newGroup!=null && !newGroup.getStudents().contains(myStudent)){

                newGroup.addStudent(myStudent);
                myStudent.addGroup(newGroup);


                ObservableList<Group> updatedGroup = FXCollections.observableArrayList();
               updatedGroup.addAll(myGroups.getItems());
               updatedGroup.add(newGroup);
               myGroups.setItems(updatedGroup);

                myGroups.setCellFactory(listView -> new ListCell<>() {
                    @Override
                    protected void updateItem(Group group, boolean empty) {
                        super.updateItem(group, empty);
                        if (empty || group == null) {
                            setText(null);
                        } else {
                            setText(group.getGroupName());
                        }
                    }
                });
            }
        }));


        myGroups.setCellFactory(listView ->  new ListCell<>() {
            @Override
            protected  void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);

                if(empty || group == null){
                    setText(null);
                } else{
                    setText((group.getGroupName()));
                    Tooltip tooltip = new Tooltip("Liczba studentów: "+ group.getNumberOfStudents()+"\n Lista studentów: " + group.getStudents()+"\n ");
                    setTooltip(tooltip);
                }
            }
        } );



    }

    public void onRemoveGroup(){
        Group selectedGroup = myGroups.getSelectionModel().getSelectedItem();

        if(selectedGroup!= null){
            selectedGroup.removeStudent(UserData.getInstance().getStudent());
            UserData.getInstance().getStudent().getGroups().remove(selectedGroup);
            myGroups.getItems().remove(selectedGroup);
            allGroups.refresh();


        }
    }
    public ObservableList<Group> generateGroups(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Group> query = session.createQuery("SELECT  g FROM Group g  JOIN FETCH g.listOfStudents", Group.class);
            List<Group> groupList = query.list();
            ObservableList<Group> groups = FXCollections.observableArrayList(groupList);
            return groups;
        } catch (Exception e ){
            e.printStackTrace();
        }
            return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Group> myGroups = FXCollections.observableArrayList(UserData.getInstance().getGroups());

        allGroups.setItems(generateGroups());
       // myGroups.setAll(myGroups);

        allGroups.setCellFactory(listView ->  new ListCell<>() {
            @Override
            protected  void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);

                if(empty || group == null){
                    setText(null);
                } else{
                    setText((group.getGroupName()));
                    Tooltip tooltip = new Tooltip("Liczba studentów: "+ group.getNumberOfStudents()+"\n Lista studentów: " + group.getStudents()+"\n ");
                    setTooltip(tooltip);
                }
            }
        } );



    }
}
