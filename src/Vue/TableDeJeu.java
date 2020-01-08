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
		this.setBackground(new Color(255, 255, 0));
		this.setSize(800,700);
		this.setLocation(0,0);
		this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		
		trophee[0]= new ButtonCard();
		trophee[0].setSize(90,119);
		trophee[0].setLocation(230,90);
		this.add(trophee[0]);
		
		trophee[1]= new ButtonCard();
		trophee[1].setSize(90,119);
		trophee[1].setLocation(60,90);
		this.add(trophee[1]);
		

	}
	public ButtonCard[] getButtonCard() {
		return trophee;
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		if(o instanceof Trophee) {
//			if(arg1.equals(1)||arg1.equals(0)) {
				trophee[(Integer) arg1].setVisible(false);
//			}
		
		
		}
	}
}