package com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class JeuNormal extends Jeu {

	// Constructeur qui appelle celui de Jeu
	public JeuNormal(Joueur j1, Joueur j2) {
		super(j1, j2);
	}
	
	@Override
	protected void initialiserPaquet() {
		// Implémentation spécifique au Jeu Normal :
		// On crée simplement le paquet de 54 cartes classiques
		System.out.println("Création du paquet standard (54 cartes)...");
		this.setPioche(Carte.CreationPaquet());
	}
	
	@Override
	public void piocher(Jeu game, Joueur J) {
		if (game.getPioche().size() > 0) {
			Carte.pioche(J.getPaquetJoueur(), game.getPioche());
	    } else {
	    	System.out.println("Il n'y a plus de cartes à piocher !");
	    }
	}

	@SuppressWarnings("null")
	@Override
	public boolean jouerCarte(Carte carte, Joueur joueur, int indexBorne, int indexCarteMain, List<Borne> bornes) {
	    // On détermine le côté de la borne
	    List<Carte> coteBorne = null;
	    if (joueur == this.getJ1()) {
	    	coteBorne = bornes.get(indexBorne - 1).getCartesJ1();
	    } else {
	    	coteBorne = bornes.get(indexBorne - 1).getCartesJ2();
	    }
	    // on vérifie si on peut jouer la carte
	    if (coteBorne.size() < 3) {
	        coteBorne.add(carte);
	        joueur.getPaquetJoueur().remove(indexCarteMain - 1);
	        
	        // On pioche après avoir joué
	        this.piocher(this, joueur); 
	        return true;
	    } else {
	        System.out.println("Cette borne est déjà pleine de votre côté !");
	        return false;
	    }
	}
}
