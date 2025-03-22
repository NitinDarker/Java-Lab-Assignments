package lab1;
class LineObj {
    // Object for a line
    Points p1, p2;

    void createLine(Points p1, Points p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    void createLine(int x1, int y1, int x2, int y2) {
        // Function overloading on createLine function
        p1 = new Points();
        p1.x = x1;
        p1.y = y1;

        p2 = new Points();
        p2.x = x2;
        p2.y = y2;
    }

    void showLine() {
        System.out.println("\nA line is created by joining these two points:");
        System.out.println("x1 = " + p1.x + ", y1 = " + p1.y);
        System.out.println("x2 = " + p2.x + ", y2 = " + p2.y);
    }

    void showSlope() {
        if (p2.x - p1.x == 0) {
            System.out.println("Slope is Not Defined for this line!");
            return;
        }

        double slope = (double) (p2.y - p1.y) / (double) (p2.x - p1.x);
        System.out.println("Slope of line = " + slope);
    }
}

public class Q2 {
    public static void main(String args[]) {

        Points p1 = new Points();
        Points p2 = new Points();
        p1.setValue(5 ,5);
        p2.setValue(10, 10);

        LineObj myLine = new LineObj();
        myLine.createLine(p1, p2);
        myLine.showLine();
        myLine.showSlope();

        LineObj yourLine = new LineObj();
        yourLine.createLine(5, 10, -5, 15);
        yourLine.showLine();
        yourLine.showSlope();
    }
}