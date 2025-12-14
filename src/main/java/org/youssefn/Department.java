package org.youssefn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    @Setter private String departmentName;
    private static int nextId = 1;

    /**
     * checks if department name is valid:
     * it should only contain letters or spaces
     * @param departmentName the department name input
     * @return true if department name is valid and false if it is not
     */
    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null) {
            return false;
        }
        for (int i = 0; i < departmentName.length(); i++) {
            char currentChar = departmentName.charAt(i);
            if (!Character.isLetter(currentChar)) {
                if (currentChar != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static String generateNewId() {
        return String.format("D%02d", nextId++);
    }

    public Department(String departmentName) {
        if (!isDepartmentNameValid(departmentName)) {
            this.departmentName = null;
            this.departmentId = null;
        }
        else {
            this.departmentName = departmentName;
            this.departmentId = generateNewId();
        }
    }
}
