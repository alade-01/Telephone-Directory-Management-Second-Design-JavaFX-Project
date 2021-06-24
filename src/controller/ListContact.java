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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ListContact implements Initializable {
    /**
     * Definition of global variables.
     */
    public Integer idUsers;
    public String  varLastName;
    @FXML
    private Button printContactOneButton;
    @FXML
    private Button printContactAllButton;

    @FXML
    private  Button previousButton;

    @FXML
    private Button button;
    @FXML
    private Button deleteButton;

    //public TextField name;
    @FXML
    private TextField telContactUser;
    @FXML
    private TextField nameFirstNameContactUser;
    @FXML
    private TextField emailContactUser;
    @FXML
    private TextField sexeContactUser;

    //btn savemodify
    @FXML
    private Button saveModify;
    @FXML
    private Button windowShow;

    @FXML
    private Label label;

    @FXML
    private javafx.scene.control.TableView<Person> TableView;// = new TableView<Person>();
    @FXML
    private TableColumn<Integer, Integer> numberCol;
    @FXML private TableColumn<String, String>  telCol ;
    @FXML private TableColumn<String, String> nameFisrtNamecol;
    @FXML private TableColumn<String, String> emailCol ;
    @FXML private TableColumn<String, String> sexeCol ;

    @FXML TextField addFirstName;
    @FXML TextField addLastName;
    @FXML TextField addEmail;


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

        //table.setSelectionModel().;
        // TODO
        /*
        final ObservableList<Person> data
                = FXCollections.observableArrayList(
                new Person(1, "Jacob", "Smith", "jacob.smith@example.com","homme"),
                new Person(1, "Isabella", "Johnson", "isabella.johnson@example.com","homme"),
                );*/
    }
    /**
     * This method is used for save modification on the data list.
     */
    @FXML
    private void saveModifyContact(ActionEvent actionEvent) throws SQLException, IOException {
        String tel   = telContactUser.getText().trim();
        String nom   = nameFirstNameContactUser.getText().toUpperCase().trim();
        String email = emailContactUser.getText().trim();
        String sexe  = sexeContactUser.getText().toUpperCase().trim();
        //Add variable in the array informationsUser.
        String[] informationsUser = {"+225 "+tel,nom,email,sexe,String.valueOf(idUsers)};
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
            alert.setContentText("Modification de "+varLastName+" identifiant "+idUsers+" réussite.");
            alert.showAndWait();
            //loaad file FXML modifyContact.
            Parent loadFileFXMLmodifyContact = FXMLLoader.load(getClass().getResource("../view/modifyContact.fxml"));
            Scene modifyCpntacttWindowScene = new Scene(loadFileFXMLmodifyContact);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(modifyCpntacttWindowScene);
            app_stage.centerOnScreen();
            app_stage.show();
            /*/
            //Clear TextField from the saveContact window.
            telephoneContact.setText("");
            nomContact.setText("");
            emailContact.setText("");
            sexeContact.setText("");*/
        }else{
            //Error Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Échec de d'enregistrement : réessayer.");
            alert.showAndWait();
        }
       /*
        System.out.println(idUsers);
        System.out.println(tel);
        System.out.println(nom);
        System.out.println(email);
        System.out.println(sexe);
        */
    }
    /**
     *This method allows you to return to the welcome window.
     */
    public void previousButtonLogin(ActionEvent actionEvent) {
        //Load file FXML for the dashboard.
        Parent loadFileFXMLDashboard = null;
        try {
            loadFileFXMLDashboard = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
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

    public void printAllContact(ActionEvent actionEvent) {
        System.out.println("IMPRIMER TOUT LES CONTACTS");
    }

    public void printOneContact(ActionEvent actionEvent) {
        System.out.println("IMPRIMER UN CONTACT");
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
        }*/
    }
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
            */
        }

    }

    /**
     * This method displays the dashboard window.
     */
    /*
    public void windowShowLogin(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setContentText("Êtes-vous sûre de vouloir quitter cette fenêtre ?");
        ButtonType okButton = new ButtonType("oui", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("non", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.setY(230);
        alert.setX(500);
        alert.showAndWait().ifPresent(type -> {
            //Display window login.
            if (type == okButton) { // do something
                //Get a handle to the stage.
                Stage stage = (Stage) windowShow.getScene().getWindow();
                //Do what you have to do.
                stage.close();
            }
            //We stay on the modifyContact window.
            else if (type == noButton) { // do something
                //Load file FXML for the dashboard.
                Parent loadFileFXMLDashboard = null;
                try {
                    loadFileFXMLDashboard = FXMLLoader.load(getClass().getResource("../sample/modifyContact.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLDashboard != null;
                Scene dashboardWindowScene = new Scene(loadFileFXMLDashboard);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //app_stage.hide();
                app_stage.setScene(dashboardWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
            //We stay on the modifyContact window.
            else { // do something
                //Load file FXML for the dashboard.
                Parent loadFileFXMLDashboard = null;
                try {
                    loadFileFXMLDashboard = FXMLLoader.load(getClass().getResource("../sample/modifyContact.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLDashboard != null;
                Scene dashboardIWindowScene = new Scene(loadFileFXMLDashboard);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //app_stage.hide();
                app_stage.setScene(dashboardIWindowScene);
                app_stage.centerOnScreen();
                app_stage.show();
            }
        });
    }
*/
}