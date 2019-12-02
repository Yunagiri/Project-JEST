package fr.utt.lo02.jest; 

public class Joker extends Carte {
	
	//There's only 1 joker per game and we know his condition, might aswell make it static.
	private static Conditions jokeCond = new Conditions(action.BEST);
	
	
	public Joker() {
		super(0, true, jokeCond);
	}
	
	
    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append("Why so serious? ");
    		sb.append(super.montrer());

    		return sb.toString();
    	}
		return super.montrer().toString();

    }
}
