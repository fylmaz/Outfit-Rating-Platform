package Repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import DataAccess.FileIO;
import Interfaces.IObservable;
import Interfaces.IObserver;
import Models.CollectionModel;
import Models.CommentModel;
import Models.OutfitModel;
import Models.UserModel;
import Views.StatsView;

/**
 * Initializes repositories using data from FileIO
 * Observes Models to save them to files when any change occurs
 */
public class RepositoryHandler implements IObserver {
	private UserRepository userRepository;
	private OutfitRepository outfitRepository;
	private FileIO fileIO;
	
	//it will observe all outfits and users to update stats
	private StatsView statsObserver;
	
	public RepositoryHandler(StatsView statsObserver) throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		this.statsObserver = statsObserver;
		this.userRepository = new UserRepository();
		this.outfitRepository = new OutfitRepository();
		this.fileIO = new FileIO();
		this.parseOutfitJSON();
		this.parseUserXML();
		
        for(UserModel user: this.userRepository.getAllUsers()) {
      	  user.addObserver(this.statsObserver);
        }
        
        for(OutfitModel outfit: this.outfitRepository.getOutfits()) {
        	outfit.addObserver(this.statsObserver);
        }

	}
	
	
	/**
	 * Save moodels to files when any change occurs
	 */
	@SuppressWarnings("unchecked")
	public void update(IObservable observable) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(OutfitModel outfit: this.outfitRepository.getOutfits()) {
			jsonArray.add(outfit.toJSONObject());
		}
		jsonObject.put("Outfits",jsonArray);
		try {
			this.fileIO.writeOutfitData(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String usersXMLString = "<Users>";
			for(UserModel user: this.userRepository.getAllUsers())
				usersXMLString += user.toXMLString();
		usersXMLString += "</Users>";
		this.fileIO.writeUserData(usersXMLString);
        

	}
		
	public void parseOutfitJSON() throws FileNotFoundException, IOException, ParseException {
		JSONArray outfits = (JSONArray) fileIO.readOutfitData().get("Outfits");
		Iterator<JSONObject> iterator = outfits.iterator();
		while (iterator.hasNext()) {
			JSONObject outfit = iterator.next();
			int id = Integer.parseInt((String) outfit.get("Id"));
			String brandName = (String) outfit.get("BrandName");
			String clothingType = (String) outfit.get("ClothingType");
			String suitableOccasion = (String) outfit.get("SuitableOccasion");
			String gender = (String) outfit.get("Gender");
			String size = (String) outfit.get("Size");
			String color = (String) outfit.get("Color");
			OutfitModel outfitModel = new OutfitModel(id, brandName, clothingType, suitableOccasion, gender, size, color);
		
			JSONArray comments = (JSONArray) outfit.get("Comments");
			Iterator<JSONObject> iterator2 = comments.iterator();
			while(iterator2.hasNext()) {
				JSONObject comment = iterator2.next();
				int commentId = Integer.parseInt((String) comment.get("Id"));
				String commentOwnerUsername = (String) comment.get("CommentOwnerUsername");
				String text = (String) comment.get("Text");
				CommentModel commentModel = new CommentModel(commentId, commentOwnerUsername, text);
				if(commentId > this.outfitRepository.getMaxCommentId())
					outfitRepository.setMaxCommentId(commentId);
				outfitModel.addComment(commentModel);
			}		
			outfitModel.addObserver(this);
			this.outfitRepository.getOutfits().add(outfitModel);
		}
	
	}
	
	public void parseUserXML() throws SAXException, IOException, ParserConfigurationException {
		   Map<UserModel, ArrayList<Integer>> userFollowersMap = new HashMap<UserModel, ArrayList<Integer>>();
		   Map<UserModel, ArrayList<Integer>> userFollowingsMap = new HashMap<UserModel, ArrayList<Integer>>();
	       NodeList usersList = fileIO.readUserData().getElementsByTagName("User");
	          for (int usersVar = 0; usersVar < usersList.getLength(); usersVar++) {
	              Node userNode = usersList.item(usersVar);
	              if (userNode.getNodeType() == Node.ELEMENT_NODE) {
	                  Element userElement = (Element) userNode;

	                 
	                  int id = Integer.parseInt(userElement.getElementsByTagName("Id").item(0).getTextContent());
	                  String username = userElement.getElementsByTagName("Username").item(0).getTextContent();
	                  String name = userElement.getElementsByTagName("Name").item(0).getTextContent();
	                  String surname = userElement.getElementsByTagName("Surname").item(0).getTextContent();
	                  String gender = userElement.getElementsByTagName("Gender").item(0).getTextContent();
	                  String password = userElement.getElementsByTagName("Password").item(0).getTextContent();
	                  UserModel userModel = new UserModel(id, username, name, surname, gender, password);
	                  
	                  List<Integer> followers = new ArrayList<Integer>();
	                  NodeList followersList = userElement.getElementsByTagName("FollowerId");
	                  for (int followersVar = 0; followersVar < followersList.getLength(); followersVar++) {
	                      Node followersNode = followersList.item(followersVar);
	                      if (followersNode.getNodeType() == Node.ELEMENT_NODE) {
	                          Element followersElement = (Element) followersNode;
	                          followers.add(Integer.parseInt(followersElement.getTextContent()));
	                      	}
	                  }
	                  
	                  List<Integer> followings = new ArrayList<Integer>();
	                  NodeList followingsList = userElement.getElementsByTagName("FollowingId");
	                  for (int followingsVar = 0; followingsVar < followingsList.getLength(); followingsVar++) {
	                      Node followingsNode = followingsList.item(followingsVar);
	                      if (followingsNode.getNodeType() == Node.ELEMENT_NODE) {
	                          Element followingsElement = (Element) followingsNode;
	                          followings.add(Integer.parseInt(followingsElement.getTextContent()));
	                      	}
	                  }
	                  

	                  NodeList collectionsList = userElement.getElementsByTagName("Collection");
	                  for (int collectionsVar = 0; collectionsVar < collectionsList.getLength(); collectionsVar++) {
	                      Node collectionsNode = collectionsList.item(collectionsVar);
	                      if (collectionsNode.getNodeType() == Node.ELEMENT_NODE) {
	                          Element collectionsElement = (Element) collectionsNode;
	    	                  int collectionId = Integer.parseInt(collectionsElement.getElementsByTagName("CollectionId").item(0).getTextContent());
	    	                  String collectionName = collectionsElement.getElementsByTagName("CollectionName").item(0).getTextContent();
	    	                  CollectionModel collection = new CollectionModel(collectionId, collectionName);

	    	                  userModel.addCollection(collection);
	    	                  
	    	                  NodeList outfitsList = collectionsElement.getElementsByTagName("OutfitId");
	    	                  for (int outfitsVar = 0; outfitsVar < outfitsList.getLength(); outfitsVar++) {
	    	                      Node outfitsNode = outfitsList.item(outfitsVar);
	    	                      if (outfitsNode.getNodeType() == Node.ELEMENT_NODE) {
	    	                          Element outfitsElement = (Element) outfitsNode;
	    	                          int outfitId =  Integer.parseInt(outfitsElement.getTextContent());
	    	                          OutfitModel outfit = this.outfitRepository.getOutfitById(outfitId);
	    	                          collection.addOutfit(outfit);
	    	                          outfit.addObserver(this);
	    	                      	}
	    	                  }
	    	                  collection.addObserver(this);
	                      	}
	                  }
	                  
	                  
	                  NodeList likesList = userElement.getElementsByTagName("LikeId");
	                  for (int likesVar = 0; likesVar < likesList.getLength(); likesVar++) {
	                      Node likesNode = likesList.item(likesVar);
	                      if (likesNode.getNodeType() == Node.ELEMENT_NODE) {
	                          Element likesElement = (Element) likesNode;
	                          int likeId = Integer.parseInt(likesElement.getTextContent());
	                          OutfitModel likedOutfit= this.outfitRepository.getOutfitById(likeId);
	                          likedOutfit.addLiker(userModel);
	                          userModel.addLike(likedOutfit);
	                      	}
	                  }
	                  
	                  

	                  NodeList dislikesList = userElement.getElementsByTagName("DislikeId");
	                  for (int dislikesVar = 0; dislikesVar < dislikesList.getLength(); dislikesVar++) {
	                      Node dislikesNode = dislikesList.item(dislikesVar);
	                      if (dislikesNode.getNodeType() == Node.ELEMENT_NODE) {
	                          Element dislikesElement = (Element) dislikesNode;
	                          int dislikeId = Integer.parseInt(dislikesElement.getTextContent());
	                          OutfitModel dislikedOutfit = this.outfitRepository.getOutfitById(dislikeId);
	                          dislikedOutfit.addDisliker(userModel);
	                          userModel.addDislike(dislikedOutfit);

	                      	}
	                  }
	                  userFollowersMap.put(userModel, (ArrayList<Integer>) followers);
	                  userFollowingsMap.put(userModel, (ArrayList<Integer>) followings);
	                  userModel.addObserver(this);
	                  this.userRepository.getAllUsers().add(userModel);

	              }
	          }
	          
	          for (Map.Entry<UserModel, ArrayList<Integer>> entry : userFollowersMap.entrySet()) {
	        	    UserModel key = entry.getKey();
	        	    ArrayList<Integer> value = entry.getValue();
	        	    for(int valueId: value ) {
	        	    	UserModel mdl = userRepository.getUserById(valueId);
	        	    	key.addFollower(mdl);
	        	    }
	        	}
	          
	          for (Map.Entry<UserModel, ArrayList<Integer>> entry : userFollowingsMap.entrySet()) {
	        	    UserModel key = entry.getKey();
	        	    ArrayList<Integer> value = entry.getValue();
	        	    for(int valueId: value ) {
	        	    	UserModel mdl = userRepository.getUserById(valueId);
	        	    	key.addFollowing(mdl);
	        	    }
	        	}
	          

	         
	}
		
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public OutfitRepository getOutfitRepository() {
		return outfitRepository;
	}	
}
