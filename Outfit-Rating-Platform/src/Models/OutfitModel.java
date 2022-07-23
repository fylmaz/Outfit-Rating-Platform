package Models;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Interfaces.IObservable;
import Interfaces.IObserver;

public class OutfitModel implements IObservable {
	private int id;
	private String brandName;
	private String clothingType;
	private String suitableOccasion;
	private String gender;
	private String size;
	private String color;
	private List<UserModel> likers; 
	private List<UserModel> dislikers;
	private List<CommentModel> comments;
	private List<IObserver> observers;

	public OutfitModel(int id, String brandName, String clothingType, String suitableOccasion, String gender,String size, String color) {
		this.id = id;
		this.brandName = brandName;
		this.clothingType = clothingType;
		this.suitableOccasion = suitableOccasion;
		this.gender = gender;
		this.size = size;
		this.color = color;
		this.likers = new ArrayList<UserModel>();
		this.dislikers = new ArrayList<UserModel>();
		this.comments = new ArrayList<CommentModel>();
		this.observers = new ArrayList<IObserver>();
	}
		
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Id", String.valueOf(this.id));
		jsonObj.put("BrandName", this.brandName);
		jsonObj.put("ClothingType", this.clothingType);
		jsonObj.put("SuitableOccasion", this.suitableOccasion);
		jsonObj.put("Gender", this.gender);
		jsonObj.put("Size", this.size);
		jsonObj.put("Color", this.color);
		jsonObj.put("NumberOfLikes", String.valueOf(this.likers.size()));
		jsonObj.put("NumberOfDislikes", String.valueOf(this.dislikers.size()));
		jsonObj.put("NumberOfComments", String.valueOf(this.comments.size()));
		JSONArray comments = new JSONArray();
		for(CommentModel comment: this.comments) {
			comments.add(comment.toJSONObject());
		}
		jsonObj.put("Comments", comments);
		return jsonObj;
	}
	
	
	public void addLiker(UserModel liker) {
		this.likers.add(liker);
		this.notifyObservers(this);
	}
	
	public void addDisliker(UserModel disliker) {
		this.dislikers.add(disliker);
		this.notifyObservers(this);
	}
	
	public void addComment(CommentModel comment) {
		this.comments.add(comment);
		this.notifyObservers(this);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.notifyObservers(this);
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
		this.notifyObservers(this);

	}
	public String getClothingType() {
		return clothingType;
	}
	public void setClothingType(String clothingType) {
		this.clothingType = clothingType;
		this.notifyObservers(this);

	}
	public String getSuitableOccasion() {
		return suitableOccasion;
	}
	public void setSuitableOccasion(String suitableOccasion) {
		this.suitableOccasion = suitableOccasion;
		this.notifyObservers(this);

	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
		this.notifyObservers(this);

	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
		this.notifyObservers(this);

	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
		this.notifyObservers(this);
	}

	public void addObserver(IObserver observer) {
		this.observers.add(observer);	
	}

	public List<UserModel> getLikers() {
		return likers;
	}

	public void setLikers(List<UserModel> likers) {
		this.likers = likers;
		this.notifyObservers(this);
	}

	public List<UserModel> getDislikers() {
		return dislikers;
	}

	public void setDislikers(List<UserModel> dislikers) {
		this.dislikers = dislikers;
		this.notifyObservers(this);
	}

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
		this.notifyObservers(this);
	}


	public void notifyObservers(IObservable observable) {
		for(IObserver observer: this.observers) {
			observer.update(observable);
		}
	}

	public int getNumberOfLikes() {
		return this.likers.size();
	}
	
	public int getNumberOfDislikes() {
		return this.dislikers.size();
	}
	
	public int getNumberOfComments() {
		return this.comments.size();
	}

	public void removeDisliker(UserModel disliker) {
		this.dislikers.remove(disliker);
		this.notifyObservers(this);		
	}

	public void removeLiker(UserModel liker) {
		this.likers.remove(liker);
		this.notifyObservers(this);		
		
	}

	public CommentModel getCommentById(int id) {
		for(CommentModel comment: this.comments) {
			if(comment.getId() == id)
				return comment;
		}
		return null;
	}

	

}
