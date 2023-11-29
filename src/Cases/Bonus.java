package Cases;

import Jeu.Snake;
import Jeu.Quadrillage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe repr�sente le bonus que le snake doit manger en passant sa t�te dessus.
 * 
 * @see Snake
 * @see TeteSnake
 *
 */

public class Bonus extends Case{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean reapparaitre;

	/**
	 * Ce constructeur permet uniquement de d'instancier un objet Bonus sans qu'il ait de position.
	 * C'est pourquoi on affecte 'true' � son bool�en 'reapparaitre'.
	 */
	
	public Bonus() {
		super();
		this.reapparaitre = true;
	}
	
	/**
	 * Ce constructeur permet d'instancier un objet Bonus en lui affectant une position 'al�atoire' sur
	 * l'ensemble des cases vides.
	 * 
	 * Pou cela, on instancie 2 tableaux de int de la taille max potentiellement occup�es par des cases vides.
	 * Puis on remplit ces tableaux avec les positions des cases vides contenues dans tabTypeCase.
	 * 
	 * Ensuite on g�n�re al�atoirement un entier.
	 * Cet entier va d�signer dans quelle case des 2 tableaux on va r�cup�rer la position pour le bonus.
	 * 
	 * Et pour finir, on affecte la position au bonus.
	 * 
	 * @param tabTypeCase Le tableau contenant les types de cases actuelles aux positions x et y dans
	 * les cases du tableau[x][y].
	 * 
	 * @param tailleQuadrillageX La taille en abscisse du quadrillage.
	 * @param tailleQuadrillageY La taille en ordonn�e du quadrillage.
	 * 
	 * @see CaseVide
	 * @see TypeCase
	 * @see Quadrillage
	 */
	
	public void setPos(Case[][] tabTypeCase, int tailleQuadrillageX, int tailleQuadrillageY) {
		 this.reapparaitre = false;
		  int newPosX;
		  int newPosY;
		  
		  do {
		   newPosX = (int) Math.round(Math.random()*(tabTypeCase.length-1));
		   newPosY = (int) Math.round(Math.random()*(tabTypeCase[newPosX].length-1));
		  } while(tabTypeCase[newPosX][newPosY].type()!=TypeCase.CaseVide);

		  super.setPosX(newPosX);
		  super.setPosY(newPosY);
	}
	
	public TypeCase type() {
		return TypeCase.Bonus;
	}
	
	
	/**
	 * Cette m�thode permet de charger une image en arri�re plan.
	 */
	
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/fruit/pomme.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
	
	/**
	 * Cette m�thode permet de savoir si le bonus doit changer de positions
	 * � la prochaine actualisation du quadrillage.
	 * 
	 * @return Elle renvoie vrai si le bonus doit r�apparaittre et faux sinon.
	 */
	public boolean isreapparaitre() {
		return reapparaitre;
	}

	/**
	 * Cette m�thode permet d'affecter vrai ou faux au bool�en r�apparaitre.
	 * 
	 * @param reapparaitre Un bool�en disant si le bonus doit r�apparaittre et faux sinon.
	 */
	public void setReapparaitre(boolean reapparaitre) {
		this.reapparaitre = reapparaitre;
	}
	
}
