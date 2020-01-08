package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Modele.CompteurDeScore1;
import Modele.Joueur;
import Modele.JoueurPhys;
import Modele.JoueurVirt;
import Modele.Partie;
import VueText.VueText;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class FenetreParamettre extends JFrame {

	private Partie partie;
	
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JCheckBox chckbxDifficile;
	private JCheckBox chckbxFacile;
	private JButton btnNewButton;
	
	
	
	
	public FenetreParamettre(Partie partie) {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(false);
		
		comboBox = new JComboBox();
		comboBox.setBounds(170, 41, 43, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4" }));
		this.getContentPane().add(comboBox);
		
		JLabel lblCombienDeJoueurs = new JLabel("Nombre de joueurs reels:");
		lblCombienDeJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCombienDeJoueurs.setBounds(12, 96, 189, 40);
		this.getContentPane().add(lblCombienDeJoueurs);
		
		JLabel lblNombredejoueur = new JLabel("Nombre de joueurs:");
		lblNombredejoueur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombredejoueur.setBounds(12, 43, 165, 16);
		this.getContentPane().add(lblNombredejoueur);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1","2", "3","4"}));
		comboBox_1.setBounds(213, 107, 43, 22);
		this.getContentPane().add(comboBox_1);
		
		chckbxFacile = new JCheckBox("Facile");
		chckbxFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxDifficile.setSelected(false);
			}
		});
		chckbxFacile.setBounds(157, 175, 75, 25);
		this.getContentPane().add(chckbxFacile);
		
		chckbxDifficile = new JCheckBox("Difficile");
		chckbxDifficile.setBounds(276, 175, 113, 25);
		chckbxDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxFacile.setSelected(false);
			}
		});
		this.getContentPane().add(chckbxDifficile);
		
		JLabel lblNiveau = new JLabel("Niveau de difficulte:");
		lblNiveau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNiveau.setBounds(21, 178, 128, 16);
		this.getContentPane().add(lblNiveau);
		
		btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int a =Integer.parseInt(comboBox.getSelectedItem().toString());
				int b =Integer.parseInt(comboBox_1.getSelectedItem().toString());
				if((chckbxFacile.isSelected() || chckbxDifficile.isSelected())& a>=b) {
					partie.setNbJoueurs(Integer.parseInt(comboBox.getSelectedItem().toString()));
					partie.setNbJoueursPhysic(Integer.parseInt(comboBox_1.getSelectedItem().toString()));
					for(int j=0;j<a;j++) {
						if(j<b) {
							Joueur joueur= new JoueurPhys(Joueur.numero+"");
							partie.joueurs.add(joueur);

						}
						else if(j>=b) {
							if(chckbxFacile.isSelected()) {
								Joueur joueur= new JoueurVirt(1,Joueur.numero+"");
								partie.joueurs.add(joueur);

							}
							else {
								Joueur joueur= new JoueurVirt(2,Joueur.numero+"");
								partie.joueurs.add(joueur);
							}
							
						}
					}
					FenetreParamettre.this.setVisible(false);
//					partie.continu();
					VueText vue = new VueText(partie);
					partie.creerPioche();
					partie.getPiocheGrand().melanger();
					partie.preparer();
					JestInterface jf= new JestInterface(partie);
					jf.setVisible(true);
					System.out.println("Choisir une carte a faire offre");
					vue.start();
					Thread t= new Thread() {
						public void run() {
							try {
								boolean condition =true;
								while(condition) {
									partie.distribuerCartes();
									partie.continu();
									JOptionPane.showMessageDialog(null, "Choisir une carte a faire offre");
									partie.pause();
									System.out.println("--------------------------------");
									if(!partie.console) {
										partie.choisirJoueur();
										int tours = 0;
										ArrayList<Joueur> temp1 = new ArrayList<Joueur>();
										ArrayList<Joueur> joueurs= partie.joueurs;
										temp1.addAll(joueurs);
										while (tours < joueurs.size()) {
											// Actions to take in a single turn of a player: Choose a player, take a card in
											// their hand and put it in jest.
											// for (int i = 0; i < this.joueurs.size(); i++) {
											Iterator<Joueur> itJoueur = joueurs.iterator();
											while (itJoueur.hasNext()) {
												Joueur a = (Joueur) itJoueur.next();
												while (a.estEnTour) {
													temp1.remove(a);
													boolean differentPrenom = true;
													if (a instanceof JoueurVirt) {
														Thread.sleep(500);
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
																Thread.sleep(1000);
																a.prendreOffre(0, o);
																if (temp1.indexOf(o) != -1) {
																	prochainJoueur = o;
																} else {
																	Iterator<Joueur> it1 = temp1.iterator();
																	if (temp1.size() != 0) {
																		prochainJoueur = it1.next();
																		while (it1.hasNext()) {
																			Joueur joueurActuel = (Joueur) it1.next();
																			for (int counter = 0; counter < joueurActuel.main.nombreDeCartes; counter++) {
																				if (!joueurActuel.main.listCarte.get(counter).getFaceCachee()) {
																					if (joueurActuel.main.listCarte
																							.get(counter).getValeur() > prochainJoueur.main.listCarte
																									.get(counter).getValeur()) {
																						prochainJoueur = joueurActuel;
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														partie.finirTour(a);
														partie.donnerTour(prochainJoueur);
														tours++;
													}else {
														System.out.println("A vous de jouer!");
														partie.notifyObservers("prendreOffre");
														JOptionPane.showMessageDialog(null, "A vous de jouer");
														partie.pause();
														Joueur prochainJoueur = new Joueur();
														System.out.println("Recherche du joueur en cours");
														/*
														 * for (int recherche = 0; recherche < this.joueurs.size(); recherche++) { if
														 * (this.joueurs.get(recherche).getPrenom().equals(prenom)) {
														 * this.joueurs.get(i).prendreOffre(z, this.joueurs.get(recherche));
														 * prochainJoueur = this.joueurs.get(recherche); } }
														 */
		//												Iterator<Joueur> it = joueurs.iterator();
		//												while (it.hasNext()) {
		//													Joueur o = (Joueur) it.next();
		//													if (o.getPrenom().equals(window.partie.joueurActuel.prenom)) {
																if (temp1.indexOf(partie.joueurActuel) != -1) {
																	prochainJoueur = partie.joueurActuel;
																} else {
																	Iterator<Joueur> it1 = temp1.iterator();
																	
																	if (temp1.size() != 0) {
																		prochainJoueur = it1.next();
																		while (it1.hasNext()) {
																			Joueur joueurActuel1 = (Joueur) it1.next();
																			for (int counter = 0; counter < joueurActuel1.main.nombreDeCartes; counter++) {
																				if (!joueurActuel1.main.listCarte.get(counter).getFaceCachee()) {
																					if (joueurActuel1.main.listCarte
																							.get(counter).getValeur() > prochainJoueur.main.listCarte
																									.get(counter).getValeur()) {
																						prochainJoueur = joueurActuel1;
																					}
																				}
																			}
																		}
																	}
																}
		//													}
		//												}
														partie.finirTour(a);
														partie.donnerTour(prochainJoueur);
														tours++;
													}
												
												}
											}
										}
									}else {
										System.out.println("---------------------");
										partie.continu();
										System.out.println("---------------------");
										System.out.println("---------------------");

										partie.pause();
										System.out.println("--------------------");
									}
									partie.numeroRound++;
									
									if (partie.getPiocheGrand().nombreDeCartes < partie.joueurs.size()) {
										condition = false;
									}
									partie.creerPiochePetit();
									
									partie.getpiochePetite().melanger();
								}
								partie.notifyObservers("terminer");
								partie.continu();
								partie.getTrophee().distribuerTrophee(partie.joueurs, new CompteurDeScore1());
								partie.compterScore();
								partie.afficherJest();
								partie.choisirVainqueur();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
					t.start();

				}
			}
		});
		btnNewButton.setBounds(167, 215, 97, 25);
		this.getContentPane().add(btnNewButton);

		 
	}
	
}