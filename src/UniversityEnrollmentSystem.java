import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int maxStudents;
    private int enrolledStudents;
    private String prerequisite;

    public Course(String name, int maxStudents, String prerequisite) {
        this.name = name;
        this.maxStudents = maxStudents;
        this.enrolledStudents = 0;
        this.prerequisite = prerequisite;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void enroll(String studentName, List<String> completedCourses)
            throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents >= maxStudents) {
            throw new CourseFullException("CourseFullException - " + name + " is already full.");
        }

        if (prerequisite != null && !completedCourses.contains(prerequisite)) {
            throw new PrerequisiteNotMetException(
                    "PrerequisiteNotMetException - Complete " + prerequisite + " before enrolling in " + name + "."
            );
        }

        enrolledStudents++;
        System.out.println(studentName + " successfully enrolled in " + name + ".");
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define a course
        Course advancedJava = new Course("Advanced Java", 2, "Core Java");

        // Simulate student info
        String studentName = "Alice";
        List<String> completedCourses = new ArrayList<>(); // Student hasn't completed "Core Java"

        try {
            System.out.print("Enroll in Course: " + advancedJava.getName() + "\n");
            System.out.println("Prerequisite: " + advancedJava.getPrerequisite());
            System.out.println("Status: Prerequisite not completed");

            // Attempt to enroll
            advancedJava.enroll(studentName, completedCourses);

        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

