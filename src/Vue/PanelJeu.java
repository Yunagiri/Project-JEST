
package Vue;

import java.awt.Color;

import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Modele.*;
import Controleur.*;

/**
 * This class is the observer in the Observer design pattern: it answers to a
 * change in state of the Observable. This class represents the zone of the
 * game, it contains the PanelJoueur, the TableDeJeu
 * 
 * @author dinh_,tran_
 * @see PanelJoueur
 */
public class PanelJeu extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Partie partie;
	private JoueurPhys joueurPhys;
	private PanelJoueur[] pj;
	private TableDeJeu tdj;
	private JLabel tour;

	/**
	 * This constructor initialises most of the elements of the GUI
	 * 
	 * @param partie the game being player
	 * @param jp     the physical player
	 */
	public PanelJeu(Partie partie, JoueurPhys jp) {
		this.joueurPhys = jp;
		setLayout(null);
		this.setBackground(new Color(0, 0, 255));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setSize(960, 540);
		pj = new PanelJoueur[partie.getListeJoueurs().size()];
		if (partie.getListeJoueurs().size() == 2) {
			pj[0] = this.creerEspaceDeJoueur(5, this.getHeight() * 720 / 1080);
			pj[1] = this.creerEspaceDeJoueur(this.getWidth() * 1365 / 1920, 5);
		} else if (partie.getListeJoueurs().size() == 3) {
			pj[0] = this.creerEspaceDeJoueur(5, this.getHeight() * 720 / 1080);
			pj[1] = this.creerEspaceDeJoueur(this.getWidth() * 1365 / 1920, 5);
			pj[2] = this.creerEspaceDeJoueur(this.getWidth() * 1365 / 1920, this.getHeight() * 720 / 1080);
		} else if (partie.getListeJoueurs().size() == 4) {
			pj[0] = this.creerEspaceDeJoueur(5, this.getHeight() * 720 / 1080);
			pj[1] = this.creerEspaceDeJoueur(5, 5);
			pj[2] = this.creerEspaceDeJoueur(this.getWidth() * 1365 / 1920, 5);
			pj[3] = this.creerEspaceDeJoueur(this.getWidth() * 1365 / 1920, this.getHeight() * 720 / 1080);
		}
		for (int i = 0; i < partie.getListeJoueurs().size(); i++) {
			if (pj[i] != null) {
				partie.getListeJoueurs().get(i).addObserver(pj[i]);
				if (partie.getListeJoueurs().get(i) instanceof JoueurPhys) {
					System.out.println("nice!" + i);
				}
				pj[i].setVisible(true);
				this.add(pj[i]);
			}
		}
		pj[jp.getId() - 1].getRegarderJest().setVisible(true);
		pj[jp.getId() - 1].getRegarderCarte().setVisible(true);
		this.tdj = this.creerTableDeJeu();
		try {
			tdj.getButtonCard()[0].renouvellerEtatDeCarte(false, partie.getTrophee().getCarteTas().get(0));
			tdj.getButtonCard()[1].renouvellerEtatDeCarte(false, partie.getTrophee().getCarteTas().get(1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partie.addObserver(tdj);
		this.add(tdj);

		tour = new JLabel("Round " + partie.numeroRound);
		tour.setBounds(this.getWidth() * 780 / 1920, this.getHeight() * 20 / 1080, this.getWidth() * 330 / 1920,
				this.getHeight() * 200 / 1080);
		tour.setHorizontalAlignment(SwingConstants.CENTER);
		tour.setFont(new Font("Tahoma", Font.ITALIC, 19));
		tour.setForeground(new Color(0, 255, 255));
		this.add(tour);
		partie.addObserver(this);

		partie.getTrophee().addObserver(this.getTableDeJeu());
		new Controleur(partie, this);

	}

	/**
	 * create a JPanel for players
	 * 
	 * @param x dimensions
	 * @param y dimensions
	 * @return a JPanel for a player
	 */
	public PanelJoueur creerEspaceDeJoueur(int x, int y) {
		PanelJoueur j = new PanelJoueur();
		j.setBounds(x, y, this.getWidth() * 550 / 1920, this.getHeight() * 350 / 1080);
		j.setVisible(true);
		return j;
	}

	/**
	 * Create a TableDeJeu
	 * 
	 * @return a TableDeJeu
	 */
	public TableDeJeu creerTableDeJeu() {
		TableDeJeu t = new TableDeJeu();
		t.setBounds(this.getWidth() * 580 / 1920, this.getHeight() * 207 / 1080, this.getWidth() * 760 / 1920,
				this.getHeight() * 665 / 1080);
		t.setVisible(true);

		return t;
	}

	public ButtonCard[] getButtonTrophee() {
		return this.getTableDeJeu().getButtonCard();
	}

	/**
	 * This is the method that implements the Observer design pattern, it updates
	 * each time the round number changes.
	 */
	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Partie) {
			Partie partie1 = (Partie) o;
			this.tour.setText("Round " + partie1.getNumeroRound());
		}

	}

	public PanelJoueur[] getPanelJoueur() {
		return pj;
	}

	public TableDeJeu getTableDeJeu() {
		return tdj;
	}

	public JoueurPhys getJoueurReel() {
		return joueurPhys;
	}

}
