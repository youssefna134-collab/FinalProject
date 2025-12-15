package org.youssefn;


import Util.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class Student {
    private String studentId;
    @Setter
    private String studentName;
    @Setter
    private Gender gender;
    @Setter
    private Address address;
    @Setter
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = generateNewId();
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * Adds a course to course list of a student.
     * @param course the course of the student
     * @return true if the course was successfully added
     * and false if it was already in the list
     */
    public boolean registerCourse(Course course) {
        if (this.registeredCourses.contains(course)) {
            return false;
        }

        this.registeredCourses.add(course);
        if (!course.getRegisteredStudents().contains(this)) {
            course.registerStudent(this);
        }

        return true;
    }

    /**
     * Removes course from course list of a student
     * @param course the course of the student
     * @return true if the course was successfully removed
     * and false if it was never in the course.
     */
    public boolean dropCourse(Course course) {
        if (!this.registeredCourses.contains(course)) {
            return false;
        }

        this.registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        return true;
    }

    public enum Gender {
        MALE, FEMALE
    }

    private static String generateNewId() {
        return String.format("S%06d", nextId++);
    }

    private String formatRegisteredCourses() {
        String courseListString = "";
        for (Course course : registeredCourses) {
            courseListString += String.format("  - %s\n", course.toSimplifiedString());
        }

        return courseListString;
    }

    /**
     * Simplified version of the toString method
     * @return studentId, studentName and departmentname in a simplified string
     */
    public String toSimplifiedString() {
        if (department == null) {
            return "Not available";
        } else {
            String departmentName = department.getDepartmentName();
            return String.format("Student{studentId=%s, studentName=%s, departmentName=%s}",
                    studentId, studentName, departmentName);
        }
    }

    public String toString() {
        String studentDetails = String.format(
                "Student{studentId=%s, studentName=%s, gender=%s, address=%s, department=%s}",
                studentId,
                studentName,
                gender,
                (address == null) ? "null" : address.toString(),
                (department == null) ? "null" : department.getDepartmentName()
        );

        return studentDetails + "\nRegistered Courses:\n" + formatRegisteredCourses();
    }
}
