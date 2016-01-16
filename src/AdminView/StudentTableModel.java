package AdminView;

import java.util.*;
import javax.swing.table.AbstractTableModel;


import Student.StudentUI;

public class StudentTableModel extends AbstractTableModel {

	ArrayList<StudentUI> arrayStudent;

	private String[] s = { "First Name", "Last Name", "Student NO", "Phone",
			"Email", "User Name", "Password", "Address" };

	public StudentTableModel() {
		arrayStudent = new ArrayList<StudentUI>();
	}

	@Override
	public int getColumnCount() {
		int size = s.length;
		return size;
	}

	@Override
	public String getColumnName(int in) {
		// TODO Auto-generated method stub
		return s[in];
	}

	@Override
	public int getRowCount() {
		return arrayStudent.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return arrayStudent.get(row).getFirstName();
		} else if (column == 1) {
			return arrayStudent.get(row).getLastName();
		} else if (column == 2) {
			return arrayStudent.get(row).getStudentNo();
		} else if (column == 3) {
			return arrayStudent.get(row).getPhone();
		} else if (column == 4) {
			return arrayStudent.get(row).getEmail();
		} else if (column == 5) {
			return arrayStudent.get(row).getUserName();
		} else if (column == 6) {
			return arrayStudent.get(row).getPassword();
		} else if (column == 7) {
			return arrayStudent.get(row).getAddress();
		}
		return null;
	}

	void setDs(ArrayList<StudentUI> arrayStudentEnterance) {
		this.arrayStudent = arrayStudentEnterance;
	}
}
