package Models;

import java.util.ArrayList;
import java.util.List;
import Interfaces.IObservable;
import Interfaces.IObserver;
 
public class UserModel implements IObservable {
	private int id;
	private String username;
	private String name;
	private String surname; 
	private String gender;
	private String password;
	private List<UserModel> followers;
	private List<UserModel> followings;
	private List<CollectionModel> collections;
	private List<OutfitModel> likes; 
	private List<OutfitModel> dislikes;
	private List<IObserver> observers;
	
	public UserModel(int id, String username, String name, String surname, String gender, String password) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.password = password;
		this.followers = new ArrayList<UserModel>();
		this.followings = new ArrayList<UserModel>();
		this.collections = new ArrayList<CollectionModel>();
		this.likes = new ArrayList<OutfitModel>();
		this.dislikes = new ArrayList<OutfitModel>();
		this.observers = new ArrayList<IObserver>();
	}
	
	public String toXMLString() {
		String str = "<User>";
			str += "<Id>" + this.id +"</Id>";
			str += "<Username>" + this.username +"</Username>";
			str += "<Name>" + this.name +"</Name>";
			str += "<Surname>" + this.surname +"</Surname>";
			str += "<Gender>" + this.gender +"</Gender>";
			str += "<Password>" + this.password +"</Password>";
			str += "<FollowersIds>";
				for(UserModel follower: this.followers) 
					str += "<FollowerId>" + follower.getId() + "</FollowerId>";
			str += "</FollowersIds>";
			str += "<FollowingsIds>";
			for(UserModel following: this.followings) 
				str += "<FollowingId>" + following.getId() + "</FollowingId>";
			str += "</FollowingsIds>";		
			str += "<Collections>";
			for(CollectionModel collection: this.collections) 
				str += collection.toXMLString();
			str += "</Collections>";		
			str += "<LikesIds>";
			for(OutfitModel like: this.likes) 
				str += "<LikeId>" + like.getId() + "</LikeId>" ;
			str += "</LikesIds>";
			str += "<DislikesIds>";
			for(OutfitModel dislike: this.dislikes) 
				str += "<DislikeId>" + dislike.getId() + "</DislikeId>" ;
			str += "</DislikesIds>";	
		str += "</User>";
		return str;
	}

	public void addObserver(IObserver observer) {
		this.observers.add(observer);

		
	}

	public void notifyObservers(IObservable observable) {
		for(IObserver observer: this.observers) {
			observer.update(observable);
		}
		
	} 
	
	public void addCollection(CollectionModel collection) {
		this.collections.add(collection);
		this.notifyObservers(this);
	}
	
	public void addFollower(UserModel follower) {
		this.followers.add(follower);
		this.notifyObservers(this);
		follower.notifyObservers(follower);
	}
	
	public void addFollowing(UserModel following) {
		this.followings.add(following);
		this.notifyObservers(this);
		following.notifyObservers(following);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.notifyObservers(this);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
		this.notifyObservers(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		this.notifyObservers(this);
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
		this.notifyObservers(this);
	}
	
	public List<UserModel> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<UserModel> followers) {
		this.followers = followers;
		this.notifyObservers(this);
	}
	
	public List<UserModel> getFollowings() {
		return followings;
	}
	
	public void setFollowing(List<UserModel> followings) {
		this.followings = followings;
		this.notifyObservers(this);
	}
	
	public List<CollectionModel> getCollections() {
		return collections;
	}
	
	public void setCollections(List<CollectionModel> collections) {
		this.collections = collections;
		this.notifyObservers(this);
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
		this.notifyObservers(this);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
		this.notifyObservers(this);
	}


	public void removeFollower(UserModel follower) {
		this.followers.remove(follower);
		this.notifyObservers(this);
		follower.notifyObservers(follower);
		
	}


	public void removeFollowing(UserModel following) {
		this.followings.remove(following);
		this.notifyObservers(this);
		following.notifyObservers(following);
		
	}

	public void addDislike(OutfitModel dislike) {
		this.dislikes.add(dislike);
		this.notifyObservers(this);
	}
	
	public void addLike(OutfitModel like) {
		this.likes.add(like);
		this.notifyObservers(this);
	}
	
	public List<OutfitModel> getLikes() {
		return likes;
	}
	
	public void setLikes(List<OutfitModel> likes) {
		this.likes = likes;
		this.notifyObservers(this);

	}
	public List<OutfitModel> getDislikes() {
		return dislikes;
	}
	
	public void setDislikes(List<OutfitModel> dislikes) {
		this.dislikes = dislikes;
		this.notifyObservers(this);

	}
	
	public CollectionModel getCollectionById(int collectionId) {
		for(CollectionModel collection: this.collections) {
			if(collection.getId() == collectionId)
				return collection;
		}
		return null;
		
	}

	public void removeCollection(CollectionModel collection) {
		this.collections.remove(collection);
		this.notifyObservers(this);
		collection.notifyObservers(null);
	}


	public void removeDislike(OutfitModel dislike) {
		this.dislikes.remove(dislike);
		this.notifyObservers(this);

	}

	public void removeLike(OutfitModel like) {
		this.likes.remove(like);
		this.notifyObservers(this);
		
	}
}
