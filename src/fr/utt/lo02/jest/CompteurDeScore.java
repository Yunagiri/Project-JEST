package fr.utt.lo02.jest;

public class CompteurDeScore implements VisitorDeJest{

	private Regle regle;
	
	public CompteurDeScore(int variante) {
		if (variante == 1) {
			this.regle = new Regle1();
		}
		else {
			this.regle = new Regle2();
		}
	}
	
	@Override
	public void compter(Jest j) {
		// TODO Auto-generated method stub
		System.out.println("En visite");
	}

}
