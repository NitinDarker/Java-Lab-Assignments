// Calculator to add two integers and two real numbers
class Q2 {
    static int add (int x, int y) {
        return x + y;
    }

    static double add (double x, double y) {
        return x + y;
    }
    // Making Static methods so that main can access them

    public static void main(String args[]) {
        System.out.println(add(5, 10)); // 15
        System.out.println(add(5.5, 10.9)); // 16.4
    }    
}