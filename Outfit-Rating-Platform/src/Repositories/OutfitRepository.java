package Repositories;

import java.util.ArrayList;
import java.util.List;
import Models.CommentModel;
import Models.OutfitModel;

public class OutfitRepository {
	private List<OutfitModel> outfits;
	private int maxCommentId;
	
	public OutfitRepository() {
		this.outfits = new ArrayList<OutfitModel>();
		this.maxCommentId = 0;
	}

	public List<OutfitModel> getOutfits() {
		return outfits;
	}

	public void setOutfits(List<OutfitModel> outfits) {
		this.outfits = outfits;
	}
	
	public OutfitModel getOutfitById(int id) {
		for(OutfitModel outfit: this.outfits) {
			if(outfit.getId() == id) {
				return outfit;
			}
		}
		return null;
	}

	public CommentModel newComment(){
		return new CommentModel(++this.maxCommentId, "","");
	}

	public int getMaxCommentId() {
		return maxCommentId;
	}

	public void setMaxCommentId(int maxCommentId) {
		this.maxCommentId = maxCommentId;
	}

	public OutfitModel getMostLikedOutfit() {
		OutfitModel mostLiked = null;
		int max = -1;
		for(OutfitModel outfit: this.outfits) {
			if(outfit.getNumberOfLikes() > max) {
				max = outfit.getNumberOfLikes();
				mostLiked = outfit;
			}
		}
		return mostLiked;
	}
	
	public OutfitModel getMostDislikedOutfit() {
		OutfitModel mostDisliked = null;
		int max = -1;
		for(OutfitModel outfit: this.outfits) {
			if(outfit.getNumberOfDislikes() > max) {
				max = outfit.getNumberOfDislikes();
				mostDisliked = outfit;
			}
		}
		return mostDisliked;
	}


	
	
}
