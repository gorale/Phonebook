package model;

import model.Enum.EmailType;
import model.Enum.NumberType;



public class Contact {
    private String firstName;
    private String lastName;
    private String company;
    private PhoneNumber phoneNumber;
    private Email email;

    public static final String ANSI_BLUE = "\u001B[34m";


    public Contact(String firstName, String lastName, String company, PhoneNumber phoneNumber, Email email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public PhoneNumber getPhoneNumbers() {
        return phoneNumber;
    }

    public void setPhoneNumbers(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public String toString() {

       return ANSI_BLUE+"Contact\t|" + (firstName.equals("")?"name passing":firstName)  +
                "\t|"+ (lastName.equals("")?"last name passing":lastName) +
                "\t|"+(company.equals("")?"company name passing":company)+"\t|"
                +(phoneNumber==null?"phone number passing":phoneNumber.numberType+": "+phoneNumber.number)+
                "\t|"+(email.email == null?"email passing":email.emailType+": "+email.email);



    }


    public static class PhoneNumber {
        NumberType numberType;
        private String number;

        public PhoneNumber(NumberType numberType, String number) {
            this.numberType = numberType;
            this.number = number;
        }

        public PhoneNumber() {

        }

        public NumberType getNumberType() {
            return numberType;
        }

        public void setNumberType(NumberType numberType) {
            this.numberType = numberType;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
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
         String email;

        public Email(EmailType emailType, String email) {
            this.emailType = emailType;
            this.email = email;
        }

        public EmailType getEmailType() {
            return emailType;
        }

        public void setEmailType(EmailType emailType) {
            this.emailType = emailType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
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
