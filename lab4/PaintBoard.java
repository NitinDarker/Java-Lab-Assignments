class PaintBoard {
    private final int width;
    private final int height;
    private final LinkedList<Shape> shapes = new LinkedList<>();

    public PaintBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void displayAllInfo() {
        System.out.println("\nPaint Board (" + width + "x" + height + ")");
        System.out.println("Shapes:");
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            System.out.println("-------------------");
            System.out.println(shape.getProperties());
            System.out.printf("Area: %.2f\n", shape.getArea());
            System.out.printf("Perimeter: %.2f\n", shape.getPerimeter());
            System.out.println("Fill Color: " + shape.getColor());
        }
    }

    public void setFillColorForAll(String color) {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).fillColor(color);
        }
    }

    public void setOutlineColorForAll(String color) {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).fillOutline(color);
        }
    }
}