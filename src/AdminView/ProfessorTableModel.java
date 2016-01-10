package AdminView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Professor.ProfessorUI;
import Student.StudentUI;

public class ProfessorTableModel extends AbstractTableModel {
	
	List<ProfessorUI> arrayProfessor;
	
	private String[] s = { "First Name", "Last Name", "Professr NO", "Phone","Email", "User Name", "Password", "Address" };

	ProfessorTableModel(){
		arrayProfessor = new ArrayList<ProfessorUI>();
	}
	
	@Override
	public int getColumnCount() {
		int size = s.length;
		return size;
	}

	@Override
	public String getColumnName(int in) {
		return s[in];
	}

	@Override
	public int getRowCount() {
		return arrayProfessor.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		if (column == 0) {
			return arrayProfessor.get(row).getFirstName();
		} else if (column == 1) {
			return arrayProfessor.get(row).getLastName();
		} else if (column == 2) {
			return arrayProfessor.get(row).getProfessorNo();
		} else if (column == 3) {
			return arrayProfessor.get(row).getPhone();
		} else if (column == 4) {
			return arrayProfessor.get(row).getEmail();
		} else if (column == 5) {
			return arrayProfessor.get(row).getUserName();
		} else if (column == 6) {
			return arrayProfessor.get(row).getPassword();
		} else if (column == 7) {
			return arrayProfessor.get(row).getAddress();
		}

		return null;
	}


	public void setDataset(List<ProfessorUI> arrayProfessor) {
		this.arrayProfessor = arrayProfessor;
	}

}
