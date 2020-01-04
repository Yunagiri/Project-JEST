package Modele;

import java.util.ArrayList;
import java.util.Random;

public class Difficile implements Strategie {

	@Override
	public int faireOffre(ArrayList<Carte> listCarte) {
		// TODO Auto-generated method stub
		Carte cmin = listCarte.get(0);
		for (Carte c : listCarte) {
			if (c.valeur < cmin.valeur) {
				cmin = c;
			}
		}
		return listCarte.indexOf(cmin);
		
	}
	@Override
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueur) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int temp = random.nextInt(listJoueur.size());
		return listJoueur.get(temp);
	}
	
	
}
