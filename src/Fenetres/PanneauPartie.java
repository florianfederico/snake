package Fenetres;

import javax.swing.JPanel;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe contient le JPanel Partie.
 * Elle sert � lancer une nouvelle partie avec un Thread sur Partie.
 * Ceci dans le but de pouvoir actualiser r�guli�rement l'affichage de la partie.
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
	 * Cr�e une nouvelle partie.
	 * Cette m�thode vide tout d'abord le JPanel pour effacer l'affichage de la partie pr�c�dente.
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
