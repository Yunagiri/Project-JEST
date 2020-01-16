package Modele;
/**
 * This is the Observer interface implementing the Observer design pattern.
 * @author dinh_
 * @see Observable, Vue.PanelJeu,Vue.TableDeJeu,Vue.PanelJoueur,VueText.VueText
 */
public interface Observer {
	/**
	 * This is the update method that all classes that implements Observer has to implement.
	 * @param a the Observable that changed
	 * @param o	the object of change
	 */
	public void update (Observable a, Object o);
}
