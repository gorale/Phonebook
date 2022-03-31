package service;

import model.Enum.CRUD;

import java.util.Locale;
import java.util.Scanner;

public class ContactController {
    Service service = new Service();
    Scanner sc = new Scanner(System.in);
    CRUD message;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void start() {


        while (true) {
            try {
                System.out.println(ANSI_YELLOW+ "Please enter one of the following action "+'\n'+
                        "-CREATE"+'\n'+
                        "-READE"+'\n'+
                        "-UPDATE"+'\n'+
                        "-DELETE");
                //System.out.println("Enter valid action");
                message = CRUD.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e){
                continue;
            }
            switch (message) {
                    case CREATE:
                        System.out.println(service.add());
                        break;
                    case READE:
                        System.out.println(service.getAll());
                        boolean question = Service.question();
                        if(question){
                            System.out.println("search first name: ");
                            String firstname = sc.nextLine();
                            service.get(firstname);
                        }
                        break;
                    case UPDATE:


                }

        }
    }
}
