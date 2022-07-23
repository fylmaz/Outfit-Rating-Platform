package Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import Business.AppManager;
import Interfaces.IObservable;
import Interfaces.IObserver;
import Models.CollectionModel;
import Models.UserModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import javax.swing.JList;
import javax.swing.DefaultListModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserView extends JFrame implements IObserver {
	private AppManager appManager;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel profilePhotoLabel;
	private JLabel usernameLabel;
	private JLabel nameAndSurnameLabel;
	private JLabel followersLabel;
	private JLabel followingsLabel;
	private JLabel myCollectionsLabel;
	private JPanel myCollections;
	private JPanel collectionsPanel;
	private JButton previousPageButton;
	private JButton nextPageButton;
	private JLabel numberOfPagesLabel;
	private JLabel currentPageNoLabel;
	private int collectionCounter = 0;
	private int currentPageNo = 0;
	private JList followersList;
	private JList followingsList;
	private JScrollPane followersListPane;
	private JScrollPane followingsListPane;
	private JButton logoutButton;
	private JButton followUnfollowButton;
	private JLabel allUsersLabel;
	private JScrollPane allUsersListPane;
	private JList allUsersList;
	private JLabel collectionsMessageLabel;
	private JButton createCollectionButton;
	private JTextArea newCollectionName;
	private JButton finishCollectionCreationButton;
	private JLabel popupMessageLabel;
	private JButton seeAllOutfitsButton;
	private JFrame createCollectionPopup;
	private JFrame allOutfitsPopup;
	private JScrollPane allOutfitsListPane;
	private JList allOutfitsList;
	private JLabel allOutfitsLabel;
	private JButton seeOutfitDetailsPopupButton;
	private JButton StatsButton;

	public UserView(AppManager appManager) {
		this.setTitle("User View");
		this.appManager = appManager; 
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.panel = new JPanel();
		panel.setBounds(0, 0, 800, 100);
		panel.setBackground(new Color(0,80,130));
		contentPane.add(panel);
		panel.setLayout(null);
		
		this.profilePhotoLabel = new JLabel("");
		profilePhotoLabel.setBounds(0, 0, 100, 100);
		panel.add(profilePhotoLabel);
		
		this.usernameLabel = new JLabel("");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setBounds(110, 48, 137, 20);
		panel.add(usernameLabel);
		
		this.nameAndSurnameLabel = new JLabel("");
		nameAndSurnameLabel.setForeground(Color.WHITE);
		nameAndSurnameLabel.setBounds(110, 70, 137, 20);
		panel.add(nameAndSurnameLabel);
		
		allUsersListPane = new JScrollPane(); 
		allUsersListPane.setBounds(553, 20, 100, 80);
		panel.add(allUsersListPane);
		
		allUsersList = new JList();
		allUsersListPane.setViewportView(allUsersList);
		
		this.allUsersLabel = new JLabel("");
		allUsersLabel.setBackground(Color.LIGHT_GRAY);
		allUsersListPane.setColumnHeaderView(allUsersLabel);
		allUsersLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		allUsersLabel.setForeground(Color.BLACK);
		//allUsersListPane.setVisible(false); 
		
		followersListPane = new JScrollPane();
		followersListPane.setBounds(341, 20, 100, 80);
		panel.add(followersListPane);
		
		followersList = new JList();
		followersListPane.setViewportView(followersList);
		
		this.followersLabel = new JLabel("");
		followersLabel.setBackground(Color.LIGHT_GRAY);
		followersListPane.setColumnHeaderView(followersLabel);
		followersLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		followersLabel.setForeground(Color.BLACK);
		//followersListPane.setVisible(false);
		
		followingsListPane = new JScrollPane();
		followingsListPane.setBounds(447, 20, 100, 80);
		panel.add(followingsListPane);
		
		followingsList = new JList();
		followingsListPane.setViewportView(followingsList);
		
		this.followingsLabel = new JLabel("");
		followingsLabel.setBackground(Color.LIGHT_GRAY);
		followingsListPane.setColumnHeaderView(followingsLabel);
		followingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		followingsLabel.setForeground(Color.BLACK);
		//followingsListPane.setVisible(false);
		
	    logoutButton = new JButton("Logout");
		logoutButton.setBackground(Color.RED);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBounds(110, 24, 85, 21);
		panel.add(logoutButton);
		
		followUnfollowButton = new JButton("");
		followUnfollowButton.setBounds(110, 24, 85, 21);
		panel.add(followUnfollowButton); 

		
		this.myCollections = new JPanel();
		myCollections.setBounds(100, 125, 600, 414);
		myCollections.setBackground(new Color(0,80,130));
		contentPane.add(myCollections);
		myCollections.setLayout(null);
		
		this.myCollectionsLabel = new JLabel("My Collections");
		myCollectionsLabel.setForeground(Color.WHITE);
		myCollectionsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		myCollectionsLabel.setBounds(226, 10, 219, 21);
		myCollections.add(myCollectionsLabel);
		
		this.collectionsPanel = new JPanel();		
		this.collectionsPanel.setName("collectionsPanel-0");
		collectionsPanel.setBounds(0, 50, 600, 310);
		collectionsPanel.setBackground(Color.GRAY);
		collectionsPanel.setForeground(Color.WHITE);
		myCollections.add(collectionsPanel);
		collectionsPanel.setLayout(null);
		
		previousPageButton = new JButton("Previous Page");
		previousPageButton.setBounds(10, 370, 120, 20);
		myCollections.add(previousPageButton);
		
		nextPageButton = new JButton("Next Page");
		nextPageButton.setBounds(470, 370, 120, 20);
		myCollections.add(nextPageButton);
		
		numberOfPagesLabel = new JLabel("Number Of Pages: 1");
		numberOfPagesLabel.setForeground(Color.WHITE);
		numberOfPagesLabel.setBounds(177, 370, 134, 13);
		myCollections.add(numberOfPagesLabel);
		
		currentPageNoLabel = new JLabel("");
		currentPageNoLabel.setForeground(Color.WHITE);
		currentPageNoLabel.setBounds(311, 370, 120, 13);
		myCollections.add(currentPageNoLabel);
		currentPageNoLabel.setText("Current Page No: " + (this.currentPageNo + 1) );
		
		collectionsMessageLabel = new JLabel("");
		collectionsMessageLabel.setForeground(Color.GREEN);
		collectionsMessageLabel.setBounds(177, 393, 219, 13);
		myCollections.add(collectionsMessageLabel);
		
		createCollectionButton = new JButton("Create a New Collection");
		createCollectionButton.setForeground(Color.WHITE);
		createCollectionButton.setBackground(Color.GREEN);
		createCollectionButton.setBounds(424, 30, 176, 21);
		createCollectionButton.setVisible(false);
		myCollections.add(createCollectionButton);
		
		seeAllOutfitsButton = new JButton("See All Outfits");
		seeAllOutfitsButton.setBackground(Color.GREEN);
		seeAllOutfitsButton.setForeground(Color.WHITE);
		seeAllOutfitsButton.setBounds(0, 30, 176, 21);
		seeAllOutfitsButton.setVisible(false);
		myCollections.add(seeAllOutfitsButton);
		
        createCollectionPopup = new JFrame("New Collection Name");
        createCollectionPopup.getContentPane().setBackground(new Color(0,69,107)); 
        createCollectionPopup.setSize(300, 175);
        createCollectionPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createCollectionPopup.setVisible(false);
        createCollectionPopup.setResizable(false);
        createCollectionPopup.getContentPane().setLayout(null);
               
        newCollectionName = new JTextArea();
        newCollectionName.setBounds(10, 20, 265, 20);
        createCollectionPopup.getContentPane().add(newCollectionName);
        
        finishCollectionCreationButton = new JButton("Create");
        finishCollectionCreationButton.setForeground(Color.WHITE);
        finishCollectionCreationButton.setBackground(Color.GREEN);
        finishCollectionCreationButton.setBounds(100, 60, 100, 20);
        createCollectionPopup.getContentPane().add(finishCollectionCreationButton);
        
        popupMessageLabel = new JLabel();
        popupMessageLabel.setText("");
        popupMessageLabel.setBounds(10, 80, 265, 20);
        popupMessageLabel.setForeground(Color.RED);
        createCollectionPopup.getContentPane().add(popupMessageLabel);
        
        
        allOutfitsPopup = new JFrame("All Outfits");
        allOutfitsPopup.getContentPane().setBackground(new Color(0,69,107)); 
        allOutfitsPopup.setSize(300, 370);
        allOutfitsPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        allOutfitsPopup.setVisible(false);
        allOutfitsPopup.setResizable(false);
        
        allOutfitsPopup.getContentPane().setLayout(null);
        
		this.allOutfitsListPane = new JScrollPane();
		allOutfitsListPane.setBounds(10, 10, 265, 250);
		allOutfitsPopup.getContentPane().add(allOutfitsListPane);
		
		this.allOutfitsList = new JList();
		allOutfitsListPane.setViewportView(allOutfitsList);
		
		this.allOutfitsLabel = new JLabel("All Outfits");
		allOutfitsLabel.setForeground(Color.BLACK);
		allOutfitsLabel.setBackground(Color.LIGHT_GRAY);
		allOutfitsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		allOutfitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allOutfitsListPane.setColumnHeaderView(allOutfitsLabel);
		
		seeOutfitDetailsPopupButton = new JButton("See Outfit Details");
		seeOutfitDetailsPopupButton.setBounds(75, 275, 150, 20);
		seeOutfitDetailsPopupButton.setBackground(Color.GREEN);
		seeOutfitDetailsPopupButton.setForeground(Color.WHITE);
		seeOutfitDetailsPopupButton.setVisible(false);
		allOutfitsPopup.getContentPane().add(seeOutfitDetailsPopupButton);
	
		popupMessageLabel = new JLabel();
		popupMessageLabel.setBounds(10, 300, 300, 20);
		popupMessageLabel.setForeground(Color.WHITE);
		allOutfitsPopup.getContentPane().add(popupMessageLabel);

		
		StatsButton = new JButton("Stats");
		StatsButton.setForeground(Color.WHITE);
		StatsButton.setBackground(Color.ORANGE);
		StatsButton.setBounds(705, 0, 85, 21);
		panel.add(StatsButton);
		    
	}
	
	public void showPreviousPage() {
		JPanel prevPage = this.getCollectionsPanelByPageNo(this.currentPageNo - 1);
		if(prevPage != null) {
			this.getCollectionsPanelByPageNo(currentPageNo).setVisible(false);
			this.setCurrentPageNo(this.currentPageNo + -1);
			prevPage.setVisible(true);
			this.currentPageNoLabel.setText("Current Page No: " + (this.currentPageNo + 1) );
		}
		
	}
	
	private void setCurrentPageNo(int pageNo) {
		this.currentPageNo = pageNo;
	}

	public void showNextPage() {
		JPanel nextPage = this.getCollectionsPanelByPageNo(this.currentPageNo + 1);
		if(nextPage != null) {
			this.getCollectionsPanelByPageNo(currentPageNo).setVisible(false);
			this.setCurrentPageNo(this.currentPageNo + 1);
			nextPage.setVisible(true);
			this.currentPageNoLabel.setText("Current Page No: " + (this.currentPageNo + 1));
		}
		
	}
	
	/**
	 * To show all collections in the owner user view
	 * @param collection name
	 * @param collection id
	 */
	public void addCollectionPanel(String name, int id) {
		JPanel newPanel = new JPanel();
		newPanel.setName("collectionPanel-" + id);
		newPanel.setBounds(10, this.collectionCounter % 5 * 60 + 10, 580, 50);
		newPanel.setLayout(null);
		newPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel newLabel = new JLabel(name);
		newLabel.setForeground(Color.BLACK);
		newPanel.add(newLabel);
		newLabel.setBounds(10, 10, 200, 10);
	
	    if(collectionCounter == 0) {
			this.getCollectionsPanelByPageNo(0).add(newPanel);
		}
		
		else if(collectionCounter % 5 != 0) {
			this.getCollectionsPanelByPageNo(collectionCounter / 5).add(newPanel);
		}
		
		else {
			JPanel newCollectionsPanel = new JPanel();		
			newCollectionsPanel.setName("collectionsPanel-" + (collectionCounter / 5));
			newCollectionsPanel.setBounds(0, 50, 600, 310);
			newCollectionsPanel.setBackground(Color.GRAY);
			newCollectionsPanel.setForeground(Color.WHITE);
			newCollectionsPanel.setLayout(null);
			newCollectionsPanel.setVisible(false);
			this.myCollections.add(newCollectionsPanel);	
			newCollectionsPanel.add(newPanel);
			
		}
	    
		JButton newButton = new JButton("Remove");
		newButton.setBounds(480, 15, 90, 20);
		newButton.setBackground(Color.RED);
		newButton.setName("remove-" + id);
		newPanel.add(newButton);

		this.collectionCounter++;

		this.numberOfPagesLabel.setText("Number Of Pages: " + (int)Math.ceil((double)this.collectionCounter / (double)5));
		
	}
	
	public JPanel getCollectionsPanelByPageNo(int pageNo) {
		Component[] components = this.myCollections.getComponents();
		for(int i = 0; i < components.length ; i++) {
			if(components[i] != null && components[i].getName() != null) {
				if(components[i].getName().equals("collectionsPanel-" + (pageNo))) {
					return (JPanel)components[i];
				}
			}
		}
		return null;
		
	}
	
	public void resetCollectionPanels() {
		for(Component component: this.myCollections.getComponents()) {
			if(component.getName() != null && component.getName().contains("collectionsPanel")) {
				if(component.getName().split("-")[1].equals("0")) {
					for(Component comp: ((JPanel) component).getComponents()) {
						((JPanel)component).remove(comp);
					}
				}else {
					this.myCollections.remove(component);
				}
			}
		}
		this.collectionsPanel.setVisible(true);
		this.currentPageNoLabel.setText("Current Page No: 1");
		this.collectionCounter = 0;
		this.currentPageNo = 0;
	}
	

	public void update(IObservable observable) {
		UserModel model = (UserModel) observable;
		
		this.showUsername("@"+model.getUsername());
		this.showNameAndSurname(model.getName() + " " + model.getSurname());
		this.showFollowers("Followers: "+ model.getFollowers().size());
		this.showFollowings("Followings: " + model.getFollowings().size());
		String profilePhotoPath;
		if(model.getGender().equals("Woman")) {
			profilePhotoPath = "womanAvatar.png"; 
		}else {
			profilePhotoPath = "manAvatar.png";
		}
		this.showProfilePhoto(profilePhotoPath);
		
		this.resetCollectionPanels();
		this.numberOfPagesLabel.setText("Number Of Pages: 1");
		
		for(CollectionModel collection: model.getCollections()) 
			this.addCollectionPanel(collection.getName(), collection.getId());
		
		this.addDefaultListModelToFollowersList();
		for(UserModel follower: model.getFollowers())
			this.addElementToFollowersList(follower.getUsername());
		
		this.addDefaultListModelToFollowingsList();
		for(UserModel following: model.getFollowings()) 
			this.addElementToFollowingsList(following.getUsername());
				
		this.setVisibilityOfLogoutButton(false);
		if(appManager.getOnlineUser() == model) {
			this.setVisibilityOfLogoutButton(true);
		}
		if(model == appManager.getOnlineUser()) {
			this.setVisibilityOfFollowUnfollowButton(false);
		}else {
			
			if(!this.appManager.getOnlineUser().getFollowings().contains(model)) {
				this.setFollowUnfollowButtonText("Follow");
				this.setFollowUnfollowButtonBackground(Color.GREEN);
				this.setVisibilityOfMyCollections(false);
			}else {
				this.setFollowUnfollowButtonText("Unfollow");
				this.setFollowUnfollowButtonBackground(Color.RED);
				this.setVisibilityOfMyCollections(true);
			}
			this.setVisibilityOfFollowUnfollowButton(true);
		}
		
		this.repaint();
	}

	public void setVisibilityOfCreateCollectionButton(boolean visibility) {
		this.createCollectionButton.setVisible(visibility);
	}

	public void setVisibilityOfSeeAllOutfitsButton(boolean visibility) {
		this.seeAllOutfitsButton.setVisible(visibility);
	}

	public void showUsername(String username) {
		this.usernameLabel.setText(username);
	}

	public void showNameAndSurname(String nameAndSurname) {
		this.nameAndSurnameLabel.setText(nameAndSurname);
	}

	public void showFollowers(String followers) {
		this.followersLabel.setText(followers);
	}

	public void showFollowings(String followings) {
		this.followingsLabel.setText(followings);
	}

	public void showProfilePhoto(String profilePhotoPath) {
		ImageIcon ico = new ImageIcon(profilePhotoPath);
		Image img = ico.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, this.profilePhotoLabel.getWidth(), this.profilePhotoLabel.getHeight(), null);
		ImageIcon newIco = new ImageIcon(bi);
		this.profilePhotoLabel.setIcon(newIco);
		
	}

	public void addDefaultListModelToFollowersList() {
		this.followersList.setModel(new DefaultListModel());
	}

	public void addElementToFollowersList(String newElem) {
		((DefaultListModel)this.followersList.getModel()).addElement(newElem);
	}

	public void addDefaultListModelToFollowingsList() {
		this.followingsList.setModel(new DefaultListModel());	
	}

	public void addElementToFollowingsList(String newElem) {
		((DefaultListModel)this.followingsList.getModel()).addElement(newElem);		
	}

	public void showAllUsers(String allUsers) {
		this.allUsersLabel.setText(allUsers);
	}

	public void addDefaultListModelToAllUsersList() {
		this.allUsersList.setModel(new DefaultListModel());
	}

	public void addElementToAllUsersList(String newElem) {
		((DefaultListModel)this.allUsersList.getModel()).addElement(newElem);
	}

	public void addDefaultListModelToAllOutfits() {
		this.allOutfitsList.setModel(new DefaultListModel());
	}

	public void addElementToAllOutfitsList(String newElem) {
		((DefaultListModel)this.allOutfitsList.getModel()).addElement(newElem);		
	}

	public void setVisibilityOfLogoutButton(boolean visibility) {
		this.logoutButton.setVisible(visibility);
	}

	public void setVisibilityOfFollowUnfollowButton(boolean visibility) {
		this.followUnfollowButton.setVisible(visibility);
	}

	public void setFollowUnfollowButtonText(String text) {
		this.followUnfollowButton.setText(text);
		
	}

	public void setFollowUnfollowButtonBackground(Color color) {
		this.followUnfollowButton.setBackground(color);
	}

	public void setVisibilityOfMyCollections(boolean visibility) {
		this.myCollections.setVisible(visibility);
	}

	public String getAllOutfitsListSelectedValue() {
		return this.allOutfitsList.getSelectedValue().toString();
	}

	public void addSeeOutfitDetailsPopupListener(ActionListener seeOutfitDetailsPopupListener) {
		this.seeOutfitDetailsPopupButton.addActionListener(seeOutfitDetailsPopupListener);	
	}

	public void setVisibilityOfSeeOutfitDetailsPopupButton(boolean visibility) {
		this.seeOutfitDetailsPopupButton.setVisible(visibility);
	}

	public void addAllOutfitsListSelectionListener(ListSelectionListener allOutfitsListSelectionListener) {
		this.allOutfitsList.addListSelectionListener(allOutfitsListSelectionListener);
	}

	public void setAllOutfitsPopupVisibility(boolean visibility) {
		this.allOutfitsPopup.setVisible(true);
	}

	public void addSeeAllOutfitsListener(ActionListener seeAllOutfitsListener) {
		this.seeAllOutfitsButton.addActionListener(seeAllOutfitsListener);
	}

	public String getNewCollectionName() {
		return this.newCollectionName.getText();		
	}

	public void showPopupMessage(String message) {
		this.popupMessageLabel.setText(message);
	}

	public void setNewCollectionNameText(String text) {
		this.newCollectionName.setText(text);
	}

	public void disposeCreateCollectionPopup() {
		this.createCollectionPopup.dispose();	
	}

	public void addFinishCollectionCreationListener(ActionListener finishCollectionCreation) {
		this.finishCollectionCreationButton.addActionListener(finishCollectionCreation);
	}

	public void setVisibilityOfCreateCollectionPopup(boolean visibility) {
		this.createCollectionPopup.setVisible(visibility);
	}

	public void addCreateCollectionListener(ActionListener createCollectionListener) {
		this.createCollectionButton.addActionListener(createCollectionListener);
	}

	public void addFollowUnfollowListener(ActionListener followUnfollowListener) {
		this.followUnfollowButton.addActionListener(followUnfollowListener);
	}

	public void addLogoutListener(ActionListener logoutListener) {
		this.logoutButton.addActionListener(logoutListener);
	}

	public Object getAllUsersListSelectedValue() {
		return this.allUsersList.getSelectedValue();
	}

	public void clearSelectionOfAllUsersList() {
		this.allUsersList.clearSelection();
	}

	public void addAllUsersListSelectionListener(ListSelectionListener allUsersListSelection) {
		this.allUsersList.addListSelectionListener(allUsersListSelection);
	}

	public Object getFollowingsListSelectedValue() {
		return this.followingsList.getSelectedValue();
	}

	public void clearSelectionOfFollowingsList() {
		this.followingsList.clearSelection();
	}

	public void addFollowingListSelectionListener(ListSelectionListener followingsListSelection) {
		this.followingsList.addListSelectionListener(followingsListSelection);
	}

	public Object getFollowersListSelectedValue() {
		return this.followersList.getSelectedValue();
	}

	public void clearSelectionoOfFollowersList() {
		this.followersList.clearSelection();
	}

	public void addFollowersListSelectionListener(ListSelectionListener followersListSelection) {
		this.followersList.addListSelectionListener(followersListSelection);
	}

	public void addPreviousPageListener(ActionListener previousPageListener) {
		this.previousPageButton.addActionListener(previousPageListener);
	}

	public void addNextPageListener(ActionListener nextPageListener) {
		this.nextPageButton.addActionListener(nextPageListener);
	}

	public void addMyCollectionsContainerListener(ContainerAdapter containerAdapter) {
		this.myCollections.addContainerListener(containerAdapter);
	}

	public void showCollectionsMessage(String message) {
		this.collectionsMessageLabel.setText(message);
	}

	public void addMyDefaultCollectionContainerListener(ContainerAdapter containerAdapter) {
		this.collectionsPanel.addContainerListener(containerAdapter);
	}

	public void setOnCloseAsNoNothing() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}

	public void addStatsListener(ActionListener statsListener) {
		this.StatsButton.addActionListener(statsListener);
		
	}
}
