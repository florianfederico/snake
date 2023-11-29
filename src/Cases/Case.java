package Cases;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * 
 * @author THOMAS_Anthony
 * @version 1.0
 * 
 * Cette classe abstraite désigne l'ensemble des cases du projet.
 * 
 * @see CaseVide
 * @see Obstacle
 * @see TeteSnake
 * @see CorpsSnake
 * @see Bonus
 *
 */

public abstract class Case extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int TAILLE = 14;
	private int posX;
	private int posY;
	private Color couleur;
	
	/**
	 * Permet d'initialiser toutes les cases à la même dimension.
	 */
	public Case() {
		this.setPreferredSize(new Dimension(this.getTAILLE(), this.getTAILLE()));
	}
	
	public abstract TypeCase type();
	
	/**
	 * Renvoie la position en abscisse de la case.
	 * @return La position en abscisse de la case.
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Affecte la position en abscisse de la case.
	 * @param posX La nouvelle position en abscisse de la case.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	/**
	 * Renvoie la position en ordonnée de la case.
	 * @return La position en ordonnée de la case.
	 */
	 
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Affecte la position en ordonnée de la case.
	 * @param posY La nouvelle position en ordonnée de la case.
	 */
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	/**
	 * Renvoie la taille de la case.
	 * @return La taille de la case.
	 */
	public int getTAILLE() {
		return TAILLE;
	}
	
	/**
	 * Renvoie la couleur de la case.
	 * @return La couleur de la case.
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Affecte la couleur de la case.
	 * @param couleur La nouvelle couleur de la case.
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
