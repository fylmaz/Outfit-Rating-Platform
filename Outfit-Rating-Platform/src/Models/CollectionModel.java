package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IObservable;
import Interfaces.IObserver;

public class CollectionModel implements IObservable {
	private int id;
	private String name;
	private List<OutfitModel> outfits;
	private List<IObserver> observers;
	
	public CollectionModel(int id, String name) {
		this.id = id;
		this.name = name;
		this.outfits = new ArrayList<OutfitModel>();
		this.observers = new ArrayList<IObserver>();
	}
	
	public String toXMLString() {
		String str = "<Collection>";
		str += "<CollectionId>" + this.id + "</CollectionId>";
		str += "<CollectionName>" + this.name + "</CollectionName>";
		str += "<OutfitIds>";
			for(OutfitModel outfit: this.outfits)
				str += "<OutfitId>" + outfit.getId() + "</OutfitId>";
		str += "</OutfitIds>";
		str += "</Collection>";
		return str;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyObservers(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.notifyObservers(this);
	}

	public List<OutfitModel> getOutfits() {
		return outfits;
	}

	public void setOutfits(List<OutfitModel> outfits) {
		this.outfits = outfits;
		this.notifyObservers(this);
	}

	public void addObserver(IObserver observer) {
		this.observers.add(observer);	
	}

	public void notifyObservers(IObservable observable) {
		for(IObserver observer: this.observers) {
			observer.update(observable);
		}
	}

	public OutfitModel getOutfitById(int id) { 
		for(OutfitModel outfit: this.outfits) {
			if(outfit.getId() == id)
				return outfit;
		}
		return null;
	}

	public void addOutfit(OutfitModel outfit) {
		this.outfits.add(outfit);
		this.notifyObservers(this);
	}

	public void removeOutfit(OutfitModel outfit) {
		this.outfits.remove(outfit);
		this.notifyObservers(this);
	}


}
