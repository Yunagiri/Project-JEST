package fr.utt.lo02.jest;

//The variable hauteur IS NOT VALUE! Value is influenced by the hauteur of the card when it's in the Jest
public class SuitCards extends Carte {

    private suits enseigne;
    public SuitCards(int hauteur, boolean faceCachee, condition condition, suits ensCondition, suits enseigne) {
    	super(hauteur, faceCachee, condition, ensCondition );
    	this.enseigne = enseigne;
    }

    public String montrer() {
    	StringBuffer sb = new StringBuffer();
    	if (!this.faceCachee) {
    		sb.append(super.toString());
    		sb.append("La carte est un ");
    		sb.append(this.hauteur);
    		sb.append(" de ");
    		sb.append(this.enseigne);
    		return sb.toString();
    	}
		return sb.append("La carte est face cachee").toString();

    }
}

