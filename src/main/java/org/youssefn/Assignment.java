package org.youssefn;


import java.util.List;
import java.util.Random;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private List<Integer> scores;
    private static int nextId = 1;

    /**
     * Calculates the average of every score for the assignment.
     * If a score is null, it ignores it and only adds
     * the rest of the scores and divides it by (Total scores - null scores)
     * @return the average of the assignment scores
     */
    public double calcAssignmentAvg() {
        if (this.scores == null) {
            return 0;
        }

        double sum = 0;
        double validScores = 0;

        for (Integer score : scores) {
            if (score != null) {
                sum += score;
                validScores++;
            }
        }

        if (validScores == 0) {
            return 0;
        }

        return sum / validScores;
    }

    /**
     * Generates a random score for an assignment and adds it to the scores list.
     */
    public void generateRandomScore() {
        Random random = new Random();

        for (int i = 0; i < this.scores.size(); i++) {
            int studentScore = switch (random.nextInt(0, 11)) {
                case 0 -> random.nextInt(0, 60);
                case 1, 2 -> random.nextInt(60, 70);
                case 3, 4 -> random.nextInt(70, 80);
                case 5, 6, 7, 8 -> random.nextInt(80, 90);
                case 9, 10 -> random.nextInt(90, 101);
                default -> 0;
            };
            this.scores.set(i, studentScore);
        }
    }

    private static String generateNewId() {
        return String.format("%02d", nextId++);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
