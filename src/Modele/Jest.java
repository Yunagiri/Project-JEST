package Modele;
/**
 * This class represents the Jest in the game. It implements the Score interface which allows it to be visited.
 * @author tran_ ,dinh_
 *
 */
public class Jest extends Tas implements Score {
	/**
	 * This is the value of the Jest
	 * @see Jest#getValeur()
	 */
    private int valeur;
    /**
     * This is the constructor of the class, it calls the superclass constructor.
     */

    public Jest() {
    	super();
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    
    /**
     * This method indicates if a Joker card is present in this jest.
     * @return true if there is a joker, else return false
     */
	public boolean hasJoker() {
		for (Carte c : this.listCarte) {
			if (c instanceof Joker) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method implements the Visitor design pattern, allow for visits.
	 */
	@Override

	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

	


    
}
