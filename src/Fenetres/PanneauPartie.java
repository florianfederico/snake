package Fenetres;

import javax.swing.JPanel;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe contient le JPanel Partie.
 * Elle sert à lancer une nouvelle partie avec un Thread sur Partie.
 * Ceci dans le but de pouvoir actualiser régulièrement l'affichage de la partie.
 *
 * @see Partie
 */

public class PanneauPartie extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partie partie;
	
	public PanneauPartie() {
		
	}
	
	/**
	 * Crée une nouvelle partie.
	 * Cette méthode vide tout d'abord le JPanel pour effacer l'affichage de la partie précédente.
	 * Puis elle lance un Thread sur Partie
	 * 
	 * @see Partie
	 */
	
	public void nouvellePartie() {
		this.removeAll();
		partie = new Partie();
		this.add(partie);
		Thread calculs = new Thread(partie);
		
		calculs.start();
	}
}
