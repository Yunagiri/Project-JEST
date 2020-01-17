package Modele;

/**
 * This class represents the condition inscribed at the bottom of each card, for
 * whenever it is a trophy card. The card will be awarded to the player meeting
 * the requirements.
 * 
 * @author dinh_, tran_
 *
 */
public class Conditions {
	/**
	 * This is the suit of a condition, used for HIGHEST and LOWEST actions
	 */
	protected suits enseigne;
	/**
	 * This is the action needed to do in order to acquire the trophy
	 */
	protected action cond;
	/**
	 * This boolean indicates if whether the condition needs a suit to be valid or
	 * not, e.g a MAJORITY action doesn't need a suit to be used but needs a face
	 * value.
	 */
	protected boolean hasEnseigne;
	/**
	 * The face-up value needed for the MAJORITY action
	 */
	protected int hauteur;

	/**
	 * This is the constructor for conditions that need a suit in order to be met
	 * 
	 * @param cond     the action, possible amongst HIGHEST, LOWEST
	 * @param enseigne the suit required, e.g LOWEST CLUB means the jest with the
	 *                 lowest face value club card wins the trophy.
	 */
	public Conditions(action cond, suits enseigne) {
		this.enseigne = enseigne;
		this.cond = cond;
		this.hasEnseigne = true;
	}

	/**
	 * This is the constructor for conditions that need a face value in order to be
	 * met
	 * 
	 * @param cond    the only action that requires a face value, MAJORITY
	 * @param hauteur the face value required
	 */
	public Conditions(action cond, int hauteur) {
		this.cond = cond;
		this.hasEnseigne = false;
		this.hauteur = hauteur;
	}

	/**
	 * The constructor of conditions that stand on their own.
	 * 
	 * @param cond Possible choices are BEST, BEST_NOJOKER, and JOKER
	 */
	public Conditions(action cond) {
		this.cond = cond;
		this.hasEnseigne = false;
	}

	/**
	 * This method return the nature of the condition. Will be appended to the
	 * description of the card in the console.
	 * 
	 * @return a string describing the condition
	 */
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (this.hasEnseigne == true) {
			sb.append(". La condition pour gagner est: ");
			sb.append(this.cond);
			sb.append(" ");
			sb.append(this.enseigne);

			return sb.toString();
		}

		else {
			sb.append(". La condition pour gagner est: ");
			sb.append(this.cond);
			if (this.hauteur != 0) {
				sb.append(this.hauteur);
			}
			return sb.toString();
		}
	}

}
