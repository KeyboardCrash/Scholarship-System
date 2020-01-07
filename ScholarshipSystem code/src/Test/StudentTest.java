package Test;
import static org.junit.Assert.*;
import org.junit.*;

import ScholarshipSystem.*;

public class StudentTest {
    User student;
    UserDatabase udb = new UserDatabase();

    @Before
    public void setup() {
        // Setup a student with arbitrary parameters
        // Name, LastName
        // Pass = 123456
        // Level = Undergraduate
        // Faculty = Engineering
        // Year = 3
        // GPA = 4.0
        
        student = new Student("Name", "LastName", "123456", Enumerations.level.UNDERGRADUATE, Enumerations.faculty.Engineering, 3, 4.0, udb);
        udb.serialize();
    }
    
    @Test
    public void checkCorrectFirstName() {
        String localFirstName = student.getFirstName();
        assertEquals("Expected Firstname of 'Name'", "Name", localFirstName);
    }
    
    @Test
    public void checkCorrectLastName() {
        String localLastName = student.getLastName();
        assertEquals("Expected Last Name of 'LastName'", "LastName", localLastName);
    }
    
    @Test
    public void checkStudentLoginSuccessful() {
        Login login = new Login();
        int localID = student.getUserID();
        String localPassword = student.getPassword();
        assertTrue("Testing login for student Name, Lastname with password 123456", login.checkAuthorization(localID, localPassword));
    }
    
    @Test
    public void checkStudentLevel() {
        Enumerations.level localLevel = ((Student)(student)).getLevel();
        assertEquals("Expected Level of Enumerations.level.UNDERGRADUATE", Enumerations.level.UNDERGRADUATE, localLevel);
    
    }
    
    @Test
    public void checkStudentFaculty() {
        Enumerations.faculty localFaculty = ((Student)(student)).getFaculty();
        assertEquals("Expected Faculty of Enumerations.faculty.Engineering", Enumerations.faculty.Engineering, localFaculty);
    }
    
    @Test
    public void checkStudentYear() {
        int localYear = ((Student)(student)).getYear();
        assertEquals("Expected Year of 3", 3, localYear);
    }
    
    @Test
    public void checkStudentGPA() {
        double localGPA = ((Student)(student)).getGPA();
        System.out.print("Testing if GPA 4.0 is exact");
        assertEquals(4.0, localGPA, 0);
    }
    
    
    
    

}
