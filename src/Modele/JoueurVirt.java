package Modele;

import java.util.ArrayList;
import java.util.Random;

public class JoueurVirt extends Joueur {
    private int niveau;

    Strategie strat;
    
    public JoueurVirt(int niveau, String prenom) {
		this.prenom = prenom;
		this.main = new Main();
		this.jest = new Jest();
		this.estEnTour = false;
		this.Score = 0;
    	this.niveau = niveau;
    	if (this.niveau == 1) {
    		this.strat = new Facile();
    	}
    	else {
    		this.strat = new Difficile();
    	}
    }
    
    public void faireOffre() {
    	int temp = strat.faireOffre(this.main.listCarte);
    	super.faireOffre(temp);
    	
    }
    
    public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
    	return strat.choisirJoueur(listJoueurs);
    }
    
    public void prendreOffre(ArrayList<Joueur> listJoueurs) {
    	Random rd = new Random();
    	Joueur j = this.choisirJoueur(listJoueurs);
		int temp = rd.nextInt(listJoueurs.size());
    	super.prendreOffre(temp, j);
    }
}
