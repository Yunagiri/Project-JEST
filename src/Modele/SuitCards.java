package fr.utt.lo02.jest;

//The variable hauteur IS NOT VALUE! Value is influenced by the hauteur of the card when it's in the Jest
public class SuitCards extends Carte {

	public SuitCards(int hauteur, boolean faceCachee, Conditions cond, suits enseigne) {
		super(hauteur, faceCachee, cond, enseigne);
	}
	
	
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (!this.faceCachee) {
			sb.append("La carte est un ");
			sb.append(this.hauteur);
			sb.append(" de ");
			sb.append(this.enseigne);
			sb.append(super.montrer());
			return sb.toString();
		}
		
		//return sb.append("La carte est face cachee").toString();
	}
}
