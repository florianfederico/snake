package Cases;

import Jeu.Quadrillage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author THOMAS_Anthony
 * @version 1.0
 *
 * Cette classe d�signe les cases vides du quadrillage.
 * 
 * @see Quadrillage
 * 
 */
public class CaseVide extends Case {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaseVide(int x, int y) {
		super();
		super.setPosX(x);
		super.setPosY(y);
	}
	//TODO MAT
	public CaseVide() {
		super();
	}
	
	public TypeCase type() {
		return TypeCase.CaseVide;
	}
	
	/**
	 * Cette m�thode permet de charger une image en arri�re plan.
	 */
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/terrain/case_vide.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
}
