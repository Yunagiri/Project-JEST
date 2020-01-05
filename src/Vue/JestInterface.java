package Vue;
import Modele.*;
import Controleur.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import Vue.Fenetre1;
//import Vue.Fenetre2;
//import Vue.FenetreProps;
//import Vue.FenetreTricks;
import Vue.PanelJeu;
import Vue.PanelJoueur;
import Vue.ButtonCard;
import Vue.TableDeJeu;

public class JestInterface extends JFrame{
	private JPanel panel;
	private PanelJeu pjeu;
//	private ControleurJeu ctr;
	public JestInterface(Partie partie) {
		super("Jest");
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setSize(960,540);
		this.add(panel);

		for (Joueur joueur : partie.joueurs) {
			if(joueur instanceof JoueurPhys) {
				pjeu=new PanelJeu(partie,(JoueurPhys) joueur);
				break;
			}
		}
		
		
	
		
		pjeu.setBounds(0,0,960,540);
		panel.add(pjeu);
		
//		if(jeu.getNombreJoueurReel()>=2) {
//			pjeu[1]= new PanelJeu(jeu, jr.get(1));
//			pjeu[1].setBounds(960,0,960,540);
//			panel.add(pjeu[1]);
//		}
//		if(jeu.getNombreJoueurReel()>=3) {
//			pjeu[2]= new PanelJeu(jeu, jr.get(2));
//			pjeu[2].setBounds(0,540,960,540);
//			panel.add(pjeu[2]);
//		}
//		if(jeu.getNombreJoueurReel()==4) {
//			pjeu[3]= new PanelJeu(jeu, jr.get(3));
//			pjeu[3].setBounds(960,540,960,540);
//			panel.add(pjeu[3]);
//		}
		
//		ctr = new ControleurJeu(jeu,jr,this);
		
//		if(jeu.getNombreJoueurReel()>1) {
//			this.setBounds(100, 100, 1920, 1080);
//		}
//		else {
//			this.setBounds(100, 100, 980,590);
//		}
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public PanelJeu getPanelJeu() {
		return pjeu;
	}
//	public ControleurJeu getControleur() {
//		return ctr;
//	}
}