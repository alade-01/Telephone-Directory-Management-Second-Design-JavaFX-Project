package model;

import java.sql.*;
/**
 * This class checks information about the registerlogin table.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class RegisterLogin extends ConnectDataBase {
    //Attributes.
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    /**
     * This method is the constructor of the RegisterLogin class.
     */
    public RegisterLogin() {
        //Constructor of the ConnectDataBase class.
        super();
    }
    /**
     * This method checks if the information entered by the user is in the registerlogin table.
     *
     * @param emailLogin This parameter is used to retrieve the user's email
     * @param passwordLogin This parameter is used to recover the user's password
     * @return Here it returns a Object.
     */
    public ResultSet loginUsers(String emailLogin,String passwordLogin) throws SQLException {
        //MyQuery for login information from the registerlogin table.
        String myQuery = "SELECT * FROM registerlogin WHERE email = '" + emailLogin + "' AND  password = '" + passwordLogin + "'";
        preparedStatement = connectDataBase.prepareStatement(myQuery);
        result = preparedStatement.executeQuery();
        //connectDataBase.close();
        return result;
    }
    /**
     * This method inserts the informations in the registerlogin table.
     *
     * @param usersName this parameter retrieves the username  for the user.
     * @param emailUsers this parameter retrieves the email for the user .
     * @param passwordUserS this parameter retrieves the password for the user.
     * @return Here it returns a boolean.
     */
    public boolean registerUsers(String usersName, String emailUsers, String passwordUserS) throws SQLException {
        //MyQuery for the save informations register in the registerlogin table.
        String myQuery = " INSERT INTO registerlogin(username,email,password)" + " VALUES (?, ?, ?)";
        //Create the mysql insert preparedstatement.
        preparedStatement = connectDataBase.prepareStatement(myQuery);
        preparedStatement.setString    (1, usersName);
        preparedStatement.setString    (2, emailUsers);
        preparedStatement.setString    (3, passwordUserS);
        //Execute the preparedstatement
        boolean bool = preparedStatement.execute();
        //Close database.
        connectDataBase.close();
        return bool;
    }
}