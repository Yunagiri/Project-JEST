package Vue;

import Modele.Partie;
import Modele.Carte;
import Modele.Joueur;
import Modele.Trophee;
import Modele.Observer;
import Modele.Observable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
 

public class TableDeJeu extends JPanel implements Observer{
	
//	private JButton prop;
	//private JButton trick;
	//private ButtonTrick trickRetourne;
	private ButtonCard[] trophee=new ButtonCard[2];
	private JLabel t;
	
	public TableDeJeu() {
		setLayout(null);
		this.setBackground(new Color(0, 128, 0));
		this.setSize(800,700);
		this.setLocation(0,0);
		this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		trophee[0]= new ButtonCard();
		trophee[0].setBounds(this.getWidth()*370/600,this.getHeight()*120/400,this.getWidth()*190/600,this.getHeight()*272/400);
		this.add(trophee[0]);
		
		trophee[1]= new ButtonCard();
		trophee[1].setSize(this.getWidth()*190/600,this.getHeight()*272/400);
		trophee[1].setLocation(this.getWidth()*160/600,this.getHeight()*120/400);
		this.add(trophee[1]);
		

	}
	@Override
	public void update(Observable o, Object arg1) {
		if(o instanceof Trick) {
			this.getButtonTrick().setEnabled(false);
			this.afficherTrickRetourne((Trick)o);
		}
		if(o instanceof Jeu) {
			t.setText("Trick: "+((Jeu) o).getTrick().size());
			this.afficherTrickRetourne(((Jeu)o).prendreTrick());
		}
		this.repaint();
		this.validate();
	}
}