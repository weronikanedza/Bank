package Base;

import java.io.Serializable;

public class AddAccountRequest extends PersonalData
        implements Serializable {
    public String id_request;

    public AddAccountRequest(String id_request,String firstName, String lastName, String street, String zipCode, String city, String pesel,
                             String idNumber, String email, String phoneNumber) {
        super(firstName, lastName, street, zipCode, city, pesel, idNumber, email, phoneNumber);
        this.id_request=id_request;
    }
    public AddAccountRequest() {

    }

    @Override
    public String toString() {
        return "AddAccountRequest [id_request=" + id_request + ", firstName=" + firstName + ", lastName=" + lastName
                + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city + ", pesel=" + pesel
                + ", idNumber=" + idNumber + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

}
