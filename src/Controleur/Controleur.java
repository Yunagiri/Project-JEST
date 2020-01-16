package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Modele.*;
import Vue.*;

/**
 * This class receives information from the Vue package and transmit it to the Modele package
 * @author dinh_
 *
 */
public class Controleur {
	//Vue
	private PanelJeu panelJeu;
	private PanelJoueur[] panelJoueur;
	private TableDeJeu tableDeJeu;
	//Modele
	private Partie partie;
	private JoueurPhys joueurPhys;
	
	private int compteur=0;
	
	//ActionListener
	private ActionListener faireOffre;
	private ActionListener prendreOffre;
	
	
	/**
	 * This constructor defines all the actions possible on the GUI, each click on a button has a different function 
	 * and this class is the one who defines those.
	 * @param partie the game being played	
	 * @param panelJeu the GUI 
	 */
	public Controleur(Partie partie,PanelJeu panelJeu) {
		this.partie=partie;
		this.panelJeu=panelJeu;
		this.panelJoueur=panelJeu.getPanelJoueur();
		this.tableDeJeu=panelJeu.getTableDeJeu();
		
		this.joueurPhys=panelJeu.getJoueurReel();
		
		prendreOffre= new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean reset= false;
				for(int i=0; i<panelJoueur.length;i++) {
					for(int j=0;j<panelJoueur[i].getPanelCarte().length;j++) {
						if((ButtonCard)e.getSource()==panelJoueur[i].getPanelCarte()[j]) {
							if(partie.getListeJoueurs().get(i).getMain().getCarteTas().size()==2) {
								joueurPhys.prendreOffre(j,partie.getListeJoueurs().get(i));
								partie.joueurActuel=partie.getListeJoueurs().get(i);
								reset=true;
							}
							else {
								JOptionPane.showMessageDialog(null, "Il reste encore des gens avec deux cartes");
							}
							
						}
						
					}
				}
				if(reset) {
					for(int i=0; i<panelJoueur.length;i++) {
						for(int j=0;j<panelJoueur[i].getPanelCarte().length;j++) {
							panelJoueur[i].getPanelCarte()[j].removeActionListener(prendreOffre);
							if(partie.getListeJoueurs().indexOf(joueurPhys)==i) {
								panelJoueur[i].getPanelCarte()[j].addActionListener(faireOffre);
							}
						}
					}
					partie.continu();
					
				}
				
				
			}
			
		};
		
		faireOffre=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if((ButtonCard)e.getSource()==panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[0]||(ButtonCard)e.getSource()==panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[1]) {
					for(int j=0;j<panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte().length;j++) {
						if((ButtonCard)e.getSource()==panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[j]) {
							partie.getListeJoueurs().get(partie.getListeJoueurs().indexOf(joueurPhys)).faireOffre(j);
						}
						panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[j].removeActionListener(faireOffre);
						
					}
					for(int i=0; i<panelJoueur.length;i++) {
						for(int j=0;j<panelJoueur[i].getPanelCarte().length;j++) {
							panelJoueur[i].getPanelCarte()[j].addActionListener(prendreOffre);
						}
					}
					for (Joueur j : Controleur.this.partie.getListeJoueurs()) {
						if (j instanceof JoueurVirt) {
							((JoueurVirt) j).faireOffre();
						}
					}
					Controleur.this.partie.continu();
				}
				
				
				
			}
		};
		
		
		
			for(int j=0;j<panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte().length;j++){
				panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[j].addActionListener(faireOffre);
			}
			
			panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getRegarderCarte().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(compteur==0) {
						for(int i=0;i<joueurPhys.getMain().getCarteTas().size();i++) {
							try {
								panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[i].renouvellerEtatDeCarte(false, joueurPhys.getMain().getCarteTas().get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						compteur=1;
					}else {
						for(int i=0;i<joueurPhys.getMain().getCarteTas().size();i++) {
							try {
								panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getPanelCarte()[i].renouvellerEtatDeCarte(true, joueurPhys.getMain().getCarteTas().get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							compteur=0;
						}
					}
				}
				
			});
			panelJoueur[partie.getListeJoueurs().indexOf(joueurPhys)].getRegarderJest().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Thread t = new Thread() {
						public void run() {
							FenetreJest f=new FenetreJest(joueurPhys.getJest());
							f.resetCompteur();
							f.setVisible(true);
							
						};
					};
					t.start();
					
				}
				
			});
				
			
		
		
		
	}
	
}
