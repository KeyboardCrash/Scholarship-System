package ScholarshipSystem;

import java.io.Serializable;

/**
 * Class implementing the admin object
 * 
 * @author Jessie Cai, Shaina Rosell
 * @version final (4/12/19)
 * 
 */

public class Admin extends User implements Serializable {

	private ScholarshipManager scholarshipManager;

	/**
	 * Constructor for admin calls the superconstructor for User
	 * 
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param udb
	 */
	public Admin(String firstName, String lastName, String password, UserDatabase udb) {
		super(firstName, lastName, password, udb);
		// TODO Auto-generated constructor stub
	}

	/**
	 * add scholarship to the database
	 *
	 * @param scholarship
	 */
	public void addScholarshipGUI(String title, String donor, Enumerations.faculty faculty, double amount,
			int awardLimit, int deadlineDay, int deadlineMonth, int deadlineYear, String baseAge, String baseGPA,
			String baseLevel, String baseYear, String major, String baseGender, String baseIncome, String baseCitizen,
			String baseExtraCurric) {
		scholarshipManager.addScholarshipGUI(title, donor, faculty, amount, awardLimit, deadlineDay, deadlineMonth,
				deadlineYear, baseAge, baseGPA, baseLevel, baseYear, major, baseGender, baseIncome, baseCitizen,
				baseExtraCurric);
	}

	public void setScholarshipManager(ScholarshipManager scholarshipManager) {
		this.scholarshipManager = scholarshipManager;
	}

	/**
	 * reward student with scholarship
	 *
	 * @param student
	 *            Student to be rewarded
	 * @param scholarship
	 *            scholarship to reward student
	 */

	public boolean rewardScholarshipAdmin(String scholarship, Student student, NotificationManager nm) {

		if (scholarshipManager.getScholarship(scholarship).getCanbeAwarded()) {
			// Decrements to let system know scholarship has been distributed
			scholarshipManager.getScholarship(scholarship)
					.setAwardLimit(scholarshipManager.getScholarship(scholarship).getAwardLimit() - 1);
			scholarshipManager.getScholarship(scholarship).getApplication(student.getUserID())
					.setStatus(Enumerations.status.APPROVED);
			scholarshipManager.serialize();

			if (scholarshipManager.getScholarship(scholarship).getAwardLimit() == 0) {
				this.closeScholarship(scholarship);
			}

			Notification notification = new Notification("Rewarded " + scholarship + " " + student.getUserID(),
					"You fufilled the requirements for the scholarship " + scholarship + " and have been accepted. ");
			nm.sendNotification(student.getUserID(), notification);

			// nm.printOut();

			return true;

		}

		else {
			return false;
		}

	}

	/**
	 * Reject student application for scholarship
	 * 
	 * @param scholarship
	 * @param student
	 * @param nm
	 */
	public void rejectScholarshipAdmin(String scholarship, Student student, NotificationManager nm) {
		scholarshipManager.getScholarship(scholarship).getApplication(student.getUserID())
				.setStatus(Enumerations.status.REJECTED);

		Notification notification = new Notification("Rejected " + scholarship + " " + student.getUserID(),
				"You did not fufill the requirements for the scholarship " + scholarship + " and have been rejected. ");
		nm.sendNotification(student.getUserID(), notification);
		// nm.printOut();

		scholarshipManager.serialize();

	}

	/**
	 * Open scholarship for applications from students
	 * 
	 * @param title
	 */
	public void openScholarship(String title) {
		scholarshipManager.getScholarship(title).openApplication();
		scholarshipManager.serialize();
	}

	/**
	 * Close scholarship for applications from students
	 * 
	 * @param title
	 */
	public void closeScholarship(String title) {
		scholarshipManager.getScholarship(title).closeApplication();
		scholarshipManager.serialize();

	}

	/**
	 * make changes to the scholarship
	 *
	 * @param scholarshipTitle
	 *            title Scholarship to edit
	 * @param field
	 *            field Element of scholarship to edit (must be one of Donor, Title,
	 *            Amount, Faculty, Date Month, Date Day, Date Year, Award Limit)
	 *            type sensitive
	 * @param changes
	 *            Things to change that field to
	 */
	public void editScholarship(String scholarshipTitle, String field, String changes) {
		scholarshipManager.editScholarship(scholarshipTitle, field, changes);
	}

	/**
	 * removes scholarship from the scholarship database
	 *
	 * Scholarship Manager
	 * 
	 * @param scholarshipTitle
	 */
	public void removeScholarship(String scholarshipTitle) {
		scholarshipManager.removeScholarship(scholarshipTitle);
	}

	/**
	 * change the status of the scholarship
	 *
	 * @param sc
	 *            ScholarshipManager
	 * @param student
	 *            student to change the status of
	 * @param scholarshipTitle
	 *            scholarship associated with the student
	 * @param status
	 *            status to be changed to (Enumeration.status)
	 */
	public void changeApplicationStatus(Student student, String scholarshipTitle, Enumerations.status status) {
		scholarshipManager.getScholarship(scholarshipTitle).getApplication(student.getUserID()).setStatus(status);
	}

	/**
	 *
	 * @param sc
	 *            scholarship manager
	 * @param student
	 * @param scholarshipTitle
	 * @return
	 */
	public ScholarshipApplication getApplication(String currentScholarshipTitle, Student currentStudent) {
		return scholarshipManager.getApplication(currentScholarshipTitle, currentStudent);
	}

	/**
	 *
	 * @param scholarship
	 * @param requirement
	 */
	public void addRequirement(Scholarship scholarship, String requirement) {
		(scholarship.getRequirements()).add(requirement);
	}

	/**
	 *
	 * @param scholarship
	 * @param month
	 * @param day
	 * @param year
	 */
	public void setDeadline(Scholarship scholarship, int month, int day, int year) {
		scholarship.setDeadlineMonth(month);
		scholarship.setDeadlineDay(day);
		scholarship.setDeadlineYear(year);
	}
}
