package main;

import model.Contact;
import service.ContactController;

public class Main {

    public static void main(String[] args) {

        ContactController contactController = new ContactController();
        contactController.start();
    }
}
