package ScholarshipSystem;

/**
 *
 * A weighted item representing a title for use with heap sort methods
 *
 * @author Colin Au Yeung
 * @version final (4/12/19)
 * 
 */
public class WeighedTitle {
	private String title = "";
	private int weigh = 0;

	WeighedTitle parent;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            the title
	 * @param weigh
	 *            the weigh of the title
	 */
	public WeighedTitle(String title, int weigh) {
		this.title = title;
		this.weigh = weigh;

	}

	/**
	 * Returns the weight
	 * 
	 * @return the weigh
	 */
	public int getWeight() {
		return weigh;
	}

	/**
	 * Returns the title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * gets the parents of the title (heap)
	 * 
	 * @return parent
	 */
	public WeighedTitle getParent() {
		return parent;
	}

	/**
	 * Sets the parent of the title (heap)
	 * 
	 * @param parent
	 *            set value
	 */
	public void setParent(WeighedTitle parent) {
		this.parent = parent;
	}
}
