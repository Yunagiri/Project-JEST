package fr.utt.lo02.jest;

public class Trophee extends Tas {
	public Trophee() {
		super();
	}
	
	/*Award the trophy card to the player fulfilling the condition of the trophy card
	* posTrophee indicates which card to give, and jestGagnant indicates who to give to
	 */
	public void donnerTrophee(int posTrophee, Jest jestGagnant) {
		jestGagnant.listCarte.add(this.listCarte.get(posTrophee));
		this.listCarte.clear();
		this.nombreDeCartes--;
	}
}
