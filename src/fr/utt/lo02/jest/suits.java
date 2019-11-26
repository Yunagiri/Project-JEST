package fr.utt.lo02.jest;

import java.util.Random;

public enum suits {

	TREFLE(3),
	CARREAU(2), 
	COEUR(1), 
	PIQUE(4);
	private final int valeur;
	
	private suits (int i) {
		this.valeur = i;
	}
	public int getValeur() {
		return this.valeur;
	}
	
	public static suits getRandomSuits() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
		
	}
}
	
