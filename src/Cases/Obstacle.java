package Cases;

import Jeu.Snake;
import Jeu.Quadrillage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author THOMAS_Anthony
 * @version 1.0
 * 
 * Cette classe d�signe les obstacles du quadrillage.
 * Si la t�te du snake entre en collision avec un obstacle, 
 * la partie est termin�e.
 * 
 * @see Snake
 * @see TeteSnake
 * @see Quadrillage
 *
 */

public class Obstacle extends Case {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Obstacle(int x, int y) {
		super();
		super.setPosX(x);
		super.setPosY(y);
		super.setCouleur(Color.RED);	// On laisse le setCouleur car sinon bugue d'affichage
		this.setBackground(super.getCouleur());
	}
	public Obstacle() {
		super();
		setCouleur(Color.RED);
		setBackground(getCouleur());
	}
	
	public TypeCase type() {
		return TypeCase.Obstacle;
	}
	
	/**
	 * Cette m�thode permet de charger une image en arri�re plan.
	 */
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/terrain/obstacle.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
}
