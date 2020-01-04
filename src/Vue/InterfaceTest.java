package Vue;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InterfaceTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceTest window = new InterfaceTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public InterfaceTest() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.setLocation(0, 0);
		btnNewButton.setLayout(null);
		btnNewButton.setSize(81,119);
		btnNewButton.removeAll();
		BufferedImage image = ImageIO.read(new File("image/14.png"));
		ImageIcon icon = new ImageIcon(image.getScaledInstance(btnNewButton.getWidth(),btnNewButton.getHeight(), image.SCALE_SMOOTH));
btnNewButton.setIcon(icon);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.repaint();
		btnNewButton.validate();
	}
		
}
