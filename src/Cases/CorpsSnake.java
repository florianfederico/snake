package Cases;

import Jeu.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author GALMES_SIMON
 * @version 1.0
 * 
 * Cette classe désigne les éléments du corps du snake.
 * Un objet CorpsSnake est un Obstacle.
 *
 * @see Snake
 * @see Obstacle
 */

public class CorpsSnake extends Obstacle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorpsSnake(int x, int y) {
		super(x, y);
	}
	//TODO MAT
	public CorpsSnake() 
	{
		super();
	}
	
	public TypeCase type() {
		return TypeCase.CorpsSnake;
	}
	
	/**
	 * Cette méthode permet de charger une image en arrière plan.
	 */
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/snake/corps_dg.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
	
	/**
	 * Cette méthode remplace la position de cette case par 
	 * la position de la case passée en paramètre.
	 * 
	 * @param destination L'élément du corps du snake dont cet objet doit prendre la position.
	 */
	
	public void avancer(CorpsSnake destination) {
		this.setPosX(destination.getPosX());
		this.setPosY(destination.getPosY());
	}
}
