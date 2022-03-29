package service;

import java.util.Scanner;

public class ContactController {
    Service service = new Service();

    public void start() {
        while (true) {
            System.out.println("add or remove");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
                switch (message) {
                    case "add":
                        System.out.println(service.add());
                        break;

                }

        }
    }
}
