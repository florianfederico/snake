package Cases;

import Fenetres.Partie;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette énumération désigne les types de cases existants.
 * Elle est notamment utilisée pour le tableau de types de cases dans Partie.
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
