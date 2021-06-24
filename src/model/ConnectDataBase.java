package model;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * This class allows the connection to the database.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class ConnectDataBase {
    //Attributes for the connect database.
    protected Connection connectDataBase;

    //The constructor of the class.
    protected ConnectDataBase(){
        //We try to connect to the database.
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Database connection information.
            String nameDataBase = "miagerepertoire";
            String url = "jdbc:mysql://localhost:3306/" + nameDataBase;
            String user = "root";
            String password = "";
            connectDataBase = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            //Show an error window.
            Alert fail= new Alert(Alert.AlertType.ERROR);
            fail.setHeaderText("Échec");
            fail.setContentText("Erreur lors de la connection à la base de données.");
            fail.showAndWait();
            System.exit(0);
        }
    }
}