

package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


import Modele.*;
import Controleur.*;

public class PanelJeu extends JPanel implements Observer{
	
	private Partie partie;
	private JoueurVirt JoueurVirt;
	private PanelJoueur[] pj;
	private TableDeJeu tdj;
	private JLabel tour; 
	
	public PanelJeu(Partie partie,JoueurVirt jv) {
		this.JoueurVirt=jv;
		setLayout(null);
		this.setBackground(new Color(128, 0, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		this.setSize(960,540);
		pj=new PanelJoueur[partie.joueurs.size()];
		if(partie.joueurs.size()==2) {
			pj[0]=this.creerEspaceDeJoueur(5, this.getHeight()*720/1080);
			pj[1]=this.creerEspaceDeJoueur(this.getWidth()*1365/1920, 5);
		}
		else if(partie.joueurs.size()==3) {
			pj[0]=this.creerEspaceDeJoueur(5, this.getHeight()*720/1080);
			pj[1]=this.creerEspaceDeJoueur(this.getWidth()*1365/1920, 5);	
			pj[2]=this.creerEspaceDeJoueur(this.getWidth()*1365/1920,this.getHeight()*720/1080);
		}
		else if(partie.joueurs.size()==4) {
			pj[0]=this.creerEspaceDeJoueur(5, this.getHeight()*720/1080);
			pj[1]=this.creerEspaceDeJoueur(5,5);	
			pj[2]=this.creerEspaceDeJoueur(this.getWidth()*1365/1920, 5);
			pj[3]=this.creerEspaceDeJoueur(this.getWidth()*1365/1920,this.getHeight()*720/1080);
		}
		for(int i=0;i<partie.joueurs.size();i++) {
			if(pj[i]!=null) {
				partie.joueurs.get(i).addObserver(pj[i]);
				pj[i].setVisible(true);
				this.add(pj[i]);
			}
		}
		pj[jv.getId()-1].getRegarderJest().setVisible(true);
		pj[jv.getId()-1].getRegarderCarte().setVisible(true);
		this.tdj = this.creerTableDeJeu();
		partie.addObserver(tdj);;
		this.add(tdj);
		
		tour = new JLabel("TOUR");
		tour.setBounds(this.getWidth()*780/1920,this.getHeight()*20/1080,this.getWidth()*330/1920,this.getHeight()*200/1080);
		tour.setHorizontalAlignment(SwingConstants.CENTER);
		tour.setFont(new Font("Tahoma", Font.BOLD, 19));
		tour.setForeground(new Color(255, 255, 255));
		this.add(tour);
		partie.getTour().addObserver(this);
		partie.addObserver(this);
		for(Trick t: jeu.getTrick()) {
			t.addObserver(tdj);
		}
		
		new ControleurJoueur(jv, pj[jv.getId()-1]);
		new ControleurTable(jeu,tdj);
		
		
	}
	public PanelJoueur creerEspaceDeJoueur(int x, int y) {
		PanelJoueur j = new PanelJoueur();
		j.setBounds(x,y,this.getWidth()*550/1920,this.getHeight()*350/1080);
		j.setVisible(true);
		return j;
	}
	public TableDeJeu creerTableDeJeu() {
		TableDeJeu t = new TableDeJeu();
		t.setBounds(this.getWidth()*580/1920,this.getHeight()*207/1080,this.getWidth()*760/1920,this.getHeight()*665/1080);
		t.setVisible(true);
		return t;
	}
	public JButton getButtonProp(){
		return this.getTableDeJeu().getButtonProp();
	}
	@Override
	public void update(Observable o, Object arg1) {
		if(o instanceof Tour) {
			tour.setText("Tour de joueur "+ ((Tour)o).getTour());
		}
		if(o instanceof Jeu) {
			if(JoueurVirt.getId()==((Jeu)o).getTour().getTour() && ((Jeu)o).getDoIt()) {
				this.getTableDeJeu().getButtonTrick().setEnabled(true);
			}
		}
		
	}
	public PanelJoueur[] getPanelJoueurReel() {
		return pj;
	}
	public TableDeJeu getTableDeJeu() {
		return tdj;
	}
	public JoueurVirt getJoueurReel() {
		return JoueurVirt;
	}
	
}
