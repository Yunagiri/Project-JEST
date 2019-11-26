package fr.utt.lo02.jest;

import java.util.HashMap;

public class Carte {

	protected int hauteur;

	protected boolean faceCachee;
	protected condition condition;
	protected suits enseigne;

	// Storing the win condition, a set of action and a suit, or a action and null
	// if it doesn't need a suit to be done.
	protected HashMap<condition, suits> winCond;

	// Constructor for class SuitCards having ensconditon
	public Carte(int hauteur, boolean faceCachee, condition cond, suits ensCondition) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.winCond.put(cond, ensCondition);
	}

	// Constructor for class Suitcards non having enscondition
	public Carte(int hauteur, boolean faceCachee, condition cond) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condition = cond;
	}

	// Constructor for class Joker: the Joker doesn't have hauteur
	//public Carte(boolean faceCachee, condition cond, suits ensCondition) {
	//	this.faceCachee = faceCachee;
	//	this.winCond.put(cond, ensCondition);
//	}

	/*
	 * The class Carte can only show the condition of the card, the rest of the
	 * attributes is left to the subclasses SuitCards( suit and hauteur) and Joker (
	 * doesn't have hauteur)
	 */
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (!this.faceCachee) {
			sb.append(" La condition de cette carte est");
			sb.append(this.condition)
			sb.append(this.winCond.toString());
			sb.toString();
			return sb.toString();
		}

		// If the condition was not met, this method doesn't allow the player calling to
		// see it.
		else {
			return sb.append("La carte est face cachee").toString();
		}
		
	}

	public static void main(String[] args) {
		SuitCards carte = new SuitCards(5, true, condition.LOWEST, suits.COEUR, suits.PIQUE);
		carte.winCond.values();
	}
}