package Modele;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class inherits from Joueur and is the main user of the Strategy design pattern. 
 * This class has redefined methods from its superclass. 
 * @author dinh_,tran_
 *
 */
public class JoueurVirt extends Joueur {
	/**
	 * The attribute that determines the difficulty level for the virtual player.
	 */
    private int niveau;
    /**
     * The strategy it will use
     */
    Strategie strat;
    /**
     * This constructor initialise the attributes inherited from its superclass. Creates a jest and a hand at the same time.
     * It also defines the strategy to use.
     * @param niveau define the strategy used, easy or hard.
     * @param prenom the name of the player
     */
    public JoueurVirt(int niveau, String prenom) {
		this.prenom = prenom;
		this.main = new Main();
		this.jest = new Jest();
		this.estEnTour = false;
		this.Score = 0;
    	this.niveau = niveau;
    	if (this.niveau == 1) {
    		this.strat = new Facile();
    	}
    	else {
    		this.strat = new Difficile();
    	}
    	Joueur.numero = numero +1;
		this.id = numero;
		
    }
    
    /**
     * Redefined method from superclass to use with the strategy design pattern
     * @see Joueur#faireOffre(int)
     */
    public void faireOffre() {
    	int temp = strat.faireOffre(this.main.listCarte);
    	super.faireOffre(temp);
    	
    }
    
    /**
     * Choosing a player, following the strategy chosen
     * @return a player chosen by the strategy used 
     */
    public Joueur choisirJoueur(ArrayList<Joueur> listJoueurs) {
    	return strat.choisirJoueur(listJoueurs);
    }
    
    /**
     * Redefined method from superclass to use with the Strategy design pattern.
     * @param listJoueurs a list of players in game currently
     * @see Joueur#prendreOffre(int, Joueur)
     */
    public void prendreOffre(ArrayList<Joueur> listJoueurs) {
    	Random rd = new Random();
    	Joueur j = this.choisirJoueur(listJoueurs);
		int temp = rd.nextInt(listJoueurs.size());
    	super.prendreOffre(temp, j);
    }
}
