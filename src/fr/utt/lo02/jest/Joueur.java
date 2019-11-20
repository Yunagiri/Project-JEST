package fr.utt.lo02.jest;

public class Joueur {
	
    protected String nom;
    protected String prenom;
    
    private Main main;
    private Jest jest;
    
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

    public void regarderMain() {
    }
    
    public void montrerCarte() {
    }
    //add the card into the jest
    public void mettreJest(Carte carte) {
    	this.jest.ajouterCartes(carte);
    }
    //give card to another desk
    public void donnerCarte(Carte carte, Tas autreTas) {
    	this.main.distribuer(carte, autreTas);
    }
    // finish round
    public void finTour() {
    	System.out.println("J'ai fini mon tour");
    }

}
