package model;

import model.Enum.EmailType;
import model.Enum.NumberType;

public class Contact {
    private String firstName;
    private String lastName;
    private String company;
    private PhoneNumbers phoneNumbers;
    private Email email;

    public Contact(String firstName, String lastName, String company, PhoneNumbers phoneNumbers, Email email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phoneNumbers = phoneNumbers;
        this.email = email;
    }

    public Contact(String firstName, PhoneNumbers phoneNumbers) {
        this.firstName = firstName;
        this.phoneNumbers = phoneNumbers;
    }

    public Contact() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact\t|" + (firstName.equals("")?"name passing":firstName)  +
                "\t|"+ (lastName.equals("")?"last name passing":lastName) +
                "\t|"+(company.equals("")?"company name passing":company)+"\t|"
                +(phoneNumbers.number.equals("")?"phone number passing":phoneNumbers.number)+
                "\t|"+(email.email.equals("")?"email passing":email.email);


    }

    public static class PhoneNumbers {
        NumberType numberType;
        private String number;

        public PhoneNumbers(NumberType numberType, String number) {
            this.numberType = numberType;
            this.number = number;
        }

        @Override
        public String toString() {
            return "PhoneNumbers{" +
                    "numberType=" + numberType +
                    ", number='" + number + '\'' +
                    '}';
        }
    }


    public static class Email {
        EmailType emailType;
        private String email;

        public Email(EmailType emailType, String email) {
            this.emailType = emailType;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Email{" +
                    "emailType=" + emailType +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
