package fr.utt.lo02.jest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Partie {
	private int rounds;
	private boolean partienEnCours;
	private int numeroRound;

	private Pioche piocheGrand;
	private Trophee trophee;
	private Tas piochePetit;

	public List<Joueur> joueurs;

	public Partie() {
		numeroRound = 1;
		joueurs = new ArrayList<Joueur>();
		piocheGrand = new Pioche();
		piochePetit = new Tas();
		trophee = new Trophee();
		piocheGrand.melanger();

//		for (int i = 0; i < 2; i++) {
//			piocheGrand.distribuer(trophee);
//		}
	}

	// create cartes and add to pioche
	public void creerPioche() {
		for (int i = 1; i < 5; i++) {
			for (suits s : suits.values()) {
				if (condition.getRandomCondition() == condition.HIGHEST
						&& condition.getRandomCondition() == condition.LOWEST
						&& condition.getRandomCondition() == condition.MAJORITY) {
					Conditions cond = new Conditions(condition.getRandomCondition(), suits.getRandomSuits());
					SuitCards carte = new SuitCards(i, true, cond , s);
					this.piocheGrand.listCarte.add(carte);
				} else {
					Conditions cond = new Conditions(condition.getRandomCondition());
					SuitCards carte = new SuitCards(i, true, cond, s);
					this.piocheGrand.listCarte.add(carte);
				}

			}
		}
		Joker joker = new Joker();
		this.piocheGrand.listCarte.add(joker);
	}

	// add joueur
	public void ajouterJoueur(Joueur joueur) {
		if (this.partienEnCours == false) {
			joueurs.add(joueur);
		}
	}

	// delete joueur
	public void retirerJoueur(Joueur joueur) {
		joueurs.remove(joueur);
	}

	// public Visitor visitor;

	// ??? What's finished? the round? the game? myself? I fucking wish
	// public boolean estTerminee() {
	// Assuming that it's the player round finishing

	// distribute 2 cards in each row
//	}

	public void distribuerCartes() {
		this.partienEnCours = true;
		Iterator<Joueur> it = joueurs.iterator();
		while (piocheGrand.estVide() == false) {
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				if (this.numeroRound == 1) {
					for (int i = 0; i < 2; i++) {
						j.prendreCartes(piocheGrand.listCarte.get(piocheGrand.nombreDeCartes - 1));
					}
				} else {
					for (int i = 0; i < 2; i++) {
						j.prendreCartes(piochePetit.listCarte.get(piochePetit.nombreDeCartes - 1));
					}
				}
			}
		}
	}

	public void donnerTour(Joueur joueur) {
		if (joueur.estEnTour == false) {
			joueur.estEnTour = true;
			System.out.println("Now, it's " + joueur + "'s turn ");
		}
	}

	public void choisirJoueur() {
		while (this.partienEnCours) {
			Joueur joueurMax = new Joueur();
			Iterator<Joueur> it = joueurs.iterator();
			joueurMax = it.next();
			while (it.hasNext()) {
				Joueur i = (Joueur) it.next();
				for (int counter = 0; counter < i.main.listCarte.size(); counter++) {
					if (!i.main.listCarte.get(counter).faceCachee)
						if (i.main.listCarte.get(counter).hauteur > joueurMax.main.listCarte.get(counter).hauteur) {
							joueurMax = i;
						} else if (i.main.listCarte.get(counter).hauteur == joueurMax.main.listCarte
								.get(counter).hauteur
								&& i.main.listCarte.get(counter).enseigne
										.getValeur() != joueurMax.main.listCarte.get(counter).enseigne.getValeur()) {
							if (i.main.listCarte.get(counter).enseigne
									.getValeur() > joueurMax.main.listCarte.get(counter).enseigne.getValeur()) {
								joueurMax = i;
							}
						}
				}
			}
			this.donnerTour(joueurMax);
		}
	}

	public void verifierCondition() {
	}

	public void afficherScore() {

	}

	public void terminer() {
		this.partienEnCours = false;
	}

	public static void main(String[] args) {
		Partie partie = new Partie();
		Joueur thanhtri = new Joueur("Thanh", "Tri");
		Joueur vietphuong = new Joueur("Viet", "Phuong");
		partie.ajouterJoueur(thanhtri);
		partie.ajouterJoueur(vietphuong);
		partie.creerPioche();

		System.out.println(partie.piocheGrand.listCarte.size());
		partie.piocheGrand.listCarte.get(5).faceCachee = false;
		System.out.println(partie.piocheGrand.listCarte.get(5).montrer());

	}

}
