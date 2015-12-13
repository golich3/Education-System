package AdminView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Course.CourseUI;

public class CourseTablePanel extends JPanel {
	CourseTableModel courseTableModel;
	JTable table;
	JPopupMenu popupMenu;
	JMenuItem removeItem;
	tablePanelListener courseTableListener;

	public void setCourseTableListener(tablePanelListener courseTableListener) {
		this.courseTableListener = courseTableListener;
	}

	public CourseTablePanel() {

		Dimension dim = new Dimension();
		dim.width = 700;
		dim.height = 500;
		setPreferredSize(dim);
		
		popupMenu = new JPopupMenu();
		removeItem = new JMenuItem("Delete row");
		popupMenu.add(removeItem);
		
		courseTableModel = new CourseTableModel();
		table = new JTable(courseTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (courseTableListener != null) {
					courseTableListener.tableRowDeleted(row);
					refreshTable();
				}
			}
		});
	}

	public void setModelDataSourse(List<CourseUI> arrayCourse) {
		courseTableModel.setModel(arrayCourse);
		
	}

	public void refreshTable() {
		courseTableModel.fireTableDataChanged();
	}
}
