package ScholarshipSystem;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

import java.awt.Image;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * 
 * This class constructs the graphical user interface for the login and
 * registration for the scholarship system application. This encompasses login
 * and registration. This GUI class is used to verify whether users are able to
 * access the main interface.
 * 
 * @author Jessie Cai, Shaina Rosell, Brandon Lu
 * @version final (4/12/19)
 * 
 */
public class LoginRegistrationPane extends JPanel {

	private JPasswordField textField_PasswordLogin;
	private JTextField textField_UserID;
	private JTextField textField_firstName;
	private JTextField textField_lastName;
	private JTextField textField_year;
	private JPasswordField textField_PasswordRegistration;
	private JPanel startScreen = new JPanel();
	private JPanel regForm = new JPanel();
	private JPanel registerTop = new JPanel();
	private JPanel confirmationScreen = new JPanel();
	private JLabel lblFaculty;
	private JLabel lblYear;
	private JLabel lblPassword;
	private JButton btnConfirm;
	private JLabel loginError;
	private JLabel lblNewLabel;
	private JLabel lblPassword_1;
	private JTextField textField_gpa;
	private JLabel lblRegistrationSuccessful;
	private JPanel panel;
	private JLabel lblGenerateID;
	private JLabel logoPicture;
	private JLabel loginTitle;
	private JLabel lblWelcome;
	private JPanel bar;

	/**
	 * Login screen for this system. Also contains a sub-panel for registering for a
	 * student account. (admin account is already created in MainFrame)
	 * 
	 * @param frame
	 *            - the window used for the system
	 * @param udb
	 *            - userDataBase containing all user objects
	 * @param scholarshipManager
	 *            - scholarshipManager containing scholarships. To keep the
	 *            scholarships consistent when they are changed
	 * @param studentManager
	 *            - studentManager containing students. To keep the student objects
	 *            consistent when they are changed
	 * @param nm
	 *            - notificationManager. To keep the notification objects consistent
	 *            when they are changed
	 */

	public LoginRegistrationPane(JFrame frame, UserDatabase udb, ScholarshipManager scholarshipManager,
			StudentManager studentManager, NotificationManager nm) {

		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(100, 100, MainFrame.widthSize, MainFrame.heightSize);

		String[] levelStrings = { "Undergraduate", "Graduate", "Doctorate", "PhD" };
		String[] facultyStrings = { "Science", "Arts", "Medicine", "Environmental_Design", "Graduate_Studies",
				"Business", "Kinesiology", "Law", "Engineering", "Social Work", "Veterinary_Medicine", "Education" };
		Image red = new ImageIcon(this.getClass().getResource("/ScholarshipSystem/loginLogo.png")).getImage();
		Image scaledred = red.getScaledInstance(90, 75, Image.SCALE_SMOOTH);

		regForm.setBackground(Color.WHITE);
		regForm.setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		regForm.setVisible(false);

		registerTop.setVisible(false);

		startScreen.setBackground(Color.WHITE);
		startScreen.setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		add(startScreen);
		startScreen.setLayout(null);

		textField_PasswordLogin = new JPasswordField();
		textField_PasswordLogin.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_PasswordLogin.setBounds(327, 305, 396, 35);
		startScreen.add(textField_PasswordLogin);
		textField_PasswordLogin.setEchoChar('*');
		textField_PasswordLogin.setColumns(10);

		textField_UserID = new JTextField();
		textField_UserID.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_UserID.setBounds(327, 230, 396, 35);
		startScreen.add(textField_UserID);
		textField_UserID.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(102, 51, 0));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startScreen.setVisible(false);
				regForm.setVisible(true);
				registerTop.setVisible(true);
				textField_PasswordLogin.setText("");
				textField_UserID.setText("");
				loginError.setText("");
			}
		});

		loginError = new JLabel();
		loginError.setHorizontalAlignment(SwingConstants.CENTER);
		loginError.setForeground(new Color(204, 0, 0));
		loginError.setFont(new Font("Dubai", Font.PLAIN, 18));
		loginError.setBounds(356, 356, 349, 26);
		startScreen.add(loginError);
		btnRegister.setBounds(469, 468, 126, 35);
		startScreen.add(btnRegister);
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRegister.setOpaque(true);
		btnRegister.setBorderPainted(false);

		lblNewLabel = new JLabel("UserID:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel.setBounds(327, 213, 75, 14);
		startScreen.add(lblNewLabel);

		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPassword_1.setBounds(328, 287, 126, 14);
		startScreen.add(lblPassword_1);

		JButton btnLogin = new JButton("Log In");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setToolTipText("");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login login = new Login();

				try {
					int idLogin = Integer.parseInt(textField_UserID.getText());
					String passLogin = textField_PasswordLogin.getText();
					boolean loginSuccessful = login.checkAuthorization(idLogin, passLogin);
					System.out.println(loginSuccessful);

					if (loginSuccessful == false) {
						throw new NullPointerException();
					}

					else {
						if (login.getIsAdmin() == false) {
							studentManager.setViewedStudent(idLogin);
							MainPanelStudent pane = new MainPanelStudent(frame, scholarshipManager, udb,
									studentManager.getViewedStudent(), studentManager, nm);
							frame.setContentPane(pane);
							frame.revalidate();
						}

						else {
							MainPanelAdmin pane = new MainPanelAdmin(frame, scholarshipManager, udb,
									(Admin) udb.getLoggedInUser(idLogin), studentManager, nm);
							frame.setContentPane(pane);
							frame.revalidate();
						}

					}
					textField_PasswordLogin.setText("");
					textField_UserID.setText("");
					loginError.setText("");

				} catch (Exception loginException) {
					loginError.setForeground(new Color(204, 0, 0));
					loginError.setText("Incorrect UserID or password");
				}
			}
		});
		btnLogin.setBounds(469, 408, 126, 35);
		startScreen.add(btnLogin);
		btnLogin.setBackground(new Color(102, 204, 204));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);

		logoPicture = new JLabel("");
		logoPicture.setIcon(new ImageIcon(scaledred));
		logoPicture.setBounds(312, 110, 75, 65);
		frame.getContentPane().add(logoPicture);
		startScreen.add(logoPicture);

		loginTitle = new JLabel("SC SCHOLARSHIPS");
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setForeground(new Color(51, 102, 102));
		loginTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		loginTitle.setBounds(397, 110, 296, 35);
		startScreen.add(loginTitle);

		lblWelcome = new JLabel("Scholarships Made Easy");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setBounds(397, 156, 296, 26);
		startScreen.add(lblWelcome);

		bar = new JPanel();
		bar.setBackground(new Color(153, 204, 204));
		bar.setBounds(397, 152, 296, 3);
		startScreen.add(bar);
		registerTop.setBounds(0, 0, 1057, 57);
		add(registerTop);
		registerTop.setBackground(new Color(102, 204, 204));
		registerTop.setForeground(new Color(102, 204, 204));

		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setForeground(Color.WHITE);
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 22));
		GroupLayout gl_registerTop = new GroupLayout(registerTop);
		gl_registerTop.setHorizontalGroup(
				gl_registerTop.createParallelGroup(Alignment.LEADING).addGroup(gl_registerTop.createSequentialGroup()
						.addGap(22).addComponent(lblRegistration).addContainerGap(686, Short.MAX_VALUE)));
		gl_registerTop
				.setVerticalGroup(gl_registerTop.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_registerTop.createSequentialGroup().addContainerGap()
								.addComponent(lblRegistration, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addContainerGap()));
		registerTop.setLayout(gl_registerTop);
		add(regForm);
		regForm.setLayout(null);

		textField_firstName = new JTextField();
		textField_firstName.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_firstName.setColumns(10);
		textField_firstName.setBounds(134, 180, 262, 35);
		regForm.add(textField_firstName);

		textField_lastName = new JTextField();
		textField_lastName.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_lastName.setColumns(10);
		textField_lastName.setBounds(134, 270, 262, 35);
		regForm.add(textField_lastName);

		textField_year = new JTextField();
		textField_year.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_year.setColumns(10);
		textField_year.setBounds(598, 270, 262, 35);
		regForm.add(textField_year);

		textField_PasswordRegistration = new JPasswordField();
		textField_PasswordRegistration.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_PasswordRegistration.setColumns(10);
		textField_PasswordRegistration.setBounds(598, 359, 262, 35);
		textField_PasswordRegistration.setEchoChar('*');
		regForm.add(textField_PasswordRegistration);

		textField_gpa = new JTextField();
		textField_gpa.setFont(new Font("Dubai", Font.PLAIN, 18));
		textField_gpa.setColumns(10);
		textField_gpa.setBounds(134, 445, 262, 35);
		regForm.add(textField_gpa);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Dubai", Font.BOLD, 18));
		lblFirstName.setBounds(134, 153, 171, 16);
		regForm.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Dubai", Font.BOLD, 18));
		lblLastName.setBounds(134, 249, 171, 16);
		regForm.add(lblLastName);

		lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Dubai", Font.BOLD, 18));
		lblFaculty.setBounds(598, 147, 171, 28);
		regForm.add(lblFaculty);

		lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Dubai", Font.BOLD, 18));
		lblYear.setBounds(598, 249, 171, 16);
		regForm.add(lblYear);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Dubai", Font.BOLD, 18));
		lblPassword.setBounds(598, 339, 171, 16);
		regForm.add(lblPassword);
		JComboBox comboBox = new JComboBox(levelStrings);
		comboBox.setFont(new Font("Dubai", Font.PLAIN, 18));
		comboBox.setBounds(134, 359, 262, 35);
		regForm.add(comboBox);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Dubai", Font.BOLD, 18));
		lblLevel.setBounds(135, 339, 171, 16);
		regForm.add(lblLevel);

		JComboBox comboFaculty = new JComboBox(facultyStrings);
		comboFaculty.setFont(new Font("Dialog", Font.PLAIN, 18));
		comboFaculty.setBounds(598, 180, 262, 35);
		regForm.add(comboFaculty);

		JLabel regErrorMsg = new JLabel("");
		regErrorMsg.setForeground(new Color(255, 0, 51));
		regErrorMsg.setFont(new Font("Dialog", Font.PLAIN, 16));
		regErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		regErrorMsg.setBounds(587, 405, 322, 28);
		regForm.add(regErrorMsg);

		btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String firstName = textField_firstName.getText();
					String lastName = textField_lastName.getText();
					String level = (comboBox.getSelectedItem().toString()).toUpperCase();
					Double gpa = Double.parseDouble(textField_gpa.getText());
					String faculty = comboFaculty.getSelectedItem().toString(); // do check if faculty exists
					int year = Integer.parseInt(textField_year.getText());
					String password = textField_PasswordRegistration.getText();

					User one = new Student(firstName, lastName, password, Enumerations.level.valueOf(level),
							Enumerations.faculty.valueOf(faculty), year, gpa, udb);

					udb.addUser(one);
					studentManager.updateStudent(one);
					nm.addUserToMap(one.getUserID());

					textField_firstName.setText("");
					textField_lastName.setText("");
					textField_gpa.setText("");
					textField_year.setText("");
					textField_PasswordRegistration.setText("");

					regErrorMsg.setText("");
					regForm.setVisible(false);
					startScreen.setVisible(true);
					loginError.setForeground(new Color(0, 153, 0));
					loginError.setText("Account successfully created. Please login.");
					lblGenerateID.setText("UserID: " + one.getUserID());
					String userIdd = Integer.toString(one.getUserID());
					StringSelection stringSelection = new StringSelection(userIdd);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);

					regForm.setVisible(false);
					registerTop.setVisible(true);
					startScreen.setVisible(false);
					confirmationScreen.setVisible(true);

				} catch (Exception regException) {
					regErrorMsg.setText("*Incorrect format/one or more fields is empty");
				}

			}
		});

		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setFont(new Font("Dubai", Font.BOLD, 18));
		lblGpa.setBounds(134, 425, 171, 16);
		regForm.add(lblGpa);

		btnConfirm.setFont(new Font("Dubai", Font.BOLD, 18));
		btnConfirm.setBackground(new Color(102, 51, 0));
		btnConfirm.setBounds(783, 445, 126, 35);
		btnConfirm.setOpaque(true);
		btnConfirm.setBorderPainted(false);
		regForm.add(btnConfirm);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_firstName.setText("");
				textField_lastName.setText("");
				textField_gpa.setText("");
				textField_year.setText("");
				textField_PasswordRegistration.setText("");

				textField_PasswordLogin.setText("");
				regForm.setVisible(false);
				registerTop.setVisible(false);
				startScreen.setVisible(true);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dubai", Font.BOLD, 18));
		btnBack.setBackground(new Color(102, 51, 0));
		btnBack.setBounds(598, 445, 126, 35);
		btnBack.setOpaque(true);
		btnBack.setBorderPainted(false);
		regForm.add(btnBack);

		confirmationScreen.setVisible(false);
		confirmationScreen.setFocusable(false);
		confirmationScreen.setBackground(Color.WHITE);

		confirmationScreen.setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		add(confirmationScreen);

		lblRegistrationSuccessful = new JLabel("Registration successful. UserID has been copied to your clipboard");
		lblRegistrationSuccessful.setFont(new Font("Calibri", Font.PLAIN, 26));

		panel = new JPanel();
		panel.setBackground(Color.BLACK);

		JButton btnLoginScreen = new JButton("LOGIN SCREEN");
		btnLoginScreen.setFocusable(false);
		btnLoginScreen.setBorder(null);
		btnLoginScreen.setForeground(new Color(255, 255, 255));
		btnLoginScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_PasswordLogin.setText("");
				regForm.setVisible(false);
				registerTop.setVisible(false);
				confirmationScreen.setVisible(false);
				startScreen.setVisible(true);

			}
		});
		btnLoginScreen.setBackground(new Color(139, 69, 19));
		btnLoginScreen.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoginScreen.setOpaque(true);
		btnLoginScreen.setBorderPainted(false);

		GroupLayout gl_confirmationScreen = new GroupLayout(confirmationScreen);
		gl_confirmationScreen.setHorizontalGroup(gl_confirmationScreen.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_confirmationScreen.createSequentialGroup().addContainerGap(185, Short.MAX_VALUE)
						.addGroup(gl_confirmationScreen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_confirmationScreen.createSequentialGroup().addGap(259).addComponent(
										btnLoginScreen, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_confirmationScreen.createSequentialGroup().addGap(172).addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRegistrationSuccessful, GroupLayout.PREFERRED_SIZE, 719,
										GroupLayout.PREFERRED_SIZE))
						.addGap(153)));
		gl_confirmationScreen.setVerticalGroup(gl_confirmationScreen.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_confirmationScreen.createSequentialGroup().addGap(181)
						.addComponent(lblRegistrationSuccessful, GroupLayout.PREFERRED_SIZE, 44,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE).addGap(80)
						.addComponent(btnLoginScreen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(188, Short.MAX_VALUE)));

		lblGenerateID = new JLabel("UserID:");
		lblGenerateID.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblGenerateID.setForeground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(68).addComponent(lblGenerateID).addContainerGap(177, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(19).addComponent(lblGenerateID).addContainerGap(24, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		confirmationScreen.setLayout(gl_confirmationScreen);

	}
}
