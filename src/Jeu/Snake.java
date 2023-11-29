package Jeu;

import Cases.CorpsSnake;
import Cases.TeteSnake;

/**
 * 
 * @author GALMES_SIMON
 * @version 1.0
 * 
 * Cette classe d�signe le snake.
 * Le snake est compos� d'une t�te et de 
 * plusieurs objets CorpsSnake qui repr�sentent son corps.
 * 
 * Le snake poss�de une direction pour aiguiller ses d�placements.
 * 
 *  @see TeteSnake
 *  @see CorpsSnake
 *  @see Direction
 *
 */

public class Snake {
	private final int TAILLE_INIT_CORPS = 4;
	
	// Temps (en ms) durant lequel le snake ne bouge pas 
	private final int VITESSE = 120;

	private TeteSnake tete;
	private CorpsSnake[] corps;
	
	private int tailleCorpsSnakeMax;
	private int tailleCorpsSnake;
	private Direction direction;
	
	private int tempsDerniereAction;
	
	// Emp�che le joueur de changer plusieurs fois de direction avant le d�placement du snake
	private boolean aAvancerVersDerniereDirection;
	
	/**
	 * Ce constructeur instancie le snake.
	 * 
	 * Il d�finit sa direction � 'GAUCHE'.
	 * Il instancie sa t�te avec la position pass�e en param�tre. 
	 * Il instancie un tableau de taille max pour son corps.
	 * Il positionne son corps sur la droite de sa t�te.
	 * 
	 * @param posX La position en abscisse de la t�te du snake.
	 * @param posY La position en ordonn�e de la t�te du snake.
	 * @param tailleQuadrillageX La taille X du quadrillage.
	 * @param tailleQuadrillageY La taille Y du quadrillage.
	 * 
	 * @see TeteSnake
	 * @see CorpsSnake
	 * @see Direction
	 * @see Quadrillage
	 */
	
	public Snake(int posX, int posY, int tailleQuadrillageX, int tailleQuadrillageY) {
		
		this.direction = Direction.GAUCHE;
		tete = new TeteSnake(posX, posY, this.direction);
		this.tailleCorpsSnakeMax = (tailleQuadrillageX-2) * (tailleQuadrillageY-2) -1; // -2 pour bordures et -1 pour tete
		corps = new CorpsSnake[this.tailleCorpsSnakeMax];
		
		for(int i=0; i < this.TAILLE_INIT_CORPS; i++) {
			posX++;
			corps[i] = new CorpsSnake(posX, posY);	
		}
		
		
		this.tailleCorpsSnake = this.TAILLE_INIT_CORPS;
		this.tempsDerniereAction = 0;
		this.aAvancerVersDerniereDirection = false;
	}
	
	/**
	 * Cette m�thode permet de faire avancer le snake.
	 * Le corps i prend la place du corps i-1 et la t�te avance dans la direction actuelle.
	 * 
	 */
	
	public void avancer() {
		for(int i= this.tailleCorpsSnake-1; i >= 1; i--) {
			corps[i].avancer(corps[i-1]);
		}
		corps[0].avancer(tete);
		tete.avancer(direction);
		this.aAvancerVersDerniereDirection = true;
	}
	
	/**
	 * Cette m�thode permet de faire grandir le snake.
	 * La taille de son corps augmente de 1.
	 * La nouvelle queue du snake est positionn�e � la place de l'ancienne queue.
	 * 
	 */
	
	public void grandir() {
		if(this.tailleCorpsSnake < this.tailleCorpsSnakeMax) {
			int queuePosX = this.corps[this.tailleCorpsSnake-1].getPosX();
			int queuePosY = this.corps[this.tailleCorpsSnake-1].getPosY();
			
			this.corps[this.tailleCorpsSnake] = new CorpsSnake(queuePosX, queuePosY);
			this.tailleCorpsSnake++;
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
	 * Affecte la direction du snake et celle de sa t�te.
	 * @param direction La nouvelle direction du snake.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
		this.tete.setDirection(direction);
	}
	
	/**
	 * Renvoie la t�te du snake.
	 * @return La t�te du snake.
	 */
	
	public TeteSnake getTete() {
		return tete;
	}

	/**
	 * Renvoie le corps du snake.
	 * @return Le corps du snake.
	 */
	
	public CorpsSnake[] getCorps() {
		return corps;
	}

	/**
	 * Renvoie la taille du corps du snake.
	 * @return La taille du corps du snake.
	 */

	public int getTailleCorpsSnake() {
		return tailleCorpsSnake;
	}

	/**
	 * Renvoie la vitesse du snake.
	 * @return La vitesse du snake (en ms).
	 */
	
	public int getVITESSE() {
		return VITESSE;
	}
	
	/**
	 * Renvoie le temps de la derniere action du snake.
	 * @return Le temps de la derniere action du snake.
	 */

	public int getTempsDerniereAction() {
		return tempsDerniereAction;
	}

	/**
	 * Affecte le temps de la derniere action du snake.
	 * @param tempsDerniereAction Le nouveau temps de la derniere action du snake.
	 */
	
	public void setTempsDerniereAction(int tempsDerniereAction) {
		this.tempsDerniereAction = tempsDerniereAction;
	}
	
	/**
	 * Permet de savoir si le snake a avanc� vers sa derni�re direction.
	 * @return Vrai si il a avanc�, faux sinon.
	 */

	public boolean isaAvancerVersDerniereDirection() {
		return aAvancerVersDerniereDirection;
	}
	
	/**
	 * Affecte le bool�en qui permet de savoir si le snake a avanc� vers sa derni�re direction.
	 * @param aAvancerVersDerniereDirection Le nouveau bool�en.
	 */

	public void setaAvancerVersDerniereDirection(
			boolean aAvancerVersDerniereDirection) {
		this.aAvancerVersDerniereDirection = aAvancerVersDerniereDirection;
	}
	
	
}
