
package fr.utt.lo02.jest;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int rounds;

    public List<Pioche> pioche = new ArrayList<Pioche> ();

    public List<Trophee> trophée = new ArrayList<Trophee> ();

    public List<Joueur> joueur = new ArrayList<Joueur> ();

    public Visitor visitor;
    
    // ??? What's finished? the round? the game? myself? I fucking wish
    public boolean estTerminee() {
    	//Assuming that it's the player round finishing
    	
    }

    public void donnerTour() {
    }

    public void verifierCondition() {
    }

    public void afficherScore() {
    	
    }

    public void terminer() {
    }
    
    public static void main(String[] args) {
    	
    }

}
