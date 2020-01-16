package Modele;

import java.util.ArrayList;

public class Trophee extends Tas {
	public Trophee() {
		super();
	}
	
	/*Award the trophy card to the player fulfilling the condition of the trophy card
	* posTrophee indicates which card to give, and jestGagnant indicates who to give to
	 */
	public void distribuerTrophee(ArrayList<Joueur> joueurs, Visitor compteur) {
		boolean cantakecard1 = true;
		boolean cantakecard2 = true;
		
		ArrayList<Carte> temp = new ArrayList<Carte>();
		temp.addAll(this.listCarte);
		Joueur joueur1 = new Joueur();
		Joueur joueur2 = new Joueur();
		for (Carte carteT : temp) {
			if (carteT.condi.cond == action.HIGHEST) {
				Joueur JoueurMax = new Joueur();
				ArrayList<Joueur> joueurHighest = new ArrayList<Joueur>();
				for (Joueur joueur : joueurs) {
					for (int i = 0; i < joueur.jest.listCarte.size(); i++) {
						if (joueur.jest.listCarte.get(i).enseigne == carteT.condi.enseigne ) {
							joueurHighest.add(joueur);
						}
					}
				}
				
					JoueurMax = joueurHighest.get(0);
					int index = 0;
					for (Joueur joueur21 : joueurHighest) {
						for (int i = 0; i < joueur21.jest.listCarte.size(); i++) {
							if ( joueur21.jest.listCarte.get(i).hauteur > JoueurMax.jest.listCarte.get(index).hauteur) {
								index = i;
								JoueurMax = joueur21;
							}
						}
				}
				// JoueurMax.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
				if (temp.indexOf(carteT) == 0) {
					joueur1 = JoueurMax;
				} else if (temp.indexOf(carteT) == 1) {
					joueur2 = JoueurMax;
				}
			} else if (carteT.condi.cond == action.LOWEST) {
				Joueur JoueurMin = new Joueur();
				ArrayList<Joueur> joueurLowest = new ArrayList<Joueur>();
				for (Joueur joueur : joueurs) {
					for (int i = 0; i < joueur.jest.listCarte.size(); i++) {
						if (joueur.jest.listCarte.get(i).enseigne == carteT.condi.enseigne ) {
							joueurLowest.add(joueur);
						}
					}
				}
				if(joueurLowest.size()>0) {
					JoueurMin = joueurLowest.get(0);
				}
				int index = 0;
				for (Joueur joueur : joueurLowest) {
					for (int i = 0; i < joueur.jest.listCarte.size(); i++) {
						if ( joueur.jest.listCarte.get(i).hauteur < JoueurMin.jest.listCarte.get(index).hauteur) {
							index = i;
							JoueurMin = joueur;
						}
					}
				}
				if (temp.indexOf(carteT) == 0) {
					joueur1 = JoueurMin;
				} else if (temp.indexOf(carteT) == 1) {
					joueur2 = JoueurMin;
				}
				// JoueurMin.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
			} else if (carteT.condi.cond == action.MAJORITY) {
				Joueur JoueurMax = new Joueur();
				JoueurMax = joueurs.get(0);
				int highestMajority = 0;
				int highestValeur = 0;
				for (Joueur joueur : joueurs) {
					int majority = 0;
					int valeur = 0;
					for (Carte carte : joueur.jest.listCarte) {
						if (carte.hauteur == carteT.condi.hauteur) {
							majority++;
							if (carte.valeur > valeur) {
								valeur = carte.valeur;
							}
						}
					}
					if (majority > highestMajority) {
						JoueurMax = joueur;
						highestMajority = majority;
						highestValeur = valeur;
					} else if (majority == highestMajority && valeur > highestValeur) {
						JoueurMax = joueur;
						highestMajority = majority;
						highestValeur = valeur;
					}
				}
				//JoueurMax.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
				if (temp.indexOf(carteT) == 0) {
					joueur1 = JoueurMax;
				} else if (temp.indexOf(carteT) == 1) {
					joueur2 = JoueurMax;
				}
			} else if (carteT.condi.cond == action.JOKER) {
				int nbnoJoker =0;
				for (Joueur j : joueurs) {
					if (j.jest.hasJoker()) {
						if (temp.indexOf(carteT) == 0) {
							joueur1 = j;
						} else if (temp.indexOf(carteT) == 1) {
							joueur2 = j;
						}
						// j.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
					} else {
						nbnoJoker ++;
					}
				}
				if ( nbnoJoker == joueurs.size()) {
					if (temp.indexOf(carteT) == 0) {
						cantakecard1 = false;
					} else if (temp.indexOf(carteT) == 1) {
						cantakecard2 = false;
					}
				}
			} else if (carteT.condi.cond == action.BEST) {
				Joueur jMax = new Joueur();
				jMax = joueurs.get(0);
				int highestValeur = 0;
				for (Joueur i : joueurs) {
					int Valeur = 0;
					
					for (Carte c : i.jest.listCarte) {
						if (c.valeur > Valeur) {
							Valeur = c.valeur;
						}
					}
					if (compteur.visit(i.jest) > compteur.visit(jMax.jest)) {
						jMax = i;
						highestValeur = Valeur;
					} else if (compteur.visit(i.jest) == compteur.visit(jMax.jest) && highestValeur <= Valeur) {
						jMax = i;
						highestValeur = Valeur;
					} else if ((compteur.visit(i.jest) == compteur.visit(jMax.jest)) && (highestValeur > Valeur)) {
						jMax = i;
						highestValeur = Valeur;
					} else {
					}
				}
				for (Joueur i : joueurs) {
					System.out.println("Score de " + i.prenom);
					System.out.println(compteur.visit(i.jest));
				}
				if (temp.indexOf(carteT) == 0) {
					joueur1 = jMax;
				} else if (temp.indexOf(carteT) == 1) {
					joueur2 = jMax;
				}
				// jMax.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
			} else if (carteT.condi.cond == action.BEST_NOJOKER) {
				Joueur jMax = new Joueur();
				int highestValeur = 0;
				ArrayList<Joueur> temp3 = new ArrayList<Joueur>();
				for (Joueur i : joueurs) {
					if(!i.jest.hasJoker()) {
						temp3.add(i);
					}
				}
				jMax = temp3.get(0);
				for (Joueur i : temp3) {
					int Valeur = 0;
					for (Carte c : i.jest.listCarte) {
						if (c.valeur > Valeur) {
							Valeur = c.valeur;
						}
					}
					if (compteur.visit(i.jest) > compteur.visit(jMax.jest)) {
						jMax = i;
						highestValeur = Valeur;
					} else if ((compteur.visit(i.jest) == compteur.visit(jMax.jest)) && (highestValeur <= Valeur)) {
						jMax = i;
						highestValeur = Valeur;
					} else if ((compteur.visit(i.jest) == compteur.visit(jMax.jest)) && (highestValeur > Valeur)) {
						jMax = i;
						highestValeur = Valeur;
					} else {
					}
					// jMax.prendreOffre(this.trophee.listCarte.indexOf(carteT), trophee);
				}
				for (Joueur i : joueurs) {
					
					System.out.println("Score de " + i.prenom);
					System.out.println(compteur.visit(i.jest));
				}
				if (temp.indexOf(carteT) == 0) {
					joueur1 = jMax;
				} else if (temp.indexOf(carteT) == 1) {
					joueur2 = jMax;
				}

			}
		}
		if ( cantakecard1 = true) { 
		joueur1.prendreOffre(0, this);
		this.setChanged();
		this.notifyObservers(0);
		}
		if ( cantakecard2 = true) {
		joueur2.prendreOffre(1, this);
		this.setChanged();
		this.notifyObservers(1);
		}
		this.listCarte.clear();
		this.nombreDeCartes = 0;
		
	}
	
}
