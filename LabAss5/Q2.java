package lab5;

import java.util.Scanner;

public class Q2 {
    static int countWords(String str) {
        String arr[] = str.split(" ");
        return arr.length;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a valid string: ");
        String input = sc.nextLine();

        System.out.println("Total No. of words = " + countWords(input));

        sc.close();
    }
}
