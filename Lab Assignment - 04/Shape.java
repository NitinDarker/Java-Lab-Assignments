public abstract class Shape implements Color {
    // protected double width;
    // protected double height;
    protected String color;
    protected String outline;

    public Shape() {
        color = "White";
        outline = "Black";
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract void getProperties();
}

/*
 * class Rectangle extends Shape {
 * double length, breadth;
 * 
 * public Rectangle(double l, double b) {
 * length = l;
 * breadth = b;
 * }
 * 
 * @Override
 * double getArea() {
 * return length * breadth;
 * }
 * 
 * @Override
 * double getPerimeter() {
 * return (2 * length) + (2 * breadth);
 * }
 * 
 * @Override
 * void getProperties() {
 * System.out.println("Length: " + length);
 * System.out.println("Breadth: " + breadth);
 * }
 * }
 */