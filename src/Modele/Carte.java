package Modele;
/**
 * <b>This class defines the different types of cards used in the game, and their possible actions.</b>
 * <p>
 * It contains the constructors for its subclasses, SuitCards and Joker.
 * </p>
 * 
 * @see SuitCards,Joker
 * @author dinh_ ,tran_
 *
 */
public class Carte {
	/**
	 * The number displayed on the card
	 * @see Carte#getHauteur()
	 */
	protected int hauteur;
	/**
	 * The state of the card
	 * @see Carte#getFaceCachee()
	 */
	protected boolean faceCachee;
	/**
	 * The condition of the card, if it is the trophy this round
	 * @see Carte#getCondi()
	 * @see Trophee
	 */
	protected Conditions condi;
	/**
	 * This card's suit
	 * @see Carte#getEnseigne()
	 */
	protected suits enseigne;
	/**
	 * This card's total value, calculated from its suit and face value. Not to be mistaken as the hauteur. Used to calculate jest's scores.
	 * The value is determined as the following:
	 *<ul>
	 *<li>The suit power: Heart < Diamond < Club < Spade.
	 *<li>The number ranging from 1 to 4.
	 *</ul>
	 *
	 *For example, the Ace of Heart initial value is 1, the 4 of Spade value is 16.
	 * @see Carte#getValeur()
	 */
	protected int valeur;

	/**
	 * This is the constructor for the subclass SuitCards, it is used when creating a new SuitCard.
	 * @param hauteur the face value of the card
	 * @param faceCachee the state of the card, face-up or face down.
	 * @param condi indicates the condition that enables the players to take this card as a trophy.
	 * @param enseigne the suit of the card
	 */
	public Carte(int hauteur, boolean faceCachee, Conditions condi, suits enseigne) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condi = condi;
		this.enseigne = enseigne;
	}
	/**
	 * This is the constructor for the Joker, since it doesn't have a suit, it needs a constructor that doesn't take 
	 * suits as a parameter.
	 * @param hauteur the face value of the card
	 * @param faceCachee the state of the card, face-up or face-down
	 * @param condi the condition that enables players to take this card as a trophy
	 */
	public Carte(int hauteur, boolean faceCachee, Conditions condi) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condi = condi;
	}
	public boolean getFaceCachee() {
		return this.faceCachee;
	}
	public Conditions getCondi () {
		return this.condi;
	}
	public suits getEnseigne() {
		return this.enseigne;
	}

	public int getHauteur() {
		return this.hauteur;
	}
	public boolean getEtat() {
		return this.faceCachee;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public  int getValeur() {
		return valeur;
	}

	/**
	 * This method shows the card's name e.g "1 de Coeur" and it's value on a console command line.
	 * @return a line on the console command, either the card is face down and you can't see it, or it's face up and will return 
	 * its characteristics.
	 */
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (this.faceCachee) {
			return sb.append("La carte est face cachee").toString();
		}
		else {
			sb.append(this.condi.montrer());
			sb.append(" Valeur: ");
			sb.append(this.valeur);
			return sb.toString();
		}
	}
}