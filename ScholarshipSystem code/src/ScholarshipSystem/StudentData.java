package ScholarshipSystem;

/**
 * StudentData object that holds all the additional data for student object
 * 
 * @author Jessie Cai, Shaina Rosell 
 * @version final (4/12/19) 
 */

import java.io.Serializable;
import java.util.ArrayList;

public class StudentData implements Serializable {

	private double gpa;
	private Enumerations.level level;
	private int year;
	private Enumerations.faculty faculty;
	private int numberOfScholarshipsAccepted;
	private int numberOfScholarshipsAcceptedLimit = 1;

	/**
	 * Stores an array of applications the user has applied to
	 */
	private ArrayList<String> applicationTitles = new ArrayList<>();

	/**
	 * Base constructor that stores the basic information of a student
	 * 
	 * @param gpa
	 * @param level
	 * @param year
	 * @param scholarshipApplied
	 * @param faculty
	 */
	public StudentData(double gpa, Enumerations.level level, int year,
			ArrayList<ScholarshipApplication> scholarshipApplied, Enumerations.faculty faculty) {
		this.gpa = gpa;
		this.level = level;
		this.year = year;
		this.faculty = faculty;

	}

	/**
	 * Copy constructor that creates a copy of studentData for an input studentData
	 * 
	 * @param copy
	 */
	public StudentData(StudentData copy) {
		this.gpa = copy.getGPA();
		this.level = copy.getLevel();
		this.year = copy.getYear();
		this.faculty = copy.getFaculty();

	}

	/**
	 * Add an application title to the application array list
	 * 
	 * @param applicationTitle
	 */
	public void addScholarshipAppliedTitle(String applicationTitle) {
		applicationTitles.add(applicationTitle);

	}

	/**
	 * Gets the list of scholarship applications
	 * 
	 * @return ArrayList<sString> applicationTitles
	 */
	public ArrayList<String> getScholarshipAppliedTitles() {
		return applicationTitles;
	}

	/**
	 * Reorders the arraylist of scholarship applications by priority
	 * 
	 * @param scholarshipTitle
	 */
	public void fixPriority(String scholarshipTitle) {
		int applicationPosition = this.applicationTitles.indexOf(scholarshipTitle);
		this.applicationTitles.remove(applicationPosition);
		this.applicationTitles.add(0, scholarshipTitle);

	}

	/**
	 * Get the priority of a given scholarship application
	 * 
	 * @param scholarshipTitle
	 * @return
	 */
	public int getPriority(String scholarshipTitle) {
		return applicationTitles.indexOf(scholarshipTitle) + 1;

	}

	/**
	 * Get the number of applications that the student has currently sent out
	 * 
	 * @return int numberOfScholarshipsAccepted
	 */
	public int getNumberOfScholarshipsAccepted() {
		return numberOfScholarshipsAccepted;
	}

	/**
	 * Return the max number of scholarships a student may have open at a time
	 * 
	 * @return int numberofScholarshipsAcceptedLimit
	 */
	public int getNumberOfScholarshipsAcceptedLimit() {
		return numberOfScholarshipsAcceptedLimit;
	}

	/**
	 * Returns the GPA stored
	 * 
	 * @return int GPA
	 */
	public double getGPA() {
		return gpa;
	}

	/**
	 * Returns the level stored
	 * 
	 * @return Enumerations.level level
	 */
	public Enumerations.level getLevel() {
		return level;
	}

	/**
	 * Returns the faculty stored
	 * 
	 * @return Enumerations.faculty faculty
	 */
	public Enumerations.faculty getFaculty() {
		return faculty;
	}

	/**
	 * Returns the year stored
	 * 
	 * @return int year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the number of scholarships accepted variable
	 * 
	 * @param numberOfScholarshipsAccepted
	 */
	public void setNumberOfScholarshipsAccepted(int numberOfScholarshipsAccepted) {
		this.numberOfScholarshipsAccepted = numberOfScholarshipsAccepted;
	}

	/**
	 * Set the GPA of a student
	 * 
	 * @param val
	 */
	public void setGPA(double val) {
		if (0.00 <= val && val <= 4.00) {
			gpa = val;
		}
	}

	/**
	 * Setter for the level variable
	 * 
	 * @param level
	 */
	public void setLevel(Enumerations.level level) {
		this.level = level;

	}

	/**
	 * Setter for the year variable
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;

	}

	/**
	 * Setter for the faculty variable
	 * 
	 * @param faculty
	 */
	public void setFaculty(Enumerations.faculty faculty) {
		this.faculty = faculty;

	}

	/**
	 * Removes a scholarship application from the list
	 * 
	 * @param schol
	 */
	public void removeScholarshipApplication(String schol) {
		// scholarshipApplied.remove(schol);
		applicationTitles.remove(schol);

	}

}
