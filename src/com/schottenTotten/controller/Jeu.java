package com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.schottenTotten.ai.Humain;
import com.schottenTotten.ai.Robot;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.view.Affichage;

public abstract class Jeu {
	
	// Attributs propres à toutes les variantes de jeux
	protected int isPlaying;
	protected Joueur J1;
	protected Joueur J2;
	protected List<Carte> pioche;
	protected List<Borne> bornes;
	
	// Méthode pour créer un jeu de base
	public Jeu(Joueur J1, Joueur J2) {
		this.isPlaying = 1;
		this.J1 = J1;
		this.J2 = J2;
		this.pioche = new ArrayList<Carte>();
		this.bornes = new ArrayList<Borne>();
		
		// Initialisation des 9 bornes, commun à toutes les variantes
		for (int i = 0; i < 9; i++) {
			bornes.add(new Borne(new ArrayList<Carte>(), new ArrayList<Carte>(), 0, 0));
		}
	}

	// Chaque version du jeu devra définir comment on prépare son paquet
	public abstract void initialiserPaquet(); 
	
	// Méthode commune à chaque variante, lance le jeu
	public void lancerPartie() {
		System.out.println("Initialisation du jeu....\n");
		
		// On initialise le jeu selon la variante
		this.initialiserPaquet();
		
		// Mélange et Distribution (Commun)
		Collections.shuffle(this.pioche);
		Carte.distribInit(this.J1, this.pioche);
		Carte.distribInit(this.J2, this.pioche);
		
		System.out.println("Bienvenue dans cette partie de Schotten Totten ! ");

		Joueur JoueurActif = this.J1;
		Joueur JoueurPassif = this.J2;
		
		// Boucle de jeu principale
		while(this.isPlaying == 1) {
			
			// Vérification des bornes
			Borne.GiveBornes(this.bornes, this.J1, this.J2);
			
			// Affichage
			System.out.println("Etat du jeu:");
			Affichage.AfficherPlateau(this.bornes, JoueurActif, JoueurPassif, this);
			
			// Tour du joueur
			boolean verifyTurn = JoueurActif.prochainTour(this.bornes, this);
			
			if (verifyTurn) {
				System.out.println("Le tour s'est bien passé");
				// Échange des rôles
				Joueur temp = JoueurActif;
				JoueurActif = JoueurPassif;
				JoueurPassif = temp;
			} else {
				System.out.println("Il y a eu un problème dans le tour. Veuillez rejouer.");
			}
			
			// Condition de victoire
			if (Borne.isPlayerWin(JoueurActif)) {
				System.out.println("Le joueur " + JoueurActif.getPseudo() + " a gagné, bravo !");
				this.isPlaying = 0;
			} else if (Borne.isPlayerWin(JoueurPassif)) {
                // On peut aussi vérifier l'autre joueur, très rare
				System.out.println("Le joueur " + JoueurPassif.getPseudo() + " a gagné, bravo !");
				this.isPlaying = 0;
			}
		}
		System.out.println("La partie est terminée ! Merci d'avoir joué !");
	}

	// Main qui lance le jeu
	public static void main(String[] args) {
		// Le main ne sert plus qu'à configurer et lancer
		Jeu game = InitParams();
		if (game != null) {
			game.lancerPartie();
		}
	}
	
	@SuppressWarnings("resource")
	public static Jeu InitParams() {
		Affichage.AffichageDeBienvenue();
		Scanner scEntry = new Scanner(System.in);
		
		// On peut aussi demander l'ouverture d'un menu pour un mode graphique ici
		
		// Demander ici si on crée un JeuNormal ou un JeuTactique
		
		// Boucle pour l'initialisation du jeu
		while(true) {
			System.out.println("Tapez 1 pour Joueur vs Bot, 2 pour Joueur vs Joueur :");
			String strEntry = scEntry.nextLine();
			
			if (strEntry.equals("2")) {
				System.out.println("Mode : Humain vs Humain.");
				Humain J1 = new Humain(new ArrayList<Carte>(), new int[9], "J1");
				Humain J2 = new Humain(new ArrayList<Carte>(), new int[9], "J2");
				// Pour l'instant uniquement JeuNormal
				return new JeuNormal(J1, J2); 
				
			} else if (strEntry.equals("1")) {
				System.out.println("Mode : Humain vs Ordinateur.");
				Humain J1 = new Humain(new ArrayList<Carte>(), new int[9], "J1");
				Robot J2 = new Robot(new ArrayList<Carte>(), new int[9], "Bot");
				// Pour l'instant uniquement JeuNormal
				return new JeuNormal(J1, J2);
			}
		}
	}
	
	// Getters et Setters
	public int getIsPlaying() {
		return isPlaying; 
	}
	
	public void setIsPlaying( int isPlaying) { 
		this.isPlaying = isPlaying; 
	}
	
	public Joueur getJ1() { 
		return J1; 
	}
	public Joueur getJ2() { 
		return J2; 
	}
	public List<Carte> getPioche() { 
		return pioche; 
	}
	public void setPioche(List<Carte> pioche) { 
		this.pioche = pioche; 
	}
    public void setJ1(Joueur j1) {
		J1 = j1;
	}

	public void setJ2(Joueur j2) {
		J2 = j2;
	}
}