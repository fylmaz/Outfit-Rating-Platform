package Interfaces;

public interface IObservable {
	
	public void addObserver(IObserver observer);
	
	public void notifyObservers(IObservable observable);


}
