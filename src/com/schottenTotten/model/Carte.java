package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carte {
	private int valeur;
	private String couleur;
	
	public Carte(int valeur, String couleur) {
		this.setValeur(valeur);
		this.setCouleur(couleur);
	}

	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public static void displayCard(Carte card) {
		System.out.println("Voici une carte: " + card.getCouleur() + " " + card.getValeur());
	}
	
	public static List<Carte> CreationPaquet() {
		List<Carte> PaquetCartes = new ArrayList<Carte>();
		List<String> Colors = Arrays.asList("Red", "Blue", "Purple", "Green", "Brown", "Yellow");
		for (int i = 0; i < 6; i++)
			for (int j = 1; j < 10; j++) {
				PaquetCartes.add(new Carte(j, Colors.get(i)));
			}
		return PaquetCartes;
	}
	//Méthode pour vérifier si un paquet est bien initialisé (affiche toutes les cartes dans la console)
	public static void printPaquet(List<Carte> Paquet) {
		for (int i = 0; i < Paquet.size(); i++) {
			System.out.println(Paquet.get(i).getValeur() + " " + Paquet.get(i).getCouleur());			
		}
	}
	
	// Méthode qui distribue les cartes, renvoie un joueur avec des cartes
	public static void distribInit(Joueur j, List<Carte> Pioche) {
		for (int i = 0; i < 6; i++) {
			j.getPaquetJoueur().add(Pioche.get(Pioche.size()-1));
			Pioche.removeLast();
		}
	}
	
}
