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

import java.sql.SQLException;
import java.util.Objects;

/**
 * This class allows users to register.
 *
 * @author  CyberDine
 * @since   2020-04-11
 */
public class Register {

    @FXML
    private Button loginUsersButton;
    @FXML
    private Button register;
    @FXML
    private Button closeWindowRegister;
    @FXML
    private TextField userNameRegisterUsers;
    @FXML
    private TextField emailRegisterUsers;
    @FXML
    private PasswordField passwordRegisterUsers;
    /**
     * This method is used to check if the information for register entered by the user is correct.
     */
    public void registerUsers(ActionEvent actionEvent) throws SQLException, IOException {
        //Retrieves the information entered.
        String usernameRegister =  userNameRegisterUsers.getText().trim();
        String emailRegister    = emailRegisterUsers.getText().trim();
        String passwordRegister = passwordRegisterUsers.getText().trim();
        //Check if the information is empty.
        if (usernameRegister.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username.");
            alert.showAndWait();
        }else if (emailRegister.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email address.");
            alert.showAndWait();
        }else if (passwordRegister.isEmpty()){
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
            //Return the information from te method registerUsers.
            boolean boolDataBase = registerLogin.registerUsers(usernameRegister,emailRegister,passwordRegister);
            if (boolDataBase){
                //Error Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Register failed : try again.");
                alert.showAndWait();
            }else {
                //Information Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Success registration.");
                alert.showAndWait();

                //loaad file FXM login.
                Parent loadFileFXMLLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
                Scene loginWindowScene = new Scene(loadFileFXMLLogin);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(loginWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
        }
    }
    /**
     * This method is used to close the registration window.
     */
    public void closeWindowRegister(ActionEvent actionEvent) throws IOException {
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
            //Close the register window.
            if (type == okButton) { // do something
                //Get a handle to the stage.
                Stage stage = (Stage) closeWindowRegister.getScene().getWindow();
                //Do what you have to do.
                stage.close();
            }
            //We stay on the register window.
            else if (type == noButton) { // do something
                //Load file FXML for the register.
                Parent loadFileFXMLRegister = null;
                try {
                    loadFileFXMLRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/register.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLRegister != null;
                Scene homeWindowScene = new Scene(loadFileFXMLRegister);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(homeWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
            //We stay on the register window.
            else { // do something
                //Load file FXML for the register.
                Parent loadFileFXMLRegister = null;
                try {
                    loadFileFXMLRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/register.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLRegister != null;
                Scene homeWindowScene = new Scene(loadFileFXMLRegister);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(homeWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
        });
    }
    /*
    *This method displays the login window.
    */
    public void loginUsers(ActionEvent actionEvent) throws IOException {
        //Load file FXML for the login window.
        Parent  loadFileFXMLresetpassword = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
        Scene reSetWindowScene = new Scene(loadFileFXMLresetpassword);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(reSetWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}