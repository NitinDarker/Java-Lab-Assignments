import java.util.Scanner;

public class Q1 {
    public static void main(String args[]) {
        Menu.run();
    }
}

class Menu {
    private static final LinkedList<PaintBoard> paintBoards = new LinkedList<>();
    private static final LinkedList<Shape> shapes = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createPaintBoard();
                case 2 -> createShape();
                case 3 -> addShapeToPaintBoard();
                case 4 -> displayPaintBoardInfo();
                case 5 -> setColorsForPaintBoard();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create Paint Board");
        System.out.println("2. Create Shape");
        System.out.println("3. Add Shape to Paint Board");
        System.out.println("4. Display Paint Board Info");
        System.out.println("5. Set Colors for Paint Board");
        System.out.println("6. Exit");
    }

    private static void createPaintBoard() {
        int width = getIntInput("Enter board width: ");
        int height = getIntInput("Enter board height: ");
        paintBoards.add(new PaintBoard(width, height));
        System.out.println("Paint Board created!");
    }

    private static void createShape() {
        System.out.println("\nShape Types:");
        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");
        System.out.println("4. Square");
        int type = getIntInput("Select shape type: ");

        Shape shape;
        switch (type) {
            case 1 -> {
                double radius = getDoubleInput("Enter radius: ");
                shape = new Circle(radius);
            }
            case 2 -> {
                double width = getDoubleInput("Enter width: ");
                double height = getDoubleInput("Enter height: ");
                shape = new Rectangle(width, height);
            }
            case 3 -> {
                double a = getDoubleInput("Enter side 1: ");
                double b = getDoubleInput("Enter side 2: ");
                double c = getDoubleInput("Enter side 3: ");
                try {
                    shape = new Triangle(a, b, c);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
            }
            case 4 -> {
                double side = getDoubleInput("Enter side length: ");
                shape = new Square(side);
            }
            default -> {
                System.out.println("Invalid shape type");
                return;
            }
        }

        shapes.add(shape);
        System.out.println("Shape created!");
    }

    private static void addShapeToPaintBoard() {
        if (paintBoards.isEmpty() || shapes.isEmpty()) {
            System.out.println("Create at least one paint board and shape first!");
            return;
        }

        PaintBoard pb = selectPaintBoard();
        Shape shape = selectShape();

        pb.addShape(shape);
        System.out.println("Shape added to paint board!");
    }

    private static void displayPaintBoardInfo() {
        if (paintBoards.isEmpty()) {
            System.out.println("No paint boards available!");
            return;
        }

        selectPaintBoard().displayAllInfo();
    }

    private static void setColorsForPaintBoard() {
        if (paintBoards.isEmpty()) {
            System.out.println("No paint boards available!");
            return;
        }

        PaintBoard pb = selectPaintBoard();
        System.out.print("Enter fill color (or 'skip'): ");
        String fill = scanner.nextLine();
        if (!fill.equalsIgnoreCase("skip")) {
            pb.setFillColorForAll(fill);
        }

        System.out.print("Enter outline color (or 'skip'): ");
        String outline = scanner.nextLine();
        if (!outline.equalsIgnoreCase("skip")) {
            pb.setOutlineColorForAll(outline);
        }

        System.out.println("Colors updated!");
    }

    private static PaintBoard selectPaintBoard() {
        System.out.println("\nAvailable Paint Boards:");
        for (int i = 0; i < paintBoards.size(); i++) {
            System.out.println((i + 1) + ". Board " + (i + 1));
        }
        int index = getIntInput("Select paint board: ") - 1;
        return paintBoards.get(index);
    }

    private static Shape selectShape() {
        System.out.println("\nAvailable Shapes:");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i + 1) + ". " + shapes.get(i).getProperties());
        }
        int index = getIntInput("Select shape: ") - 1;
        return shapes.get(index);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}