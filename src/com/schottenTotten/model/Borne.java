package com.schottenTotten.model;

import java.util.List;

import com.schottenTotten.controller.Jeu;

public class Borne {
	private List<Carte> cartesJ1;
	private List<Carte> cartesJ2; 
	// Score du joueur sur la borne
	private int scoreJ1;
	private int scoreJ2;
	
	public Borne(List<Carte> cartesJ1, List<Carte> cartesJ2, int scoreJ1, int scoreJ2) {
		this.setCartesJ1(cartesJ1);
		this.setCartesJ2(cartesJ2);
		this.setScoreJ1(scoreJ1);
		this.setScoreJ2(scoreJ2);
	}

	public List<Carte> getCartesJ1() {
		return cartesJ1;
	}

	public void setCartesJ1(List<Carte> cartesJ1) {
		this.cartesJ1 = cartesJ1;
	}

	public List<Carte> getCartesJ2() {
		return cartesJ2;
	}

	public void setCartesJ2(List<Carte> cartesJ2) {
		this.cartesJ2 = cartesJ2;
	}
	
	public int getScoreJ1() {
		return scoreJ1;
	}

	public void setScoreJ1(int scoreJ1) {
		this.scoreJ1 = scoreJ1;
	}

	public int getScoreJ2() {
		return scoreJ2;
	}

	public void setScoreJ2(int scoreJ2) {
		this.scoreJ2 = scoreJ2;
	}
	
	// Méthode pour donner une borne au joueur qui a la meilleur combinaison sur l'une d'elles
	public static void GiveBornes(List<Borne> bornes, Joueur J1, Joueur J2) {
		for (int i = 0; i < bornes.size(); i++) {
			Borne b = bornes.get(i);
			if (b.getCartesJ1().size() == 3 & b.getCartesJ2().size() == 3) {
				b.setScoreJ1(Jeu.scoreBorne(b.getCartesJ1()));
				b.setScoreJ2(Jeu.scoreBorne(b.getCartesJ2()));
				int score1 = b.getScoreJ1();
				int score2 = b.getScoreJ2();
				
				if (score1 > score2) {
					J1.getBornes()[i] = 1;
				} else if (score2 > score1) {
					J2.getBornes()[i] = 1	;
				}
			}
		}
	}
	
	// Méthode qui vérifie si un joueur remplit les condition de victoire
	public static boolean isPlayerWin(Joueur J) {
		
		// On vérifie d'abord si le joueur possède 3 bornes alignées
		for (int borne = 0; borne < J.getBornes().length-3; borne++) {
			if (J.getBornes()[borne] == 1 && J.getBornes()[borne+1] == 1 && J.getBornes()[borne+2] == 1) {
				return true;
			}
		}
		// Sinon, on vérifie que le joueur possède 5 bornes en tout
		int nbBornes = 0;
		for (int borne = 0; borne < J.getBornes().length; borne++) {
			if (J.getBornes()[borne] == 1) {
				nbBornes ++;
			}
		}
		if (nbBornes >= 5) {
			return true;
		} else {
			return false;
		}
	}
	
}
