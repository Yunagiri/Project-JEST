package fr.utt.lo02.jest;

public class Jest extends Tas implements Score {
    private int valeur;

    public Joueur joueur;
    
    public Jest() {
    	super();
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    public void accepte(VisitorDeJest j) {
    	j.visiter(this);
    }
    
}
