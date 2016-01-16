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

import Professor.ProfessorUI;

public class ProfessorFormPanel extends JPanel {
	private JLabel professorNo;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel phone;
	private JLabel email;
	private JLabel address;
	private JLabel userName;
	private JLabel password;
	private JTextField professorNoText;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField phoneText;
	private JTextField emailText;
	private JTextField addressText;
	private JTextField userNameText;
	private JPasswordField passwordText;
	private JButton addProfessorBtn;
	private ProfessorUI newProfessor;
	private IProfessor iProfessor;

	public JTextField getProfessorNoText() {
		return professorNoText;
	}

	public void setProfessorNoText(JTextField professorNoText) {
		this.professorNoText = professorNoText;
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


	public void setiProfessor(IProfessor iProfessor) {
		this.iProfessor = iProfessor;
	}

	ProfessorFormPanel() {
		Dimension dim = new Dimension();
		dim.height = 200;
		dim.width = 700;
		setPreferredSize(dim);
		setMinimumSize(dim);
		TitledBorder professorTitledBorder = new TitledBorder(
				"Professor Information");
		EtchedBorder innerBorder = new EtchedBorder(2);
		setBorder(BorderFactory.createCompoundBorder(professorTitledBorder,
				innerBorder));
		setLayout(new GridBagLayout());
		ComponentLayout();

		addProfessorBtn.addActionListener(new ActionListener() {
			String emptyHintHolder="";
			String notMatchedFormatHintHolder="";
			String isDuplicate="";
			@Override
			public void actionPerformed(ActionEvent in) {
//				checknecessaryFieldsfilling();
//				checkMatchFieldsFormat();
				checkProfessorDuplication();
//				if (!emptyHintHolder.isEmpty()) {
//					JOptionPane.showMessageDialog(ProfessorFormPanel.this, emptyHintHolder, "Necessary Fields did not fill", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
//				}
//				else if (!notMatchedFormatHintHolder.isEmpty()) {
//					JOptionPane.showMessageDialog(ProfessorFormPanel.this, notMatchedFormatHintHolder, "Incorrect format", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
//				}
//				else
				if (!isDuplicate.isEmpty()){
					JOptionPane.showMessageDialog(ProfessorFormPanel.this, isDuplicate, "Duplicated Professor Number", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
				}else {
					makeNewProffesor();
					DataUtil.proffesorArray.add(newProfessor);
					iProfessor.setProfessor(newProfessor);
					EmptyProfessorForm();
				}
			}
			
			public void checkMatchFieldsFormat() {
				notMatchedFormatHintHolder="";
				if (!professorNoText.getText().matches("\\d{9}")) {
					notMatchedFormatHintHolder = "Professor number is not valid\n";
				}if (!firstNameText.getText().matches("[a-zA-Z]+")) {
					notMatchedFormatHintHolder += "First name is not valid\n";
				}if (!lastNameText.getText().matches("[a-zA-Z]+")) {
					notMatchedFormatHintHolder += "Last name is not valid\n";
				}if (!emailText.getText().isEmpty() && !emailText.getText().matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]{2,3}")) {
					notMatchedFormatHintHolder += "Email is not valid\n";
				}if (!phoneText.getText().isEmpty() && !phone.getText().matches("[0-9]{3}-[0-9]{8}")) {
					notMatchedFormatHintHolder += "Phone number is not valid\n";
				}
			}
			
			public void checknecessaryFieldsfilling() {
				emptyHintHolder="";
				if (professorNoText.getText().isEmpty()) {
					emptyHintHolder = "Professor number is empty\n";
				}if (firstNameText.getText().isEmpty()) {
					emptyHintHolder += "First name is empty\n";
				}if (lastNameText.getText().isEmpty()) {
					emptyHintHolder += "Last name is empty\n";
				}if (userNameText.getText().isEmpty()) {
					emptyHintHolder += "User name is empty\n";
				}if (passwordText.getText().isEmpty()) {
					emptyHintHolder += "Password is empty";
				}
			}

			public void checkProfessorDuplication() {
				isDuplicate="";
				for (int i = 0; i < DataUtil.proffesorArray.size(); i++) {
					if (DataUtil.proffesorArray.get(i).getProfessorNo().equals(getProfessorNoText().getText())){
						isDuplicate = "Professor number is duplicated";
					}
				}
			}

			public void EmptyProfessorForm() {
				getProfessorNoText().setText(null);
				getFirstNameText().setText(null);
				getLastNameText().setText(null);
				getAddressText().setText(null);
				getPhoneText().setText(null);
				getEmailText().setText(null);
				getPasswordText().setText(null);
				getUserNameText().setText(null);
			}
			
			public void makeNewProffesor() {
				newProfessor = new ProfessorUI();
				newProfessor.setProfessorNo(getProfessorNoText().getText());
				newProfessor.setFirstName(getFirstNameText().getText());
				newProfessor.setLastName(getLastNameText().getText());
				newProfessor.setAddress(getAddressText().getText());
				newProfessor.setPhone(getPhoneText().getText());
				newProfessor.setEmail(getEmailText().getText());
				newProfessor.setPassword(getPasswordText().getText());
				newProfessor.setUserName(getUserNameText().getText());
			}
		});
	}

	private void ComponentLayout() {
		GridBagConstraints c = putProfessorNoOnForm();
		putFirstNameOnForm(c);
		putLastNameOnForm(c);
		putEmailOnForm(c);
		putPhoneOnForm(c);
		putUserNameOnForm(c);
		putPasswordOnForm(c);
		putAdrressOnForm(c);
		PutAddProfessorButtonOnForm(c);
	}

	public void PutAddProfessorButtonOnForm(GridBagConstraints c) {
		addProfessorBtn = new JButton("Add Professor");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 4;
		c.gridy = 5;
		add(addProfessorBtn, c);
	}

	public void putAdrressOnForm(GridBagConstraints c) {
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
		password = new JLabel("Password");
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
		userName = new JLabel("UserName");
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
		lastName = new JLabel("LastName");
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
		firstName = new JLabel("FirstName");
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

	public GridBagConstraints putProfessorNoOnForm() {
		GridBagConstraints c = new GridBagConstraints();
		professorNo = new JLabel("ProfessorNo");
		c.ipadx = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		add(professorNo, c);

		professorNoText = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(professorNoText, c);
		return c;
	}

}
