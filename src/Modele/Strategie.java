package Modele;

import java.util.ArrayList;

public interface Strategie {
	
	public int faireOffre(ArrayList<Carte> listCarte);
	
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueur);
	
}
