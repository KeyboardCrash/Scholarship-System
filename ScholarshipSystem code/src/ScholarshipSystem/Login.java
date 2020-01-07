package ScholarshipSystem;

/**
 * 
 * The login class checks for password and whether the user is in the database
 * ALso checks if the user is an admin.
 * 
 * @author Brandon Lu, Bryan Huynh
 * @version final (4/12/19)
 * 
 */
public class Login {

	private static boolean isAdmin = false;

	private User currentLoggedIn;

	/**
	 * checks if the user is in the database and has inputed correct password
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean checkAuthorization(int id, String password) {
		UserDatabase udb = new UserDatabase();
		for (User user : udb.getUsers()) {
			if (user.getUserID() == id && user.getPassword().equals(password)) {
				System.out.println("logging in user successfully: " + user.getFirstName() + " " + user.getUserID());
				currentLoggedIn = user;
				if (user.getUserID() > 1000) {
					this.isAdmin = false;
				} else {
					this.isAdmin = true;
				}
				return true;
			}
		}
		System.out.println("no entry found (Invalid login)");
		return false;
	}

	/**
	 * Gets the boolean isAdmin to determine whether the current logging in user is
	 * an admin
	 * 
	 * @return isAdmin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * Returns the current user accessing the login page
	 * 
	 * @return currentLoggedIn
	 */
	public User getLoggedInUser() {
		return currentLoggedIn;
	}

	/**
	 * Sets the boolean to determine whether the current user is an admin.
	 * 
	 * @param isAdmin
	 */
	public static void setAdmin(boolean isAdmin) {
		Login.isAdmin = isAdmin;
	}

}