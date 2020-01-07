package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ScholarshipSystem.User;
import ScholarshipSystem.UserDatabase;

public class UserDatabaseTest {
	
	/**
	 * adding user to the database and trying to retrieve them under a new instance of the udb
	 */
	@Test
	public void SerializingTest() {
		UserDatabase udb = new UserDatabase();
		User user = new User();
		udb.addUser(user);
		udb = new UserDatabase();
		assertEquals(udb.getUsers().get(0).getUserID(), user.getUserID());
	}
}
