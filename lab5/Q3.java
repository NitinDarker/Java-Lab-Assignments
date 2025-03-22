package lab5;

import java.util.Scanner;

public class Q3 {
    static boolean isBinary(String str) {
        String regex = "[01]*";
        return str.matches(regex);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        System.out.println("isBinary: " + isBinary(str));
        sc.close();
    }
}
