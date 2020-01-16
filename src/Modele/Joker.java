package Modele; 

/**
 * This class represents the joker card in the game. It doesn't have a suit and has a face value of 0. 
 * @author dinh_ 
 *
 */
public class Joker extends Carte {
	
	/**
	 * The condition of the joker is always known, since there's only 1 joker in the game.
	 */
	private static Conditions jokeCond = new Conditions(action.BEST);
	
	/**
	 * The constructor, called from the superclass Carte.
	 */
	public Joker() {
		super(0, true, jokeCond);
	}
	
	/**
	 * This method returns the description of the card, and whether it's face up or down.
	 */
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.getFaceCachee()) {
    		sb.append("Why so serious? ");
    		sb.append(super.montrer());

    		return sb.toString();
    	}
		return super.montrer().toString();

    }
}
