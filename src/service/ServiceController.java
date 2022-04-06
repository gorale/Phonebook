package service;

import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;
import validation.ContactValidation;

import java.util.*;

public class ServiceController {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static ContactValidation cn = new ContactValidation();
    private static Scanner sc = new Scanner(System.in);
    private static ServicePhoneBook sb = new ServicePhoneBook();
 ///   private static Map<UUID, Contact> mapContact=sb.mapContact;

    private static UUID uuid;
  //  private static ServicePhoneBook sb = new ServicePhoneBook();



    /**
     * insert firsName
     *
     * @return String
     */
    public static String insertFirstName() {
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
        return firstName;

    }

    public static String insertLastName() {

        System.out.println("Do you want to enter lastname?: ");
        String lastName = Query.answer();
        return lastName;
    }

    public static String insertCompany() {
        System.out.println("Do you want to enter company name?: ");
        String company = Query.answer();
        return company;
    }

    public static Contact.PhoneNumber insertPhoneNumber() {
        NumberType numberType;
        Contact.PhoneNumber phoneNumber;
        String number;
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
                    return phoneNumber = new Contact.PhoneNumber(numberType, number);

                default:
                    return phoneNumber = new Contact.PhoneNumber(NumberType.OTHER, number);
            }
        }
    }

    public static Contact.Email insertEmail() {
        String myEmail;
        EmailType emailType;
        Contact.Email email;
        uuid = UUID.randomUUID();
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
                   return email = new Contact.Email(emailType, myEmail);

                default:
                   return email = new Contact.Email(EmailType.OTHER, myEmail);
            }
        }



    }



    public static ArrayList<Contact> create(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {

            if (map.getValue().getFirstName().equals(s)) {
                list.add(map.getValue());
            }
        }
        return list;
    }

    public static ArrayList<Contact> createLastnameList(ArrayList list, String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {

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
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if(map1.getValue().getFirstName().equals(s)) {
                System.out.println("What can you update" +'\n'+
                        "1:FirstName"+'\n'+
                        "2:LastName"+'\n'+
                        "3:CompanyName"+'\n'+
                        "4:PhoneNumber"+'\n');
                String s1=sc.nextLine();
                switch (s1){
                    case "1":System.out.print("Enter new First Name: ");
                        String newfirstname = sc.nextLine();
                        map1.getValue().setFirstName(newfirstname);
                        return;
                    case "2":System.out.print("Enter new Last Name: ");
                        String newlastname = sc.nextLine();
                        map1.getValue().setLastName(newlastname);
                        return;
                    case "3":System.out.print("Enter new Company name: ");
                        String newcompanyname = sc.nextLine();
                        map1.getValue().setCompany(newcompanyname);
                        return;
                    case "4":System.out.print("Enter new phonenumber: ");
                        String newphonenumber = sc.nextLine();
                        map1.getValue().getPhoneNumbers().setNumber(newphonenumber);
                        return;

                }

            }

        }
        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(s.equals(item.getFirstName())){
                System.out.println(item);
            }
        }

    }

    public static void changeLastName(String s) {
        String newName = "";
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {

                System.out.print("Enter new name: ");
                newName = sc.nextLine();

                map1.getValue().setLastName(newName);
            }
        }
        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(newName.equals(item.getLastName())){
                System.out.println(item);
            }
        }


    }

    public static void changeCompanyName(String s) {
        String newName = "";
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {

                System.out.print("Enter new name: ");
                newName = sc.nextLine();

                map1.getValue().setCompany(newName);
            }

        }
        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(newName.equals(item.getCompany())){
                System.out.println(item);
            }
        }

    }

    public static void changePhoneNumber(String s) {
        String number = "";
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {
            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {


                System.out.print("Enter new phone number: ");
                number = sc.nextLine();

                map1.getValue().getPhoneNumbers().setNumber(number);
            }

        }
        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(number.equals(item.getPhoneNumbers().getNumber())){
                System.out.println(item);
            }
        }

    }

    public static void changeEmail(String s) {
        String email = "";
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {
            if (map1.getValue().getEmail().getEmail().equals(s)) {


                System.out.print("Enter new email: ");
                email = sc.nextLine();

                map1.getValue().getEmail().setEmail(email);
            }

        }
        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(email.equals(item.getEmail().getEmail())){
                System.out.println(item);
            }
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

        list1.get(index-1).setFirstName(name);

        for (Contact item : ServicePhoneBook.getMapContact().values()){
            if(name.equals(item.getFirstName())){
                System.out.println(item);
            }
        }


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
        list1.get(index-1).getPhoneNumbers().setNumber(number);


        System.out.println(ServicePhoneBook.getMapContact().values());


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
        System.out.println(ServicePhoneBook.getMapContact());
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

        list1.get(index-1).setCompany(name);

        System.out.println(ServicePhoneBook.getMapContact().values());


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

        list1.get(index-1).getEmail().setEmail(name);
        System.out.println(ServicePhoneBook.getMapContact());
    }

    public static void deleteFirstName(String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {
            if (map.getValue().getFirstName().equals(s)) {
                ServicePhoneBook.getMapContact().remove(map.getKey());
                break;
            }
        }
    }

    public static void deleteLastName(String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {
            if (map.getValue().getLastName().equals(s)) {
                ServicePhoneBook.getMapContact().remove(map.getKey());
                break;
            }
        }
    }

    public static void deleteCompanyName(String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {
            if (map.getValue().getCompany().equals(s)) {
                ServicePhoneBook.getMapContact().remove(map.getKey());
                break;
            }
        }
    }

    public static void deletePhoneNumber(String s) {
        for (Map.Entry<UUID, Contact> map : ServicePhoneBook.getMapContact().entrySet()) {
            if (map.getValue().getPhoneNumbers().getNumber().equals(s)) {
                ServicePhoneBook.getMapContact().remove(map.getKey());
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

        ServicePhoneBook.getMapContact().values().remove(list1.get(index - 1));
    }

    public static void createListToDeleteFirstName(String inputfirstname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, inputfirstname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        ServicePhoneBook.getMapContact().values().remove(list1.get(index - 1));
    }

    public static void createListToDeleteCompanyName(String companyName) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        create(list1, companyName);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        ServicePhoneBook.getMapContact().values().remove(list1.get(index - 1));
    }

    public static void createListToDeleteLastName(String inputlastname) {
        int index;
        ArrayList<Contact> list1 = new ArrayList<>();
        createLastnameList(list1, inputlastname);
        display(list1);

        System.out.print("which contact want you delete, enter number: ");
        index = sc.nextInt();

        sc.nextLine();

        ServicePhoneBook.getMapContact().values().remove(list1.get(index - 1));
    }

    public static void deleteByfirstname() {
        System.out.print("Enter First Name: ");
        String inputname = sc.nextLine();
        int count = getCountFirstName(ServicePhoneBook.getMapContact(), inputname);
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

    public static void deleteByLastName() {
        System.out.print("Enter Last Name: ");
        String inputname = sc.nextLine();
        int count = getCountLastname(ServicePhoneBook.getMapContact(), inputname);
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

    public static void deleteByCompanyName() {
        System.out.print("Enter Company Name: ");
        String inputname = sc.nextLine();
        int count = getCountCompanyName(ServicePhoneBook.getMapContact(), inputname);
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

    public static void deleteByPhoneNumber() {
        System.out.print("Enter PhoneNumbers: ");
        String inputname = sc.nextLine();
        int count = getCountPhoneNumbers(ServicePhoneBook.getMapContact(), inputname);
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
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getFirstName().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public static int getCountLastname(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getLastName().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public static int getCountPhoneNumbers(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getPhoneNumbers().getNumber().equals(s)) {
                count++;
            }
        }
        return count;
    }

    public static int getCountCompanyName(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getCompany().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public static int getCountEmail(Map<UUID, Contact> map, String s) {
        int count = 0;
        for (Map.Entry<UUID, Contact> map1 : ServicePhoneBook.getMapContact().entrySet()) {

            if (map1.getValue().getEmail().getEmail().equals(s)) {
                count++;
            }
        }
        return count;

    }

    public static void updateByFirstName() {
        System.out.print("Which First Name Want You Update: ");
        String inputname = sc.nextLine();


        int count = getCountFirstName(ServicePhoneBook.getMapContact(), inputname);

        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangeFirstName(inputname);

        } else {
            for (Contact item : ServicePhoneBook.getMapContact().values()){
                if(inputname.equals(item.getFirstName())){
                    System.out.println(item);
                }
            }
            changeFirstName(inputname);
        }
    }

    public static void updateByLastName() {
        System.out.print("Which Last Name Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountLastname(ServicePhoneBook.getMapContact(), inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangeLastName(inputname);

        } else {
            for (Contact item : ServicePhoneBook.getMapContact().values()){
                if(inputname.equals(item.getLastName())){
                    System.out.println(item);
                }
            }
            changeLastName(inputname);
        }
    }

    public static void updateByCompanyName() {
        System.out.print("Which Company Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountCompanyName(ServicePhoneBook.getMapContact(), inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangeCompanyName(inputname);

        } else {
            for (Contact item : ServicePhoneBook.getMapContact().values()){
                if(inputname.equals(item.getCompany())){
                    System.out.println(item);
                }
            }
            changeCompanyName(inputname);
        }
    }

    public static void updateByPhoneNumber() {
        System.out.print("Which PhoneNumber Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountPhoneNumbers(ServicePhoneBook.getMapContact(), inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {

            createListToChangePhoneNumber(inputname);

        } else{

            for (Contact item : ServicePhoneBook.getMapContact().values()){
                if(inputname.equals(item.getPhoneNumbers().getNumber())){
                    System.out.println(item);
                }
            }
            changePhoneNumber(inputname);
        }
    }

    public static void updateByEmail() {
        System.out.print("Which Email Want You Update: ");
        String inputname = sc.nextLine();
        int count = getCountEmail(ServicePhoneBook.getMapContact(), inputname);
        if (count == 0) {
            System.out.println(ANSI_RED + "Can't find user " + ANSI_YELLOW);
            sb.update();
        }
        if (count > 1) {
            createListToChangeEmail(inputname);


        } else {
            for (Contact item : ServicePhoneBook.getMapContact().values()){
                if(inputname.equals(item.getEmail().getEmail())){
                    System.out.println(item);
                }
            }
            changeEmail(inputname);
        }
    }

}
