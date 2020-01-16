package Vue;

import Modele.*;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Vue.PanelJeu;

/**
 * This class is the exterior mantle that envelops the Panels
 * 
 * @see PanelJeu, PanelJoueur,TableDeJeu
 * @author dinh_,tran_
 *
 */
public class JestInterface extends JFrame {
	/**
	 * It contains the panel
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private PanelJeu pjeu;

	/**
	 * The constructor initialise the attributes of this interface.
	 * 
	 * @param partie the game that is playing.
	 */
	public JestInterface(Partie partie) {
		super("Jest");
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setSize(960, 540);
		this.add(panel);

		for (Joueur joueur : partie.getListeJoueurs()) {
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