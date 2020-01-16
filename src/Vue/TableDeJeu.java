package Vue;

import Modele.Trophee;
import Modele.Observer;
import Modele.Observable;

import java.awt.Color;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

public class TableDeJeu extends JPanel implements Observer {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private ButtonCard[] trophee = new ButtonCard[2];

	public TableDeJeu() {
		setLayout(null);
		this.setBackground(new Color(255, 255, 0));
		this.setSize(800, 700);
		this.setLocation(0, 0);
		this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		trophee[0] = new ButtonCard();
		trophee[0].setSize(90, 119);
		trophee[0].setLocation(230, 90);
		this.add(trophee[0]);

		trophee[1] = new ButtonCard();
		trophee[1].setSize(90, 119);
		trophee[1].setLocation(60, 90);
		this.add(trophee[1]);

	}

	public ButtonCard[] getButtonCard() {
		return trophee;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Trophee) {
			trophee[(Integer) arg1].setVisible(false);

		}
	}
}