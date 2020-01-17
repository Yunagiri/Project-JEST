/**
 * This package is the core of the application
 */
package Modele;

/**
 * <b>This enumeration stores the actions needed to get the trophy.</b>
 * <p>
 * There are 6 possible actions that a trophy can require:
 * HIGHEST: the trophy is awarded to the player with the highest card value
 * of a given suit in their jest.
 * LOWEST: the trophy is awarded to the player with the lowest card value of
 * a given suit in their jest.
 * MAJORITY: the trophy is awarded to the player with the most cards of a
 * given value in their jest.
 * JOKER: the trophy is awarded to the player with the joker in their jest.
 * BEST: the trophy is awarded to the player with the highest total jest
 * value.
 * BEST_NOJOKER: the trophy is awarded to the player with the highest total
 * jest value, without joker.
 * </p>
 * @see suits
 * @author dinh_, tran_
 *
 */
public enum action {
	HIGHEST, LOWEST, MAJORITY, JOKER, BEST, BEST_NOJOKER;

}
