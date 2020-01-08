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
		panel.setBackground(Color.BLUE);
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
		

		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public PanelJeu getPanelJeu() {
		return pjeu;
	}
//	public ControleurJeu getControleur() {

}