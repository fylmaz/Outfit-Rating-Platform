package Models;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import Interfaces.IObservable;
import Interfaces.IObserver;

public class CommentModel implements IObservable {
	private int id;
	private String commentOwnerUsername;
	private String text;
	private List<IObserver> observers;
	
	public CommentModel(int id, String commentOwnerUsername, String text) {
		this.id = id;
		this.commentOwnerUsername = commentOwnerUsername;
		this.text = text;
		this.observers = new ArrayList<IObserver>();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Id", String.valueOf(this.id));
		jsonObj.put("CommentOwnerUsername", this.commentOwnerUsername);
		jsonObj.put("Text", this.text);
		return jsonObj;
	}
	
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}

	public void notifyObservers(IObservable observable) {
		for(IObserver o: this.observers) {
			o.update(observable);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyObservers(this);

	}

	public String getcommentOwnerUsername() {
		return this.commentOwnerUsername;
	}

	public void setCommentOwnerUsername(String commentOwnerUsername) {
		this.commentOwnerUsername = commentOwnerUsername;
		this.notifyObservers(this);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.notifyObservers(this);
	}


}
