package Modele;

import Modele.Observable;

/**
 * This is the Observable class used in the Observer design pattern. It allows
 * its subclasses to notify, add and remove observers.
 * 
 * @author dinh_,tran_
 * @see Joueur
 * @see Partie
 * @see Tas
 */
public class Observable {
	/**
	 * The max number of observers an observable can have
	 */
	public final static int MAX_OBSERVES = 100;
	/**
	 * The array containing the Observers
	 */
	private Observer[] observers;
	/**
	 * The size of the array
	 */
	private int numberOfObservers;
	/**
	 * The state of the Observable either it has changed or not. To know when to
	 * notify Observers.
	 */
	private boolean hasChanged;

	/**
	 * The constructor class, initialise the attributes
	 */
	public Observable() {
		observers = new Observer[Observable.MAX_OBSERVES];
		numberOfObservers = 0;
		hasChanged = false;
	}

	/**
	 * This method allows an Observable to add an Observer into their array
	 * 
	 * @param o the observer to be added
	 */
	public void addObserver(Observer o) {
		if (numberOfObservers < Observable.MAX_OBSERVES) {
			observers[numberOfObservers] = o;
			numberOfObservers++;
		} else {
			System.err.println("Error: max number of observers reached");
		}
	}

	/**
	 * This method removes an observer from it's array
	 * 
	 * @param o the observer to be removed
	 */
	public void deleteObserver(Observer o) {
		boolean foundObserver = false;
		for (int i = 0; i < numberOfObservers; i++) {
			if (foundObserver == false) {
				if (observers[i].equals(o)) {
					observers[i] = null;
					foundObserver = true;
				}
			} else {
				observers[i - 1] = observers[i];
			}
		}
		if (foundObserver == true) {
			numberOfObservers--;
		}
	}

	/**
	 * This method notify all observers if the Observable has changed state, then
	 * reset the state. Else it doesn't notify
	 * 
	 * @param o the nature of the notification
	 */
	public void notifyObservers(Object o) {
		if (hasChanged == true) {
			for (int i = 0; i < numberOfObservers; i++) {
				observers[i].update(this, o);
			}
			hasChanged = false;
		}
	}

	/**
	 * Set the state to changed
	 */
	public void setChanged() {
		hasChanged = true;
	}

	/**
	 * Reset changes
	 */
	public void clearChanged() {
		hasChanged = false;
	}
}