package Vue;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modele.CompteurDeScore1;
import Modele.Joueur;
import Modele.JoueurVirt;
import Modele.Partie;
import Modele.VisitorDeJest;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InterfaceTest {

	private JestInterface jinter;
	private FenetreParamettre fp;
	
	private Partie partie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceTest window = new InterfaceTest();
					window.fp.setVisible(true);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public InterfaceTest() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		
		
		partie= new Partie();
		
//		partie.pause();
		this.fp= new FenetreParamettre(partie);
		
		
//			System.out.println(partie.joueurs.get(0).getMain().listCarte.get(0).montrer());
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fp.getContentPane().setLayout(null);
	}
		
}
