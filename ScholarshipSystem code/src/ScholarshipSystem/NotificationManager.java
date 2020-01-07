package ScholarshipSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * Database that manages instances of notifications and saves their states for
 * each user.
 * 
 * @author Bryan Hyunh
 * @version final (4/12/19)
 */
public class NotificationManager {
	private HashMap<Integer, ArrayList<Notification>> userNotifications = new HashMap<>();

	/**
	 * deals with the connection between Users and their notification. links
	 * specific notifications to specific users that can be accessed between
	 * different runs of the program allows for sending notifications to user's to
	 * be possible and easy
	 * 
	 * @param id
	 *            the ID of the user
	 */
	public NotificationManager(int id) {
		setIntegerNotifications(deserialize());
		if (userFound(id) == false) {
			this.addUserToMap(id);
		}

		serialize();
	}

	/**
	 * deals with the connection between Users and their notification. links
	 * specific notifications to specific users that can be accessed between
	 * different runs of the program allows for sending notifications to user's to
	 * be possible and easy
	 * 
	 * @param users
	 */
	public NotificationManager(Integer... users) {
		setIntegerNotifications(deserialize());
		for (Integer user : users) {
			addUserToMap(user);
		}
		serialize();
	}

	/**
	 * returns a string list of notification titles that are under the user's id
	 * 
	 * @param userID
	 * @return
	 */
	public ArrayList<String> getNotificationTitles(int userID) {
		ArrayList<String> notificationTitles = new ArrayList<>();

		ArrayList<Notification> notifs = getNotifications(userID);
		System.out.println(userID);
		for (Notification n : notifs) {
			notificationTitles.add(n.getTitle());
			// System.out.println(n.getTitle());
		}

		return notificationTitles;

	}

	/**
	 * adds a user to the system, allowing them to now recieve notifications.
	 * 
	 * @param user
	 */
	public void addUserToMap(Integer user) {
		userNotifications.put(user, new ArrayList<Notification>());
		serialize();
	}

	/**
	 * gets notifications of a specific user
	 * 
	 * @param Integerid
	 * @return
	 */
	public ArrayList<Notification> getNotifications(int userid) {
		return userNotifications.get(userid);
	}

	/**
	 * adds a user if absent from key set
	 * 
	 * @param user
	 */
	private boolean userFound(Integer user) {
		boolean result = false;
		for (Integer u : userNotifications.keySet()) {
			if (u.equals(user))
				result = true;
		}
		// System.out.println(user + " was absent from the system, but it's gucci now");

		return result;

	}

	/**
	 * sends notification to user. NOTE!!! the notification will be duplicated and
	 * argument cannot be used to reference the notification associated with user
	 * 
	 * @param user
	 * @param notification
	 */
	public void sendNotification(Integer user, Notification notification) {
		// userFound(user);
		ArrayList<Notification> notif = getNotifications(user);
		notif.add(new Notification(notification));
		userNotifications.put(user, notif);
		serialize();
	}

	/**
	 * returns all the users of a certain notification
	 * 
	 * @param notification
	 * @return
	 */
	public ArrayList<Integer> IntegersOfNotification(Notification notification) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ArrayList<Integer> users = new ArrayList<Integer>(userNotifications.keySet());
		for (Integer user : users) {
			userFound(user);
			for (Notification note : getNotifications(user)) {
				if (note.compareTo(notification)) {
					ret.add(user);
				}
			}
		}
		return ret;
	}

	/**
	 * get notification instance associated with user
	 * 
	 * @param user
	 * @param notification
	 * @return
	 */
	public Notification getIntegersInstanceOfNotification(Integer user, Notification notification) {
		userFound(user);
		for (Notification note : getNotifications(user)) {
			if (note.compareTo(notification)) {
				return note;
			}
		}
		return null;
	}

	/**
	 * marks the notification of a user as read
	 * 
	 * @param user
	 * @param notification
	 */
	public void userReadNotification(Integer user, Notification notification) {
		userFound(user);
		notification = getIntegersInstanceOfNotification(user, notification);
		notification.markAsRead();
		serialize();
	}

	/**
	 * sends out notification to multiple users
	 * 
	 * @param notification
	 * @param users
	 */
	public void sendMassNotification(Notification notification, Integer... users) {
		for (Integer user : users) {
			sendNotification(user, notification);
		}
		serialize();
	}

	/**
	 * sends out notifications to multiple users
	 * 
	 * @param notification
	 * @param users
	 */
	public void sendMassNotification(Notification notification, ArrayList<Integer> users) {
		for (Integer user : users) {
			sendNotification(user, notification);
		}
		serialize();
	}

	/**
	 * 
	 * 
	 * Neatly printout the users and their notifications
	 * 
	 * 
	 */
	public void printOut() {
		System.out.println(
				"----------------------------------------------------start Printout------------------------------------------");
		ArrayList<Integer> users = new ArrayList<Integer>(userNotifications.keySet());
		for (Integer user : users) {
			System.out.println("--------------------------------------------" + user
					+ "--------------------------------------------");
			for (Notification note : getNotifications(user)) {
				System.out.println(note);
				System.out.println();
				System.out.println("##################################");
			}
		}
		System.out.println(
				"----------------------------------------------------end Printout------------------------------------------");

	}

	/**
	 * 
	 * Returns the hash map from this class
	 * 
	 * @return userNotifications
	 */
	public HashMap<Integer, ArrayList<Notification>> getIntegerNotifications() {
		return userNotifications;
	}

	/**
	 *
	 * Sets the userNotifications
	 * 
	 * @param userNotifications
	 */
	public void setIntegerNotifications(HashMap<Integer, ArrayList<Notification>> userNotifications) {
		this.userNotifications = userNotifications;
	}

	/**
	 *
	 * Saves the notifications and their link to Users for future use
	 * 
	 */
	public void serialize() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("notifications.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userNotifications);
			fileOut.close();
			out.close();
			System.out.println("notifications.ser was found, and has been serialized");
		} catch (FileNotFoundException e) {
			System.out.println("notifications.ser not found");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Retrieves the notifications and their link for users from file directory
	 * 
	 * @return hashmap
	 * 
	 * 
	 */
	public HashMap<Integer, ArrayList<Notification>> deserialize() {
		HashMap<Integer, ArrayList<Notification>> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("notifications.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (HashMap<Integer, ArrayList<Notification>>) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception k) {
			System.out.println("students.ser was not found");
			return new HashMap<Integer, ArrayList<Notification>>();
		}
		return e;
	}

}