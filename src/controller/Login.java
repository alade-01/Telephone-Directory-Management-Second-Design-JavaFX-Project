package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.RegisterLogin;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

/**
 * This class allows users to login.
 *
 * @author  CyberDine
 * @since   2020-04-11
 */
public class Login {
    @FXML private Button previousButtonWindowLogin;
    @FXML private Button login;
    @FXML private Button register;
    @FXML private Button closeWindowLoginButton;
    @FXML private TextField emailLoginUsers;
    @FXML private PasswordField passwordLoginUsers;
    /**
     * This method is used to check if the information for login entered by the user is correct.
     */
    public void loginUsers(ActionEvent actionEvent) throws SQLException, IOException {
        //Retrieves the information entered.
        String emailLogin = emailLoginUsers.getText().trim();
        String passwordLogin = passwordLoginUsers.getText().trim();
        //Check if the information is empty.
        if (emailLogin.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email address.");
            alert.showAndWait();
        }else if (passwordLogin.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your password.");
            alert.showAndWait();
        } else {
            //Instance the class RegisterLogin.
            RegisterLogin registerLogin = new RegisterLogin();
            //Return the information from te method loginUsers.
            ResultSet answerDataBase = registerLogin.loginUsers(emailLogin,passwordLogin);
            //Check if the information is not empty.
            if (answerDataBase.next()){
                //Information Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                //Checking the field size.
                if (emailLogin.length() >= 10){
                    alert.setContentText("Happy to see you again Mr(Mme)  : "+emailLogin.substring(0, emailLogin.length()-10));
                }else{
                    alert.setContentText("Happy to see you again Mr(Mme) : "+emailLogin);
                }
                alert.showAndWait();

                //Close DataBase.
                answerDataBase.close();

                //Load file FXML for the dashboard window.
                Parent loadFileFXMLDasboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboard.fxml")));
                Scene dashboardInterfaceScene = new Scene(loadFileFXMLDasboard);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(dashboardInterfaceScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }else{
                //Error Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Connection failure : please check the information entered.");
                alert.showAndWait();
            }
        }
    }
    /**
     * This method allows you to register.
     */
    public void registerUsers(ActionEvent actionEvent) throws IOException {
        //Load file FXML for the register window.
        Parent  loadFileFXMLresetpassword = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/register.fxml")));
        Scene reSetWindowScene = new Scene(loadFileFXMLresetpassword);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(reSetWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    /**
     * This method closes the connection window.
     */
    public void closeWindowLogin(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to quit the software ?");
        ButtonType okButton = new ButtonType("yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("no", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.setY(230);
        alert.setX(500);
        alert.showAndWait().ifPresent(type -> {
            //Close the window login.
            if (type == okButton) { // do something
                //Get a handle to the stage.
                Stage stage = (Stage) closeWindowLoginButton.getScene().getWindow();
                //Do what you have to do.
                stage.close();
            }
            //We stay on the login window.
            else if (type == noButton) { // do something
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
            //We stay on the login  window.
            else { // do something
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
    });
    }
    /**
     *This method allows you to return to the welcome window.
     */
    public void previousWindowLogin(ActionEvent actionEvent) {
        //Load file FXML for the home window.
        Parent loadFileFXMLHome = null;
        try {
            loadFileFXMLHome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/home.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert loadFileFXMLHome != null;
        Scene homeWindowScene = new Scene(loadFileFXMLHome);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(homeWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}