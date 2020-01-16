
package Modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * The core of the game, the Partie class inherits Observable and is where the game plays on console and a major part of it 
 * is used for the GUI interaction. The partie can chose the player that begins the round, give the turn to the next player,
 * supervise how a round of cards go and does this for both virtual and physical players. 
 * @author dinh_,tran_
 * @see Observable, Pioche, Trophee, Tas, Visitor, Joueur
 */
public class Partie extends Observable {
	/**
	 * If true, the game continues, else the game stops
	 */
	private boolean partieEnCours;
	/**
	 * The number of rounds that has passed in a single game
	 */
	public int numeroRound;
	/**
	 * The original deck where cards are distributed from
	 */
	private Pioche piocheGrand;
	/**
	 * The trophy zone, containing 2 trophy cards
	 */
	private Trophee trophee;
	/**
	 * The small deck which constitutes itself from player's hands
	 */
	private Tas piochePetite;
	/**
	 * The number of players in game
	 */
	private int nbJoueurs;
	/**
	 * The number of physical players in the game
	 */
	private int nbJoueursPhysic;
	/**
	 * Contains the visitor that will calculate the score of each player
	 */
	private Visitor compteur;
	/**
	 * The array containing all the players in game
	 */
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	public ArrayList<Joueur> getListeJoueurs(){
		return this.joueurs;
	}
	public Visitor getCompteur() {
		return this.compteur;
	}

	public void setCompteur(Visitor compteur) {
		this.compteur = compteur;
	}

	public boolean getPartieEnCours() {
		return this.partieEnCours;
	}

	public int getNumeroRound() {
		return this.numeroRound;
	}

	public Pioche getPiocheGrand() {
		return this.piocheGrand;
	}

	public int getnbJoueurs() {
		return this.nbJoueurs;
	}

	public Tas getpiochePetite() {
		return this.piochePetite;
	}
	
	/**
	 * This boolean act as a lock for the threads using Partie. 
	 */
	private boolean waitForIt;
	/**
	 * This is the player in turn
	 */
	public Joueur joueurActuel;
	
	/**
	 * This boolean checks if the player wants to use the GUI or the console
	 */
	public boolean console = false;

	/**
	 * The constructor for Partie. initialise the round at 1, create the original deck and a smaller deck
	 */
	public Partie() {
		numeroRound = 1;
		piocheGrand = new Pioche();
		piochePetite = new Tas();
		trophee = new Trophee();
		System.out.println("Commence une partie de Jest...");
	}

	public Trophee getTrophee() {
		return this.trophee;
	}

	public void setNbJoueurs(int a) {
		this.nbJoueurs = a;
	}

	public void setNbJoueursPhysic(int a) {
		this.nbJoueursPhysic = a;
	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

	/**
	 * This method create cards and add them to the original deck of Partie, using Carte constructor and Conditions.
	 * @see Carte, Conditions
	 */
	public void creerPioche() {

		Joker joker = new Joker();
		this.piocheGrand.getCarteTas().add(joker);
		this.piocheGrand.getCarteTas().get(0).valeur = 0;

		Conditions lowest_trefle = new Conditions(action.LOWEST, suits.TREFLE);
		SuitCards PIQUE_4 = new SuitCards(4, true, lowest_trefle, suits.PIQUE);
		this.piocheGrand.getCarteTas().add(PIQUE_4);
		this.piocheGrand.getCarteTas().get(1).valeur = 16;

		Conditions lowest_pique = new Conditions(action.LOWEST, suits.PIQUE);
		SuitCards TREFLE_4 = new SuitCards(4, true, lowest_pique, suits.TREFLE);
		this.piocheGrand.getCarteTas().add(TREFLE_4);
		this.piocheGrand.getCarteTas().get(2).valeur = 15;

		Conditions Joker = new Conditions(action.JOKER);
		SuitCards COEUR_4 = new SuitCards(4, true, Joker, suits.COEUR);
		this.piocheGrand.getCarteTas().add(COEUR_4);
		this.piocheGrand.getCarteTas().get(3).valeur = 13;

		Conditions best_nojoker = new Conditions(action.BEST_NOJOKER);
		SuitCards CARREAU_4 = new SuitCards(4, true, best_nojoker, suits.CARREAU);
		this.piocheGrand.getCarteTas().add(CARREAU_4);
		this.piocheGrand.getCarteTas().get(4).valeur = 14;

		Conditions lowest_coeur = new Conditions(action.LOWEST, suits.COEUR);
		SuitCards TREFLE_3 = new SuitCards(3, true, lowest_coeur, suits.TREFLE);
		this.piocheGrand.getCarteTas().add(TREFLE_3);
		this.piocheGrand.getCarteTas().get(5).valeur = 11;

		Conditions maj_2 = new Conditions(action.MAJORITY, 2);
		SuitCards PIQUE_3 = new SuitCards(3, true, maj_2, suits.PIQUE);
		this.piocheGrand.getCarteTas().add(PIQUE_3);
		this.piocheGrand.getCarteTas().get(6).valeur = 12;

		Conditions lowest_carreau = new Conditions(action.LOWEST, suits.CARREAU);
		SuitCards CARREAU_3 = new SuitCards(3, true, lowest_carreau, suits.CARREAU);
		this.piocheGrand.getCarteTas().add(CARREAU_3);
		this.piocheGrand.getCarteTas().get(7).valeur = 10;

		SuitCards COEUR_3 = new SuitCards(3, true, Joker, suits.COEUR);
		this.piocheGrand.getCarteTas().add(COEUR_3);
		this.piocheGrand.getCarteTas().get(8).valeur = 9;

		SuitCards TREFLE_2 = new SuitCards(2, true, lowest_coeur, suits.TREFLE);
		this.piocheGrand.getCarteTas().add(TREFLE_2);
		this.piocheGrand.getCarteTas().get(9).valeur = 7;

		Conditions maj_3 = new Conditions(action.MAJORITY, 3);
		SuitCards PIQUE_2 = new SuitCards(2, true, maj_3, suits.PIQUE);
		this.piocheGrand.getCarteTas().add(PIQUE_2);
		this.piocheGrand.getCarteTas().get(10).valeur = 8;

		Conditions highest_carreau = new Conditions(action.HIGHEST, suits.CARREAU);
		SuitCards CARREAU_2 = new SuitCards(2, true, highest_carreau, suits.CARREAU);
		this.piocheGrand.getCarteTas().add(CARREAU_2);
		this.piocheGrand.getCarteTas().get(11).valeur = 6;

		SuitCards COEUR_2 = new SuitCards(2, true, Joker, suits.COEUR);
		this.piocheGrand.getCarteTas().add(COEUR_2);
		this.piocheGrand.getCarteTas().get(12).valeur = 5;

		SuitCards COEUR_1 = new SuitCards(1, true, Joker, suits.COEUR);
		this.piocheGrand.getCarteTas().add(COEUR_1);
		this.piocheGrand.getCarteTas().get(13).valeur = 1;

		SuitCards PIQUE_1 = new SuitCards(1, true, lowest_trefle, suits.PIQUE);
		this.piocheGrand.getCarteTas().add(PIQUE_1);
		this.piocheGrand.getCarteTas().get(14).valeur = 4;

		Conditions highest_pique = new Conditions(action.HIGHEST, suits.PIQUE);
		SuitCards TREFLE_1 = new SuitCards(1, true, highest_pique, suits.TREFLE);
		this.piocheGrand.getCarteTas().add(TREFLE_1);
		this.piocheGrand.getCarteTas().get(15).valeur = 3;

		Conditions maj_4 = new Conditions(action.MAJORITY, 4);
		SuitCards CARREAU_1 = new SuitCards(1, true, maj_4, suits.CARREAU);
		this.piocheGrand.getCarteTas().add(CARREAU_1);
		this.piocheGrand.getCarteTas().get(16).valeur = 2;

		this.piocheGrand.nombreDeCartes = 17;

	}

	public int getNumeroRounds() {
		return this.numeroRound;
	}

	/**
	 * Create the smaller deck where cards not taken at the end of the round are sent to.
	 */
	public void creerPiochePetit() {
		if (this.piocheGrand.nombreDeCartes >= this.joueurs.size()) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur o = (Joueur) it.next();
				o.main.getCarteTas().get(0).faceCachee = true;
				this.piochePetite.getCarteTas().add(o.main.getCarteTas().get(0));
				o.main.getCarteTas().remove(0);
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
				o.prendreOffre(0, o);
			}
		}
	}

	/**
	 *  shuffles piocheGrand AND distribute 2 cards to the trophy.
	 */
	public void preparer() {

		this.piocheGrand.melanger();
		for (int i = 0; i < 2; i++) {
			this.piocheGrand.distribuer(this.trophee);
			System.out.println(trophee.getCarteTas().size());
		}

	}
	
	/**
	 * Show the trophy cards on the console
	 */
	public void montrerTrophee() {
		System.out.println("Les trophees sont: ");
		for (Carte c : this.trophee.getCarteTas()) {
			c.faceCachee = false;
			System.out.println(c.montrer() + "     ");
		}
	}

	/**
	 *  This method allows each player to take 2 cards from the piocheGrand in the 1st round and 2 cards from the piochePetit from the 2nd round onwards
	 */
	public void distribuerCartes() {
		this.partieEnCours = true;
		Iterator<Joueur> it = joueurs.iterator();

		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			if (this.numeroRound == 1) {
				String msg = String.format("Distribution en cours au joueur %s", j.prenom);
				System.out.println(msg);
				for (int i = 0; i < 2; i++) {
					j.prendreCartes(this.piocheGrand.getCarteTas().get(piocheGrand.nombreDeCartes - 1), this.piocheGrand);

				}
			} else {
				String msg = String.format("Distribution en cours au joueur %s", j.prenom);
				System.out.println(msg);
				for (int i = 0; i < 2; i++) {
					j.prendreCartes(this.piochePetite.getCarteTas().get(piochePetite.nombreDeCartes - 1),
							this.piochePetite);

				}
			}
		}

	}

	/**
	 * gives the turn to the designated player
	 * @param joueur the designated player
	 */
	public void donnerTour(Joueur joueur) {
		if (joueur.estEnTour == false) {
			joueur.estEnTour = true;
			String msg = String.format("\nAu tour de %s de jouer.", joueur.prenom);
			System.out.println(msg);
		}
	}

	
	/**
	 * End a player's round
	 * @param joueur the designated player
	 */
	public void finirTour(Joueur joueur) {
		joueur.estEnTour = false;
		String msg = String.format("Fin du tour de %s.", joueur.prenom);
		System.out.println(msg);
	}

	/**
	 * This method choose the next player to get to play
	 */
	public void choisirJoueur() {

		Joueur joueurMax = new Joueur();
		Iterator<Joueur> it = joueurs.iterator();
		joueurMax = it.next();
		while (it.hasNext()) {
			Joueur joueurActuel = (Joueur) it.next();
			for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
				if (!joueurActuel.main.getCarteTas().get(counter).faceCachee) {
					if (joueurActuel.main.getCarteTas().get(counter).valeur > joueurMax.main.getCarteTas()
							.get(counter).valeur) {
						joueurMax = joueurActuel;
					}

				}
			}
		}
		this.donnerTour(joueurMax);
	}

	/**
	 * End the game
	 */
	public void terminer() {
		this.partieEnCours = false;
	}

	/**
	 * This method simulates what a player's round is like for both virtual and physical players. First, they must chose a player to 
	 * take an offer from, then choose a card from their offer to take.
	 */
	public void lancerRound() {
		this.choisirJoueur();
		int tours = 0;
		ArrayList<Joueur> temp1 = new ArrayList<Joueur>();
		temp1.addAll(joueurs);
		//While all the players haven't had their turn yet 
		while (tours < this.joueurs.size()) {
			Iterator<Joueur> itJoueur = joueurs.iterator();
			//Iterates all players
			while (itJoueur.hasNext()) {
				Joueur a = (Joueur) itJoueur.next();
				//Checks each player to see if it's their turn 
				while (a.estEnTour) {
					temp1.remove(a);
					for (int j = 0; j < this.joueurs.size(); j++) {
						System.out.println("Main de " + this.joueurs.get(j).getPrenom());
						this.joueurs.get(j).montrerOffre();
					}
					boolean differentPrenom = true;
					//The virtual player's path
					if (a instanceof JoueurVirt) {
						Joueur d;
						do {
							//Choose a player 
							d = ((JoueurVirt) a).choisirJoueur(joueurs);
							differentPrenom = true;
							//Check if a chose himself or not
							if (a.prenom.equals(d.prenom)) {
								differentPrenom = false;
								ArrayList<Joueur> temp = new ArrayList<Joueur>();
								temp.addAll(joueurs);
								//a had their turn, we remove him from the array of players 
								temp.remove(a);
								//Check if a CAN actually choose himself or not
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
						
						//Choosing the next player 
						Joueur prochainJoueur = new Joueur();
						Iterator<Joueur> it = joueurs.iterator();
						while (it.hasNext()) {
							Joueur o = (Joueur) it.next();
							//if the player chosen by a is o, a takes his offer
							if (o.getPrenom().equals(d.prenom)) {
								a.prendreOffre(1, o);
								//if o is in the list of players waiting for their turn
								if (temp1.indexOf(o) != -1) {
									prochainJoueur = o;
									
								} 
								//else, find the next player to give turn to
								else {
									Iterator<Joueur> it1 = temp1.iterator();
									if (temp1.size() != 0) {
										prochainJoueur = it1.next();
										while (it1.hasNext()) {
											Joueur joueurActuel = (Joueur) it1.next();
											//compare the face up value of the hands of remaining players
											for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
												if (!joueurActuel.main.getCarteTas().get(counter).faceCachee) {
													if (joueurActuel.main.getCarteTas()
															.get(counter).valeur > prochainJoueur.main.getCarteTas()
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
						//end a's turn and give the turn to the next player
						this.finirTour(a);
						this.donnerTour(prochainJoueur);
						tours++;
					}
					//Physical player path
					else {
						Scanner sc = new Scanner(System.in);
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
												if (!joueurActuel.main.getCarteTas().get(counter).faceCachee) {
													if (joueurActuel.main.getCarteTas()
															.get(counter).valeur > prochainJoueur.main.getCarteTas()
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
		this.augmenternumeroRound();
	}

	/**
	 * Increment round number and notify the observers
	 */
	public void augmenternumeroRound() {
		this.numeroRound++;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Show the original deck on console
	 */
	public void montrerPiocheGrand() {
		for (int i = 0; i < this.piocheGrand.getCarteTas().size(); i++) {
			this.piocheGrand.getCarteTas().get(i).faceCachee = false;
			System.out.println(this.piocheGrand.getCarteTas().get(i).montrer());
			this.piocheGrand.getCarteTas().get(i).faceCachee = true;
		}
	}

	/**
	 * Show the small deck on console
	 */
	public void montrerPiochePetite() {
		for (int i = 0; i < this.piochePetite.getCarteTas().size(); i++) {
			this.piochePetite.getCarteTas().get(i).faceCachee = false;
			System.out.println(this.piochePetite.getCarteTas().get(i).montrer());
			this.piochePetite.getCarteTas().get(i).faceCachee = true;
		}
	}

	/**
	 * Show the cards in a player's hand
	 */
	public void DisplayMain() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur i = (Joueur) it.next();
			i.regarderMain();
		}
	}

	/**
	 * This method allows one to start a game, asks for a number of players, their names, if they're virtual or physical and if they're virtual,
	 * indicates the difficulty of the virtual player.
	 */
	public void commencer() {
		Scanner sc = new Scanner(System.in);
		boolean okJoueur = false;
		do {
			System.out.println("Entrez le nombre de joueurs");
			nbJoueurs = sc.nextInt();
			if (nbJoueurs > 4) {
				System.out.println("Trop de joueurs!");
				okJoueur = true;
			} else {
				okJoueur = false;
			}
		} while (okJoueur);
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

	/**
	 * This method chooses the rules by which the score will be calculated
	 */
	public void choisirCompteur() {
		Scanner sc = new Scanner(System.in);
		boolean okVariante = true;
		do {
			System.out.println("Choisissez la regle: 1.Original     2.DLC Season Pass");
			int variante = sc.nextInt();
			if (variante == 1) {
				CompteurDeScore1 compteur1 = new CompteurDeScore1();
				okVariante = false;
				this.compteur = compteur1;

			} else if (variante == 2) {
				CompteurDeScore2 compteur2 = new CompteurDeScore2();
				okVariante = false;
				this.compteur = compteur2;

			} else {
				System.out.println("Veuillez choisir une option valide");
				okVariante = true;
			}
		} while (okVariante);

	}

	
	/**
	 * This method allows everyone to make offers
	 */
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

	/**
	 * This method uses the counter to visit each player's jest and get their score
	 * @param v the visitor, can be either CompteurDeScore1 or CompteurDeScore2
	 * 
	 * @see CompteurDeScore1, CompteurDeScore2
	 */
	public void compterScore(Visitor v) {

		for (Joueur i : joueurs) {

			System.out.println("Score de " + i.prenom);
			i.getJest().accept(v);

		}
	}

	
	/**
	 * This method chooses the winner of the game
	 */
	public void choisirVainqueur() {
		Joueur JoueurMax = new Joueur();
		JoueurMax = this.joueurs.get(0);
		for (Joueur i : joueurs) {
			i.setScore(this.compteur.visit(i.jest));
			if (i.getScore() > JoueurMax.getScore()) {
				JoueurMax = i;
			}
		}
		System.out.println("Le vainqueur est " + JoueurMax.prenom);
		JOptionPane.showMessageDialog(null, "1er: " + JoueurMax.prenom + " avec " + JoueurMax.getScore() + "\n");

	}

	
	/**
	 * This method pauses all threads that accesses this instance.
	 * @throws InterruptedException
	 */
	public synchronized void pause() throws InterruptedException {
		this.waitForIt = true;
		while (this.waitForIt) {
			this.wait();
		}
	}

	/**
	 * This method resumes and give the lock back to the next thread waiting, it sends out a notification to all Observers.
	 */
	public synchronized void continu() {
		this.waitForIt = false;
		this.notifyAll();
	}

	/**
	 * This method shows the jests of all the players in console.
	 */
	public void afficherJest() {
		for (Joueur i : joueurs) {
			System.out.println("Le jest de " + i.prenom);
			for (int j = 0; j < i.jest.nombreDeCartes; j++) {
				i.jest.getCarteTas().get(j).faceCachee = false;
				System.out.println(i.jest.getCarteTas().get(j).montrer());
				i.jest.getCarteTas().get(j).faceCachee = true;
			}

		}
	}

	
	/**
	 * The main method, it creates the Partie and simulates an entire game
	 * @param args
	 */
	public static void main(String[] args) {

		// Declaration
		Scanner sc = new Scanner(System.in);
		Partie partie = new Partie();

		partie.choisirCompteur();
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

			if (partie.piocheGrand.nombreDeCartes < partie.joueurs.size()) {
				condition = false;
			}
			partie.creerPiochePetit();

			partie.piochePetite.melanger();
		}
		partie.afficherJest();
		partie.montrerTrophee();

		partie.trophee.distribuerTrophee(partie.joueurs, partie.compteur);
		partie.afficherJest();
		partie.compterScore(partie.getCompteur());
		partie.choisirVainqueur();

	}
}
