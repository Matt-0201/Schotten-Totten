package com.schottenTotten.ai;

import java.util.List;
import java.util.Scanner;

import com.schottenTotten.controller.Jeu;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class Humain extends Joueur{

	public Humain(List<Carte> paquetJoueur, int[] bornes) {
		super(paquetJoueur, bornes);
	}
	
	// Méthode pour récupérer l'entrée joueur
	@SuppressWarnings("resource")
	public static String [] placementJoueur(int player, Jeu game) {
		System.out.println("Joueur " + player + ", c'est à vous de jouer" );
		
		// On récupère la sortie du joueur
		Scanner scEntry = new Scanner(System.in);
		String strEntry = scEntry.nextLine();
		// On vérifie si le joueur a demandé a quitter la partie
		if (strEntry.equals("quit")) {
			System.out.println("Vous avez quitté cette partie. À la prochaine ! ");
			game.setIsPlaying(0);
		}
		
		// On place la demande du joueur dans une liste de String
		String [] placement = strEntry.split(" ");
		
		return placement;
	}
	
	// Méthode pour que le joueur humain puisse placer sa carte
	public static boolean placerCarte(String [] placement, Joueur J, List<Borne> bornes, Jeu game) {
		int indexCarte;
		int indexBorne ;
		// On vérifie si on n'essaie pas de passer une Sting vide à parseInt
		try {
			indexCarte = Integer.parseInt(placement[0]);
			indexBorne = Integer.parseInt(placement[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		if ((indexCarte < 1|| indexCarte > J.getPaquetJoueur().size()) || (indexBorne < 1 || indexBorne > 9)) {
			return false;
		}
		Carte carteAPlacer = J.getPaquetJoueur().get(indexCarte-1);
		List<Carte> coteBorne;
	    if (J == game.getJ1()) {
	        coteBorne = bornes.get(indexBorne - 1).getCartesJ1();
	    } else {
	        coteBorne = bornes.get(indexBorne - 1).getCartesJ2();
	    }

	    if (coteBorne.size() < 3) {
	        coteBorne.add(carteAPlacer);
	        J.getPaquetJoueur().remove(indexCarte - 1);
	        
	        if (game.getPioche().size() > 0) {
	            Carte.pioche(J, game.getPioche());
	        }
	        return true;
	    } else {
	        System.out.println("Cette borne est déjà pleine de votre côté !");
	        return false;
	    }
	}
	
}
