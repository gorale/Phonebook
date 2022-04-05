package service;

import model.Contact;
import validation.ContactValidation;

import java.util.*;

public class ServiceController {
   private static Map<UUID, Contact> mapContact = new HashMap<>();
   private static Scanner sc = new Scanner(System.in);
    ContactValidation cn = new ContactValidation();

    private UUID uuid;


    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static ServicePhoneBook sb=new ServicePhoneBook();

    public static ArrayList<Contact> create(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {

            if (map.getValue().getFirstName().equals(s)) {
                list.add(map.getValue());
            }
        }
        return list;
    }
    public static ArrayList<Contact> createLastnameList(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {

            if (map.getValue().getLastName().equals(s)) {
                list.add(map.getValue());
            }
        }
        return list;
    }

    public static void display(ArrayList<Contact> list) {
        for (Contact contact : list) {
            System.out.println(1 + list.indexOf(contact) + " " + contact);
        }
    }

    public static void changeFirstName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getFirstName().equals(s)) {

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setFirstName(newname);
            }
            System.out.println(mapContact.values());
        }

    }
    public static void changeLastName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setLastName(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public static void changeCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {

                System.out.print("Enter new name: ");
                String newname = sc.nextLine();

                map1.getValue().setCompany(newname);
            }
            System.out.println(mapContact.values());
        }

    }

    public static void changePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {


                System.out.print("Enter new phone number: ");
                String number = sc.nextLine();

                map1.getValue().getPhoneNumbers().setNumber(number);
            }
            System.out.println(mapContact.values());
        }

    }

    public static void changeEmail(String s) {
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {
            if (map1.getValue().getEmail().getEmail().equals(s)) {


                System.out.print("Enter new email: ");
                String email = sc.nextLine();

                map1.getValue().getEmail().setEmail(email);
            }
            System.out.println(mapContact.values());
        }

    }
    public static void createListToChangeFirstName(String inputfirstname) {
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

    public static void createListToChangePhoneNumber(String inputfirstname) {
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


    public static void createListToChangeLastName(String inputLastname) {
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

    public static void createListToChangeCompanyName(String inputfirstname) {
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

    public static void createListToChangeEmail(String inputLastname) {
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
    public static void deleteFirstName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getFirstName().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }

    public static void deleteLastName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getLastName().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }
    public static void deleteCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getCompany().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }

    public static void deletePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map : mapContact.entrySet()) {
            if (map.getValue().getPhoneNumbers().getNumber().equals(s)) {
                mapContact.remove(map.getKey());
                break;
            }
        }
    }
    public static void createListToDeletePhoneNumbers(String phonenumbers) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, phonenumbers);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }

    public static void createListToDeleteFirstName(String inputfirstname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }
    public static void createListToDeleteCompanyName(String companyName) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, companyName);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }

    public static void createListToDeleteLastName(String inputlastname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        createLastnameList(list1, inputlastname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        mapContact.values().remove(list1.get(index - 1));
    }
    public static void deletebyfirstname() {
        System.out.print("Enter First Name: ");
        String inputname = sc.nextLine();
        int count = getCountFirstName(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
            sb.delete();
        }
        if (count == 1) {
            deleteFirstName(inputname);
        } else {
            createListToDeleteFirstName(inputname);
        }
    }

    public static void deletebyLastName() {
        System.out.print("Enter Last Name: ");
        String inputname = sc.nextLine();
        int count = getCountLastname(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
           sb.delete();
        }
        if (count == 1) {
            deleteLastName(inputname);
        } else {
            createListToDeleteLastName(inputname);
        }
    }
    public static void deletebyCompanyName() {
        System.out.print("Enter Company Name: ");
        String inputname = sc.nextLine();
        int count = getCountCompanyName(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
            sb.delete();
        }
        if (count == 1) {
            deleteCompanyName(inputname);
        } else {
            createListToDeleteCompanyName(inputname);
        }
    }

    public static void deletebyPhoneNumber() {
        System.out.print("Enter PhoneNumbers: ");
        String inputname = sc.nextLine();
        int count = getCountPhoneNumbers(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user, enter other field" + ANSI_YELLOW);
            sb.delete();
        }
        if (count == 1) {
            deletePhoneNumber(inputname);
        } else {
            createListToDeletePhoneNumbers(inputname);
        }
    }



    public static int getCountFirstName(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getFirstName().equals(s)) {
                count++;
            }
        }
        return count;

    }
    public static int getCountLastname(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {
                count++;
            }
        }
        return count;

    }
    public static int getCountPhoneNumbers(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {
                count++;
            }
        }
        return count;
    }
    public static int getCountCompanyName(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {
                count++;
            }
        }
        return count;

    }
    public static int getCountEmail(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : mapContact.entrySet()) {

            if (map1.getValue().getEmail().getEmail().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public  static void updateByFirstName() {
        System.out.print("Which First Name Want You Update: ");
        String inputname = sc.nextLine();
        int count =getCountFirstName(mapContact, inputname);

        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangeFirstName(inputname);

        } else
           changeFirstName(inputname);
    }

    public static void updateByLastName() {
        System.out.print("Which Last Name Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountLastname(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

           createListToChangeLastName(inputname);

        } else
            changeLastName(inputname);
    }
    public static void updateByCompanyName() {
        System.out.print("Which Company Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountCompanyName(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangeCompanyName(inputname);

        } else
            changeCompanyName(inputname);
    }

    public static void updateByPhoneNumber() {
        System.out.print("Which PhoneNumber Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountPhoneNumbers(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangePhoneNumber(inputname);

        } else
            changePhoneNumber(inputname);
    }
    public static void updateByEmail() {
        System.out.print("Which Email Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountEmail(mapContact, inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {
            createListToChangeEmail(inputname);


        } else
            changeEmail(inputname);
    }



}
