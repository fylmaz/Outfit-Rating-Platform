package Controllers;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Business.AppManager;
import Models.CollectionModel;
import Models.OutfitModel;
import Models.UserModel;
import Views.UserView;

public class UserController {
	private AppManager appManager;
	private UserModel model;
	private UserView view;

	public UserController(AppManager appManager, UserModel model, UserView view) {
		this.appManager = appManager; 
		this.model = model;
		this.view = view;
		this.model.addObserver(view);

		view.addSeeOutfitDetailsPopupListener(new SeeOutfitDetailsPopupListener());
		view.addAllOutfitsListSelectionListener(new AllOutfitsListSelectionListener());
		view.addSeeAllOutfitsListener(new SeeAllOutfitsListener());
		view.addFinishCollectionCreationListener(new FinishCollectionCreationListener());
		view.addCreateCollectionListener(new CreateCollectionListener());
		view.addFollowUnfollowListener(new FollowUnfollowListener());
		view.addLogoutListener(new LogoutListener());
		view.addAllUsersListSelectionListener(new AllUsersListSelection());
		view.addFollowingListSelectionListener(new FollowingsListSelection());
		view.addFollowersListSelectionListener(new FollowersListSelection());
		view.addPreviousPageListener(new PreviousPageListener());
		view.addNextPageListener(new NextPageListener());
		view.addStatsListener(new StatsListener());
		this.addMyCollectionsContainerListener();
		this.addMyDefaultCollectionContainerListener();
		
		this.updateViewComponents();	

	}

	
	class StatsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			appManager.showStatsView();
		}
	}
	
	/**
	 * To show next collections page
	 */
	class NextPageListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.showNextPage();
		}
	}
	
	/**
	 * To show previous collections page
	 */
	class PreviousPageListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.showPreviousPage();	
		}
	}
	
	/**
	 * To show selected user from the all followers list
	 */
	class FollowersListSelection implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(view.getFollowersListSelectedValue() != null) {
				UserModel selectedUser = appManager.getUserService().getUserByUsername(view.getFollowersListSelectedValue().toString());
				view.clearSelectionoOfFollowersList();
				appManager.showNewUserView(selectedUser);
			}				
		}
	}
	
	/**
	 * To show selected user from the all followings list
	 */
	class FollowingsListSelection implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(view.getFollowingsListSelectedValue() != null) {
				UserModel selectedUser = appManager.getUserService().getUserByUsername(view.getFollowingsListSelectedValue().toString());
				view.clearSelectionOfFollowingsList();
				appManager.showNewUserView(selectedUser);
			}				
		}
	}

	/**
	 * To show selected user from the all users list
	 */
	class AllUsersListSelection implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(view.getAllUsersListSelectedValue() != null) {
				UserModel selectedUser = appManager.getUserService().getUserByUsername(view.getAllUsersListSelectedValue().toString());
				view.clearSelectionOfAllUsersList();
				appManager.showNewUserView(selectedUser);
			}				
		} 
	}
	
	/**
	 * To logout
	 */
	class LogoutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			appManager.logout();
		}
	}
	
	/**
	 * To follow/unfollow a user
	 */
	class FollowUnfollowListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!appManager.getOnlineUser().getFollowings().contains(model)) {
				model.addFollower(appManager.getOnlineUser());
				appManager.getOnlineUser().addFollowing(model);

			}else {
				model.removeFollower(appManager.getOnlineUser());
				appManager.getOnlineUser().removeFollowing(model);
			}
		}
	}
	
	/**
	 * To show create collection popup
	 */
	class CreateCollectionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.setVisibilityOfCreateCollectionPopup(true);
		}
	}
	
	/**
	 * To finish collection creation process
	 */
	class FinishCollectionCreationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = view.getNewCollectionName();
			if(name.equals("")) {
				//view.showPopupMessage("Collection name cannot be empty.");
			}else {
				view.setNewCollectionNameText("");
				//view.showPopupMessage("");
				view.disposeCreateCollectionPopup();
				CollectionModel newCollection = appManager.getUserService().newCollection();
				newCollection.setName(name);
				model.addCollection(newCollection);
				appManager.showNewCollectionView(newCollection);
			}
		}
	}
	
	/**
	 * To show all outfits popup
	 */
	class SeeAllOutfitsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.setAllOutfitsPopupVisibility(true);
		}
	}
	
	/**
	 * When an outfit selected
	 */
	class AllOutfitsListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			view.setVisibilityOfSeeOutfitDetailsPopupButton(true);
		}
	}
	
	/**
	 * To show an outfit view
	 */
	class SeeOutfitDetailsPopupListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int outfitId = Integer.parseInt(view.getAllOutfitsListSelectedValue().split(":")[1]);
			appManager.showNewOutfitView(appManager.getOutfitService().getOutfitById(outfitId));
		}
	}
	
	

	/**
	 * When a new collection added to a user(If it will be added to default collection container panel), necessary container, action and mouse adapters/listeners will be added 
	 */
	private void addMyDefaultCollectionContainerListener() {
		view.addMyDefaultCollectionContainerListener(new ContainerAdapter() {
			public void componentAdded(ContainerEvent e) {
				((JPanel)e.getChild()).addContainerListener(new ContainerAdapter() {
					public void componentAdded(ContainerEvent e) {
						if(e.getChild().getName() != null) { 
							if(model == appManager.getOnlineUser()) {
								((JButton)e.getChild()).addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent actionEvent) {
										int collectionId = Integer.parseInt(e.getComponent().getName().split("-")[1]);
										CollectionModel collection = model.getCollectionById(collectionId);
										model.removeCollection(collection);
										view.showCollectionsMessage(collection.getName() + " is removed succesfuly");
									}
								});
							}else {
								e.getChild().setVisible(false);
							}
						}
					}
				
				});
				e.getChild().addMouseListener(new MouseAdapter() {
					
				public void mouseClicked(MouseEvent e)
				{
					int collectionId = Integer.parseInt(e.getComponent().getName().split("-")[1]);
					appManager.showNewCollectionView(model.getCollectionById(collectionId));
				}
					
				public void mouseEntered(MouseEvent e) {
					e.getComponent().setBackground(Color.WHITE);
					e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					
				}

				public void mouseExited(MouseEvent e) {
					e.getComponent().setBackground(Color.LIGHT_GRAY);
					e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
				}
			
		});
		
	}
});
		
	}

	/**
	 * When a new collection added to a user, necessary container, action and mouse adapters/listeners will be added 
	 */
	private void addMyCollectionsContainerListener() {
		this.view.addMyCollectionsContainerListener(new ContainerAdapter() {
			public void componentAdded(ContainerEvent e) {
				if(e.getChild().getName() != null && e.getChild().getName().contains("collectionsPanel")) {
					((JPanel)e.getChild()).addContainerListener(new ContainerAdapter() {
						public void componentAdded(ContainerEvent e) {
								((JPanel)e.getChild()).addContainerListener(new ContainerAdapter() {
									public void componentAdded(ContainerEvent e) {
										if(e.getChild().getName() != null) {
											if(model == appManager.getOnlineUser()) {
												((JButton)e.getChild()).addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent actionEvent) {
														int collectionId = Integer.parseInt(e.getComponent().getName().split("-")[1]);
														CollectionModel collection = model.getCollectionById(collectionId);
														model.removeCollection(collection);
														view.showCollectionsMessage(collection.getName() + " is removed succesfuly");
													}
												});
											}else {
												e.getChild().setVisible(false);
											}
										}
									}
								
								});
							
								e.getChild().addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e)
								{
									int collectionId = Integer.parseInt(e.getComponent().getName().split("-")[1]);
									appManager.showNewCollectionView(model.getCollectionById(collectionId));
								}
								
								public void mouseEntered(MouseEvent e) {
									
									e.getComponent().setBackground(Color.WHITE);
									e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
									
								}
			
								public void mouseExited(MouseEvent e) {
									e.getComponent().setBackground(Color.LIGHT_GRAY);
									e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
									
								}	
						});
							
						}
					}); 	 
				}
				
			}
		});
	}
	
	public void updateViewComponents() {
		if(this.appManager.getOnlineUser() == this.model) {
			this.view.setOnCloseAsNoNothing();
		}
		
		this.view.addDefaultListModelToAllOutfits();
		for(OutfitModel outfit: this.appManager.getOutfitService().getAllOutfits()) 
			this.view.addElementToAllOutfitsList("Product Id:" + outfit.getId());
		
		if(this.appManager.getOnlineUser() == this.model) {
			this.view.setVisibilityOfCreateCollectionButton(true);
			this.view.setVisibilityOfSeeAllOutfitsButton(true);
		}
		
		this.view.showUsername("@"+this.model.getUsername());
		this.view.showNameAndSurname(this.model.getName() + " " + this.model.getSurname());
		this.view.showFollowers("Followers: "+ this.model.getFollowers().size());
		this.view.showFollowings("Followings: " + this.model.getFollowings().size());
		String profilePhotoPath;
		if(this.model.getGender().equals("Woman")) {
			profilePhotoPath = "womanAvatar.png"; 
		}else {
			profilePhotoPath = "manAvatar.png";
		}
		this.view.showProfilePhoto(profilePhotoPath);

		for(CollectionModel collection: this.model.getCollections()) 
			this.view.addCollectionPanel(collection.getName(), collection.getId());
		
		this.view.addDefaultListModelToFollowersList();
		for(UserModel follower: this.model.getFollowers())
			this.view.addElementToFollowersList(follower.getUsername());
		
		this.view.addDefaultListModelToFollowingsList();
		for(UserModel following: this.model.getFollowings()) 
			this.view.addElementToFollowingsList(following.getUsername());
		
		this.view.showAllUsers("All Users: "+ this.appManager.getUserService().getAllUsers().size());
		this.view.addDefaultListModelToAllUsersList();
		for(UserModel user: this.appManager.getUserService().getAllUsers()) 
			this.view.addElementToAllUsersList(user.getUsername());
		
		this.view.setVisibilityOfLogoutButton(false);
		if(this.appManager.getOnlineUser() == this.model) {
			this.view.setVisibilityOfLogoutButton(true);
		}
		if(this.model == this.appManager.getOnlineUser()) {
			this.view.setVisibilityOfFollowUnfollowButton(false);
		}else {
			
			if(!this.appManager.getOnlineUser().getFollowings().contains(model)) {
				this.view.setFollowUnfollowButtonText("Follow");
				this.view.setFollowUnfollowButtonBackground(Color.GREEN);
				this.view.setVisibilityOfMyCollections(false);
			}else {
				this.view.setFollowUnfollowButtonText("Unfollow");
				this.view.setFollowUnfollowButtonBackground(Color.RED);
				this.view.setVisibilityOfMyCollections(true);
			}
			this.view.setVisibilityOfFollowUnfollowButton(true);
		}
		
	}
}
