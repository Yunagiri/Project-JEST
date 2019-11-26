package fr.utt.lo02.jest; 

public class Joker extends Carte {
	
	//The value of the Joker card depends on the amount of hearts the player possess in their hands 
	public Joker(Conditions condi) {
		super(0, true , condi);
	}
	
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append(super.montrer());
    		sb.append("Why so serious?");
    		return sb.toString();
    	}
		return super.montrer().toString();

    }
}
