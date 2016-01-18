package AdminView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import Course.CourseUI;
import Professor.ProfessorUI;
import Student.StudentUI;
import Controller.Controller;

public class MainFrame extends JFrame {

	private JTabbedPane tabedPanel;
	private JSplitPane splitPane;
	private StudentFormPanel studentFormPanel;
	private StudentTablePanel studentTablePanel;
	private CourseFormPanel courseFormPanel;
	private CourseTablePanel courseTablePanel;
	private ProfessorFormPanel professorFormPanel;
	private ProfessorTablePanel professorTablePanel;
	private ArrayList<StudentUI> arrayStudent;
	private ArrayList<ProfessorUI> arrayProfessor;
	private ArrayList<CourseUI> arrayCourse;
	private ToolBar toolBar;
	private Controller controller;

	public MainFrame() {
		super("Education System");
		setBounds(700, 200, 900, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setJMenuBar(createMenu());

		controller = new Controller();

		arrayStudent = new ArrayList<StudentUI>();
		arrayProfessor = new ArrayList<ProfessorUI>();
		arrayCourse = new ArrayList<CourseUI>();

		tabedPanel = new JTabbedPane();
		studentFormPanel = new StudentFormPanel();
		studentTablePanel = new StudentTablePanel();
		courseFormPanel = new CourseFormPanel();
		courseTablePanel = new CourseTablePanel();
		professorFormPanel = new ProfessorFormPanel();
		professorTablePanel = new ProfessorTablePanel();
		toolBar = new ToolBar();
//		studentFormPanel.setVisible(false);
//		studentTablePanel.setVisible(false);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,professorFormPanel, professorTablePanel);
		splitPane.setOneTouchExpandable(true);
		tabedPanel.add("Professor", splitPane);

		try {
			arrayProfessor.addAll(controller.loadProfDBData());
			DataUtil.proffesorArray.addAll(arrayProfessor);
			professorTablePanel.setModelDataSourse(arrayProfessor);
			professorTablePanel.refreshTable();
			courseFormPanel.addNewProfessorToProfessorComboBox(DataUtil.proffesorArray);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, studentFormPanel,studentTablePanel);
		splitPane.setOneTouchExpandable(true);
		tabedPanel.add("Student", splitPane);

		try {
			arrayStudent.addAll(controller.loadStudentDBData());
			DataUtil.studentArray.addAll(arrayStudent);
			studentTablePanel.setModelDataSourse(arrayStudent);
			studentTablePanel.refreshTable();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, courseFormPanel,courseTablePanel);
		splitPane.setOneTouchExpandable(true);
		tabedPanel.add("Course", splitPane);

		try {
			arrayCourse.addAll(controller.loadCourseDBData());
			DataUtil.courseArray.addAll(arrayCourse);
			courseTablePanel.setModelDataSourse(arrayCourse);
			studentTablePanel.refreshTable();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		studentFormPanel.setiStudet(new IStudent() {
			@Override
			public void setStudent(StudentUI newStudent) {
				arrayStudent.add(newStudent);
				studentTablePanel.setModelDataSourse(arrayStudent);
				studentTablePanel.refreshTable();
			}
		});

		courseFormPanel.setiCourse(new ICourse() {
			@Override
			public void setCourse(CourseUI newCourse) {
				arrayCourse.add(newCourse);
				courseTablePanel.setModelDataSourse(arrayCourse);
				courseTablePanel.refreshTable();
			}
		});

		professorFormPanel.setiProfessor(new IProfessor() {
			@Override
			public void setProfessor(ProfessorUI newProfessorEnterance) {
				arrayProfessor.add(newProfessorEnterance);
				professorTablePanel.setModelDataSourse(arrayProfessor);
				professorTablePanel.refreshTable();
				courseFormPanel.addNewProfessorToProfessorComboBox(DataUtil.proffesorArray);
			}
		});

		toolBar.setToolbarListener(new ToolbarListener() {
			@Override
			public void saveEventOccured() {
				try {
					controller.save(arrayProfessor,arrayStudent,arrayCourse);
					} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}



			@Override
			public void refreshEventOccured() {

			}
		});

		studentTablePanel.setStudentTableListener(new tablePanelListener() {
			@Override
			public void tableRowDeleted(int row) {
				arrayStudent.remove(row);
			}
		});

		professorTablePanel.setProfessorTableListener(new tablePanelListener() {
			@Override
			public void tableRowDeleted(int row) {
				arrayProfessor.remove(row);
			}
		});

		courseTablePanel.setCourseTableListener(new tablePanelListener() {
			@Override
			public void tableRowDeleted(int row) {
				arrayCourse.remove(row);
			}
		});

		add(tabedPanel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.PAGE_START);
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu colorMenu = new JMenu("Color");
		JMenu pageMenu = new JMenu("Page");
		JMenu prefrences = new JMenu("Prefrences");
		JMenuItem saveToDB = new JMenuItem("SaveToDB");
		JMenuItem loadFromDB = new JMenuItem("Load From DB");
		JMenuItem RefExit = new JMenuItem("Ref Exit");
		JMenuItem setDbData = new JMenuItem("Set DB Data");
		JMenuItem changeTheme = new JMenuItem("Change Theme");
		JMenuItem changePassword = new JMenuItem("Change Password");

		JRadioButtonMenuItem plainItem = new JRadioButtonMenuItem("Plain");
		JRadioButtonMenuItem blueItem = new JRadioButtonMenuItem("Blue");
		JRadioButtonMenuItem greenItem = new JRadioButtonMenuItem("Green");
		ButtonGroup buttonGroup = new ButtonGroup();

		JCheckBoxMenuItem studentPage = new JCheckBoxMenuItem("Student");
		JCheckBoxMenuItem coursePage = new JCheckBoxMenuItem("Course");
		JCheckBoxMenuItem professorPage = new JCheckBoxMenuItem("Professor");

		plainItem.setSelected(true);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(colorMenu);
		menuBar.add(pageMenu);

		fileMenu.add(saveToDB);
		fileMenu.add(loadFromDB);
		fileMenu.add(RefExit);

		buttonGroup.add(plainItem);
		buttonGroup.add(blueItem);
		buttonGroup.add(greenItem);

		editMenu.add(prefrences);
		editMenu.add(changePassword);

		prefrences.add(setDbData);
		prefrences.add(changeTheme);

		colorMenu.add(plainItem);
		colorMenu.add(blueItem);
		colorMenu.add(greenItem);

		pageMenu.add(studentPage);
		pageMenu.add(coursePage);
		pageMenu.add(professorPage);

		studentPage.setSelected(false);

		saveToDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		loadFromDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		RefExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		setDbData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		changeTheme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));

		fileMenu.setMnemonic(KeyEvent.VK_F);
		loadFromDB.setMnemonic(KeyEvent.VK_L);
		RefExit.setMnemonic(KeyEvent.VK_X);
		editMenu.setMnemonic(KeyEvent.VK_E);
		prefrences.setMnemonic(KeyEvent.VK_F);
		setDbData.setMnemonic(KeyEvent.VK_D);
		changeTheme.setMnemonic(KeyEvent.VK_T);
		colorMenu.setMnemonic(KeyEvent.VK_R);
		pageMenu.setMnemonic(KeyEvent.VK_P);

		saveToDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent in) {
				System.out.println("Save To DB");
			}
		});

		RefExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		plainItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent in) {
				tabedPanel.setBackground(Color.lightGray);
			}
		});

		blueItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent in) {
				tabedPanel.setBackground(Color.blue);

			}
		});

		greenItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent in) {
				tabedPanel.setBackground(Color.green);

			}
		});

		studentPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent in) {
				studentFormPanel.setVisible(true);
				studentTablePanel.setVisible(true);
			}
		});
		return menuBar;
	}
}
