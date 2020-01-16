package VueText;

import java.util.InputMismatchException;
import java.util.Scanner;

import Modele.*;

public class VueText extends Thread implements Observer {

	private Partie partie;
	private int[] cartesChoisies = new int[2];
	private Joueur joueurChoisit;
	private boolean continu = true;
	private String reponse;

	private Thread t;

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
				System.out.println("-----------");
				try {
					partie.pause();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("lancerRound");
				partie.lancerRound();
				partie.continu();
				System.out.println("----------");
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