package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;
import validation.ContactValidation;

import java.util.*;

public class Service  implements CreateReadUpdateDelete {

    Map<UUID, Contact> mapContact = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    String firstName;
    String lastName;
    String company;
    String myEmail;
    String number;
    UUID uuid;
    NumberType numberType;
    Contact.PhoneNumbers phoneNumbers;
    EmailType emailType;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";




    public static Boolean question(){
        String question = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Do you want to give out someone else's information?" + '\n'
                        + "-YES" + '\n' + "-NO");
                question = sc.nextLine().toUpperCase(Locale.ROOT);

            } catch (IllegalArgumentException e) {

                continue;
            }


            switch (question) {
                case "YES":

                    return true;
                case "NO":
                    break;
            }
            break;
        }
        return false;
    }

    public static String answer() {
        String answer = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Choose answer:" + '\n'
                        + "-YES" + '\n' + "-NO");
                answer = sc.nextLine().toUpperCase(Locale.ROOT);

            } catch (IllegalArgumentException e) {

                continue;
            }


            switch (answer) {
                case "YES":
                    System.out.println("Enter field ");
                    return sc.nextLine();
                case "NO":
                    break;
                default:
                    answer();
            }
            break;
        }
        return "";
    }

    @Override
    public Contact add() {
        uuid = UUID.randomUUID();
        ContactValidation cn = new ContactValidation();
        System.out.println("Enter firstname");
        while (true) {
            firstName = sc.nextLine();

            if (cn.isValidFirstName(firstName)) {
                break;
            } else {
                System.out.println(ANSI_RED +"Enter valid firstname");
            }
        }


        System.out.println("Do you want to enter lastname?: ");
        lastName = answer();
        System.out.println("Do you want to enter company name?: ");
        company = answer();
        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Choose phone number type-" + '\n' + "-MOBILE" + '\n' +
                        "-HOME" + '\n' +
                        "-SCHOOL" + '\n' +
                        "-WORK");
                numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                System.out.println(ANSI_RED + "Enter valid type");
                continue;


            }

            System.out.println("Enter phone number");


            while (true) {
                number = sc.nextLine();

                if (cn.isValidPhoneNumber(number)) {
                    break;

                } else {
                    System.out.println(ANSI_RED + "Enter valid phone number");
                }


            //Contact.PhoneNumbers phoneNumbers;
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
        }

            while (true) {
                try {
                    System.out.println(ANSI_YELLOW +"Choose email type-" + '\n' +
                            "-GMAIL" + '\n' +
                            "-MAIL" + '\n' +
                            "-ICLOUD" + '\n' +
                            "-OTHER");
                    emailType = EmailType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
                    System.out.println("email: ");
                } catch (IllegalArgumentException e) {
                    System.out.println(ANSI_RED +"Enter valid type");
                    continue;
                }


                String myEmail = sc.nextLine();
                while (true) {

                    if (cn.isValidEmail(myEmail, emailType)) {
                        break;
                    } else
                        System.out.println(ANSI_RED +"Enter valid email");
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


                return contact;
            }
        }
    }

        @Override
        public String getAll () {

            List<String> firstNameList = new ArrayList<>();
            for (Contact item : mapContact.values()) {
            firstNameList.add(item.getFirstName());

            }
            Collections.sort(firstNameList);
            for(String item: firstNameList){
                System.out.println(item);
            }
            return "";
        }

    @Override
    public void get(String firstName){
        int count = 0;
        for (Map.Entry<UUID,Contact> map : mapContact.entrySet()){
            if(map.getValue().getFirstName().equals(firstName)){
                count++;
            System.out.println(map.getValue());
            }
        }
        if(count == 0){
            System.out.println("Can't find this user");
        }


    }


    }

