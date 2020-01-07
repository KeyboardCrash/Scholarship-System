package ScholarshipSystem;

import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

/**
 * 
 * This class creates a custom editable table model for the JTable in graphic
 * user interface, it allows editing of certain columns
 * 
 * @author Jessie Cai
 * @version final (4/12/19)
 * 
 */
public class EditableTableModel extends DefaultTableModel {

	private boolean[] editable;

	/**
	 * 
	 * The constructor for the editable table model, which needs a specified object
	 * array and columns array
	 * 
	 * @param requirements
	 * @param columns
	 */
	public EditableTableModel(Object[][] requirements, String[] columns) {
		super(requirements, columns);
		editable = new boolean[columns.length];
		Arrays.fill(editable, false);
	}

	/**
	 *
	 *
	 * Returns a boolean value for whether a specific column is editable or not
	 * 
	 * @param column
	 * @return
	 */
	public boolean getEditable(int column) {
		return editable[column];
	}

	/**
	 * 
	 * This sets a certain column in the table model to be editable
	 * 
	 * @param column
	 * @param flag
	 */
	public void setEditable(int column, boolean flag) {
		editable[column] = flag;
	}

	/**
	 * 
	 * Overrides the original isCellEditable method in order to return editable
	 * value for this class' instance variable
	 * 
	 * @param row
	 * @param column
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		if (editable[column]) {
			return true;
		} else {
			return false;
		}
	}
}
