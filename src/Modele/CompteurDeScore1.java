package Modele;

import java.util.ArrayList;
/**
 * This class is a Visitor designed to calculate player's jest scores. It contains methods that uses the value of each 
 * card in the player's jest in order to get the final jest score. It takes in account the game's rules such as double value for 
 * a pair of same face-value Spade and Club.
 * 
 * @see Visitor, CompteurDeScore2
 * 
 * @author dinh_
 *
 */
public class CompteurDeScore1 implements Visitor {
	/**
	 * The score for each player's jest.
	 */
	private int score;

	/**
	 * The constructor of this class, initialise the score to 0 for each time it is called to calculate a jest's score.
	 */
	public CompteurDeScore1() {
		this.score = 0;
	}
	/**
	 * This method counts the number of hearts in a player's jest.
	 * @param takes a player's jest
	 * @return the number of hearts in the jest.
	 */
	public int CompterNbCoeur(ArrayList<Carte> Jest) {
		int nbCoeurs = 0;
		for (Carte c : Jest) {
			if (c.enseigne == suits.COEUR) {
				nbCoeurs++;
			}
		}
		return nbCoeurs;
	}
	/**
	 * This method contains all the elements to calculate the score of Spade, Club and Diamond suit cards. 
	 * If the card suit is a Spade or a Club, the score of the jest increases of the face value of the card.
	 * If the card suit is Diamond, it decreases the score of the jest of the face value of the card.
	 * @param j the jest which needs scoring.
	 */
	public void compterTrefleCarreauPique(ArrayList<Carte> j) {
		ArrayList<Carte> temps = new ArrayList<Carte>();
		temps.addAll(j);
		for (Carte c : j) {
			if (c instanceof SuitCards) {
				if (c.enseigne == suits.PIQUE || c.enseigne == suits.TREFLE) {
					this.score = this.score + c.hauteur;
					temps.remove(c);
					for (Carte l : temps) {
						if (c.hauteur == l.hauteur) {
							if ((l.enseigne == suits.PIQUE || l.enseigne == suits.TREFLE) && l.enseigne != c.enseigne) {
				
								this.score = this.score + 2;

							}
						}
					}

				} else if (c.enseigne == suits.CARREAU) {
					this.score = this.score - c.hauteur;
				}
			}
		}
	}
	/**
	 * This method indicates if a jest has a joker card in it or not. 
	 * @param j the Jest that needs scoring
	 * @return true if the jest contains a Joker, false if not.
	 */
	public boolean hasJoker(ArrayList<Carte> j) {
		for (Carte c : j) {
			if (c instanceof Joker) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method calculates the score Heart cards gives. The Heart score is dictated by the rules of the game:
	 * <ul>
	 * <li>If the jest has a joker and no Hearts, increases the score by 4.
	 * <li>If the jest has a joker and 3 Hearts or less, decreases the score by the total face value of all the Hearts.
	 * <li>If the jest has a joker and all Hearts, increases the score by the total face value of all Hearts.
	 * <li>If the jest doesn't have a joker, Hearts doesn't add anything nor decrease anything.
	 * @param j the Jest which needs scoring
	 */
	public void compterScoreCoeur(ArrayList<Carte> j) {
		if (this.hasJoker(j)) {
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

	/**
	 * This method determines  whether an Ace is worth 1 or 5 points, depending on the number of cards of the same suit in the jest. 
	 * @param j The Jest which needs scoring
	 */
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
	
	/**
	 * The method that implements the Visitor interface. Call all the methods defined above and return the score of the Jest visited.
	 * @param j the Jest which needs scoring.
	 */
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
