package ScholarshipSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * A class to manage the adding, editing and removal of scholarships and their
 * applications in the system
 *
 * @author Jessie Cai, Colin Au Yeung
 * @version final (4/12/19)
 */

/*
 * Notes: Editing should be done through the ScholarshipManager in order to
 * ensure the data is saved and order of arrays is maintained If you named the
 * instance of the ScholarshipManager "sc": To get a all scholarships should be
 * done with loop like: for(String s: sc.getTitles(){ sc.getScholarship(s); }
 * 
 * 
 */
public class ScholarshipManager {

	public ArrayList<Scholarship> Scholarships;

	public void viewScholarship(Scholarship scholarship) {
	}

	public ScholarshipManager() {
		Scholarships = this.deserialize();
	}

	/**
	 * Adds a scholarship to the arraylist
	 * 
	 * @param add
	 *            scholarship to add
	 */
	public void addScholarship(Scholarship add) {
		Scholarships.add(add);
		Scholarships.sort(Comparator.naturalOrder());
		this.serialize();
	}

	/**
	 * Creates a scholarship based on the base requirements of the GUI
	 * 
	 * @param title
	 *            title of the scholarship
	 * @param donor
	 *            donar of the scholarship
	 * @param faculty
	 *            faculty of the scholarship
	 * @param amount
	 *            amount of the scholarship
	 * @param awardLimit
	 *            number times that the scholarship can be awarded
	 * @param deadlineDay
	 *            the deadline of the scholarship, day
	 * @param deadlineMonth
	 *            the deadline of the scholarship, month
	 * @param deadlineYear
	 *            the deadline of the scholarship, year
	 * @param baseAge
	 *            the required age of the scholarship
	 * @param baseGPA
	 *            the required GPA of the scholarship
	 * @param baseLevel
	 *            the required level of the scholarship
	 * @param baseYear
	 *            the required year of study
	 * @param major
	 *            the required major
	 * @param baseGender
	 *            the required gender
	 * @param baseIncome
	 *            the required income
	 * @param baseCitizen
	 *            the required citizenship
	 * @param baseExtraCurric
	 *            the required extra currics
	 */
	public void addScholarshipGUI(String title, String donor, Enumerations.faculty faculty, double amount,
			int awardLimit, int deadlineDay, int deadlineMonth, int deadlineYear, String baseAge, String baseGPA,
			String baseLevel, String baseYear, String major, String baseGender, String baseIncome, String baseCitizen,
			String baseExtraCurric) {
		Scholarship scholarship = new Scholarship(title, donor, faculty, amount, awardLimit, deadlineDay, deadlineMonth,
				deadlineYear);
		this.addScholarship(scholarship);
		getScholarship(title).addRequirement("Age", baseAge);
		getScholarship(title).addRequirement("GPA", baseGPA);
		getScholarship(title).addRequirement("Level", baseLevel);
		getScholarship(title).addRequirement("Year", baseYear);
		getScholarship(title).addRequirement("Major", major);
		getScholarship(title).addRequirement("Gender", baseGender);
		getScholarship(title).addRequirement("Income", baseIncome);
		getScholarship(title).addRequirement("Citizen", baseCitizen);
		getScholarship(title).addRequirement("ExtraCuric", baseExtraCurric);
		this.serialize();

	}

	/**
	 *
	 * @param title
	 *            Scholarship to edit
	 * @param field
	 *            Element of scholarship to edit (must be one of Donor, Title,
	 *            Amount, Faculty, Date Month, Date Day, Date Year, Award Limit)
	 *            type sensitive
	 * @param newField
	 *            Things to change that field to
	 */
	public boolean editScholarship(String title, String field, String newField) {
		boolean success = true;
		int sc = search(title);
		if (sc != -1) {

			switch (field) {
			case "Donor":
				Scholarships.get(sc).setDonor(newField);
				break;
			case "Title":
				Scholarships.get(sc).setTitle(newField);
				Scholarships.sort(Comparator.naturalOrder());
				break;
			case "Amount":
				try {
					Scholarships.get(sc).setAmount(Double.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Faculty":
				try {
					Scholarships.get(sc).setFaculty(Enumerations.faculty.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Date Month":
				try {
					Scholarships.get(sc).setDeadlineMonth(Integer.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Date Day":
				try {
					Scholarships.get(sc).setDeadlineDay(Integer.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}

				break;
			case "Date Year":
				try {
					Scholarships.get(sc).setDeadlineYear(Integer.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Award Limit":
				try {
					Scholarships.get(sc).setAwardLimit(Integer.valueOf(newField));
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Age":
				try {
					Scholarships.get(sc).editRequirement("Age", newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "GPA":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Level":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Year":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Major":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Gender":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Income":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "Citizen":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;
			case "ExtraCuric":
				try {
					Scholarships.get(sc).editRequirement(field, newField);
				} catch (Exception e) {
					success = false;
				}
				break;

			}

		}
		this.serialize();
		return success;
	}

	/**
	 * 
	 * @param title
	 * @return returns the scholarship from the arraylist that has that title
	 */
	public Scholarship getScholarship(String title) {
		int sc = search(title);
		if (sc == -1) {
			return null;
		}
		return Scholarships.get(sc);
	}

	/**
	 *
	 * @param title
	 *            removes a scholarship from the arraylist that has that title
	 *
	 */
	public void removeScholarship(String title) {
		int sc = search(title);
		if (sc != -1) {
			Scholarships.remove(sc);
		}
		this.serialize();
	}

	/**
	 *
	 * @return a list of all scholarships
	 */
	public ArrayList<String> getTitles() {
		ArrayList<String> titles = new ArrayList<>();
		for (Scholarship s : Scholarships) {
			titles.add(s.getTitle());
		}
		return titles;
	}

	/**
	 *
	 * @param faculty
	 *            the faculty to search for
	 * @return a list of scholarships have a particular faculty
	 */
	public ArrayList<String> getTitleByFaculty(Enumerations.faculty faculty) {
		ArrayList<String> titles = new ArrayList<>();
		for (Scholarship s : Scholarships) {
			if (s.getFaculty().equals(faculty)) {
				titles.add(s.getTitle());
			}
		}
		return titles;
	}

	/**
	 *
	 * @param title:
	 *            The title of the scholarship, you want. TYPE SENSITIVE
	 * @return index of the scholarship in the scholarship array
	 */
	private int search(String title) {
		return search(title, 0, Scholarships.size() - 1);
	}

	private int search(String title, int first, int last) {
		if (first == last) {
			if ((title.equals(Scholarships.get(first).getTitle()))) {
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
		if (last >= Scholarships.size()) {
			return -1;
		}
		int mid = (first + last) / 2;
		int check = Scholarships.get(mid).compareTo(title);
		int found = mid;
		if (check > 0) {
			found = search(title, first, mid - 1);
		} else {
			if (check < 0) {
				found = search(title, mid + 1, last);
			}
		}
		return found;
	}

	/**
	 * Serializes scholarships to the data file
	 *
	 */
	public void serialize() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("scholarships.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Scholarships);
			fileOut.close();
			out.close();
			System.out.println("Serialization completed");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the scholarships from the data file
	 * 
	 * @return the scholarships from the data file
	 */
	public ArrayList<Scholarship> deserialize() {
		ArrayList<Scholarship> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("scholarships.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (ArrayList<Scholarship>) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception k) {
			System.out.println("scholarships.ser was not found");
			return new ArrayList<Scholarship>();
		}
		return e;
	}

	/**
	 * Retrieves a application
	 * 
	 * @param title
	 *            the scholarship that the appliaction belongs to
	 * @param student
	 *            the student that the application belongs to
	 * @return
	 */
	public ScholarshipApplication getApplication(String title, Student student) {
		return this.getScholarship(title).getApplication(student.getUserID());
	}

	/**
	 * Adds an application to a scholarship
	 * 
	 * @param Scholarship
	 *            Scholar to make an application to
	 * @param s
	 *            the student to make an application for
	 * @param application
	 *            The application to add
	 * @return whether successful or not
	 */
	public boolean addApplication(String Scholarship, Student s, ScholarshipApplication application) {
		int found = search(Scholarship);
		if (found == -1)
			return false;
		Scholarships.get(found).addApplication(s, application);
		serialize();
		return true;
	}

	/**
	 * Fills a field in an application
	 * 
	 * @param Scholarship
	 *            Scholarship which the application belongs to
	 * @param Applicationid
	 *            the student id of the application
	 * @param fieldToFill
	 *            the field to fill
	 * @param info
	 *            what to fill into the field
	 * @return whether successful or not
	 */

	public boolean fillApplication(String Scholarship, int Applicationid, String fieldToFill, String info) {
		System.out.println(Applicationid);
		int found = search(Scholarship);
		if (found == -1)
			return false;
		Scholarships.get(found).getApplication(Applicationid).fillField(fieldToFill, info);
		System.out.println(Scholarships.get(found).getApplication(Applicationid).getApplicant().getUserID());
		serialize();
		return true;

	}

	/**
	 * Removes an application from a scholarship
	 * 
	 * @param Scholarship
	 *            The scholarship that the application belongs to
	 * @param Applicationid
	 *            the student id of the application
	 * @return whether successful or not
	 */
	public boolean removeApplication(String Scholarship, int Applicationid) {
		int found = search(Scholarship);
		if (found == -1)
			return false;
		Scholarships.get(found).removeApplication(Applicationid);
		serialize();
		return true;
	}

	/**
	 * Returns the titles in order of closest to the input
	 * 
	 * @param in
	 *            the string to search titles for
	 * @return An array of titles in order of closest to the input
	 */
	public ArrayList<String> findTitle(String in) {
		ArrayList<String> str = this.getTitles();
		return Searching.getSimilar(in, str, str.size());
	}

	/**
	 * Returns the titles in order of closest to the input and filters by a critera
	 * 
	 * @param in
	 *            the string to search titles for
	 * @param filter
	 *            the critera to filter by
	 * @param field
	 * @return
	 */
	public ArrayList<String> filterTitles(String in, String filter, String field) {
		String[] broken;
		int[] nums;
		int i;
		ArrayList<String> titles;
		switch (filter) {
		// field = "Science"
		case "Faculty":
			ArrayList<String> str = this.getTitleByFaculty(Enumerations.faculty.valueOf(field));
			return Searching.getSimilar(in, str, str.size());
		// field = "500"
		case "Amount Less Than":
			titles = new ArrayList<>();
			int amount = Integer.valueOf(field);
			if (amount == -1) {
				return titles;
			}
			for (Scholarship sc : Scholarships) {
				if (sc.getAmount() >= amount) {
					titles.add(sc.getTitle());
				}
			}
			return Searching.getSimilar(in, titles, titles.size());
		// field = "500"
		case "Amount Greater Than":
			titles = new ArrayList<>();
			int amount2 = Integer.valueOf(field);
			if (amount2 == -1) {
				return titles;
			}
			for (Scholarship sc : Scholarships) {
				if (sc.getAmount() <= amount2) {
					titles.add(sc.getTitle());
				}
			}
			return Searching.getSimilar(in, titles, titles.size());
		// field = DD/MM/YYYY
		case "Before Date":
			titles = new ArrayList<>();
			broken = field.split("/");
			nums = new int[broken.length];
			i = 0;
			for (String s : broken) {
				nums[i] = Integer.valueOf(s);
				i++;
			}
			for (Scholarship sc : Scholarships) {
				if (Searching.compareDate(nums[0], sc.getDeadlineDay(), nums[1], sc.getDeadlineMonth(), nums[2],
						sc.getDeadlineYear()) == 1) {
					titles.add(sc.getTitle());
				}
			}
			return Searching.getSimilar(in, titles, titles.size());
		case "After Date":
			titles = new ArrayList<>();
			broken = field.split("/");
			nums = new int[broken.length];
			i = 0;
			for (String s : broken) {
				nums[i] = Integer.valueOf(s);
				i++;
			}
			for (Scholarship sc : Scholarships) {
				if (Searching.compareDate(nums[0], sc.getDeadlineDay(), nums[1], sc.getDeadlineMonth(), nums[2],
						sc.getDeadlineYear()) == -1) {
					titles.add(sc.getTitle());
				}
			}
			return Searching.getSimilar(in, titles, titles.size());
		case "Date is equal":
			titles = new ArrayList<>();
			broken = field.split("/");
			nums = new int[broken.length];
			i = 0;
			for (String s : broken) {
				nums[i] = Integer.valueOf(s);
				i++;
			}
			for (Scholarship sc : Scholarships) {
				if (Searching.compareDate(nums[0], sc.getDeadlineDay(), nums[1], sc.getDeadlineMonth(), nums[2],
						sc.getDeadlineYear()) == 0) {
					titles.add(sc.getTitle());
				}
			}
			return Searching.getSimilar(in, titles, titles.size());
		}
		return new ArrayList<>();
	}

}
