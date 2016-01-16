package AdminView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Student.StudentUI;

public class StudentTablePanel extends JPanel{
	StudentTableModel studentTableModel;
	JTable table;
	private JPopupMenu popupMenu;
	tablePanelListener studentTableListener;
	
	public StudentTablePanel() {
		
		Dimension dim = new Dimension();
		dim.width = 700;
		dim.height = 500;
		setPreferredSize(dim);
		
		studentTableModel = new StudentTableModel();
		table = new JTable(studentTableModel);
		setLayout(new BorderLayout());
		popupMenu = new JPopupMenu();
		JMenuItem removeItem = new JMenuItem("Delete Row");
		popupMenu.add(removeItem);
		table.getColumnModel().getColumn(7).setPreferredWidth(250);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent in) {
				int row = table.rowAtPoint(in.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				if (in.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, in.getX(), in.getY());
				}
			}
		});
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent in) {
				int row = table.getSelectedRow();
				if (studentTableListener != null) {
					studentTableListener.tableRowDeleted(row);
					refreshTable();
				}
			}
		});
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	void setModelDataSourse(ArrayList<StudentUI> st){
		studentTableModel.setDs(st);
		
	}
	
void refreshTable(){
		studentTableModel.fireTableDataChanged();
	}
	
public void setStudentTableListener(tablePanelListener studentTableListener) {
		this.studentTableListener = studentTableListener;
	}
}
