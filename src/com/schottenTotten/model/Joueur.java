package com.schottenTotten.model;

import java.util.List;

public class Joueur {
	private List<Carte> PaquetJoueur;
	private List<Boolean> BornesRevendiquées;
	
	public Joueur(List<Carte> paquetJoueur, List<Boolean> BornesRevendiquées) {
		this.setPaquetJoueur(paquetJoueur);
		this.setBornesRevendiquées(BornesRevendiquées);
	}
	
	public List<Carte> getPaquetJoueur() {
		return PaquetJoueur;
	}
	public void setPaquetJoueur(List<Carte> paquetJoueur) {
		PaquetJoueur = paquetJoueur;
	}

	public List<Boolean> getBornesRevendiquées() {
		return BornesRevendiquées;
	}

	public void setBornesRevendiquées(List<Boolean> bornesRevendiquées) {
		BornesRevendiquées = bornesRevendiquées;
	}
}
