package main;

import service.ContactController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ContactController contactController = new ContactController();
        contactController.start();
    }
}
