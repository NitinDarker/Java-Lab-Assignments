import java.util.Scanner;

public class Q1 {
    static String findUsername(String email) {
        int ind = email.indexOf('@');
        String username = email.substring(0, ind);
        return username;
    }

    static boolean isGmail(String email) {
        int indAt = email.indexOf('@');
        int indDot = email.indexOf('.');
        String domain = email.substring(indAt+1, indDot);
        return domain.equals("gmail.com");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email address: ");
        String email = sc.next();
        System.out.println("Username: " + findUsername(email));
        System.out.println("IsGmail: " + isGmail(email));
        sc.close();
    }
}
