package Controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.AppManager;
import Models.CommentModel;
import Models.OutfitModel;
import Models.UserModel;
import Views.OutfitView;

public class OutfitController {
	private AppManager appManager;
	private OutfitModel model;
	private OutfitView view;
	
	public OutfitController(AppManager appManager, OutfitModel model, OutfitView view) {
		this.appManager = appManager;
		this.model = model;
		this.view = view;
		this.model.addObserver(view);
		this.updateViewComponents();
	
		view.addDislikeListener(new DislikeListener());
		view.addLikeListener(new LikeListener());
		view.addCommentListener(new CommentListener());
		view.addWindowListener(new WindowListenerr());
		view.addCommentPopupListener(new CommentPopupListener());
		view.addCommentSelectionListener(new CommentSelectionListener());
	}
	
	/**
	 * To show a new (selected)comment view
	 */
	class CommentSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(view.getCommentListSelectedValue() != null) {
				CommentModel selectedComment = model.getCommentById(Integer.parseInt(view.getCommentListSelectedValue().toString().split("-")[0]));
				view.clearCommentsListSelection();
				appManager.showNewCommentView(selectedComment);
			}	
		}
	}
	
	/**
	 * To add a new comment (using textarea in the popup)
	 */
	class CommentPopupListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CommentModel comment = appManager.getOutfitService().newComment();
			comment.setCommentOwnerUsername(appManager.getOnlineUser().getUsername());
			comment.setText(view.getCommentText());
			model.addComment(comment);
			view.resetCommentTextArea();
			view.disposeCommentPopup();
		}	
	}
	
	/**
	 * To dispose popup of the view when view closed
	 */
	class WindowListenerr extends WindowAdapter{
	    public void windowClosing(WindowEvent windowEvent) {
			view.disposeCommentPopup();
	    }
	}
	
	/**
	 * To comment to the outfit
	 */
	class CommentListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.setVisibilityOfCommentPopup(true);			
		}
	}
	
	/**
	 * To like the outfit
	 */
	class LikeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!appManager.getOnlineUser().getLikes().contains(model)) {
				appManager.getOnlineUser().addLike(model);
				model.addLiker(appManager.getOnlineUser());				
			}else {
				appManager.getOnlineUser().removeLike(model);
				model.removeLiker(appManager.getOnlineUser());
			}
		}
	}
	
	/**
	 * To dislike the outfit
	 */
	class DislikeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!appManager.getOnlineUser().getDislikes().contains(model)) {
				appManager.getOnlineUser().addDislike(model);
				model.addDisliker(appManager.getOnlineUser());
			}else {
				appManager.getOnlineUser().removeDislike(model);
				model.removeDisliker(appManager.getOnlineUser());
			}
		}
	}
	
	public void updateViewComponents() {
		this.view.showId("Id: " + this.model.getId());
		this.view.showBrandName("Brand Name: " + this.model.getBrandName());
		this.view.showClothingType("Clothing Type: " + this.model.getClothingType());
		this.view.showSuitableOccassion("Suitable Occasion: " + this.model.getSuitableOccasion());
		this.view.showGender("Gender: " + this.model.getGender());
		this.view.showSize("Size: " + this.model.getSize());
		this.view.showColor("Color: " + this.model.getColor());
		this.view.showLikes("Likes: " + this.model.getNumberOfLikes());
		this.view.showDislikes("Dislikes: " + this.model.getNumberOfDislikes());
		this.view.showComments("Comments: " + this.model.getNumberOfComments());
		
		this.view.addDefaultListModelToLikesList();
		for(UserModel liker: this.model.getLikers()) 
			view.addElementToLikesList(liker.getUsername());
		
		this.view.addDefaultListModelToDislikesList();
		for(UserModel disliker: this.model.getDislikers()) 
			view.addElementToDislikesList(disliker.getUsername());
		
		this.view.addDefaultListModelToCommentsList();
		for(CommentModel comment: this.model.getComments())
			this.view.addElementToCommentsList(comment.getId() + "-" + comment.getcommentOwnerUsername() + ": " + comment.getText());
		
		if(appManager.getOnlineUser().getLikes().contains(this.model) ) {
			this.view.setBackgroundOfLikeButton(Color.GREEN);
			this.view.setBackgroundOfDislikeButton(Color.GRAY);
			this.view.setEnabledOfDislikeButton(false);
		}
		else if(appManager.getOnlineUser().getDislikes().contains(this.model) ) {
			this.view.setBackgroundOfDislikeButton(Color.RED);
			this.view.setBackgroundOfLikeButton(Color.GRAY);
			this.view.setEnabledOfLikeButton(false);
		}
		else {
			this.view.setBackgroundOfDislikeButton(Color.GRAY);
			this.view.setBackgroundOfLikeButton(Color.GRAY);
			this.view.setEnabledOfDislikeButton(true);
			this.view.setEnabledOfLikeButton(true);
		}
	}
	
}
