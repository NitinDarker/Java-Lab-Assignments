package lab5;

import java.util.Scanner;

public class Q4 {
    static String[] filter(String emails[]) {
        String[] res = new String[emails.length];
        for (int i = 0; i < emails.length; i++) {
            char capital = (emails[i].charAt(0));
            boolean ss = Character.isUpperCase(capital);
            if (ss) {
                System.out.println(emails[i]);
            }
        }
        return res;
    }

    static void printEmails(String emails[]) {
        for (String email : emails) {
            System.out.println(email);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.print("Enter the number of emails: ");
        num = sc.nextInt();
        String arr[] = new String[num];
        for (int i = 0; i < num; i++) {
            System.out.print("Enter Email " + i + " : ");
            arr[i] = sc.next();
        }
        filter(arr);
        sc.close();
    }
}
