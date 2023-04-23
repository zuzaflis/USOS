package com.example.usos;

import com.example.usos.StudentDashboard.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DataController {
    @FXML private Label nameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label albumLabel;
    @FXML private Label yearLabel;
    @FXML private Label usernameLabel;
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
}
