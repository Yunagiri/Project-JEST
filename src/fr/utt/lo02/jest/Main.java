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
    
    //This method allows a player to take a card from another player's offer
    public void prendreOffre(int posCarte, Tas autreTas) {
    	autreTas.ajouterCartes(this.listCarte.get(posCarte));
    	this.listCarte.remove(this.nombreDeCartes);
    	this.nombreDeCartes--;
    }
    
    
    //This method allows a player to make an offer, choosing a card to show face down and the other face up
    public void faireOffre(int posCarteFaceCachee) {
    	
    	//Change the faceCachee value of the card 
    	this.listCarte.get(posCarteFaceCachee).faceCachee = true;
    	
    	//Shows the newly made offer 
    	for (int i = 0; i < this.listCarte.size(); i++) {
    		this.listCarte.get(i).montrer();
    	}
    	
    }
    
    
    //This method allows one player to look at the offer of one of the other players
    public void regarderOffre(Main autreMain) {
    	
    	//Shows the offer of the other player
    	for (int i = 0; i < autreMain.listCarte.size(); i++) {
    		this.listCarte.get(i).montrer();
    	}
    	
    }
    
    
}
