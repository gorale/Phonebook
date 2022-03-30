package validation;

public class ContactValidation {
    private String firstName;

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

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (((phoneNumber.charAt(i)>='0' && phoneNumber.charAt(i)<='9') ||
                    phoneNumber.charAt(0) == '+' ||
                    phoneNumber.charAt(i) == '#') &&
                    (phoneNumber.length()>=3 && phoneNumber.length()<=16)) {
                return true;
            } else
                return false;

        }
        return false;
    }
    public boolean isValidEmail(String email){

        return email.contains("@gmail.com") ||
                email.contains("@mail.ru") ||
                email.contains("@icloud.com");

    }



}
