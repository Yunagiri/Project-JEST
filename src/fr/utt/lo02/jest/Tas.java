package fr.utt.lo02.jest;

import java.util.ArrayList;
import java.util.List;

public class Tas {

	protected int nombreDeCartes;

	// The array used to stock the cards
	public List<Carte> listCarte = new ArrayList<Carte>();

	// Constructors
	public Tas() {
		this.nombreDeCartes = 0;
	}

	public Tas(int nombreDeCartes, ArrayList<Carte> listCarteAjoutee) {
		this.nombreDeCartes = nombreDeCartes;
		// Take each card in the listCarte and put them into this Tas's listCarte
		for (int i = 0; i < listCarteAjoutee.size(); i++) {
			this.listCarte.set(i, listCarteAjoutee.get(i));
		}
	}

	// Add 1 card, disregarding anything happening to the party whose card got
	// taken.
	// Must be used inside other more complete methods.
	public void ajouterCartes(Carte carteAjoutee) {
		this.nombreDeCartes++;
		this.listCarte.add(carteAjoutee);
	}

	// Shuffle Tas
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

	// Distribute a card from this Tas to another one, the card distributed is the
	// last one to arrive (LIFO)
	public void distribuer(Tas autreTas) {
		autreTas.ajouterCartes(this.listCarte.get(this.nombreDeCartes - 1));
		this.listCarte.remove(this.nombreDeCartes - 1);
		this.nombreDeCartes--;

	}

}
