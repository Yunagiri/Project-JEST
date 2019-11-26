package fr.utt.lo02.jest; 

public class Joker extends Carte {
	
	//There's only 1 joker per game and we know his condition, might aswell make it static.
	private static Conditions jokeCond = new Conditions(condition.BEST);
	
	
	public Joker() {
		super(0, true, jokeCond);
	}
	
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append(super.montrer());
    		sb.append(", Why so serious?");
    		return sb.toString();
    	}
		return super.montrer().toString();

    }
}
