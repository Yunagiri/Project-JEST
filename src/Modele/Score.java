package Modele;

/**
 * This interface implements the Visitor design pattern, used by visitable
 * classes
 * 
 * @author dinh_, tran_
 * @see Jest
 */
public interface Score {
	/**
	 * This method allows an instance of score to be visited, in this case the jest
	 * 
	 * @param v Can be CompteurDeScore1 or CompteurDeScore2
	 * @see CompteurDeScore1
	 */
	void accept(Visitor v);
}
