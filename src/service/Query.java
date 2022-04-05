package service;

import java.util.Locale;
import java.util.Scanner;

public class Query {
    private static Scanner sc = new Scanner(System.in);
    /**
     *
     * @return boolean
     */
    public static boolean question() {


        System.out.println("Do you want to give out someone else's information?" + '\n'
                + "-YES" + '\n' + "-NO");
        String question = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (question) {
            case "YES":
                return true;
            case "NO":
                break;
            default:
                question();

        }
        return false;
    }

    /**
     *
     * @return String
     */
    public static String answer() {



        System.out.println("Choose answer:" + '\n'
                + "-YES" + '\n' + "-NO");
        String answer = sc.nextLine().toUpperCase(Locale.ROOT);


        switch (answer) {
            case "YES":
                System.out.print("Enter field: ");
                return sc.nextLine();
            case "NO":
                break;
            default:
                answer();
        }

        return "";
    }
}
