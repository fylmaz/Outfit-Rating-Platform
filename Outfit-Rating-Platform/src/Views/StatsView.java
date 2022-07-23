package Views;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Business.AppManager;
import Interfaces.IObservable;
import Interfaces.IObserver;

public class StatsView extends JFrame implements IObserver {
	private AppManager appManager;
	private JPanel contentPane;
	private JLabel mostLikedOutfitLabel;
	private JLabel mostDislikedOutfitLabel;
	private JLabel mostFollowedUser;

	public StatsView(AppManager appManager) {
		this.appManager = appManager;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		this.setTitle("Statistics View");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107)); 
		this.setResizable(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);
        
        mostLikedOutfitLabel = new JLabel("MostLiked");
        mostDislikedOutfitLabel = new JLabel("MostDisliked");
        mostFollowedUser = new JLabel("MostFollowed");
        
        mostLikedOutfitLabel.setForeground(Color.white);
        mostDislikedOutfitLabel.setForeground(Color.white);
        mostFollowedUser.setForeground(Color.white);
        
        mostLikedOutfitLabel.setBounds(10, 10, 290, 20 );
        mostDislikedOutfitLabel.setBounds(10, 40, 290, 20 );
        mostFollowedUser.setBounds(10, 70, 290, 20 );

        contentPane.add(mostLikedOutfitLabel);
        contentPane.add(mostDislikedOutfitLabel);
        contentPane.add(mostFollowedUser);
	
	}
	
	public void update(IObservable observable) {
		this.updateStats();
		
	}
	
	public void updateStats() {
		if(this.appManager.getOutfitService().getMostLikedOutfit() != null) {
			this.updateMostLikedOutfit(this.appManager.getOutfitService().getMostLikedOutfit().getId());
		}else {
			this.updateMostLikedOutfit(-1);
		}
		
		if(this.appManager.getOutfitService().getMostDislikedOutfit() != null) {
			this.updateMostDislikedOutfit(this.appManager.getOutfitService().getMostDislikedOutfit().getId());
		}else {
			this.updateMostDislikedOutfit(-1);
		}
		
		if(this.appManager.getUserService().getMostFollowedUser() != null) {
			this.updateMostFollowedUser(this.appManager.getUserService().getMostFollowedUser().getUsername());
		}else {
			this.updateMostFollowedUser("None");
			}
		
	}

	public void updateMostLikedOutfit(int id) {
		this.mostLikedOutfitLabel.setText("Most Liked Outfit Id: " + id);
		
	}

	public void updateMostDislikedOutfit(int id) {
		this.mostDislikedOutfitLabel.setText("Most Disliked Outfit Id: " + id);
	}

	public void updateMostFollowedUser(String username) {
		this.mostFollowedUser.setText("Most Followed User Username: " + username );
	}






}
