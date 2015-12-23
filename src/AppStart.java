import javax.swing.SwingUtilities;

public class AppStart {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AdminView.MainFrame();
			//new Student.StudentFrame();
			}
		});

	}

}
