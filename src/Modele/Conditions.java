package fr.utt.lo02.jest;

public class Conditions {

	protected suits enseigne;
	protected action cond;
	protected boolean hasEnseigne;
	protected int hauteur;
	
	public Conditions(action cond , suits enseigne) {
		this.enseigne = enseigne;
		this.cond = cond;
		this.hasEnseigne = true;
	}
	public Conditions(action cond, int hauteur) {
		this.cond = cond;
		this.hasEnseigne = false;
		this.hauteur = hauteur;
	}
	
	public Conditions(action cond) {
		this.cond = cond;
		this.hasEnseigne = false;
	}
	public String montrer() {
		StringBuffer sb = new StringBuffer();
		if (this.hasEnseigne == true) {
			sb.append(". La condition pour gagner est: ");
			sb.append(this.cond);
			sb.append(" ");
			sb.append(this.enseigne);
			
			
			return sb.toString();
		}
		
		else {
			sb.append(". La condition pour gagner est: ");
			sb.append(this.cond);
			if ( this.hauteur !=0) {
			sb.append(this.hauteur);
			}
			return sb.toString();
		}
	}
	
	
}
