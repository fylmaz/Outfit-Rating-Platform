package Business;

import java.awt.Window;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import Controllers.CollectionController;
import Controllers.CommentController;
import Controllers.LoginController;
import Controllers.OutfitController;
import Controllers.UserController;
import Models.CollectionModel;
import Models.CommentModel;
import Models.OutfitModel;
import Models.UserModel;
import Repositories.OutfitRepository;
import Repositories.RepositoryHandler;
import Repositories.UserRepository;
import Services.OutfitService;
import Services.UserService;
import Views.CollectionView;
import Views.CommentView;
import Views.LoginView;
import Views.OutfitView;
import Views.StatsView;
import Views.UserView;

/**
 * It acts like a mediator to manage interactions between views, controllers, models.
 * It creates,shows new views, and creates controllers of the views .
 *
 */
public class AppManager {
	//it will observe all outfits and users to update stats
	private StatsView statsView;
	
	private RepositoryHandler repositoryHandler;
	private UserModel onlineUser;
	private UserService userService;
	private UserRepository userRepository;
	private LoginController loginController;
	private LoginView loginView;
	private OutfitRepository outfitRepository;
	private OutfitService outfitService;
	

	
	public AppManager() throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		this.statsView = new StatsView(this);
		statsView.setVisible(false);
		this.repositoryHandler = new RepositoryHandler(this.statsView);
		this.userRepository = repositoryHandler.getUserRepository();
		this.outfitRepository = repositoryHandler.getOutfitRepository();
		this.outfitService = new OutfitService(outfitRepository);
		this.userService = new UserService(userRepository);
		this.onlineUser = null;
		this.loginView = new LoginView();
		this.loginController = new LoginController(loginView, this);
		
		//it will initalize stats according to readed data from the files
		this.statsView.updateStats();

	}

	public void run() {
		this.loginView.setVisible(true);
	}
	
	public UserModel getOnlineUser() {
		return onlineUser;
 	}
	
	public void setOnlineUser(UserModel onlineUser) {
		this.onlineUser = onlineUser;
		UserView newView = new UserView(this);
		UserController newConroller = new UserController(this, onlineUser, newView);
		newView.setVisible(true);
	}
	
	public void showNewUserView(UserModel selectedUser) {
		//Online User View will be shown for 1 time and it will not be closed until logout
		if(selectedUser != this.onlineUser) {
			UserView view = new UserView(this);
			UserController controller = new UserController(this, selectedUser, view);
			view.setVisible(true);
		}
	}

	public void showNewCollectionView(CollectionModel selectedCollection) {
		CollectionView view = new CollectionView(this);
		CollectionController controller = new CollectionController(this, selectedCollection, view);
		view.setVisible(true);	
	}

	public void showNewOutfitView(OutfitModel selectedOutfit) {
		OutfitView view = new OutfitView(this);
		OutfitController controller = new OutfitController(this, selectedOutfit, view);
		view.setVisible(true);
	}

	public void showNewCommentView(CommentModel selectedComment) {
		CommentView view = new CommentView();
		CommentController controller = new CommentController(selectedComment, view);
		view.setVisible(true);
	}
	
	public void logout() {
		for(Window window: Window.getWindows()) {
			window.dispose();
		}
		this.onlineUser = null;
		this.loginView.setVisible(true); 
		
	}

	public UserService getUserService() {
		return userService;
	}


	public OutfitService getOutfitService() {
		return outfitService;
	}

	public void showStatsView() {
		this.statsView.setVisible(true);
	}
	
}
