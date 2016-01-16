package AdminView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Professor.ProfessorUI;

public class ProfessorTablePanel extends JPanel{
	ProfessorTableModel professorTableModel;
	JTable table;
	JPopupMenu popupMenu;
	JMenuItem removeItem;
	tablePanelListener professorTableListener;
	
	public ProfessorTablePanel() {
		
		Dimension dim = new Dimension();
		dim.width = 700;
		dim.height = 500;
		setPreferredSize(dim);
		
		popupMenu = new JPopupMenu();
		removeItem = new JMenuItem("Delete row");
		popupMenu.add(removeItem);
		
		professorTableModel = new ProfessorTableModel();
		table = new JTable(professorTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
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
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (professorTableListener != null) {
					professorTableListener.tableRowDeleted(row);
					refreshTable();
				}
				
			}
		});
	}
	public void setModelDataSourse(ArrayList<ProfessorUI> arrayProfessor) {
		professorTableModel.setDataset(arrayProfessor);
	}
	public void refreshTable() {
		professorTableModel.fireTableDataChanged();
	}
	public void setProfessorTableListener(tablePanelListener professorTableListener) {
		this.professorTableListener = professorTableListener;
	}
}
