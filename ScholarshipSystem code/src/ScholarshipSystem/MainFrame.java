package ScholarshipSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * This launches the main GUI applications. This will be used to launch our
 * program.
 * 
 * 
 * @author Jessie Cai, Shaina Rosell, Brandon Lu
 * @version final (4/12/19)
 * 
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;
	public static int topBorderOffset = 35;
	public static int widthSize = 1057 - 15;
	public static int heightSize = 650 - topBorderOffset;
	public static UserDatabase udb;
	private ScholarshipManager scholarshipManager = new ScholarshipManager();
	private StudentManager studentManager;
	private NotificationManager notificationManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		udb = new UserDatabase();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame (window). Initially creates an admin account (does not
	 * create a new account when there already exists an admin account) Then
	 * continues to create a LoginRegistrationPane with the managers created.
	 * 
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1057, 650);

		User admin = new Admin("First", "LastName", "1", udb);
		udb = new UserDatabase();
		studentManager = new StudentManager(udb);
		admin.setUserID(123);
		udb.addUser(admin);
		notificationManager = new NotificationManager(admin.getUserID());

		LoginRegistrationPane loginReg = new LoginRegistrationPane(this, udb, scholarshipManager, studentManager,
				notificationManager);
		setContentPane(loginReg);
		System.out.println("size" + this.getContentPane().getSize());
	}

}
