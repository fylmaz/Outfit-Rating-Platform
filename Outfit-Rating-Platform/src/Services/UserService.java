package Services;

import java.util.List;

import Models.CollectionModel;
import Models.UserModel;
import Repositories.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository; 
	}

	public UserModel getUserByUsername(String username) {
		return this.userRepository.getUserByUsername(username);
	}
	
	public List<UserModel> getAllUsers(){
		return this.userRepository.getAllUsers();
	}
	
	public CollectionModel newCollection() {
		return this.userRepository.newCollection();
	}

	public UserModel getMostFollowedUser() {
		return this.userRepository.getMostFollowedUser();
	}
	 
}
