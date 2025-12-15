package org.youssefn;

import Util.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;
    private static int nextId = 1;

    private List<List<Double>> studentScores = new ArrayList<>();
    private List<Double> finalScores = new ArrayList<>();

    public Course(String courseName, double credits, Department department) {
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.courseId = generateCourseId();
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    private String generateCourseId () {
        return "C-" + department.getDepartmentId() + "-" + String.format("%02d", nextId++);
    }

    public boolean isAssignmentWeightValid() {
        double sum = 0.0;
        for (Assignment assignment : assignments) {
            sum += assignment.weight;
        }
        return Math.abs(sum - 100.0) < 0.001;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);
        finalScores.add(null);

        List<Double> scoresList = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            scoresList.add(null);
        }

        studentScores.add(scoresList);

        return true;
    }

    /**
     * Calculates the weighted average score of all students.
     * Returns an array of averages cast to integers. Uses basic loops.
     */
    public int[] calcStudentsAverage() {
        int[] averageArray = new int[registeredStudents.size()];

        for (int studentIndex = 0; studentIndex < registeredStudents.size(); studentIndex++) {
            List<Double> scoresList = studentScores.get(studentIndex);

            boolean dataMissing = scoresList.size() != assignments.size();
            for (Double score : scoresList) {
                if (score == null) { dataMissing = true; break; }
            }

            if (dataMissing) {
                finalScores.set(studentIndex, 0.0);
                averageArray[studentIndex] = 0;
                continue;
            }

            double totalScore = 0.0;
            for (int i = 0; i < assignments.size(); i++) {
                double score = scoresList.get(i);
                double weight = assignments.get(i).weight;
                totalScore += (score * (weight / 100.0));
            }

            finalScores.set(studentIndex, totalScore);
            averageArray[studentIndex] = (int) Math.round(totalScore);
        }

        return averageArray;
    }

    /**
     * Adds a new assignment to the course, always return true.
     * Updates all existing students score lists with a new null entry.
     */
    public boolean addAssignment(String assignmentName, double weight) {
        Assignment newAssignment = new Assignment();
        newAssignment.name = assignmentName;
        newAssignment.weight = weight;
        assignments.add(newAssignment);

        for (List<Double> studentScore : studentScores) {
            studentScore.add(null);
        }
        return true;
    }

    /**
     * Generates random scores for each assignment and student, and calculates the final score for each student.
     */
    public void generateScores() {
        Random rand = new Random();
        for (int studentIndex = 0; studentIndex < registeredStudents.size(); studentIndex++) {
            List<Double> scoresList = studentScores.get(studentIndex);

            scoresList.clear();
            for (int i = 0; i < assignments.size(); i++) {
                double score = 70.0 + (30.0 * rand.nextDouble());
                scoresList.add(Math.round(score * 10.0) / 10.0);
            }
        }
        calcStudentsAverage();
    }

    /**
     * Displays the scores of a course in a table.
     */
    public void displayScores() {
        System.out.printf("Course: %s(%s)\n", courseName, courseId);
        System.out.printf("%-20s", "Name");
        for (Assignment assignment : assignments) {
            System.out.printf("%15s", assignment.name);
        }
        System.out.printf("%15s\n", "Final Score");

        for (int studentIndex = 0; studentIndex < registeredStudents.size(); studentIndex++) {
            Student student = registeredStudents.get(studentIndex);
            List<Double> scoresList = studentScores.get(studentIndex);
            Double finalScore = finalScores.get(studentIndex);

            if (finalScore == null || scoresList.contains(null)) {
                System.out.printf("%-20s (Data Incomplete)\n", student.getStudentName());
                continue;
            }

            System.out.printf("%-20s", student.getStudentName());
            for (Double aDouble : scoresList) {
                System.out.printf("%15.1f", aDouble);
            }
            System.out.printf("%15.1f\n", finalScore);
        }
        System.out.println("Average Row display logic omitted for brevity.");
    }

    public String toSimplifiedString() {
        String deptName = (department != null) ? department.getDepartmentName() : "N/A";
        return String.format("%s: %s (%.1f credits, %s)",
                courseId, courseName, credits, deptName);
    }

    @Override
    public String toString() {
        String studentList = "";
        for (Student registeredStudent : registeredStudents) {
            studentList += "\n    - " + registeredStudent.toSimplifiedString();
        }
        if (registeredStudents.isEmpty()) { studentList = "None"; }

        String assignmentList = "";
        for (int i = 0; i < assignments.size(); i++) {
            Assignment assignment1 = assignments.get(i);
            assignmentList += assignment1.name + " (" + assignment1.weight + "%)";
            if (i < assignments.size() - 1) { assignmentList += ", "; }
        }

        String deptName = (department != null) ? department.getDepartmentName() : "null";
        String weightValidStatus = isAssignmentWeightValid() ? "valid" : "invalid";

        String output = "Course Details {\n";
        output += "  studentId='" + courseId + "'\n";
        output += ",  courseName='" + courseName + "'\n";
        output += ",  credits=" + credits + "\n";
        output += ",  department=" + deptName + "\n";
        output += ",  assignments=[" + assignmentList + "]\n";
        output += ",  registeredStudents=[" + studentList + "\n  ]\n";
        output += ",  isAssignmentWeightValid: " + weightValidStatus + "\n";
        output += "}";

        return output;
    }
}
