package ScholarshipSystem;

import java.util.ArrayList;

/**
 *
 * A group of methods for searching and comparing strings and dates
 *
 * @author Colin Au Yeung
 * @version final (4/12/19)
 */

public class Searching {

	// https://en.wikipedia.org/wiki/Levenshtein_distance

	/**
	 * Returns the Levenshtein distance of two words
	 * 
	 * @param a
	 *            word 1
	 * @param b
	 *            word 2
	 * @return the Levenshtein distance
	 */
	public static int LevenshteinDistance(String a, String b) {
		char[] a_s = a.toCharArray();
		char[] b_s = b.toCharArray();
		int[][] m = new int[a_s.length + 1][b_s.length + 1];
		for (int[] i : m) {
			for (int j : i) {
				j = 0;
			}
		}
		for (int i = 1; i < a_s.length + 1; i++) {
			m[i][0] = i;
		}
		for (int j = 1; j < b_s.length + 1; j++) {
			m[0][j] = j;
		}
		for (int j = 1; j < m.length; j++) {
			for (int i = 1; i < m[j].length; i++) {
				int cost;
				if (a_s[j - 1] == b_s[i - 1]) {
					cost = 0;
				} else {
					cost = 1;
				}
				m[j][i] = Math.min(m[j - 1][i] + 1, Math.min(m[j][i - 1] + 1, m[j - 1][i - 1] + cost));
			}
		}
		return m[m.length - 1][m[m.length - 1].length - 1];
	}

	/**
	 * Returns an the input array in order of closest to the input
	 * 
	 * @param search
	 *            the search input
	 * @param list
	 *            the input list of words
	 * @param num
	 *            the number of input words
	 * @return the list of words sorted
	 */
	public static ArrayList<String> getSimilar(String search, ArrayList<String> list, int num) {
		Heap m = new Heap();
		for (String a : list) {
			m.queue(new WeighedTitle(a, LevenshteinDistance(search, a)));
		}
		ArrayList<String> re = new ArrayList<>();
		for (int i = 0; i < num && !m.isEmpty(); i++) {
			re.add(m.dequeue().getTitle());
		}
		return re;
	}

	/**
	 * Compares two dates, date1 and date2. Returns 1 if date1 is before date2, -1
	 * if date1 is after date2 and 0 if the dates are the same
	 * 
	 * @param day1
	 *            the day of date1
	 * @param day2
	 *            the day of date2
	 * @param month1
	 *            the month of date1
	 * @param month2
	 *            the month of date2
	 * @param year1
	 *            the year of date 1
	 * @param year2
	 *            the year of date2
	 * @return
	 */
	public static int compareDate(int day1, int day2, int month1, int month2, int year1, int year2) {
		if (year1 < year2) {
			return 1;
		} else {
			if (year1 > year2) {
				return -1;
			} else {
				if (month1 < month2) {
					return 1;
				} else {
					if (month1 > month2) {
						return -1;
					} else {
						if (day1 < day2) {
							return 1;
						} else {
							if (day1 > day2) {
								return -1;
							} else {
								return 0;
							}
						}
					}
				}
			}
		}
	}

}
