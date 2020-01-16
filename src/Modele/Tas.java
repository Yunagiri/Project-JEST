package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the superclass for all collections of cards, it contains a list
 * of cards and is observable.
 * 
 * @author dinh_, tran_
 * @see Observable, Pioche, Main, Jest
 *
 */
public class Tas extends Observable {

	/**
	 * Number of cards in the array
	 */
	public int nombreDeCartes;

	/**
	 * 
	 */
	private ArrayList<Carte> listCarte = new ArrayList<Carte>();

	public ArrayList<Carte> getCarteTas() {
		return this.listCarte;
	}

	/**
	 * This is the constructor of Tas, no arguments means it will start empty,
	 * number of cards is 0
	 */
	public Tas() {
		this.nombreDeCartes = 0;
	}

	/**
	 * Put a certain number of cards from a Tas into this Tas
	 * 
	 * @param nombreDeCartes   number of cards added
	 * @param listCarteAjoutee the Tas from which the cards were added
	 */
	public Tas(int nombreDeCartes, ArrayList<Carte> listCarteAjoutee) {
		this.nombreDeCartes = nombreDeCartes;
		// Take each card in the listCarte and put them into this Tas's listCarte
		for (int i = 0; i < listCarteAjoutee.size(); i++) {
			this.listCarte.set(i, listCarteAjoutee.get(i));
		}
	}

	/**
	 * Add cards to the Tas
	 * 
	 * @param carteAjoutee the card to add to the Tas
	 */
	public void ajouterCartes(Carte carteAjoutee) {
		this.nombreDeCartes++;
		this.listCarte.add(carteAjoutee);
	}

	/**
	 * Shuffle the deck
	 */
	public void melanger() {
		int nombreAleat;

		for (int i = 0; i < this.nombreDeCartes; i++) {
			// Generates a random number between and the size of the listCarte
			nombreAleat = (int) Math.abs(2 * this.nombreDeCartes * Math.random() - this.nombreDeCartes);

			// Swap a random card with the card in the i position in listCartes
			Carte inter = this.listCarte.get(nombreAleat);
			this.listCarte.set(nombreAleat, this.listCarte.get(i));
			this.listCarte.set(i, inter);
		}
	}

	/**
	 * Distribute a card from this Tas to another one, follow the LIFO
	 * 
	 * @param autreTas the other Tas
	 */
	public void distribuer(Tas autreTas) {
		autreTas.ajouterCartes(this.listCarte.get(this.nombreDeCartes - 1));
		this.listCarte.remove(this.nombreDeCartes - 1);
		this.nombreDeCartes--;

	}

}
