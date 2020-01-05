package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Modele.*;
import Vue.*;


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
							if(partie.joueurs.get(i).getMain().listCarte.size()==2) {
								joueurPhys.prendreOffre(j,partie.joueurs.get(i));
								partie.joueurActuel=partie.joueurs.get(i);
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
							if(partie.joueurs.indexOf(joueurPhys)==i) {
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
				if((ButtonCard)e.getSource()==panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[0]||(ButtonCard)e.getSource()==panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[1]) {
					for(int j=0;j<panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte().length;j++) {
						if((ButtonCard)e.getSource()==panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[j]) {
							partie.joueurs.get(partie.joueurs.indexOf(joueurPhys)).faireOffre(j);
						}
						panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[j].removeActionListener(faireOffre);
						
					}
					for(int i=0; i<panelJoueur.length;i++) {
						for(int j=0;j<panelJoueur[i].getPanelCarte().length;j++) {
							panelJoueur[i].getPanelCarte()[j].addActionListener(prendreOffre);
						}
					}
					Controleur.this.partie.continu();
				}
				
				
				
			}
		};
		
		
		
			for(int j=0;j<panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte().length;j++){
				panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[j].addActionListener(faireOffre);
			}
			
			panelJoueur[partie.joueurs.indexOf(joueurPhys)].getRegarderCarte().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(compteur==0) {
						for(int i=0;i<joueurPhys.getMain().listCarte.size();i++) {
							try {
								panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[i].renouvellerEtatDeCarte(false, joueurPhys.getMain().listCarte.get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						compteur=1;
					}else {
						for(int i=0;i<joueurPhys.getMain().listCarte.size();i++) {
							try {
								panelJoueur[partie.joueurs.indexOf(joueurPhys)].getPanelCarte()[i].renouvellerEtatDeCarte(true, joueurPhys.getMain().listCarte.get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							compteur=0;
						}
					}
				}
				
			});
			panelJoueur[partie.joueurs.indexOf(joueurPhys)].getRegarderJest().addActionListener(new ActionListener() {

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