package fr.utt.lo02.jest;

public class Main extends Tas {
    public Joueur joueur;
    
    public Main() {
    	super();
    }
    
    //Will be used when calculating the points for the Jest
    public boolean PossederJoker() {
    	for (int i = 0; i < this.listCarte.size(); i++) {
    		if (this.listCarte.get(i) instanceof Joker) {
    			return true;
    		}
    	}
    	return false;
    }

    //This method allows this player to take a card from another player's offer
    public void prendreOffre(int posCarte, Tas autreTas) {
    	
    	this.ajouterCartes(this.listCarte.get(posCarte));
    	autreTas.listCarte.remove(posCarte);
    	autreTas.nombreDeCartes--;
    }
    
    
    //This method allows a player to make an offer, choosing a card to show face down and the other face up
    public void faireOffre(int posCarteFaceCachee) {
    	
    	//Change the faceCachee value of the card 
    	this.listCarte.get(posCarteFaceCachee).faceCachee = true;
    	//Shows the newly made offer, can also offer insight on the number of cards.
    	for (int i = 0; i < this.listCarte.size(); i++) {
    		this.listCarte.get(i).montrer();
    	}
    	
    }
    
    //This method allows a player to give the card chosen by their opponent, an overcharged version of the Tas' distribuer() method
    public void distribuer(int posCarte, Main autreMain) {
    	autreMain.ajouterCartes(this.listCarte.get(posCarte));
    	this.listCarte.remove(posCarte);
    	this.nombreDeCartes--;
    	
    }
    
    //This method allows one player to look at the offer of one of the other players
    public void regarderOffre(Main autreMain) {
    	//Shows the offer of the other player
    	for (int i = 0; i < autreMain.listCarte.size(); i++) {
    		this.listCarte.get(i).montrer();
    	}
    	
    }
    
    
}
