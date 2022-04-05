package service;

import model.Enum.CRUD;

import java.util.Locale;
import java.util.Scanner;

public class ContactController {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    ServicePhoneBook service = new ServicePhoneBook();
    Scanner sc = new Scanner(System.in);
    CRUD message;

    public void start() {


        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Please enter one of the following action " + '\n' +
                        "-CREATE" + '\n' +
                        "-READE" + '\n' +
                        "-SEARCH" + '\n' +
                        "-UPDATE" + '\n' +
                        "-DELETE" + '\n' +
                        "-EXIT");
                //System.out.println("Enter valid action");
                System.out.print("Your choose: ");
                message = CRUD.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                continue;
            }
            switch (message) {
                case CREATE:
                    System.out.println(service.add());
                    break;
                case READE:
                    if (service == null) {
                        break;
                    } else {
                        System.out.println(service.getAll());
                    }

                    if (ServicePhoneBook.question()) {
                        System.out.print("search first name: ");
                        String firstname = sc.nextLine();
                        service.get(firstname);
                    }
                    break;
                case UPDATE:
                    service.update();
                    break;
                case SEARCH:
                    System.out.print("Enter FirstName: ");
                    String firstName = sc.nextLine();

                    service.get(firstName);
                    break;
                case DELETE:
                    service.delete();
                    break;
                case EXIT:
                    service.exit();
                    break;
            }


        }

    }
}

