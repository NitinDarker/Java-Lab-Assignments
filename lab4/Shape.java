// Interface for all Colors
interface Color {
    void fillColor(String colorName);
    void fillOutline(String colorName);
    String getColor();
}

// Abstract Shape class implementing common color functionality
abstract class Shape implements Color {
    protected String fillColor = "none";
    protected String outlineColor = "none";
    
    abstract double getArea();
    abstract double getPerimeter();
    abstract String getProperties();

    @Override
    public void fillColor(String colorName) {
        this.fillColor = colorName;
    }

    @Override
    public void fillOutline(String colorName) {
        this.outlineColor = colorName;
    }

    @Override
    public String getColor() {
        return fillColor;
    }
}