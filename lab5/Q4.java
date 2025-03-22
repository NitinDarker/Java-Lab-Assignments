// Q.4. Write a Java program that reads a list of email addresses and filter the email
//      addresses that start with a capital letter. Print the results.
package lab5;

import java.util.Scanner;
import static java.lang.System.exit;

public class Q4 {
    static void validateEmail(String email) {
        String regex = "^[A-Za-z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
        if (!email.matches(regex)) {
            System.out.println("Invalid Email!");
            exit(0);
        }
    }

    static String[] filterEmails(String[] emails) {
        String[] filteredList = new String[emails.length];
        int ind = 0;

        for (String email : emails) {
            char capital = (email.charAt(0));
            if (Character.isUpperCase(capital)) {
                filteredList[ind++] = email;
            }
        }
        return filteredList;
    }

    static void printList(String[] list) {
        System.out.println("\nEmails that start with capital letters: ");
        for (String ele : list) {
            if (ele == null) continue;
            System.out.println(ele);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num;
        System.out.print("Enter the number of emails: ");
        num = sc.nextInt();

        String[] arr = new String[num];

        for (int i = 0; i < num; i++) {
            System.out.print("Enter Email " + i + " : ");
            arr[i] = sc.next();
            validateEmail(arr[i]);
        }

        String[] filteredEmails = filterEmails(arr);
        printList(filteredEmails);

        sc.close();
    }
}
