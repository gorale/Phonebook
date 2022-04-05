package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;
import validation.ContactValidation;

import java.util.*;

public class ServicePhoneBook implements CreateReadUpdateDelete {

    Map<UUID, Contact> mapContact = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ContactValidation cn = new ContactValidation();

    private UUID uuid;


    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static boolean question() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to give out someone else's information?" + '\n'
                + "-YES" + '\n' + "-NO");
        String question = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (question) {
            case "YES":
                return true;
            case "NO":
                break;
            default:
                question();

        }
        return false;
    }

    public static String answer() {

        Scanner sc = new Scanner(System.in);

        System.out.println(ANSI_YELLOW + "Choose answer:" + '\n'
                + "-YES" + '\n' + "-NO");
        String answer = sc.nextLine().toUpperCase(Locale.ROOT);


        switch (answer) {
            case "YES":
                System.out.print("Enter field: ");
                return sc.nextLine();


            case "NO":
                break;
            default:
                answer();
        }

        return "";
    }

    public String insertFirstName() {
        String firstName;
        System.out.print(ANSI_YELLOW + "Enter firstname: ");
        while (true) {
            firstName = sc.nextLine();

            if (cn.isValidFirstName(firstName)) {
                break;
            } else {
                System.out.print(ANSI_RED + "Enter valid firstname: ");
            }
        }
        return firstName;

    }

    public String insertLastName() {
        String lastName;

        //   System.out.print(ANSI_YELLOW + "Enter lastname: ");
        lastName = answer();
        while (true) {
            lastName = sc.nextLine();

            if (cn.isValidLastName(lastName)) {
                break;
            } else {
                System.out.print(ANSI_RED + "Enter valid lastname: ");
            }
        }
        return lastName;

    }


    public NumberType insertPhoneNumberType() {
        NumberType numberType;

        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Choose phone number type-" + '\n' + "-MOBILE" + '\n' +
                        "-HOME" + '\n' +
                        "-SCHOOL" + '\n' +
                        "-WORK");
                System.out.print("Enter your choose: ");
                return numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                System.out.print(ANSI_RED + "Enter valid type: " + ANSI_YELLOW);
                continue;


            }

        }
    }


    @Override
    public Contact add() {
        //  String firstName = "";
        String myEmail;
        String number;
        uuid = UUID.randomUUID();
        // NumberType numberType;
        Contact.PhoneNumbers phoneNumbers;
        EmailType emailType;
        Contact.Email email;
        ContactValidation cn = new ContactValidation();
        String firstName = insertFirstName();

        // System.out.println(ANSI_YELLOW + "Do you want to enter lastname?: ");
        String lastName;
        lastName = insertLastName();
        System.out.println(ANSI_YELLOW + "Do you want to enter company name?: ");
        String company = "";
        NumberType numberType = insertPhoneNumberType();
        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Choose phone number type-" + '\n' + "-MOBILE" + '\n' +
                        "-HOME" + '\n' +
                        "-SCHOOL" + '\n' +
                        "-WORK");
                System.out.print("Enter your choose: ");

                numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                System.out.print(ANSI_RED + "Enter valid type: ");
                continue;


            }

        }
    /*
        public String insertNumber () {
            System.out.print("Enter phone number: ");

            while (true) {
                number = sc.nextLine();

                if (cn.isValidPhoneNumber(number)) {
                    break;

                } else {
                    System.out.print(ANSI_RED + "Enter valid phone number: ");
                }
            }
        }

     */


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


        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Choose email type-" + '\n' +
                        "-GMAIL" + '\n' +
                        "-EMAIL" + '\n' +
                        "-ICLOUD" + '\n' +
                        "-OTHER");
                System.out.print("Enter your choose: ");
                emailType = EmailType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                System.out.print(ANSI_RED + "Enter valid type: ");
                continue;


            }


            System.out.print("Your Email: ");


            while (true) {
                myEmail = sc.nextLine();

                if (cn.isValidEmail(myEmail, emailType)) {
                    break;
                } else
                    System.out.print(ANSI_RED + "Enter valid email: ");
            }
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




    @Override
    public String getAll() {

        List<String> firstNameList = new ArrayList<>();
        if (mapContact.size() == 0) {
            System.out.println("no contacts");
            return "";
        }
        for (Contact item : mapContact.values()) {
            firstNameList.add(item.getFirstName());

        }
        Collections.sort(firstNameList);
        for (String item : firstNameList) {
            System.out.println(item);
        }
        return "";
    }

    @Override
    public void get(String firstName) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getFirstName().equals(firstName)) {
                count++;
                System.out.println(map.getValue());
            }
        }
        if (count == 0) {
            System.out.println("Can't find this user");
        }


    }

    @Override
    public void delete() {
        System.out.println(getAll());
        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n');
        System.out.print("Enter your choose: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                ServiceController.deletebyfirstname();
                break;

            case "2":
                ServiceController.deletebyLastName();
                break;
            case "3":
                ServiceController.deletebyCompanyName();
                break;
            case "4":
                ServiceController.deletebyPhoneNumber();
                break;
            default:
                delete();
        }

    }@Override
    public void update() {

        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n' +
                "5:Email " + '\n' +
                "6:Exit ");
        System.out.print("Your choose: ");
        String answertype = sc.nextLine();
        String inputname;


        switch (answertype) {
            case "1":
               ServiceController.updateByFirstName();
               break;
            case "2":
                ServiceController.updateByLastName();
                break;
            case "3":
                ServiceController.updateByCompanyName();
                break;
            case "4":
                ServiceController.updateByPhoneNumber();
                break;
            case "5":
                ServiceController.updateByEmail();
                break;
            case "6":
                break;
            default:
                update();
        }

    }

    @Override
    public void exit() {
        System.exit(0);
    }


}

















