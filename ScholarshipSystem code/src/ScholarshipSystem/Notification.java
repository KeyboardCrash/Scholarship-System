package ScholarshipSystem;

import java.io.Serializable;

/**
 *
 * Creates a notification that will be sent to either the admin or a student.
 * Objects for each user will be managed by NotificationManager.
 * 
 * @author Bryan Hyunh
 * @version final (4/12/19)
 */
public class Notification implements Serializable {
	private boolean read = false;
	private String message = null;
	private String title;

	/**
	 * A data structure that holds messages and titles To be sent to Users for the
	 * purpose of email style notifications
	 * 
	 * @param title
	 *            Title of the notification
	 * @param message
	 */
	public Notification(String title, String message) {
		this.title = title;
		setMessage(message);
	}

	/**
	 * 
	 * @param note
	 */
	Notification(Notification note) {
		this.message = note.getMessage();
		this.title = note.getTitle();
	}

	/**
	 * compares if another notification is the same
	 * 
	 * @param notification
	 * @return
	 */
	boolean compareTo(Notification notification) {

		if (!message.equals(notification.getMessage())) {
			return false;
		}
		if (!title.equals(notification.getTitle())) {
			return false;
		}
		return true;
	}

	/**
	 * @return the title, message and read status as a string
	 * 
	 */
	public String toString() {
		return "Read: " + read + "\n" + "Title: " + title + "\n" + "message: " + message + "";
	}

	/**
	 * Returns whether the notification has been read
	 * 
	 * @return read
	 * 
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * 
	 * @return the title of the notification
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * Setting the string title
	 * 
	 * @param title
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *
	 * Marks the notification as read
	 * 
	 */
	public void markAsRead() {
		setRead(true);
	}

	/**
	 * 
	 * Sets the message to be read
	 * 
	 * @param read
	 */
	public void setRead(boolean read) {
		this.read = read;
	}

	/**
	 * 
	 * Returns the message
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *
	 * Sets the message
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
