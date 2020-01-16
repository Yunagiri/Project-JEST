package Modele;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class implements the design pattern Strategie. This is the hard variant, it will look for the card with the lowest value in hand,
 * and make an offer putting that card face up in order to conceal their higher value card, so that the opponent's jest won't get a higher socre.
 * @author dinh_
 *
 */
public class Difficile implements Strategie {
	
	/**
	 * This method return the position of the card to put face up.
	 * @param listCarte the list of cards the virtual player is holding up, 2.
	 * @return the position of the card of the lowest value in the hand 
	 */
	@Override
	public int faireOffre(ArrayList<Carte> listCarte) {
		// TODO Auto-generated method stub
		Carte cmin = listCarte.get(0);
		for (Carte c: listCarte) {
			if (c.valeur < cmin.valeur) {
				cmin = c;
			}
		}
		return listCarte.indexOf(cmin);
		
	}
	
	/**
	 * This method returns a random player from the list of players in the game.
	 * @param listJoueurs the list of players currently playing the game
	 * @return a player chosen at random to take offer from.
	 */
	@Override
	public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int temp = random.nextInt(listJoueurs.size());
		return listJoueurs.get(temp);
	}
	
	
}
