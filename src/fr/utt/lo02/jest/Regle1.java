package fr.utt.lo02.jest;

import java.util.ArrayList;

public class Regle1 extends Regle {


	public int CompterNbCoeur(Jest j) {
		int nbCoeurs = 0;
		for (Carte c : j.listCarte) {
			if (c.enseigne == suits.COEUR) {
				nbCoeurs++;
			}
		}
		return nbCoeurs;
	}

	public void compterTrefleCarreauPique(Jest j) {
		for (Carte c : j.listCarte) {
			if (c instanceof SuitCards) {
				if (c.enseigne == suits.PIQUE || c.enseigne == suits.TREFLE) {
					j.setValeur(j.getValeur() + c.hauteur);
					ArrayList<Carte> temps = new ArrayList<Carte>();
					temps.remove(c);
					for (Carte l : temps) {
						if (c.hauteur == l.hauteur) {
							if (c.enseigne == suits.PIQUE || c.enseigne == suits.TREFLE) {
								j.setValeur(j.getValeur() + 2);
							}
						}
					}
					
				}
			} else if (c.enseigne == suits.CARREAU) {
				j.setValeur(j.getValeur() - c.hauteur);
			}
		}
	}*/

	public int trouverJoker(Jest j) {
		if (j.hasJoker()) {
			for (Carte c : j.listCarte) {
				if (c instanceof Joker) {
					return j.listCarte.indexOf(c);
				}
			}
		}
		return -1;
	}

	public void compterScoreCoeur(Jest j) {
		if (j.hasJoker()) {
			int pos = this.trouverJoker(j);
			if (this.CompterNbCoeur(j) == 0) {
				j.listCarte.get(pos).setHauteur(4);
			}
			else if(this.CompterNbCoeur(j) <= 3) {
				for (Carte c : j.listCarte) {
					if (c.enseigne == suits.COEUR){
						j.setValeur(j.getValeur() - c.hauteur);
					}
				}
			}
			else {
				j.listCarte.get(pos).setHauteur(0);
				for (Carte c : j.listCarte) {
					if (c.enseigne == suits.COEUR) {
						j.setValeur(j.getValeur() + c.hauteur);
					}
				}
			}
		}
		else {
			System.out.println("Pas de joker");
		}
	}
	
	public void compterAce(Jest j) {
		int nbCarreau = 0;
		int nbTrefle = 0;
		int nbPique = 0;
		int nbCoeur = 0;
		for (Carte c : j.listCarte) {
			if (c.enseigne == suits.CARREAU){
				nbCarreau++; 
			}
			else if (c.enseigne == suits.COEUR) {
				nbCoeur++;
			}
			else if (c.enseigne == suits.PIQUE) {
				nbPique ++;
			}
			else {
				nbTrefle++;
			}
		}
		if (nbCarreau != 0 || nbTrefle != 0 && nbCoeur != 0 || nbPique != 0) {
			for (Carte c : j.listCarte) {
				if (c.hauteur == 1) {
					c.setHauteur(5);
				}
			}
		}
	}
	public int compter(Jest j) {
		if (j.hasJoker()) {
			
		}
	}
}
