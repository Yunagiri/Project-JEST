package Vue;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Modele.Carte;
import Modele.Jest;

/**
 * This class is the basis for the GUI, it is the window.
 * 
 * @author dinh_,tran_ 
 * see InterfaceTest,JestInterface,PanelJeu,PanelJoueur,TableDeJeu
 */
public class FenetreJest extends JFrame {

	/**
	 * The attributes are the buttons and labels, aswell as the player's jest and
	 * the counter.
	 */
	private static final long serialVersionUID = 1L;
	private Jest jests;
	private JLabel image;
	private JButton b1;
	private JButton b2;
	private int compteur;

	/**
	 * This is the constructor, it initialises the attributes of the GUI
	 * 
	 * @param jests The jest of the player
	 */
	public FenetreJest(Jest jests) {
		compteur = 0;
		this.jests = jests;
		this.setLocation(0, 0);
		this.setSize(500, 350);
		this.setLayout(null);
		this.setBackground(new Color(0, 128, 0));
		this.setResizable(false);
		this.setVisible(false);

		b1 = new JButton("<");
		b1.setHorizontalAlignment(SwingConstants.CENTER);
		b1.setBounds(25, 160, 50, 30);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent o) {
				compteur--;
				dessinerJests();
				if (compteur == 0) {
					b1.setEnabled(false);
				}
				if (b2.isEnabled() == false) {
					b2.setEnabled(true);
				}
			}
		});
		b1.setEnabled(false);
		this.add(b1);

		b2 = new JButton(">");
		b2.setHorizontalAlignment(SwingConstants.CENTER);
		b2.setBounds(445, 160, 50, 30);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent o) {
				compteur++;
				dessinerJests();
				if (compteur == FenetreJest.this.jests.getCarteTas().size() - 1) {
					b2.setEnabled(false);
				}
				if (b1.isEnabled() == false) {
					b1.setEnabled(true);
				}
			}
		});
		b2.setEnabled(false);
		this.add(b2);

		image = new JLabel();
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		image.setBounds(80, 45, 90, 119);
		this.add(image);
		this.dessinerJests();
	}

	/**
	 * This method allows cards to get a face up and face down side
	 */
	public void dessinerJests() {
		this.remove(image);
		if (compteur < jests.getCarteTas().size()) {
			Carte carte = jests.getCarteTas().get(compteur);
			try {
				BufferedImage image;
				image = ImageIO.read(new File("src/image/" + carte.getValeur() + ".png"));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(90, 119, image.SCALE_SMOOTH));
				this.image.setIcon(icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image.setHorizontalAlignment(SwingConstants.CENTER);
			image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			image.setBounds(80, 45, 90, 119);
			this.add(image);
			this.repaint();
			this.validate();
		}
		if (jests.getCarteTas().size() - compteur > 1) {
			b2.setEnabled(true);
		}
	}

	public void resetCompteur() {
		compteur = 0;
	}

}