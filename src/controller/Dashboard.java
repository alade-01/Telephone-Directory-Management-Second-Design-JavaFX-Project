package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/* This class displays the dashboard.
*
* @author  CyberDine
* @since   2020-04-11
*/
public class Dashboard {
    @FXML
    private Button logoutSofwareButton;
    @FXML
    private Button addContact;
    @FXML
    private Button modifyContact;
    @FXML
    private Button deleteContact;
    @FXML
    private Button listContact;
    /**
     * This method displays the saveContact window.
     */
    public void addContact(ActionEvent actionEvent) throws IOException {
        //loaad file FXML saveContact window.
        Parent loadFileFXMLsaveContact = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/saveContact.fxml")));
        Scene saveContactWindowScene = new Scene(loadFileFXMLsaveContact);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(saveContactWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    /**
     * This method displays the editContact window.
     */
    public void modifyContact(ActionEvent actionEvent) throws IOException {
        //loaad file FXML modifyContact window.
        Parent loadFileFXMLmodifyContact = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/modifyContact.fxml")));
        Scene modifyContactWindowScene = new Scene(loadFileFXMLmodifyContact);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(modifyContactWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    /**
     * This method displays the deleteContact window.
     */
    public void deleteContact(ActionEvent actionEvent) throws IOException {
        //loaad file FXML deleteContact window.
        Parent loadFileFXMLdeleteContact = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/deleteContact.fxml")));
        Scene deleteContactWindowScene = new Scene(loadFileFXMLdeleteContact);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(deleteContactWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    /**
     * This method displays the listContact window.
     */
    public void listContact(ActionEvent actionEvent) throws IOException {
        //loaad file FXML listContact window.
        Parent loadFileFXMLlistContact = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/listContact.fxml")));
        Scene listContactWindowScene = new Scene(loadFileFXMLlistContact);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(listContactWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    /**
     * This method allows you to disconnect.
     */
    public void logoutSofware(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to log out ?");
        ButtonType okButton = new ButtonType("yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("no", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.setY(230);
        alert.setX(500);
        alert.showAndWait().ifPresent(type -> {
            //Display login window.
            if (type == okButton) { // do something
                //Load file FXML for the login.
                Parent loadFileFXMLLogin = null;
                try {
                    loadFileFXMLLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLLogin != null;
                Scene loginWindowScene = new Scene(loadFileFXMLLogin);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(loginWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
            //We stay on the window dashboard.
            else if (type == noButton) { // do something
                //Load file FXML for the dashboard window.
                Parent loadFileFXMLDashboard = null;
                try {
                    loadFileFXMLDashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboard.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLDashboard != null;
                Scene dashboardWindowScene = new Scene(loadFileFXMLDashboard);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(dashboardWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
            //We stay on the dashboard window.
            else { // do something
                //Load file FXML for the dashboard window.
                Parent loadFileFXMLDashboard = null;
                try {
                    loadFileFXMLDashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboard.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLDashboard != null;
                Scene dashboardIWindowScene = new Scene(loadFileFXMLDashboard);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(dashboardIWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
        });
    }
}