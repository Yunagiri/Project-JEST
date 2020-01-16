package Vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Modele.*;
/**
 * This class is used for creating the cards
 * @author dinh_,tran_
 * @see PanelJeu, PanelJoueur
 */
public class ButtonCard extends JButton {
	
	private static final long serialVersionUID = 1L;

	/**
	 * This is the constructor for the card's UI.
	 * Settings are defined.
	 */
	public ButtonCard() {
		setLayout(null);
		setSize(90, 119);

		JLabel jl = new JLabel("Carte");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setBounds(0, 0, this.getWidth(), this.getHeight());
		add(jl);
		this.setVisible(true);
	}
	
	/**
	 * This is used for the update method of the observers, once an action has been registered and the state of the observable has changed,
	 * the cards must also change.
	 * @param b the state of the card, face up or down
	 * @param carte the card that needs to be updated
	 * @throws IOException 
	 */
	public void renouvellerEtatDeCarte(boolean b, Carte carte) throws IOException {
		this.removeAll();

		if (b) {
			BufferedImage image = ImageIO.read(new File("src/image/dos.jpg"));
			ImageIcon icon = new ImageIcon(
					image.getScaledInstance(this.getWidth(), this.getHeight(), image.SCALE_SMOOTH));
			this.setIcon(icon);
		} else {
			BufferedImage image = ImageIO.read(new File("src/image/" + carte.getValeur() + ".png"));
			ImageIcon icon = new ImageIcon(
					image.getScaledInstance(this.getWidth(), this.getHeight(), image.SCALE_SMOOTH));
			this.setIcon(icon);
		}
		this.repaint();
		this.validate();
	}
}