package fr.utt.lo02.jest;

import java.util.ArrayList;
import java.util.Random;

public class Facile implements Strategie {
	
	public int faireOffre(ArrayList<Carte> listCarte) {
		int nombreAleatoire = 0 +(int)(Math.random() * ((1 - 0) + 1));
		return nombreAleatoire;
	}


	public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
		Random random = new Random();
		int temp = random.nextInt(listJoueurs.size());
		return listJoueurs.get(temp);
	}
}
