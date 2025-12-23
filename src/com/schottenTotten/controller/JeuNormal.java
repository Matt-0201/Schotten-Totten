package com.schottenTotten.controller;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class JeuNormal extends Jeu {

	// Constructeur qui appelle celui de Jeu
	public JeuNormal(Joueur j1, Joueur j2) {
		super(j1, j2);
	}

	@Override
	public void initialiserPaquet() {
		// Implémentation spécifique au Jeu Normal :
		// On crée simplement le paquet de 54 cartes classiques
		System.out.println("Création du paquet standard (54 cartes)...");
		this.setPioche(Carte.CreationPaquet());
	}
}
