import java.util.ArrayList;

public class GradeTracker {

    private ArrayList<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (findById(student.getId()) != null) {
            return false;
        }
        students.add(student);
        return true;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateMarks(int id, double newMarks) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        s.setMarks(newMarks);
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        students.remove(s);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public int getCount() {
        return students.size();
    }

    public double calculateAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (Student s : students) {
            total += s.getMarks();
        }
        return total / students.size();
    }

    public Student getHighest() {
        if (students.isEmpty()) {
            return null;
        }
        Student best = students.get(0);
        for (Student s : students) {
            if (s.getMarks() > best.getMarks()) {
                best = s;
            }
        }
        return best;
    }

    public Student getLowest() {
        if (students.isEmpty()) {
            return null;
        }
        Student worst = students.get(0);
        for (Student s : students) {
            if (s.getMarks() < worst.getMarks()) {
                worst = s;
            }
        }
        return worst;
    }
}
