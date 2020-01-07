package ScholarshipSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * A class representing a scholarship in the system
 *
 * @author Jessie Cai, Colin Au Yeung
 * @version final (4/12/19)
 */
public class Scholarship implements Comparable, Serializable {

	private String title = "";
	private String donor = "";
	private Enumerations.faculty faculty;
	private HashMap<String, String> requirements = new HashMap<>();
	private int numRequirements = requirements.size();
	private ArrayList<ScholarshipApplication> applications = new ArrayList<>();
	private double amount;
	private int awardLimit;
	private int deadlineDay = 0;
	private int deadlineMonth = 0;
	private int deadlineYear = 0;
	private boolean canBeAwarded;
	private boolean applicationOpen;

	/**
	 * Constructor with requirements and applications passed in
	 * 
	 * @param title
	 *            title of the scholarship
	 * @param donor
	 *            donar
	 * @param faculty
	 *            faculty of the scholarship
	 * @param requirements
	 *            requirements of the scholarship
	 * @param applications
	 *            any applications of the scholarship
	 * @param amount
	 *            the amount of the scholarship
	 * @param awardLimit
	 *            the awardlimit of the scholarship
	 * @param deadlineDay
	 *            the deadline's day of the scholarship
	 * @param deadlineMonth
	 *            he deadline's month of the scholarship
	 * @param deadlineYear
	 *            he deadline's year of the scholarship
	 */
	public Scholarship(String title, String donor, Enumerations.faculty faculty, ArrayList<String> requirements,
			ArrayList<ScholarshipApplication> applications, double amount, int awardLimit, int deadlineDay,
			int deadlineMonth, int deadlineYear) {

		this.title = title;
		this.donor = donor;
		this.faculty = faculty;
		this.requirements = new HashMap<>();
		for (String e : requirements) {
			this.requirements.put(e, "None");
		}
		this.applications = applications;
		this.amount = amount;
		this.awardLimit = awardLimit;
		this.deadlineDay = deadlineDay;
		this.deadlineMonth = deadlineMonth;
		this.deadlineYear = deadlineYear;

	}

	/**
	 * Base Constructor
	 * 
	 * @param title
	 *            Title of the scholarship
	 */
	public Scholarship(String title) {
		this.title = title;
	}

	/**
	 * Constructor without requirements and applications
	 * 
	 * @param title
	 *            title of the scholarship
	 * @param donor
	 *            donar
	 * @param faculty
	 *            faculty of the scholarship
	 * @param amount
	 *            the amount of the scholarship
	 * @param awardLimit
	 *            the awardlimit of the scholarship
	 * @param deadlineDay
	 *            the deadline's day of the scholarship
	 * @param deadlineMonth
	 *            he deadline's month of the scholarship
	 * @param deadlineYear
	 *            he deadline's year of the scholarship
	 */
	public Scholarship(String title, String donor, Enumerations.faculty faculty, double amount, int awardLimit,
			int deadlineDay, int deadlineMonth, int deadlineYear) {
		this.title = title;
		this.donor = donor;
		this.faculty = faculty;
		this.amount = amount;
		this.awardLimit = awardLimit;
		this.deadlineDay = deadlineDay;
		this.deadlineMonth = deadlineMonth;
		this.deadlineYear = deadlineYear;
		this.applicationOpen = false;
	}

	/**
	 * Constructor with Gui base requirements
	 * 
	 * @param title
	 *            title of the scholarship
	 * @param donor
	 *            donar
	 * @param faculty
	 *            faculty of the scholarship
	 * @param requirements
	 *            requirements of the scholarship
	 * @param amount
	 *            the amount of the scholarship
	 * @param awardLimit
	 *            the awardlimit of the scholarship
	 * @param deadlineDay
	 *            the deadline's day of the scholarship
	 * @param deadlineMonth
	 *            he deadline's month of the scholarship
	 * @param deadlineYear
	 *            he deadline's year of the scholarship
	 */
	public Scholarship(String title, String donor, Enumerations.faculty faculty, ArrayList<String> requirements,
			double amount, int awardLimit, int deadlineDay, int deadlineMonth, int deadlineYear) {
		this.title = title;
		this.donor = donor;
		this.faculty = faculty;
		this.amount = amount;
		this.awardLimit = awardLimit;
		this.deadlineDay = deadlineDay;
		this.deadlineMonth = deadlineMonth;
		this.deadlineYear = deadlineYear;
		this.requirements.put("Date of birth (mm/dd/yyyy)", "None");
		this.requirements.put("Citizenship", "None");
		this.requirements.put("Current GPA", "3.5");
		this.requirements.put("Extracurricular experience", "None");
		this.requirements.put("Other important info", "None");
		this.requirements.put("Gender", "None");
		this.requirements.put("Current Major", "None");
		this.requirements.put("Contact", "None");
		this.requirements.put("Annual income", "None");
		this.applicationOpen = true;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass().equals(Scholarship.class))
			return compareTo((Scholarship) o);
		else if (o.getClass().equals(String.class)) {
			return compareTo((String) o);
		}
		return 0;
	}

	/**
	 * Overload for compareTo
	 * 
	 * @param other
	 * @return
	 */
	public int compareTo(Scholarship other) {
		return this.title.compareTo(other.getTitle());
	}

	/**
	 * Overload for compareTO
	 * 
	 * @param title
	 * @return
	 */
	public int compareTo(String title) {
		return this.title.compareTo(title);
	}

	public ArrayList<String> getRequirements() {
		ArrayList<String> n = new ArrayList<>();
		n.addAll(requirements.keySet());
		return n;
	}

	public String dateFormat() {
		return deadlineMonth + "/" + deadlineDay + "/" + deadlineYear;
	}

	/**
	 * Gets Donar
	 * 
	 * @return Donar
	 */
	public String getDonor() {
		return donor;
	}

	/**
	 * Sets Dopar
	 * 
	 * @param donor
	 *            set value
	 */
	public void setDonor(String donor) {
		this.donor = donor;
	}

	/**
	 * Gets the faculty
	 * 
	 * @return the faculty
	 */
	public Enumerations.faculty getFaculty() {
		return faculty;
	}

	/**
	 * sets the faculty
	 * 
	 * @param faculty
	 *            the set value
	 */
	public void setFaculty(Enumerations.faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * gets the amount
	 * 
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * sets the amount
	 * 
	 * @param amount
	 *            the set value
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * gets the award limit
	 * 
	 * @return the award limit=
	 */
	public int getAwardLimit() {
		return awardLimit;
	}

	/**
	 * sets the award limit
	 * 
	 * @param awardLimit
	 *            the set value
	 */
	public void setAwardLimit(int awardLimit) {
		this.awardLimit = awardLimit;
	}

	/**
	 * Getss the deadline' day
	 * 
	 * @return the deadline's day
	 */
	public int getDeadlineDay() {
		return deadlineDay;
	}

	/**
	 * Set's the deadline's day
	 * 
	 * @param deadlineDay
	 *            set value
	 */
	public void setDeadlineDay(int deadlineDay) {
		this.deadlineDay = deadlineDay;
	}

	/**
	 * Getss the deadline's month
	 * 
	 * @return the deadline's month
	 */
	public int getDeadlineMonth() {
		return deadlineMonth;
	}

	/**
	 * Set's the deadline's month
	 * 
	 * @param deadlineMonth
	 *            set value
	 */
	public void setDeadlineMonth(int deadlineMonth) {
		this.deadlineMonth = deadlineMonth;
	}

	/**
	 * Getss the deadline's year
	 * 
	 * @return the deadline's year
	 */
	public int getDeadlineYear() {
		return deadlineYear;
	}

	/**
	 * Set's the deadline's year
	 * 
	 * @param deadlineYear
	 *            set value
	 */
	public void setDeadlineYear(int deadlineYear) {
		this.deadlineYear = deadlineYear;
	}

	/**
	 * Gets the title of the scholarship
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * 
	 * @param title
	 *            set value
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Changes a requirement's baseline
	 * 
	 * @param type
	 *            requirement to change
	 * @param field
	 *            new baseline
	 */
	public void editRequirement(String type, String field) {
		this.requirements.replace(type, field);

	}

	/**
	 * Returns whether it can be award to another student
	 * 
	 * @return whether it can be award to another student
	 */
	public boolean getCanbeAwarded() {

		if (awardLimit == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Adds a requirement to the scholarship
	 * 
	 * @param requirement
	 *            requirement to add
	 */
	public void addRequirement(String requirement, String baseline) {
		// if (applicationOpen == true) {
		requirements.put(requirement, baseline);
		numRequirements = requirements.size();
		// }
	}

	/**
	 * Removes a requirement in the scholarship
	 * 
	 * @param toRemove
	 *            requirement to remove
	 * @return whether act was performed successfully
	 */
	public boolean removeRequirement(String toRemove) {
		if (!applicationOpen) {
			boolean success = false;
			if (requirements.containsKey(toRemove)) {
				requirements.remove(toRemove);
				success = true;
			}
			numRequirements = requirements.size();
			return success;
		}
		return false;
	}

	/**
	 * Edits an application in commandline
	 * 
	 * @param id
	 *            student id of the applicant to edit
	 * @return whether application exists or not
	 */
	public boolean editApplicationPrint(int id) {

		Scanner scan = new Scanner(System.in);
		int found = search(id);
		if (found == -1) {
			return false;
		}
		for (String e : applications.get(found).getRequirements()) {

			String response = scan.nextLine();
			applications.get(found).fillField(e, response);
		}
		return true;
	}

	/**
	 * Gets all applicants to the scholarship
	 * 
	 * @return int array of ids
	 */
	public ArrayList<String> getApplicants() {
		ArrayList<String> applicants = new ArrayList<>();
		if (applications.size() != 0) {
			for (ScholarshipApplication s : applications) {
				applicants.add(s.getApplicant().getFirstName() + " " + s.getApplicant().getLastName() + ", "
						+ s.getApplicant().getUserID());
			}
		}

		return applicants;
	}

	// Using this for GUI
	public void addApplication(Student s, ScholarshipApplication application) {
		if (applicationOpen) {
			applications.add(application);
		}
		applications.sort(Comparator.naturalOrder());
	}

	/**
	 * Adds an application for a student
	 * 
	 * @param s
	 *            the student to add for
	 */
	public void addApplication(Student s) {
		if (applicationOpen) {
			applications.add(new ScholarshipApplication(this.getRequirements(), s));
		}
		applications.sort(Comparator.naturalOrder());
	}

	/**
	 * Removes an application based on id
	 * 
	 * @param id
	 *            the application to remove
	 */

	public void removeApplication(int id) {
		int found = search(id);
		if (found != -1) {
			applications.remove(found);
		}
	}

	/**
	 * Gets the application of a user with a specificed id
	 * 
	 * @param id
	 *            id of the user to get application of
	 * @return application, null if it doesn't exist
	 */
	public ScholarshipApplication getApplication(int id) {

		int found = search(id);
		if (found == -1) {
			return null;
		}

		return applications.get(found);
	}

	/**
	 * Searches for an application in the array based on id
	 * 
	 * @param id
	 *            id to search for
	 * @return the position in the array of that app or -1 if missing
	 */
	private int search(int id) {
		return search(id, 0, applications.size() - 1);
	}

	/**
	 * Recursive call for search()
	 * 
	 * @param id
	 * @param first
	 * @param last
	 * @return
	 */
	private int search(int id, int first, int last) {
		if (first == last) {
			if ((id == applications.get(first).getApplicant().getUserID())) {
				return first;
			} else {
				return -1;
			}
		}
		if (first > last) {
			return -1;
		}
		if (first < 0) {
			return -1;
		}
		if (last >= applications.size()) {
			return -1;
		}
		int mid = (first + last) / 2;
		int check = applications.get(mid).compareID(id);
		int found = mid;
		if (check > 0) {
			found = search(id, first, mid - 1);
		} else {
			if (check < 0) {
				found = search(id, mid + 1, last);
			}
		}
		return found;
	}

	/**
	 * Prints the scholarship information to command line
	 */
	public void viewScholarshipPrint() {
		System.out.println(this.getTitle());
		System.out.println(this.getFaculty());
		System.out.println(this.dateFormat());
		System.out.println(this.awardLimit);
		System.out.println(this.getDonor());
		System.out.println("Requirements:");
		for (String e : requirements.keySet()) {
			System.out.println("	" + e);
		}
	}

	/**
	 * Prints the information of all applications to the scholarship
	 */
	public void printApplications() {
		System.out.println(this.getTitle());
		System.out.println(this.getFaculty());
		System.out.println(this.dateFormat());
		System.out.println(this.awardLimit);
		System.out.println(this.getDonor());
		for (ScholarshipApplication a : applications) {
			System.out.println(a.getApplicant().getFirstName() + " " + a.getApplicant().getLastName());
			for (String e : a.getRequirements()) {
				System.out.println("	" + e + ":");
				System.out.println(a.getField(e));
			}
		}
	}

	/**
	 * Opens the scholarship for applications
	 */
	public void openApplication() {
		this.applicationOpen = true;
	}

	/**
	 * Closes the scholarship for applications
	 */
	public void closeApplication() {
		this.applicationOpen = false;
	}

	/**
	 * Gets the status of the scholarship fro applications
	 * 
	 * @return the status of the scholarship
	 */
	public boolean getApplicationOpen() {
		return applicationOpen;

	}

	/**
	 * Gets the baseline requirement for a requirement
	 * 
	 * @param requirement
	 *            requirement to get
	 * @return the baseline requirment
	 */
	public String getbaseline(String requirement) {
		if (requirements.containsKey(requirement)) {
			return requirements.get(requirement);
		}
		return null;

	}

}
