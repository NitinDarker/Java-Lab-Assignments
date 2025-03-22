package lab2;
class Complex {
    int real, img;

    // Default constructor
    Complex() {
        real = img = 0;
    }

    // Parametrized constructor
    Complex(int real, int img) {
        this.real = real;
        this.img = img;
    }

    void display() {
        System.out.println(real + " + " + img + "i");
    }

    // Method for adding two complex numbers using real and imaginary parts directly
    Complex addition(int r1, int i1, int r2, int i2) {
        return new Complex((r1 + r2), (i1 + i2));
    }

    // Method for adding two complex number objects
    Complex addition(Complex c1, Complex c2) {
        return new Complex((c1.real + c2.real), (c1.img + c2.img));
    }

    // Method for adding a complex number to the calling object
    Complex addition(Complex num) {
        return new Complex(this.real + num.real, this.img + num.img);
    }
}

public class Q3 {
    public static void main(String args[]) {
        Complex com1 = new Complex();
        com1.display(); // 0 + 0i

        Complex com2 = new Complex(5, 8);
        com2.display(); // 5 + 8i

        Complex com3 = new Complex(15, 2);
        com3.display(); // 15 + 2i

        // Add two complex numbers by passing real and imaginary parts
        Complex sum = new Complex();
        sum = sum.addition(2,4,5,9);
        sum.display(); // 7 + 13i

        // Add two complex number objects
        sum = sum.addition(com2, com3);
        sum.display(); // 20 + 10i

        // Add a complex number to the calling object (sum)
        sum = sum.addition(com2);
        sum.display(); // 25 + 18i

        // Add the sum object to itself
        sum = sum.addition(sum);
        sum.display(); // 50 + 36i
    }
}