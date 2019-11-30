package fr.utt.lo02.jest;

import java.util.ArrayList;

public class Facile implements Strategie {
	
	public int faireOffre() {
		int temp = (int) (2*Math.random() - 1);
		return temp;
	}


	public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
		int temp = (int) (listJoueurs.size()*Math.random() - listJoueurs.size());
		return listJoueurs.get(temp);
	}
}
