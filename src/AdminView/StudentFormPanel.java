package AdminView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Student.StudentUI;

public class StudentFormPanel extends JPanel {

	private IStudent iStudet;

	public void setiStudet(IStudent iStudet) {
		this.iStudet = iStudet;
	}

	private JLabel studentNo;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel phone;
	private JLabel email;
	private JLabel address;
	private JLabel userName;
	private JLabel password;
	private JTextField studentNoText;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField phoneText;
	private JTextField emailText;
	private JTextField addressText;
	private JTextField userNameText;
	private JPasswordField passwordText;
	private JButton addStudentBtn;
	private StudentUI newStudent;

	public JTextField getStudentNoText() {
		return studentNoText;
	}

	public void setStudentNoText(JTextField studentNoText) {
		this.studentNoText = studentNoText;
	}

	public JTextField getFirstNameText() {
		return firstNameText;
	}

	public void setFirstNameText(JTextField firstNameText) {
		this.firstNameText = firstNameText;
	}

	public JTextField getLastNameText() {
		return lastNameText;
	}

	public void setLastNameText(JTextField lastNameText) {
		this.lastNameText = lastNameText;
	}

	public JTextField getPhoneText() {
		return phoneText;
	}

	public void setPhoneText(JTextField phoneText) {
		this.phoneText = phoneText;
	}

	public JTextField getEmailText() {
		return emailText;
	}

	public void setEmailText(JTextField emailText) {
		this.emailText = emailText;
	}

	public JTextField getAddressText() {
		return addressText;
	}

	public void setAddressText(JTextField addressText) {
		this.addressText = addressText;
	}

	public JTextField getUserNameText() {
		return userNameText;
	}

	public void setUserNameText(JTextField userNameText) {
		this.userNameText = userNameText;
	}

	public JPasswordField getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(JPasswordField passwordText) {
		this.passwordText = passwordText;
	}

	StudentFormPanel() {
		Dimension dim = getPreferredSize();
		dim.height = 200;
		setPreferredSize(dim);
		setMinimumSize(dim);
		TitledBorder outerBorder = new TitledBorder("Student Information");
		EtchedBorder innerBorder = new EtchedBorder(2);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setLayout(new GridBagLayout());
		ComponentLayout();
		addStudentBtn.addActionListener(new ActionListener() {
			String emptyHintHolder="";
			String notMatchedFormatHintHolder="";

			@Override
			public void actionPerformed(ActionEvent in) {
				
//					checknecessaryFieldsfilling();
//					checkMatchFieldsFormat();					
//				if (emptyHintHolder.length() > 0) {
//					JOptionPane.showMessageDialog(StudentFormPanel.this,emptyHintHolder, "Necessary Fields did not fill", JOptionPane.OK_OPTION| JOptionPane.ERROR_MESSAGE);
//					emptyHintHolder = "";
//				} else if (notMatchedFormatHintHolder.length() > 0) {
//					JOptionPane.showMessageDialog(StudentFormPanel.this,notMatchedFormatHintHolder, "Incorect format", JOptionPane.OK_OPTION| JOptionPane.ERROR_MESSAGE);
//					notMatchedFormatHintHolder = "";
//				}else {
					makeNewObjectOfStudent();
					iStudet.setStudent(newStudent);
					makeTableEmpty();
//				}	
			}

			public void checkMatchFieldsFormat() {
				notMatchedFormatHintHolder = "";
				if (!getStudentNoText().getText().matches("\\d{9}")) {
					notMatchedFormatHintHolder = "Student number is not valid\n";
				}if (!getFirstNameText().getText().matches("[a-zA-Z]+")) {
					notMatchedFormatHintHolder += "First name is not valid\n";
				}if (!getLastNameText().getText().matches("[a-zA-Z]+")) {
					notMatchedFormatHintHolder += "Last name is not valid\n";
				}if (!getEmailText().getText().equals("") && !getEmailText().getText().matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]{2,3}")) {
					notMatchedFormatHintHolder += "Email is not is not valid\n";
				}if (!getPhoneText().getText().equals("") && !getPhoneText().getText().matches("\\d{3}-\\d{8}") ) {
					notMatchedFormatHintHolder += "Phone number is not valid\n";
				}
			}

			public void checknecessaryFieldsfilling() {
				notMatchedFormatHintHolder = "";
				if (getStudentNoText().getText().length() == 0) {
					emptyHintHolder = "Student Nuumber is empty\n";
				}if (firstNameText.getText().length() == 0) {
					emptyHintHolder += "First name is empty\n";
				} if (lastNameText.getText().length() == 0) {
					emptyHintHolder += "Last name is empty\n";
				}if (getUserNameText().getText().length() == 0) {
					emptyHintHolder += "User name is empty\n";
				}if (getPasswordText().getPassword().length == 0) {
					emptyHintHolder += "Password is empty";
				}
			}

			public void makeTableEmpty() {
				getStudentNoText().setText(null);
				getFirstNameText().setText(null);
				getLastNameText().setText(null);
				getAddressText().setText(null);
				getPhoneText().setText(null);
				getEmailText().setText(null);
				getPasswordText().setText(null);
				getUserNameText().setText(null);
			}

			public void makeNewObjectOfStudent() {
				newStudent = new StudentUI();
				newStudent.setStudentNo(getStudentNoText().getText());
				newStudent.setFirstName(getFirstNameText().getText());
				newStudent.setLastName(getLastNameText().getText());
				newStudent.setAddress(getAddressText().getText());
				newStudent.setPhone(getPhoneText().getText());
				newStudent.setEmail(getEmailText().getText());
				newStudent.setPassword(getPasswordText().getPassword());
				newStudent.setUserName(getUserNameText().getText());
			}
		});
	}

	private void ComponentLayout() {
		GridBagConstraints c = new GridBagConstraints();
		putStudentNoOnForm(c);

		putFirstNameOnForm(c);

		putLastNameOnForm(c);

		putEmailOnForm(c);

		putPhoneOnForm(c);

		putUserNameOnForm(c);

		putPasswordOnForm(c);

		putAddressOnForm(c);

		putAddStudentButtonOnForm(c);
	}

	public void putAddStudentButtonOnForm(GridBagConstraints c) {
		addStudentBtn = new JButton("Add Student");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 4;
		c.gridy = 5;
		add(addStudentBtn, c);
	}

	public void putAddressOnForm(GridBagConstraints c) {
		address = new JLabel("Address");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 4;
		add(address, c);

		addressText = new JTextField(20);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 4;
		add(addressText, c);
	}

	public void putPasswordOnForm(GridBagConstraints c) {
		password = new JLabel("Password *");
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.25;
		c.gridx = 2;
		c.gridy = 3;
		add(password, c);

		passwordText = new JPasswordField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(passwordText, c);
	}

	public void putUserNameOnForm(GridBagConstraints c) {
		userName = new JLabel("User Name *");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 3;
		add(userName, c);

		userNameText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 3;
		add(userNameText, c);
	}

	public void putPhoneOnForm(GridBagConstraints c) {
		phone = new JLabel("Phone");
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 2;
		add(phone, c);

		phoneText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(phoneText, c);
	}

	public void putEmailOnForm(GridBagConstraints c) {
		email = new JLabel("email");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		add(email, c);

		emailText = new JTextField(20);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 2;
		add(emailText, c);
	}

	public void putLastNameOnForm(GridBagConstraints c) {
		lastName = new JLabel("Last Name *");
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 1;
		add(lastName, c);

		lastNameText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(lastNameText, c);
	}

	public void putFirstNameOnForm(GridBagConstraints c) {
		firstName = new JLabel("First Name *");
		c.weighty = 0.05;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		add(firstName, c);

		firstNameText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		add(firstNameText, c);
	}

	public void putStudentNoOnForm(GridBagConstraints c) {
		studentNo = new JLabel("Student No *");
		c.ipadx = 10;
		c.weighty = 0.05;
		c.weightx = 1;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		add(studentNo, c);

		studentNoText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(studentNoText, c);
	}

}
