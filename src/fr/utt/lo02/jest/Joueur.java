package fr.utt.lo02.jest;

public class Joueur {

	protected String prenom;
	protected boolean estEnTour;

	protected Main main;
	protected Jest jest;

	public Joueur(String prenom) {
		this.prenom = prenom;
		this.main = new Main();
		this.jest = new Jest();
		this.estEnTour = false;
	}

	public Joueur() {

	}

	public String getPrenom() {
		return this.prenom;
	}
	// Take a card from another Tas, then remove it from the said Tas and decrease
	// it's nombreDeCartes accordingly.
	public void prendreCartes(Carte carte, Tas autreTas) {
		this.main.ajouterCartes(carte);
		autreTas.listCarte.remove(autreTas.nombreDeCartes - 1);
		autreTas.nombreDeCartes--;
	}

	// This method allows a player to make an offer, choosing a card to show face
	// down and the other face up
	public void faireOffre(int posCarteFaceCachee) {
		String msg = String.format("%s fait une offre", this.prenom);
		System.out.println(msg);
		// Change the faceCachee value of the card, this one is face-up
		this.main.listCarte.get(posCarteFaceCachee).faceCachee = false;
		// Shows the newly made offer, can also offer insight on the number of cards.
		for (int i = 0; i < this.main.listCarte.size(); i++) {
			System.out.println(i + 1 + ". " + this.main.listCarte.get(i).montrer());
		}
	}
	
	public void montrerOffre() {
		for (int i = 0; i < this.main.listCarte.size(); i++) {
			System.out.println(i + 1 + ". " + this.main.listCarte.get(i).montrer());
		}
	}

	// This method allow a player to look at the cards in their hand even if it's
	// face down.
	public void regarderMain() {
		// format() allows you to format a string
		String msg = String.format("Le joueur %s regarde sa main", this.prenom);
		System.out.println(msg);
		for (int i = 0; i < this.main.nombreDeCartes; i++) {
			this.main.listCarte.get(i).faceCachee = false;
			System.out.println(this.main.listCarte.get(i).montrer());
			this.main.listCarte.get(i).faceCachee = true;
		}
	}

	// This method allows this player to take a card from another player's offer,
	// and put it in their jest.
	public void prendreOffre(int posCarte, Joueur j) {
		String msg = String.format("%s prend la carte %d de %s et le met dans son jest.", this.prenom, posCarte, j.prenom);
		System.out.println(msg);
		this.jest.ajouterCartes(j.main.listCarte.get(posCarte-1));
		j.main.listCarte.remove(posCarte-1);
		j.main.nombreDeCartes--;
	}
	public void prendreOffre(int i, Trophee t) {
		String msg = String.format("%s prend la carte %d de trophee et le met dans son jest.", this.prenom, i);
		System.out.println(msg);
		this.jest.ajouterCartes(t.listCarte.get(i));
		t.listCarte.remove(i);
		t.nombreDeCartes--;
	}

	public void montrerCarte() {
	}

	// add the card into the jest, obsolete
	/* public void mettreJest(Carte carte) {
	 *	 this.jest.ajouterCartes(carte);
	 *	}
	 */

	// give card to another desk,
	// parameter posCarte indicates the position of the card
	// and the autreTas indicates where to take it from.
	public void donnerCarte(int posCarte, Main autreMain) {
		this.main.distribuer(posCarte, autreMain);
	}

	// finish round
	public void finTour() {
		System.out.println("J'ai fini mon tour");
	}

}
