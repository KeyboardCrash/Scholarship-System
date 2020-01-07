package ScholarshipSystem;

/**
 * Manager class that interacts with student object to ensure proper data hierarchy
 * 
 * @author Jessie Cai, Shaina Rosell 
 * @version final (4/12/19) 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StudentManager {

	private ArrayList<Student> students = new ArrayList<>();

	private Student viewedStudent;

	/**
	 * Basic constructor for the student manager
	 * 
	 * @param database
	 */
	public StudentManager(UserDatabase database) {
		students = this.deserialize();
	}

	/**
	 * Return the currently viewed student
	 * 
	 * @return Student viewedStudent
	 */
	public Student getViewedStudent() {
		return viewedStudent;
	}

	/**
	 * Accept the scholarship of a given student's application
	 * 
	 * @param student
	 * @param sc
	 * @param title
	 * @param admin
	 * @param nm
	 * @return Boolean
	 */
	public boolean acceptScholarship(Student student, ScholarshipManager sc, String title, int admin,
			NotificationManager nm) {

		student.getStudentData().setNumberOfScholarshipsAccepted(1);
		sc.getScholarship(title).getApplication(student.getUserID()).setStatus(Enumerations.status.ACCEPTED);

		Notification notification = new Notification("Accepted " + title + " " + student.getUserID(),
				student.getFirstName() + " " + student.getLastName() + " has accepted " + title + ".");
		nm.sendNotification(admin, notification);

		updateStudentInfo(student);
		sc.serialize();

		return true;
	}

	/**
	 * Reject the scholarship of a given student's application
	 * 
	 * @param student
	 * @param sc
	 * @param title
	 * @param admin
	 * @param nm
	 * @return Boolean
	 */
	public boolean rejectScholarship(Student student, ScholarshipManager sc, String title, int admin,
			NotificationManager nm) {

		sc.getScholarship(title).setAwardLimit(sc.getScholarship(title).getAwardLimit() + 1);
		if (sc.getScholarship(title).getAwardLimit() > 0) {
			sc.getScholarship(title).openApplication();
		}
		sc.getScholarship(title).getApplication(student.getUserID()).setStatus(Enumerations.status.DECLINED);

		Notification notification = new Notification("Declined " + title + " " + student.getUserID(),
				student.getFirstName() + " " + student.getLastName() + " has declined " + title + ".");
		nm.sendNotification(admin, notification);

		updateStudentInfo(student);
		sc.serialize();

		return true;
	}

	/**
	 * Return the list of students
	 * 
	 * @return ArrayList<Student> students
	 */
	public ArrayList<Student> getStudentList() {
		return students;
	}

	/**
	 * 
	 * Get student object associated with ID
	 * 
	 * @param id
	 * @return
	 */
	public Student getStudent(int id) {

		for (Student student : this.deserialize()) {
			if (student.getUserID() == id) {
				this.viewedStudent = student;
			}
		}

		return viewedStudent;

	}

	/**
	 * Return the index of a student in the array list
	 * 
	 * @param id
	 * @return
	 */
	public int getStudentIndex(int id) {
		int i = -1;
		for (Student student : this.deserialize()) {
			i += 1;
			if (student.getUserID() == id) {
				return i;
			}
		}

		return i;
	}

	/**
	 * toString method to receive name of student
	 * 
	 * @param a
	 * @return String
	 */
	public String getFullName(Student a) {
		return a.getFirstName() + " " + a.getLastName() + ", " + a.getUserID();
	}

	/**
	 * Sort categories to display for UI
	 * 
	 * @param sort
	 * @return ArrayList<String>
	 */
	public ArrayList<String> sortCategory(Enumerations.sort sort) {

		ArrayList<String> sortedList = new ArrayList<>();

		switch (sort) {
		case GRANTEDSCHOLARSHIP:
			for (Student a : students) {
				if (!(a.canApplyForScholarships())) {
					sortedList.add(getFullName(a));

				}
			}
			break;

		case NOAPPLICATION: {
			for (Student a : students) {
				if (a.getStudentData().getScholarshipAppliedTitles().size() == 0) {
					sortedList.add(getFullName(a));
				}
			}
		}
			break;

		case APPLIEDONLY: {
			for (Student a : students) {
				if (a.canApplyForScholarships() && a.getStudentData().getScholarshipAppliedTitles().size() != 0) {
					sortedList.add(getFullName(a));
				}
			}
		}
			break;
		}

		return sortedList;
	}

	/**
	 * Get the list of names of students
	 * 
	 * @return ArrayList<String> studentNames
	 */
	public ArrayList<String> getListNames() {

		ArrayList<String> studentNames = new ArrayList<>();
		for (Student a : this.deserialize()) {
			studentNames.add(a.getFirstName() + " " + a.getLastName() + ", " + a.getUserID());
		}
		return studentNames;
	}

	/**
	 * Adds a student to the database
	 * 
	 * @param a
	 */
	public void updateStudent(User a) {
		students.add((Student) a);
		this.serialize();
	}

	// update this student in the arraylist

	/**
	 * Updates info for a student
	 * 
	 * @param st
	 */
	public void updateStudentInfo(User st) {

		int index = getStudentIndex(st.getUserID());
		System.out.println("Index of student " + index);
		students.set(index, (Student) st);
		this.serialize();
	}

	/**
	 * Set the current student that is viewing the panel
	 * 
	 * @param ID
	 */
	public void setViewedStudent(int ID) {
		for (Student a : this.deserialize()) {

			if (a.getUserID() == ID) {
				this.viewedStudent = a;
			}
		}
	}

	/**
	 * Remove an application from the student
	 * 
	 * @param st
	 * @param schol
	 */
	public void removeApplication(Student st, String schol) {
		st.removeScholarshipApplication(schol);
		updateStudentInfo(st);
	}

	/**
	 * Save data to database
	 */
	public void serialize() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("students.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(students);
			fileOut.close();
			out.close();
			System.out.println("student.ser was found, and has been serialized");
		} catch (FileNotFoundException e) {
			System.out.println("student.ser not found");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Export data and return an arraylist of saved data
	 * 
	 * @return ArrayList<Student>
	 */
	public ArrayList<Student> deserialize() {
		ArrayList<Student> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("students.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (ArrayList<Student>) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception k) {
			System.out.println("students.ser was not found");
			return new ArrayList<Student>();
		}
		return e;
	}

}
