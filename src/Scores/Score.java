package Scores;

import java.io.Serializable;

/**
 * 
 * @author GUILAUME_Corentin
 * @version 1.0
 * 
 * Cette classe représente un meilleur score.
 * Elle est permet d'enregistrer les meilleurs scores dans un fichier grâce à la sérialisation.
 *
 */

public class Score implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomJoueur;
	private int valeur;
	
	public Score(String nomJoueur, int valeur) {
		this.nomJoueur = nomJoueur;
		this.valeur = valeur;
	}
	
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	public int getValeur() {
		return valeur;
	}
	public String getValeurString() {
		return ""+this.valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
}
