package com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class Jeu {
	
	public static void main(String[] args) {
		Carte c = new Carte(5, "Rouge");
		System.out.println("Le jeu commence");
		System.out.println("Voici la valeur d'une carte: " + c.getValeur() + " " + c.getCouleur());
		
		List<Carte> Pioche = Carte.CreationPaquet();
		
		Collections.shuffle(Pioche);
		
		Carte.printPaquet(Pioche);
		System.out.println("Paquet affiché");
		
		Joueur J1 = new Joueur(new ArrayList<Carte>(), new ArrayList<Boolean>());
		
		Carte.distribInit(J1, Pioche);
		
		System.out.println(Pioche.size());
	}
}
