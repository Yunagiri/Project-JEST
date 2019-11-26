package fr.utt.lo02.jest;

import java.util.Random;

public enum suits {
	TREFLE,
	CARREAU, 
	COEUR, 
	PIQUE;
	
	
	public static suits getRandomSuits() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
