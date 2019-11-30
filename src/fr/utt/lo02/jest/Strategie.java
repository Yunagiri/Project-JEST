package fr.utt.lo02.jest;

import java.util.ArrayList;

public interface Strategie {
	
	public int faireOffre();
	
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueur);
	
}
