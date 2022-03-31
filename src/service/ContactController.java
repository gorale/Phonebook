package service;

import model.Enum.CRUD;

import java.util.Locale;
import java.util.Scanner;

public class ContactController {
    Service service = new Service();
    Scanner sc = new Scanner(System.in);
    CRUD message;

    public void start() {
        while (true) {
            try {
                System.out.println("Please enter one of the following action "+'\n'+
                        "-CREATE"+'\n'+
                        "-READE"+'\n'+
                        "-UPDATE"+'\n'+
                        "-DELETE");
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
                        System.out.println("Do you want to give out someone else's information?");
                        System.out.println("yes or no");
                        String mess = sc.nextLine();
                        if(mess.equals("yes")){
                            System.out.println("search first name: ");
                            String firstname = sc.nextLine();
                            service.get(firstname);
                        }
                        break;
                    case UPDATE:
                        System.out.println();

                }

        }
    }
}
