package fr.utt.lo02.jest;

import java.util.ArrayList;

public class Regle1 extends Regle  {
	protected int score ;
	public Regle1() {
		this.score = 0;
	}


	public int CompterNbCoeur(ArrayList<Carte> j) {
		int nbCoeurs = 0;
		for (Carte c : j) {
			if (c.enseigne == suits.COEUR) {
				nbCoeurs++;
			}
		}
		return nbCoeurs;
	}

	public void compterTrefleCarreauPique(ArrayList<Carte> j) {
		ArrayList<Carte> temps = new ArrayList<Carte>();
		temps.addAll(j);
		for (Carte c : j) {
			if (c instanceof SuitCards) {
				if (c.enseigne == suits.PIQUE || c.enseigne == suits.TREFLE) {
					//j.setValeur(j.getValeur() + c.hauteur);
					temps.remove(c);
					for (Carte l : temps) {
						if (c.hauteur == l.hauteur) {
							if ((l.enseigne == suits.PIQUE || l.enseigne == suits.TREFLE) && l.enseigne != c.enseigne) {
								//j.setValeur(j.getValeur() + 2);
								c.hauteur += 2;
								j.get(j.indexOf(l)).hauteur +=2;
								
							}
						}
					}
					
				} 
				else if (c.enseigne == suits.CARREAU) {
					//j.setValeur(j.getValeur() - c.hauteur);
					c.hauteur = -c.hauteur;
				}
			} 
		}
	}

	public int trouverJoker(ArrayList<Carte> j) {
		if (hasJoker(j)) {
			for (Carte c : j) {
				if (c instanceof Joker) {
					return j.indexOf(c);
				}
			}
		}
		return -1;
	}
	public boolean hasJoker(ArrayList<Carte> j) {
		for (Carte c : j) {
			if (c instanceof Joker) {
				return true;
			}
		}
		return false;
	}

	public void compterScoreCoeur(ArrayList<Carte> j ) {
		if (this.hasJoker(j)) {
			int pos = this.trouverJoker(j);
			if (this.CompterNbCoeur(j) == 0) {
				j.get(pos).setHauteur(4);
			}
			else if(this.CompterNbCoeur(j) <= 3) {
				for (Carte c : j) {
					if (c.enseigne == suits.COEUR){
						c.hauteur = -c.hauteur;
						j.get(pos).setHauteur(0);
					}
				}
			}
			else {
				j.get(pos).setHauteur(0);
			}
		}
		else {
			System.out.println("Pas de joker");
			for ( Carte i : j) {
				if( i.enseigne == suits.COEUR ) {
					i.setHauteur(0);
				}
			}
		}
	}
	
	public void compterAce(ArrayList<Carte> j) {
		int nbCarreau = 0;
		int nbCarreauAce =0;
		int nbTrefle = 0;
		int nbTrefleAce = 0;
		int nbPique = 0;
		int nbPiqueAce = 0;
		int nbCoeur = 0;
		int nbCoeurAce = 0;
		for (Carte c : j) {
			if (c.enseigne == suits.CARREAU  ){
				if (c.hauteur==1) {
					nbCarreauAce++; 
				} else {
					nbCarreau++;
				}
			}
			else if (c.enseigne == suits.COEUR  ){
				if (c.hauteur==1) {
					nbCoeurAce++; 
				} else {
					nbCoeur++;
				}
			}
			else if (c.enseigne == suits.PIQUE  ){
				if (c.hauteur==1) {
					nbPiqueAce++; 
				} else {
					nbPique++;
				}
			}
			else if (c.enseigne == suits.TREFLE  ){
				if (c.hauteur==1) {
					nbTrefleAce++; 
				} else {
					nbTrefle++;
				}
			}
		}
		if (nbCarreauAce == 1 && nbCarreau ==0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.CARREAU) {
					c.setHauteur(5);
				}
			}
		}
		if (nbCoeurAce == 1 && nbCoeur ==0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.COEUR) {
					c.setHauteur(5);
				}
			}
		}
		if (nbPiqueAce == 1 && nbPique ==0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.PIQUE) {
					c.setHauteur(5);
				}
			}
		}
		if (nbTrefleAce == 1 && nbTrefle ==0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.TREFLE) {
					c.setHauteur(5);
				}
			}
		}
	}
	public int compter(Jest j) {
		ArrayList<Carte> temp = new ArrayList<Carte>();
		temp.addAll(j.listCarte);
		this.compterAce(temp);
		this.compterTrefleCarreauPique(temp);
		this.compterScoreCoeur(temp);
		for ( Carte i : temp) {
			this.score = this.score + i.hauteur;
		}
		
		System.out.println("score est " + this.score);
		return this.score;
	}
}
