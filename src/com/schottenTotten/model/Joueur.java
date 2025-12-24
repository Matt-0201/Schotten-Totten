package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.schottenTotten.controller.Jeu;

public abstract class Joueur {
	protected List<Carte> paquetJoueur;
	protected int[] bornes;
	protected String pseudo;
	
	public Joueur(List<Carte> paquetJoueur, int[] bornes, String pseudo) {
		this.setPaquetJoueur(paquetJoueur);
		this.setBornes(bornes);
		this.setPseudo(pseudo);
	}
	
	public Joueur() {
		paquetJoueur = new ArrayList<Carte>();
		bornes =  new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
	}
	
	public List<Carte> getPaquetJoueur() {
		return paquetJoueur;
	}
	public void setPaquetJoueur(List<Carte> paquetJoueur) {
		this.paquetJoueur = paquetJoueur;
	}

	public int[] getBornes() {
		return bornes;
	}

	public void setBornes(int[] bornes) {
		Arrays.fill(bornes, 0);
		this.bornes = bornes;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public static void bornesToSring (int[] bornes) {
		for (int i = 0; i < bornes.length; i++) {
			System.out.print(bornes[i] + "  ");			
		}
	}

	// Méthode qui gère la logique des tours
	public abstract boolean prochainTour(List<Borne> bornes, Jeu game);
	
	// Méthode pour permettre à un joueur humain de se présenter
	public abstract void pseudoJoueur();

}
