package Modele;

/**
 * This is the subclass of the class Cartes, it's main purpose is to display descriptions of suit cards on the console
 * @author dinh_, tran_
 * @see Carte
 */
public class SuitCards extends Carte {
	
	/**
	 * This is the constructor, called from it's superclass
	 * @param hauteur face value of the card
	 * @param faceCachee the state of the card, face up or down
	 * @param cond the condition to win this card if it was a trophy card
	 * @param enseigne the suit of the card
	 */
	public SuitCards(int hauteur, boolean faceCachee, Conditions cond, suits enseigne) {
		super(hauteur, faceCachee, cond, enseigne);
	}
	
	
	/**
	 * Shows the card's characteristics with a string on the console
	 */
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (!this.faceCachee) {
			sb.append("La carte est un ");
			sb.append(this.hauteur);
			sb.append(" de ");
			sb.append(this.enseigne);
			sb.append(super.montrer());
			return sb.toString();
		}
		
		return sb.append("La carte est face cachee").toString();
	}
}
