package AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {

	private JButton saveButton;
	private JButton refreshButton;
	private ToolbarListener toolbarListener;

	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(true);
		saveButton = new JButton();
		saveButton.setIcon(Utils.createIcon("/images/Save16.gif"));
		saveButton.setToolTipText("Save To DataBase");

		refreshButton = new JButton();
		refreshButton.setToolTipText("Refresh Data");
		refreshButton.setIcon(Utils.createIcon("/images/Refresh16.gif"));

		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);

		add(saveButton);
		addSeparator();
		add(refreshButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			if (this.toolbarListener != null) {
				toolbarListener.saveEventOccured();
			}
		} else {
			if (this.toolbarListener != null) {
				toolbarListener.refreshEventOccured();
			}
		}
	}

	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}
}
