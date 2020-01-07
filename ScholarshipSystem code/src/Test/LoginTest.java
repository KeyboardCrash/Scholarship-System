package Test;
import static org.junit.Assert.*;
import org.junit.*;

import ScholarshipSystem.*;

public class LoginTest {
    User someuser;
    UserDatabase udb = new UserDatabase();
    int userID;
    
    @Before
    public void setupLogin() {
        UserDatabase udb = new UserDatabase();
    
        someuser = new User("Test", "User", "123456");
        userID = someuser.getUserID();
    
    }
    
    @Test
    public void registerAndLoginSuccessful() {
        System.out.println("Testing creating a new student account Test User with password 123456");
        
        userID = someuser.getUserID();
        
        udb.addUser(someuser);
        
        udb.serialize();
        
        Login login = new Login();
        
        assertTrue("Test if user Test User can be logged in successfully", login.checkAuthorization(userID, "123456"));
        
    }
    
    @Test
    public void registerAndBadPasswordFailed() {
        System.out.println("Testing creating a new student account Test User with password 123456");
        
        
        userID = someuser.getUserID();
        
        udb.addUser(someuser);
        
        udb.serialize();
        
        System.out.println("Test if user successfully registered");
        
        Login login = new Login();
        
        assertFalse("Test if user Test User can be logged in with improper credentials", login.checkAuthorization(userID, "13579"));
        
    }
    
    @Test
    public void loginBadUserIDFailed() {
        System.out.println("Testing login of user that does not exist. Suppose User ID 999999");
    
        Login login = new Login();
    
        assertFalse("Test if user Test User can be logged in with bad userID", login.checkAuthorization(999999, "123456"));
    
    }
    
    @Test
    public void loginBadUserIDAndPassFailed() {
        System.out.println("Testing login of user that does not exist. Suppose User ID 999999 and pass abcdefghijklmnop");
        
        Login login = new Login();
        
        assertFalse("Test if user Test User can be logged in with bad userID and password", login.checkAuthorization(999999, "abcdefghijklmnop"));
        
    }
    
}
