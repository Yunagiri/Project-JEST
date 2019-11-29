package fr.utt.lo02.jest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Partie {
	private int rounds;
	private boolean partieEnCours;
	private int numeroRound;

	private Pioche piocheGrand;
	private Trophee trophee;
	private Tas piochePetite;

	public List<Joueur> joueurs;

	// Can't use melanger() on piocheGrand, there's nothing in its listCarte yet,
	// hence it also cannot distribuer(trophee), there's nothing to distribute.
	public Partie() {
		numeroRound = 1;
		joueurs = new ArrayList<Joueur>();
		piocheGrand = new Pioche();
		piochePetite = new Tas();
		trophee = new Trophee();
	}

	// create cartes and add to pioche
	public void creerPioche() {
		for (int i = 1; i < 5; i++) {
			for (suits s : suits.values()) {
				action temp = action.getRandomAction();
				if (temp == action.HIGHEST || temp == action.LOWEST || temp == action.MAJORITY) {
					// System.out.println(action.getRandomAction());
					Conditions cond = new Conditions(temp, suits.getRandomSuits());
					// System.out.println("Creation d'une condition a 2 parametres");
					SuitCards carte = new SuitCards(i, true, cond, s);
					this.piocheGrand.listCarte.add(carte);
					this.piocheGrand.nombreDeCartes++;
				} else {
					// System.out.println(temp);
					Conditions cond = new Conditions(temp);
					// System.out.println("Creation d'une condition a 1 parametre");
					SuitCards carte = new SuitCards(i, true, cond, s);
					this.piocheGrand.listCarte.add(carte);
					this.piocheGrand.nombreDeCartes++;
				}
			}
		}
		Joker joker = new Joker();
		this.piocheGrand.listCarte.add(joker);
		this.piocheGrand.nombreDeCartes++;
	}

	// shuffles piocheGrand AND distribute 2 cards to the trophy.
	public void preparer() {
		this.piocheGrand.melanger();
		for (int i = 0; i < 2; i++) {
			this.piocheGrand.distribuer(this.trophee);
		}
	}

	// add joueur
	public void ajouterJoueur(Joueur joueur) {
		if (this.partieEnCours == false) {
			this.joueurs.add(joueur);
		}
	}

	// delete joueur
	public void retirerJoueur(Joueur joueur) {
		this.joueurs.remove(joueur);
	}

	// public Visitor visitor;

	// ??? What's finished? the round? the game? myself? I fucking wish
	// public boolean estTerminee() {
//	}

	// Each player takes 2 cards from the piocheGrand in the 1st round and 2 cards
	// from the piochePetit from the 2nd round onwards
	public void distribuerCartes() {
		this.partieEnCours = true;
		Iterator<Joueur> it = joueurs.iterator();

		// Fucking hell it was a while when it should have been an if, lost like an hour looking for a problem in the other classes XD
		if (piocheGrand.estVide() == false) {
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				if (this.numeroRound == 1) {
					String msg = String.format("Distribution en cours au joueur %s", j.prenom);
					System.out.println(msg);
					for (int i = 0; i < 2; i++) {
						j.prendreCartes(piocheGrand.listCarte.get(piocheGrand.nombreDeCartes - 1), piocheGrand);

					}
				} else {
					for (int i = 0; i < 2; i++) {
						j.prendreCartes(piochePetite.listCarte.get(piochePetite.nombreDeCartes - 1), piocheGrand);

					}
				}
			}
		}
	}

	public void donnerTour(Joueur joueur) {
		if (joueur.estEnTour == false) {
			joueur.estEnTour = true;
			String msg = String.format("Au tour de %s de jouer.", joueur.prenom);
			System.out.println(msg);
		}
	}
	
	public void finirTour(Joueur joueur) {
		joueur.estEnTour = false;
		String msg = String.format("Fin du tour de %s.", joueur.prenom);
		System.out.println(msg);
	}

	public void choisirJoueur() {
		//while (this.partieEnCours) {

			// joueurMax stocks the player with the highest face-up card in this iteration
			Joueur joueurMax = new Joueur();
			Iterator<Joueur> it = joueurs.iterator();
			// the 1st element of the iterator is affected to joueurMax to initialize it
			joueurMax = it.next();
			// while the iterator hasn't finished running through the joueur ArrayList
			while (it.hasNext()) {
				// Creation of a new instance, i different from JoueurMax that stores the
				// current Joueur with whom to compare face_up cards value
				Joueur joueurActuel = (Joueur) it.next();

				// This loop compares the joueurActuel face-up card value with the face-up card
				// value of the joueurMax
				for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
					// This condition allows the loop to only compare face-up cards.
					if (!joueurActuel.main.listCarte.get(counter).faceCachee)

						// If face-up value of joueurActuel is higher than joueurMax' value, affect
						// joueurActuel to joueurMax
						if (joueurActuel.main.listCarte.get(counter).hauteur > joueurMax.main.listCarte
								.get(counter).hauteur) {
							joueurMax = joueurActuel;
						}
						// else if they're the same value, break ties following the values of the suits.
						// Exclude the case where joueurActuel IS joueurMax.
						else if (joueurActuel.main.listCarte.get(counter).hauteur == joueurMax.main.listCarte
								.get(counter).hauteur
								&& joueurActuel.main.listCarte.get(counter).enseigne
										.getValeur() != joueurMax.main.listCarte.get(counter).enseigne.getValeur()) {
							// Break ties accordingly
							if (joueurActuel.main.listCarte.get(counter).enseigne
									.getValeur() > joueurMax.main.listCarte.get(counter).enseigne.getValeur()) {
								joueurMax = joueurActuel;
							}
						}
				}
			}
			// Once the iterator is done running through joueur, give the turn to joueurMax
			this.donnerTour(joueurMax);
		}
	//}

	public void verifierCondition() {

	}

	public void afficherScore() {

	}

	public void terminer() {
		this.partieEnCours = false;
	}

	public static void main(String[] args) {

		// Declaration
		Scanner sc = new Scanner(System.in);
		Partie partie = new Partie();
		Joueur thanhtri = new Joueur("Thanh", "Tri");
		Joueur vietphuong = new Joueur("Viet", "Phuong");
		
		
		partie.ajouterJoueur(thanhtri);
		partie.ajouterJoueur(vietphuong);
		partie.creerPioche();
		partie.preparer();
		System.out.println(partie.piocheGrand.nombreDeCartes);

		// Show the deck shuffled
		//for (int i = 0; i < partie.piocheGrand.listCarte.size(); i++) {
			//partie.piocheGrand.listCarte.get(i).faceCachee = false;
			//System.out.println(partie.piocheGrand.listCarte.get(i).montrer());
			//partie.piocheGrand.listCarte.get(i).faceCachee = true;
		//}
		partie.distribuerCartes();

		// Players look at their hand
		for (int i = 0; i < partie.joueurs.size(); i++) {
			partie.joueurs.get(i).regarderMain();

		}

		// Players making offer, will have to replace it with a scanner class so we can
		// take inputs in the console.
		
		for (int i = 0; i < partie.joueurs.size(); i++) {
			String msg = String.format("%s, veuillez choisir une carte à mettre face recto, l'autre sera verso.", partie.joueurs.get(i).prenom);
			System.out.println(msg);
			int posCarteFaceCachee = sc.nextInt();
			partie.joueurs.get(i).faireOffre(posCarteFaceCachee -1);
		}
		partie.choisirJoueur();
		
		
		//Actions to take in a single turn of a player: Choose a player, take a card in their hand and put it in jest.
		for (int i = 0; i < partie.joueurs.size(); i++) {
			if (partie.joueurs.get(i).estEnTour) {
				sc.nextLine();
				System.out.println("Veuillez choisir un joueur");
				String prenom = sc.nextLine();
				String msg = String.format("%s a choisi %s",partie.joueurs.get(i).prenom, prenom);
				System.out.println(msg);
//				for (int o = 0; o < partie.joueurs.size(); o++) {
//					if (prenom == partie.joueurs.get(i).prenom && partie.joueurs.get(o).main.nombreDeCartes == 2) {
//						System.out.println("Vous ne pouvez pas vous choisir");
//					}
				System.out.println("Veuillez choisir le numéro de sa carte");	
				int z = sc.nextInt();
				sc.nextLine();
				Joueur prochainJoueur = new Joueur();
				System.out.println("Recherche du joueur en cours");
				for (int r = 0; r < partie.joueurs.size(); r++) {
					if (partie.joueurs.get(r).getPrenom().equals(prenom)) {
						partie.joueurs.get(i).prendreOffre(z, partie.joueurs.get(r));
						prochainJoueur = partie.joueurs.get(r);
					}
				}
				partie.finirTour(partie.joueurs.get(i));
				partie.donnerTour(prochainJoueur);
				
				
				
			}	
		}
		
		
		
		
	}
}
