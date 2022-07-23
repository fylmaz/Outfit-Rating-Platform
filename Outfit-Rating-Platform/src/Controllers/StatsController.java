package Controllers;

import Business.AppManager;
import Views.StatsView;

public class StatsController {
	private AppManager appManager;
	private StatsView view;
	
	public StatsController(AppManager appManager, StatsView view) {
		this.appManager = appManager;
		this.view = view;
		this.updateViewComponents();
	}

	public void updateViewComponents() {
		System.out.println("a");
		if(this.appManager.getOutfitService().getMostLikedOutfit() != null) {
			this.view.updateMostLikedOutfit(this.appManager.getOutfitService().getMostLikedOutfit().getId());
		}else {
			this.view.updateMostLikedOutfit(-1);
		}
		
		if(this.appManager.getOutfitService().getMostDislikedOutfit() != null) {
			this.view.updateMostDislikedOutfit(this.appManager.getOutfitService().getMostDislikedOutfit().getId());
		}else {
			this.view.updateMostDislikedOutfit(-1);
		}
		
		if(this.appManager.getUserService().getMostFollowedUser() != null) {
			this.view.updateMostFollowedUser(this.appManager.getUserService().getMostFollowedUser().getUsername());
		}else {
			this.view.updateMostFollowedUser("None");
			}
	}
	
}


