package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Service implements CreatReadUpdateDelete{
    Map<String,Contact> mapContact = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    public Contact add(){
        System.out.println("Enter firstname: ");
        String firstName = sc.nextLine();
        System.out.println("Enter lastname: ");
        String lastName = sc.nextLine();
        System.out.println("Enter company: ");
        String company = sc.nextLine();

        System.out.println("Choose phone number type(work,home,mobile,school,other)");
        NumberType numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
        System.out.println("phone number: ");
        String number = sc.nextLine();
        Contact.PhoneNumbers phoneNumbers;
        switch (numberType){
            case WORK:
            case MOBILE:
            case HOME:
            case SCHOOL:
               phoneNumbers = new Contact.PhoneNumbers(numberType,number);
               break;
            default:
                phoneNumbers = new Contact.PhoneNumbers(NumberType.OTHER,number);
        }

        System.out.println("Choose email type(gmail,email,icloud,other)");
        EmailType emailType = EmailType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
        System.out.println("email: ");
        String myEmail = sc.nextLine();
        Contact.Email email;
        switch (emailType){
            case GMAIL:
            case EMAIL:
            case ICLOUD:
                email = new Contact.Email(emailType,myEmail);
                break;
            default:
                email = new Contact.Email(EmailType.OTHER,myEmail);
        }


        Contact contact = new Contact(firstName,lastName,company,phoneNumbers,email);
        mapContact.put(firstName,contact);


        return contact;
    }

}
