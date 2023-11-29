package Jeu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import Cases.*;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe designe le quadrillage de la partie.
 * Elle utlise un GridBagLayout pour creer une grille contenant des cases.
 * 
 * @see Case
 * @see CaseVide
 * @see Bonus
 * @see CorpsSnake
 * @see TeteSnake
 * @see Obstacle
 *
 */

public class Quadrillage extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private int tailleX;
	private int tailleY;
	
	/**
	 * On cree un quadrillage de taille 'tailleX' par 'tailleY'.
	 * 
	 * @param tailleX La taille en abscisse du quadrillage.
	 * @param tailleY La taille en ordonnÃ©e du quadrillage.
	 * 
	 */
	
	public Quadrillage(int tailleX, int tailleY) {
		this.setLayout(new GridBagLayout());
		
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		
		this.setPreferredSize(new Dimension(600,300));
		this.setMaximumSize(new Dimension(600, 300));
		this.setMinimumSize(new Dimension(600, 300));
		
		gbc.gridx = 0;
	    gbc.gridy = 0;
	}	
	
	/**
	 * Cette méthode va remplir le quadrillage à partir du tableau de cases
	 * passé en paramètres.
	 * @param tabTypeCase Le tableau de cases.
	 */
	
	public void actualiser(Case[][] tabTypeCase) 
	{
		this.removeAll();
		
		for(int x=0; x < this.tailleX; x++) 
		{
			for(int y=0; y < this.tailleY; y++) 
			{
				gbc.gridx = x;
				gbc.gridy = y;
				if(tabTypeCase[x][y]!=null)
				{
					this.add(tabTypeCase[x][y], gbc);
					tabTypeCase[x][y].validate();
				}
			}
		}
	    this.validate();
	}
}

