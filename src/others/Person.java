package others;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final SimpleIntegerProperty lastNameCol1Number;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty sexeUsers;

    public Person(Integer number, String fName, String lName, String email,String sexe) {
        this.lastNameCol1Number = new SimpleIntegerProperty(number);
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.sexeUsers = new SimpleStringProperty(sexe);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

    public int getLastNameCol1Number() {
        return lastNameCol1Number.get();
    }

    public SimpleIntegerProperty lastNameCol1NumberProperty() {
        return lastNameCol1Number;
    }

    public String getSexeUsers() {
        return sexeUsers.get();
    }

    public SimpleStringProperty sexeUsersProperty() {
        return sexeUsers;
    }
}