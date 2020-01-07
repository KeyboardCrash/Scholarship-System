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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.DefaultListCellRenderer;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextArea;
import java.awt.Rectangle;

/**
 * This is the main graphical user interface (GUI) for the Admin login. This
 * encompasses everything form viewing, adding, editing scholarships, etc. to
 * handling reward and reject of scholarships to students. Admin is able to view
 * student data and profiles as well.
 * 
 * @author Jessie, Shaina
 * @version final (4/12/19)
 * 
 */
public class MainPanelAdmin extends JPanel {

	/**
	 * All local variables related to this class (components)
	 */
	public ScholarshipManager scholarshipManager;
	public JPanel sideBar = new JPanel();
	public JPanel stProfileScreen = new JPanel();
	public JPanel studentsScreen = new JPanel();
	public JPanel scholarshipScreen = new JPanel();
	public JPanel notificationScreen = new JPanel();
	public JPanel Activitypanel = new JPanel();
	public JPanel applicationViewPanel;
	public JPanel scholApplicantsPanel;
	public JPanel newScholPanel;
	public JPanel scholHomePanel;
	public JPanel scholListPanel;
	public JPanel scholInfoPanel;
	private JPanel studentsBorder;
	private JPanel studentProfilePanel;
	private JPanel distributeScholarshipsPanel;
	private JLabel profilePic;
	private JFrame frame;

	public int sideBarWidth = 260;
	public int activityScreenWidth = MainFrame.widthSize - sideBarWidth;
	private int scholarshipIndex = 0;
	private int studentIndex = 0;
	private String currentScholarshipTitle;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private NumberFormat formatter = new DecimalFormat("#0.00");
	private String[] facultyStrings = { "Science", "Arts", "Medicine", "Environmental_Design", "Graduate_Studies",
			"Business", "Kinesiology", "Law", "Engineering", "Social Work", "Veterinary_Medicine", "Education" };
	private String[] levelStrings = { "Undergraduate", "Graduate", "Doctorate", "PhD" };
	private String[] genderStrings = { "Male", "Female", "Other" };
	private ArrayList<String> scholarshipTitles;
	private ArrayList<String> scholarshipApplicantNames;
	private ArrayList<String> notifs;
	private JList applicantsList = new JList();
	private JList allScholarshipList;
	private JList studentApplicationsList;
	private JList studentsList = new JList();
	private JList notificationsList;
	private DefaultListModel scholarshipModel;
	private DefaultListModel studentModel;
	private DefaultListModel applicantsModel;
	private Admin currentUser;
	private ArrayList<String> scholarshipList = new ArrayList<>();
	private ArrayList<String> studentSortList = new ArrayList<>();
	private Scholarship currentScholarship;
	private Student currentStudent;
	private ScholarshipApplication currentApplication;
	private UserDatabase udb;
	private StudentManager studentManager;
	private NotificationManager notificationManager;
	private int studentID;
	private JScrollPane reqScroll;
	private JScrollPane applicationsScroll;
	private EditableTableModel tableModel;
	private JTextArea txtScholarshipTitle;
	private JTextField tfFacultyInfo;
	private JLabel stuScreenName;
	private JLabel stuScreenID;
	private JLabel stuScreenYear;
	private JLabel stuScreenFaculty;
	private JLabel stuScreenLevel;
	private JLabel stuScreenGPA;
	private JLabel lblScholarshipTitleApplicants;
	private JLabel lblQuantityInfo;
	private JLabel lblfieldNotFilledincorrectEdit;
	private JLabel lblfieldNotFilledincorrect;
	private JLabel lblStatusChange;
	private JLabel lblAgeApp;
	private JLabel lblGPAApp;
	private JLabel lblYearApp;
	private JLabel lblFacultyApp;
	private JLabel lblLevelApp;
	private JLabel lblGenderApp;
	private JLabel lblMajorApp;
	private JLabel lblCitizenApp;
	private JLabel lblContactApp;
	private JLabel lblExtraCurricApp;
	private JLabel lblIncomeApp;
	private JLabel lblPriorityApp;
	private JLabel lblStudentNameConfirmation;
	private JLabel lblScholarshipTitleConfirmation;
	private JLabel lblStatusApp;
	private JLabel lblStatusTxt;
	private JLabel lblIDApp;
	private JLabel lblpressAgainTo;
	private JLabel lblAwarded;
	private JLabel lblTo;
	private JLabel studentNameApp;
	private JTextField tfDeadlineInfo;
	private JTextField tfDonorInfo;
	private JTable reqTable;
	private JTextField tfQuantityInfo;
	private JTextField tfAmountInfo;
	private JTextField tfAmount;
	private JTextField tfQuantity;
	private JTextField tfDonor;
	private JTextField tfDayDate;
	private JTextField tfTitle;
	private JTextField tfGPA;
	private JTextField tfYear;
	private JTextField tfMonthDate;
	private JTextField tfYearDate;
	private JTextField tfMajor;
	private JTextField tfIncome;
	private JTextField tfAge;
	private JTextField tfCitizen;
	private JTextField tfExtraCurricular;
	private JButton btnViewProfile;
	private JButton btnApplicationBack;
	private JButton btnAward;
	private JButton btnReject;
	private boolean onStudentsScreenView;

	/**
	 * 
	 * Constructor for this class, creates all GUI components and sets appropriate
	 * objects to this class
	 * 
	 * @param frame
	 * @param scManager
	 * @param udb
	 * @param admin
	 * @param studentManager
	 * 
	 */
	public MainPanelAdmin(JFrame frame, ScholarshipManager scManager, UserDatabase udb, Admin admin,
			StudentManager studentManager, NotificationManager notificationManager) {

		scholarshipManager = scManager;
		this.currentUser = admin;
		this.frame = frame;
		this.udb = udb;
		this.studentManager = studentManager;
		this.notificationManager = notificationManager;
		currentUser.setScholarshipManager(scholarshipManager);

		setBounds(0, 0, MainFrame.widthSize, MainFrame.heightSize);
		setLayout(new BorderLayout(0, 0));

		Activitypanel.setBackground(Color.WHITE);
		add(Activitypanel, BorderLayout.CENTER);
		Activitypanel.setPreferredSize(new Dimension(activityScreenWidth, MainFrame.heightSize));
		Activitypanel.setLayout(new BorderLayout(0, 0));

		createStProfileScreen();
		createSideBar();
		createScholarshipScreen();
		createNotificationScreen();
		createStudentsScreen();

		// Main activity panel. ONLY ONE JPanel can be shown since its a borderlayout
		// Use switch method to change panels. Change panel below to see it in design in
		// win builder

		Activitypanel.add(scholarshipScreen, BorderLayout.CENTER);

	}

	/**
	 * 
	 * Switches the view of the sidebar buttons to the screen that its viewing. The
	 * home screen for each type will show up.
	 * 
	 * @param pane
	 */
	public void switchAP(JPanel pane) {
		Activitypanel.remove(Activitypanel.getComponent(0));
		Activitypanel.add(pane, BorderLayout.CENTER);
		Activitypanel.repaint();
		Activitypanel.revalidate();
	}

	/**
	 * 
	 * Creates the GUI sidebar components that can be clicked to switch screens
	 * 
	 * 
	 */
	public void createSideBar() {

		double botHeight = MainFrame.heightSize * (1 / 3);

		JPanel sideBar = new JPanel();
		sideBar.setVerifyInputWhenFocusTarget(false);
		sideBar.setBackground(new Color(102, 204, 204));
		add(sideBar, BorderLayout.WEST);
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
				scholHomePanel.setVisible(true);
				scholApplicantsPanel.setVisible(false);
				scholInfoPanel.setVisible(false);
				newScholPanel.setVisible(false);
				applicationViewPanel.setVisible(false);
				distributeScholarshipsPanel.setVisible(false);
				switchAP(scholarshipScreen);
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
		lblAvailableScholarships.setFont(new Font("Dialog", Font.BOLD, 26));
		scholarshipSelection.add(lblAvailableScholarships, "name_168470910017400");

		JPanel studentsSelection = new JPanel();
		studentsSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				studentsBorder.setVisible(true);
				studentProfilePanel.setVisible(false);
				switchAP(studentsScreen);

			}
		});
		studentsSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(studentsSelection);
		studentsSelection.setLayout(new CardLayout(0, 0));

		JLabel lblNotifications = new JLabel("Students");
		lblNotifications.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		lblNotifications.setFont(new Font("Dubai", Font.BOLD, 26));
		lblNotifications.setForeground(Color.WHITE);
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		studentsSelection.add(lblNotifications, "name_1394916853619000");

		JPanel notificationsSelection = new JPanel();
		notificationsSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifs = notificationManager.getNotificationTitles(123);
				notificationsList.setListData(notifs.toArray());
				switchAP(notificationScreen);
			}
		});
		notificationsSelection.setBackground(new Color(51, 204, 204));
		sideBar.add(notificationsSelection);
		notificationsSelection.setLayout(new CardLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Notifications");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 26));
		notificationsSelection.add(lblNewLabel, "name_1394762229728200");

		JPanel botSideBar = new JPanel();
		botSideBar.setBackground(new Color(51, 102, 153));
		sideBar.add(botSideBar);
		botSideBar.setPreferredSize(new Dimension(sideBarWidth, 200));
		botSideBar.setLayout(null);

		JButton BtnLogout = new JButton("LOGOUT");
		BtnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		BtnLogout.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		BtnLogout.setBackground(new Color(0, 102, 153));
		BtnLogout.setForeground(new Color(255, 255, 255));
		BtnLogout.setBounds(68, 116, 117, 43);
		BtnLogout.setOpaque(true);
		BtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				revalidate();
				repaint();
				LoginRegistrationPane loginReg = new LoginRegistrationPane(frame, udb, scholarshipManager,
						studentManager, notificationManager);
				frame.setContentPane(loginReg);
			}
		});
		botSideBar.add(BtnLogout);

	}

	/**
	 * 
	 * Iterates through the scholarship JList model that is used to display
	 * scholarship titles. This will be called every time the Jlist needs to be
	 * refreshed in the case of deletion or addition of a scholarship
	 * 
	 */
	public void updateScholarshipList() {
		scholarshipTitles = scholarshipManager.getTitles();
		scholarshipModel.clear();
		for (int i = 0; i < scholarshipTitles.size(); i++) {
			scholarshipModel.addElement(scholarshipTitles.get(i));
		}
		allScholarshipList.setModel(scholarshipModel);
	}

	/**
	 * 
	 * Creates the sidebar switch view for profile screen of the admin
	 * 
	 */
	public void createStProfileScreen() {
		stProfileScreen.setBackground(Color.WHITE);
		stProfileScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);

		Image red = new ImageIcon(this.getClass().getResource("/ScholarshipSystem/profilePic.png")).getImage();
		Image scaledred = red.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		stProfileScreen.setLayout(null);

		profilePic = new JLabel("");
		profilePic.setIcon(new ImageIcon(scaledred));
		profilePic.setBounds(140, 200, 159, 157);
		frame.getContentPane().add(profilePic);
		stProfileScreen.add(profilePic);
		stProfileScreen.add(profilePic);

		JLabel userName = new JLabel(currentUser.getFirstName() + " " + currentUser.getLastName());
		userName.setFont(new Font("Dubai", Font.BOLD, 30));
		userName.setBounds(332, 219, 365, 45);
		stProfileScreen.add(userName);

		JLabel userID = new JLabel("User ID: " + Integer.toString(currentUser.getUserID()));
		userID.setFont(new Font("Dubai", Font.PLAIN, 22));
		userID.setBounds(332, 275, 268, 32);
		stProfileScreen.add(userID);

		JLabel userType = new JLabel("Type: Admin");
		userType.setFont(new Font("Dubai", Font.PLAIN, 22));
		userType.setBounds(332, 307, 268, 32);
		stProfileScreen.add(userType);

		EmptyBorder titleBorders = new EmptyBorder(0, 20, 0, 0);

	}

	/**
	 * 
	 * Sets up the requirements table that is shown in scholarship info panel on the
	 * scholarship switch screen.
	 * 
	 */
	public void showReqTable() {

		String[] columns = new String[] { "REQUIREMENTS", "" };
		Object[][] requirements = new String[][] { { "Age:", "" }, { "GPA:", "" }, { "Level: " }, { "Year:", "" },
				{ "Major:", "" }, { "Gender:", "" }, { "Income ($):", "" }, { "Citizen:", "" },
				{ "Extra Curriculars:", "" } };

		tableModel = new EditableTableModel(requirements, columns);

		reqTable = new JTable(tableModel);

		reqTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		reqTable.setRowHeight(25);
		reqTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		reqTable.setBackground(Color.WHITE);
		reqTable.setBounds(5, 10, 300, 150);

		reqScroll = new JScrollPane(reqTable);
		reqScroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		reqScroll.setAutoscrolls(true);
		reqScroll.setBounds(45, 291, 706, 200);
		reqTable.setFillsViewportHeight(true);
		JTableHeader tableHeader = reqTable.getTableHeader();
		Font f = new Font("Arial", Font.BOLD, 18);
		tableHeader.setFont(f);

		scholInfoPanel.add(reqScroll);

	}

	/**
	 * 
	 * Creates the scholarship info panel and all its components. Will consist of
	 * displaying all information for a created scholarship and the ability to edit
	 * the scholarship fields if closed and no applicants.
	 * 
	 */
	public void createScholarshipInfo() {

		scholInfoPanel = new JPanel();
		scholInfoPanel.setBackground(Color.WHITE);
		scholInfoPanel.setBounds(0, 0, activityScreenWidth, MainFrame.heightSize);
		scholInfoPanel.setVisible(false);
		scholInfoPanel.setLayout(null);

		JLabel lblAmountInfo = new JLabel("Amount ($):");
		lblAmountInfo.setFont(new Font("Dubai", Font.PLAIN, 24));
		lblAmountInfo.setBounds(46, 101, 137, 29);
		scholInfoPanel.add(lblAmountInfo);

		lblQuantityInfo = new JLabel("Quantity:");
		lblQuantityInfo.setFont(new Font("Dubai", Font.PLAIN, 24));
		lblQuantityInfo.setBounds(46, 141, 211, 25);
		scholInfoPanel.add(lblQuantityInfo);

		JLabel lblDeadlineInfo = new JLabel("Deadline:");
		lblDeadlineInfo.setFont(new Font("Dubai", Font.PLAIN, 24));
		lblDeadlineInfo.setBounds(45, 211, 109, 25);
		scholInfoPanel.add(lblDeadlineInfo);

		JLabel lblDonorInfo = new JLabel("Donor:");
		lblDonorInfo.setFont(new Font("Dubai", Font.PLAIN, 24));
		lblDonorInfo.setBounds(45, 177, 109, 25);
		scholInfoPanel.add(lblDonorInfo);

		JLabel lblFacultyInfo = new JLabel("Faculty:");
		lblFacultyInfo.setFont(new Font("Dubai", Font.PLAIN, 24));
		lblFacultyInfo.setBounds(45, 247, 126, 25);
		scholInfoPanel.add(lblFacultyInfo);

		JPanel bottomBlueLine = new JPanel();
		bottomBlueLine.setBackground(new Color(51, 204, 204));
		bottomBlueLine.setBounds(0, 505, 782, 19);
		scholInfoPanel.add(bottomBlueLine);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(UIManager.getColor("Button.background"));
		topPanel.setBounds(0, 0, 782, 82);
		scholInfoPanel.add(topPanel);
		topPanel.setLayout(null);
		scholInfoPanel.add(topPanel);

		txtScholarshipTitle = new JTextArea();
		txtScholarshipTitle.setEditable(false);
		txtScholarshipTitle.setFont(new Font("Dialog", Font.BOLD, 34));
		txtScholarshipTitle.setBounds(10, 23, 636, 48);
		txtScholarshipTitle.setBackground(UIManager.getColor("Button.background"));
		topPanel.add(txtScholarshipTitle);
		txtScholarshipTitle.setText("Scholarship Title");

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblStatus.setBounds(656, 21, 84, 23);
		topPanel.add(lblStatus);

		lblStatusChange = new JLabel("dysta");
		lblStatusChange.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusChange.setForeground(new Color(204, 0, 0));
		lblStatusChange.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStatusChange.setBounds(605, 49, 135, 22);
		topPanel.add(lblStatusChange);

		showReqTable();

		JLabel lblEditableFields = new JLabel("*All fields are now editable");
		lblEditableFields.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditableFields.setVisible(false);
		lblEditableFields.setForeground(new Color(0, 102, 153));
		lblEditableFields.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEditableFields.setBounds(410, 93, 362, 34);
		lblEditableFields.setVisible(false);
		scholInfoPanel.add(lblEditableFields);

		JPanel editButtons = new JPanel();
		editButtons.setVisible(false);

		JPanel bottomButtonsStart = new JPanel();
		bottomButtonsStart.setBackground(Color.WHITE);
		bottomButtonsStart.setBounds(0, 524, 782, 91);
		scholInfoPanel.add(bottomButtonsStart);

		JButton btnEdit = new JButton("Edit ");
		btnEdit.setBounds(36, 20, 133, 43);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Check to see if the scholarship is closed and there are no applicants
				// If so, then editing is allowed
				if (currentScholarship.getApplicationOpen() == false
						&& currentScholarship.getApplicants().size() == 0) {

					lblfieldNotFilledincorrectEdit.setVisible(false);
					bottomButtonsStart.setVisible(false);
					editButtons.setVisible(true);
					lblEditableFields.setText("*All fields are now editable");
					lblEditableFields.setVisible(true);

					tableModel.setEditable(1, true);
					txtScholarshipTitle.setEditable(true);
					tfFacultyInfo.setEditable(true);
					tfDeadlineInfo.setEditable(true);
					tfDonorInfo.setEditable(true);
					tfQuantityInfo.setEditable(true);
					tfAmountInfo.setEditable(true);

				}

				// Else, there are applicants or the scholarship is open, cannot edit
				else {
					lblEditableFields.setText("*Cannot edit open scholarship/has applicants.");
					lblEditableFields.setVisible(true);

				}

			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBackground(new Color(51, 204, 204));
		btnEdit.setFont(new Font("Dubai", Font.BOLD, 20));

		JButton seeList_bt = new JButton("Back");
		seeList_bt.setBounds(644, 20, 111, 43);
		seeList_bt.setForeground(Color.WHITE);
		seeList_bt.setBackground(new Color(51, 204, 204));
		seeList_bt.setFont(new Font("Dubai", Font.BOLD, 20));

		seeList_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Back to main scholarship home screen
				lblEditableFields.setVisible(false);
				scholInfoPanel.revalidate();
				scholInfoPanel.repaint();
				scholHomePanel.revalidate();
				scholHomePanel.repaint();
				scholHomePanel.setVisible(true);
				scholInfoPanel.setVisible(false);

			}
		});

		JButton btnApplciants = new JButton("Applicants");
		btnApplciants.setBounds(197, 20, 155, 43);
		btnApplciants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Set visibility of labels
				lblEditableFields.setVisible(false);
				lblScholarshipTitleApplicants.setText(currentScholarship.getTitle());

				// Set up applicants list
				scholarshipApplicantNames = currentScholarship.getApplicants();
				applicantsList.setListData(scholarshipApplicantNames.toArray());

				scholApplicantsPanel.setVisible(true);
				scholInfoPanel.setVisible(false);
			}
		});
		btnApplciants.setForeground(Color.WHITE);
		btnApplciants.setFont(new Font("Dialog", Font.BOLD, 20));
		btnApplciants.setBackground(new Color(51, 204, 204));
		btnApplciants.setOpaque(true);
		btnApplciants.setBorderPainted(false);

		JButton btnOpenScholarship = new JButton("Open Scholarship");
		btnOpenScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Check to see if there are applicants already applied or scholarship has been
				// closed
				// If so, then cannot be opened.
				if (currentScholarship.getApplicants().size() != 0 || currentScholarship.getAwardLimit() == 0) {
					lblEditableFields.setText("*No more awards available/already opened.");
					lblEditableFields.setVisible(true);
				}

				// Else, able to open the scholarship
				else {
					lblEditableFields.setVisible(false);
					lblStatusChange.setForeground(new Color(51, 153, 0));
					lblStatusChange.setText("OPEN");
					currentUser.openScholarship(currentScholarshipTitle);

				}

			}
		});
		btnOpenScholarship.setForeground(new Color(255, 255, 255));
		btnOpenScholarship.setFont(new Font("Dialog", Font.BOLD, 20));
		btnOpenScholarship.setBackground(new Color(51, 204, 204));
		btnOpenScholarship.setBounds(379, 20, 233, 43);
		bottomButtonsStart.setLayout(null);
		bottomButtonsStart.add(btnEdit);
		bottomButtonsStart.add(btnOpenScholarship);
		bottomButtonsStart.add(btnApplciants);
		bottomButtonsStart.add(seeList_bt);
		editButtons.setBackground(Color.WHITE);
		editButtons.setBounds(0, 524, 782, 91);
		scholInfoPanel.add(editButtons);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Stop table from editing
				if (reqTable.isEditing()) {
					reqTable.getCellEditor().stopCellEditing();
				}

				// Set all editable fields to being un-editable
				txtScholarshipTitle.setEditable(false);
				tableModel.setEditable(1, false);
				txtScholarshipTitle.setEditable(false);
				tfFacultyInfo.setEditable(false);
				tfDeadlineInfo.setEditable(false);
				tfDonorInfo.setEditable(false);
				tfQuantityInfo.setEditable(false);
				tfAmountInfo.setEditable(false);
				lblEditableFields.setVisible(false);
				editButtons.setVisible(false);
				bottomButtonsStart.setVisible(true);
				editButtons.setVisible(false);

				// Reset display of information, show original information before any changes
				txtScholarshipTitle.setText(currentScholarship.getTitle());
				tfAmountInfo.setText(formatter.format(currentScholarship.getAmount()));
				tfQuantityInfo.setText(Integer.toString(currentScholarship.getAwardLimit()));
				tfDonorInfo.setText(currentScholarship.getDonor());
				tfDeadlineInfo.setText(currentScholarship.dateFormat());
				tfFacultyInfo.setText(currentScholarship.getFaculty().str);
				tableModel.setValueAt(currentScholarship.getbaseline("Age"), 0, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("GPA"), 1, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Level"), 2, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Year"), 3, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Major"), 4, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Gender"), 5, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Income"), 6, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Citizen"), 7, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("ExtraCuric"), 8, 1);

				scholInfoPanel.revalidate();
				scholInfoPanel.repaint();

			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCancel.setBackground(new Color(51, 204, 204));
		btnCancel.setOpaque(true);
		btnCancel.setBorderPainted(false);

		lblfieldNotFilledincorrectEdit = new JLabel("*Invalid field(s). Try again.");
		lblfieldNotFilledincorrectEdit.setVisible(false);
		lblfieldNotFilledincorrectEdit.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblfieldNotFilledincorrectEdit.setForeground(Color.RED);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					// Stop table editing
					if (reqTable.isEditing()) {
						reqTable.getCellEditor().stopCellEditing();
					}

					// Checking format
					int awardLimit = Integer.parseInt(tfQuantityInfo.getText());
					double amount = Double.parseDouble(tfAmountInfo.getText());
					int age = Integer.parseInt(tableModel.getValueAt(0, 1) + "");
					double gpa = Double.parseDouble(tableModel.getValueAt(1, 1) + "");
					int years = Integer.parseInt(tableModel.getValueAt(3, 1) + "");
					double income = Double.parseDouble(tableModel.getValueAt(6, 1) + "");
					Enumerations.gender gender = Enumerations.gender
							.valueOf((tableModel.getValueAt(5, 1) + "").toUpperCase());
					Enumerations.faculty faculty = Enumerations.faculty.valueOf(tfFacultyInfo.getText());
					Enumerations.level level = Enumerations.level
							.valueOf(("" + tableModel.getValueAt(2, 1)).toUpperCase());

					String title = txtScholarshipTitle.getText();
					String deadline = tfDeadlineInfo.getText();
					String donor = tfDonorInfo.getText();
					String major = "" + tableModel.getValueAt(4, 1);
					String citizen = "" + tableModel.getValueAt(7, 1);
					String extracurric = "" + tableModel.getValueAt(8, 1);

					lblfieldNotFilledincorrectEdit.setVisible(false);

					// Check to see if string fields are filled
					// If so, proceed to saving all new changes
					if (!(title.equals("")) && !(deadline.equals("")) && !(donor.equals("")) && !(major.equals(""))
							&& !(citizen.equals("")) && !(extracurric.equals(""))) {

						// Set all fields to uneditable
						txtScholarshipTitle.setEditable(false);
						tableModel.setEditable(1, false);
						txtScholarshipTitle.setEditable(false);
						tfFacultyInfo.setEditable(false);
						tfDeadlineInfo.setEditable(false);
						tfDonorInfo.setEditable(false);
						tfQuantityInfo.setEditable(false);
						tfAmountInfo.setEditable(false);
						lblEditableFields.setVisible(false);
						editButtons.setVisible(false);
						bottomButtonsStart.setVisible(true);

						// Parse the date
						String[] date = deadline.split("/");
						String month = date[0];
						String day = date[1];
						String year = date[2];

						// Save fields for data not displayed in table to scholarship object
						currentUser.editScholarship(currentScholarshipTitle, "Title", txtScholarshipTitle.getText());
						currentUser.editScholarship(currentScholarshipTitle, "Faculty", tfFacultyInfo.getText());
						currentUser.editScholarship(currentScholarshipTitle, "Date Month", month);
						currentUser.editScholarship(currentScholarshipTitle, "Date Day", day);
						currentUser.editScholarship(currentScholarshipTitle, "Date Year", year);
						currentUser.editScholarship(currentScholarshipTitle, "Donor", tfDonorInfo.getText());
						currentUser.editScholarship(currentScholarshipTitle, "Award Limit", tfQuantityInfo.getText());
						currentUser.editScholarship(currentScholarshipTitle, "Amount", tfAmountInfo.getText());

						// Save fields for data displayed in table to scholarship object
						currentUser.editScholarship(currentScholarshipTitle, "Age", "" + tableModel.getValueAt(0, 1));
						currentUser.editScholarship(currentScholarshipTitle, "GPA", "" + tableModel.getValueAt(1, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Level", "" + tableModel.getValueAt(2, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Year", "" + tableModel.getValueAt(3, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Major", "" + tableModel.getValueAt(4, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Gender",
								"" + tableModel.getValueAt(5, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Income",
								"" + tableModel.getValueAt(6, 1));
						currentUser.editScholarship(currentScholarshipTitle, "Citizen",
								"" + tableModel.getValueAt(7, 1));
						currentUser.editScholarship(currentScholarshipTitle, "ExtraCuric",
								"" + tableModel.getValueAt(8, 1));
						tableModel.fireTableDataChanged();

						// Update Jlist display
						updateScholarshipList();

					}

					// Cannot save changes yet, display error message
					else {
						lblfieldNotFilledincorrectEdit.setVisible(true);
					}

					// Print out error messages
				} catch (Exception a) {
					a.printStackTrace();
					lblfieldNotFilledincorrectEdit.setVisible(true);

				}

			}
		});

		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Dialog", Font.BOLD, 20));
		btnConfirm.setBackground(new Color(51, 204, 204));
		btnConfirm.setOpaque(true);
		btnConfirm.setBorderPainted(false);

		GroupLayout gl_editButtons = new GroupLayout(editButtons);
		gl_editButtons.setHorizontalGroup(gl_editButtons.createParallelGroup(Alignment.TRAILING).addGroup(gl_editButtons
				.createSequentialGroup().addGap(77)
				.addComponent(lblfieldNotFilledincorrectEdit, GroupLayout.PREFERRED_SIZE, 219,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE).addGap(60)
				.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE).addGap(52)));
		gl_editButtons
				.setVerticalGroup(gl_editButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_editButtons.createSequentialGroup().addGap(24)
								.addGroup(gl_editButtons.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
										.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 43,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblfieldNotFilledincorrectEdit, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(24)));
		editButtons.setLayout(gl_editButtons);

		tfFacultyInfo = new JTextField();
		tfFacultyInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfFacultyInfo.setEditable(false);
		tfFacultyInfo.setColumns(10);
		tfFacultyInfo.setBounds(185, 247, 205, 25);
		scholInfoPanel.add(tfFacultyInfo);

		tfDeadlineInfo = new JTextField();
		tfDeadlineInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfDeadlineInfo.setEditable(false);
		tfDeadlineInfo.setColumns(10);
		tfDeadlineInfo.setBounds(185, 209, 205, 25);
		scholInfoPanel.add(tfDeadlineInfo);

		tfDonorInfo = new JTextField();
		tfDonorInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfDonorInfo.setEditable(false);
		tfDonorInfo.setColumns(10);
		tfDonorInfo.setBounds(185, 174, 205, 25);
		scholInfoPanel.add(tfDonorInfo);

		tfQuantityInfo = new JTextField();
		tfQuantityInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfQuantityInfo.setEditable(false);
		tfQuantityInfo.setColumns(10);
		tfQuantityInfo.setBounds(185, 141, 205, 25);
		scholInfoPanel.add(tfQuantityInfo);

		tfAmountInfo = new JTextField();
		tfAmountInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfAmountInfo.setEditable(false);
		tfAmountInfo.setColumns(10);
		tfAmountInfo.setBounds(185, 101, 205, 25);
		scholInfoPanel.add(tfAmountInfo);

	}

	/**
	 * 
	 * Create screen for adding new scholarships This will display a standard form
	 * that will be filled by the admin as well as error checking for proper format
	 * of fields
	 * 
	 */
	public void createNewScholarship() {

		newScholPanel = new JPanel();
		newScholPanel.setBounds(0, 0, 782, 615);
		newScholPanel.setBackground(Color.WHITE);
		newScholPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		newScholPanel.setVisible(false);

		newScholPanel.setLayout(null);

		JLabel lblAmount2 = new JLabel("Amount:");
		lblAmount2.setBounds(42, 166, 109, 29);
		newScholPanel.add(lblAmount2);
		lblAmount2.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblAwardLimit = new JLabel("Quantity:");
		lblAwardLimit.setBounds(42, 206, 98, 25);
		newScholPanel.add(lblAwardLimit);
		lblAwardLimit.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setBounds(42, 242, 109, 22);
		newScholPanel.add(lblDonor);
		lblDonor.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblDeadline = new JLabel("Deadline (mm/dd/yyyy):");
		lblDeadline.setBounds(42, 276, 297, 35);
		newScholPanel.add(lblDeadline);
		lblDeadline.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setBounds(42, 420, 126, 25);
		newScholPanel.add(lblFaculty);
		lblFaculty.setFont(new Font("Dialog", Font.PLAIN, 24));

		tfAmount = new JTextField("");
		tfAmount.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfAmount.setBounds(154, 166, 205, 25);
		newScholPanel.add(tfAmount);
		tfAmount.setColumns(10);

		tfQuantity = new JTextField("");
		tfQuantity.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfQuantity.setBounds(154, 203, 205, 25);
		newScholPanel.add(tfQuantity);
		tfQuantity.setColumns(10);

		tfDonor = new JTextField("");
		tfDonor.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfDonor.setBounds(154, 238, 205, 25);
		newScholPanel.add(tfDonor);
		tfDonor.setColumns(10);

		tfDayDate = new JTextField("");
		tfDayDate.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfDayDate.setBounds(154, 350, 205, 25);
		newScholPanel.add(tfDayDate);
		tfDayDate.setColumns(10);

		JPanel header = new JPanel();
		header.setBounds(0, 0, 782, 67);
		newScholPanel.add(header);
		header.setBackground(new Color(102, 102, 204));

		JLabel lblCreateNewScholarship = new JLabel("NEW SCHOLARSHIP");
		lblCreateNewScholarship.setForeground(new Color(255, 255, 255));
		lblCreateNewScholarship.setFont(new Font("Arial", Font.BOLD, 26));

		JLabel lblNote = new JLabel("If not required, type 0 (numerical fields) or write \"N/A\"");
		lblNote.setForeground(new Color(255, 255, 255));
		lblNote.setFont(new Font("Calibri", Font.ITALIC, 17));
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup().addGap(22).addComponent(lblCreateNewScholarship).addGap(37)
						.addComponent(lblNote, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(51, Short.MAX_VALUE)));
		gl_header.setVerticalGroup(gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_header.createSequentialGroup().addGap(18)
						.addGroup(gl_header.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNote, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(lblCreateNewScholarship, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37,
										Short.MAX_VALUE))
						.addContainerGap()));
		header.setLayout(gl_header);

		tfTitle = new JTextField("");
		tfTitle.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfTitle.setColumns(10);
		tfTitle.setBounds(154, 126, 205, 25);
		newScholPanel.add(tfTitle);

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblTitle.setBounds(42, 126, 109, 29);
		newScholPanel.add(lblTitle);

		JLabel lblGPA = new JLabel("GPA:");
		lblGPA.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblGPA.setBounds(418, 164, 126, 25);
		newScholPanel.add(lblGPA);

		tfGPA = new JTextField("");
		tfGPA.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfGPA.setColumns(10);
		tfGPA.setBounds(530, 165, 205, 25);
		newScholPanel.add(tfGPA);

		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblYear.setBounds(418, 236, 126, 25);
		newScholPanel.add(lblYear);

		tfYear = new JTextField("");
		tfYear.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfYear.setColumns(10);
		tfYear.setBounds(530, 237, 205, 25);
		newScholPanel.add(tfYear);

		tfMonthDate = new JTextField("");
		tfMonthDate.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfMonthDate.setColumns(10);
		tfMonthDate.setBounds(154, 313, 205, 25);
		newScholPanel.add(tfMonthDate);

		tfYearDate = new JTextField("");
		tfYearDate.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfYearDate.setColumns(10);
		tfYearDate.setBounds(154, 384, 205, 25);
		newScholPanel.add(tfYearDate);

		JLabel lblday = new JLabel("Day");
		lblday.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblday.setBounds(82, 346, 58, 26);
		newScholPanel.add(lblday);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblMonth.setBounds(82, 312, 58, 26);
		newScholPanel.add(lblMonth);

		JLabel lblYear2 = new JLabel("Year");
		lblYear2.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblYear2.setBounds(82, 383, 58, 26);
		newScholPanel.add(lblYear2);

		JLabel lblMajor = new JLabel("Major:");
		lblMajor.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblMajor.setBounds(418, 272, 126, 25);
		newScholPanel.add(lblMajor);

		tfMajor = new JTextField("");
		tfMajor.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfMajor.setColumns(10);
		tfMajor.setBounds(530, 273, 205, 25);
		newScholPanel.add(tfMajor);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblGender.setBounds(418, 308, 126, 25);
		newScholPanel.add(lblGender);

		JLabel lblIncome = new JLabel("Income:");
		lblIncome.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblIncome.setBounds(418, 344, 126, 25);
		newScholPanel.add(lblIncome);

		tfIncome = new JTextField("");
		tfIncome.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfIncome.setColumns(10);
		tfIncome.setBounds(530, 344, 205, 25);
		newScholPanel.add(tfIncome);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblAge.setBounds(418, 126, 126, 29);
		newScholPanel.add(lblAge);

		tfAge = new JTextField("");
		tfAge.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfAge.setColumns(10);
		tfAge.setBounds(530, 130, 205, 25);
		newScholPanel.add(tfAge);

		JLabel lblCitizenship = new JLabel("Citizen:");
		lblCitizenship.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblCitizenship.setBounds(418, 380, 109, 25);
		newScholPanel.add(lblCitizenship);

		tfCitizen = new JTextField("");
		tfCitizen.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfCitizen.setColumns(10);
		tfCitizen.setBounds(530, 380, 205, 25);
		newScholPanel.add(tfCitizen);

		JLabel lblExtraCurricular = new JLabel("ExtraCuric:");
		lblExtraCurricular.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblExtraCurricular.setBounds(418, 416, 126, 25);
		newScholPanel.add(lblExtraCurricular);

		tfExtraCurricular = new JTextField("");
		tfExtraCurricular.setFont(new Font("Calibri", Font.PLAIN, 18));
		tfExtraCurricular.setColumns(10);
		tfExtraCurricular.setBounds(530, 416, 205, 25);
		newScholPanel.add(tfExtraCurricular);

		JLabel lblRequirements = new JLabel("Requirements:");
		lblRequirements.setFont(new Font("Dialog", Font.BOLD, 24));
		lblRequirements.setBounds(491, 78, 182, 25);
		newScholPanel.add(lblRequirements);

		lblfieldNotFilledincorrect = new JLabel("*Field not filled/invalid input");
		lblfieldNotFilledincorrect.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblfieldNotFilledincorrect.setForeground(Color.RED);
		lblfieldNotFilledincorrect.setBounds(136, 523, 306, 35);
		newScholPanel.add(lblfieldNotFilledincorrect);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblLevel.setBounds(417, 196, 86, 35);
		newScholPanel.add(lblLevel);

		JComboBox comboLevel = new JComboBox(levelStrings);
		comboLevel.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboLevel.setBounds(530, 203, 205, 23);
		newScholPanel.add(comboLevel);

		JComboBox comboFaculty = new JComboBox(facultyStrings);
		comboFaculty.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboFaculty.setBounds(154, 420, 205, 25);
		newScholPanel.add(comboFaculty);

		JComboBox comboGender = new JComboBox(genderStrings);
		comboGender.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboGender.setBounds(530, 308, 205, 24);
		newScholPanel.add(comboGender);

		JButton btnConfirmNewScholarship = new JButton("Confirm");
		btnConfirmNewScholarship.setOpaque(true);
		btnConfirmNewScholarship.setBorderPainted(false);
		btnConfirmNewScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					// Check format for input
					// Basic information
					String title = tfTitle.getText();
					String donor = tfDonor.getText();
					String faculty = comboFaculty.getSelectedItem().toString();
					double amount = Double.parseDouble(tfAmount.getText());
					int awardLimit = Integer.parseInt(tfQuantity.getText());
					int dayDate = Integer.parseInt(tfDayDate.getText());
					int monthDate = Integer.parseInt(tfMonthDate.getText());
					int yearDate = Integer.parseInt(tfYearDate.getText());

					// Requirements fields
					String age = tfAge.getText();
					String gpa = tfGPA.getText();
					String level = (comboLevel.getSelectedItem().toString());
					String year = tfYear.getText();
					String major = tfMajor.getText();
					String gender = comboGender.getSelectedItem().toString();
					String income = tfIncome.getText();
					String citizen = tfCitizen.getText();
					String extracurric = tfExtraCurricular.getText();

					// If text fields aren't empty, then proceed
					if (!(title.equals("")) && !(donor.equals("")) && !(extracurric.equals("")) && !(citizen.equals(""))
							&& !(income.equals(""))) {

						// Create scholarship
						currentUser.addScholarshipGUI(title, donor, Enumerations.faculty.valueOf(faculty), amount,
								awardLimit, dayDate, monthDate, yearDate, age, gpa, level, year, major, gender, income,
								citizen, extracurric);

						System.out.println("Made scholarship");

						// Update home scholarship Jlist
						updateScholarshipList();

						// Clear all input fields upon exit
						tfTitle.setText("");
						tfDonor.setText("");
						tfAmount.setText("");
						tfQuantity.setText("");
						tfDayDate.setText("");
						tfMonthDate.setText("");
						tfYearDate.setText("");
						tfAge.setText("");
						tfGPA.setText("");
						tfYear.setText("");
						tfMajor.setText("");
						tfIncome.setText("");
						tfCitizen.setText("");
						tfExtraCurricular.setText("");

						scholHomePanel.setVisible(true);
						newScholPanel.setVisible(false);

						// Incorrect/unfilled fields
					} else {
						lblfieldNotFilledincorrect.setVisible(true);
					}

					// Print error messages
				} catch (Exception ex) {
					lblfieldNotFilledincorrect.setVisible(true);
					System.out.println("error" + ex.getMessage());
					System.out.println(ex.getStackTrace());
					System.out.println(ex.getLocalizedMessage());

				}

			}
		});
		btnConfirmNewScholarship.setBackground(new Color(0, 153, 204));
		btnConfirmNewScholarship.setForeground(Color.WHITE);
		btnConfirmNewScholarship.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirmNewScholarship.setBounds(546, 493, 146, 41);
		btnConfirmNewScholarship.setOpaque(true);
		btnConfirmNewScholarship.setBorderPainted(false);
		newScholPanel.add(btnConfirmNewScholarship);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Change display back to scholarship home
				newScholPanel.setVisible(false);
				scholHomePanel.setVisible(true);

			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBackground(new Color(0, 153, 204));
		btnBack.setBounds(546, 543, 146, 41);
		btnBack.setOpaque(true);
		btnBack.setBorderPainted(false);
		newScholPanel.add(btnBack);

	}

	/**
	 * 
	 * Create the view for the applicantation info for each applicant of a
	 * scholarship. This page features the ability for admin to review the
	 * information and manually reject/reward a scholarship to a student.
	 * 
	 */
	public void createApplicationView() {

		applicationViewPanel = new JPanel();
		applicationViewPanel.setBackground(Color.WHITE);
		applicationViewPanel.setBounds(0, 0, 782, 615);
		applicationViewPanel.setLayout(null);
		applicationViewPanel.setVisible(false);

		JPanel topApplicantHeader = new JPanel();
		topApplicantHeader.setBackground(new Color(248, 248, 255));
		topApplicantHeader.setBounds(0, 0, 782, 74);
		applicationViewPanel.add(topApplicantHeader);
		topApplicantHeader.setLayout(null);

		lblStatusTxt = new JLabel("Status:");
		lblStatusTxt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusTxt.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStatusTxt.setBounds(673, 9, 85, 34);
		topApplicantHeader.add(lblStatusTxt);

		lblStatusApp = new JLabel("given status");
		lblStatusApp.setForeground(new Color(128, 0, 128));
		lblStatusApp.setBackground(new Color(0, 0, 0));
		lblStatusApp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusApp.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStatusApp.setBounds(604, 32, 154, 31);
		topApplicantHeader.add(lblStatusApp);

		studentNameApp = new JLabel("Student Name");
		studentNameApp.setBounds(26, 9, 323, 34);
		topApplicantHeader.add(studentNameApp);
		studentNameApp.setFont(new Font("Dialog", Font.BOLD, 26));

		lblIDApp = new JLabel("Student ID");
		lblIDApp.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblIDApp.setBounds(36, 43, 203, 20);
		topApplicantHeader.add(lblIDApp);

		JPanel applicationCenter = new JPanel();
		applicationCenter.setBounds(new Rectangle(0, 0, 13, 0));
		applicationCenter.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		applicationCenter.setBackground(new Color(255, 255, 255));
		applicationCenter.setBounds(0, 74, 782, 444);
		applicationViewPanel.add(applicationCenter);
		applicationCenter.setLayout(null);

		JLabel lblAge_1 = new JLabel("Age:");
		lblAge_1.setBounds(34, 10, 185, 31);
		lblAge_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblAge_1);

		lblAgeApp = new JLabel("New label");
		lblAgeApp.setBounds(224, 12, 278, 26);
		lblAgeApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblAgeApp);

		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(34, 46, 51, 31);
		lblGpa.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblGpa);

		lblGPAApp = new JLabel("New label");
		lblGPAApp.setBounds(224, 46, 260, 31);
		lblGPAApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblGPAApp);

		JLabel lblYear_1 = new JLabel("Year:");
		lblYear_1.setBounds(34, 82, 185, 31);
		lblYear_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblYear_1);

		lblYearApp = new JLabel("New label");
		lblYearApp.setBounds(224, 82, 260, 31);
		lblYearApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblYearApp);

		JLabel lblNewLabel_6 = new JLabel("Faculty:");
		lblNewLabel_6.setBounds(34, 118, 185, 31);
		lblNewLabel_6.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblNewLabel_6);

		lblFacultyApp = new JLabel("New label");
		lblFacultyApp.setBounds(224, 118, 260, 31);
		lblFacultyApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblFacultyApp);

		JLabel lblLevel_1 = new JLabel("Level:");
		lblLevel_1.setBounds(34, 154, 185, 31);
		lblLevel_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblLevel_1);

		lblLevelApp = new JLabel("New label");
		lblLevelApp.setBounds(224, 154, 278, 31);
		lblLevelApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblLevelApp);

		JLabel lblGender_1 = new JLabel("Gender:");
		lblGender_1.setBounds(34, 190, 185, 31);
		lblGender_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblGender_1);

		lblGenderApp = new JLabel("New label");
		lblGenderApp.setBounds(224, 190, 278, 31);
		lblGenderApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblGenderApp);

		JLabel lblMajor_1 = new JLabel("Major:");
		lblMajor_1.setBounds(34, 226, 185, 31);
		lblMajor_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblMajor_1);

		lblMajorApp = new JLabel("New label");
		lblMajorApp.setBounds(224, 226, 278, 31);
		lblMajorApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblMajorApp);

		JLabel lblCitizenship_1 = new JLabel("Citizenship:");
		lblCitizenship_1.setBounds(34, 262, 185, 31);
		lblCitizenship_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblCitizenship_1);

		lblCitizenApp = new JLabel("New label");
		lblCitizenApp.setBounds(224, 262, 278, 31);
		lblCitizenApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblCitizenApp);

		JLabel lblContact = new JLabel("Contact:");
		lblContact.setBounds(34, 298, 185, 31);
		lblContact.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblContact);

		lblContactApp = new JLabel("New label");
		lblContactApp.setBounds(224, 298, 278, 31);
		lblContactApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblContactApp);

		JLabel lblExtraCurricular_1 = new JLabel("Extra Curricular:");
		lblExtraCurricular_1.setBounds(34, 334, 185, 31);
		lblExtraCurricular_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblExtraCurricular_1);

		lblExtraCurricApp = new JLabel("New label");
		lblExtraCurricApp.setBounds(224, 334, 278, 31);
		lblExtraCurricApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblExtraCurricApp);

		JLabel lblIncome_1 = new JLabel("Income:");
		lblIncome_1.setBounds(34, 370, 185, 31);
		lblIncome_1.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblIncome_1);

		lblIncomeApp = new JLabel("New label");
		lblIncomeApp.setBounds(224, 370, 278, 31);
		lblIncomeApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblIncomeApp);

		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setBounds(34, 406, 185, 31);
		lblPriority.setFont(new Font("Calibri", Font.BOLD, 25));
		applicationCenter.add(lblPriority);

		lblPriorityApp = new JLabel("New label");
		lblPriorityApp.setBounds(224, 406, 278, 31);
		lblPriorityApp.setFont(new Font("Calibri", Font.PLAIN, 20));
		applicationCenter.add(lblPriorityApp);

		lblpressAgainTo = new JLabel("*Press again to confirm decision.");
		lblpressAgainTo.setBounds(512, 11, 260, 36);
		applicationCenter.add(lblpressAgainTo);
		lblpressAgainTo.setVisible(false);
		lblpressAgainTo.setForeground(new Color(139, 0, 0));
		lblpressAgainTo.setFont(new Font("Calibri", Font.BOLD, 18));

		btnReject = new JButton("REJECT");
		btnReject.setBounds(37, 539, 146, 44);
		applicationViewPanel.add(btnReject);
		btnReject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Check to see if reject button pressed
				if (btnReject.getText() == "REJECT") {
					btnReject.setText("CONFIRM");
					lblpressAgainTo.setVisible(true);

					// Click again to confirm reject
				} else {
					currentUser.rejectScholarshipAdmin(currentScholarshipTitle, currentStudent, notificationManager);
					lblScholarshipTitleConfirmation.setText(currentScholarshipTitle);
					lblStudentNameConfirmation
							.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
					lblAwarded.setText("Rejected");
					lblTo.setText("for");
					lblpressAgainTo.setVisible(false);
					distributeScholarshipsPanel.setVisible(true);
					applicationViewPanel.setVisible(false);
				}
			}
		});
		btnReject.setForeground(Color.WHITE);
		btnReject.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReject.setBackground(new Color(204, 0, 0));
		btnReject.setOpaque(true);
		btnReject.setBorderPainted(false);

		btnAward = new JButton("AWARD");
		btnAward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAward.setBounds(222, 539, 146, 44);
		applicationViewPanel.add(btnAward);
		btnAward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Check to see if award button pressed
				if (btnAward.getText() == "AWARD") {
					btnAward.setText("CONFIRM");
					lblpressAgainTo.setVisible(true);

					// Confirm the award of scholarship
				} else {
					boolean success = currentUser.rewardScholarshipAdmin(currentScholarshipTitle, currentStudent,
							notificationManager);
					System.out.println(success);
					lblScholarshipTitleConfirmation.setText(currentScholarshipTitle);
					lblStudentNameConfirmation
							.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
					lblpressAgainTo.setVisible(false);
					distributeScholarshipsPanel.setVisible(true);
					applicationViewPanel.setVisible(false);
				}

			}
		});
		btnAward.setForeground(Color.WHITE);
		btnAward.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAward.setBackground(new Color(51, 204, 51));
		btnAward.setOpaque(true);
		btnAward.setBorderPainted(false);

		btnApplicationBack = new JButton("Back");
		btnApplicationBack.setOpaque(true);
		btnApplicationBack.setBorderPainted(false);
		btnApplicationBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If on student screen, back button switches to student view
				if (onStudentsScreenView == true) {
					applicationViewPanel.setVisible(false);
					studentsBorder.setVisible(false);
					studentProfilePanel.setVisible(true);
					switchAP(studentsScreen);

					// Else, on scholarship screen
				} else {
					scholApplicantsPanel.setVisible(true);
					applicationViewPanel.setVisible(false);
				}

			}
		});

		btnApplicationBack.setForeground(Color.WHITE);
		btnApplicationBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnApplicationBack.setBackground(new Color(0, 204, 204));
		btnApplicationBack.setBounds(611, 539, 146, 44);
		applicationViewPanel.add(btnApplicationBack);

		btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set profile information display
				stuScreenName.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
				stuScreenID.setText("" + currentStudent.getUserID());
				stuScreenYear.setText("" + currentStudent.getStudentData().getYear());
				stuScreenFaculty.setText("" + currentStudent.getFaculty());
				stuScreenLevel.setText("" + currentStudent.getLevel());
				stuScreenGPA.setText("" + currentStudent.getGPA());

				// Set applications list
				studentApplicationsList.setListData(currentStudent.getScholarshipAppliedTitles().toArray());

				// Switch to student profile screen
				switchAP(studentsScreen);
				studentsBorder.setVisible(false);
				studentProfilePanel.setVisible(true);

			}
		});
		btnViewProfile.setForeground(Color.WHITE);
		btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewProfile.setBackground(new Color(0, 204, 204));
		btnViewProfile.setBounds(416, 539, 155, 44);
		btnViewProfile.setOpaque(true);
		btnViewProfile.setBorderPainted(false);
		applicationViewPanel.add(btnViewProfile);

	}

	/**
	 * 
	 * Create the panel to display a list of applicants for a specific scolarship
	 * Only one panel view.
	 * 
	 */
	public void createScholarshipApplicants() {

		scholApplicantsPanel = new JPanel();
		scholApplicantsPanel.setBackground(Color.WHITE);
		scholApplicantsPanel.setBounds(0, 0, 782, 615);
		scholApplicantsPanel.setLayout(null);
		scholApplicantsPanel.setVisible(false);

		JLabel lblApplicants = new JLabel("Applicants");
		lblApplicants.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicants.setFont(new Font("Dialog", Font.BOLD, 28));
		lblApplicants.setBounds(0, 88, 782, 37);
		scholApplicantsPanel.add(lblApplicants);

		JButton btnViewApplication = new JButton("View Application");
		btnViewApplication.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Get the student ID from the Jlist
				String selection = applicantsList.getSelectedValue().toString();
				String[] selectionstr = selection.split(", ");
				studentID = Integer.parseInt(selectionstr[1]);

				// Set the current student being viewed to currentStudent object
				currentStudent = studentManager.getStudent(studentID);
				currentApplication = currentUser.getApplication(currentScholarshipTitle, currentStudent);

				// Viewing scholarship screen
				onStudentsScreenView = false;

				// Set visibility of profile and labels
				btnViewProfile.setVisible(true);
				btnApplicationBack.setBounds(611, 539, 146, 44);
				lblpressAgainTo.setVisible(false);

				// Check to see if reward/reject buttons should be displayed
				if ((currentApplication.getStatus().str).equals("Approved")
						|| (currentApplication.getStatus().str).equals("Rejected")
						|| (currentApplication.getStatus().str).equals("Accepted")
						|| (currentApplication.getStatus().str).equals("Declined")) {
					btnAward.setVisible(false);
					btnReject.setVisible(false);

				}

				else {
					btnAward.setVisible(true);
					btnReject.setVisible(true);

				}

				// Set info for application display
				lblStatusApp.setText(currentApplication.getStatus().str);
				studentNameApp.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
				lblIDApp.setText(currentStudent.getUserID() + "");
				lblAgeApp.setText(currentApplication.getField("Age"));
				lblGPAApp.setText(currentApplication.getField("GPA"));
				lblYearApp.setText(currentStudent.getStudentData().getYear() + "");
				lblFacultyApp.setText(currentStudent.getFaculty().str);
				lblLevelApp.setText(currentStudent.getLevel().str);
				lblGenderApp.setText(currentApplication.getField("Gender"));
				lblMajorApp.setText(currentApplication.getField("Major"));
				lblCitizenApp.setText(currentApplication.getField("Citizen"));
				lblContactApp.setText(currentApplication.getField("Contact"));
				lblExtraCurricApp.setText(currentApplication.getField("ExtraCuric"));
				lblIncomeApp.setText(currentApplication.getField("Income"));
				lblPriorityApp.setText(currentStudent.getStudentData().getPriority(currentScholarshipTitle) + "");

				scholApplicantsPanel.setVisible(false);
				applicationViewPanel.setVisible(true);

			}
		});
		btnViewApplication.setForeground(Color.WHITE);
		btnViewApplication.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewApplication.setBackground(new Color(0, 204, 204));
		btnViewApplication.setBounds(175, 524, 200, 44);
		btnViewApplication.setOpaque(true);
		btnViewApplication.setBorderPainted(false);
		scholApplicantsPanel.add(btnViewApplication);

		scholarshipApplicantNames = new ArrayList<>();
		applicantsList.setBorder(new LineBorder(new Color(0, 0, 0)));

		applicantsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		applicantsList.setBounds(10, 11, 742, 497);
		applicantsList.setFont(new Font("Dubai", Font.PLAIN, 30));
		applicantsList.setBackground(new Color(255, 255, 255));
		applicantsList.setListData(scholarshipApplicantNames.toArray());

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) applicantsList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane applicantsScroll = new JScrollPane();
		applicantsScroll.setBounds(91, 143, 624, 355);
		applicantsScroll.setViewportView(applicantsList);
		scholApplicantsPanel.add(applicantsScroll);

		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set the status for the scholarship
				if (scholarshipManager.getScholarship(currentScholarshipTitle).getApplicationOpen() == false) {
					lblStatusChange.setText("CLOSED");
					lblStatusChange.setForeground(new Color(204, 0, 0));
				} else {
					lblStatusChange.setForeground(new Color(51, 153, 0));
					lblStatusChange.setText("OPEN");
				}

				scholApplicantsPanel.setVisible(false);
				scholInfoPanel.setVisible(true);

			}
		});

		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 204, 204));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(487, 524, 146, 44);
		scholApplicantsPanel.add(btnNewButton_2);

		lblScholarshipTitleApplicants = new JLabel("Scholarship Title");
		lblScholarshipTitleApplicants.setHorizontalAlignment(SwingConstants.CENTER);
		lblScholarshipTitleApplicants.setFont(new Font("Dialog", Font.BOLD, 28));
		lblScholarshipTitleApplicants.setBounds(0, 47, 782, 37);
		scholApplicantsPanel.add(lblScholarshipTitleApplicants);

	}

	public void distributeScholarship() {

		distributeScholarshipsPanel = new JPanel();
		distributeScholarshipsPanel.setBounds(0, 0, 782, 615);
		distributeScholarshipsPanel.setBackground(Color.WHITE);
		distributeScholarshipsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		distributeScholarshipsPanel.setLayout(null);
		distributeScholarshipsPanel.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(84, 68, 609, 352);
		distributeScholarshipsPanel.add(panel);
		panel.setLayout(null);

		lblAwarded = new JLabel("Awarded");
		lblAwarded.setFont(new Font("Calibri", Font.PLAIN, 27));
		lblAwarded.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwarded.setBounds(10, 107, 589, 34);
		panel.add(lblAwarded);

		lblScholarshipTitleConfirmation = new JLabel("Scholarship Title");
		lblScholarshipTitleConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblScholarshipTitleConfirmation.setFont(new Font("Calibri", Font.BOLD, 28));
		lblScholarshipTitleConfirmation.setBounds(10, 152, 589, 34);
		panel.add(lblScholarshipTitleConfirmation);

		lblTo = new JLabel("to");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Calibri", Font.PLAIN, 27));
		lblTo.setBounds(10, 197, 589, 34);
		panel.add(lblTo);

		lblStudentNameConfirmation = new JLabel("Student Name");
		lblStudentNameConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentNameConfirmation.setFont(new Font("Calibri", Font.BOLD, 28));
		lblStudentNameConfirmation.setBounds(10, 242, 589, 34);
		panel.add(lblStudentNameConfirmation);

		JLabel lblConfirmation_1 = new JLabel("CONFIRMATION");
		lblConfirmation_1.setForeground(new Color(46, 139, 87));
		lblConfirmation_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmation_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblConfirmation_1.setBounds(10, 22, 589, 34);
		panel.add(lblConfirmation_1);

		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Set view to home
				distributeScholarshipsPanel.setVisible(false);
				scholHomePanel.setVisible(true);
			}
		});
		btnHome.setOpaque(true);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHome.setBorderPainted(false);
		btnHome.setBackground(new Color(0, 204, 204));
		btnHome.setBounds(311, 487, 146, 44);
		distributeScholarshipsPanel.add(btnHome);

	}

	/**
	 * 
	 * Creates the home scholarship screen, which displays a list of current
	 * scholarships in the system Also features buttons to edit, delete, and add new
	 * scholarships Scholarships are listed in Jlist and can be easily navigated
	 * 
	 * 
	 */
	public void createScholarshipHome() {

		scholListPanel = new JPanel();

		scholHomePanel = new JPanel();
		scholHomePanel.setBounds(0, 0, 782, 615);
		scholHomePanel.setBackground(Color.WHITE);
		scholHomePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		scholHomePanel.setLayout(null);

		scholarshipTitles = scholarshipManager.getTitles();
		scholarshipModel = new DefaultListModel() {
		};

		for (int i = 0; i < scholarshipTitles.size(); i++) {
			scholarshipModel.addElement(scholarshipTitles.get(i));
		}

		JButton btnDeleteScholarship = new JButton("DELETE SCHOLARSHIP");
		JLabel lblConfirmation = new JLabel("Delete this scholarship? Press again to confirm.");
		lblConfirmation.setForeground(new Color(153, 0, 0));
		lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmation.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfirmation.setBounds(66, 423, 633, 34);
		lblConfirmation.setVisible(false);
		scholHomePanel.add(lblConfirmation);

		allScholarshipList = new JList(scholarshipModel);
		allScholarshipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allScholarshipList.setBounds(10, 11, 742, 497);
		allScholarshipList.setFont(new Font("Dubai", Font.PLAIN, 30));
		allScholarshipList.setBackground(new Color(255, 255, 255));

		allScholarshipList.setSelectedIndex(0);
		allScholarshipList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				// Get the index of the selected item
				btnDeleteScholarship.setText("DELETE SCHOLARSHIP");
				lblConfirmation.setVisible(false);
				scholarshipIndex = allScholarshipList.getSelectedIndex();
				System.out.println(scholarshipIndex);

			}
		});

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) allScholarshipList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scScrollPane = new JScrollPane();
		scScrollPane.setBounds(66, 34, 633, 382);
		scScrollPane.setViewportView(allScholarshipList);
		scholHomePanel.add(scScrollPane);

		JButton btnNewButton_1 = new JButton("VIEW SCHOLARSHIP");

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Get the current scholarship to be viewed and set the data
				currentScholarshipTitle = "" + scholarshipModel.getElementAt(scholarshipIndex);
				currentScholarship = scholarshipManager.getScholarship(currentScholarshipTitle);
				txtScholarshipTitle.setText(currentScholarship.getTitle());
				tfAmountInfo.setText(formatter.format(currentScholarship.getAmount()));
				tfQuantityInfo.setText(Integer.toString(currentScholarship.getAwardLimit()));
				tfDonorInfo.setText(currentScholarship.getDonor());
				tfDeadlineInfo.setText(currentScholarship.dateFormat());
				tfFacultyInfo.setText(currentScholarship.getFaculty().str);

				// Set the data for the table display
				tableModel.setValueAt(currentScholarship.getbaseline("Age"), 0, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("GPA"), 1, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Level"), 2, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Year"), 3, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Major"), 4, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Gender"), 5, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Income"), 6, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("Citizen"), 7, 1);
				tableModel.setValueAt(currentScholarship.getbaseline("ExtraCuric"), 8, 1);

				// Display status for scholarship
				if (currentScholarship.getApplicationOpen() == true) {
					lblStatusChange.setForeground(new Color(51, 153, 0));
					lblStatusChange.setText("OPEN");
				} else {
					lblStatusChange.setForeground(new Color(51, 153, 0));
					lblStatusChange.setText("CLOSED");
					lblStatusChange.setForeground(new Color(204, 0, 0));
				}

				scholInfoPanel.setVisible(true);
				scholHomePanel.setVisible(false);
				scholInfoPanel.revalidate();
			}
		});
		btnNewButton_1.setBackground(new Color(0, 153, 204));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(89, 466, 263, 47);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
		scholHomePanel.add(btnNewButton_1);

		JButton btnAddScholarship = new JButton("ADD SCHOLARSHIP");
		btnAddScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblfieldNotFilledincorrect.setVisible(false);
				scholHomePanel.setVisible(false);
				newScholPanel.setVisible(true);
			}
		});
		btnAddScholarship.setBackground(new Color(0, 153, 204));
		btnAddScholarship.setForeground(Color.WHITE);
		btnAddScholarship.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddScholarship.setBounds(399, 466, 263, 47);
		btnAddScholarship.setOpaque(true);
		btnAddScholarship.setBorderPainted(false);
		scholHomePanel.add(btnAddScholarship);

		btnDeleteScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// On first button click, check for confirmation
				if (btnDeleteScholarship.getText() == "DELETE SCHOLARSHIP") {
					lblConfirmation.setText("Delete this scholarship? Press again to confirm.");
					lblConfirmation.setVisible(true);
					btnDeleteScholarship.setText("CONFIRM DELETION");
				}

				// On second button click, confirmation of deletion
				else {

					// Get the scholarship to be deleted
					String title = allScholarshipList.getSelectedValue().toString();
					currentScholarship = scholarshipManager.getScholarship(title);

					// Check to see if there are any applicants for the scholarship
					// If the scholarship does not have applicants, it can be deleted
					if (currentScholarship.getApplicants().size() == 0) {

						// Get scholarship information and remove the scholarship, reset Jlist list
						// display
						int scholarshipIndex = allScholarshipList.getSelectedIndex();
						currentUser.removeScholarship(scholarshipTitles.get(scholarshipIndex));
						scholarshipModel.removeElementAt(scholarshipIndex);
						scholarshipTitles = scholarshipManager.getTitles();
						allScholarshipList.setListData(scholarshipTitles.toArray());

						// Reset selection for JList
						lblConfirmation.setVisible(false);
						btnDeleteScholarship.setText("DELETE SCHOLARSHIP");
						int prev = scholarshipIndex - 1;
						System.out.println("The selected index is " + scholarshipIndex);
						if (scholarshipIndex == 0) {
							allScholarshipList.setSelectedIndex(0);
						} else {
							allScholarshipList.setSelectedIndex(prev);
						}

						allScholarshipList.revalidate();
						allScholarshipList.repaint();

					}
					// The scholarship has applicants, cannot be deleted, set error message
					else {
						lblConfirmation.setText("This scholarship cannot be deleted since it already has applicants.");

					}

				}

			}
		});
		btnDeleteScholarship.setBackground(new Color(51, 153, 204));
		btnDeleteScholarship.setForeground(Color.WHITE);
		btnDeleteScholarship.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDeleteScholarship.setBounds(246, 524, 263, 47);
		btnDeleteScholarship.setOpaque(true);
		btnDeleteScholarship.setBorderPainted(false);
		scholHomePanel.add(btnDeleteScholarship);

	}

	/**
	 * 
	 * Creates all the components/panels needed for the scholarship screen view in
	 * the sidebar. All components are added to the scholarship screen.
	 * 
	 */
	public void createScholarshipScreen() {
		scholarshipScreen.setBackground(Color.WHITE);
		scholarshipScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);
		scholarshipScreen.setLayout(null);

		createApplicationView();
		scholarshipScreen.add(applicationViewPanel);

		distributeScholarship();
		scholarshipScreen.add(distributeScholarshipsPanel);

		createScholarshipApplicants();
		scholarshipScreen.add(scholApplicantsPanel);

		createScholarshipHome();
		scholarshipScreen.add(scholHomePanel);

		createScholarshipInfo();
		scholarshipScreen.add(scholInfoPanel);

		createNewScholarship();
		scholarshipScreen.add(newScholPanel);

	}

	/**
	 * 
	 * Creates all components for student screen. Includes viewing list of students,
	 * sorting students in system by different criteria as well as viewing each
	 * profile and their list of applications and the status/priority of each
	 * application.
	 * 
	 */
	public void createStudentsScreen() {
		studentsScreen.setBackground(Color.WHITE);
		studentsScreen.setBounds(sideBarWidth, 0, activityScreenWidth, MainFrame.heightSize);

		studentsScreen.setLayout(null);

		studentProfilePanel = new JPanel();
		studentProfilePanel.setBackground(new Color(255, 255, 255));
		studentProfilePanel.setBounds(0, 0, 782, 615);
		studentsScreen.add(studentProfilePanel);
		studentProfilePanel.setLayout(null);
		studentProfilePanel.setVisible(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		tabbedPane.setBounds(0, 87, 782, 528);
		studentProfilePanel.add(tabbedPane);
		tabbedPane.setForeground(new Color(0, 102, 153));
		tabbedPane.setBackground(new Color(255, 255, 255));

		JPanel profileTabPanel = new JPanel();
		tabbedPane.addTab("Profile", null, profileTabPanel, null);
		profileTabPanel.setBackground(new Color(240, 248, 255));
		GridBagLayout gbl_profileTabPanel = new GridBagLayout();
		gbl_profileTabPanel.columnWidths = new int[] { 58, 383, 388, 0 };
		gbl_profileTabPanel.rowHeights = new int[] { 1, 153, 0, 0, 0, 58, 0, 52, 0, 56, 0, 58, 53, 0 };
		gbl_profileTabPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_profileTabPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		profileTabPanel.setLayout(gbl_profileTabPanel);

		stuScreenName = new JLabel("Jane Doe");
		GridBagConstraints gbc_stuScreenName = new GridBagConstraints();
		gbc_stuScreenName.fill = GridBagConstraints.BOTH;
		gbc_stuScreenName.insets = new Insets(0, 0, 5, 5);
		gbc_stuScreenName.gridx = 1;
		gbc_stuScreenName.gridy = 1;
		profileTabPanel.add(stuScreenName, gbc_stuScreenName);
		stuScreenName.setHorizontalAlignment(SwingConstants.LEFT);
		stuScreenName.setFont(new Font("Dialog", Font.BOLD, 31));

		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Calibri", Font.BOLD, 25));
		GridBagConstraints gbc_lblUserId = new GridBagConstraints();
		gbc_lblUserId.fill = GridBagConstraints.BOTH;
		gbc_lblUserId.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId.gridx = 1;
		gbc_lblUserId.gridy = 3;
		profileTabPanel.add(lblUserId, gbc_lblUserId);

		stuScreenID = new JLabel("New label");
		stuScreenID.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_stuScreenID = new GridBagConstraints();
		gbc_stuScreenID.fill = GridBagConstraints.BOTH;
		gbc_stuScreenID.insets = new Insets(0, 0, 5, 0);
		gbc_stuScreenID.gridx = 2;
		gbc_stuScreenID.gridy = 3;
		profileTabPanel.add(stuScreenID, gbc_stuScreenID);

		JLabel label = new JLabel("Year:");
		label.setFont(new Font("Calibri", Font.BOLD, 25));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 5;
		profileTabPanel.add(label, gbc_label);

		stuScreenYear = new JLabel("New label");
		stuScreenYear.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_stuScreenYear = new GridBagConstraints();
		gbc_stuScreenYear.fill = GridBagConstraints.BOTH;
		gbc_stuScreenYear.insets = new Insets(0, 0, 5, 0);
		gbc_stuScreenYear.gridx = 2;
		gbc_stuScreenYear.gridy = 5;
		profileTabPanel.add(stuScreenYear, gbc_stuScreenYear);

		JLabel label_2 = new JLabel("Faculty:");
		label_2.setFont(new Font("Calibri", Font.BOLD, 25));
		label_2.setAlignmentY(1.0f);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 7;
		profileTabPanel.add(label_2, gbc_label_2);

		stuScreenFaculty = new JLabel("New label");
		stuScreenFaculty.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_stuScreenFaculty = new GridBagConstraints();
		gbc_stuScreenFaculty.fill = GridBagConstraints.BOTH;
		gbc_stuScreenFaculty.insets = new Insets(0, 0, 5, 0);
		gbc_stuScreenFaculty.gridx = 2;
		gbc_stuScreenFaculty.gridy = 7;
		profileTabPanel.add(stuScreenFaculty, gbc_stuScreenFaculty);

		JLabel label_3 = new JLabel("Level:");
		label_3.setFont(new Font("Calibri", Font.BOLD, 25));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 9;
		profileTabPanel.add(label_3, gbc_label_3);

		stuScreenLevel = new JLabel("New label");
		stuScreenLevel.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_stuScreenLevel = new GridBagConstraints();
		gbc_stuScreenLevel.fill = GridBagConstraints.BOTH;
		gbc_stuScreenLevel.insets = new Insets(0, 0, 5, 0);
		gbc_stuScreenLevel.gridx = 2;
		gbc_stuScreenLevel.gridy = 9;
		profileTabPanel.add(stuScreenLevel, gbc_stuScreenLevel);

		JLabel stuScreenGPAlbl = new JLabel("GPA:");
		stuScreenGPAlbl.setFont(new Font("Calibri", Font.BOLD, 25));
		GridBagConstraints gbc_stuScreenGPAlbl = new GridBagConstraints();
		gbc_stuScreenGPAlbl.fill = GridBagConstraints.BOTH;
		gbc_stuScreenGPAlbl.insets = new Insets(0, 0, 5, 5);
		gbc_stuScreenGPAlbl.gridx = 1;
		gbc_stuScreenGPAlbl.gridy = 11;
		profileTabPanel.add(stuScreenGPAlbl, gbc_stuScreenGPAlbl);

		stuScreenGPA = new JLabel("New label");
		stuScreenGPA.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_stuScreenGPA = new GridBagConstraints();
		gbc_stuScreenGPA.fill = GridBagConstraints.BOTH;
		gbc_stuScreenGPA.insets = new Insets(0, 0, 5, 0);
		gbc_stuScreenGPA.gridx = 2;
		gbc_stuScreenGPA.gridy = 11;
		profileTabPanel.add(stuScreenGPA, gbc_stuScreenGPA);

		JPanel ScholarshipApplicationsTab = new JPanel();
		ScholarshipApplicationsTab.setBackground(new Color(240, 248, 255));
		ScholarshipApplicationsTab.setForeground(new Color(240, 248, 255));
		tabbedPane.addTab("Scholarship Applications", null, ScholarshipApplicationsTab, null);
		ScholarshipApplicationsTab.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 51)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(66, 247, 661, 197);
		ScholarshipApplicationsTab.add(panel_1);
		panel_1.setLayout(null);

		JButton btnViewApplication_1 = new JButton("View Application ");
		btnViewApplication_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Student profile view

				// Set buttons view to false
				btnViewProfile.setVisible(false);
				lblpressAgainTo.setVisible(false);

				// Get current student's application
				currentApplication = currentUser.getApplication(currentScholarshipTitle, currentStudent);

				// Check to see if Reward/Reject buttons should be displayed
				if ((currentApplication.getStatus().str).equals("Approved")
						|| (currentApplication.getStatus().str).equals("Rejected")
						|| (currentApplication.getStatus().str).equals("Accepted")
						|| (currentApplication.getStatus().str).equals("Declined")) {

					btnAward.setVisible(false);
					btnReject.setVisible(false);

				}

				else {
					btnAward.setVisible(true);
					btnReject.setVisible(true);

				}

				// Set the application info for display
				lblStatusApp.setText(currentApplication.getStatus().str);
				currentApplication = currentUser.getApplication(currentScholarshipTitle, currentStudent);
				studentNameApp.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
				lblIDApp.setText(currentStudent.getUserID() + "");
				lblAgeApp.setText(currentApplication.getField("Age"));
				lblGPAApp.setText(currentApplication.getField("GPA"));
				lblYearApp.setText(currentStudent.getStudentData().getYear() + "");
				lblFacultyApp.setText(currentStudent.getFaculty().str);
				lblLevelApp.setText(currentStudent.getLevel().str);
				lblGenderApp.setText(currentApplication.getField("Gender"));
				lblMajorApp.setText(currentApplication.getField("Major"));
				lblCitizenApp.setText(currentApplication.getField("Citizen"));
				lblContactApp.setText(currentApplication.getField("Contact"));
				lblExtraCurricApp.setText(currentApplication.getField("ExtraCuric"));
				lblIncomeApp.setText(currentApplication.getField("Income"));
				lblPriorityApp.setText(currentStudent.getStudentData().getPriority(currentScholarshipTitle) + "");

				// Switch screens and views
				onStudentsScreenView = true;
				switchAP(scholarshipScreen);
				scholApplicantsPanel.setVisible(false);
				scholHomePanel.setVisible(false);
				scholInfoPanel.setVisible(false);
				newScholPanel.setVisible(false);
				applicationViewPanel.setVisible(true);
				distributeScholarshipsPanel.setVisible(false);
				studentProfilePanel.setVisible(false);
				applicationViewPanel.repaint();
				applicationViewPanel.revalidate();

			}
		});
		btnViewApplication_1.setBounds(400, 91, 214, 41);
		btnViewApplication_1.setForeground(Color.WHITE);
		btnViewApplication_1.setFont(new Font("Calibri", Font.BOLD, 23));
		btnViewApplication_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnViewApplication_1.setBackground(new Color(51, 204, 204));
		panel_1.add(btnViewApplication_1);

		JLabel scholAppProfileTitle = new JLabel("Scholarship Title");
		scholAppProfileTitle.setFont(new Font("Calibri", Font.BOLD, 26));
		scholAppProfileTitle.setBounds(24, 34, 451, 41);
		panel_1.add(scholAppProfileTitle);

		JLabel lblStatus_1 = new JLabel("Status:");
		lblStatus_1.setFont(new Font("Calibri", Font.BOLD, 23));
		lblStatus_1.setBounds(24, 77, 73, 41);
		panel_1.add(lblStatus_1);

		JLabel lblPriority_1 = new JLabel("Priority:");
		lblPriority_1.setFont(new Font("Calibri", Font.BOLD, 23));
		lblPriority_1.setBounds(24, 118, 85, 29);
		panel_1.add(lblPriority_1);

		JLabel statusChange = new JLabel("");
		statusChange.setFont(new Font("Calibri", Font.PLAIN, 23));
		statusChange.setBounds(107, 80, 283, 35);
		panel_1.add(statusChange);

		JLabel priorityChange = new JLabel("");
		priorityChange.setFont(new Font("Calibri", Font.PLAIN, 23));
		priorityChange.setBounds(117, 118, 157, 29);
		panel_1.add(priorityChange);

		studentApplicationsList = new JList();
		studentApplicationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				// If the application list is not empty, get its info
				if (!(studentApplicationsList.isSelectionEmpty())) {
					currentScholarshipTitle = studentApplicationsList.getSelectedValue().toString();
					scholAppProfileTitle.setText(currentScholarshipTitle);
					statusChange.setText(
							currentUser.getApplication(currentScholarshipTitle, currentStudent).getStatus().str);
					priorityChange.setText(currentStudent.getStudentData().getPriority(currentScholarshipTitle) + "");
				}

			}
		});
		studentApplicationsList.setBorder(new LineBorder(new Color(0, 0, 0)));

		studentApplicationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentApplicationsList.setBounds(10, 11, 742, 497);
		studentApplicationsList.setFont(new Font("Dialog", Font.PLAIN, 25));
		studentApplicationsList.setBackground(new Color(255, 255, 255));
		studentApplicationsList.setListData(scholarshipList.toArray());

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) studentApplicationsList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		applicationsScroll = new JScrollPane();
		applicationsScroll.setBounds(66, 23, 661, 197);
		applicationsScroll.setViewportView(studentApplicationsList);
		ScholarshipApplicationsTab.add(applicationsScroll);

		studentsBorder = new JPanel();
		studentsBorder.setBackground(new Color(255, 255, 255));
		studentsBorder.setBounds(0, 0, 782, 615);
		studentsBorder.setLayout(null);
		studentsScreen.add(studentsBorder);

		JButton stuBackBtn = new JButton("Back");
		stuBackBtn.setForeground(new Color(0, 0, 0));
		stuBackBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		stuBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If on student screen switch, back button goes to student profile
				if (onStudentsScreenView == true) {
					studentsBorder.setVisible(true);
					studentProfilePanel.setVisible(false);
				}

				// Else, on scholarship screen, back button goes to application info
				else {
					switchAP(scholarshipScreen);
					scholApplicantsPanel.setVisible(false);
					scholHomePanel.setVisible(false);
					scholInfoPanel.setVisible(false);
					newScholPanel.setVisible(false);
					applicationViewPanel.setVisible(true);

				}

			}
		});
		stuBackBtn.setFont(new Font("Tahoma", Font.BOLD, 22));
		stuBackBtn.setBackground(new Color(255, 255, 255));
		stuBackBtn.setBounds(644, 56, 117, 43);
		studentProfilePanel.add(stuBackBtn);

		studentsList = new JList();
		studentsList.setFont(new Font("Tahoma", Font.PLAIN, 25));

		studentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentsList.setBorder(new LineBorder(new Color(0, 0, 0)));
		studentsList.setBackground(new Color(255, 255, 255));
		studentsList.setBounds(10, 11, 742, 497);
		studentsList.setSelectedIndex(0);
		studentsList.setListData(studentManager.getListNames().toArray());
		studentsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				// If value changed, get the index of the selection
				studentIndex = studentsList.getSelectedIndex();

			}
		});

		renderer = (DefaultListCellRenderer) studentsList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPaneStudents = new JScrollPane();
		scrollPaneStudents.setBounds(27, 165, 731, 347);
		scrollPaneStudents.setViewportView(studentsList);
		studentsBorder.add(scrollPaneStudents);

		JButton btnGranted = new JButton("ACCEPTED SCHOLARSHIP");
		btnGranted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Sort list for students that have accepted scholarships
				studentSortList = studentManager.sortCategory(Enumerations.sort.GRANTEDSCHOLARSHIP);
				studentsList.setListData(studentSortList.toArray());
			}
		});
		btnGranted.setForeground(new Color(0, 0, 0));
		btnGranted.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnGranted.setBackground(new Color(255, 255, 255));
		btnGranted.setFont(new Font("Helvetica", Font.BOLD, 16));
		btnGranted.setBounds(124, 87, 237, 51);
		btnGranted.setOpaque(true);
		studentsBorder.add(btnGranted);

		JButton btnNoApp = new JButton("NO APPLICATIONS");
		btnNoApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Sort list for students who have no applications
				studentSortList = studentManager.sortCategory(Enumerations.sort.NOAPPLICATION);
				studentsList.setListData(studentSortList.toArray());
			}
		});
		btnNoApp.setBackground(new Color(255, 255, 255));
		btnNoApp.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNoApp.setForeground(new Color(0, 0, 0));
		btnNoApp.setFont(new Font("Helvetica", Font.BOLD, 16));
		btnNoApp.setOpaque(true);
		btnNoApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNoApp.setBounds(561, 87, 197, 51);
		studentsBorder.add(btnNoApp);

		JButton btnAppliedOnly = new JButton("APPLIED ONLY");
		btnAppliedOnly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Sort list for students who have only applied
				studentSortList = studentManager.sortCategory(Enumerations.sort.APPLIEDONLY);
				studentsList.setListData(studentSortList.toArray());

			}
		});
		btnAppliedOnly.setBackground(new Color(255, 255, 255));
		btnAppliedOnly.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAppliedOnly.setForeground(new Color(0, 0, 0));
		btnAppliedOnly.setFont(new Font("Helvetica", Font.BOLD, 16));
		btnAppliedOnly.setOpaque(true);

		btnAppliedOnly.setBounds(371, 87, 180, 51);

		studentsBorder.add(btnAppliedOnly);

		JLabel lblNewLabel_4 = new JLabel("Current Students");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel_4.setBounds(287, 31, 188, 29);
		studentsBorder.add(lblNewLabel_4);

		JButton btnViewProfile_1 = new JButton("View Profile");
		btnViewProfile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Get the student ID from the JList (string)
				String listselect = studentsList.getSelectedValue().toString() + "";
				String[] parts = listselect.split(", ");
				int id = Integer.parseInt(parts[1]);

				// Set the current student by searching by ID
				currentStudent = studentManager.getStudent(id);
				onStudentsScreenView = true;

				// Set scholarship info
				scholAppProfileTitle.setText(currentScholarshipTitle);

				// Set applications list
				studentApplicationsList.setListData(currentStudent.getScholarshipAppliedTitles().toArray());

				// Set profile information on profile screen
				stuScreenName.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
				stuScreenID.setText("" + currentStudent.getUserID());
				stuScreenYear.setText("" + currentStudent.getStudentData().getYear());
				stuScreenFaculty.setText("" + currentStudent.getFaculty());
				stuScreenLevel.setText("" + currentStudent.getLevel());
				stuScreenGPA.setText("" + currentStudent.getGPA());

				studentsBorder.setVisible(false);
				studentProfilePanel.setVisible(true);

			}
		});

		btnViewProfile_1.setForeground(Color.BLACK);
		btnViewProfile_1.setFont(new Font("Helvetica", Font.BOLD, 18));
		btnViewProfile_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnViewProfile_1.setBackground(Color.WHITE);
		btnViewProfile_1.setBounds(295, 539, 180, 42);
		btnViewProfile_1.setOpaque(true);
		studentsBorder.add(btnViewProfile_1);

		JButton btnAll = new JButton("ALL");
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Set list to show all students in the system
				studentSortList.clear();
				studentSortList = studentManager.getListNames();
				studentsList.setListData(studentSortList.toArray());
			}
		});
		btnAll.setForeground(Color.BLACK);
		btnAll.setFont(new Font("Helvetica", Font.BOLD, 16));
		btnAll.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAll.setBackground(Color.WHITE);
		btnAll.setBounds(27, 87, 87, 51);
		btnAll.setOpaque(true);
		studentsBorder.add(btnAll);

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

		JLabel notifInfo = new JLabel("Description of notifications. eg. reward accepted by student x");
		notifInfo.setBounds(21, 105, 635, 60);
		notifInfo.setAlignmentY(Component.TOP_ALIGNMENT);
		notifInfo.setFont(new Font("Calibri", Font.PLAIN, 18));
		notificationsInfo.add(notifInfo);
		notificationsInfo.add(notifTitle);

		ArrayList<Notification> notifObject = notificationManager.getNotifications(123);

		notificationsList = new JList();
		notificationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (notificationsList.getSelectedIndex() < 0) {
					notifTitle.setText("NOTIFICATIONS TITLE");
					notifInfo.setText("Description of notifications. eg. reward accepted by student x");

				} else {
					int notifIndex = notificationsList.getSelectedIndex();
					notifTitle.setText(notifObject.get(notifIndex).getTitle());
					notifInfo.setText(notifObject.get(notifIndex).getMessage());

				}

			}
		});
		notificationsList.setFont(new Font("Tahoma", Font.PLAIN, 25));

		notificationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		notificationsList.setBorder(new LineBorder(new Color(147, 112, 219)));
		notificationsList.setBackground(new Color(230, 230, 250));
		notificationsList.setBounds(10, 11, 742, 497);
		notificationsList.setSelectedIndex(0);

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
