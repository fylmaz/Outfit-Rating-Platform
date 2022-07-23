package Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Business.AppManager;
import Models.UserModel;
import Views.LoginView;

public class LoginController {
	private AppManager appManager;
	private LoginView view;
	
	public LoginController(LoginView view,AppManager appManager) {
		this.appManager = appManager;
		this.view = view;
		
		this.view.addLoginListener(new LoginListener());
		this.view.addUsernameFieldListener(new UsernameFieldListener());
		this.view.addPasswordfieldListener(new PasswordFieldListener());	
	}
		
	/**
	 * To check whether password is valid or not
	 */
	class PasswordFieldListener implements DocumentListener{
		  public void changedUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  public void removeUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  public void insertUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  
		  public void warn() {
			  if(!isUserPasswordValid()) {
				  view.showLoginWarningMessage("Password lentgh must be greater than 6!");
			  }else {
				  view.showLoginWarningMessage("");
			  }  
		  }
	}
	
	/**
	 * To check whether username is valid or not
	 */
	class UsernameFieldListener implements DocumentListener{
		  public void changedUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  public void removeUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  public void insertUpdate(DocumentEvent e) {
			  this.warn();
		  }
		  
		  public void warn() {
			  if(!isUserNameValid()) {
				  view.showLoginWarningMessage("Username lentgh must be greater than 6!");
			  }else {
				  view.showLoginWarningMessage("");
			  }  
		  }
	}
	
	/**
	 * To make logged in the user if given password and username are valid
	 */
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isLoginFormValid()) {
				setLoginFormWarningMessage("The Form is Invalid!");
			}else {
				login();
			}	
		}		
	}
	
	
	public boolean isUserNameValid() {
		 String username = this.view.getUsername();
		 if(username.length() < 6) {
		 	return false;
		 }
		  return true;
	 }
	  
	  public boolean isUserPasswordValid() {
		 String password = String.valueOf(this.view.getPassword());
		 if(password.length() < 6) {
		 	return false;
		 } 
		  return true;
	  }
	  
	  private boolean isLoginFormValid() {
		 return this.isUserNameValid() && this.isUserPasswordValid();
	  }
	
	  public void setLoginFormWarningMessage(String message) {
		  this.view.showLoginWarningMessage(message);
	  }
	  
	
	public void login() {
		String username = this.view.getUsername();
		String password = String.valueOf(this.view.getPassword());
		UserModel user = this.appManager.getUserService().getUserByUsername(username);
		if(user != null && user.getPassword().equals(password) ) {
			appManager.setOnlineUser(user);
			view.setVisible(false);
		}else {
			view.showLoginWarningMessage("Invalid password or username");
		}	
	}

	
	
	
}
