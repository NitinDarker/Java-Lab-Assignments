// Q1. Write a java program to extract the username from given email address and
//     verify that the domain of given email address if “gmail.com”.
package lab5;

import java.util.Scanner;
import static java.lang.System.exit;

public class Q1 {
    static void validateEmail(String email) {
        String regex = "^[A-Za-z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
        if (!email.matches(regex)) {
            System.out.println("Invalid Email!");
            exit(0);
        }
    }

    static String findUsername(String email) {
        int ind = email.indexOf('@');
        return email.substring(0, ind);
    }

    static boolean isGmail(String email) {
        int indAt = email.indexOf('@');
        String domain = email.substring(indAt+1);
        return domain.equals("gmail.com");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email address: ");
        String email = sc.next();

        validateEmail(email);

        System.out.println("Username: " + findUsername(email));

        if (isGmail(email)) System.out.println("Domain of the given email is gmail.com");
        else  System.out.println("Domain of the given email is NOT gmail.com");

        sc.close();
    }
}
