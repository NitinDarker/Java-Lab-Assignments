// Q.5. Write a Java program that takes user input for a phone number and an IP
//      address. Validate them and print whether they are valid or not.
package lab5;

import java.util.Scanner;

public class Q5 {
    static void validatePhone(String phone) {
        String regex = "^[6-9][0-9]{9}$";
        if (phone.matches(regex)) {
            System.out.println("Given phone number is VALID");
        } else {
            System.out.println("Given number is NOT a valid 10-digit Phone Number");
        }
    }

    static void validateIp(String ip) {
        String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (ip.matches(regex)) {
            System.out.println("Given IP is a VALID IPv4 address");
        } else {
            System.out.println("Given IP is NOT a valid IPv4 address");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your phone number: ");
        String phone = sc.next();
        validatePhone(phone);

        System.out.print("Enter your IP address: ");
        String ip = sc.next();
        validateIp(ip);
    }
}
