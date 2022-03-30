package service;

import model.Enum.CRUD;

import java.util.Locale;
import java.util.Scanner;

public class ContactController {
    Service service = new Service();

    public void start() {
        while (true) {
            System.out.println("(create,reade,update or delete)");
            Scanner sc = new Scanner(System.in);
            CRUD message = CRUD.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
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
