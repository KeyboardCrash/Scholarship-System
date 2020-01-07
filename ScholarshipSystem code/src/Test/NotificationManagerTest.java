package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ScholarshipSystem.Notification;
import ScholarshipSystem.NotificationManager;

public class NotificationManagerTest {
	
	/**
	 * testing to see if notifications get serialized in the system
	 */
	@Test
	public  void serializingTest() {
		NotificationManager nm = new NotificationManager();
		nm.addUserToMap(123456);
		nm.addUserToMap(1234567);
		Notification note = new Notification("title", "desc");
		Notification note2 = new Notification("title2", "desc");
		nm.sendNotification(123456, note);
		nm.sendNotification(123456, note2);
		nm.sendNotification(1234567, note2);
		nm.serialize();
		nm = new NotificationManager();

		assertEquals("title", nm.getIntegerNotifications().get(123456).get(0).getTitle());
		assertEquals("title2", nm.getIntegerNotifications().get(123456).get(1).getTitle());
		assertEquals("title2", nm.getIntegerNotifications().get(1234567).get(0).getTitle());
	}
	
	/**
	 * testing to get the appropriate list of titles for specific users
	 */
	@Test
	public  void notificationTitlesTest() {
		NotificationManager nm = new NotificationManager();
		nm.addUserToMap(654);
		Notification note = new Notification("title", "desc");
		Notification note2 = new Notification("title2", "desc");
		nm.sendNotification(654, note);
		nm.sendNotification(654, note2);

		assertEquals("title", nm.getNotificationTitles(654).get(0));
		assertEquals("title2", nm.getNotificationTitles(654).get(1));
	}
	

	
	
	
	

}
