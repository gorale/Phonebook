package main;

import model.Enum.CRUD;
import service.Query;
import service.ServicePhoneBook;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static CRUD message;
    private static Scanner sc = new Scanner(System.in);


    public static String menuUpdate(){
        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n' +
                "5:Email " + '\n' +
                "6:Exit ");
        System.out.print("Your choose: ");
        String answerType = sc.nextLine();
        return answerType;
    }
    public static String menuDelete(){
        System.out.println("Choose which field want to change" + '\n' +
                "1:First Name" + '\n' +
                "2:Last Name" + '\n' +
                "3:Company Name" + '\n' +
                "4:PhoneNumber " + '\n');
        System.out.print("Enter your choose: ");
        String choice = sc.nextLine();
        return choice;
    }

    public static void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        ServicePhoneBook service = new ServicePhoneBook();


        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Please enter one of the following action " + '\n' +
                        "-CREATE" + '\n' +
                        "-READE" + '\n' +
                        "-SEARCH" + '\n' +
                        "-UPDATE" + '\n' +
                        "-DELETE" + '\n' +
                        "-EXIT");
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

                    if (Query.question()) {
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
