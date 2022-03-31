package validation;

import model.Contact;
import model.Enum.EmailType;

import java.util.regex.Pattern;

public class ContactValidation {

    private static final Pattern PATTERN_GLOBAL_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");


    public boolean isValidFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            return false;
        }
        return (firstName.length() >= 3 && firstName.length() <= 20);
    }

    public boolean isValidLastName(String lastName) {
        if (lastName == null || lastName.length() == 0) {
            return false;
        }
        return (lastName.length() >= 3 && lastName.length() <= 20);
    }

    public boolean isValidItCompanyName(String company) {
        if (company == null || company.length() == 0) {
            return false;
        }
        return (company.length() >= 3 && company.length() <= 20);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() == 0) {
            return false;
        }
        return phoneNumber.matches("[0-9]+");
    }

    public boolean isValidEmail(String email, EmailType emailType) {

        final Pattern PATTERN_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@mail.ru");
        final Pattern PATTERN_GMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@gmail.com");
        final Pattern PATTERN_ICLOUD = Pattern.compile("^[A-Za-z0-9+_.-]+@icloud.com");

        if (emailType == EmailType.GMAIL){
            return email.matches(PATTERN_GMAIL.pattern());
        }
        if(emailType == EmailType.EMAIL){
            return email.matches(PATTERN_EMAIL.pattern());
        }
        if(emailType == EmailType.ICLOUD){
            return email.matches(PATTERN_ICLOUD.pattern());
        }

        return email.matches(PATTERN_GLOBAL_EMAIL.pattern());
    }


}
