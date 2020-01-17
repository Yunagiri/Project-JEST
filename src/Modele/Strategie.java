package Modele;

import java.util.ArrayList;

/**
 * This interface implements the Strategy design pattern, used for virtual
 * players to adopt different behaviours.
 * 
 * @see JoueurVirt
 * @author dinh_, tran_
 *
 */
public interface Strategie {
	/**
	 * This method allows a strategy to implement it's way of making an offer
	 * 
	 * @param listCarte the cards available for the offer, often in the hands of the
	 *                  player
	 * @return the position of the chosen card
	 */
	public int faireOffre(ArrayList<Carte> listCarte);

	/**
	 * This method allows a strategy to chose a player based on a list of players
	 * 
	 * @param listJoueur list of players
	 * @return a player inside the list given
	 */
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueur);

}
