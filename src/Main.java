import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GradeTracker tracker = new GradeTracker();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("     STUDENT GRADE TRACKER (Java)    ");
        System.out.println("=====================================");

        System.out.print("Load demo data? (y/n): ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("y")) {
            loadDemoData();
        }

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter your choice (1-9): ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateMarks();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    showAverage();
                    break;
                case 7:
                    showHighestLowest();
                    break;
                case 8:
                    generateReport();
                    break;
                case 9:
                    System.out.println("\nThank you for using Student Grade Tracker. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Calculate Average");
        System.out.println("7. Display Highest and Lowest Marks");
        System.out.println("8. Generate Summary Report");
        System.out.println("9. Exit");
        System.out.println("--------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        int id = readInt("Enter student ID: ");

        if (tracker.findById(id) != null) {
            System.out.println("A student with ID " + id + " already exists.");
            return;
        }

        String name = readName("Enter student name: ");
        double marks = readMarks("Enter marks (0-100): ");

        tracker.addStudent(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (tracker.isEmpty()) {
            System.out.println("No students found. Add some first.");
            return;
        }
        printTableHeader();
        for (Student s : tracker.getAllStudents()) {
            printStudentRow(s);
        }
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int id = readInt("Enter student ID to search: ");
        Student s = tracker.findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            printTableHeader();
            printStudentRow(s);
        }
    }

    private static void updateMarks() {
        System.out.println("\n--- Update Marks ---");
        int id = readInt("Enter student ID: ");
        Student s = tracker.findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }
        System.out.println("Current marks of " + s.getName() + ": " + s.getMarks());
        double newMarks = readMarks("Enter new marks (0-100): ");
        tracker.updateMarks(id, newMarks);
        System.out.println("Marks updated! New grade: " + s.getGrade());
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = readInt("Enter student ID to delete: ");
        Student s = tracker.findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        System.out.print("Delete " + s.getName() + "? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            tracker.deleteStudent(id);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void showAverage() {
        System.out.println("\n--- Class Average ---");
        if (tracker.isEmpty()) {
            System.out.println("No students found. Cannot calculate average.");
            return;
        }

        System.out.printf("Average marks of %d students: %.2f%n",
                tracker.getCount(), tracker.calculateAverage());
    }

    private static void showHighestLowest() {
        System.out.println("\n--- Highest and Lowest Marks ---");
        if (tracker.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        Student high = tracker.getHighest();
        Student low = tracker.getLowest();
        System.out.printf("Highest: %s (ID %d) - %.1f marks, Grade %s%n",
                high.getName(), high.getId(), high.getMarks(), high.getGrade());
        System.out.printf("Lowest : %s (ID %d) - %.1f marks, Grade %s%n",
                low.getName(), low.getId(), low.getMarks(), low.getGrade());
    }

    private static void generateReport() {
        System.out.println("\n========== SUMMARY REPORT ==========");
        if (tracker.isEmpty()) {
            System.out.println("No students to report on.");
            return;
        }
        printTableHeader();
        for (Student s : tracker.getAllStudents()) {
            printStudentRow(s);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Total students : " + tracker.getCount());
        System.out.printf("Average marks  : %.2f%n", tracker.calculateAverage());
        Student high = tracker.getHighest();
        Student low = tracker.getLowest();
        System.out.printf("Highest score  : %.1f (%s)%n", high.getMarks(), high.getName());
        System.out.printf("Lowest score   : %.1f (%s)%n", low.getMarks(), low.getName());
        System.out.println("=================================================");
    }

    private static void printTableHeader() {

        System.out.printf("%-8s %-20s %-8s %-6s%n", "ID", "Name", "Marks", "Grade");
        System.out.println("-------------------------------------------------");
    }

    private static void printStudentRow(Student s) {
        System.out.printf("%-8d %-20s %-8.1f %-6s%n",
                s.getId(), s.getName(), s.getMarks(), s.getGrade());
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    private static void loadDemoData() {
        tracker.addStudent(new Student(101, "Asha Raman", 92.5));
        tracker.addStudent(new Student(102, "Karthik Kumar", 78));
        tracker.addStudent(new Student(103, "Meena Iyer", 85));
        tracker.addStudent(new Student(104, "Rahul Verma", 54));
        tracker.addStudent(new Student(105, "Priya Nair", 66.5));
        tracker.addStudent(new Student(106, "Dinesh", 100));
        System.out.println("6 demo students loaded.");
    }
}
