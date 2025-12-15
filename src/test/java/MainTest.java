import Util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.youssefn.Address;
import org.youssefn.Department;

public class MainTest {
    @Test
    @DisplayName("IsPostalCodeValid: A2C?D7 -> false")
    void testIsPostalCodeValid1() {
        String postalCode = "A2C?D7";
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("IsPostalCodeValid: A2C3D4 -> true")
    void testIsPostalCodeValid2() {
        String postalCode = "A2C3D4";
        boolean expected = true;
        boolean actual = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("IsPostalCodeValid: Computer Science -> true")
    void testIsDepartmentNameValid1() {
        String departmentName = "Computer Science";
        boolean expected = true;
        boolean actual = Department.isDepartmentNameValid(departmentName);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("IsPostalCodeValid: 123 -> false")
    void testIsDepartmentNameValid2() {
        String departmentName = "123";
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(departmentName);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("ToTitleCase: computer science -> Computer Science")
    void testToTitleCase() {
        String str = "computer science";
        String expected = "Computer Science";
        String actual = Util.toTitleCase(str);

        Assertions.assertEquals(expected,actual);
    }
}
