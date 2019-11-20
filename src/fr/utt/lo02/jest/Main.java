package fr.utt.lo02.jest;

public class Main extends Tas {
    public Joueur joueur;
    
    public Main() {
    	super();
    }
    
    public boolean hasJoker() {
    	for (int i = 0; i < this.listCarte.size(); i++) {
    		if (this.listCarte.get(i) instanceof Joker) {
    			return true;
    		}
    	}
    	return false;
    }
}
