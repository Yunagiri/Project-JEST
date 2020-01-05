package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Modele.JoueurVirt;
import Modele.Partie;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetreParamettre {

	private JFrame frame;
	private Partie partie;
	
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JCheckBox chckbxDifficile;
	private JCheckBox chckbxFacile;
	private JButton btnNewButton;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partie partie = new Partie();
					FenetreParamettre window = new FenetreParamettre(partie);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreParamettre(Partie partie) {
		this.partie=partie;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);
		
		comboBox = new JComboBox();
		comboBox.setBounds(170, 41, 43, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4" }));
		frame.getContentPane().add(comboBox);
		
		JLabel lblCombienDeJoueurs = new JLabel("Nombre de joueurs reels:");
		lblCombienDeJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCombienDeJoueurs.setBounds(12, 96, 189, 40);
		frame.getContentPane().add(lblCombienDeJoueurs);
		
		JLabel lblNombredejoueur = new JLabel("Nombre de joueurs:");
		lblNombredejoueur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombredejoueur.setBounds(12, 43, 165, 16);
		frame.getContentPane().add(lblNombredejoueur);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1","2", "3","4"}));
		comboBox_1.setBounds(213, 107, 43, 22);
		frame.getContentPane().add(comboBox_1);
		
		chckbxFacile = new JCheckBox("Facile");
		chckbxFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxDifficile.setSelected(false);
			}
		});
		chckbxFacile.setBounds(157, 175, 75, 25);
		frame.getContentPane().add(chckbxFacile);
		
		chckbxDifficile = new JCheckBox("Difficile");
		chckbxDifficile.setBounds(276, 175, 113, 25);
		chckbxDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxFacile.setSelected(false);
			}
		});
		frame.getContentPane().add(chckbxDifficile);
		
		JLabel lblNiveau = new JLabel("Niveau de difficulte:");
		lblNiveau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNiveau.setBounds(21, 178, 128, 16);
		frame.getContentPane().add(lblNiveau);
		
		btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int a =Integer.parseInt(comboBox.getSelectedItem().toString());
				int b =Integer.parseInt(comboBox_1.getSelectedItem().toString());
				if((chckbxFacile.isSelected() || chckbxDifficile.isSelected())& a>=b) {
					partie.setNbJoueurs(Integer.parseInt(comboBox.getSelectedItem().toString()));
					partie.setNbJoueursPhysic(Integer.parseInt(comboBox_1.getSelectedItem().toString()));
					if(chckbxFacile.isSelected()) {
						for ( int i =0; i < (a-b); i++) {
							JoueurVirt j = new JoueurVirt(1,"a");
						}
					}
					else {
						for ( int i =0; i < (a-b); i++) {
							JoueurVirt j = new JoueurVirt(2,"a");
						}
					}
					frame.setVisible(false);
					
//					Thread t = new Thread() {
//						@Override
//						public void run() {		
//							AjoutCarte c = new AjoutCarte(jeu);
//							c.getJFrame().setVisible(true);
//						//	jeu.lancerJeu();
//						}
//					};
//					t.start();
				}
			}
		});
		btnNewButton.setBounds(167, 215, 97, 25);
		frame.getContentPane().add(btnNewButton);
		 
	}
	public void setVisible() {
		frame.setVisible(true);
	}
	public JButton getButton() {
		return btnNewButton;
	}
}