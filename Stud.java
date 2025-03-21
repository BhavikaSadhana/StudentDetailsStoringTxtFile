import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Student class with getters and setters
class Students {
    private String name;
    private int age;

    public Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Returns formatted student details
    public String getFormattedDetails() {
        return String.format("| %-20s | %-5d |", name, age);
    }
}

// Node class for LinkedList
class Node {
    Students data;
    Node next;

    Node(Students data) {
        this.data = data;
        this.next = null;
    }
}

// LinkedList to store students
class StudentDetails {
    private Node head;

    // Add a student to the end of the list
    public void addLast(Students student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Get all student details
    public void getAllStudents() {
        if (head == null) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("+----------------------+-------+");
        System.out.println("| Name                 | Age   |");
        System.out.println("+----------------------+-------+");
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data.getFormattedDetails());
            temp = temp.next;
        }
        System.out.println("+----------------------+-------+");
    }

    // Get student details by name
    public void getDetailsByName(String name) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.data.getName().equalsIgnoreCase(name)) {
                System.out.println(temp.data.getFormattedDetails());
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Student with name " + name + " not found.");
        }
    }

    // Get student details by age
    public void getDetailsByAge(int age) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.data.getAge() == age) {
                System.out.println(temp.data.getFormattedDetails());
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No students found with age " + age);
        }
    }

    // Update student name
    public void updateStudentName(String oldName, String newName) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getName().equalsIgnoreCase(oldName)) {
                temp.data.setName(newName);
                System.out.println("Student name updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with name " + oldName + " not found.");
    }

    // Update student age
    public void updateStudentAge(String name, int newAge) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getName().equalsIgnoreCase(name)) {
                temp.data.setAge(newAge);
                System.out.println("Student age updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with name " + name + " not found.");
    }

    // Delete student by name
    public void deleteStudentByName(String name) {
        if (head == null) {
            System.out.println("No students found.");
            return;
        }

        if (head.data.getName().equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println("Student with name " + name + " deleted successfully.");
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.getName().equalsIgnoreCase(name)) {
                temp.next = temp.next.next;
                System.out.println("Student with name " + name + " deleted successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with name " + name + " not found.");
    }

    // Delete student by age
    public void deleteStudentByAge(int age) {
        if (head == null) {
            System.out.println("No students found.");
            return;
        }

        while (head != null && head.data.getAge() == age) {
            head = head.next;
        }

        Node temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.data.getAge() == age) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        System.out.println("Students with age " + age + " deleted successfully.");
    }

    // Save student list to a file in table format
    public void saveToFile(String filename) {
        try (FileWriter w = new FileWriter(filename)) {
            w.write("+----------------------+-------+\n");
            w.write("| Name                 | Age   |\n");
            w.write("+----------------------+-------+\n");

            Node curr = head;
            while (curr != null) {
                w.write(curr.data.getFormattedDetails() + "\n");
                curr = curr.next;
            }

            w.write("+----------------------+-------+\n");
            System.out.println("Student data saved successfully in table format.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

// Main class
public class Stud {
    public static void main(String[] args) {
        StudentDetails s = new StudentDetails();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - Add Student");
            System.out.println("2 - Get All Students");
            System.out.println("3 - Get Details by Name");
            System.out.println("4 - Get Details by Age");
            System.out.println("5 - Update Student Name");
            System.out.println("6 - Update Student Age");
            System.out.println("7 - Delete Student by Name");
            System.out.println("8 - Delete Student by Age");
            System.out.println("9 - Save to File");
            System.out.println("10 - Exit");
            System.out.print("Enter choice: ");

            int op = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (op) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter student age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    s.addLast(new Students(name, age));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    s.getAllStudents();
                    break;

                case 3:
                    System.out.print("Enter name: ");
                    s.getDetailsByName(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter age: ");
                    s.getDetailsByAge(sc.nextInt());
                    sc.nextLine();
                    break;

                case 5:
                    System.out.print("Enter current name: ");
                    String oldName = sc.nextLine();
                    System.out.print("Enter new name: ");
                    s.updateStudentName(oldName, sc.nextLine());
                    break;

                case 6:
                    System.out.print("Enter name: ");
                    String nameToUpdate = sc.nextLine();
                    System.out.print("Enter new age: ");
                    s.updateStudentAge(nameToUpdate, sc.nextInt());
                    sc.nextLine();
                    break;

                case 7:
                    System.out.print("Enter name to delete: ");
                    s.deleteStudentByName(sc.nextLine());
                    break;

                case 8:
                    System.out.print("Enter age to delete: ");
                    s.deleteStudentByAge(sc.nextInt());
                    sc.nextLine();
                    break;

                case 9:
                    s.saveToFile("stuts.txt");
                    break;

                case 10:
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
