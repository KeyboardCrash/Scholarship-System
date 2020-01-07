package ScholarshipSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Enumerations for different categories for sorting/labeling related to
 * scholarships applications, and students.
 * 
 * @author Jessie Cai, Shaina Rosell, Colin Au Yeung
 * @version final (4/12/19)
 * 
 */
public class Enumerations implements Serializable {

	/**
	 * 
	 * Specifies the level of a student
	 *
	 */
	public enum level {

		UNDERGRADUATE("Undergraduate"), GRADUATE("Graduate"), MASTERS("Masters"), DOCTORATE("Doctorate"), PHD("PhD");

		public String str;

		level(String str) {
			this.str = str;
		}
	}

	/**
	 *
	 * Specifies the status of an application
	 *
	 *
	 */
	public enum status {

		NOTSUBMITTED("Not submitted"), SUBMITTED("Submitted"), REJECTED("Rejected"), APPROVED("Approved"), ACCEPTED(
				"Accepted"), DECLINED("Declined");

		public String str;

		status(String str) {
			this.str = str;
		}
	}

	/**
	 * Sorts the list of students for admin
	 *
	 */
	public enum sort {

		GRANTEDSCHOLARSHIP("Granted scholarship"), NOAPPLICATION("No application"), APPLIEDONLY(
				"Applied only"), SATISFIEDALLREQUIREMENTS(
						"Satisfied all requirements"), SATISFIEDPARTIALREQUIREMENTS("Satisfied partial requirements");

		public String str;

		sort(String str) {
			this.str = str;
		}
	}

	/**
	 * 
	 * Lists the different faculties
	 *
	 */
	public enum faculty {

		Science("Science"), Arts("Arts"), Medicine("Medicine"), Environmental_Design(
				"Environmental Design"), Graduate_Studies("Graduate Studies"), Business("Business"), Kinesiology(
						"Kinesiology"), Law("Law"), Engineering("Engineering"), Social_Work(
								"Social Work"), Veterinary_Medicine("Veterinary Medicine"), Education("Education");

		public String str;

		faculty(String str) {
			this.str = str;
		}
	}

	/**
	 * 
	 * Specifies the different genders
	 *
	 */
	public enum gender {

		MALE("Male"), FEMALE("Female"), OTHER("Other");

		public String str;

		gender(String str) {
			this.str = str;
		}

	}
}
