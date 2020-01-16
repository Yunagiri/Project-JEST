package Vue;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import Modele.Partie;

/**
 * This class is used to launch the GUI
 * @author dinh_
 *
 */
public class InterfaceTest {

	private FenetreParamettre fp;

	private Partie partie;

	/**
	 * Start the thread dedicated to the GUI
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceTest window = new InterfaceTest();
					window.fp.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public InterfaceTest() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void initialize() throws IOException, InterruptedException {

		partie = new Partie();
		this.fp = new FenetreParamettre(partie);

		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fp.getContentPane().setLayout(null);
	}

}
