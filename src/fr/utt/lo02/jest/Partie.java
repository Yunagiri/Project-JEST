
package fr.utt.lo02.jest;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Partie {
	//private int rounds;
	private boolean partieEnCours;
	private int numeroRound;

	private Pioche piocheGrand;
	private Trophee trophee;
	private Tas piochePetite;

	public ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	// Can't use melanger() on piocheGrand, there's nothing in its listCarte yet,
	// hence it also cannot distribuer(trophee), there's nothing to distribute.
	public Partie() {
		numeroRound = 1;
		piocheGrand = new Pioche();
		piochePetite = new Tas();
		trophee = new Trophee();
		System.out.println("Commence une partie de Jest...");
	}

	// create cartes and add to pioche
	public void creerPioche() {
		for (int i = 1; i < 5; i++) {
			for (suits s : suits.values()) {
				action temp = action.getRandomAction();
				if (temp == action.HIGHEST || temp == action.LOWEST ) {
					// System.out.println(action.getRandomAction());
					Conditions cond = new Conditions(temp, suits.getRandomSuits());
					// System.out.println("Creation d'une condition a 2 parametres");
					SuitCards carte = new SuitCards(i, true, cond, s);
					this.piocheGrand.listCarte.add(carte);
					this.piocheGrand.nombreDeCartes++;
				} else if (temp == action.MAJORITY) {
					Random r = new Random();
					Conditions cond = new Conditions(temp,1 + r.nextInt(4) );
					SuitCards carte = new SuitCards(i,true,cond,s);
					this.piocheGrand.listCarte.add(carte);
					this.piocheGrand.nombreDeCartes++;
				}
				else {
					// System.out.println(temp);
					Conditions cond = new Conditions(temp);
					// System.out.println("Creation d'une condition a 1 parametre");
					SuitCards carte = new SuitCards(i, true, cond, s);
					this.piocheGrand.listCarte.add(carte);
					this.piocheGrand.nombreDeCartes++;
				}
			}
		}
		for (int i = 1; i <= this.piocheGrand.listCarte.size(); i++) {
			this.piocheGrand.listCarte.get(i - 1).valeur = i;
		}
		Joker joker = new Joker();
		this.piocheGrand.listCarte.add(joker);
		joker.valeur = 0;
		this.piocheGrand.nombreDeCartes++;
	}
	//creer pioche petite
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
		for (Carte c: this.trophee.listCarte) {
			c.faceCachee = false;
			System.out.println(c.montrer() +"     ");
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
						j.prendreCartes(this.piochePetite.listCarte.get(piochePetite.nombreDeCartes - 1), this.piochePetite);

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

	public void verifierCondition() {

	}

	public void afficherScore() {

	}

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
		//for (int i = 0; i < this.joueurs.size(); i++) {
			
		//}
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur i = (Joueur) it.next();
			i.regarderMain();
		}
	}
	//commencer le jeu
	public void commencer(int nbJoueurs) {
		Scanner sc = new Scanner(System.in);
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
		for(Joueur i : joueurs) {
			CompteurDeScore compteur = new CompteurDeScore(1);
			System.out.println(i.prenom);
			compteur.compter(i.jest);
		}
	}
	public void afficherJest() {
		for(Joueur i : joueurs) {
			System.out.println("Le jest de "+ i.prenom);
			for(int j=0; j< i.jest.nombreDeCartes;j++) {
				i.jest.listCarte.get(j).faceCachee = false;
				System.out.println(i.jest.listCarte.get(j).montrer());
				i.jest.listCarte.get(j).faceCachee = false;
			}
			
		}
	}
	/*public void afficherTrophee() {
		System.out.println("La carte de trophee est :");
			for (int i = 0; i < this.trophee.listCarte.size(); i++) {
				this.trophee.listCarte.get(i).faceCachee = false;
				System.out.println(this.trophee.listCarte.get(i).montrer());
				
			}
	}*/
	public void distribuerTrophee() {
		for (Carte carteT : this.trophee.listCarte) {
			if ( carteT.condi.cond == action.HIGHEST) {
				Joueur JoueurMax = new Joueur();
				JoueurMax = joueurs.get(0);
				int index =0 ;
				for (Joueur joueur : joueurs) {
					for(int i =0; i< joueur.jest.listCarte.size();i++) {
						if(joueur.jest.listCarte.get(i).enseigne == carteT.condi.enseigne && joueur.jest.listCarte.get(i).hauteur > JoueurMax.jest.listCarte.get(index).hauteur ) {
							index=i;
							JoueurMax = joueur;
						}
					}
				}
				JoueurMax.prendreOffre(trophee.listCarte.indexOf(carteT), trophee);
			}
			else if ( carteT.condi.cond == action.LOWEST) {
				Joueur JoueurMin = new Joueur();
				JoueurMin = joueurs.get(0);
				int index =0 ;
				for (Joueur joueur : joueurs) {
					for(int i =0; i< joueur.jest.listCarte.size();i++) {
						if(joueur.jest.listCarte.get(i).enseigne == carteT.condi.enseigne && joueur.jest.listCarte.get(i).hauteur < JoueurMin.jest.listCarte.get(index).hauteur ) {
							index=i;
							JoueurMin = joueur;
						}
					}
				}
				JoueurMin.prendreOffre(trophee.listCarte.indexOf(carteT), trophee);
			}
			else if( carteT.condi.cond == action.MAJORITY) {
				Joueur JoueurMax = new Joueur();
				JoueurMax = joueurs.get(0);
				int highestMajority = 0;
				int highestValeur = 0;
				for(Joueur joueur : joueurs) {
					int majority = 0;
					int valeur =0;
					for(Carte carte : joueur.jest.listCarte) {
						if(carte.hauteur == carteT.condi.hauteur ) {
							majority++;
							if (carte.valeur > highestValeur) {
								highestValeur = carte.valeur;
							}
						}
					}
					if (majority > highestMajority) {
						JoueurMax = joueur;
					}
					if ( majority ==  highestMajority && valeur > highestValeur) {
						JoueurMax = joueur;
					}
						
				}
				JoueurMax.prendreOffre(trophee.listCarte.indexOf(carteT), trophee);
			}
		}
	}
	

	public static void main(String[] args) {

		// Declaration
		Scanner sc = new Scanner(System.in);
		Partie partie = new Partie();

		System.out.println("Entrez le nombre de joueurs");
		int nbJoueurs = sc.nextInt();
		partie.commencer(nbJoueurs);
		
		// Players look at their hand
		boolean condition = true;
	//	partie.afficherTrophee();
		
		while (condition) {
		System.out.println(partie.piocheGrand.nombreDeCartes);
			partie.distribuerCartes();

			partie.DisplayMain();

			partie.faireOffreAll();

			partie.lancerRound();
					
			partie.numeroRound ++;
			
			if (partie.piocheGrand.nombreDeCartes < partie.joueurs.size()) {
				condition = false;
			}
			partie.creerPiochePetit();
			
			partie.piochePetite.melanger();
		}
		partie.afficherJest();
		partie.distribuerTrophee();
		partie.compterScore();
		
	}
}
