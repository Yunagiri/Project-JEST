package Modele;

import Modele.Observable;

public class Observable {
	public final static int MAX_OBSERVES =100;
	private Observer[] observers;
	private int numberOfObservers;
	private boolean hasChanged;
	
	public Observable() {
		observers = new Observer[Observable.MAX_OBSERVES];
		numberOfObservers = 0;
		hasChanged = false;
	}
	
	public void addObserver(Observer o) {
		if ( numberOfObservers < Observable.MAX_OBSERVES) {
			observers[numberOfObservers] = o;
			numberOfObservers ++;
		} else {
			System.err.println("Error: max number of observers reached");
		}
	}
	
	public void deleteObserver(Observer o) {
		boolean foundObserver = false;
		for ( int i=0; i < numberOfObservers; i++) {
			if(foundObserver == false) {
				if ( observers[i].equals(o)) {
					observers[i] = null;
					foundObserver = true;
				} 
			} else { 
				observers[i-1] = observers[i];
			}
		}
		if ( foundObserver == true) {
			numberOfObservers--;
		}
	}
	public void notifyObservers(Object o) {
		if (hasChanged == true) {
			for (int i=0; i< numberOfObservers; i++) {
				observers[i].update(this,o);
			}
			hasChanged = false;
		}
	}
	public void setChanged() {
		hasChanged = true;
	}
	public void clearChanged() {
		hasChanged = false;
	}
}