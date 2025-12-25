package com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.schottenTotten.ai.Humain;
import com.schottenTotten.ai.Robot;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.CarteTactique;
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
	protected abstract void initialiserPaquet(); 
	
	// Chaque version du jeu propose au joueur de piocher différemennt
	public abstract void piocher(Jeu game, Joueur J);
	
	// Chaque version du jeu joue différemment
	//public abstract boolean jouerCarte(Carte c);
	public abstract boolean jouerCarte(Carte carte, Joueur joueur, int indexBorne, int indexCarteMain, List<Borne> bornes);
	
	// Méthode commune à chaque variante, lance le jeu
	public void lancerPartie() {
		System.out.println("Initialisation du jeu....\n");
		// On initialise le jeu selon la variante
		this.initialiserPaquet();
		
		// Mélange et Distribution (Commun)
		Collections.shuffle(this.pioche);
		Carte.distribInit(this.J1, this.pioche, this);
		Carte.distribInit(this.J2, this.pioche, this);
		
		System.out.println("Bienvenue dans cette partie de Schotten Totten ! ");

		this.J1.pseudoJoueur();
		this.J2.pseudoJoueur();
		
		Joueur JoueurActif = this.J1;
		Joueur JoueurPassif = this.J2;
		
		// Boucle de jeu principale
		while(this.isPlaying == 1) {
			
			// Vérification des bornes
			this.GiveBornes(this.bornes, this.J1, this.J2);
			
			// Affichage
			Affichage.AfficherPlateau(this.bornes, JoueurActif, JoueurPassif, this);
			
			// Tour du joueur
			boolean verifyTurn = JoueurActif.prochainTour(this.bornes, this);
			
			if (verifyTurn) {
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
		
		// Boucle pour demander si on veut jouer avec un humain ou un robot
		Joueur J1 = null;
		Joueur J2 = null;
		boolean choice = true;
		while(choice) {
			String strEntry = scEntry.nextLine();
			
			if (strEntry.equals("2")) {
				System.out.println("Mode : 2 Joueurs.");
				J1 = new Humain(new ArrayList<Carte>(), new int[9], "J1");
				J2 = new Humain(new ArrayList<Carte>(), new int[9], "J2");
				choice = false;
				
			} else if (strEntry.equals("1")) {
				System.out.println("Mode : 1 Joueur.");
				J1 = new Humain(new ArrayList<Carte>(), new int[9], "J1");
				J2 = new Robot(new ArrayList<Carte>(), new int[9], "Bot");
				choice = false;
			} else {
				System.out.println("Veuillez rentrer \"1\" ou \"2\" pour sélectionner le nombre de Joueur");
			}
		}
		// Boucle pour demander si on veut jouer en version normal ou tactique
		while(true) {
			System.out.println("Veuillez choisir votre variante, 1 pour normal et 2 pour tactique");
			String strEntry = scEntry.nextLine();
			
			if (strEntry.equals("1")) {
				return new JeuNormal(J1, J2);
				
			} else if (strEntry.equals("2")) {
				return new JeuTactique(J1, J2, new ArrayList<CarteTactique>());
				
			} else {
				System.out.println("Veuillez rentrer \"1\" ou \"2\" pour sélectionner le mode de Jeu");
			}
		}
	}

	public int scoreBorne(List<Carte> cartes, int indexBorne) {
		List<Integer> listeValeur = new ArrayList<Integer>();
		for (int i = 0; i < cartes.size(); i++) {
			if (cartes.get(i) instanceof CarteTactique) {
				CarteTactique ct = (CarteTactique) cartes.get(i);
				if (ct.getNomTactique().equals("Joker") && ct.getValeur() == -1) {
			        JeuTactique.choixJoker(ct, this, indexBorne);
			    } 
				if (ct.getNomTactique().equals("Espion") && ct.getCouleur().equals("undefined")) {
			    	JeuTactique.choixEspion(ct, this, indexBorne);
			    }
			}
			listeValeur.add(cartes.get(i).getValeur());
		}
		Collections.sort(listeValeur);
		int isFollowing = 0;
		int sameValue = 0;
		int sameColor = 0;
		Carte c1 = cartes.get(0);
		Carte c2 = cartes.get(1);
		Carte c3 = cartes.get(2);
				
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += listeValeur.get(i);
		}
				
		if (((listeValeur.get(0) + 1) == listeValeur.get(1)) && ((listeValeur.get(1) + 1) == listeValeur.get(2))) {
				isFollowing = 1;
		} else if (listeValeur.get(0) == listeValeur.get(1) && ((listeValeur.get(1)) == listeValeur.get(2))) {
				sameValue = 1;
		}
		if (c1.getCouleur() == c2.getCouleur() && c2.getCouleur() == c3.getCouleur()) {
			sameColor = 1;
		}
		if (isFollowing == 1 && sameColor == 1) {return 200 + sum;}
				
		else if (sameValue == 1) {return 150 + sum;}
				
		else if (sameColor == 1) {return 100 + sum;}
				
		else if (isFollowing == 1) {return 50 + sum;}
				
		else {return sum;}
	}

	// Méthode pour donner une borne au joueur qui a la meilleur combinaison sur l'une d'elles
	public void GiveBornes(List<Borne> bornes, Joueur J1, Joueur J2) {
		for (int i = 0; i < bornes.size(); i++) {
			Borne b = bornes.get(i);
			if (b.getCartesJ1().size() == 3 & b.getCartesJ2().size() == 3) {
				b.setScoreJ1(this.scoreBorne(b.getCartesJ1(), i));
				b.setScoreJ2(this.scoreBorne(b.getCartesJ2(), i));
				int score1 = b.getScoreJ1();
				int score2 = b.getScoreJ2();
				
				if (score1 > score2) {
					J1.getBornes()[i] = 1;
				} else if (score2 > score1) {
					J2.getBornes()[i] = 1	;
				}
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