package Views;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import Business.AppManager;
import Interfaces.IObservable;
import Interfaces.IObserver;
import Models.CommentModel;
import Models.OutfitModel;
import Models.UserModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OutfitView extends JFrame implements IObserver {
	private AppManager appManager;
	private JPanel contentPane;
	private JLabel idLabel;
	private JLabel brandNameLabel;
	private JLabel clothingTypeLabel;
	private JLabel suitableOccasionLabel;
	private JLabel genderLabel;
	private JLabel sizeLabel;
	private JLabel colorLabel;
	private JScrollPane likesPane;
	private JList likesList;
	private JButton commentButton;
	private JButton dislikeButton;
	private JButton likeButton;
	private JLabel commentsLabel;
	private JList commentsList;
	private JScrollPane commentsPane;
	private JLabel dislikesLabel;
	private JList dislikesList;
	private JScrollPane dislikesPane;
	private JLabel likesLabel;
	private JFrame commentPopup;
	private JButton addCommentPopupButton;
	private JLabel popupMessageLabel;
	private JTextArea commentTextArea;

	public OutfitView(AppManager appManager) {
		this.setTitle("Outfit View");
		this.appManager = appManager;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 469); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.idLabel = new JLabel("New label");
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(10, 10, 376, 20);
		contentPane.add(idLabel);
		
		this.brandNameLabel = new JLabel("New label");
		brandNameLabel.setForeground(Color.WHITE);
		brandNameLabel.setBounds(10, 40, 376, 20);
		contentPane.add(brandNameLabel);
		
		this.clothingTypeLabel = new JLabel("New label");
		clothingTypeLabel.setForeground(Color.WHITE);
		clothingTypeLabel.setBounds(10, 70, 376, 20);
		contentPane.add(clothingTypeLabel);
		
		this.suitableOccasionLabel = new JLabel("New label");
		suitableOccasionLabel.setForeground(Color.WHITE);
		suitableOccasionLabel.setBounds(10, 100, 376, 20);
		contentPane.add(suitableOccasionLabel);
		
	    this.genderLabel = new JLabel("New label");
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(10, 130, 376, 20);
		contentPane.add(genderLabel);
		
	    this.sizeLabel = new JLabel("New label");
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setBounds(10, 160, 376, 20);
		contentPane.add(sizeLabel);
		
		this.colorLabel = new JLabel("New label");
		colorLabel.setForeground(Color.WHITE);
		colorLabel.setBounds(10, 192, 376, 20);
		contentPane.add(colorLabel);
		
		this.likesPane = new JScrollPane();
		likesPane.setBounds(10, 222, 110, 131);
		contentPane.add(likesPane);
		
		this.likesList = new JList();
		likesPane.setViewportView(likesList);
		
		this.likesLabel = new JLabel("New label");
		likesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		likesPane.setColumnHeaderView(likesLabel);
		
	    this.dislikesPane = new JScrollPane();
		dislikesPane.setBounds(135, 222, 110, 131);
		contentPane.add(dislikesPane);
		
		this.dislikesList = new JList();
		dislikesPane.setViewportView(dislikesList);
		
		this.dislikesLabel = new JLabel("New label");
		dislikesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dislikesPane.setColumnHeaderView(dislikesLabel);
		
		this.commentsPane = new JScrollPane();
		commentsPane.setBounds(260, 222, 110, 131);
		contentPane.add(commentsPane);
		
		this.commentsList = new JList();
		commentsPane.setViewportView(commentsList);
		
		this.commentsLabel = new JLabel("New label");
		commentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		commentsPane.setColumnHeaderView(commentsLabel);
		
		this.likeButton = new JButton("Like");
		likeButton.setBackground(Color.GRAY);
		likeButton.setForeground(Color.WHITE);
		likeButton.setBounds(10, 366, 110, 20);
		contentPane.add(likeButton);
		
		this.dislikeButton = new JButton("Dislike");
		dislikeButton.setForeground(Color.WHITE);
		dislikeButton.setBackground(Color.GRAY);
		dislikeButton.setBounds(135, 366, 110, 20);
		contentPane.add(dislikeButton);
		
		this.commentButton = new JButton("Comment");
		commentButton.setForeground(Color.WHITE);
		commentButton.setBackground(Color.GRAY);
		commentButton.setBounds(260, 366, 110, 20);
		contentPane.add(commentButton);
		
        commentPopup = new JFrame("Comment");
        commentPopup.getContentPane().setBackground(new Color(0,69,107)); 
        commentPopup.setSize(300, 370);
        commentPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        commentPopup.setVisible(false);
        commentPopup.setResizable(false); 
        commentPopup.getContentPane().setLayout(null);
        
        commentTextArea = new JTextArea();
        commentTextArea.setBounds(10, 20, 260, 240);

		JScrollPane scrollPane = new JScrollPane(commentTextArea);
		scrollPane.setBounds(10, 10, 267, 245);
		commentPopup.add(scrollPane);
      
		addCommentPopupButton = new JButton("Add Comment");
		addCommentPopupButton.setBounds(90, 275, 120, 20);
		addCommentPopupButton.setBackground(Color.GREEN);
		addCommentPopupButton.setForeground(Color.WHITE);
		addCommentPopupButton.setVisible(true);
		
		commentPopup.getContentPane().add(addCommentPopupButton);
		popupMessageLabel = new JLabel();
		popupMessageLabel.setBounds(10, 300, 300, 20);
		popupMessageLabel.setForeground(Color.WHITE);
		commentPopup.getContentPane().add(popupMessageLabel);
	}
	
	public void update(IObservable observable) {
		OutfitModel model = (OutfitModel) observable;
		
		this.showId("Id: " + model.getId());
		this.showBrandName("Brand Name: " + model.getBrandName());
		this.showClothingType("Clothing Type: " + model.getClothingType());
		this.showSuitableOccassion("Suitable Occasion: " + model.getSuitableOccasion());
		this.showGender("Gender: " + model.getGender());
		this.showSize("Size: " + model.getSize());
		this.showColor("Color: " + model.getColor());
		this.showLikes("Likes: " + model.getNumberOfLikes());
		this.showDislikes("Dislikes: " + model.getNumberOfDislikes());
		this.showComments("Comments: " + model.getNumberOfComments());
		
		this.addDefaultListModelToLikesList();
		for(UserModel liker: model.getLikers()) 
			this.addElementToLikesList(liker.getUsername());
		
		this.addDefaultListModelToDislikesList();
		for(UserModel disliker: model.getDislikers()) 
			this.addElementToDislikesList(disliker.getUsername());
		
		this.addDefaultListModelToCommentsList();
		for(CommentModel comment: model.getComments())
			this.addElementToCommentsList(comment.getId() + "-" + comment.getcommentOwnerUsername() + ": " + comment.getText());
		
		if(appManager.getOnlineUser().getLikes().contains(model) ) {
			this.setBackgroundOfLikeButton(Color.GREEN);
			this.setBackgroundOfDislikeButton(Color.GRAY);
			this.setEnabledOfDislikeButton(false);
		}
		else if(appManager.getOnlineUser().getDislikes().contains(model) ) {
			this.setBackgroundOfDislikeButton(Color.RED);
			this.setBackgroundOfLikeButton(Color.GRAY);
			this.setEnabledOfLikeButton(false);
		}
		else {
			this.setBackgroundOfDislikeButton(Color.GRAY);
			this.setBackgroundOfLikeButton(Color.GRAY);
			this.setEnabledOfDislikeButton(true);
			this.setEnabledOfLikeButton(true);
		}
	}
	
	public void showId(String id) {
		this.idLabel.setText(id);
	}

	public void showBrandName(String brandName) {
		this.brandNameLabel.setText(brandName);
	}

	public void showClothingType(String clothingType) {
		this.clothingTypeLabel.setText(clothingType);
	}

	public void showSuitableOccassion(String suitableOccassion) {
		this.suitableOccasionLabel.setText(suitableOccassion);
	}

	public void showGender(String gender) {
		this.genderLabel.setText(gender);
	}

	public void showSize(String size) {
		this.sizeLabel.setText(size);	
	}

	public void showColor(String color) {
		this.colorLabel.setText(color);
	}

	public void showLikes(String likes) {
		this.likesLabel.setText(likes);
	}

	public void showDislikes(String dislikes) {
		this.dislikesLabel.setText(dislikes);
	}
	
	public void showComments(String comments) {
		this.commentsLabel.setText(comments);
	}

	public void addDefaultListModelToLikesList() {
		this.likesList.setModel(new DefaultListModel());		
	}
	
	public void addElementToLikesList(String newElem) {
		((DefaultListModel)this.likesList.getModel()).addElement(newElem);
	}
	
	public void addDefaultListModelToDislikesList() {
		this.dislikesList.setModel(new DefaultListModel());	
	}

	public void addElementToDislikesList(String newElem) {
		((DefaultListModel)this.dislikesList.getModel()).addElement(newElem);
	}

	public void addDefaultListModelToCommentsList() {
		this.commentsList.setModel(new DefaultListModel());	
	}

	public void addElementToCommentsList(String newElem) {
		((DefaultListModel)this.commentsList.getModel()).addElement(newElem);
	}

	public void setBackgroundOfLikeButton(Color green) {
		this.likeButton.setBackground(green);		
	}

	public void setBackgroundOfDislikeButton(Color gray) {
		this.dislikeButton.setBackground(gray);
	}

	public void setEnabledOfDislikeButton(boolean enabled) {
		this.dislikeButton.setEnabled(enabled);
	}

	public void setEnabledOfLikeButton(boolean enabled) {
		this.likeButton.setEnabled(enabled);
	}

	public void addDislikeListener(ActionListener dislikeListener) {
		this.dislikeButton.addActionListener(dislikeListener);
	}
	
	public void addLikeListener(ActionListener likeListener) {
		this.likeButton.addActionListener(likeListener);
	}

	public void setVisibilityOfCommentPopup(boolean visibility) {
		this.commentPopup.setVisible(true);
	}

	public void addCommentListener(ActionListener commentListener) {
		this.commentButton.addActionListener(commentListener);
	}

	public void disposeCommentPopup() {
		this.commentPopup.dispose();
	}

	public String getCommentText() {
		return this.commentTextArea.getText();
	}

	public void addCommentPopupListener(ActionListener commentPopupListener) {
		this.addCommentPopupButton.addActionListener(commentPopupListener);
	}

	public void resetCommentTextArea() {
		this.commentTextArea.setText("");
	}

	public Object getCommentListSelectedValue() {
		return this.commentsList.getSelectedValue();
	}

	public void addCommentSelectionListener(ListSelectionListener commentSelectionListener) {
		this.commentsList.addListSelectionListener(commentSelectionListener);
	}

	public void clearCommentsListSelection() {
		this.commentsList.clearSelection();
	}
}
