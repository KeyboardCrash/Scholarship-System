package ScholarshipSystem;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * 
 * This class creates the attributes for the user class Includes ID, password,
 * first name, last name as well as getter/set methods for each attribute
 * 
 * @author Jessie Cai, Brandon Lu, Shaina Rosell
 * @version final (4/12/19)
 */
public class User implements Serializable {

	private int userID;
	private String password;
	private String firstName;
	private String lastName;

	// Constructor for general user, will randomly generate ID
	/**
	 * To be used to store data about the user, and allows them to use the system
	 * based on their specific role
	 * 
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param udb
	 */

	public User(String firstName, String lastName, String password, UserDatabase udb) { // get this from parsing valid
																						// registration form...
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		generateUserID(udb);

	}

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * To be used to store data about the user, and allows them to use the system
	 * based on their specific role
	 * 
	 * @param firstName
	 * @param lastName
	 * @param password
	 */
	public User(String firstName, String lastName, String password) { // get this from parsing valid registration
																		// form...
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		// generateUserID(new UserDatabase());
	}

	/**
	 * generates a random unique user id
	 * 
	 * @param udb
	 * 
	 */
	private void generateUserID(UserDatabase udb) {
		int range = (9999999 - 1000000) + 1;
		int currentNum = (int) (Math.random() * range) + 1000000;

		// Make an ArrayList and store the ID's of all the users in it
		ArrayList<Integer> listUserID = new ArrayList<Integer>();

		for (User user : udb.deserialize()) {
			listUserID.add(user.getUserID());
		}

		// Randomly generate until the ID is not in the list, will break if trying to
		// generate more ID's than the range
		while (listUserID.contains(currentNum)) {
			currentNum = (int) (Math.random() * range) + 1;
		}

		this.userID = currentNum;

		try {
			// Add this user to the database
			udb.addUser(this);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * 
	 * Gets the string of userID, first name, and last name
	 * 
	 */
	@Override
	public String toString() {
		return getUserID() + " " + getPassword() + " " + getFirstName() + " " + getLastName();
	}

	/**
	 * 
	 * Get the userID
	 * 
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 *
	 * Set the userID
	 * 
	 * @param userID
	 * 
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * 
	 * Get the password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * set the password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * Get the first name
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * Get the last name
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the object
	 * 
	 * 
	 * @return User
	 */
	public User getUser() {
		return this;
	}

}
