# Student Grade Tracker (Java)

A beginner-friendly, menu-driven console application that stores and analyses student grades.
It uses only core Java: **classes/objects, ArrayList, Scanner, loops, if/else, switch and methods**.
No frameworks, no database.

## Project structure

```
StudentGradeTracker/
├── README.md
└── src/
    ├── Student.java        <- one student (id, name, marks, grade)
    ├── GradeTracker.java   <- manages the list of students (add, find, update, delete, stats)
    └── Main.java           <- menu + user input (Scanner) + printing
```

## How the code is organised (simple English)

| File | Role | Key idea |
|------|------|----------|
| `Student.java` | A blueprint for one student. Stores `id`, `name`, and `marks`. It keeps the student data and supports updates while the program itself handles display formatting. | **Class & Object**, **Encapsulation** (private fields + getters/setters) |
| `GradeTracker.java` | Holds an `ArrayList<Student>` and has all the logic: `addStudent`, `findById`, `updateMarks`, `deleteStudent`, `calculateAverage`, `getHighest`, `getLowest`. It never prints or reads input. | **Separation of concerns**, **ArrayList**, **loops** |
| `Main.java` | Shows the menu, reads input with `Scanner`, validates it, and calls `GradeTracker`. Contains one method per menu option plus small helper methods (`readInt`, `readMarks`, `readName`). | **Methods**, **while loop**, **switch**, **input validation** |

Every line inside the three files has a comment explaining what it does.

### Validation rules built in

* Marks must be a number between **0 and 100** (otherwise the program asks again).
* IDs must be whole numbers and **unique** (duplicates are rejected).
* Names cannot be empty.
* Menu choice must be a number (letters do not crash the program).
* Delete asks for a **y/n confirmation**.

## How to run in VS Code

1. **Install the JDK** (version 11 or newer). Check in a terminal: `java -version` and `javac -version`.
2. **Install the "Extension Pack for Java"** in VS Code (Extensions icon → search *Extension Pack for Java* → Install).
3. Unzip the project, then **File → Open Folder…** and choose the `StudentGradeTracker` folder.
4. Open `src/Main.java`. Click **Run** above `public static void main` (or press **F5**).
5. Type your answers in the **Terminal** panel at the bottom.

**Or run from the terminal** (inside the `StudentGradeTracker` folder):

```bash
mkdir out
javac -d out src/*.java
java -cp out Main
```

(On Windows PowerShell use the same commands; `mkdir out` is enough.)

## Sample session

```
=====================================
     STUDENT GRADE TRACKER (Java)
=====================================
Load demo data? (y/n): n

------------- MENU -------------
1. Add Student
...
Enter your choice (1-9): 1

--- Add Student ---
Enter student ID: 201
Enter student name: Divya Menon
Enter marks (0-100): 150
Marks must be between 0 and 100.
Enter marks (0-100): abc
Invalid input. Please enter a number.
Enter marks (0-100): 88
Student added successfully!

(add 202 Arun Das 45, then 203 Sneha Pillai 73.5)

Enter your choice (1-9): 2

--- All Students ---
ID       Name                 Marks
----------------------------------------
201      Divya Menon          88.0
202      Arun Das             45.0
203      Sneha Pillai         73.5

Enter your choice (1-9): 4

--- Update Marks ---
Enter student ID: 202
Current marks of Arun Das: 45.0
Enter new marks (0-100): 62
Marks updated successfully.

Enter your choice (1-9): 5

--- Delete Student ---
Enter student ID to delete: 203
Delete Sneha Pillai? (y/n): y
Student deleted.

Enter your choice (1-9): 6

--- Class Average ---
Average marks of 2 students: 75.00

Enter your choice (1-9): 7

--- Highest and Lowest Marks ---
Highest: Divya Menon (ID 201) - 88.0 marks
Lowest : Arun Das (ID 202) - 62.0 marks

Enter your choice (1-9): 8

========== SUMMARY REPORT ==========
ID       Name                 Marks
----------------------------------------
201      Divya Menon          88.0
202      Arun Das             62.0
-------------------------------------------------
Total students : 2
Average marks  : 75.00
Highest score  : 88.0 (Divya Menon)
Lowest score   : 62.0 (Arun Das)
=================================================

Enter your choice (1-9): 9

Thank you for using Student Grade Tracker. Goodbye!
```

## Demo data

At start-up the program asks **"Load demo data? (y/n)"**. Answer `y` to load five sample students
(IDs 101–105) so you can try every menu option immediately.

## Ideas to extend it (practice)

* Save and load students from a text file (`FileWriter` / `Scanner(File)`).
* Sort students by marks or name (`Collections.sort` with a `Comparator`).
* Support multiple subjects per student (an `ArrayList<Double>` inside `Student`).
* Show a grade distribution (how many A's, B's, ...).
