package service;

import main.Menu;
import model.Contact;
import model.Enum.EmailType;
import model.Enum.NumberType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class ContactController  {
    public static final String file="C:\\Users\\User\\IdeaProjects\\Phonebook\\src\\db\\contact.txt";
    public void start() throws IOException {

        FileReader fr=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fr);
        try {
            while (bufferedReader.ready()){

                String[] array= bufferedReader.readLine().split(",");
                String firstName = array[0];
                String lastName=array[1];
                String company=array[2];
                Contact.PhoneNumber phoneNumber=new Contact.PhoneNumber(NumberType.valueOf(array[3]), array[4]);
                Contact.Email email=new Contact.Email(EmailType.valueOf(array[5]),array[6]);

                Contact contact = new Contact(firstName, lastName, company, phoneNumber, email);

                ServicePhoneBook.getMapContact().put(UUID.randomUUID(),contact);
            }
        }
        catch (IOException e){
            System.out.println("File not found");

        }
        fr.close();
        bufferedReader.close();
            Menu.menu();

    }
}


