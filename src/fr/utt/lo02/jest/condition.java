package fr.utt.lo02.jest;

import java.util.ArrayList;
import java.util.Random;
import java.util.EnumMap;
import java.util.List;

/* Condition of each card in the game will be defined by an enum class which will link each condition to a 
 * way to calculate to whom should the trophy card be awarded.
 */
public enum condition {
	HIGHEST, LOWEST, MAJORITY, JOKER, BEST, BEST_NOJOKER;

//public static void main(String[] args) {

	/*
	 * //I've come to the conclusion that the best type of Map is the EnumMap, to
	 * link 2 enums together EnumMap<condition,List<suits>> omega_condition = new
	 * EnumMap<condition, List<suits>>(condition.class);
	 * 
	 * //Since you can't have multiple values in 1 key I've decided to pair a key
	 * with a List (List doesn't work but ArrayList does...) List<suits> listSuits =
	 * new ArrayList<suits>(); for (suits enseigne : suits.values()) {
	 * listSuits.add(enseigne); } System.out.println(listSuits);
	 * 
	 * 
	 * //2 loops to pair up actions that can be paired with a suit and pair null
	 * with actions that doesn't need a suit for (condition act: condition.values())
	 * {
	 * 
	 * //These are the actions that doesn't need a suit to be done if (act !=
	 * condition.JOKER && act != condition.BEST_NOJOKER && act != condition.BEST) {
	 * 
	 * //Pair an action to win the card with a suit, the problem is to find how to
	 * choose a specific suit... omega_condition.put(act, listSuits); } else {
	 * omega_condition.put(act, null); }
	 * 
	 * } System.out.println(omega_condition.toString());
	 * System.out.println(omega_condition.get(HIGHEST).get(2));
	 */

//	SuitCards carte = new SuitCards(5, true, HIGHEST, suits.COEUR, suits.PIQUE );

	// }
	public static condition getRandomCondition() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
