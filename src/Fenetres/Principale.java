package Fenetres;

import java.awt.CardLayout;


/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe contient la méthode main du projet.
 * C'est elle qui initialise les JFrame du projets.
 *
 */


public class Principale {
	
	static Fenetre fenetre = new Fenetre();
	static Panneau pan = new Panneau();
	static Menu menu = new Menu();
	
	static PanneauPartie panPartie = new PanneauPartie();
	static MeilleursScores meilleursScores = new MeilleursScores();
	static CardLayout monCL = new CardLayout();
	
	public static void main(String[] args) {
		
		pan.setLayout(monCL);
		pan.add("Menu", menu);
		pan.add("Partie", panPartie);
		pan.add("Meilleurs_Scores", meilleursScores);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
	}

}




