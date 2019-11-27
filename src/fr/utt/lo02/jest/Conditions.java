package fr.utt.lo02.jest;

public class Conditions {

	private suits enseigne;
	private action cond;
	private boolean hasEnseigne;
	
	public Conditions(action cond , suits enseigne) {
		this.enseigne = enseigne;
		this.cond = cond;
		this.hasEnseigne = true;
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
			
			return sb.toString();
		}
	}
	
	
}
