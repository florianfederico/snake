package Cases;

import Jeu.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jeu.Direction;

/**
 * 
 * @author GALMES_SIMON
 * @version 1.0
 * 
 * Cette classe désigne la tête du snake.
 * Si la tête du snake passe sur un bonus, corps du snake augmente de 1.
 * En revanche, si elle entre en collision avec un obstacle, la partie est terminée.
 * 
 * @see Snake
 * @see Bonus
 * @see CorpsSnake
 * @see Obstacle
 *
 */


public class TeteSnake extends CorpsSnake {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Direction direction;

	/**
	 * Ce constructeur instancie la tête du snake avec une position et une direction.
	 * 
	 * @param x La position en abscisse.
	 * @param y La position en ordonnée.
	 * @param direction La direction.
	 * 
	 * @see Snake
	 * @see Direction
	 */
	
	public TeteSnake(int x, int y, Direction direction) {
		super(x, y);
		this.direction = direction;
	}
	public TeteSnake() {
		super();
	}
	
	public TypeCase type() {
		return TypeCase.TeteSnake;
	}
	
	/**
	 * Cette méthode permet de charger une image en arrière plan.
	 */
	
	public void paintComponent(Graphics g){
		 try {
			 Image img;
			 switch(this.direction) {
			 case GAUCHE:
				 img = ImageIO.read(new File("images/snake/tete_g.png"));
				 break;
			 case DROITE:
				 img = ImageIO.read(new File("images/snake/tete_d.png"));
				 break;
			 case HAUT:
				 img = ImageIO.read(new File("images/snake/tete_h.png"));
				 break;
			 case BAS:
				 img = ImageIO.read(new File("images/snake/tete_b.png"));
				 break;
			default:
				img = ImageIO.read(new File("images/snake/tete_g.png"));
				break;
			 }
			 
			 g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }                
	}
	
	/**
	 * Cette méthode permet de faire avancer 
	 * la tête du snake dans la direction actuelle du snake.
	 *  
	 * @param direction La direction du snake.
	 * 
	 * @see Direction
	 */
	
	public void avancer(Direction direction) {
		switch(direction) {
		case GAUCHE:
			this.setPosX(this.getPosX() -1);
			break;
		case DROITE:
			this.setPosX(this.getPosX() +1);
			break;
		case HAUT:
			this.setPosY(this.getPosY() -1);
			break;
		case BAS:
			this.setPosY(this.getPosY() +1);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Renvoie la direction du snake.
	 * @return La direction du snake.
	 */
	
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Affecte la direction du snake.
	 * @param direction La nouvelle direction du snake.
	 */
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
