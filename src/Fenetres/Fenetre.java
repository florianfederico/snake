package Fenetres;

import javax.swing.JFrame;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Fenetre est la classe correspondant à la JFrame du projet.
 *
 */

public class Fenetre extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fenetre() {
		this.setTitle("Snake");
		this.setSize(640, 510);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
}
