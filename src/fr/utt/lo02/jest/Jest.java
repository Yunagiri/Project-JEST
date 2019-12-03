package fr.utt.lo02.jest;

public class Jest extends Tas implements Score {
    private int valeur;

    public Joueur joueur;
    
    public Jest() {
    	super();
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    
    
	public boolean hasJoker() {
		for (Carte c : this.listCarte) {
			if (c instanceof Joker) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void accepter(VisitorDeJest v) {
		// TODO Auto-generated method stub
		v.compter(this);
	}

	


    
}
