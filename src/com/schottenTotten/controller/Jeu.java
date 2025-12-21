package com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.schottenTotten.ai.Humain;
import com.schottenTotten.ai.Robot;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.view.Affichage;

public class Jeu {
	@SuppressWarnings("resource")
	
	//private List<Borne> bornes;
	
	private int isPlaying;
	private Joueur J1;
	private Joueur J2;
	private List<Carte> pioche;
	
	public Jeu(int isPlaying, Joueur J1, Joueur J2, List<Carte> pioche) {
		this.setIsPlaying(isPlaying);
		this.setJ1(J1);
		this.setJ2(J2);
		this.setPioche(pioche);
	}
	
	// Créé un "Jeu" par défaut
	public Jeu() {
		isPlaying = 1;
		J1 = new Joueur();
		J2 = new Joueur();
		pioche = new ArrayList<Carte>();
	}

	public int getIsPlaying() {
		return isPlaying;
	}

	public void setIsPlaying(int isPlaying) {
		this.isPlaying = isPlaying;
	}

	public Joueur getJ1() {
		return J1;
	}

	public void setJ1(Joueur j1) {
		J1 = j1;
	}

	public Joueur getJ2() {
		return J2;
	}

	public void setJ2(Joueur j2) {
		J2 = j2;
	}
	
	public List<Carte> getPioche() {
		return pioche;
	}

	public void setPioche(List<Carte> pioche) {
		this.pioche = pioche;
	}

	public static void main(String[] args) {
		
		// Initialisation des vaariables utiles au bon foctionnement du jeu
		System.out.println("Initialisation du jeu....");
		System.out.print("\n");
		
		Jeu game = InitParams();
		
		// Création des 54 cartes utiles au jeu
		game.setPioche(Carte.CreationPaquet());
		
		// Mélange des cartes dans la liste crée
		Collections.shuffle(game.pioche);
		
		
		// Distribution initiale, 6 cartes pour chaque joueur
		Carte.distribInit(game.J1, game.pioche);
		Carte.distribInit(game.J2, game.pioche);
		
		// Test d'ajout de cartes dans une liste de cartes (pour les bornes)
		List<Carte> Cartes1 = new ArrayList<Carte>();
		Cartes1.add(new Carte(5, "Red"));
		Cartes1.add(new Carte(3, "Purple"));
		Cartes1.add(new Carte(4, "Blue"));
		
		List<Carte> Cartes2 = new ArrayList<Carte>();
		Cartes2.add(new Carte(1, "Green"));
		Cartes2.add(new Carte(3, "Green"));
		Cartes2.add(new Carte(2, "Green"));
		
		List<Carte> Cartes3 = new ArrayList<Carte>();
		Cartes3.add(new Carte(8, "Blue"));
		Cartes3.add(new Carte(8, "Brown"));
		Cartes3.add(new Carte(8, "Yellow"));

		// Création des bornes
		List<Borne> bornes = new ArrayList<Borne>();
		for (int i = 0; i < 9; i++) {
			List<Carte> c1 = new ArrayList<Carte>();
			List<Carte> c2 = new ArrayList<Carte>();
			int score1 = 0;
			int score2 = 0;
			Borne b = new Borne(c1, c2, score1, score2);
			bornes.add(b);
		}
		// Tests divers sur les bornes (on garde pour l'instant, permet de mettre des cartes dans le jeu)
		Borne b1 = bornes.get(5);
		b1.setCartesJ1(Cartes1);
		
		/*Borne b2 = bornes.get(5);
		b2.setCartesJ2(Cartes2);

		Borne b3 = bornes.get(7);
		b3.setCartesJ1(Cartes3);*/
		
		// Le joueur 1 commence
		Joueur JoueurActif = game.J1;
		//Borne BorneActive = 
		Joueur JoueurPassif = game.J2;
		int player = 1;
		
		// Le jeu peut démarrer
		System.out.println("Bienvenue dans cette partie de Schotten Totten ! ");
		
		//Borne borne2 = bornes.get(5);
		System.out.println(JoueurActif.equals(game.J1));
		System.out.println(JoueurActif.equals(game.J2));
		System.out.println("");
		System.out.println(JoueurActif == game.J1);
		System.out.println(JoueurActif == game.J2);
		
		// Boucle de jeu
		while(game.isPlaying == 1) {
			
			// A chaque début de boucle, on regarde si une borne est remplie (3 cartes de chaque côté)
			Borne.GiveBornes(bornes, game.J1, game.J2);
			
			if (JoueurActif instanceof Humain) {
				// On démarre chaque boucle de joueur humain en affichant le plateau de jeu et la main du joueur
				System.out.println("Etat du jeu:");
				//Affichage.AfficherPlateau(bornes, player, game.J1, game.J2);
				Affichage.AfficherPlateau(bornes, JoueurActif, JoueurPassif, game);
				Affichage.AfficherJeu(JoueurActif);
				
				// On demande au joueur de placer une carte
				String [] placement = Humain.placementJoueur(player, game);
				
				// On vérifie si l'entrée joueur est correcte
				if (placement.length != 2 && game.getIsPlaying() == 1) {
					System.out.println("Erreur dans l'expression. Format: a-b avec a=index de votre carte et b=index de la borne.");
					
				} else if (game.getIsPlaying() == 1) {
					// On fait jouer l'humain
					boolean verifyTurn = Humain.placerCarte(placement, JoueurActif, bornes, game);
					// On change de joueur si le tour s'est bien passé
					if (verifyTurn) {
						System.out.println("Le tour s'est bien passé");
						if (JoueurActif == game.J1) {
							JoueurActif = game.J2;
							JoueurPassif = game.J1;
							//player = 2;
						} else {
							JoueurActif = game.J1;
							JoueurPassif = game.J2;
							//player = 1;
						}
					} else {
						System.out.println("Il y a eu un problème. Vous avez peut-être joué sur une zone impossible.");
						System.out.println("Veuillez rejouer.");
					}
				} else if (game.getIsPlaying() == 0) { // La valeur isPlaying a peut-être été modifiée dans placementJoueur
					System.out.println("La partie est terminée");
				}
			} else if (JoueurActif instanceof Robot) {
				System.out.println("Le bot joue...");
				// On fait jouer le robot
				boolean verifyTurn = Robot.placementRandom(JoueurActif, bornes, game.pioche);
				// On change de joueur si le tour s'est bien passé
				if (verifyTurn) {
					if (JoueurActif == game.J1) {
						JoueurActif = game.J2;
						JoueurPassif = game.J1;
						//player = 2;
					} else {
						JoueurActif = game.J1;
						JoueurPassif = game.J2;
						//player = 1;					
					}
				}
			}
			
			// A chaque fin de boucle, on regarde si le joueur actif remplit la condition de victoire
			boolean isPlayerWin = Borne.isPlayerWin(JoueurActif);
			if (isPlayerWin == true) {
				System.out.println("Le joueur " + player + " a gagné, bravo !");
				game.isPlaying = 0;
			}
			System.out.println("La partie est terminée ! Merci d'avoir joué !");
			
		}
	}
	
	// Fonction qui regarde le score d'un côté du jeu. Indépendante du joueur.
	public static int scoreBorne(List<Carte> cartes) {
		// On vérifie le score sur la borne en regardant la combinaison dessus.
		List<Integer> listeValeur = new ArrayList<Integer>();
		for (int i = 0; i < cartes.size(); i++) {
			listeValeur.add(cartes.get(i).getValeur());
		}
		// On classe les cartes pour faciliter la vérification des suites
		Collections.sort(listeValeur);
		int isFollowing = 0;
		int sameValue = 0;
		int sameColor = 0;
		// On extrait les 3 cartes pour manipuler plus facilement
		Carte c1 = cartes.get(0);
		Carte c2 = cartes.get(1);
		Carte c3 = cartes.get(2);
		
		// Test pour voir si les cartes se suivent
		if (((listeValeur.get(0) + 1) == listeValeur.get(1)) & ((listeValeur.get(1) + 1) == listeValeur.get(2))) {
			isFollowing = 1;
		} else if (listeValeur.get(0) == listeValeur.get(1) & ((listeValeur.get(1)) == listeValeur.get(2))) {
			sameValue = 1;
		}
		if (c1.getCouleur() == c2.getCouleur() & c2.getCouleur() == c3.getCouleur()) {
			sameColor = 1;
		}
		// On vérifie en premier la condition la plus forte
		if (isFollowing == 1 & sameColor == 1) {
			return 4;
		} else if (sameValue == 1){
			return 3;
		} else if (sameColor == 1){
			return 2;
		} else if (isFollowing == 1){
			return 1;
		} else {
			return 0;
		}
	}
	// Méthode pour initialiser le jeu
	@SuppressWarnings("resource")
	public static Jeu InitParams() {
		// Affichage pour accueillir le joueur
		Affichage.AffichageDeBienvenue();
		// Création du "Jeu"
		Jeu game = null;
		while(true) {
			Scanner scEntry = new Scanner(System.in);
			String strEntry = scEntry.nextLine();
			if (strEntry.equals("2")) {
				System.out.println("Vous avez décidé de jouer en mode deux joueurs.");
				// Création des joueurs (deux humains)
				Humain J1 = new Humain(new ArrayList<Carte>(), new int[9]);
				Humain J2 = new Humain(new ArrayList<Carte>(), new int[9]);
				game = new Jeu(1, J1, J2, new ArrayList<Carte>());
				return game;
			} else if (strEntry.equals("1")) {
				System.out.println("Vous avez décidé de jouer contre un ordinateur.");
				Humain J1 = new Humain(new ArrayList<Carte>(), new int[9]);
				Robot J2 = new Robot(new ArrayList<Carte>(), new int[9]);
				game = new Jeu(1, J1, J2, new ArrayList<Carte>());
				return game;
			}
		}
	}
	
}
