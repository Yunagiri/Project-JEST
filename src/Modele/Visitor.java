package Modele;
/**
 * This interface implements the Visitor design pattern 
 * @author dinh_, tran_
 *
 */
public interface Visitor {
	/**
	 * This method allows a counter to visit a jest and calculate its score
	 * @param j the jest to be visited	
	 * @return the score of the visited jest
	 */
	public int visit(Jest j);
}
