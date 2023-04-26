package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Group;
import com.example.usos.StudentMethods.Student;
import com.example.usos.StudentMethods.StudentCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;


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
            myGroups.getItems().remove(selectedGroup);
            allGroups.refresh();


        }
    }
    public void generateGroups(){
        List<Student> students1= new ArrayList<>();
        students1.add(new Student("Olek" ,"Kowalski", StudentCondition.PRESENT,2003,203.0,"213321"));
        Group group1 = new Group("Grupa 1", students1,30);
        allGroups.getItems().add(group1);


        List<Student> students2= new ArrayList<>();
        students2.add(new Student("Aleksander" ,"Kowalski", StudentCondition.PRESENT,2003,203.0,"213321"));
        //students2.add(UserData.getInstance().getStudent());
        Group group2 = new Group("Grupa 2", students2,30);
        allGroups.getItems().add(group2);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Group> groups = FXCollections.observableArrayList();
        allGroups.setItems(groups);
        generateGroups();


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
