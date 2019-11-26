package fr.utt.lo02.jest;

public class Carte {

	protected int hauteur;

	protected boolean faceCachee;
	
	protected Conditions condi;
	
	protected suits enseigne;

	//Constructor for SuitCards
	public Carte(int hauteur, boolean faceCachee, Conditions condi, suits enseigne) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condi = condi;
		this.enseigne = enseigne;
	}
	
	//Constructor for Joker
	public Carte(int hauteur, boolean faceCachee, Conditions condi) {
		this.hauteur = hauteur;
		this.faceCachee = faceCachee;
		this.condi = condi;
	}
	

	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (this.faceCachee) {
			return sb.append("La carte est face cachée").toString();
		}
		else {
			sb.append(this.condi.montrer());
			return sb.toString();
		}
	}
}