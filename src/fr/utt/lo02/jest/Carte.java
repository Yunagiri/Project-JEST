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
	

    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append("La hauteur de cette carte est");
    		sb.append(this.hauteur);
    		sb.append(" La condition de la carte est");
    		sb.append(this.condition);
    		
    		sb.toString();
    	}
    	
    		return sb.append("La carte est face cachee").toString();

    }


}
