package Vue;

import Modele.*;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Vue.PanelJeu;

public class JestInterface extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private PanelJeu pjeu;

	public JestInterface(Partie partie) {
		super("Jest");
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setSize(960, 540);
		this.add(panel);

		for (Joueur joueur : partie.joueurs) {
			if (joueur instanceof JoueurPhys) {
				pjeu = new PanelJeu(partie, (JoueurPhys) joueur);
				break;
			}
		}

		pjeu.setBounds(0, 0, 960, 540);
		panel.add(pjeu);

		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public PanelJeu getPanelJeu() {
		return pjeu;
	}
}