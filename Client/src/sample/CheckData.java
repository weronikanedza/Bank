package sample;

public class CheckData
{

    private boolean textHasCapital(String text)
    {
        if(text.equals(text.toLowerCase()))
            return false;
        return true;
    }

    private boolean textHasLower(String text)
    {
        if(text.equals(text.toUpperCase()))
            return false;
        return true;
    }

    private boolean textHasSpecial(String text)
    {
        if (text.matches(".*[-!@#$,<.>/?;:'%^&*()`~}{|_=+].*") || text.matches("\"") || text.matches("]")) // nie sprawdzane : [ \
            return true;
        return false;
    }

    private boolean textHasNumber(String text)
    {
        if (text.matches(".*[0-9].*"))
            return true;
        return false;
    }

    private boolean textHasCharacter(String text)
    {
        if (text.matches(".*[a-zA-Z].*"))
            return true;
        return false;
    }

    public boolean checkLoginData(String login, String password)
    {
        boolean isCorrect;
        isCorrect = textHasCapital(login) &&
                textHasCapital(password) &&
                !textHasSpecial(login) &&
                !textHasSpecial(password) &&
                textHasNumber(login) &&
                textHasNumber(password);
        return isCorrect;
    }

    public boolean checkPersonalData(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email)
    {
        boolean isNameCorrect;
        boolean isLastNameCorrect;
        boolean isPeselCorrecct;
        boolean isCityCorrect;
        boolean isStreetCorrect;
        boolean isZipCodeCorrect;
        boolean isIDNumberCorrect;
        boolean isIDSeriesCorrect;
        boolean isPhoneNumCorrect;
        boolean isEmialCorrect;

        String idNumOnly;
        String idSeriesOnly;

        isNameCorrect = !name.isEmpty() &&
                !textHasNumber(name) &&
                !textHasSpecial(name);

        isLastNameCorrect = !lastName.isEmpty() &&
                !textHasNumber(lastName) &&
                !textHasSpecial(lastName);

        if(pesel.length()==11)
        {
            isPeselCorrecct = !pesel.isEmpty() &&
                    !textHasSpecial(pesel) &&
                    !textHasCharacter(pesel);
        }
        else
            isPeselCorrecct = false;

        isCityCorrect = !city.isEmpty() &&
                !textHasNumber(city) &&
                !textHasSpecial(city);

        isStreetCorrect = textHasNumber(street) &&
                !textHasSpecial(street);

        isZipCodeCorrect = !zipCode.isEmpty() &&
                !(zipCode.matches(".*[!@#$,<.>/?;:'%^&*()`~}{|_=+].*") || zipCode.matches("\"") || zipCode.matches("]")) &&
                !textHasCharacter(zipCode);

        if(idNumber.isEmpty() || idNumber.length() == 9)
        {
            isIDNumberCorrect = false;
            isIDSeriesCorrect = false;
        }
        else
        {   idNumOnly = idNumber.substring(0,2);
            idSeriesOnly = idNumber.substring(3);

            isIDNumberCorrect = !textHasCharacter(idNumOnly) &&
                    !textHasSpecial(idNumOnly);
            isIDSeriesCorrect = !textHasNumber(idSeriesOnly) &&
                    !textHasLower(idSeriesOnly);
        }

        isPhoneNumCorrect = !textHasCharacter(phoneNum) &&
                !textHasSpecial(phoneNum);

        isEmialCorrect = !email.isEmpty() &&
                !(email.matches(".*[!#$,<>/?;:'%^&*()`~}{|_=+].*") || email.matches("\"") || email.matches("]")) &&
                email.matches(".*[@.]*.");

        return isCityCorrect && isIDNumberCorrect && isIDSeriesCorrect && isLastNameCorrect && isNameCorrect && isPeselCorrecct && isPhoneNumCorrect && isStreetCorrect && isZipCodeCorrect && isEmialCorrect;
    }

}
