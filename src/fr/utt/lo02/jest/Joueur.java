package fr.utt.lo02.jest;

public class Joueur {
	
    protected String nom;
    protected String prenom;
    protected boolean estEnTour;
    
    protected Main main;
    protected Jest jest;
    
    public Joueur( String nom, String prenom) {
    	this.nom = nom;
    	this.prenom = prenom;
    }
    public Joueur() {
    	
    }
    // take the card from another desk
    public void prendreCartes(Carte carte) {
    	this.main.ajouterCartes(carte);
    }

    
    //This method allow a player to look at the cards in their hand even if it's face down.
    public void regarderMain() {
    }
    
    public void montrerCarte() {
    }
    
    //add the card into the jest
    public void mettreJest(Carte carte) {
    	this.jest.ajouterCartes(carte);
    }
    
    //give card to another desk, 
    //parameter posCarte indicates the position of the card 
    // and the autreTas indicates where to take it from.
    public void donnerCarte(int posCarte, Main autreMain) {
    	this.main.distribuer(posCarte, autreMain);
    }
    // finish round
    public void finTour() {
    	System.out.println("J'ai fini mon tour");
    }

}
