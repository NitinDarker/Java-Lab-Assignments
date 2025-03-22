package lab2;
class Person {
    String name, address;
    int age;

    // Constructor for Person (parent) class
    Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
    }
}

class Student extends Person {
    String branch;
    int semester;

    // Constructor for Student (child) class
    Student(String name, int age, String address, String branch, int semester) {
        super(name, age, address);
        this.branch = branch;
        this.semester = semester;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Branch: " + branch);
        System.out.println("Semester: " + semester);
    }
}

class Employee extends Person {
    String organization, designation;
    double salary;

    // Constructor for Employee (child) class
    Employee(String name, int age, String address, String organization, String designation, double salary) {
        super(name, age, address);
        this.organization = organization;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Organization: " + organization);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " + salary);
    }
}

public class Q1 {
    public static void main(String args[]) {

        Person p1 = new Person("Nitin", 19, "Jaipur");
        System.out.println("\nObject of Person class:");
        p1.display();

        Student s1 = new Student("Mohit", 20, "Ajmer", "CSE", 4);
        System.out.println("\nObject of Student class:");
        s1.display();

        Employee e1 = new Employee("Umesh", 20, "Sikar", "Google", "Junior", 500);
        System.out.println("\nObject of Employee class:");
        e1.display();
    }
}
