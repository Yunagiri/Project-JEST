package Vue;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modele.CompteurDeScore1;
import Modele.Joueur;
import Modele.JoueurVirt;
import Modele.Partie;
import Modele.VisitorDeJest;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InterfaceTest {

	private JestInterface jinter;
	private FenetreParamettre fp;
	
	private Partie partie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceTest window = new InterfaceTest();
					window.fp.setVisible(true);
//					JOptionPane.showMessageDialog(null, "Choisir une carte a faire offre");
//					Thread t= new Thread() {
//						public void run() {
//							try {
//								boolean condition =true;
//								while(condition) {
//									window.partie.distribuerCartes();
//									window.partie.pause();
//									window.partie.choisirJoueur();
//									int tours = 0;
//									ArrayList<Joueur> temp1 = new ArrayList<Joueur>();
//									ArrayList<Joueur> joueurs= window.partie.joueurs;
//									temp1.addAll(joueurs);
//									while (tours < joueurs.size()) {
//										// Actions to take in a single turn of a player: Choose a player, take a card in
//										// their hand and put it in jest.
//										// for (int i = 0; i < this.joueurs.size(); i++) {
//										Iterator<Joueur> itJoueur = joueurs.iterator();
//										while (itJoueur.hasNext()) {
//											Joueur a = (Joueur) itJoueur.next();
//											while (a.estEnTour) {
//												temp1.remove(a);
//												boolean differentPrenom = true;
//												if (a instanceof JoueurVirt) {
//													Thread.sleep(500);
//													Joueur d;
//													do {
//														d = ((JoueurVirt) a).choisirJoueur(joueurs);
//														differentPrenom = true;
//														if (a.prenom.equals(d.prenom)) {
//															differentPrenom = false;
//															ArrayList<Joueur> temp = new ArrayList<Joueur>();
//															temp.addAll(joueurs);
//															temp.remove(a);
//															for (Joueur j : temp) {
//																if (j.main.nombreDeCartes == 2) {
//																	System.out.println("Il reste encore des gens ayant 2 cartes!");
//																	differentPrenom = true;
//																}
//															}
//														} else if (d.main.nombreDeCartes == 1) {
//															String msg = String.format("%s n'a seulement qu'une carte", d.prenom);
//															System.out.println(msg);
//														} else {
//															differentPrenom = false;
//														}
//													} while (differentPrenom);
//	
//													Joueur prochainJoueur = new Joueur();
//													Iterator<Joueur> it = joueurs.iterator();
//													while (it.hasNext()) {
//														Joueur o = (Joueur) it.next();
//														if (o.getPrenom().equals(d.prenom)) {
//															Thread.sleep(1000);
//															a.prendreOffre(0, o);
//															if (temp1.indexOf(o) != -1) {
//																prochainJoueur = o;
//															} else {
//																Iterator<Joueur> it1 = temp1.iterator();
//																if (temp1.size() != 0) {
//																	prochainJoueur = it1.next();
//																	while (it1.hasNext()) {
//																		Joueur joueurActuel = (Joueur) it1.next();
//																		for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
//																			if (!joueurActuel.main.listCarte.get(counter).faceCachee) {
//																				if (joueurActuel.main.listCarte
//																						.get(counter).valeur > prochainJoueur.main.listCarte
//																								.get(counter).valeur) {
//																					prochainJoueur = joueurActuel;
//																				}
//																			}
//																		}
//																	}
//																}
//															}
//														}
//													}
//													window.partie.finirTour(a);
//													window.partie.donnerTour(prochainJoueur);
//													tours++;
//												}else {
//													JOptionPane.showMessageDialog(null, "A vous de jouer");
//													window.partie.pause();
//													Joueur prochainJoueur = new Joueur();
//													System.out.println("Recherche du joueur en cours");
//													/*
//													 * for (int recherche = 0; recherche < this.joueurs.size(); recherche++) { if
//													 * (this.joueurs.get(recherche).getPrenom().equals(prenom)) {
//													 * this.joueurs.get(i).prendreOffre(z, this.joueurs.get(recherche));
//													 * prochainJoueur = this.joueurs.get(recherche); } }
//													 */
//	//												Iterator<Joueur> it = joueurs.iterator();
//	//												while (it.hasNext()) {
//	//													Joueur o = (Joueur) it.next();
//	//													if (o.getPrenom().equals(window.partie.joueurActuel.prenom)) {
//															if (temp1.indexOf(window.partie.joueurActuel) != -1) {
//																prochainJoueur = window.partie.joueurActuel;
//															} else {
//																Iterator<Joueur> it1 = temp1.iterator();
//																if (temp1.size() != 0) {
//																	prochainJoueur = it1.next();
//																	while (it1.hasNext()) {
//																		Joueur joueurActuel1 = (Joueur) it1.next();
//																		for (int counter = 0; counter < joueurActuel1.main.nombreDeCartes; counter++) {
//																			if (!joueurActuel1.main.listCarte.get(counter).faceCachee) {
//																				if (joueurActuel1.main.listCarte
//																						.get(counter).valeur > prochainJoueur.main.listCarte
//																								.get(counter).valeur) {
//																					prochainJoueur = joueurActuel1;
//																				}
//																			}
//																		}
//																	}
//																}
//															}
//	//													}
//	//												}
//													window.partie.finirTour(a);
//													window.partie.donnerTour(prochainJoueur);
//													tours++;
//												}
//											
//											}
//										}
//									}
//									window.partie.numeroRound++;
//
//									if (window.partie.piocheGrand.nombreDeCartes < window.partie.joueurs.size()) {
//										condition = false;
//									}
//									window.partie.creerPiochePetit();
//
//									window.partie.piochePetite.melanger();
//								}
//								window.partie.trophee.distribuerTrophee(window.partie.joueurs, new CompteurDeScore1());
//								window.partie.compterScore();
//								window.partie.choisirVainqueur();
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					};
//					t.start();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public InterfaceTest() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		
		
		partie= new Partie();
		
//		partie.pause();
		this.fp= new FenetreParamettre(partie);
		
		
//			System.out.println(partie.joueurs.get(0).getMain().listCarte.get(0).montrer());
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fp.getContentPane().setLayout(null);
	}
		
}
