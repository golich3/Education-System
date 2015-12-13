package AdminView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Course.CourseUI;

public class CourseTableModel extends AbstractTableModel {
	List<CourseUI> arrayCourse;

	CourseTableModel() {
		arrayCourse = new ArrayList<CourseUI>();
	}

	private String[] header = { "Course No", "Course Name","Course Professor" };

	@Override
	public int getColumnCount() {
		int size = header.length;
		return size;
	}

	@Override
	public String getColumnName(int i) {
		return header[i];
	}

	@Override
	public int getRowCount() {
		return arrayCourse.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (column==0) {
			return arrayCourse.get(row).getCourseNo();
		}if (column==1) {
			return arrayCourse.get(row).getCourseName();
		}if (column==2) {
			return arrayCourse.get(row).getProfessor();
		}
		return null;
	}

	public void setModel(List<CourseUI> arrayCourseEnterance) {
		this.arrayCourse = arrayCourseEnterance;

	}

}
