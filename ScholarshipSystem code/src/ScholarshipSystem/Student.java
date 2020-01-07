package ScholarshipSystem;

/**
 * Module for implementing the Student object methods and access privileges.
 * Will retain an instance of studentData which it interacts with to get information
 * 
 * @author Jessie Cai, Shaina Rosell 
 * @version final (4/12/19) 
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {

	private StudentData studentData;

	/**
	 * Constructor for student, calls upon superconstructor User and stores
	 * information in studentData
	 * 
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param level
	 * @param faculty
	 * @param year
	 * @param gpa
	 * @param udb
	 */

	public Student(String firstName, String lastName, String password, Enumerations.level level,
			Enumerations.faculty faculty, int year, double gpa, UserDatabase udb) {
		super(firstName, lastName, password, udb);
		studentData = new StudentData(gpa, level, year, null, faculty);
		// TODO Auto-generated constructor stub

	}

	/**
	 * Getter to return the Student's studentData object
	 * 
	 * @return StudentData studentData
	 */
	public StudentData getStudentData() {
		return studentData;
	}

	public Student() {
	}

	/**
	 * Verify that student is not over the limit of five scholarship applications by
	 * fetching from studentData
	 * 
	 * @return Boolean
	 */
	public boolean canApplyForScholarships() {

		boolean result;
		if (studentData.getNumberOfScholarshipsAccepted() < studentData.getNumberOfScholarshipsAcceptedLimit()) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * Polls data received and generates an application, then submits application to
	 * the scholarship manager. Returns a boolean if successful.
	 * 
	 * @param scManager
	 * @param stManager
	 * @param title
	 * @param age
	 * @param gender
	 * @param citizen
	 * @param major
	 * @param gpa
	 * @param contact
	 * @param extracurric
	 * @param income
	 * @return Boolean
	 */
	public boolean createAndSubmitApplication(ScholarshipManager scManager, StudentManager stManager, String title,
			String age, String gender, String citizen, String major, String gpa, String contact, String extracurric,
			String income) {

		ScholarshipApplication newApplication = new ScholarshipApplication(title, age, gender, citizen, major, gpa,
				contact, extracurric, income, this);
		boolean complete = newApplication.isComplete();

		if (complete) {

			studentData.addScholarshipAppliedTitle(title);
			boolean done = scManager.addApplication(title, this, newApplication);
			newApplication.updateStatus("SUBMITTED");
			System.out.println("Adding application to database was " + done);

			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * Poll data received and creates an application. Saves as a template for next
	 * time this is open.
	 * 
	 * @param scManager
	 * @param stManager
	 * @param title
	 * @param age
	 * @param gender
	 * @param citizen
	 * @param major
	 * @param gpa
	 * @param contact
	 * @param extracurric
	 * @param income
	 */

	public void createApplication(ScholarshipManager scManager, StudentManager stManager, String title, String age,
			String gender, String citizen, String major, String gpa, String contact, String extracurric,
			String income) {

		ScholarshipApplication newApplication = new ScholarshipApplication(title, age, gender, citizen, major, gpa,
				contact, extracurric, income, this);
		boolean complete = newApplication.isComplete();
		studentData.addScholarshipAppliedTitle(title);
		boolean done = scManager.addApplication(title, this, newApplication);
		System.out.println("Adding application to database was " + done);
		stManager.updateStudentInfo(this);
		scManager.serialize();

	}

	/**
	 * Getter that returns the application a student has submitted for a given
	 * scholarship
	 * 
	 * @param sc
	 * @param title
	 * @return Boolean
	 */
	public ScholarshipApplication getApplicationForStudent(ScholarshipManager sc, String title) {
		Scholarship s = sc.getScholarship(title);
		return s.getApplication(this.getUserID());
	}

	// Confirming changes for edits

	/**
	 * Updates the information of an application for a given scholarship
	 * 
	 * @param sc
	 * @param title
	 * @param age
	 * @param gender
	 * @param citizen
	 * @param major
	 * @param gpa
	 * @param contact
	 * @param extracurric
	 * @param income
	 * @return Boolean
	 */
	public boolean updateScholarshipEdits(ScholarshipManager sc, String title, String age, String gender,
			String citizen, String major, String gpa, String contact, String extracurric, String income) {

		sc.fillApplication(title, this.getUserID(), "Age", age);
		sc.fillApplication(title, this.getUserID(), "Gender", gender);
		sc.fillApplication(title, this.getUserID(), "Citizen", citizen);
		sc.fillApplication(title, this.getUserID(), "Major", major);
		sc.fillApplication(title, this.getUserID(), "GPA", gpa);
		sc.fillApplication(title, this.getUserID(), "Contact", contact);
		sc.fillApplication(title, this.getUserID(), "ExtraCuric", extracurric);
		sc.fillApplication(title, this.getUserID(), "Income", income);
		sc.serialize();

		ScholarshipApplication application = getApplicationForStudent(sc, title);
		if (application.isComplete()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Removes a scholarship application from a student
	 * 
	 * @param schol
	 */

	public void removeScholarshipApplication(String schol) {
		studentData.removeScholarshipApplication(schol);
	}

	/**
	 * Orders the priorities of a student's applications for a given scholarship
	 * 
	 * @param scholarshipTitle
	 */
	public void prioritizeScholarshipsApplied(String scholarshipTitle) {
		studentData.fixPriority(scholarshipTitle);

	}

	/**
	 * Getter that returns the list of scholarships a student has applied for
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getScholarshipAppliedTitles() {
		return studentData.getScholarshipAppliedTitles();
	}

	/**
	 * Returns Level of student by getting Enumerations.level from studentData
	 * 
	 * @return Enumerations.level
	 */
	public Enumerations.level getLevel() {
		return studentData.getLevel();
	}

	/**
	 * Returns GPA of a student by getting an int from studentData
	 * 
	 * @return int GPA
	 */
	public double getGPA() {
		return studentData.getGPA();
	}

	/**
	 * Returns the faculty the student is a member of by getting
	 * Enumerations.faculty from studentData
	 * 
	 * @return Enumerations.faculty faculty
	 */
	public Enumerations.faculty getFaculty() {
		return studentData.getFaculty();
	}

	/**
	 * Returns the year the student is currently in as an integer
	 * 
	 * @return int year
	 */
	public int getYear() {
		return studentData.getYear();
	}

}
