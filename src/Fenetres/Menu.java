package Fenetres;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe correspond au JPanel du menu.
 * C'est ici que l'utilisateur pourra choisir entre : 
 * lancer une partie, afficher les 5 meilleurs scores et quitter le programme.
 *
 */

public class Menu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jouer = new JButton(new ImageIcon("images/boutons/jouer.png"));
	private JButton scores = new JButton(new ImageIcon("images/boutons/scores.png"));
	private JButton quitter = new JButton(new ImageIcon("images/boutons/quitter.png"));
	private JLabel version = new JLabel("v0.9.1");
	
	public Menu() {
		super();
		
		/****** Boutons *****/
		this.setLayout(null);
		jouer.setPreferredSize(new Dimension(230,35));
		scores.setPreferredSize(new Dimension(230,35));
		quitter.setPreferredSize(new Dimension(230,35));
		
		this.add(jouer);
		jouer.setBounds(200, 180, 230, 35);
		this.add(scores);
		scores.setBounds(200, 240, 230, 35);
		scores.requestFocus();
		this.add(quitter);
		quitter.setBounds(200, 300, 230, 35);
		
		this.add(version);
		version.setBounds(590, 0, 100, 35);
		
		jouer.addActionListener(new JouerListener());
		scores.addActionListener(new ScoresListener());
		quitter.addActionListener(new QuitterListener());
		
	}
	
	/**
	 * Cette méthode permet de charger une image en arrière plan.
	 */
	
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/fond.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
	
	class JouerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Principale.panPartie.nouvellePartie();
			Principale.monCL.show(Principale.pan, "Partie");
		}
	}
	
	class ScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Principale.monCL.show(Principale.pan, "Meilleurs_Scores");
		}
	}
	
	class QuitterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
}
