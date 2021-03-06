/**
 * This package contains what makes playing on both the GUI and the console possible.
 */
package VueText;

import java.util.InputMismatchException;
import java.util.Scanner;

import Modele.*;

/**
 * This class allows for both console and GUI interactions, not at the same time
 * but each round.
 * 
 * @author dinh_,tran_
 */
public class VueText extends Thread implements Observer {
	
	private Partie partie;
	private boolean continu = true;

	private Thread t;

	/**
	 * This constructor creates a new Thread to run in and implements itself as an
	 * observer
	 * 
	 * @param partie the game currently being played
	 */
	public VueText(Partie partie) {
		this.partie = partie;
		partie.addObserver(this);
		for (Joueur joueur : partie.getListeJoueurs()) {
			joueur.addObserver(this);
		}
		t = new Thread(this);
		partie.addObserver(this);
	}

	@Override
	public void update(Observable o, Object a) {
		if (o instanceof Partie) {
			if (a.equals("terminer")) {
				continu = false;
			}

		}
		if (o instanceof Joueur) {

		}

	}

	@Override
	/**
	 * The run method of the thread, it pauses itself if no players selects the
	 * console option and executes lancerRound() if called.
	 * 
	 * @see Partie#lancerRound()
	 */
	public void run() {
		while (continu) {
			try {
				partie.pause();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("vous voulez jouer sur console?");
			Scanner sc = new Scanner(System.in);
			String st = sc.nextLine();
			if (st.equals("oui")) {
				partie.console = true;
			}
			if (partie.console) {
				partie.faireOffreAll();
				partie.continu();
				try {
					partie.pause();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				partie.lancerRound();
				partie.continu();
			}
		}
		System.out.println("console terminee");

	}

	public String saisirString() {
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		return st;
	}

	public int saisirInt() {
		Scanner sc = new Scanner(System.in);
		int st = 0;
		try {
			st = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Erreur de l'entree de paramettre!!! Resaisir!!!");
		}
		return st;
	}

}