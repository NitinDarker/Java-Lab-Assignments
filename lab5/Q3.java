// Q.3. Write a Java program that reads a string and find that if it is a binary
//      sequence of 0s and 1s or not.
package lab5;

import java.util.Scanner;

public class Q3 {
    static boolean isBinary(String str) {
        String regex = "[01]*";
        return str.matches(regex);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        System.out.println("Binary Sequence: " + isBinary(str));
        sc.close();
    }
}
