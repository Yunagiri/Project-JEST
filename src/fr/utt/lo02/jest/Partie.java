
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
	private Pioche piochePetit;

	public List<Joueur> joueurs;

	public Partie() {
		numeroRound = 1;
		joueurs = new ArrayList<Joueur>();
		piocheGrand = new Pioche();
		piochePetit = new Tas();
		trophee = new Trophee();
		piocheGrand.melanger();

		for (int i = 0; i < 2; i++) {
			piocheGrand.distribuer(trophee);
		}
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
	public boolean estTerminee() {
		// Assuming that it's the player round finishing

		// distribute 2 cards in each row
	}

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
		if (joueur.estentour == false) {
			joueur.estentour = true;
			System.out.println("Now, it's " + joueur + "'s turn " );
	}
	public void choisirJoueur() { 
		while ( this.partienEnCours) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur i =(Joueur) it.next();
				if ( i.)
			}
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

	}

}
