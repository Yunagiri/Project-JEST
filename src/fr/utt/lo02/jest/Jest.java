package fr.utt.lo02.jest;

public class Jest extends Tas implements Score {
    private int valeur;

    public Joueur joueur;
    
    public Jest() {
    	super();
    }
    
    public void accepte(VisitorDeJest j) {
    	j.visiter(this);
    }
    
}
