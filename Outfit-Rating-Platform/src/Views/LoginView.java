package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel loginWarningMessageLabel;
	private JButton loginButton;
	private JLabel loginLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	public LoginView() {
		this.setTitle("Login View");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		this.contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.loginButton = new JButton("Login");
		loginButton.setBackground(new Color(5, 190, 5));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(155,221,100, 29);
		contentPane.add(loginButton);
		
		this.loginLabel = new JLabel("Login Form");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loginLabel.setBackground(new Color(111, 111, 111));
		loginLabel.setBounds(155, 37, 120, 50);
		contentPane.add(loginLabel);
		
		this.loginWarningMessageLabel = new JLabel("");
		loginWarningMessageLabel.setBackground(Color.WHITE);
		loginWarningMessageLabel.setForeground(Color.RED);
		loginWarningMessageLabel.setBounds(101, 275, 256, 30);
		contentPane.add(loginWarningMessageLabel);
		
		this.usernameField = new JTextField();
		usernameField.setBounds(125, 122, 200, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
	
		this.passwordField = new JPasswordField();
		passwordField.setBounds(125, 162, 200, 30);
		contentPane.add(passwordField);

		this.usernameLabel = new JLabel("Username :");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setBounds(39, 122, 76, 29);
		contentPane.add(usernameLabel);
		
		this.passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(39, 162, 76, 29);
		contentPane.add(passwordLabel);
	}
	
	public void addLoginListener(ActionListener loginListener) {
		this.loginButton.addActionListener(loginListener);
	}
	
	public void addUsernameFieldListener(DocumentListener usernameFieldListener) {
		this.usernameField.getDocument().addDocumentListener(usernameFieldListener);
	}
	
	public void addPasswordfieldListener(DocumentListener passwordFieldListener) {
		this.passwordField.getDocument().addDocumentListener(passwordFieldListener);
	}

	public void showLoginWarningMessage(String string) {
		this.loginWarningMessageLabel.setText(string);
	}

	public char[] getPassword() {
		return this.passwordField.getPassword();
	}
	
	public String getUsername() {
		return this.usernameField.getText();
	}
}
