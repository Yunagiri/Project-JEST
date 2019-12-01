package fr.utt.lo02.jest;

import java.util.Iterator;

public class Jest extends Tas implements Score {
    private int valeur;

    public Joueur joueur;
    
    public Jest() {
    	super();
    	this.valeur =0;
    }
    
    
    public void accepte(VisitorDeJest j) {
    	j.visiter(this);
    }
    
    
    
}
