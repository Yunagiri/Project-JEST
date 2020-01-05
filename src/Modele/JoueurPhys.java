package Modele;

public class JoueurPhys extends Joueur {
	public JoueurPhys(String prenom){
		this.prenom = prenom;
		this.main = new Main();
		this.jest = new Jest();
		this.estEnTour = false;
		this.Score = 0;
		Joueur.numero = numero +1;
		this.id = numero;
		
	}
}
