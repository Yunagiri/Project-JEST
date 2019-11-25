package fr.utt.lo02.jest;

public class Carte {
	
    protected int hauteur;

    protected boolean faceCachee;

    protected String condition;
    
    //Constructor for class SuitCards
	public Carte(int hauteur, boolean faceCachee, String condition) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condition = condition;
	}
	
	//Constructor for class Joker: the Joker doesn't have hauteur
	public Carte(boolean faceCachee, String condition) {
		this.faceCachee = faceCachee;
		this.condition = condition;
	}
	

	/* The class Carte can only show the condition of the card, the rest of the attributes 
	 * is left to the subclasses SuitCards( suit and hauteur) and Joker ( doesn't have hauteur)
	 */
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append(" La condition de cette carte est");
    		sb.append(this.condition);
    		sb.toString();
    		return sb.toString();
    	}
    	
    	//If the condition was not met, this method doesn't allow the player calling to see it.
    	return sb.append("La carte est face cachee").toString();
    }


}
