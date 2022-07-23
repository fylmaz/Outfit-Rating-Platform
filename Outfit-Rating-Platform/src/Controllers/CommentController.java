package Controllers;
import Models.CommentModel;
import Views.CommentView;

public class CommentController {
	private CommentView view;
	private CommentModel model;
	
	public CommentController( CommentModel model, CommentView view) {
		this.view = view;
		this.model = model;
		this.updateViewComponents();
	}
	
	public void updateViewComponents() {
		view.setCommentField("Id: " + model.getId() + "\n" +
							 "Comment Owner: " + model.getcommentOwnerUsername() + "\n" +
							 "Comment: " + model.getText());
	}
}
