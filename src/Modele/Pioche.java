package Modele;
/**
 * This class inherits from the Tas, which is a composition of Cartes
 * @author dinh_, tran_
 * @see Tas, Carte
 *
 */
public class Pioche extends Tas {
	
	public Pioche() {
		super();
	}
	/**
	 * Shows the state if it is empty or not
	 * @return true if this instance of Pioche is empty, else return false
	 */
	public boolean estVide() {
		return this.getCarteTas().isEmpty();
	}
	
	
}
