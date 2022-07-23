package Repositories;

import java.util.ArrayList;
import java.util.List;
import Models.CollectionModel;
import Models.OutfitModel;
import Models.UserModel;

public class UserRepository {
	private List<UserModel> users;

	
	public UserRepository() {
		this.users = new ArrayList<UserModel>();
	}

	public List<UserModel> getAllUsers() {
		return this.users;
	}

	public CollectionModel newCollection() {
		return new CollectionModel(this.getMaxCollectionId() + 1, "");
	}

	private int getMaxCollectionId() {
		int max = 0;
		for(UserModel user: this.users) {
			for(CollectionModel collection: user.getCollections()) {
				if(collection.getId() > max)
					max = collection.getId();
			}
		}
		return max;
	}

	public UserModel getUserById(int id) {
		for(UserModel user: this.users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	public UserModel getUserByUsername(String username) {
		for(UserModel user: this.users) {
			if(user.getUsername().equals(username))
				return user;
		}
		return null;
	}

	public UserModel getMostFollowedUser() {
		UserModel mostFollowed = null;
		int max = -1;
		for(UserModel user: this.users) {
			if(user.getFollowers().size() > max) {
				max = user.getFollowers().size();
				mostFollowed = user;
			}
		}
		return mostFollowed;
	}
	
	
}
