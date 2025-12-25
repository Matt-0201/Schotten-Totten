package com.schottenTotten.ai;

import java.util.List;
import java.util.Scanner;

import com.schottenTotten.controller.Jeu;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.view.Affichage;

public class Humain extends Joueur{

	public Humain(List<Carte> paquetJoueur, int[] bornes, String pseudo) {
		super(paquetJoueur, bornes, pseudo);
	}
	
	public Humain() {
		super();
	}

	// Méthode pour récupérer l'entrée joueur
	@SuppressWarnings("resource")
	public static String [] placementJoueur(Jeu game) {
		
		// On récupère l'entrée du joueur
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
	@Override
	public boolean prochainTour(List<Borne> bornes, Jeu game) {
		
		// On affiche le jeu du joueur courant, s'il est humain uniquement donc
		Affichage.AfficherJeu(this);
		
		// On récupère l'entrée joueur
		String [] placement = Humain.placementJoueur(game);	
		if (placement.length != 2) {
			return false;
		}
		int indexCarte;
		int indexBorne;
		// On vérifie si on n'essaie pas de passer une Sting vide à parseInt
		try {
			indexCarte = Integer.parseInt(placement[0]);
			indexBorne = Integer.parseInt(placement[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		if ((indexCarte < 1|| indexCarte > this.getPaquetJoueur().size()) || (indexBorne < 1 || indexBorne > 9)) {
			return false;
		}
		Carte carteAPlacer = this.getPaquetJoueur().get(indexCarte-1);
		
		// On place la carte  suivant si c'est une carte normale, ou une carte tactique (qui sont toutes différentes)
		boolean isPlayable = game.jouerCarte(carteAPlacer, this, indexBorne, indexCarte, bornes);
		
		return isPlayable;
	    }
	
	// Méthode pour prendre le pseudo du joueur et le sauvegarder
	@Override
	public void pseudoJoueur() {
		System.out.println("Joueur " + this.pseudo + ", veuillez choisir un pseudo");
		Scanner sc = new Scanner(System.in);
		boolean madeChoice = false;
		while (!madeChoice) {
			String pseudo = sc.nextLine();
			if (pseudo.length() != 0) {
				this.pseudo = pseudo;
				madeChoice = true;				
			} else {
				System.out.println("Psuedo non valide.");
			}
		}
	}
}
