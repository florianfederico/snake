package Cases;

import Fenetres.Partie;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette �num�ration d�signe les types de cases existants.
 * Elle est notamment utilis�e pour le tableau de types de cases dans Partie.
 * 
 * @see Partie
 * @see CaseVide
 * @see CorpsSnake
 * @see TeteSnake
 * @see Obstacle
 * @see Bonus
 */

public enum TypeCase {
	CaseVide,
	Obstacle,
	TeteSnake,
	CorpsSnake,
	Bonus
}
