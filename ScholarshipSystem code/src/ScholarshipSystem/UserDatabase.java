package ScholarshipSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * an instance of UserDatabase will be used to serialize and deserialize many
 * instances of users
 * 
 * @author Bryan Huynh
 * @version final (4/12/19)
 * 
 *
 */
public class UserDatabase {
	private ArrayList<User> users = new ArrayList<User>();

	/**
	 * stores Users into a serialized java file to be used between runs.
	 */
	public UserDatabase() {
		users = this.deserialize();
	}

	/**
	 * Gets a list of users
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * assign the list of users
	 * 
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * Get user associated with the entered ID
	 * 
	 * @param enteredID
	 * @return
	 */
	public User getLoggedInUser(int enteredID) {
		User newUser = new User();
		for (User u : deserialize()) {
			if (u.getUserID() == enteredID) {
				newUser = u;
			}
		}
		return newUser;
	}

	/**
	 * adds a new user to the array of users and saves them to the users.ser class
	 * if the user is already inside the database, it won't be duplicated
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		for (User u : users) {
			if (u.getUserID() == user.getUserID()) {
				System.out.println("users already inside databse");
				return;
			}
		}
		users.add(user);
		serialize();
		System.out.println(user.getFirstName() + " has been added to the database");
	}

	/**
	 * takes all the instances in the users arraylist and serializes them to the
	 * users.ser file
	 */
	public void serialize() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users);
			fileOut.close();
			out.close();
			System.out.println("Serialization completed");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * deserializes instances of the user class in users.ser and returns them all in
	 * an arraylist
	 * 
	 * @return
	 */
	public ArrayList<User> deserialize() {
		ArrayList<User> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception k) {
			System.out.println("1. users.ser was not found");
			return new ArrayList<User>();
		}
		return e;
	}

	/**
	 * removes the users.ser file
	 * 
	 * @throws FileNotFoundException
	 */
	public static void clearFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("users.ser");
		writer.print("");
		writer.close();
	}

}
