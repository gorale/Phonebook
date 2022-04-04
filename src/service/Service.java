package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;
import validation.ContactValidation;

import java.util.*;

public class Service implements CreateReadUpdateDelete {

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

        System.out.println("Choose answer:" + '\n'
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
    public String insertFirstName(){
       String firstName;
        System.out.print("Enter firstname: ");
        while (true) {
           firstName = sc.nextLine();

            if (cn.isValidFirstName(firstName)) {
                break;
            } else {
                System.out.print(ANSI_RED + "Enter valid firstname: ");
            }
        }
        return  firstName;

    }
    public String insertPhoneNumberType(){
        NumberType numberType;

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
        }


    @Override
    public Contact add() {
        String firstName="";
        String myEmail;
        String number;
        uuid = UUID.randomUUID();
        NumberType numberType;
        Contact.PhoneNumbers phoneNumbers;
        EmailType emailType;
        Contact.Email email;
        ContactValidation cn = new ContactValidation();
       firstName=insertFirstName();
       // Scanner sc=new Scanner(System.in);
        System.out.println("Do you want to enter lastname?: ");
        String lastName = answer();
        System.out.println("Do you want to enter company name?: ");
        String company = answer();
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

            System.out.print("Enter phone number: ");

            while (true) {
                number = sc.nextLine();

                if (cn.isValidPhoneNumber(number)) {
                    break;

                } else {
                    System.out.print(ANSI_RED + "Enter valid phone number: ");
                }
            }


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

    public void deleteCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getCompany().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }

    public void deletePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getPhoneNumbers().getNumber().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }

    public void createListToDeletePhoneNumbers(String phonenumbers) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, phonenumbers);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
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

    public void createListToDeleteCompanyName(String companyName) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, companyName);
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
                "4:PhoneNumber " + '\n');
        System.out.print("Enter your choose: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Enter First Name: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if (count == 0) {
                    System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deleteFirstName(inputname);
                } else {
                    createListToDeleteFirstName(inputname);
                }
                break;

            case "2":
                System.out.print("Enter Last Name: ");
                inputname = sc.nextLine();
                count = getCountLastname(mapContact, inputname);
                if (count == 0) {
                    System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deleteLastName(inputname);
                } else {
                    createListToDeleteLastName(inputname);
                }
                break;
            case "3":
                System.out.print("Enter Company Name: ");
                inputname = sc.nextLine();
                count = getCountCompanyName(mapContact, inputname);
                if (count == 0) {
                    System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deleteCompanyName(inputname);
                } else {
                    createListToDeleteCompanyName(inputname);
                }
                break;
            case "4":
                System.out.print("Enter PhoneNumbers: ");
                inputname = sc.nextLine();
                count = getCountPhoneNumbers(mapContact, inputname);
                if (count == 0) {
                    System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
                    delete();
                }
                if (count == 1) {
                    deletePhoneNumber(inputname);
                } else {
                    createListToDeletePhoneNumbers(inputname);
                }
                break;
            default:
                delete();
        }

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

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setFirstName(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeLastName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setLastName(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setCompany(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {


                System.out.print("Enter new phone number: ");
                String number = sc.nextLine();

                map1.getValue().getPhoneNumbers().setNumber(number);
            }
            System.out.println(mapContact.values());
        }

    }

    public void changeEmail(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getEmail().getEmail().equals(s)) {


                System.out.print("Enter new email: ");
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

        System.out.print("Enter new name: ");
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

        System.out.print("Enter new phone number: ");
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

        System.out.print("Enter new name: ");
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

        System.out.print("Enter new name: ");
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

        System.out.print("Enter new email: ");
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

    public int getCountPhoneNumbers(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public int getCountCompanyName(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {
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
                "5:Email "+ '\n' +
                "6:Exit ");
        System.out.print("Your choose: ");
        String answertype = sc.nextLine();
        String inputname;


        switch (answertype) {
            case "1":
                System.out.print("Which First Name Want You Update: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);

                if(count == 0){
                    System.out.println(ANSI_RED +"Can't find user "+ANSI_YELLOW);
                    update();
                }
                if (count > 1) {

                    createListToChangeFirstName(inputname);

                } else
                    changeFirstName(inputname);

                break;
            case "2":
                System.out.print("Which Last Name Want You Update: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if(count == 0){
                    System.out.println(ANSI_RED +"Can't find user "+ANSI_YELLOW);
                    update();
                }
                if (count > 1) {

                    createListToChangeLastName(inputname);

                } else
                    changeLastName(inputname);

                break;
            case "3":
                System.out.print("Which Company Want You Update: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if(count == 0){
                    System.out.println(ANSI_RED +"Can't find user "+ANSI_YELLOW);
                    update();
                }
                if (count > 1) {

                    createListToChangeCompanyName(inputname);

                } else
                    changeCompanyName(inputname);

                break;
            case "4":
                System.out.print("Which PhoneNumber Want You Update: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if(count == 0){
                    System.out.println(ANSI_RED +"Can't find user "+ANSI_YELLOW);
                    update();
                }
                if (count > 1) {

                    createListToChangePhoneNumber(inputname);

                } else
                    changePhoneNumber(inputname);

                break;
            case "5":
                System.out.print("Which Email Want You Update: ");
                inputname = sc.nextLine();
                count = getCount(mapContact, inputname);
                if(count == 0){
                    System.out.println(ANSI_RED +"Can't find user "+ANSI_YELLOW);
                    update();
                }
                if (count > 1) {
                    createListToChangeEmail(inputname);


                } else
                    changeEmail(inputname);

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

















