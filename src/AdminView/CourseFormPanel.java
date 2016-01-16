package AdminView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Course.CourseUI;
import Professor.ProfessorUI;

public class CourseFormPanel extends JPanel {
	private JLabel courseNo;
	private JTextField courseNoText;
	private JLabel courseName;
	private JTextField courseNameText;
	private JButton addCourseBtn;
	private CourseUI newCourse;
	private ICourse iCourse;
	private JLabel courseProfessor;
	private JComboBox<ProfessorUI> professorComboBox;
	public JComboBox<ProfessorUI> getProfessorComboBox() {
		return professorComboBox;
	}

	void setProfessorComboBox(JComboBox<ProfessorUI> professorComboBox) {
		this.professorComboBox = professorComboBox;
	}

	public void setiCourse(ICourse iCoursesenterance) {
		this.iCourse = iCoursesenterance;
	}

	public JTextField getCourseNoText() {
		return courseNoText;
	}

	public void setCourseNoText(JTextField courseNoText) {
		this.courseNoText = courseNoText;
	}

	public JTextField getCourseNameText() {
		return courseNameText;
	}

	public void setCourseNameText(JTextField courseNameText) {
		this.courseNameText = courseNameText;
	}

	CourseFormPanel() {
		Dimension dim = new Dimension();
		dim.height = 150;
		dim.width = 700;
		setPreferredSize(dim);
		setMinimumSize(dim);
		setLayout(new GridBagLayout());
		ComponentLayout();

		addCourseBtn.addActionListener(new ActionListener() {

			String emptyHintHolder="";
			String notMatchedFormatHintHolder="";
			@Override
			public void actionPerformed(ActionEvent in) {
//				checknecessaryFieldsfilling();
//				checkMatchFieldsFormat();
//				if (!emptyHintHolder.isEmpty()) {
//					JOptionPane.showMessageDialog(CourseFormPanel.this, emptyHintHolder, "Necessary Fields did not fill",JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
//				}else if (!notMatchedFormatHintHolder.isEmpty()) {
//					JOptionPane.showMessageDialog(CourseFormPanel.this, notMatchedFormatHintHolder, "Incorrect format", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
//				}else {
					makeNewCourse();
					iCourse.setCourse(newCourse);
					makeTableEmpty();
//				}
			}

			public void checkMatchFieldsFormat() {
				notMatchedFormatHintHolder="";
				if (!courseNoText.getText().matches("[0-9]{4}")) {
					notMatchedFormatHintHolder = "Course number is not valid\n";
				}if (courseNameText.getText().matches("[a-zA-Z0-9]")) {
					notMatchedFormatHintHolder += "course name is not valid\n";
				}
			}

			public void checknecessaryFieldsfilling() {
				emptyHintHolder = "";
				if (courseNoText.getText().isEmpty()) {
					emptyHintHolder = "Course number is empty\n";
				}if (courseNameText.getText().isEmpty()) {
					emptyHintHolder += "Course name is empty\n";
				}
			}

			public void makeNewCourse() {
				newCourse = new CourseUI();
				newCourse.setCourseNo(getCourseNoText().getText());
				newCourse.setCourseName(getCourseNameText().getText());
				newCourse.setProfessor(professorComboBox.getSelectedItem().toString());
			}

			public void makeTableEmpty() {
				getCourseNoText().setText(null);
				getCourseNameText().setText(null);
			}
		});
	}

	private void ComponentLayout() {
		
		GridBagConstraints c = new GridBagConstraints();
		
		putCourseNoOnTable(c);

		putCourseNameOnTable(c);

		putProfessorNameComboBoxOnTable(c);

		putAddCourseButtonOnTable(c);

	}

	public void putCourseNameOnTable(GridBagConstraints c) {
		courseName = new JLabel("Course Name");
		c.weighty = 0.05;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		add(courseName, c);

		courseNameText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		add(courseNameText, c);
	}

	public void putCourseNoOnTable(GridBagConstraints c) {
		courseNo = new JLabel("Course Number");
		c.ipadx = 1;
		c.ipady = 1;
		c.weighty = 0.05;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		add(courseNo, c);

		courseNoText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(courseNoText, c);
	}
	
	public void putProfessorNameComboBoxOnTable(GridBagConstraints c) {
		courseProfessor = new JLabel("professor Name");
		c.weighty = 0.05;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 2;
		add(courseProfessor, c);

		professorComboBox = new JComboBox<ProfessorUI>();

		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		add(professorComboBox, c);
	}

	public void putAddCourseButtonOnTable(GridBagConstraints c) {
		addCourseBtn = new JButton("Add Course");
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 4;
		add(addCourseBtn, c);
	}

	public void addNewProfessorToProfessorComboBox(ArrayList<ProfessorUI> proffesorArray) {
		this.professorComboBox.setModel(new DefaultComboBoxModel(proffesorArray.toArray()));
	}
}
