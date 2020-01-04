package Modele;

import java.util.ArrayList;

public class CompteurDeScore1 implements VisitorDeJest {
	private int score;

	public CompteurDeScore1() {
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
					// j.setValeur(j.getValeur() + c.hauteur);
					this.score = this.score + c.hauteur;
					temps.remove(c);
					for (Carte l : temps) {
						if (c.hauteur == l.hauteur) {
							if ((l.enseigne == suits.PIQUE || l.enseigne == suits.TREFLE) && l.enseigne != c.enseigne) {
								// j.setValeur(j.getValeur() + 2);
								// c.hauteur += 2;
								// j.get(j.indexOf(l)).hauteur +=2;
								this.score = this.score + 2;

							}
						}
					}

				} else if (c.enseigne == suits.CARREAU) {
					// j.setValeur(j.getValeur() - c.hauteur);
					this.score = this.score - c.hauteur;
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

	public void compterScoreCoeur(ArrayList<Carte> j) {
		if (this.hasJoker(j)) {
			// int pos = this.trouverJoker(j);
			if (this.CompterNbCoeur(j) == 0) {
				this.score = this.score + 4;
			} else if (this.CompterNbCoeur(j) <= 3) {
				for (Carte c : j) {
					if (c.enseigne == suits.COEUR) {
						this.score = this.score - c.hauteur;
					}
				}
			} else {
				for (Carte c : j) {
					if (c.enseigne == suits.COEUR) {
						this.score = this.score + c.hauteur;
					}
				}
			}
		}
	}

	public void compterAce(ArrayList<Carte> j) {
		int nbCarreau = 0;
		int nbCarreauAce = 0;
		int nbTrefle = 0;
		int nbTrefleAce = 0;
		int nbPique = 0;
		int nbPiqueAce = 0;
		int nbCoeur = 0;
		int nbCoeurAce = 0;
		for (Carte c : j) {
			if (c.enseigne == suits.CARREAU) {
				if (c.hauteur == 1) {
					nbCarreauAce++;
				} else {
					nbCarreau++;
				}
			} else if (c.enseigne == suits.COEUR) {
				if (c.hauteur == 1) {
					nbCoeurAce++;
				} else {
					nbCoeur++;
				}
			} else if (c.enseigne == suits.PIQUE) {
				if (c.hauteur == 1) {
					nbPiqueAce++;
				} else {
					nbPique++;
				}
			} else if (c.enseigne == suits.TREFLE) {
				if (c.hauteur == 1) {
					nbTrefleAce++;
				} else {
					nbTrefle++;
				}
			}
		}
		if (nbCarreauAce == 1 && nbCarreau == 0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.CARREAU) {
					// c.setHauteur(5);
					this.score = this.score - 4;
				}
			}
		}
		if (nbCoeurAce == 1 && nbCoeur == 0) {
			if (this.hasJoker(j)) {
				if (this.CompterNbCoeur(j) <= 3) {
					this.score = this.score - 4;
				} else if (this.CompterNbCoeur(j) == 4) {
					this.score = this.score + 4;
				}
			}
		}
		if (nbPiqueAce == 1 && nbPique == 0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.PIQUE) {
					this.score = this.score + 4;
				}
			}
		}
		if (nbTrefleAce == 1 && nbTrefle == 0) {
			for (Carte c : j) {
				if (c.hauteur == 1 && c.enseigne == suits.TREFLE) {
					this.score = this.score + 4;
				}
			}
		}
	}

	public int visiter(Jest j) {
		this.score = 0;
		ArrayList<Carte> temp = new ArrayList<Carte>();
		temp.addAll(j.listCarte);
		this.compterAce(temp);
		this.compterTrefleCarreauPique(temp);
		this.compterScoreCoeur(temp);
		return this.score;
	}
}
