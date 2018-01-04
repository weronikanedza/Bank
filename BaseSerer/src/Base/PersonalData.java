package Base;

import java.io.Serializable;

public class PersonalData implements Serializable {
    public String firstName;
    public String lastName;
    public String street;
    public String zipCode;
    public String city;
    public String pesel;
    public String idNumber;
    public String email;
    public String phoneNumber;


    public PersonalData(String firstName, String lastName, String street, String zipCode, String city, String pesel,
                        String idNumber, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PersonalData() {

    }



    @Override
    public String toString() {
        return "PersonalData [firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", zipCode="
                + zipCode + ", city=" + city + ", pesel=" + pesel + ", idNumber=" + idNumber + ", email=" + email
                + ", phoneNumber=" + phoneNumber + "]";
    }


}
