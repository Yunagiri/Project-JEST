package fr.utt.lo02.jest;

import java.util.ArrayList;

public class JoueurVirt extends Joueur {
    private int niveau;

    Strategie strat;
    
    public JoueurVirt(int niveau) {
    	this.niveau = niveau;
    	if (this.niveau == 1) {
    		this.strat = new Facile();
    	}
    	else {
    		this.strat = new Difficile();
    	}
    }
    
    public void faireOffre() {
    	int temp = strat.faireOffre();
    	super.faireOffre(temp);
    	
    }
    
    public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
    	return strat.choisirJoueur(listJoueurs);
    }
    
    public void prendreOffre(ArrayList<Joueur> listJoueurs) {
    	Joueur j = this.choisirJoueur(listJoueurs);
    	int temp = (listJoueurs.size()*2 - listJoueurs.size());
    	super.prendreOffre(temp, j);
    }
}
