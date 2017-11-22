package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Client
{


    private List<String> sendingData = new ArrayList<String>();
    private List<String> receivedData = new ArrayList<String>();
    private CheckData checkData = new CheckData();
    String clientNr;
    String userId;
    String balance;

    public List<String> communicateWithServer(List<String> data)
    {
        System.out.println(data);
        List<String> serverData = new ArrayList<String>();
        //login test
        serverData.addAll(Arrays.asList("0", "a", "192847"));
        return serverData;
    }

//    private boolean textHasCapital(String text)
//    {
//        if(text.equals(text.toLowerCase()))
//            return false;
//        return true;
//    }
//
//    private boolean textHasLower(String text)
//    {
//        if(text.equals(text.toUpperCase()))
//            return false;
//        return true;
//    }
//
//    private boolean textHasSpecial(String text)
//    {
//        if (text.matches(".*[-!@#$,<.>/?;:'%^&*()`~}{|_=+].*") || text.matches("\"") || text.matches("]")) // nie sprawdzane : [ \
//            return true;
//        return false;
//    }
//
//    private boolean textHasNumber(String text)
//    {
//        if (text.matches(".*[0-9].*"))
//            return true;
//        return false;
//    }
//
//    private boolean textHasCharacter(String text)
//    {
//        if (text.matches(".*[a-zA-Z].*"))
//            return true;
//        return false;
//    }
//
//    private boolean checkLoginData(String login, String password)
//    {
//        boolean isCorrect;
//        isCorrect = textHasCapital(login) &&
//                    textHasCapital(password) &&
//                    !textHasSpecial(login) &&
//                    !textHasSpecial(password) &&
//                    textHasNumber(login) &&
//                    textHasNumber(password);
//        return isCorrect;
//    }
//
//    private boolean checkPersonalData(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email)
//    {
//        boolean isNameCorrect;
//        boolean isLastNameCorrect;
//        boolean isPeselCorrecct;
//        boolean isCityCorrect;
//        boolean isStreetCorrect;
//        boolean isZipCodeCorrect;
//        boolean isIDNumberCorrect;
//        boolean isIDSeriesCorrect;
//        boolean isPhoneNumCorrect;
//        boolean isEmialCorrect;
//
//        String idNumOnly;
//        String idSeriesOnly;
//
//        isNameCorrect = !name.isEmpty() &&
//                        !textHasNumber(name) &&
//                        !textHasSpecial(name);
//
//        isLastNameCorrect = !lastName.isEmpty() &&
//                            !textHasNumber(lastName) &&
//                            !textHasSpecial(lastName);
//
//        if(pesel.length()==11)
//        {
//            isPeselCorrecct = !pesel.isEmpty() &&
//                              !textHasSpecial(pesel) &&
//                              !textHasCharacter(pesel);
//        }
//        else
//            isPeselCorrecct = false;
//
//        isCityCorrect = !city.isEmpty() &&
//                        !textHasNumber(city) &&
//                        !textHasSpecial(city);
//
//        isStreetCorrect = textHasNumber(street) &&
//                          !textHasSpecial(street);
//
//        isZipCodeCorrect = !zipCode.isEmpty() &&
//                           !(zipCode.matches(".*[!@#$,<.>/?;:'%^&*()`~}{|_=+].*") || zipCode.matches("\"") || zipCode.matches("]")) &&
//                           !textHasCharacter(zipCode);
//
//        if(idNumber.isEmpty() || idNumber.length() == 9)
//        {
//            isIDNumberCorrect = false;
//            isIDSeriesCorrect = false;
//        }
//        else
//        {   idNumOnly = idNumber.substring(0,2);
//            idSeriesOnly = idNumber.substring(3);
//
//            isIDNumberCorrect = !textHasCharacter(idNumOnly) &&
//                                !textHasSpecial(idNumOnly);
//            isIDSeriesCorrect = !textHasNumber(idSeriesOnly) &&
//                                !textHasLower(idSeriesOnly);
//        }
//
//        isPhoneNumCorrect = !textHasCharacter(phoneNum) &&
//                            !textHasSpecial(phoneNum);
//
//        isEmialCorrect = !email.isEmpty() &&
//                         !(email.matches(".*[!#$,<>/?;:'%^&*()`~}{|_=+].*") || email.matches("\"") || email.matches("]")) &&
//                         email.matches(".*[@.]*.");
//
//        return isCityCorrect && isIDNumberCorrect && isIDSeriesCorrect && isLastNameCorrect && isNameCorrect && isPeselCorrecct && isPhoneNumCorrect && isStreetCorrect && isZipCodeCorrect && isEmialCorrect;
//    }

    public int login(String login, String password)
    {
        int errorCode = -1;

        if (!checkData.checkLoginData(login, password))
        {
            errorCode = -2;
            return errorCode;
        }

        //Putting everything in to a list S
        sendingData.addAll(Arrays.asList(login, password));

        //encoding the list S of data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

        // sending and receiving data to/from main server, interpreting received data all in thread
        receivedData = communicateWithServer(sendingData);

        //decoding data
        //TO DO

        System.out.println(receivedData);

        if(receivedData.get(0).equals("0"))
        {
            if (receivedData.get(1).equals("c"))// ok and client
            {
                balance = receivedData.get(2);
                errorCode = 0;
            }
            else if (receivedData.get(1).equals("a"))// ok and admin
                errorCode = 1;

            userId = receivedData.get(2);
        }
        else if(receivedData.get(0).equals("1"))// sht wrong
            errorCode=-2;

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode;
    }

    public int register(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email)
    {
        int errorCode = -1;

        if (!checkData.checkPersonalData(name, lastName, pesel, city, street, zipCode, idNumber, phoneNum, email))
        {
            errorCode = -3;
            return errorCode;
        }

        //Putting everything in to a list S
        sendingData.addAll(Arrays.asList(name, lastName, pesel, city, street, zipCode, idNumber, phoneNum, email));

        //encoding the list S of data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

        // sending and receiving data to/from main server, interpreting received data all in thread
        receivedData = communicateWithServer(sendingData);

        System.out.println(receivedData);

        if(receivedData.get(0).equals("0"))
            errorCode = 0;
        else if(receivedData.get(0).equals("1"))
            errorCode=-2;

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode; // only to tests
    }
}
