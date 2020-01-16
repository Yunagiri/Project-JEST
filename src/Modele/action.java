package Modele;
import java.util.Random;

/**
 * <b>This enumeration stores the actions needed to get the trophy.</b>
 * <p>
 * There are 6 possible actions that a trophy can require:
 * <li>HIGHEST: the trophy is awarded to the player with the highest card value of a given suit in their jest.
 * <li>LOWEST: the trophy is awarded to the player with the lowest card value of a given suit in their jest.
 * <li>MAJORITY: the trophy is awarded to the player with the most cards of a given value in their jest.
 * <li>JOKER: the trophy is awarded to the player with the joker in their jest.
 * <li>BEST: the trophy is awarded to the player with the highest total jest value.
 * <li>BEST_NOJOKER: the trophy is awarded to the player with the highest total jest value, without joker.
 * 
 * @see suits, Carte
 * @author dinh_, tran_
 *
 */
public enum action {
	HIGHEST, LOWEST, MAJORITY, JOKER, BEST, BEST_NOJOKER;

}
