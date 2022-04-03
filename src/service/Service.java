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
    UUID uuid;
    NumberType numberType;
    Contact.PhoneNumbers phoneNumbers;
    EmailType emailType;
    Contact.Email email;


    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    public static boolean question() {
        String question;
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
                    default:
                        question();

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
                System.out.println("Choose answer. . ." + '\n'
                        + "-YES" + '\n' + "-NO");
                System.out.print("your choose: ");
                answer = sc.nextLine().toUpperCase(Locale.ROOT);

            } catch (IllegalArgumentException e) {

                continue;
            }


            switch (answer) {
                case "YES":
                    System.out.print("Enter field: ");
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
        System.out.print("Enter firstname: ");
        while (true) {
            firstName = sc.nextLine();

            if (cn.isValidFirstName(firstName)) {
                break;
            } else {
                System.out.println(ANSI_RED + "Enter valid firstname");
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
                System.out.print("your choose: ");
                numberType = NumberType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                System.out.println(ANSI_RED + "Enter valid type");
                continue;


            }

            System.out.print("Enter phone number: ");

            while (true) {
                number = sc.nextLine();

                if (cn.isValidPhoneNumber(number)) {
                    break;

                } else {
                    System.out.println(ANSI_RED + "Enter valid phone number");
                }
            }

//            Contact.PhoneNumbers phoneNumbers = null;

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
                    System.out.print("your choose: ");
                    emailType = EmailType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException e) {
                    System.out.println(ANSI_RED + "Enter valid type");
                    continue;


                }

                System.out.print("Your Mail: ");

//            String myEmail;
                while (true) {
                    myEmail = sc.nextLine();

                    if (cn.isValidEmail(myEmail, emailType)) {
                        break;
                    } else
                        System.out.println(ANSI_RED + "Enter valid email");
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
    public String getAll() {

        List<String> firstNameList = new ArrayList<>();

        if (mapContact.size() == 0) {
            System.out.println("no contacts");
            return null;
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

    public void deleteFirstName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getFirstName().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }
    public void deleteLastName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getLastName().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }

    public void createListToDeleteFirstName(String inputfirstname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }
    public void createListToDeleteLastName(String inputlastname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        createLastnameList(list1, inputlastname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }


    @Override
    public void delete() {
        System.out.println(getAll());
        String inputname;
        int count;
        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n' +
                "5:Email ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter First Name");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if(count==0){
                    System.out.println(ANSI_RED+"Can't find user, enter other field"+ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deleteFirstName(inputname);
                } else  {
                    createListToDeleteFirstName(inputname);
                }
                break;

            case "2":
                System.out.println("Enter Last Name");
                inputname = sc.nextLine();
                count = getCountLastname(mapContact, inputname);
                if(count==0){
                    System.out.println(ANSI_RED+"Can't find user, enter other field"+ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deleteLastName(inputname);
                } else  {
                    createListToDeleteLastName(inputname);
                }
                break;
            case "3":
            case "4":
            case "5":
        }
      /*  System.out.println(getAll());
        System.out.println("Enter firstname");
        String ph = sc.nextLine();
        Contact contact = new Contact();


     // mapContact.containsValue(ph);

      /*  if(ph.equals(contact.getPhoneNumbers()))
                mapContact.remove(ph);
            }
        }
*/

        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getFirstName().equals(ph)) {
                mapContact.remove(map.getKey());
                break;


            }


        }*/
    }

    public ArrayList<Contact> create(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {

            if (map.getValue().getFirstName().equals(s)) {
                list.add(map.getValue());
            }
        }
        return list;
    }
    public ArrayList<Contact> createLastnameList(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {

            if (map.getValue().getLastName().equals(s)) {
                list.add(map.getValue());
            }
        }
        return list;
    }

    public void display(ArrayList<Contact> list) {
        for (Contact contact : list) {
            System.out.println(1 + list.indexOf(contact) + " " + contact);
        }
    }

    public void changeFirstName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getFirstName().equals(s)) {

                System.out.println("Enter new name");
                String newname = sc.nextLine();

                map1.getValue().setFirstName(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeLastName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {

                System.out.println("Enter new name");
                String newname = sc.nextLine();

                map1.getValue().setLastName(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {

                System.out.println("Enter new name");
                String newname = sc.nextLine();

                map1.getValue().setCompany(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {


                System.out.println("Enter new phone number");
                number = sc.nextLine();

                map1.getValue().getPhoneNumbers().setNumber(number);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeEmail(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getEmail().getEmail().equals(s)) {


                System.out.println("Enter new email");
                String email = sc.nextLine();

                map1.getValue().getEmail().setEmail(email);
            }
            System.out.println(mapContact.values());
        }

    }


    public void createListToChangeFirstName(String inputfirstname) {
        int index;

        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);


        System.out.print("which contact want you change, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new name");
        String name = sc.nextLine();

        list1.get(index).setFirstName(name);

        System.out.println(mapContact.values());


    }

    public void createListToChangePhoneNumber(String inputfirstname) {
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);


        System.out.print("which contact want you change, enter number: ");
        int index = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new phone number");
        String number = sc.nextLine();
        list1.get(index).getPhoneNumbers().setNumber(number);


        System.out.println(mapContact.values());


    }


    public void createListToChangeLastName(String inputLastname) {
        int index = 0;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputLastname);
        display(list1);
        System.out.print("which contact want you change, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new name");
        String name = sc.nextLine();

        list1.get(index - 1).setLastName((name));
        System.out.println(mapContact);
    }

    public void createListToChangeCompanyName(String inputfirstname) {
        int index;

        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);


        System.out.print("which contact want you change, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new name");
        String name = sc.nextLine();

        list1.get(index).setCompany(name);

        System.out.println(mapContact.values());


    }

    public void createListToChangeEmail(String inputLastname) {
        int index = 0;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputLastname);
        display(list1);
        System.out.print("which contact want you change, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new email");
        String name = sc.nextLine();

        list1.get(index).getEmail().setEmail(name);
        System.out.println(mapContact);
    }


    public int getCount(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getFirstName().equals(s)) {
                count++;
            }
        }
        return count;

    }
    public int getCountLastname(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {
                count++;
            }
        }
        return count;

    }

    @Override
    public void update() {

        //  Contact e = new Contact();
        int count;
        //    List<Contact> list = new ArrayList<>();
        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n' +
                "5:Email ");
        String answertype = sc.nextLine();
        String inputname;


        switch (answertype) {
            case "1":
                System.out.println("Enter First Name");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);

                if (count > 1) {

                    createListToChangeFirstName(inputname);

                } else
                    changeFirstName(inputname);

                break;
            case "2":
                System.out.println("Enter Last Name");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if (count > 1) {

                    createListToChangeLastName(inputname);

                } else
                    changeLastName(inputname);

                break;
            case "3":
                System.out.println("Enter Company Name");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if (count > 1) {

                    createListToChangeCompanyName(inputname);

                } else
                    changeCompanyName(inputname);

                break;
            case "4":
                System.out.println("Enter PhoneNumber");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if (count > 1) {

                    createListToChangePhoneNumber(inputname);

                } else
                    changePhoneNumber(inputname);

                break;
            case "5":
                System.out.println("Enter Email");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if (count > 1) {
                    createListToChangeEmail(inputname);


                } else
                    changeEmail(inputname);

                break;


}

















