package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;
import validation.ContactValidation;

import java.util.*;

public class Service implements CreateReadUpdateDelete {

    Map<UUID, Contact> mapContact = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    String firstName;
    String lastName;
    String company;
    String myEmail;
    String number;

    public static String answer() {

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (answer) {
            case "YES":
                System.out.println("Enter field ");
                return sc.nextLine();
            case "NO":
                break;
        }
        return "";
    }

    @Override
    public Map<UUID, Contact> add() {
        UUID uuid = UUID.randomUUID();
        ContactValidation cn = new ContactValidation();
        System.out.println("Enter firstname");
        while (true) {
            firstName = sc.nextLine();

            if (cn.isValidFirstName(firstName)) {
                break;
            } else {
                System.out.println("Enter valid firstname");
            }
        }


        System.out.println("Can you enter lastname: ");
        lastName = answer();
        System.out.println("Can you enter company name: ");
        company = answer();
        System.out.println("Choose phone number type(work,home,mobile,school,other)");
        NumberType numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
        System.out.println("phone number: ");

        while (true) {
            number = sc.nextLine();

            if (cn.isValidPhoneNumber(number)) {
                break;

            } else {
                System.out.println("Enter valid phone number");
            }
        }

        Contact.PhoneNumbers phoneNumbers;
        switch (numberType) {
            case WORK:
            case MOBILE:
            case HOME:
            case SCHOOL:
                phoneNumbers = new Contact.PhoneNumbers(numberType, number);
                break;
            default:
                phoneNumbers = new Contact.PhoneNumbers(NumberType.OTHER, number);
        }

        System.out.println("Choose email type(gmail,email,icloud,other)");
        EmailType emailType = EmailType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
        System.out.println("email: ");


        while (true) {
            String myEmail = sc.nextLine();
            if (cn.isValidEmail(myEmail,emailType)) {
                break;
            } else
                System.out.println("Enter valid email");
        }
        Contact.Email email;
        switch (emailType) {
            case GMAIL:
            case EMAIL:
            case ICLOUD:
                email = new Contact.Email(emailType, myEmail);
                break;
            default:
                email = new Contact.Email(EmailType.OTHER, myEmail);
        }


        Contact contact = new Contact(firstName, lastName, company, phoneNumbers, email);
        mapContact.put(uuid, contact);


        return mapContact;
    }

    @Override
    public String getAll(){
        for (Contact item : mapContact.values()){
            System.out.println(item.getFirstName()+"\n");
        }
        return "";
    }

    @Override
    public void get(String firstName){
        for (Map.Entry<UUID,Contact> map : mapContact.entrySet()){
            if(map.getValue().getFirstName().equals(firstName)){
            System.out.println(map);
            }
        }

            System.out.println("can't find user with "+firstName+" name");

    }



}
