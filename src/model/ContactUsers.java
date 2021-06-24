package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class checks information about the contacts_users table.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class ContactUsers extends ConnectDataBase{
    //Attributes.
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Integer answerDataBase;
    /**
     * This method is the constructor of the SaveContact class.
     */
    public ContactUsers(){
        //Constructor of the ConnectDataBase class.
        super();
    }
    /**
     * This method inserts the information into the saveContactTable table.
     *
     *
     * @param userContact this parameter retrieves the username.
     * @param userName this parameter retrieves the email.
     * @param userEmail this parameter retrieves the password.
     * @param userSexe this parameter retrieves the password.
     * @return Here it returns a boolean.
     */
    public boolean saveContactTable(String userContact, String userName, String userEmail, String userSexe) throws SQLException {
        //MyQuery for insert information from the contacts_users table.
        String myQuery = " INSERT INTO contacts_users(telephone,nom_prenoms,email,sexe)" + " VALUES (?, ?, ?, ?)";
        //Create the mysql insert preparedstatement.
        preparedStatement = connectDataBase.prepareStatement(myQuery);
        preparedStatement.setString    (1, userContact);
        preparedStatement.setString    (2, userName);
        preparedStatement.setString    (3, userEmail);
        preparedStatement.setString    (4, userSexe);
        //Execute the preparedstatement.
        boolean bool = preparedStatement.execute();
        //Close DataBase.
        connectDataBase.close();
        return bool;
    }
    /**
     * This method modify the information in the contacts_users table.
     *
     *
     * @param users this parameter Is an array that receives information..
     * @return Here it returns a int.
     */
   public int modifyContactTable(String[] users) throws SQLException {
       //MyQuery for update information from the contacts_users table.
       String query = "UPDATE contacts_users SET telephone = ?, nom_prenoms = ?, email = ?, sexe = ?  WHERE id = ?";
       //Create the java mysql update preparedstatement
       PreparedStatement preparedStmt = connectDataBase.prepareStatement(query);
       preparedStmt.setString   (1, users[0]);
       preparedStmt.setString   (2, users[1]);
       preparedStmt.setString   (3, users[2]);
       preparedStmt.setString   (4, users[3]);
       preparedStmt.setInt      (5, Integer.parseInt(users[4]));
       //Execute the java preparedstatement
       return preparedStmt.executeUpdate();
       //connectDataBase.close();
    }
    /**
     * This method select the information of the contacts_users table.
     *
    */
     public ResultSet selectContactTable() throws SQLException {
            //MyQuery for select information from the contacts_users table.
           String myQuery = "SELECT * FROM contacts_users";
           preparedStatement = connectDataBase.prepareStatement(myQuery);
           result = preparedStatement.executeQuery();
           return result;
     }
     /**
      * * This method removes a contact's information from the contacts_users table.
      *
      * @return
      */
     public int deleteContactTable(Integer idContactUser) throws SQLException {
        // Query to delete the contact information from the users table of the contacts.
            String myQuery = "DELETE FROM contacts_users WHERE id = ?";
         PreparedStatement preparedStatementZ = connectDataBase.prepareStatement(myQuery);
         preparedStatementZ.setInt(1, idContactUser);
         return  preparedStatementZ.executeUpdate();
            //connectDataBase.close();
           //return answerDataBase;
     }
}