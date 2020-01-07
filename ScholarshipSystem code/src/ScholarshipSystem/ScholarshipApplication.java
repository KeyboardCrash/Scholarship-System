
package ScholarshipSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * A class representing an appication to a scholarship in the system
 *
 * @author Jessie Cai, Colin Au Yeung
 * @version final (4/12/19)
 */
public class ScholarshipApplication implements Comparable, Serializable {

	private Student applicant;
	private String scholarshipTitle;
	private Enumerations.status status;
	private HashMap<String, String> requirements = new HashMap<String, String>();

	/**
	 * Constructor with requirements passed in
	 * 
	 * @param requirements
	 *            requirements of the scholarship
	 * @param s
	 *            the applicant
	 */
	public ScholarshipApplication(ArrayList<String> requirements, Student s) {
		for (String e : requirements) {
			this.requirements.put(e, "");
		}
		this.applicant = s;
		status = Enumerations.status.NOTSUBMITTED;
	}

	/**
	 * Constructor with Gui base requirements
	 * 
	 * @param scholarshipTitle
	 *            title of the scholarship
	 * @param age
	 *            age of the applicant
	 * @param gender
	 *            gender of the applicant
	 * @param citizen
	 *            citizen of the applicant
	 * @param major
	 *            major of the applicant
	 * @param gpa
	 *            gpa of the applicant
	 * @param contact
	 *            contact info of the applicant
	 * @param extracurric
	 *            extracurrics of the applicant
	 * @param income
	 *            incomee of the applicant
	 * @param s
	 *            the applicant
	 */
	public ScholarshipApplication(String scholarshipTitle, String age, String gender, String citizen, String major,
			String gpa, String contact, String extracurric, String income, Student s) {

		this.scholarshipTitle = scholarshipTitle;
		requirements.put("Age", age);
		requirements.put("Gender", gender);
		requirements.put("Citizen", citizen);
		requirements.put("Major", major);
		requirements.put("GPA", gpa);
		requirements.put("Contact", contact);
		requirements.put("ExtraCuric", extracurric);
		requirements.put("Income", income);

		this.applicant = s;
		status = Enumerations.status.NOTSUBMITTED;
	}

	/**
	 * Get's the scholarhsip title
	 * 
	 * @return the scholarship title
	 */
	public String getScholarshipTitle() {
		return scholarshipTitle;
	}

	/**
	 * Updates the status of the application
	 * 
	 * @param change
	 *            the value to set to
	 */
	public void updateStatus(String change) {

		if (change.equals("SUBMITTED")) {
			status = Enumerations.status.SUBMITTED;
		} else if (change.equals("REJECTED")) {
			status = Enumerations.status.REJECTED;
		} else if (change.equals("APPROVED")) {
			status = Enumerations.status.APPROVED;
		} else if (change.equals("ACCEPTED")) {
			status = Enumerations.status.ACCEPTED;
		} else if (change.equals("DECLINED")) {
			status = Enumerations.status.DECLINED;
		}

	}

	/**
	 * Returns whether complete
	 * 
	 * @return is complete or not
	 */
	public boolean isComplete() {
		boolean complete = true;

		for (String key : requirements.keySet()) {
			if ("".equals(this.requirements.get(key))) {
				complete = false;
			}
		}
		return complete;
	}

	/**
	 * Fills in a field in the application
	 * 
	 * @param requirement
	 *            Requirement to fill
	 * @param field
	 *            thing to save in the application
	 * @return whether the requirement was found
	 */
	public boolean fillField(String requirement, String field) {
		if (requirements.containsKey(requirement)) {
			requirements.replace(requirement, field);
			// this.updateStatus();
			return true;
		} else
			return false;
	}

	/**
	 *
	 * @return Array with all the requirements of the application
	 */
	public String[] getRequirements() {
		String[] a = new String[1];
		return requirements.keySet().toArray(a);
	}

	/**
	 * Gets a specifc field in the application
	 * 
	 * @param Requirement
	 *            requirement field to get
	 * @return String, the field
	 */
	public String getField(String Requirement) {
		if (requirements.containsKey(Requirement)) {
			return requirements.get(Requirement);
		} else
			return "";
	}

	/**
	 * gets the applicant for the application
	 * 
	 * @return the applicant
	 */
	public Student getApplicant() {
		return applicant;
	}

	/**
	 * Gets the status of the application
	 * 
	 * @return the status
	 */
	public Enumerations.status getStatus() {
		return status;

	}

	/**
	 * changes the status of an application
	 * 
	 * @param status
	 *            the status to change to
	 */
	public void setStatus(Enumerations.status status) {
		this.status = status;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() == ScholarshipApplication.class) {
			return compareTo((ScholarshipApplication) o);
		}
		return 0;
	}

	/**
	 * Overload for compareTo
	 * 
	 * @param o
	 * @return
	 */
	public int compareTo(ScholarshipApplication o) {
		return ((Integer) (applicant.getUserID())).compareTo(((Integer) o.applicant.getUserID()));
	}

	/**
	 * Compares the id of application to an input using compareTO
	 * 
	 * @param o
	 *            input id
	 * @return -1 if less, 0 if same, 1 if larger
	 */
	public int compareID(Integer o) {
		return ((Integer) (applicant.getUserID())).compareTo(o);

	}
}
