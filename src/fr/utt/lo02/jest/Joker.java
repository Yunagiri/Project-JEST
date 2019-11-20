package fr.utt.lo02.jest; 

public class Joker extends Carte {
	
	//The value of the Joker card depends on the amount of hearts the player possess in their hands 
	public Joker(int hauteur,boolean faceCachee, String condition) {
		super(faceCachee, condition );
	}
	
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
		sb.append("La carte est un Joker");
		return sb.toString();
    }
}
