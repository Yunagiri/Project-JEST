package Vue;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Modele.*;


public class ButtonCard extends JButton {
	public ButtonCard() {
		setLayout(null);
		setSize(90,119);
		
		JLabel jl = new JLabel("Carte");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setBounds(0,0,this.getWidth(),this.getHeight());
		add(jl);
		this.setVisible(true);
	}
	public void renouvellerEtatDeCarte(boolean b, Carte carte) throws IOException {
		this.removeAll();
//		b=false; // nho doi 
		if(b) {
			BufferedImage image = ImageIO.read(new File("src/image/dos.jpg"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(this.getWidth(),this.getHeight(), image.SCALE_SMOOTH));
			this.setIcon(icon);
		}
		else {
			BufferedImage image = ImageIO.read(new File("src/image/"+ carte.getValeur()+".png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(this.getWidth(),this.getHeight(), image.SCALE_SMOOTH));
			this.setIcon(icon);
		}
		this.repaint();
		this.validate();
	}
}