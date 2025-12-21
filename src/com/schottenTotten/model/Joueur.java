package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Joueur {
	private List<Carte> paquetJoueur;
	private int[] bornes;
	
	public Joueur(List<Carte> paquetJoueur, int[] bornes) {
		this.setPaquetJoueur(paquetJoueur);
		this.setBornes(bornes);
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
	
	public static void bornesToSring (int[] bornes) {
		for (int i = 0; i < bornes.length; i++) {
			System.out.print(bornes[i] + "  ");			
		}
	}

}
