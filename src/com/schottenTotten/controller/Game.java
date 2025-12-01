package com.schottenTotten.controller;

import java.util.List;
import com.schottenTotten.model.Carte;

public class Game {
	
	public static void main(String[] args) {
		Carte c = new Carte(5, "Rouge");
		System.out.println("Le jeu commence");
		System.out.println("Voici la valeur d'une carte: " + c.getValeur() + " " + c.getCouleur());
		
		List<Carte> PaquetCartes = Carte.CreationPaquet();
		
		Carte.verifPaquet(PaquetCartes);
		
	}
	
}
