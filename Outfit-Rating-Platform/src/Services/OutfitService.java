package Services;

import java.util.List;

import Models.CollectionModel;
import Models.CommentModel;
import Models.OutfitModel;
import Repositories.OutfitRepository;

public class OutfitService {
	
	private OutfitRepository outfitRepositry;
	
	public OutfitService(OutfitRepository outfitRepositry) {
		this.outfitRepositry = outfitRepositry;
	}

	public List<OutfitModel> getAllOutfits() {
		return this.outfitRepositry.getOutfits();
	}

	public OutfitModel getOutfitById(int id) {
		return this.outfitRepositry.getOutfitById(id);
	}

	public CommentModel newComment(){
		return this.outfitRepositry.newComment();
	}

	public OutfitModel getMostLikedOutfit() {
		return this.outfitRepositry.getMostLikedOutfit();
	}	
	
	public OutfitModel getMostDislikedOutfit() {
		return this.outfitRepositry.getMostDislikedOutfit();
	}

}
