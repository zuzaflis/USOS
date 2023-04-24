package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import com.example.usos.StudentMethods.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;


public class DataController {
    @FXML private Label nameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label albumLabel;
    @FXML private Label yearLabel;
    @FXML private Label usernameLabel;
    @FXML private Button changeInfoButton;

    public void initialize(){
        String name = UserData.getInstance().getStudent().getName();
        nameLabel.setText(name);

        String lastname = UserData.getInstance().getStudent().getLastName();
        lastNameLabel.setText(lastname);

        String album = UserData.getInstance().getStudent().getAlbumNumber();
        albumLabel.setText(album);

        String year = String.valueOf(UserData.getInstance().getStudent().getYearOfBirth());
        yearLabel.setText(year);

        String username = UserData.getInstance().getUsername();
        usernameLabel.setText(username);

    }

    public void onChangeInfo(ActionEvent actionEvent) throws IOException {
        Student student = UserData.getInstance().getStudent();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeInfoDialog.fxml"));
        DialogPane changeInfoDialogPane = loader.load();

        ChangeInfoDialogController dialogController = loader.getController();

        //przekazujemy do kontrolera aktualnego studenta a tam jest metoda aktualizujaca
        dialogController.setStudent(student);


        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(changeInfoDialogPane);
        dialog.setTitle("Update Student");


        dialogController.initializeFields();

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK) {
            dialogController.setStudent(student);
            UserData.getInstance().setStudent(student);
            updateLabels();

        }

    }

    //---------------------------------------------
    private void updateLabels() {
        String name = UserData.getInstance().getStudent().getName();
        nameLabel.setText(name);

        String lastname = UserData.getInstance().getStudent().getLastName();
        lastNameLabel.setText(lastname);

        String album = UserData.getInstance().getStudent().getAlbumNumber();
        albumLabel.setText(album);

        String year = String.valueOf(UserData.getInstance().getStudent().getYearOfBirth());
        yearLabel.setText(year);

        String username = UserData.getInstance().getUsername();
        usernameLabel.setText(username);
    }
}
