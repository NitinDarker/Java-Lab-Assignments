package lab5;

import java.util.Scanner;

public class Q6 {
    // Arrays for words
    private static final String[] ONES = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    private static final String[] TEENS = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private static final String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private static final String[] THOUSANDS = { "", "Thousand", "Lakh", "Crore" };

    // Convert a number < 1000 into words
    private static String convertBelowThousand(int num) {
        String result = "";

        if (num >= 100) {
            result += ONES[num / 100] + " Hundred ";
            num %= 100;
        }
        if (num >= 20) {
            result += TENS[num / 10] + " ";
            num %= 10;
        } else if (num >= 10) {
            result += TEENS[num - 10] + " ";
            return result.trim();
        }

        if (num > 0) {
            result += ONES[num] + " ";
        }

        return result.trim();
    }

    // Convert full number into words
    public static String numberToWords(int num) {
        if (num == 0) return "Zero";

        String result = "";
        int[] parts = new int[4]; // Crore, Lakh, Thousand, Remaining
        parts[0] = num / 10000000; // Crores
        num %= 10000000;
        parts[1] = num / 100000; // Lakhs
        num %= 100000;
        parts[2] = num / 1000; // Thousands
        num %= 1000;
        parts[3] = num; // Remaining

        for (int i = 0; i < 4; i++) {
            if (parts[i] > 0) {
                result += convertBelowThousand(parts[i]) + " " + THOUSANDS[i] + " ";
            }
        }

        return result.trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int num = sc.nextInt();

        System.out.println("In words: " + numberToWords(num));

        sc.close();
    }
}
