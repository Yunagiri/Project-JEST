package Modele;

public class Carte {

	protected int hauteur;

	public boolean faceCachee;
	
	protected Conditions condi;
	
	protected suits enseigne;
	
	public int valeur;

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
	public boolean getFaceCachee() {
		return this.faceCachee;
	}
	public Conditions getCondi () {
		return this.condi;
	}
	public suits getEnseigne() {
		return this.enseigne;
	}
	public int getValeur() {
		return this.valeur;
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