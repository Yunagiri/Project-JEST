package Modele;

/**
 * This class represents the player and is the superclass of the Virtual and
 * Physical player. Methods are defined here and used in the subclasses.
 * 
 * @author dinh_, tran_
 * @see JoueurPhys, JoueurVirt, Observable
 */
public class Joueur extends Observable {
	/**
	 * The designation of a player
	 */
	public String prenom;
	/**
	 * To show if the player is taking their turn or not
	 */
	public boolean estEnTour;
	/**
	 * The score of this player, based on how many rounds they won
	 */
	protected int Score;
	/**
	 * Used for GUI, to determine the number of players in a game. Each player with
	 * have their own numero that increases with the player count.
	 */
	public static int numero = 0;
	/**
	 * Used for GUI instead of the prenom field
	 */
	protected int id;
	/**
	 * The hand of the player
	 */
	protected Main main;
	/**
	 * The jest of the player
	 */
	protected Jest jest;

	public boolean getEstEnTour() {
		return this.estEnTour;
	}

	public Main getMain() {
		return this.main;
	}

	public Jest getJest() {
		return this.jest;
	}

	public void setScore(int i) {
		this.Score = i;
	}

	public int getScore() {
		return this.Score;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * Take a card from another Tas, then remove it from the said Tas and decrease
	 * it's card numbers accordingly, notify all observers.
	 * 
	 * @param carte    the card taken
	 * @param autreTas the hand of another player
	 */
	public void prendreCartes(Carte carte, Tas autreTas) {
		this.main.ajouterCartes(carte);
		autreTas.getCarteTas().remove(autreTas.nombreDeCartes - 1);
		autreTas.nombreDeCartes--;
		this.setChanged();
		this.notifyObservers(null);
	}

	/**
	 * This method allows a player to make an offer, choosing a card to show face
	 * down and the other, face up.
	 * 
	 * @param posCarteFaceCachee the position of the card to put face up.
	 */
	public void faireOffre(int posCarteFaceCachee) {
		String msg = String.format("%s fait une offre", this.prenom);
		System.out.println(msg);
		// Change the faceCachee value of the card, this one is face-up
		this.main.getCarteTas().get(posCarteFaceCachee).faceCachee = false;
		// Shows the newly made offer, can also offer insight on the number of cards.
		for (int i = 0; i < this.main.getCarteTas().size(); i++) {
			System.out.println(i + 1 + ". " + this.main.getCarteTas().get(i).montrer());
		}
		this.setChanged();
		this.notifyObservers(null);
	}

	/**
	 * This method allows the player to show their offer as a string in the console.
	 */
	public void montrerOffre() {
		for (int i = 0; i < this.main.getCarteTas().size(); i++) {
			System.out.println(i + 1 + ". " + this.main.getCarteTas().get(i).montrer());
		}
	}

	/**
	 * This method allows a player to look at the cards in their hand even if it's
	 * face down.
	 */
	public void regarderMain() {
		// format() allows you to format a string
		String msg = String.format("Le joueur %s regarde sa main", this.prenom);
		System.out.println(msg);
		for (int i = 0; i < this.main.nombreDeCartes; i++) {
			this.main.getCarteTas().get(i).faceCachee = false;
			System.out.println(this.main.getCarteTas().get(i).montrer());
			this.main.getCarteTas().get(i).faceCachee = true;
		}
	}

	/**
	 * This method allows this player to take a card from another player's offer,
	 * and put it in their jest. Notify all observers.
	 * 
	 * @param posCarte the position of the card to be taken
	 * @param j        the player from whom the card is taken
	 */

	public void prendreOffre(int posCarte, Joueur j) {
		String msg = String.format("%s prend la carte %d de %s et le met dans son jest.", this.prenom, posCarte,
				j.prenom);
		System.out.println(msg);
		this.jest.ajouterCartes(j.main.getCarteTas().get(posCarte));
		j.main.getCarteTas().remove(posCarte);
		j.main.nombreDeCartes--;
		j.setChanged();
		j.notifyObservers("CartePrise");
	}

	/**
	 * This method allow the player to take a card from the trophy zone. Notify all
	 * observers
	 * 
	 * @param i the position of the trophy card to be taken
	 * @param t the trophy composed of 2 cards
	 */
	public void prendreOffre(int pos, Trophee t) {
		String msg = String.format("%s prend une carte de trophee et le met dans son jest.", this.prenom);
		System.out.println(msg);
		this.jest.ajouterCartes(t.getCarteTas().get(pos));
		this.setChanged();
		this.notifyObservers(pos);
	}
}
