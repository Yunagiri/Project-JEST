
package Modele;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Scanner;

public class Partie {
	// private int rounds;
	private boolean partieEnCours;
	private int numeroRound;

	private Pioche piocheGrand;
	private Trophee trophee;
	private Tas piochePetite;
	private int nbJoueurs;

	public ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	public boolean getPartieEnCours() {
		return this.partieEnCours;
	}
	public int getNumeroRound() {
		return this.numeroRound;
	}
	public Pioche getPiocheGrand() {
		return this.piocheGrand;
	}
	public Trophee getTrophee() {
		return this.trophee;
	}
	public int getnbJoueurs() {
		return this.nbJoueurs;
	}
	public Tas getpiochePetite() {
		return this.piochePetite;
	}

	public Partie() {
		numeroRound = 1;
		piocheGrand = new Pioche();
		piochePetite = new Tas();
		trophee = new Trophee();
		System.out.println("Commence une partie de Jest...");
	}

	// create cartes and add to pioche
	
	public void creerPioche() {
		ArrayList<Carte> deck = this.piocheGrand.listCarte;
		Joker joker = new Joker();
		deck.add(joker);
		
		Conditions lowest_trefle = new Conditions(action.LOWEST, suits.TREFLE);
		Carte PIQUE_4 = new Carte(4,true,lowest_trefle);
		deck.add(PIQUE_4);
		
		Conditions lowest_pique = new Conditions(action.LOWEST, suits.PIQUE);
		Carte TREFLE_4 = new Carte(4,true,lowest_pique);
		deck.add(TREFLE_4);
		
		Conditions Joker = new Conditions(action.JOKER);
		Carte COEUR_4 = new Carte(4,true,Joker);
		deck.add(COEUR_4);
		
		Conditions best_nojoker = new Conditions(action.BEST_NOJOKER);
		Carte CARREAU_4 = new Carte(4,true,best_nojoker);
		deck.add(CARREAU_4);
		
		Conditions lowest_coeur = new Conditions(action.LOWEST, suits.COEUR);
		Carte TREFLE_3 = new Carte(4,true,lowest_coeur);
		deck.add(TREFLE_3);
		
		Conditions maj_2 = new Conditions(action.MAJORITY, 2);
		Carte PIQUE_3 = new Carte(4,true,maj_2);
		deck.add(PIQUE_3);
		
		Conditions lowest_carreau = new Conditions(action.LOWEST, suits.CARREAU);
		Carte CARREAU_3 = new Carte(4,true,lowest_carreau);
		deck.add(CARREAU_3);
		
		Carte COEUR_3 = new Carte(3,true, Joker);
		deck.add(COEUR_3);
		
		Carte TREFLE_2 = new Carte(4,true,lowest_coeur);
		deck.add(TREFLE_2);
		
		Conditions maj_3 = new Conditions(action.MAJORITY, 3);
		Carte PIQUE_2 = new Carte(4,true,maj_3);
		deck.add(PIQUE_2);
		
		Conditions highest_carreau = new Conditions(action.HIGHEST, suits.CARREAU);
		Carte CARREAU_2 = new Carte(4,true,highest_carreau);
		deck.add(CARREAU_2);
		
		Carte COEUR_2 = new Carte(2,true,Joker);
		deck.add(COEUR_2);
		
		Carte COEUR_1 = new Carte(1,true,Joker);
		deck.add(COEUR_1);
		
		Carte PIQUE_1 = new Carte(1,true, lowest_trefle);
		deck.add(PIQUE_1);
		
		Conditions highest_pique = new Conditions(action.HIGHEST, suits.PIQUE );
		Carte TREFLE_1 = new Carte(4,true,highest_pique);
		deck.add(TREFLE_1);
		
		Conditions maj_4 = new Conditions(action.MAJORITY, 4);
		Carte CARREAU_1 = new Carte(4,true,maj_4);
		deck.add(CARREAU_1);
	}
//	public void creerPioche() {
//		for (int i = 1; i < 5; i++) {
//			for (suits s : suits.values()) {
//				action temp = action.getRandomAction();
//				if (temp == action.HIGHEST || temp == action.LOWEST) {
//					// System.out.println(action.getRandomAction());
//					Conditions cond = new Conditions(temp, suits.getRandomSuits());
//					// System.out.println("Creation d'une condition a 2 parametres");
//					SuitCards carte = new SuitCards(i, true, cond, s);
//					this.piocheGrand.listCarte.add(carte);
//					this.piocheGrand.nombreDeCartes++;
//				} else if (temp == action.MAJORITY) {
//					Random r = new Random();
//					Conditions cond = new Conditions(temp, 1 + r.nextInt(4));
//					SuitCards carte = new SuitCards(i, true, cond, s);
//					this.piocheGrand.listCarte.add(carte);
//					this.piocheGrand.nombreDeCartes++;
//				} else {
//					// System.out.println(temp);
//					Conditions cond = new Conditions(temp);
//					// System.out.println("Creation d'une condition a 1 parametre");
//					SuitCards carte = new SuitCards(i, true, cond, s);
//					this.piocheGrand.listCarte.add(carte);
//					this.piocheGrand.nombreDeCartes++;
//				}
//			}
//		}
//		for (int i = 1; i <= this.piocheGrand.listCarte.size(); i++) {
//			this.piocheGrand.listCarte.get(i - 1).valeur = i;
//		}
//		Joker joker = new Joker();
//		this.piocheGrand.listCarte.add(joker);
//		joker.valeur = 0;
//		this.piocheGrand.nombreDeCartes++;
//	}

	// creer pioche petite
	public void creerPiochePetit() {
		if (this.piocheGrand.nombreDeCartes >= this.joueurs.size()) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur o = (Joueur) it.next();
				this.piochePetite.listCarte.add(o.main.listCarte.get(0));
				o.main.listCarte.remove(0);
				o.main.nombreDeCartes--;
				this.piochePetite.nombreDeCartes++;
			}

			for (int i = 0; i < this.joueurs.size(); i++) {
				this.piocheGrand.distribuer(piochePetite);
			}
		} else {
			System.out.println("no more cards,joueur prends sa propre carte");
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur o = (Joueur) it.next();
				o.prendreOffre(1, o);
			}
		}
	}

	// shuffles piocheGrand AND distribute 2 cards to the trophy.
	public void preparer() {
		this.piocheGrand.melanger();
		for (int i = 0; i < 2; i++) {
			this.piocheGrand.distribuer(this.trophee);
		}
		this.montrerTrophee();
	}

	public void montrerTrophee() {
		System.out.println("Les trophees sont: ");
		for (Carte c : this.trophee.listCarte) {
			c.faceCachee = false;
			System.out.println(c.montrer() + "     ");
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

	// Each player takes 2 cards from the piocheGrand in the 1st round and 2 cards
	// from the piochePetit from the 2nd round onwards
	public void distribuerCartes() {
		this.partieEnCours = true;
		Iterator<Joueur> it = joueurs.iterator();

		// Fucking hell it was a while when it should have been an if, lost like an hour
		// looking for a problem in the other classes XD

		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			if (this.numeroRound == 1) {
				String msg = String.format("Distribution en cours au joueur %s", j.prenom);
				System.out.println(msg);
				for (int i = 0; i < 2; i++) {
					j.prendreCartes(this.piocheGrand.listCarte.get(piocheGrand.nombreDeCartes - 1), this.piocheGrand);

				}
			} else {
				String msg = String.format("Distribution en cours au joueur %s", j.prenom);
				System.out.println(msg);
				for (int i = 0; i < 2; i++) {
					j.prendreCartes(this.piochePetite.listCarte.get(piochePetite.nombreDeCartes - 1),
							this.piochePetite);

				}
			}
		}

	}

	public void donnerTour(Joueur joueur) {
		if (joueur.estEnTour == false) {
			joueur.estEnTour = true;
			String msg = String.format("\nAu tour de %s de jouer.", joueur.prenom);
			System.out.println(msg);
		}
	}

	public void finirTour(Joueur joueur) {
		joueur.estEnTour = false;
		String msg = String.format("Fin du tour de %s.", joueur.prenom);
		System.out.println(msg);
	}

	public void choisirJoueur() {
		// while (this.partieEnCours) {

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

			for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
				// This condition allows the loop to only compare face-up cards.
				if (!joueurActuel.main.listCarte.get(counter).faceCachee) {
					if (joueurActuel.main.listCarte.get(counter).valeur > joueurMax.main.listCarte
							.get(counter).valeur) {
						joueurMax = joueurActuel;
					}

				}
			}
		}
		// Once the iterator is done running through joueur, give the turn to joueurMax
		this.donnerTour(joueurMax);
	}
	// }


	public void terminer() {
		this.partieEnCours = false;
	}

	public void lancerRound() {
		this.choisirJoueur();
		int tours = 0;
		ArrayList<Joueur> temp1 = new ArrayList<Joueur>();
		temp1.addAll(joueurs);
		while (tours < this.joueurs.size()) {
			// Actions to take in a single turn of a player: Choose a player, take a card in
			// their hand and put it in jest.
			// for (int i = 0; i < this.joueurs.size(); i++) {
			Iterator<Joueur> itJoueur = joueurs.iterator();
			while (itJoueur.hasNext()) {
				Joueur a = (Joueur) itJoueur.next();
				while (a.estEnTour) {
					temp1.remove(a);
					for (int j = 0; j < this.joueurs.size(); j++) {
						System.out.println("Main de " + this.joueurs.get(j).getPrenom());
						this.joueurs.get(j).montrerOffre();
					}
					boolean differentPrenom = true;
					if (a instanceof JoueurVirt) {
						Joueur d;
						do {
							d = ((JoueurVirt) a).choisirJoueur(joueurs);
							differentPrenom = true;
							if (a.prenom.equals(d.prenom)) {
								differentPrenom = false;
								ArrayList<Joueur> temp = new ArrayList<Joueur>();
								temp.addAll(joueurs);
								temp.remove(a);
								for (Joueur j : temp) {
									if (j.main.nombreDeCartes == 2) {
										System.out.println("Il reste encore des gens ayant 2 cartes!");
										differentPrenom = true;
									}
								}
							} else if (d.main.nombreDeCartes == 1) {
								String msg = String.format("%s n'a seulement qu'une carte", d.prenom);
								System.out.println(msg);
							} else {
								differentPrenom = false;
							}
						} while (differentPrenom);

						Joueur prochainJoueur = new Joueur();
						Iterator<Joueur> it = joueurs.iterator();
						while (it.hasNext()) {
							Joueur o = (Joueur) it.next();
							if (o.getPrenom().equals(d.prenom)) {
								a.prendreOffre(1, o);
								if (temp1.indexOf(o) != -1) {
									prochainJoueur = o;
								} else {
									Iterator<Joueur> it1 = temp1.iterator();
									if (temp1.size() != 0) {
										prochainJoueur = it1.next();
										while (it1.hasNext()) {
											Joueur joueurActuel = (Joueur) it1.next();
											for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
												if (!joueurActuel.main.listCarte.get(counter).faceCachee) {
													if (joueurActuel.main.listCarte
															.get(counter).valeur > prochainJoueur.main.listCarte
																	.get(counter).valeur) {
														prochainJoueur = joueurActuel;
													}
												}
											}
										}
									}
								}
							}
						}
						this.finirTour(a);
						this.donnerTour(prochainJoueur);
						tours++;
					} else {
						Scanner sc = new Scanner(System.in);
						sc.nextLine();
						String prenom;
						do {
							System.out.println("Veuillez choisir un joueur");
							prenom = sc.nextLine();
							Joueur d = new Joueur();
							for (int recherche = 0; recherche < this.joueurs.size(); recherche++) {
								if (this.joueurs.get(recherche).getPrenom().equals(prenom)) {
									d = this.joueurs.get(recherche);
								}
							}
							if (a.prenom.equals(prenom)) {
								// ArrayList<Joueur> temp = new ArrayList<Joueur>();
								// temp.addAll(joueurs);
								// temp.remove(a);
								differentPrenom = false;
								ArrayList<Joueur> temp = new ArrayList<Joueur>();
								temp.addAll(joueurs);
								temp.remove(a);
								for (Joueur j : temp) {
									if (j.main.nombreDeCartes == 2) {
										System.out.println("Il reste encore des gens ayant 2 cartes!");
										differentPrenom = true;
									}
								}
							} else if (d.main.nombreDeCartes == 1) {
								String msg = String.format("%s n'a seulement qu'une carte", d.prenom);
								System.out.println(msg);
							} else {
								differentPrenom = false;
							}
						} while (differentPrenom);

						String msg = String.format("%s a choisi %s", a.prenom, prenom);
						System.out.println(msg);
						System.out.println("Veuillez choisir le numero de sa carte");
						int z = sc.nextInt();
						sc.nextLine();
						Joueur prochainJoueur = new Joueur();
						System.out.println("Recherche du joueur en cours");
						/*
						 * for (int recherche = 0; recherche < this.joueurs.size(); recherche++) { if
						 * (this.joueurs.get(recherche).getPrenom().equals(prenom)) {
						 * this.joueurs.get(i).prendreOffre(z, this.joueurs.get(recherche));
						 * prochainJoueur = this.joueurs.get(recherche); } }
						 */
						Iterator<Joueur> it = joueurs.iterator();
						while (it.hasNext()) {
							Joueur o = (Joueur) it.next();
							if (o.getPrenom().equals(prenom)) {
								a.prendreOffre(z, o);
								if (temp1.indexOf(o) != -1) {
									prochainJoueur = o;
								} else {
									Iterator<Joueur> it1 = temp1.iterator();
									if (temp1.size() != 0) {
										prochainJoueur = it1.next();
										while (it1.hasNext()) {
											Joueur joueurActuel = (Joueur) it1.next();
											for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
												if (!joueurActuel.main.listCarte.get(counter).faceCachee) {
													if (joueurActuel.main.listCarte
															.get(counter).valeur > prochainJoueur.main.listCarte
																	.get(counter).valeur) {
														prochainJoueur = joueurActuel;
													}
												}
											}
										}
									}
								}
							}
						}
						this.finirTour(a);
						this.donnerTour(prochainJoueur);
						tours++;
					}
				}

			}

		}
		System.out.println("Fin du round");
	}

	public void montrerPiocheGrand() {
		for (int i = 0; i < this.piocheGrand.listCarte.size(); i++) {
			this.piocheGrand.listCarte.get(i).faceCachee = false;
			System.out.println(this.piocheGrand.listCarte.get(i).montrer());
			this.piocheGrand.listCarte.get(i).faceCachee = true;
		}
	}

	public void montrerPiochePetite() {
		for (int i = 0; i < this.piochePetite.listCarte.size(); i++) {
			this.piochePetite.listCarte.get(i).faceCachee = false;
			System.out.println(this.piochePetite.listCarte.get(i).montrer());
			this.piochePetite.listCarte.get(i).faceCachee = true;
		}
	}

	public void DisplayMain() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur i = (Joueur) it.next();
			i.regarderMain();
		}
	}

	// commencer le jeu
	public void commencer() {
		Scanner sc = new Scanner(System.in);
		boolean okJoueur = false;
		do {
			System.out.println("Entrez le nombre de joueurs");
			nbJoueurs = sc.nextInt();
			if (nbJoueurs > 4) {
				System.out.println("Trop de joueurs!");
				okJoueur = true;
			}
			else {
				okJoueur = false;
			}
		} while(okJoueur);
		int verifJoueur;
		while (this.joueurs.size() < nbJoueurs) {
			System.out.println("C'est un joueur: 1.Physique     2.Virtuel");
			verifJoueur = sc.nextInt();
			sc.nextLine();
			System.out.println("Entrez le prenom du joueur");
			if (verifJoueur == 1) {
				String prenom = sc.nextLine();
				JoueurPhys j = new JoueurPhys(prenom);
				this.joueurs.add(j);
			} else {
				String prenom = sc.nextLine();
				System.out.println("Choississez la difficulte");
				int niveau = sc.nextInt();
				JoueurVirt j = new JoueurVirt(niveau, prenom);
				this.joueurs.add(j);
			}
		}
		this.creerPioche();
		this.piocheGrand.melanger();
		this.preparer();

	}
	
	public VisitorDeJest choisirCompteur() {
		Scanner sc = new Scanner(System.in);
		boolean okVariante = true;
		do {
			System.out.println("Choisissez la règle: 1.Original     2.DLC Season Pass");
			int variante = sc.nextInt();
			if (variante == 1) {
				CompteurDeScore1 compteur1 = new CompteurDeScore1();
				okVariante = false;
				return (VisitorDeJest)compteur1;
				
			}
			else if (variante == 2) {
				CompteurDeScore2 compteur2 = new CompteurDeScore2();
				okVariante = false;
				return (VisitorDeJest)compteur2;
				
			}
			else {
				System.out.println("Veuillez choisir une option valide");
				okVariante = true;
			}
		} while(okVariante);
		return null;
		
	}

	public void faireOffreAll() {
		Scanner sc = new Scanner(System.in);
		for (Joueur j : joueurs) {
			String msg = String.format("%s, veuillez choisir une carte a mettre face recto, l'autre sera verso.",
					j.prenom);
			if (j instanceof JoueurVirt) {
				((JoueurVirt) j).faireOffre();
			} else {
				System.out.println(msg);
				int posCarteFaceCachee = sc.nextInt();
				j.faireOffre(posCarteFaceCachee - 1);
			}

		}
	}

	public void compterScore() {

		for (Joueur i : joueurs) {
			CompteurDeScore1 compteur = new CompteurDeScore1();
			System.out.println("Score de " + i.prenom);
			System.out.println(compteur.visiter(i.jest));
		}
	}

	public void choisirVainqueur() {
		Joueur JoueurMax = new Joueur();
		JoueurMax = this.joueurs.get(0);
		for (Joueur i : joueurs) {
			CompteurDeScore1 compteur = new CompteurDeScore1();
			i.setScore(compteur.visiter(i.jest));
			if (i.getScore() > JoueurMax.getScore()) {
				JoueurMax = i;
			}
		}
		System.out.println("Le vainqueur est " + JoueurMax.prenom);

	}

	public void afficherJest() {
		for (Joueur i : joueurs) {
			System.out.println("Le jest de " + i.prenom);
			for (int j = 0; j < i.jest.nombreDeCartes; j++) {
				i.jest.listCarte.get(j).faceCachee = false;
				System.out.println(i.jest.listCarte.get(j).montrer());
				i.jest.listCarte.get(j).faceCachee = true;
			}

		}
	}

	public static void main(String[] args) {

		// Declaration
		Scanner sc = new Scanner(System.in);
		Partie partie = new Partie();
		
		VisitorDeJest compteur = partie.choisirCompteur();
		partie.commencer();
		// Players look at their hand
		boolean condition = true;
		while (condition) {
			System.out.println(partie.piocheGrand.nombreDeCartes);
			partie.distribuerCartes();
			System.out.println();
			partie.DisplayMain();

			partie.faireOffreAll();

			partie.lancerRound();

			partie.numeroRound++;

			if (partie.piocheGrand.nombreDeCartes < partie.joueurs.size()) {
				condition = false;
			}
			partie.creerPiochePetit();

			partie.piochePetite.melanger();
		}
		partie.afficherJest();
		partie.montrerTrophee();
		
		partie.trophee.distribuerTrophee(partie.joueurs, compteur);
		partie.afficherJest();
		partie.compterScore();
		partie.choisirVainqueur();

	}
}
