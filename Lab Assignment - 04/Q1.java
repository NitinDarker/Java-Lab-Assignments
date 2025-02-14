import java.util.Scanner;

public class Q1 {
    public static void main(String args[]) {
        Menu m = new Menu();
        m.run();
    }
}

class Menu {
    Scanner sc = new Scanner(System.in);

    void run() {
        while (true) {
            System.out.println("\nWelcome to PaintBoard program!\n");
            System.out.println("Do you want to create a new PaintBoard? (y/n): ");
            String response = sc.next();
            if (response.equals("y")) {
                System.out.println("What should be the height of the paint board? ");
                double h = sc.nextDouble();
                System.out.println("What should be the width of the paint board? ");
                double w = sc.nextDouble();
                PaintBoard pb = new PaintBoard(h, w);
                insidePaintBoard(pb);
            }
        }
    }

    void insidePaintBoard(PaintBoard pb) {

    }
}
