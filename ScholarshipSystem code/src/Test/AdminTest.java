package Test;
import static org.junit.Assert.*;
import org.junit.*;

import ScholarshipSystem.*;

public class AdminTest {
    Login login = new Login();

    @Test
    public void checkIfUseAccountLoginSuccess() {
        System.out.println("Test if the current user logged in is an admin user");
        assertTrue("Test admin with login 123 and pass 1", login.checkAuthorization(123, "1"));
        
    }
    
    @Test
    public void checkAdminBadPassFailed () {
        assertFalse("Test bad password with valid admin account", login.checkAuthorization(123, "321"));
    }
    
    @Test
    public void checkAdminBadCredsFailed () {
        assertFalse("Test bad userID and pass", login.checkAuthorization(12, "21"));
    }
    

}