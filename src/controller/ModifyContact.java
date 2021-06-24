package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ContactUsers;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifyContact implements Initializable {
    String homme;
    String femme;
    boolean bool;

    String userSexeSave;

    public CheckBox chexBoxHomme;
    public CheckBox chexBoxFemme;
    /**
     * Definition of global variables.
     */
    public Integer idUsers;
    public String  varLastName;
    @FXML
    private  Button previousButtonWindowModifyContact;
    @FXML
    private Button button;
    @FXML
    private Button deleteButton;
    @FXML
    private Button modify;
    //public TextField name;
    @FXML
    private TextField telContactUser;
    @FXML
    private TextField nameFirstNameContactUser;
    @FXML
    private TextField emailContactUser;
    @FXML
    private TextField sexeContactUser;
    @FXML
    private Button saveModify;
    @FXML
    private Label label;
    @FXML
    private TableView<Person> TableView; // = new TableView<Person>();
    @FXML
    private TableColumn<Integer, Integer> numberCol;
    @FXML private TableColumn<String, String>  telCol ;
    @FXML private TableColumn<String, String> nameFisrtNamecol;
    @FXML private TableColumn<String, String> emailCol ;
    @FXML private TableColumn<String, String> sexeCol ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Instance the class ContactUsers.
        ContactUsers contactUsers = new ContactUsers();
        try {
            ResultSet answerDataBase = contactUsers.selectContactTable();
            //Create a ObservableList.
            final ObservableList<Person> data = FXCollections.observableArrayList();

            //Extract the data form the contacts_users table.
            while(answerDataBase.next()){
                //Retrieve by column name.
                int id              = answerDataBase.getInt("id");
                String telephone    = answerDataBase.getString("telephone");
                String nom_prenoms  = answerDataBase.getString("nom_prenoms");
                String email        = answerDataBase.getString("email");
                String sexe         = answerDataBase.getString("sexe");

                //Add content in the Observablelist.
                data.add(new Person(id,telephone,nom_prenoms,email,sexe));
            }
            //Column for the data list.
            numberCol.setMinWidth(50);
            telCol.setMinWidth(100);
            nameFisrtNamecol.setMinWidth(100);
            emailCol.setMinWidth(200);
            sexeCol.setMinWidth(50);
            //Save data in the TableView.
            TableView.getItems().setAll(data);
            //Multiple select in the TableView.
            TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            //Close DataBase.
            answerDataBase.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * This method allows you to select the male check box.
     */
    public void chexBoxHommeMethod(ActionEvent actionEvent) {
        if(chexBoxHomme.isSelected()){
            chexBoxFemme.setSelected(false);
            homme = "Homme";
            bool = true;
        }
    }
    /**
     * This method allows you to select the woman check box.
     */
    public void chexBoxFemmeMethod(ActionEvent actionEvent) {
        if(chexBoxFemme.isSelected()){
            chexBoxHomme.setSelected(false);
            femme = "Femme";
            bool = false;
        }
    }
    /**
     * This method is used for save modification on the data list.
     */
    @FXML
    private void saveModifyContact(ActionEvent actionEvent) throws SQLException, IOException {
        // check the table's selected item and get selected item.
        if (TableView.getSelectionModel().getSelectedItem() != null) {
            String tel   = telContactUser.getText().trim();
            String nom   = nameFirstNameContactUser.getText().toUpperCase().trim();
            String email = emailContactUser.getText().toLowerCase().trim();
            //Checking the boolean value.
            if (bool){
                userSexeSave    = homme.toUpperCase();
            }else{
                userSexeSave    = femme.toUpperCase();
            }
            //String sexe  = sexeContactUser.getText().toUpperCase().trim();

            Person selectedPerson = TableView.getSelectionModel().getSelectedItem();

            //Global variable.
            idUsers = selectedPerson.getLastNameCol1Number();
            //Add variable in the array informationsUser.
            String[] informationsUser = {tel,nom,email,userSexeSave,String.valueOf(idUsers)};
            //Instance the class ContactUsers.
            ContactUsers contactUers = new ContactUsers();
            //Return the information from te method modifyContactTable.
            int answerDataBase = contactUers.modifyContactTable(informationsUser);
            //Check on the answer.
            if (answerDataBase==1){
                //Information Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Modification of "+varLastName+" identifier "+idUsers+" success.");
                alert.showAndWait();
                //loaad file FXML modifyContact.
                Parent loadFileFXMLModifyContact = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/modifyContact.fxml")));
                Scene modifyCpntacttWindowScene = new Scene(loadFileFXMLModifyContact);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(modifyCpntacttWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            /*
            //Clear TextField from the saveContact window.
            telephoneContact.setText("");
            nomContact.setText("");
            emailContact.setText("");
            sexeContact.setText("");*/
            }else{
                //Error Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Failed to save : try again.");
                alert.showAndWait();
            }

        }else{
            //Error Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Failed : Please select a contact and press edit.");
            alert.showAndWait();
        }
    }
    /**
     * This method is used to get information about the modification of the selected contact.
     */
    public void modifyContact(ActionEvent actionEvent) throws SQLException {
        // check the table's selected item and get selected item.
        if (TableView.getSelectionModel().getSelectedItem() != null) {

            Person selectedPerson = TableView.getSelectionModel().getSelectedItem();

            //Global variable.
            idUsers = selectedPerson.getLastNameCol1Number();
            varLastName = selectedPerson.getNameFirstName();

            //Retrieved information about the selected item.
            String telUsers = selectedPerson.getTel();
            String emailUsers= selectedPerson.getEmail();
            String sexeUsers= selectedPerson.getSexeUsers();
            //Verification of the sexUsers.
            if (sexeUsers.equals("HOMME")){
                chexBoxHomme.setSelected(true);
                chexBoxFemme.setSelected(false);
            }else if(sexeUsers.equals("FEMME")){
                chexBoxFemme.setSelected(true);
                chexBoxHomme.setSelected(false);
            }
            //Display the informations about the modification of the selected contact int he TextField.
            telContactUser.setText(String.valueOf(telUsers));
            nameFirstNameContactUser.setText(varLastName);
            emailContactUser.setText(emailUsers);
            //sexeContactUser.setText(sexeUsers);

        }else{
            //Error Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Failed : Please select a contact and press edit.");
            alert.showAndWait();
        }
    }
    /**
     * Creation of the person class.
     */
    public static class Person {
        //Declaration of attributes.
        private final SimpleIntegerProperty numberId;
        private final SimpleStringProperty tel;
        private final SimpleStringProperty nameFirstName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty sexeUsers;
        /**
         * This method is the constructor of the Person class.
         *
         * @param numberUser this parameter retrieves the username  for the user.
         * @param telephoneUser this parameter retrieves the contact for the user .
         * @param nameFirstNameUser this parameter retrieves the name and firstName for the user.
         * @param emailUser this parameter retrieves the name and emailfor the user.
         * @param sexeUser this parameter retrieves the name and sexe for the user.
         */
        private Person(Integer numberUser, String telephoneUser, String nameFirstNameUser, String emailUser,String sexeUser) {
            this.numberId           = new SimpleIntegerProperty(numberUser);
            this.tel                = new SimpleStringProperty(telephoneUser);
            this.nameFirstName      = new SimpleStringProperty(nameFirstNameUser);
            this.email              = new SimpleStringProperty(emailUser);
            this.sexeUsers          = new SimpleStringProperty(sexeUser);
        }

        public String getTel() {
            return tel.get();
        }

        public void setTel(String fName) {
            tel.set(String.valueOf(Integer.parseInt(fName)));
        }

        public String getNameFirstName() {
            return nameFirstName.get();
        }

        public void setnNmeFirstName(String fName) {
            nameFirstName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }

        public int getLastNameCol1Number() {
            return numberId.get();
        }

        public SimpleIntegerProperty lastNameCol1NumberProperty() {
            return numberId;
        }

        public String getSexeUsers() {
            return sexeUsers.get();
        }

        public SimpleStringProperty sexeUsersProperty() {
            return sexeUsers;
        }
    }
    /**
     *This method allows you to return to the dashboard window.
     */
    public void previousWindowModifyContact(ActionEvent actionEvent) {
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
    /*
    //delete row
    @FXML
    private void handleButtonAction(ActionEvent event) {
        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
    }
    //add row
    @FXML
    private void addButtonClicked() {
        if (!(addFirstName.getText() == null || "".equals(addFirstName.getText().trim()))){
            TableView.getItems().add( new Person(1,addFirstName.getText(), "Smith", "jacob.smith@example.com","homme"));
            addFirstName.clear();
        }
    }

    @FXML
    private void modifyButtonClicked() {
        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
        TableView.getItems().add( new Person(1, addFirstName.getText(), "Smith", "jacob.smith@example.com","homme"));

        /*
        if (!(addFirstName.getText() == null || "".equals(addFirstName.getText().trim()))){
            table.getItems().add( new Person(addFirstName.getText(), "Smith", "jacob.smith@example.com"));
            addFirstName.clear();
        }
    }*/
    /*
    //edit row
    public void onEdit() {
        // check the table's selected item and get selected item
        if (TableView.getSelectionModel().getSelectedItem() != null) {
            Person selectedPerson = TableView.getSelectionModel().getSelectedItem();
            String var1 = selectedPerson.getTel();
            String var2 = selectedPerson.getNameFirstName();
            String var3 = selectedPerson.getEmail();

                /*
                nameTextField.setText(selectedPerson.getName());
                addressTextField.setText(selectedPerson.getAddress());


    }*/
}