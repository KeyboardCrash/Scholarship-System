package Test;

import static org.junit.Assert.*;
import org.junit.*;

import ScholarshipSystem.*;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ScholarshipTest {
    ScholarshipManager sc;
    UserDatabase ub;
    Student s;


    @Before
    public void ScholarshipSetup(){
        try {
            FileChannel.open(Paths.get("scholarships.ser"), StandardOpenOption.WRITE).truncate(0).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc = new ScholarshipManager();
        ub = new UserDatabase();
        s = new Student("Frank", "Man", "123", Enumerations.level.UNDERGRADUATE, Enumerations.faculty.Graduate_Studies,
                3, 3.5, ub);

    }

    @Test
    public void TestCreateGUI(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals("Expected title of 'Hello'", "Hello", sc.getScholarship("Hello").getTitle());
    }

    @Test
    public void TestCreateGUIDonarCorrect(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals("Expected donar of 'Frank'", "Frank", sc.getScholarship("Hello").getDonor());
    }

    @Test
    public void TestCreateGUIFacultyCorrect(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals("Expected Faculty of 'Environmental Design'", Enumerations.faculty.Environmental_Design, sc.getScholarship("Hello").getFaculty());
    }

    @Test
    public void TestCreateGUIAmountCorrect(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(30.0, sc.getScholarship("Hello").getAmount(), 0);
    }

    @Test
    public void TestCreateGUIDeadlineCorrect(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(sc.getScholarship("Hello").dateFormat(), "10/31/2011");
    }

    @Test
    public void CanBeAwarded(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(sc.getScholarship("Hello").getCanbeAwarded(), true);
    }


    @Test
    public void ChangeTitle(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Hello", "Title", "Dave");
        assertEquals("Expected title of 'Dave'", "Dave", sc.getScholarship("Dave").getTitle());
    }

    @Test
    public void ChangeDonar(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Donor", "Simba");
        assertEquals("Expected Donar of 'Simba'", "Simba", sc.getScholarship("Dave").getDonor());
    }

    @Test
    public void ChangeAmount(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Amount", "10");
        assertEquals(10.0, sc.getScholarship("Dave").getAmount(), 0);
    }

    @Test
    public void ChangeDateDay(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Date Day", "10");
        assertEquals(10.0, sc.getScholarship("Dave").getDeadlineDay(), 0);
    }

    @Test
    public void ChangeDateMonth(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Date Month", "5");
        assertEquals(5, sc.getScholarship("Dave").getDeadlineMonth(), 0);
    }

    @Test
    public void ChangeDateYear(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Date Year", "1898");
        assertEquals(1898, sc.getScholarship("Dave").getDeadlineYear(), 0);
    }


    @Test
    public void changeYearNotaNumber(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        Boolean success = sc.editScholarship("Dave", "Date Year", "a");
        assertEquals(success, false);
    }

    @Test
    public void ChangeAwardlimit(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Award Limit", "3");
        assertEquals(3, sc.getScholarship("Dave").getAwardLimit(), 0);
    }

    @Test
    public void ChangeAge(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Age", "3");
        assertEquals("Value of Age should be '3'", sc.getScholarship("Dave").getbaseline("Age"), "3");
    }

    @Test
    public void changeLeve(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Level", "3");
        assertEquals("Value of Level should be '3'", sc.getScholarship("Dave").getbaseline("Level"), "3");
    }

    @Test
    public void changeYear(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Year", "6");
        assertEquals("Value of Year should be '6", sc.getScholarship("Dave").getbaseline("Year"), "6");
    }


    @Test
    public void changeMajor(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Major", "9");
        assertEquals("Value of Major should be '9", sc.getScholarship("Dave").getbaseline("Major"), "9");
    }

    @Test
    public void changeGender(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Gender", "unicorn");
        assertEquals("Value of Gender should be '6", sc.getScholarship("Dave").getbaseline("Gender"), "unicorn");
    }

    @Test
    public void changeCitizen(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "Citizen", "three");
        assertEquals("Value of Citizen should be 'three", sc.getScholarship("Dave").getbaseline("Citizen"), "three");
    }

    @Test
    public void changeExtraCuric(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.editScholarship("Dave", "ExtraCuric", "five");
        assertEquals("Value of ExtraCuric should be 'five", sc.getScholarship("Dave").getbaseline("ExtraCuric"), "five");
    }

    @Test
    public void ChangeIncorrectField(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        Scholarship dave= sc.getScholarship("Dave");
        sc.editScholarship("Dave", "No", "five");
        assertEquals(sc.getScholarship("Dave"), dave);
    }

    @Test
    public void addApplication(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        assertEquals(s, sc.getApplication("Dave", s).getApplicant());
    }

    @Test
    public void addApplicationWrongScholarship(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Frank", s,app);
        assertEquals(0, sc.getScholarship("Dave").getApplicants().size());
    }

    @Test
    public void editApplication(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.fillApplication("Dave", s.getUserID(), "Gender", "Male");
        assertEquals("Male", sc.getApplication("Dave", s).getField("Gender"));
    }

    @Test
    public void editApplicationWrongField(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.fillApplication("Dave", s.getUserID(), "asd", "Male");
        assertEquals("Female",
                sc.getApplication("Dave", s).getField("Gender")
                );
    }

    @Test
    public void removeApplication(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.removeApplication("Dave", s.getUserID());
        assertEquals(0, sc.getScholarship("Dave").getApplicants().size());
    }

    @Test
    public void removeApplicationWrongScholarship(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.removeApplication("Hello", s.getUserID());
        assertEquals(1, sc.getScholarship("Dave").getApplicants().size());
    }

    @Test
    public void removeApplicationWrongID(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.removeApplication("Dave", 123);
        assertEquals(1, sc.getScholarship("Dave").getApplicants().size());
    }

    @Test
    public void changeApplicationStatus(){
        sc.addScholarshipGUI("Dave", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.getScholarship("Dave").openApplication();
        sc.serialize();
        ScholarshipApplication app =  new ScholarshipApplication("Dave", "1", "Female", "Canadian", "Science", "3.5",
                "hello", "None", "5", s);
        sc.addApplication("Dave", s,app);
        sc.getApplication("Dave", s).setStatus(Enumerations.status.ACCEPTED);
        assertEquals(Enumerations.status.ACCEPTED,
                sc.getScholarship("Dave").getApplication(s.getUserID()).getStatus());
    }

    @Test
    public void searchTitles(){
        sc.addScholarshipGUI("Kitten", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Transfer", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Cat", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Magical", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Cannon", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Cows", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals("[Cows, Cat, Cannon, Kitten, Magical, Transfer]", sc.findTitle("Cow").toString());
    }

    @Test
    public void removeScholarship(){
        sc.addScholarshipGUI("Hello", "Frank", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.removeScholarship("Hello");
        assertEquals(0, sc.Scholarships.size());
    }

    @Test
    public void getTitlebyFaculty(){
        sc.addScholarshipGUI("Kitten", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Transfer", "Dave", Enumerations.faculty.Social_Work, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(1, sc.getTitleByFaculty(Enumerations.faculty.Social_Work).size());
    }

    @Test
    public void getTitlebyFaculty2(){
        sc.addScholarshipGUI("Kitten", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Transfer", "Dave", Enumerations.faculty.Social_Work, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(0, sc.getTitleByFaculty(Enumerations.faculty.Business).size());
    }

    @Test
    public void getTitlebyFaculty3(){
        sc.addScholarshipGUI("Kitten", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Transfer", "Dave", Enumerations.faculty.Social_Work, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        sc.addScholarshipGUI("Magical", "Dave", Enumerations.faculty.Environmental_Design, 30.0,
                3, 31, 10, 2011, "18", "3.0", "First",
                "Second", "CS", "other", "1", "Canada",
                "1");
        assertEquals(2, sc.getTitleByFaculty(Enumerations.faculty.Environmental_Design).size());
    }




}
