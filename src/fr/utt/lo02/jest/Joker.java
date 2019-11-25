package fr.utt.lo02.jest; 

public class Joker extends Carte {
	
	//The value of the Joker card depends on the amount of hearts the player possess in their hands 
	public Joker(int hauteur,boolean faceCachee, String condition) {
		super(faceCachee, condition );
	}
	
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append(super.toString());
    		sb.append("Why so serious?");
    		return sb.toString();
    	}
    	
    	/*If faceCachee is true, return the toString() method from Carte class
    	 * which doesn't show the card if it's face down and return a String 
    	 * saying why it doesnt.
    	 */
		return super.toString();

    }
}
