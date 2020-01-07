package ScholarshipSystem;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.GroupLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListCellRenderer;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * This is the main graphical user interface (GUI) for the Student portal. This
 * encompasses everything form viewing, editing, accepting, declining
 * scholarships. Students are able to view listed scholarships and view/manage
 * their own applications
 *
 * 
 * Each sections are separated into 4 main panels, profile, scholarships,
 * applications, and notifications (which are contained in the Activitypanel)
 * each with their own sub-panels. See the components in Design in windows
 * builder **for changing views in the design page in windows builder see the
 * notes in this class' constructor
 * 
 * @author Jessie, Shaina
 * @version final (4/12/19)
 * 
 */

public class MainPanelStudent extends JPanel {

	/**
	 * Since the screens are separated into methods, some variables are needed to be
	 * declared outside the methods in order for other methods to access it
	 */
	public JFrame frame;
	public UserDatabase udb;
	public ScholarshipManager scholarshipManager;
	public JPanel sideBar = new JPanel();
	public JPanel stProfileScreen = new JPanel();
	public JPanel applicationScreen = new JPanel();
	public JPanel stScholarshipScreen = new JPanel();
	public JPanel notificationScreen = new JPanel();
	public JPanel Activitypanel = new JPanel();

	public int sideBarWidth = 260;
	public int activityScreenWidth = MainFrame.widthSize - sideBarWidth;
	private Student currentUser;
	private StudentData currentUserData;
	private StudentManager studentManager;
	private NotificationManager notificationManager;
	private String thisScholarship = "";
	private ArrayList<String> notifs = new ArrayList<>();

	private JPanel formPanel = new JPanel();
	private JPanel applicationsHome = new JPanel();
	private JList notificationsList;
	private JLabel formScholTitle = new JLabel("Scholarship title");

	/**
	 * 
	 * Called when logging in was successful. gets the currently logged in user
	 * Managers (scholarship and student) have to be consistent every time the
	 * system is executed so it is also fed into the constructor since it is created
	 * on the login screen
	 * 
	 * Initializes the screens needed for the student portal
	 * 
	 * @param frm
	 *            - current frame (window) used for the system
	 * @param scManager
	 *            - scholarship Manager
	 * @param udatabase
	 *            - user database
	 * @param user
	 *            - current logged in user. (Student in this case)
	 * @param studentManager
	 */

	public MainPanelStudent(JFrame frm, ScholarshipManager scManager, UserDatabase udatabase, Student user,
			StudentManager studentManager, NotificationManager notificationManager) {
		this.frame = frm;
		this.udb = udatabase;
		this.scholarshipManager = scManager;
		this.currentUser = user;
		this.currentUserData = currentUser.getStudentData();
		this.studentManager = studentManager;
		this.notificationManager = notificationManager;

		setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		setLayout(new BorderLayout(0, 0));

		Activitypanel.setBackground(Color.WHITE);
		add(Activitypanel, BorderLayout.CENTER);
		add(sideBar, BorderLayout.WEST);
		Activitypanel.setPreferredSize(new Dimension(activityScreenWidth, MainFrame.heightSize));

		createStProfileScreen();
		createSideBar();
		createstScholarshipScreen();
		createNotificationScreen();
		createApplicationScreen();
		Activitypanel.setLayout(new CardLayout(0, 0));

		// change the ordering of the panels below to see it in Design in windows
		// builder
		// First listed will be the top view of windows builder
		// changing sub-panels of the four main panels below is more complicated since
		// dragging it in components view in windows builder does work but it will move
		// the actual code
		// in the file which may break things
		Activitypanel.add(notificationScreen);
		Activitypanel.add(applicationScreen);
		Activitypanel.add(stProfileScreen);
		Activitypanel.add(stScholarshipScreen);

	}

	/**
	 * 
	 * for switching the contained panel in the activitypanel
	 * 
	 * @param pane
	 *            - panel to be switch to
	 */
	public void switchAP(JPanel pane) {
		Activitypanel.removeAll();
		Activitypanel.add(pane, "name_671170994005400");
		Activitypanel.repaint();
		Activitypanel.revalidate();

	}

	/**
	 * Creates the GUI sidebar components that can be clicked to switch screens
	 * 
	 */
	public void createSideBar() {

		double botHeight = MainFrame.heightSize * (1 / 3);

		sideBar.setVerifyInputWhenFocusTarget(false);
		sideBar.setBackground(new Color(102, 204, 204));
		sideBar.setPreferredSize(new Dimension(sideBarWidth, MainFrame.heightSize));
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

		JPanel profileSelection = new JPanel();
		profileSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchAP(stProfileScreen);
			}
		});
		profileSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(profileSelection);
		profileSelection.setLayout(new CardLayout(0, 0));

		JLabel lblUserProfile = new JLabel("User Profile");
		lblUserProfile.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));

		lblUserProfile.setFont(new Font("Dubai", Font.BOLD, 26));
		lblUserProfile.setForeground(Color.WHITE);
		lblUserProfile.setHorizontalAlignment(SwingConstants.CENTER);
		profileSelection.add(lblUserProfile, "name_1394865028120200");

		JPanel scholarshipSelection = new JPanel();
		scholarshipSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchAP(stScholarshipScreen);
			}
		});
		scholarshipSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(scholarshipSelection);
		scholarshipSelection.setLayout(new CardLayout(0, 0));

		JLabel lblAvailableScholarships = new JLabel("Scholarships");
		lblAvailableScholarships.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		lblAvailableScholarships.setForeground(Color.WHITE);
		lblAvailableScholarships.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAvailableScholarships.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableScholarships.setFont(new Font("Dubai", Font.BOLD, 26));
		scholarshipSelection.add(lblAvailableScholarships, "name_168470910017400");

		JPanel notificationSelection = new JPanel();
		notificationSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifs = notificationManager.getNotificationTitles(currentUser.getUserID());
				notificationsList.setListData(notifs.toArray());
				switchAP(notificationScreen);
			}
		});

		JPanel applicationsSelection = new JPanel();
		applicationsSelection.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		applicationsSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchAP(applicationScreen);
			}
		});
		applicationsSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(applicationsSelection);
		applicationsSelection.setLayout(new CardLayout(0, 0));

		JLabel lblApplications = new JLabel("My Applications");
		lblApplications.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplications.setForeground(Color.WHITE);
		lblApplications.setFont(new Font("Dubai", Font.BOLD, 26));
		applicationsSelection.add(lblApplications, "name_1394762229728200");
		notificationSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(notificationSelection);
		notificationSelection.setLayout(new CardLayout(0, 0));

		JLabel lblNotifications = new JLabel("Notifications");

		lblNotifications.setFont(new Font("Dubai", Font.BOLD, 26));
		lblNotifications.setForeground(Color.WHITE);
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		notificationSelection.add(lblNotifications, "name_1394916853619000");

		JPanel botSideBar = new JPanel();
		botSideBar.setBackground(new Color(51, 102, 153));
		sideBar.add(botSideBar);
		botSideBar.setPreferredSize(new Dimension(sideBarWidth, 180));
		botSideBar.setLayout(null);

		JButton button = new JButton("LOGOUT");
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				revalidate();
				repaint();
				LoginRegistrationPane loginReg = new LoginRegistrationPane(frame, udb, scholarshipManager,
						studentManager, notificationManager);
				frame.setContentPane(loginReg);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		button.setBackground(new Color(0, 102, 153));
		button.setBounds(67, 116, 117, 43);
		botSideBar.add(button);

	}

	public void createStProfileScreen() {
		stProfileScreen.setBackground(Color.WHITE);
		stProfileScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);

		Image prPic = new ImageIcon(this.getClass().getResource("/ScholarshipSystem/profilePic.png")).getImage();
		Image scaledprPic = prPic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		stProfileScreen.setLayout(null);

		EmptyBorder titleBorders = new EmptyBorder(0, 20, 0, 0);

		JPanel mainPane = new JPanel();
		mainPane.setBackground(new Color(255, 255, 255));
		mainPane.setBorder(null);
		mainPane.setBounds(0, 0, 782, 615);
		stProfileScreen.add(mainPane);
		mainPane.setLayout(null);

		JLabel profilePic = new JLabel("");
		profilePic.setBounds(166, 124, 157, 157);
		mainPane.add(profilePic);
		profilePic.setIcon(new ImageIcon(scaledprPic));

		JLabel userName = new JLabel(currentUser.getFirstName() + " " + currentUser.getLastName());
		userName.setBounds(348, 137, 365, 45);
		mainPane.add(userName);
		userName.setFont(new Font("Dubai", Font.BOLD, 30));

		JLabel userID = new JLabel("User ID: " + Integer.toString(currentUser.getUserID()));
		userID.setBounds(348, 190, 259, 32);
		mainPane.add(userID);
		userID.setFont(new Font("Dubai", Font.PLAIN, 22));

		JLabel userType = new JLabel("Type: Student");
		userType.setBounds(348, 226, 268, 32);
		mainPane.add(userType);
		userType.setFont(new Font("Dubai", Font.PLAIN, 22));

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(106, 90, 205)));
		infoPanel.setBounds(134, 310, 504, 225);
		mainPane.add(infoPanel);
		infoPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		infoPanel.setBackground(new Color(204, 204, 255));
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 125, 309, 0 };
		gbl_infoPanel.rowHeights = new int[] { 60, 58, 57 };
		gbl_infoPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		infoPanel.setLayout(gbl_infoPanel);

		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Dubai", Font.BOLD, 22));
		lblFaculty.setBorder(titleBorders);
		GridBagConstraints gbc_lblFaculty = new GridBagConstraints();
		gbc_lblFaculty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFaculty.insets = new Insets(0, 0, 5, 5);
		gbc_lblFaculty.gridx = 0;
		gbc_lblFaculty.gridy = 0;
		infoPanel.add(lblFaculty, gbc_lblFaculty);

		JLabel facultyEntry = new JLabel(currentUser.getStudentData().getFaculty().str);
		facultyEntry.setFont(new Font("Dubai", Font.PLAIN, 22));
		GridBagConstraints gbc_facultyEntry = new GridBagConstraints();
		gbc_facultyEntry.insets = new Insets(0, 0, 5, 0);
		gbc_facultyEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_facultyEntry.gridx = 1;
		gbc_facultyEntry.gridy = 0;
		infoPanel.add(facultyEntry, gbc_facultyEntry);

		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Dubai", Font.BOLD, 22));
		lblYear.setBorder(titleBorders);
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 1;
		infoPanel.add(lblYear, gbc_lblYear);

		JLabel yearEntry = new JLabel(Integer.toString(currentUser.getStudentData().getYear()));
		yearEntry.setFont(new Font("Dubai", Font.PLAIN, 22));
		GridBagConstraints gbc_yearEntry = new GridBagConstraints();
		gbc_yearEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearEntry.insets = new Insets(0, 0, 5, 0);
		gbc_yearEntry.gridx = 1;
		gbc_yearEntry.gridy = 1;
		infoPanel.add(yearEntry, gbc_yearEntry);

		JLabel lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Dubai", Font.BOLD, 22));
		lblLevel.setBorder(titleBorders);
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 2;
		infoPanel.add(lblLevel, gbc_lblLevel);

		JLabel levelEntry = new JLabel(currentUser.getStudentData().getLevel().str);
		levelEntry.setFont(new Font("Dubai", Font.PLAIN, 22));
		GridBagConstraints gbc_levelEntry = new GridBagConstraints();
		gbc_levelEntry.insets = new Insets(0, 0, 5, 0);
		gbc_levelEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_levelEntry.gridx = 1;
		gbc_levelEntry.gridy = 2;
		infoPanel.add(levelEntry, gbc_levelEntry);

	}

	/**
	 * 
	 * frame for viewing all scholarships available for the student Student can also
	 * view the info of the scholarship (which is contained in a sub-panel) and
	 * choose to apply there.
	 * 
	 */

	public void createstScholarshipScreen() {

		ArrayList<String> scholList = scholarshipManager.getTitles();
		stScholarshipScreen.setBackground(Color.WHITE);
		stScholarshipScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);
		stScholarshipScreen.setLayout(null);

		JPanel scholInfoPane = new JPanel();

		scholInfoPane.setBackground(Color.WHITE);
		scholInfoPane.setBounds(0, 0, activityScreenWidth, MainFrame.heightSize);
		scholInfoPane.setVisible(false);
		scholInfoPane.setLayout(null);

		stScholarshipScreen.add(scholInfoPane);

		JLabel lblAmount = new JLabel("Amount ($):");
		lblAmount.setFont(new Font("Dialog", Font.BOLD, 22));
		lblAmount.setBounds(27, 199, 124, 32);
		scholInfoPane.add(lblAmount);

		JLabel amountEntry = new JLabel("");
		amountEntry.setFont(new Font("Dialog", Font.PLAIN, 22));
		amountEntry.setBounds(161, 199, 229, 32);
		scholInfoPane.add(amountEntry);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 22));
		lblQuantity.setBounds(27, 288, 95, 48);
		scholInfoPane.add(lblQuantity);

		JLabel quantityEntry = new JLabel("");
		quantityEntry.setFont(new Font("Dialog", Font.PLAIN, 22));
		quantityEntry.setBounds(161, 288, 229, 48);
		scholInfoPane.add(quantityEntry);

		JLabel facultyEntry = new JLabel("");
		facultyEntry.setFont(new Font("Dialog", Font.PLAIN, 22));
		facultyEntry.setBounds(161, 391, 229, 32);
		scholInfoPane.add(facultyEntry);

		JLabel lblFaculty_1 = new JLabel("Faculty:");
		lblFaculty_1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblFaculty_1.setBounds(27, 391, 80, 32);
		scholInfoPane.add(lblFaculty_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 204));
		panel.setBounds(0, 505, 782, 19);
		scholInfoPane.add(panel);

		JLabel lblError = new JLabel("Max amount of accepted scholarship has been reached");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblError.setBounds(0, 554, 782, 32);
		scholInfoPane.add(lblError);

		JButton apply_bt = new JButton("Apply");
		apply_bt.setBorderPainted(false);
		apply_bt.setOpaque(true);
		apply_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean isApplicationOpen = scholarshipManager.getScholarship(thisScholarship).getApplicationOpen();

				if (isApplicationOpen) {
					if (currentUser.canApplyForScholarships()) {
						ScholarshipApplication thereIsExistingApplication = currentUser
								.getApplicationForStudent(scholarshipManager, thisScholarship);

						if (thereIsExistingApplication == null) {
							applicationsHome.setVisible(false);
							formPanel.setVisible(true);
							formScholTitle.setText(thisScholarship);
							switchAP(applicationScreen);
						}
					}
				}
			}
		});

		apply_bt.setForeground(Color.WHITE);
		apply_bt.setBackground(new Color(51, 204, 204));
		apply_bt.setFont(new Font("Dubai", Font.BOLD, 20));
		apply_bt.setBounds(305, 549, 158, 43);
		scholInfoPane.add(apply_bt);

		JPanel ScholInfoHeading = new JPanel();
		ScholInfoHeading.setBounds(0, 0, 782, 128);
		scholInfoPane.add(ScholInfoHeading);
		ScholInfoHeading.setLayout(null);

		JButton seeList_bt = new JButton("Back");
		seeList_bt.setOpaque(true);
		seeList_bt.setBorderPainted(false);
		seeList_bt.setForeground(Color.WHITE);
		seeList_bt.setBackground(new Color(102, 51, 0));
		seeList_bt.setBounds(611, 24, 141, 34);
		ScholInfoHeading.add(seeList_bt);
		seeList_bt.setFont(new Font("Dubai", Font.BOLD, 20));
		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setBounds(332, 85, 74, 32);
		ScholInfoHeading.add(lblDonor);

		lblDonor.setFont(new Font("Dubai", Font.BOLD, 22));

		JLabel donorEntry = new JLabel("");
		donorEntry.setFont(new Font("Dubai", Font.PLAIN, 22));
		donorEntry.setBounds(408, 85, 321, 32);
		ScholInfoHeading.add(donorEntry);
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(28, 85, 100, 32);
		ScholInfoHeading.add(lblDeadline);

		lblDeadline.setFont(new Font("Dubai", Font.BOLD, 22));

		JLabel deadlineEntry = new JLabel("");
		deadlineEntry.setBounds(132, 85, 190, 32);
		ScholInfoHeading.add(deadlineEntry);
		deadlineEntry.setFont(new Font("Dubai", Font.PLAIN, 22));

		JLabel scholTitle = new JLabel("Scholarship title");
		scholTitle.setBounds(23, 13, 565, 48);
		ScholInfoHeading.add(scholTitle);
		scholTitle.setFont(new Font("Dubai", Font.BOLD, 36));

		JLabel lblApplOpenClose = new JLabel("Applications open");
		lblApplOpenClose.setBounds(26, 55, 294, 32);
		ScholInfoHeading.add(lblApplOpenClose);
		lblApplOpenClose.setForeground(Color.RED);
		lblApplOpenClose.setFont(new Font("Dubai", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Dubai", Font.PLAIN, 20));
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(390, 169, 364, 283);
		scholInfoPane.add(scrollPane);

		JPanel scholListPane = new JPanel();

		JTable scholReqTable = new JTable();
		scholReqTable.setRowHeight(35);
		scholReqTable.setFont(new Font("Dubai", Font.PLAIN, 20));
		scholReqTable
				.setModel(
						new DefaultTableModel(
								new Object[][] { { "Age :", "" }, { "GPA :", "" }, { "Level :", null },
										{ "Year :", "" }, { "Major :", "" }, { "Gender :", "" }, { "Income ($) :", "" },
										{ "Citizen :", "" }, { "Extra Curriculars :", "" }, },
								new String[] { "REQUIREMENTS", "" }) {
							Class[] columnTypes = new Class[] { String.class, Object.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
		scholReqTable.getColumnModel().getColumn(0).setPreferredWidth(77);
		scholReqTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(scholReqTable);
		seeList_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scholInfoPane.setVisible(false);
				scholListPane.setVisible(true);
			}
		});

		scholListPane.setBackground(Color.WHITE);
		scholListPane.setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		stScholarshipScreen.add(scholListPane);
		scholListPane.setLayout(null);

		JPanel scholarshipBorder = new JPanel();
		scholarshipBorder.setBounds(12, 69, 762, 518);
		scholListPane.add(scholarshipBorder);
		scholarshipBorder.setBackground(new Color(240, 255, 240));
		scholarshipBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scholarshipBorder.setLayout(null);

		JButton detail_bt = new JButton("Details");
		detail_bt.setOpaque(true);
		detail_bt.setBorderPainted(false);
		detail_bt.setForeground(Color.WHITE);
		detail_bt.setBackground(new Color(51, 204, 204));
		detail_bt.setVisible(false);
		JList allScholarshipList = new JList();
		allScholarshipList.setBorder(new LineBorder(new Color(153, 204, 153)));
		allScholarshipList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (allScholarshipList.getSelectedIndex() < 0) {

					detail_bt.setVisible(false);

				} else {
					detail_bt.setVisible(true);
					thisScholarship = allScholarshipList.getSelectedValue().toString();

				}
			}
		});

		allScholarshipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allScholarshipList.setBounds(10, 11, 735, 497);
		allScholarshipList.setFont(new Font("Dubai", Font.PLAIN, 30));
		allScholarshipList.setBackground(new Color(204, 255, 204));
		allScholarshipList.setListData(scholList.toArray());
		scholListPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				allScholarshipList.clearSelection();
			}
		});

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) allScholarshipList.getCellRenderer();
		JScrollPane scScrollPane = new JScrollPane();
		scScrollPane.setBounds(10, 11, 742, 496);
		scScrollPane.setViewportView(allScholarshipList);
		scholarshipBorder.add(scScrollPane);

		detail_bt.setBounds(610, 24, 141, 34);
		scholListPane.add(detail_bt);
		detail_bt.setFont(new Font("Dubai", Font.BOLD, 20));

		JLabel lblScholarshipList = new JLabel("Scholarship List");
		lblScholarshipList.setFont(new Font("Dubai", Font.BOLD, 34));
		lblScholarshipList.setBounds(26, 20, 584, 34);
		scholListPane.add(lblScholarshipList);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		detail_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scholListPane.setVisible(false);
				scholInfoPane.setVisible(true);
				allScholarshipList.clearSelection();
				detail_bt.setVisible(false);

				boolean isApplicationOpen = scholarshipManager.getScholarship(thisScholarship).getApplicationOpen();

				if (isApplicationOpen) {

					if (currentUser.canApplyForScholarships()) {
						ScholarshipApplication thereIsExistingApplication = currentUser
								.getApplicationForStudent(scholarshipManager, thisScholarship);

						if (thereIsExistingApplication == null) {
							lblError.setVisible(false);
							apply_bt.setVisible(true);
						} else { // there is already an application
							lblError.setText(
									"You have an existing application for this scholarship. Go to your applications to view it");
							lblError.setVisible(true);
							apply_bt.setVisible(false);
						}
					} else {
						lblError.setText("Max amount of accepted scholarship has been reached");
						lblError.setVisible(true);
						apply_bt.setVisible(false);
					}

				} else {
					lblError.setText("Scholarship applications are closed");
					lblError.setVisible(true);
					apply_bt.setVisible(false);
				}

				Scholarship scholSelected = scholarshipManager.getScholarship(thisScholarship);

				amountEntry.setText(Double.toString(scholarshipManager.getScholarship(thisScholarship).getAmount()));
				quantityEntry.setText(Integer.toString(scholSelected.getAwardLimit()));
				deadlineEntry.setText(scholSelected.dateFormat());
				donorEntry.setText(scholSelected.getDonor());
				facultyEntry.setText(scholSelected.getFaculty().str);
				scholTitle.setText(thisScholarship);

				if (scholSelected.getApplicationOpen()) {
					lblApplOpenClose.setText("Applications open");
					lblApplOpenClose.setForeground(new Color(154, 205, 50));
				} else {
					lblApplOpenClose.setText("Applications closed");
					lblApplOpenClose.setForeground(new Color(255, 0, 0));
				}

				scholReqTable.setModel(new DefaultTableModel( // change table
						new Object[][] { { "Age :", scholSelected.getbaseline("Age") },
								{ "GPA :", scholSelected.getbaseline("GPA") },
								{ "Level :", scholSelected.getbaseline("Level") },
								{ "Year :", scholSelected.getbaseline("Year") },
								{ "Major :", scholSelected.getbaseline("Major") },
								{ "Gender :", scholSelected.getbaseline("Gender") },
								{ "Income ($) :", scholSelected.getbaseline("Income") },
								{ "Citizen :", scholSelected.getbaseline("Citizen") },
								{ "Extra Curriculars :", scholSelected.getbaseline("ExtraCuric") }, },
						new String[] { "REQUIREMENTS", "" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			}
		});

	}

	/**
	 * 
	 * for viewing/managing students' applications.
	 * 
	 * Functionalities:
	 * 
	 * view - standard home of this screen where you can view the list of
	 * applications.Able to select an application and view a summary of it at a
	 * glance. edit/submit/save - Can edit the scholarship in the form sub-panel
	 * where you can choose to save it (without submitting) and submit it. delete -
	 * deleting an applications. Can delete only applications with a status NOT
	 * SUBMITTED prioritize - Can also prioritize application which all it does is
	 * it moves it up to the top of the applications list in home
	 * 
	 */

	public void createApplicationScreen() {

		JTextField txtFage;
		JTextField txtFcitizenship;
		JTextField txtFGPA;
		JTextField txtFextracurric;
		JTextField txtFmajor;
		JTextField txtFcontact;
		JTextField txtFAnnualIncome;

		ArrayList<String> scholJList = currentUser.getScholarshipAppliedTitles();
		applicationScreen.setBackground(Color.WHITE);
		applicationScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);
		applicationScreen.setLayout(null);

		JPanel scholInfoPane = new JPanel();

		scholInfoPane.setBackground(Color.WHITE);
		scholInfoPane.setBounds(0, 0, activityScreenWidth, MainFrame.heightSize);
		scholInfoPane.setVisible(false);

		JLabel scholTitle = new JLabel("Scholarship title");
		scholTitle.setFont(new Font("Dubai", Font.BOLD, 36));
		scholTitle.setBounds(27, 24, 565, 48);
		scholInfoPane.add(scholTitle);

		JLabel lblAmount = new JLabel("Amount ($):");
		lblAmount.setFont(new Font("Dubai", Font.BOLD, 24));
		lblAmount.setBounds(27, 199, 124, 32);
		scholInfoPane.add(lblAmount);

		JLabel amountEntry = new JLabel("");
		amountEntry.setFont(new Font("Dubai", Font.PLAIN, 24));
		amountEntry.setBounds(161, 199, 229, 32);
		scholInfoPane.add(amountEntry);

		JLabel quantityEntry = new JLabel("");
		quantityEntry.setFont(new Font("Dubai", Font.PLAIN, 24));
		quantityEntry.setBounds(161, 288, 229, 32);
		scholInfoPane.add(quantityEntry);

		JLabel facultyEntry = new JLabel("");
		facultyEntry.setFont(new Font("Dubai", Font.PLAIN, 24));
		facultyEntry.setBounds(161, 391, 229, 32);
		scholInfoPane.add(facultyEntry);

		JLabel lblFaculty_1 = new JLabel("Faculty:");
		lblFaculty_1.setFont(new Font("Dubai", Font.BOLD, 24));
		lblFaculty_1.setBounds(27, 391, 80, 32);
		scholInfoPane.add(lblFaculty_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 204));
		panel.setBounds(0, 505, 782, 19);
		scholInfoPane.add(panel);

		JPanel ScholInfoHeading = new JPanel();
		ScholInfoHeading.setBounds(0, 0, 782, 128);
		scholInfoPane.add(ScholInfoHeading);
		ScholInfoHeading.setLayout(null);

		JButton seeList_bt = new JButton("Back");
		seeList_bt.setOpaque(true);
		seeList_bt.setBorderPainted(false);
		seeList_bt.setForeground(Color.WHITE);
		seeList_bt.setBackground(new Color(102, 51, 0));
		seeList_bt.setBounds(611, 24, 141, 34);
		ScholInfoHeading.add(seeList_bt);
		seeList_bt.setFont(new Font("Dubai", Font.BOLD, 20));
		seeList_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formPanel.setVisible(true);
				scholInfoPane.setVisible(false);
			}
		});

		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setBounds(332, 85, 74, 32);
		ScholInfoHeading.add(lblDonor);

		lblDonor.setFont(new Font("Dubai", Font.BOLD, 22));

		JLabel donorEntry = new JLabel("");
		donorEntry.setFont(new Font("Dubai", Font.PLAIN, 22));
		donorEntry.setBounds(408, 85, 321, 32);
		ScholInfoHeading.add(donorEntry);
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(28, 85, 100, 32);
		ScholInfoHeading.add(lblDeadline);

		lblDeadline.setFont(new Font("Dubai", Font.BOLD, 22));

		JLabel deadlineEntry = new JLabel("");
		deadlineEntry.setBounds(132, 85, 190, 32);
		ScholInfoHeading.add(deadlineEntry);
		deadlineEntry.setFont(new Font("Dubai", Font.PLAIN, 22));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Dubai", Font.PLAIN, 20));
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(390, 169, 364, 283);
		scholInfoPane.add(scrollPane);

		JTable scholReqTable = new JTable();
		scholReqTable.setRowHeight(35);
		scholReqTable.setFont(new Font("Dubai", Font.PLAIN, 20));
		scholReqTable
				.setModel(
						new DefaultTableModel(
								new Object[][] { { "Age :", "" }, { "GPA :", "" }, { "Level :", null },
										{ "Year :", "" }, { "Major :", "" }, { "Gender :", "" }, { "Income ($) :", "" },
										{ "Citizen :", "" }, { "Extra Curriculars :", "" }, },
								new String[] { "REQUIREMENTS", "" }) {
							Class[] columnTypes = new Class[] { String.class, Object.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
		scholReqTable.getColumnModel().getColumn(0).setPreferredWidth(77);
		scholReqTable.getColumnModel().getColumn(1).setPreferredWidth(100);

		formPanel.setBackground(Color.WHITE);
		formPanel.setBounds(0, 0, activityScreenWidth, MainFrame.heightSize);
		formPanel.setVisible(false);
		txtFcitizenship = new JTextField();
		txtFcitizenship.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFcitizenship.setColumns(10);
		txtFcitizenship.setBounds(60, 270, 266, 31);
		formPanel.add(txtFcitizenship);

		txtFGPA = new JTextField();
		txtFGPA.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFGPA.setColumns(10);
		txtFGPA.setBounds(60, 372, 266, 31);
		formPanel.add(txtFGPA);

		JLabel lblExtracurricularExperience = new JLabel("Extracurricular experience:");
		lblExtracurricularExperience.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblExtracurricularExperience.setBounds(60, 450, 342, 23);
		formPanel.add(lblExtracurricularExperience);

		txtFextracurric = new JTextField();
		txtFextracurric.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFextracurric.setColumns(10);
		txtFextracurric.setBounds(60, 473, 266, 31);
		formPanel.add(txtFextracurric);

		JLabel lblCurrentMajor = new JLabel("Current Major:");
		lblCurrentMajor.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblCurrentMajor.setBounds(440, 246, 342, 23);
		formPanel.add(lblCurrentMajor);

		JLabel lblContact = new JLabel("Contact:");
		lblContact.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblContact.setBounds(440, 346, 342, 23);
		formPanel.add(lblContact);

		JLabel lblAnnualIncome = new JLabel("Annual income:");
		lblAnnualIncome.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblAnnualIncome.setBounds(440, 450, 342, 23);
		formPanel.add(lblAnnualIncome);

		txtFmajor = new JTextField();
		txtFmajor.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFmajor.setColumns(10);
		txtFmajor.setBounds(440, 270, 266, 31);
		formPanel.add(txtFmajor);

		txtFcontact = new JTextField();
		txtFcontact.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFcontact.setColumns(10);
		txtFcontact.setBounds(440, 372, 266, 31);
		formPanel.add(txtFcontact);

		txtFAnnualIncome = new JTextField();
		txtFAnnualIncome.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFAnnualIncome.setColumns(10);
		txtFAnnualIncome.setBounds(440, 473, 266, 31);
		formPanel.add(txtFAnnualIncome);

		txtFage = new JTextField();
		txtFage.setFont(new Font("Dubai", Font.PLAIN, 20));
		txtFage.setBounds(60, 177, 266, 31);
		formPanel.add(txtFage);
		txtFage.setColumns(10);

		JLabel lblErrorLabels = new JLabel("");
		lblErrorLabels.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorLabels.setForeground(new Color(255, 0, 0));
		lblErrorLabels.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblErrorLabels.setBounds(12, 522, 758, 31);
		formPanel.add(lblErrorLabels);
		applicationScreen.add(formPanel);
		formPanel.setLayout(null);

		applicationScreen.add(formPanel);
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblAge.setBounds(60, 151, 342, 23);
		formPanel.add(lblAge);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblGender.setBounds(440, 153, 342, 23);
		formPanel.add(lblGender);

		JComboBox comboBoxGender = new JComboBox();
		comboBoxGender.setFont(new Font("Dubai", Font.PLAIN, 20));
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] { "MALE", "FEMALE" }));
		comboBoxGender.setBounds(440, 177, 270, 31);
		formPanel.add(comboBoxGender);

		JLabel lblCitizenship = new JLabel("Citizenship:");
		lblCitizenship.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblCitizenship.setBounds(60, 246, 342, 23);
		formPanel.add(lblCitizenship);

		JLabel lblCurrentGpa = new JLabel("Current GPA:");
		lblCurrentGpa.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblCurrentGpa.setBounds(60, 346, 342, 23);
		formPanel.add(lblCurrentGpa);

		JButton btSave = new JButton("Save");
		JButton btnSubmit = new JButton("Submit");

		btnSubmit.setOpaque(true);
		btnSubmit.setBorderPainted(false);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(51, 204, 204));
		btnSubmit.setFont(new Font("Dubai", Font.BOLD, 20));
		btnSubmit.setBounds(411, 554, 131, 36);

		JList appliedScholJList = new JList();

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (btnSubmit.getText().equals("Confirm")) {
						lblErrorLabels.setText("");
						btnSubmit.setText("Submit");

						ScholarshipApplication scholAppl = currentUser.getApplicationForStudent(scholarshipManager,
								thisScholarship);
						if (scholAppl == null) { // there is no application for this scholarship yet
							// create new scholarship that is incomplete

							int testAge = Integer.parseInt(txtFage.getText());
							double testGPA = Double.parseDouble(txtFGPA.getText());

							boolean success = currentUser.createAndSubmitApplication(scholarshipManager, studentManager,
									thisScholarship, txtFage.getText(), comboBoxGender.getSelectedItem().toString(),
									txtFcitizenship.getText(), txtFmajor.getText(), txtFGPA.getText(),
									txtFcontact.getText(), txtFextracurric.getText(), txtFAnnualIncome.getText());

							if (success) {
								studentManager.updateStudentInfo(currentUser);
								scholarshipManager.serialize();

								formPanel.setVisible(false);
								applicationsHome.setVisible(true);

								txtFage.setText("");
								// gender
								txtFcitizenship.setText("");
								txtFmajor.setText("");
								txtFGPA.setText("");
								txtFcontact.setText("");
								txtFextracurric.setText("");
								txtFAnnualIncome.setText("");
								lblErrorLabels.setText("");
								btnSubmit.setText("Submit");

							} else {
								lblErrorLabels.setText("Some fields are empty");
							}

						} else { // there exists an application for this scholarship

							int testAge = Integer.parseInt(txtFage.getText());
							double testGPA = Double.parseDouble(txtFGPA.getText());

							boolean succes = currentUser.updateScholarshipEdits(scholarshipManager, thisScholarship,
									txtFage.getText(), comboBoxGender.getSelectedItem().toString(),
									txtFcitizenship.getText(), txtFmajor.getText(), txtFGPA.getText(),
									txtFcontact.getText(), txtFextracurric.getText(), txtFAnnualIncome.getText());
							if (succes) {
								scholAppl.updateStatus("SUBMITTED");

								studentManager.updateStudentInfo(currentUser);
								scholarshipManager.serialize();
								txtFage.setText("");
								// gender
								txtFcitizenship.setText("");
								txtFmajor.setText("");
								txtFGPA.setText("");
								txtFcontact.setText("");
								txtFextracurric.setText("");
								txtFAnnualIncome.setText("");
								lblErrorLabels.setText("");
								btnSubmit.setText("Submit");

								formPanel.setVisible(false);
								applicationsHome.setVisible(true);

							} else {
								lblErrorLabels.setText("Some fields are empty");
							}

						}

						appliedScholJList.setListData(scholJList.toArray());
						;

					} else {

						lblErrorLabels.setText("Confirm your submission");
						btnSubmit.setText("Confirm");

					}

				} catch (Exception numExc) {
					lblErrorLabels.setText("Incorrect format/one or more fields is empty");
				}
			}
		});

		formPanel.add(btnSubmit);

		btSave.setOpaque(true);
		btSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScholarshipApplication foundAppl = currentUser.getApplicationForStudent(scholarshipManager,
						thisScholarship);
				if (foundAppl == null) { // there is no application for this scholarship yet
					// create new scholarship that is incomplete

					currentUser.createApplication(scholarshipManager, studentManager, thisScholarship,
							txtFage.getText(), comboBoxGender.getSelectedItem().toString(), txtFcitizenship.getText(),
							txtFmajor.getText(), txtFGPA.getText(), txtFcontact.getText(), txtFextracurric.getText(),
							txtFAnnualIncome.getText());
					studentManager.updateStudentInfo(currentUser);
					scholarshipManager.serialize();
					lblErrorLabels.setText("Saved!");
					System.out.println("\n\n\n\nmade new application");
				} else { // there exists an application for this scholarship

					boolean succes = currentUser.updateScholarshipEdits(scholarshipManager, thisScholarship,
							txtFage.getText(), comboBoxGender.getSelectedItem().toString(), txtFcitizenship.getText(),
							txtFmajor.getText(), txtFGPA.getText(), txtFcontact.getText(), txtFextracurric.getText(),
							txtFAnnualIncome.getText());
					studentManager.updateStudentInfo(currentUser);
					scholarshipManager.serialize();
					lblErrorLabels.setText("Saved!");

				}

				appliedScholJList.setListData(scholJList.toArray());
				;

			}
		});
		btSave.setForeground(Color.WHITE);
		btSave.setFont(new Font("Dubai", Font.BOLD, 20));
		btSave.setBorderPainted(false);
		btSave.setBackground(new Color(51, 204, 204));
		btSave.setBounds(229, 554, 131, 36);
		formPanel.add(btSave);

		JPanel FormHeadingPanel = new JPanel();
		FormHeadingPanel.setBounds(0, 0, 782, 128);
		formPanel.add(FormHeadingPanel);
		FormHeadingPanel.setLayout(null);

		JLabel lblApplicationForm = new JLabel("Application Form");
		lblApplicationForm.setBounds(27, 86, 315, 31);
		FormHeadingPanel.add(lblApplicationForm);
		lblApplicationForm.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 24));

		formScholTitle.setBounds(27, 24, 471, 48);
		FormHeadingPanel.add(formScholTitle);
		formScholTitle.setFont(new Font("Dubai", Font.BOLD, 36));

		JButton btnGoToHome = new JButton("Applications Home");
		btnGoToHome.setOpaque(true);
		btnGoToHome.setBorderPainted(false);
		btnGoToHome.setForeground(Color.WHITE);
		btnGoToHome.setBackground(new Color(102, 0, 0));
		btnGoToHome.setBounds(554, 24, 204, 34);
		btnGoToHome.setFont(new Font("Dubai", Font.BOLD, 17));
		btnGoToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formPanel.setVisible(false);
				applicationsHome.setVisible(true);

				txtFage.setText("");
				// gender
				txtFcitizenship.setText("");
				txtFmajor.setText("");
				txtFGPA.setText("");
				txtFcontact.setText("");
				txtFextracurric.setText("");
				txtFAnnualIncome.setText("");
				lblErrorLabels.setText("");
				btSave.setVisible(true);
				btnSubmit.setVisible(true);
				btnSubmit.setText("Submit");

				txtFcitizenship.setEditable(true);
				txtFGPA.setEditable(true);
				txtFextracurric.setEditable(true);
				txtFmajor.setEditable(true);
				txtFcontact.setEditable(true);
				txtFAnnualIncome.setEditable(true);
				txtFage.setEditable(true);
				comboBoxGender.setEnabled(true);

			}
		});
		FormHeadingPanel.add(btnGoToHome);

		JButton scholInfoBt = new JButton("Scholarship info");
		scholInfoBt.setOpaque(true);
		scholInfoBt.setForeground(Color.WHITE);
		scholInfoBt.setFont(new Font("Dubai", Font.BOLD, 17));
		scholInfoBt.setBorderPainted(false);
		scholInfoBt.setBackground(new Color(102, 0, 0));
		scholInfoBt.setBounds(554, 71, 204, 34);
		FormHeadingPanel.add(scholInfoBt);

		scholInfoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnSubmit.setText("Submit");
				formPanel.setVisible(false);
				scholInfoPane.setVisible(true); //

				amountEntry.setText(Double.toString(scholarshipManager.getScholarship(thisScholarship).getAmount()));
				quantityEntry
						.setText(Integer.toString(scholarshipManager.getScholarship(thisScholarship).getAwardLimit()));
				deadlineEntry.setText(scholarshipManager.getScholarship(thisScholarship).dateFormat());
				donorEntry.setText(scholarshipManager.getScholarship(thisScholarship).getDonor());
				facultyEntry.setText(scholarshipManager.getScholarship(thisScholarship).getFaculty().str);
				scholTitle.setText(thisScholarship);

				scholReqTable.setModel(new DefaultTableModel(new Object[][] {
						{ "Age :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Age") },
						{ "GPA :", scholarshipManager.getScholarship(thisScholarship).getbaseline("GPA") },
						{ "Level :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Level") },
						{ "Year :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Year") },
						{ "Major :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Major") },
						{ "Gender :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Gender") },
						{ "Income ($) :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Income") },
						{ "Citizen :", scholarshipManager.getScholarship(thisScholarship).getbaseline("Citizen") },
						{ "Extra Curriculars :",
								scholarshipManager.getScholarship(thisScholarship).getbaseline("ExtraCuric") }, },
						new String[] { "REQUIREMENTS", "" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			}
		});

		JLabel lblScholarshipTitle = new JLabel("Select a scholarship");
		JLabel lblStatus = new JLabel("Status: ");
		JLabel lblPriority = new JLabel("Priority: ");

		applicationsHome.setBackground(Color.WHITE);
		applicationsHome.setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		applicationScreen.add(applicationsHome);
		applicationsHome.setLayout(null);

		JLabel lblCurrentApplications = new JLabel("Current Applications");
		lblCurrentApplications.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentApplications.setFont(new Font("Dubai", Font.BOLD, 30));
		lblCurrentApplications.setBounds(220, 11, 334, 54);
		applicationsHome.add(lblCurrentApplications);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setOpaque(true);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.TRAILING);
		lblError.setBounds(54, 278, 527, 29);
		applicationsHome.add(lblError);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Dubai", Font.PLAIN, 20));

		JPanel scholSummary = new JPanel();
		scholSummary.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scholSummary.setBackground(Color.WHITE);
		scholSummary.setBounds(54, 348, 674, 231);
		applicationsHome.add(scholSummary);
		scholSummary.setLayout(null);

		JButton btnPrioritize = new JButton("Prioritize");
		btnPrioritize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (lblScholarshipTitle.getText() != "Select a scholarship") {
					currentUser.getStudentData().fixPriority(lblScholarshipTitle.getText());

					appliedScholJList.setListData(scholJList.toArray());
					appliedScholJList.setSelectedIndex(0);
					studentManager.updateStudentInfo(currentUser);

				}

			}
		});
		btnPrioritize.setForeground(Color.WHITE);
		btnPrioritize.setFont(new Font("Dubai", Font.PLAIN, 22));
		btnPrioritize.setBorderPainted(false);
		btnPrioritize.setBackground(new Color(102, 204, 204));
		btnPrioritize.setBounds(364, 163, 120, 37);
		scholSummary.add(btnPrioritize);

		lblScholarshipTitle.setFont(new Font("Dubai", Font.BOLD, 32));
		lblScholarshipTitle.setBounds(44, 11, 596, 46);
		scholSummary.add(lblScholarshipTitle);

		lblStatus.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblStatus.setBounds(44, 113, 76, 29);
		scholSummary.add(lblStatus);

		lblPriority.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblPriority.setBounds(44, 163, 76, 29);
		scholSummary.add(lblPriority);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Dubai", Font.PLAIN, 22));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(102, 204, 204));
		btnDelete.setBounds(501, 163, 120, 36);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (lblScholarshipTitle.getText() != "Select a scholarship") {

					ScholarshipApplication selectedSchol = currentUser.getApplicationForStudent(scholarshipManager,
							lblScholarshipTitle.getText());

					if ("Submitted".equals(selectedSchol.getStatus().str)) {
						lblError.setText("Cannot delete a submitted application");
					} else if (btnDelete.getText() == "Delete") {
						lblError.setText("Confirm the deletion of this application");
						btnDelete.setText("Confirm");
					} else {
						boolean deletionComplete = scholarshipManager.removeApplication(lblScholarshipTitle.getText(),
								currentUser.getUserID());
						if (deletionComplete) {
							studentManager.removeApplication(currentUser, lblScholarshipTitle.getText());
							btnDelete.setText("Delete");
							lblError.setText("");
							appliedScholJList.setListData(scholJList.toArray());
						} else {
							lblError.setText("Something went wrong");
						}

					}
				}

			}
		});

		scholSummary.add(btnDelete);

		JLabel lblApplOpenClose = new JLabel("Applications open");
		lblApplOpenClose.setForeground(new Color(255, 0, 0));
		lblApplOpenClose.setFont(new Font("Dubai", Font.PLAIN, 22));
		lblApplOpenClose.setBounds(44, 58, 208, 29);
		lblApplOpenClose.setVisible(false);
		scholSummary.add(lblApplOpenClose);

		JLabel lblDeadline_home = new JLabel("Deadline:");
		lblDeadline_home.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblDeadline_home.setBounds(376, 113, 86, 29);
		scholSummary.add(lblDeadline_home);

		JLabel lblStatusEntry = new JLabel("");
		lblStatusEntry.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblStatusEntry.setBounds(121, 113, 243, 29);
		scholSummary.add(lblStatusEntry);

		JLabel lblDeadlineEntry_home = new JLabel("");
		lblDeadlineEntry_home.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblDeadlineEntry_home.setBounds(467, 113, 197, 29);
		scholSummary.add(lblDeadlineEntry_home);

		JLabel lblPriorityEntry = new JLabel("");
		lblPriorityEntry.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblPriorityEntry.setBounds(121, 163, 217, 29);
		scholSummary.add(lblPriorityEntry);

		JLabel lblApproved1 = new JLabel("This scholarship has been approved!");
		lblApproved1.setForeground(new Color(255, 0, 0));
		lblApproved1.setVisible(false);
		lblApproved1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblApproved1.setBounds(335, 49, 329, 47);
		scholSummary.add(lblApproved1);

		JLabel lblApproved2 = new JLabel("Accept or Reject this scholarship.");
		lblApproved2.setForeground(Color.RED);
		lblApproved2.setVisible(false);
		lblApproved2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblApproved2.setBounds(335, 81, 329, 47);
		scholSummary.add(lblApproved2);

		JButton btnAccept = new JButton("Accept");
		JButton btnDecline = new JButton("Decline");
		btnDecline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				studentManager.rejectScholarship(currentUser, scholarshipManager, lblScholarshipTitle.getText(), 123,
						notificationManager);
				lblApproved1.setText("You have declined this scholarship!");
				lblApproved2.setVisible(false);
				btnAccept.setVisible(false);
				btnDecline.setVisible(false);
				lblStatusEntry.setText(currentUser
						.getApplicationForStudent(scholarshipManager, lblScholarshipTitle.getText()).getStatus().str);
			}
		});

		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				studentManager.acceptScholarship(currentUser, scholarshipManager, lblScholarshipTitle.getText(), 123,
						notificationManager);
				lblApproved1.setText("You have accepted this scholarship!");
				lblApproved2.setVisible(false);
				btnAccept.setVisible(false);
				btnDecline.setVisible(false);
				lblStatusEntry.setText(currentUser
						.getApplicationForStudent(scholarshipManager, lblScholarshipTitle.getText()).getStatus().str);

			}
		});
		btnAccept.setVisible(false);
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setFont(new Font("Dubai", Font.PLAIN, 22));
		btnAccept.setBorderPainted(false);
		btnAccept.setBackground(new Color(60, 179, 113));
		btnAccept.setBounds(350, 141, 120, 37);
		scholSummary.add(btnAccept);

		btnDecline.setVisible(false);
		btnDecline.setForeground(Color.WHITE);
		btnDecline.setFont(new Font("Dubai", Font.PLAIN, 22));
		btnDecline.setBorderPainted(false);
		btnDecline.setBackground(new Color(255, 99, 71));
		btnDecline.setBounds(501, 141, 120, 37);
		scholSummary.add(btnDecline);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Dubai", Font.BOLD, 24));
		lblQuantity.setBounds(27, 288, 124, 32);
		scholInfoPane.add(lblQuantity);

		btnEdit.setBounds(591, 274, 120, 36);
		btnEdit.setVisible(false);

		JScrollPane ScholList = new JScrollPane();
		ScholList.setBounds(54, 92, 674, 162);
		applicationsHome.add(ScholList);

		appliedScholJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (appliedScholJList.getSelectedIndex() < 0) { // default text when nothing is selected
					lblScholarshipTitle.setText("Select a scholarship");
					lblStatusEntry.setText("");
					lblError.setText("");
					lblPriorityEntry.setText("");
					btnDelete.setText("Delete");
					lblApplOpenClose.setVisible(false);
					lblStatusEntry.setText("");
					lblPriorityEntry.setText("");
					lblDeadlineEntry_home.setText("");
					btnEdit.setVisible(false);

					btnDelete.setVisible(true);
					lblDeadline_home.setVisible(true);
					lblDeadlineEntry_home.setVisible(true);
					btnPrioritize.setVisible(true);
					lblPriority.setVisible(true);
					lblPriorityEntry.setVisible(true);
					btnAccept.setVisible(false);
					btnDecline.setVisible(false);

					lblApproved1.setVisible(false);
					lblApproved2.setVisible(false);

				} else {

					String scholSelectedStr = appliedScholJList.getSelectedValue().toString();
					Scholarship scholSelected = scholarshipManager.getScholarship(scholSelectedStr);
					ScholarshipApplication application = currentUser.getApplicationForStudent(scholarshipManager,
							scholSelectedStr);

					lblScholarshipTitle.setText(scholSelectedStr);
					btnEdit.setVisible(true);

					lblPriorityEntry
							.setText(Integer.toString(currentUser.getStudentData().getPriority(scholSelectedStr)));

					lblStatusEntry.setText(application.getStatus().str);

					if (scholSelected.getApplicationOpen()) {
						lblApplOpenClose.setText("Applications open");
						lblApplOpenClose.setForeground(new Color(154, 205, 50));
					} else {
						lblApplOpenClose.setText("Applications closed");
						lblApplOpenClose.setForeground(new Color(255, 0, 0));
					}
					lblDeadlineEntry_home.setText(scholSelected.dateFormat());
					lblError.setText("");

					if (!"Not submitted".equals(application.getStatus().str)
							&& !"Submitted".equals(application.getStatus().str)) {
						btnDelete.setVisible(false);
						lblDeadline_home.setVisible(false);
						lblDeadlineEntry_home.setVisible(false);
						btnPrioritize.setVisible(false);
						lblPriority.setVisible(false);
						lblPriorityEntry.setVisible(false);
						lblApplOpenClose.setVisible(false);

						if ("Approved".equals(application.getStatus().str)) {

							if (currentUser.canApplyForScholarships()) {
								btnAccept.setVisible(true);
								btnDecline.setVisible(true);
								lblApproved1.setVisible(true);
								lblApproved2.setVisible(true);
							} else {
								lblApproved1.setVisible(false);
								lblApproved2.setVisible(false);
								btnAccept.setVisible(false);
								btnDecline.setVisible(false);
								lblError.setText("Max amount of accepted scholarship has been reached");
								return;
							}

						} else {
							lblApproved1.setVisible(true);
							lblApproved2.setVisible(false);
							btnAccept.setVisible(false);
							btnDecline.setVisible(false);
						}

						lblApproved1.setText(
								"This scholarship has been " + application.getStatus().str.toLowerCase() + "!");

					} else { // standard view for submitted and not submitted
						btnDelete.setVisible(true);
						lblDeadline_home.setVisible(true);
						lblDeadlineEntry_home.setVisible(true);
						btnPrioritize.setVisible(true);
						lblPriority.setVisible(true);
						lblPriorityEntry.setVisible(true);

						btnAccept.setVisible(false);
						btnDecline.setVisible(false);
						lblApproved1.setVisible(false);
						lblApproved2.setVisible(false);
						lblApplOpenClose.setVisible(true);
					}

				}

			}
		});

		appliedScholJList.setFont(new Font("Dubai", Font.PLAIN, 22));
		appliedScholJList.setBorder(new EmptyBorder(0, 10, 0, 10));
		ScholList.setViewportView(appliedScholJList);
		appliedScholJList.setListData(scholJList.toArray());

		appliedScholJList.setBackground(new Color(224, 255, 255));
		applicationsHome.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lblScholarshipTitle.getText() != "Select a scholarship") {
					thisScholarship = appliedScholJList.getSelectedValue().toString();

					boolean isApplicationOpen = scholarshipManager.getScholarship(thisScholarship).getApplicationOpen();
					if (isApplicationOpen) {

						formScholTitle.setText(thisScholarship);
						formPanel.setVisible(true);
						applicationsHome.setVisible(false);
						ScholarshipApplication thisApplication = currentUser
								.getApplicationForStudent(scholarshipManager, lblScholarshipTitle.getText());
						System.out.println("Status: " + thisApplication.getStatus().str);
						String applStatus = thisApplication.getStatus().str;

						if (!"Not submitted".equals(applStatus) || !currentUser.canApplyForScholarships()) {

							if ("Submitted".equals(applStatus)) {
								lblErrorLabels.setText("This application has been submitted");
							} else if ("Approved".equals(applStatus)) {
								lblErrorLabels.setText("This application has been approved by the admin");
							} else if ("Rejected".equals(applStatus)) {
								lblErrorLabels.setText("This application has been rejected by the admin");
							} else if ("Accepted".equals(applStatus)) {
								lblErrorLabels.setText("This application has been accepted by the student");
							} else if ("Declined".equals(applStatus)) {
								lblErrorLabels.setText("This application has been declined by the student");
							} else if (!currentUser.canApplyForScholarships()) {
								lblErrorLabels.setText("Max amount of accepted scholarship has been reached");
							}

							btSave.setVisible(false);
							btnSubmit.setVisible(false);

							txtFcitizenship.setEditable(false);
							txtFGPA.setEditable(false);
							txtFextracurric.setEditable(false);
							txtFmajor.setEditable(false);
							txtFcontact.setEditable(false);
							txtFAnnualIncome.setEditable(false);
							txtFage.setEditable(false);
							comboBoxGender.setEnabled(false);

						}

						ScholarshipApplication scholApplOrEdit = currentUser
								.getApplicationForStudent(scholarshipManager, thisScholarship);
						if (scholApplOrEdit != null) {
							txtFage.setText(scholApplOrEdit.getField("Age"));
							// gender
							txtFcitizenship.setText(scholApplOrEdit.getField("Citizen"));
							txtFmajor.setText(scholApplOrEdit.getField("Major"));
							txtFGPA.setText(scholApplOrEdit.getField("GPA"));
							txtFcontact.setText(scholApplOrEdit.getField("Contact"));
							txtFextracurric.setText(scholApplOrEdit.getField("ExtraCuric"));
							txtFAnnualIncome.setText(scholApplOrEdit.getField("Income"));
						}
						lblError.setText("");
						btnDelete.setText("Delete");

					} else { // application is not open
						lblError.setText("Scholarship applications are closed");
					}
				}

			}
		});

		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(102, 204, 204));
		btnEdit.setFont(new Font("Dubai", Font.PLAIN, 22));

		applicationsHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				appliedScholJList.clearSelection();
			}
		});
		scrollPane.setViewportView(scholReqTable);

		scholInfoPane.setLayout(null);

		applicationScreen.add(scholInfoPane);

		JButton backToHome = new JButton("Back");

		backToHome.setBorderPainted(false);
		backToHome.setForeground(Color.WHITE);
		backToHome.setBackground(new Color(102, 51, 0));
		backToHome.setBounds(611, 24, 141, 34);

		backToHome.setFont(new Font("Dubai", Font.BOLD, 20));
		backToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applicationsHome.setVisible(true);
			}
		});

	}

	/**
	 * 
	 * Create the notification screen for viewing.
	 * 
	 */

	public void createNotificationScreen() {

		notificationScreen.setBackground(Color.WHITE);
		notificationScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);

		notificationScreen.setLayout(null);
		JPanel notificationsBorder = new JPanel();

		notificationsBorder.setBounds(0, 0, 782, 615);
		notificationsBorder.setBackground(new Color(255, 255, 255));
		notificationsBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		notificationsBorder.setLayout(null);
		notificationScreen.add(notificationsBorder);

		JLabel lblNotificationScreen = new JLabel("Notifications");
		lblNotificationScreen.setBounds(302, 25, 153, 53);
		notificationsBorder.add(lblNotificationScreen);
		lblNotificationScreen.setFont(new Font("Calibri", Font.BOLD, 26));

		JPanel notificationsInfo = new JPanel();
		notificationsInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		notificationsInfo.setBackground(new Color(255, 255, 255));
		notificationsInfo.setBounds(52, 351, 667, 227);
		notificationsBorder.add(notificationsInfo);

		JLabel notifTitle = new JLabel("NOTIFICATIONS TITLE");
		notifTitle.setBounds(21, 55, 625, 44);
		notifTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		notificationsInfo.setLayout(null);

		JLabel notifInfo = new JLabel("Description of notifications. eg. accepted scholarship x");
		notifInfo.setBounds(21, 105, 625, 60);
		notifInfo.setAlignmentY(Component.TOP_ALIGNMENT);
		notifInfo.setFont(new Font("Calibri", Font.PLAIN, 17));
		notificationsInfo.add(notifInfo);
		notificationsInfo.add(notifTitle);

		ArrayList<Notification> notifObject = notificationManager.getNotifications(currentUser.getUserID());

		notificationsList = new JList();
		notificationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (notificationsList.getSelectedIndex() < 0) {
					notifTitle.setText("NOTIFICATIONS TITLE");
					notifInfo.setText("Description of notifications. eg. accepted scholarship x");

				} else {
					int notifIndex = notificationsList.getSelectedIndex();
					notifTitle.setText(notifObject.get(notifIndex).getTitle());
					notifInfo.setText("<html>" + notifObject.get(notifIndex).getMessage() + "</html>");

				}

			}
		});
		notificationsList.setFont(new Font("Tahoma", Font.PLAIN, 25));

		notificationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		notificationsList.setBorder(new LineBorder(new Color(147, 112, 219)));
		notificationsList.setBackground(new Color(230, 230, 250));
		notificationsList.setBounds(10, 11, 742, 497);
		notificationsList.setSelectedIndex(0);

		notifs = notificationManager.getNotificationTitles(currentUser.getUserID());
		notificationsList.setListData(notifs.toArray());

		notificationsBorder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				notificationsList.clearSelection();
			}
		});

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) notificationsList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPaneNotifications = new JScrollPane();
		scrollPaneNotifications.setBounds(52, 78, 667, 227);
		scrollPaneNotifications.setBackground(new Color(240, 248, 255));
		scrollPaneNotifications.setForeground(new Color(245, 255, 250));
		scrollPaneNotifications.setViewportView(notificationsList);
		notificationsBorder.add(scrollPaneNotifications);

	}
}