package Modele;

/**
 * Inherits from the Joueur, doesn't implement any methods but instead call
 * methods from the superclass. This class' main purpose is to define a proper
 * constructor as the superclass lacks of any constructor.
 * 
 * @author dinh_, tran_
 * @see Joueur
 *
 */
public class JoueurPhys extends Joueur {
	/**
	 * This is the constructor, which initialise attributes inherited from the
	 * superclass
	 * 
	 * @param prenom the name of the player
	 */
	public JoueurPhys(String prenom) {
		this.prenom = prenom;
		this.main = new Main();
		this.jest = new Jest();
		this.estEnTour = false;
		this.Score = 0;
		Joueur.numero = numero + 1;
		this.id = numero;

	}
}
