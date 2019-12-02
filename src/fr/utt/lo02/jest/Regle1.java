package fr.utt.lo02.jest;

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

	/*public int compter(Jest j) {
		for (Carte c : j.listCarte) {
			if (c instanceof SuitCards) {
				if (c.enseigne == suits.PIQUE || c.enseigne == suits.TREFLE) {
					j.setValeur(j.getValeur() + c.hauteur);
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

	public int compterScoreCoeur(Jest j) {
		if (this.hasJoker(j)) {
			if (this.CompterNbCoeur(j) == 0) {

			}
		} else {
			return 0;
		}
	}
	public int compter(Jest j) {
		if (j.hasJoker()) {
			
		}
	}
}
