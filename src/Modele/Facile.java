package Modele;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the Visitor design pattern. This is the easy variant that does everything randomly.
 * @author dinh_ ,tran_
 *
 */
public class Facile implements Strategie {
	/**
	 * This method chooses a random card in the hand to make an offer.
	 * @param listCarte the list of cards the virtual player has in hand
	 * @return the position of a random card in the virtual player's hand
	 * 
	 */
	public int faireOffre(ArrayList<Carte> listCarte) {
		int nombreAleatoire = 0 +(int)(Math.random() * ((1 - 0) + 1));
		return nombreAleatoire;
	}

	/**
	 * This method chooses a random player to take an offer from
	 * @param listJoueurs this is the list of players currently in game
	 * @return the player chosen randomly
	 */
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
		Random random = new Random();
		int temp = random.nextInt(listJoueurs.size());
		return listJoueurs.get(temp);
	}
}
