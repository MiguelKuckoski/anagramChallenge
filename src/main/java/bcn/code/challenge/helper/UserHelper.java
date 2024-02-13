package bcn.code.challenge.helper;

import java.util.Scanner;

public class UserHelper {

    private UserHelper() {

    }

    public static String getUserInput() {
        System.out.println("Please insert a word (a-Z only) or empty to close:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.replaceAll("[^a-zA-Z]", "");
        return input;
    }

}
