package service;

import main.Menu;
import model.Contact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ServicePhoneBook implements CreateReadUpdateDelete {
    public static final String file="C:\\Users\\Lusine\\IdeaProjects\\Phonebook\\src\\db\\contact.txt";

    private static Map<UUID, Contact> mapContact = new HashMap<>();


    public static Map<UUID, Contact> getMapContact() {
        return mapContact;
    }

    @Override
    public Contact add() {
        UUID uuid = UUID.randomUUID();
        String firstName = ServiceController.insertFirstName();
        String lastName = ServiceController.insertLastName();
        String company = ServiceController.insertCompany();
        Contact.PhoneNumber phoneNumber = ServiceController.insertPhoneNumber();
        Contact.Email email = ServiceController.insertEmail();

        Contact contact = new Contact(firstName, lastName, company, phoneNumber, email);
        mapContact.put(uuid, contact);

        return contact;

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
        String choice = Menu.menuDelete();
        switch (choice) {
            case "1":
                ServiceController.deleteByfirstname();
                break;

            case "2":
                ServiceController.deleteByLastName();
                break;
            case "3":
                ServiceController.deleteByCompanyName();
                break;
            case "4":
                ServiceController.deleteByPhoneNumber();
                break;
            default:
                delete();
        }

    }

    @Override
    public void update() {

        String answerType = Menu.menuUpdate();
        switch (answerType) {
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
    public void exit() throws IOException {
        FileWriter fl=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fl);
        for (Contact item:getMapContact().values()) {
            bufferedWriter.write(item.getFirstName()+","
                    +item.getLastName()
                    +","+item.getCompany()
                    +","+item.getPhoneNumbers().getNumberType()+","
                    +item.getPhoneNumbers().getNumber()+","
                    +item.getEmail().getEmailType()+","
                    +item.getEmail().getEmail()+'\n');
        }

        bufferedWriter.close();
        fl.close();
        System.exit(0);
    }


}

















