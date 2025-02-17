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
        OUTER:
        while (true) {
            System.out.println("\nWelcome to Nitin's PaintBoard program!\n");
            System.out.print("Do you want to create a new PaintBoard? (y/n): ");
            String response = sc.next();
            switch (response) {
                case "y" -> {
                    System.out.print("What should be the height of the paint board? ");
                    double h = sc.nextDouble();
                    System.out.print("What should be the width of the paint board? ");
                    double w = sc.nextDouble();
                    PaintBoard pb = new PaintBoard(h, w);
                    insidePaintBoard(pb);
                }
                case "n" -> {
                    break OUTER;
                }
                default -> {
                    break;
                }
            }
        }
    }

    void insidePaintBoard(PaintBoard pb) {

    }
}
