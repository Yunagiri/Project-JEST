package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
// import java.util.Observable;
//import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Modele.Joueur;
import Modele.Observable;
import Modele.Observer;
import Modele.Carte;

public class PanelJoueur extends JPanel implements Observer{
	private ButtonCard[] cartes=new ButtonCard[2];
	private JLabel nomJoueur;
	private JButton regarderJest;
	private JButton regarderCarte;
	private JLabel point;
	
	public PanelJoueur() {
		setBackground(Color.BLUE);
		Border line = BorderFactory.createLineBorder(Color.WHITE);
		
		setBorder(line);
		setVisible(true);
		this.setSize(290,175);
		setLayout(null);
		
		cartes[0]= new ButtonCard();
		cartes[0].setBounds(this.getWidth()*370/600,this.getHeight()*120/400,this.getWidth()*190/600,this.getHeight()*272/400);
		this.add(cartes[0]);
		
		cartes[1]= new ButtonCard();
		cartes[1].setSize(this.getWidth()*190/600,this.getHeight()*272/400);
		cartes[1].setLocation(this.getWidth()*160/600,this.getHeight()*120/400);
		this.add(cartes[1]);
		
		regarderJest= new JButton("Jest");
		regarderJest.setHorizontalAlignment(SwingConstants.CENTER);
		regarderJest.setBounds(this.getWidth()*360/600,this.getHeight()*50/400,this.getWidth()*190/600,this.getHeight()*50/400);
		regarderJest.setVisible(false);
		regarderJest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		this.add(regarderJest);
		
		regarderCarte = new JButton("Carte");
		regarderCarte.setHorizontalAlignment(SwingConstants.CENTER);
		regarderCarte.setBounds(this.getWidth()*140/600,this.getHeight()*50/400,this.getWidth()*190/600,this.getHeight()*50/400);
		regarderCarte.setVisible(false);
		regarderCarte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		this.add(regarderCarte);
		
		nomJoueur= new JLabel();
		nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		nomJoueur.setForeground(Color.WHITE);
		nomJoueur.setBounds(this.getWidth()*20/600,this.getHeight()*150/400,this.getWidth()*130/600,this.getWidth()*50/600);
		this.add(nomJoueur);
		/*
		imageJoueur= new JLabel("Jouer");
		imageJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		imageJoueur.setBounds(this.getWidth()*20/600,this.getHeight()*180/400,this.getWidth()*130/600,this.getWidth()*130/600);
		this.add(imageJoueur);
		*/
		point = new JLabel("point");
		point.setHorizontalAlignment(SwingConstants.CENTER);
		point.setForeground(Color.GREEN);
		point.setBounds(this.getWidth()*20/600,this.getHeight()*100/400,this.getWidth()*130/600,this.getWidth()*50/600);;
		this.add(point);
		this.setEnableCarte(true);
	}
	
	public void renouvellerImageCarte(Joueur joueur) throws IOException {
		for(int i=0;i<joueur.getMain().listCarte.size();i++) {
			if(joueur.getMain().listCarte.get(i)!=null) {
				this.cartes[i].renouvellerEtatDeCarte(joueur.getMain().listCarte.get(i).getEtat(),joueur.getMain().listCarte.get(i));
			}
		}
		if(joueur.getMain().listCarte.size()<2) {
			this.cartes[1].setVisible(false);
		}
		else {
			this.cartes[1].setVisible(true);
		}
	}
	public void setNomJoueur(Joueur joueur) {
		nomJoueur.setText(joueur.getPrenom());
	}
	public ButtonCard[] getPanelCarte() {
		return cartes;
	}
	public JButton getRegarderJest() {
		return regarderJest;
	}
	public JButton getRegarderCarte() {
		return regarderCarte;
	}
	public void setEnableCarte(boolean b) {
		cartes[0].setEnabled(b);
		cartes[1].setEnabled(b);
	}
	
	public void update(Observable o, Object a) {
		if(o instanceof Joueur) {
			Joueur joueur = (Joueur) o;
			try {
				this.renouvellerImageCarte(joueur);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nomJoueur.setText(joueur.getPrenom());
			point.setText("Point: "+joueur.getScore());
		}
	}
	
}