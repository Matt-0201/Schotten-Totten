package com.schottenTotten.view;

import java.util.List;

import com.schottenTotten.controller.Jeu;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;

//import java.util.List;

//import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class Affichage {
	
	// Méthode pour afficher la main d'un joueur
	public static void AfficherJeu(Joueur J) {
		System.out.print("\n\n");
		System.out.print("Votre main: ");
		for (int i = 0; i <  J.getPaquetJoueur().size() ; i++) {
			Carte carte = J.getPaquetJoueur().get(i);
			String strCarte = carte.getValeur() + " " + carte.getCouleur();
			String strCarteColor = AfficherCarte(carte, strCarte);
			System.out.printf("%10s(%d) ", strCarteColor, i+1);
		}
		System.out.print("\n");
		System.out.print("Joueur " + J.getPseudo() + ", c'est à vous de jouer.");
	}
	
	// Méthode pour afficher le plateau de jeu
	public static void AfficherPlateau(List<Borne> bornes, Joueur actif, Joueur passif, Jeu game) {
	    // On détermine les numéros pour savoir quelle liste (J1 ou J2) appeler dans Borne
	    int numActif = (actif == game.getJ1()) ? 1 : 2;
	    int numPassif = (passif == game.getJ1()) ? 1 : 2;

	    // On affiche les bornes
	    System.out.print("\t         ");
	    for (int i = 1; i <= 9; i++) {
	    	System.out.printf("Borne %d ", i);
	    	System.out.print("    ");
	    }

	    // On affiche le premier joueur
	    afficherBornesRemportees(passif);
	    afficherZoneCartes(bornes, numPassif, "J" + numPassif);

	    // On affiche la zone du milieu
	    System.out.print("\t     ");
	    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");

	    // On affiche le second joueur
	    afficherZoneCartes(bornes, numActif, "J" + numActif);
	    afficherBornesRemportees(actif);
	}

	
	// Affiche les symboles de victoire au-dessus ou en-dessous des bornes
	private static void afficherBornesRemportees(Joueur j) {
	    System.out.print("\t      ");
	    for (int i = 0; i < 9; i++) {
	        if (j.getBornes()[i] == 1) {
	            System.out.print(" +=+=+=+=+  ");
	        } else {
	            System.out.print("            ");
	        }
	    }
	    System.out.println();
	}

	// Affiche les 3 lignes de cartes pour un côté donné (J1 ou J2)
	private static void afficherZoneCartes(List<Borne> bornes, int numJoueur, String label) {
	    for (int ligne = 0; ligne < 3; ligne++) {
	        System.out.print("\t" + label + "   ");
	        for (int col = 0; col < 9; col++) {
	            // On récupère la bonne liste 
	        	List<Carte> cartes;
	        	if (numJoueur == 1) {
	        		cartes = bornes.get(col).getCartesJ1();
	        	} else {
	        		cartes = bornes.get(col).getCartesJ2();
	        	}
				if (ligne < cartes.size()) {
	                Carte c = cartes.get(ligne);
	                String visible = String.format("%-10s", c.getValeur() + " " + c.getCouleur());
	                System.out.print("| " + AfficherCarte(c, visible));
	            } else {
	                System.out.print("| ----------");
	            }
	        }
	        System.out.println("|");
	    }
	}
	
	// Méthode pour afficher une carte en couleur
	public static String AfficherCarte(Carte c, String strFormatVisible) {
		String color = c.getCouleur();
		
		String couleurANSI = ANSIColors.RESET;
        if (color.equals("Red")) {
            couleurANSI = ANSIColors.RED;
        } else if (color.equalsIgnoreCase("Green")) {
            couleurANSI = ANSIColors.GREEN;
        } else if (color.equals("Purple")) {
        	couleurANSI = ANSIColors.MAGENTA;
        } else if (color.equalsIgnoreCase("Yellow")) {
            couleurANSI = ANSIColors.YELLOW;
        } else if (color.equals("Blue")) {
        	couleurANSI = ANSIColors.BLUE;
        } else if (color.equalsIgnoreCase("Brown")) {
            couleurANSI = ANSIColors.BLACK;
        } 
        String cardColor = couleurANSI + strFormatVisible + ANSIColors.RESET;
        
        return cardColor;
	}

	public static void AffichageDeBienvenue() {
		System.out.println("Bienvenue dans ce jeu qui reproduit le jeu de cartes Shotten Totten.\n");
		System.out.println("Vous pouvez vous référer au règles du jeu en accédant au lien suivant:\n");
		System.out.println("https://iello.fr/wp-content/uploads/2022/07/schottentotten_regles.pdf\n");
		System.out.println("Pour démarrer une partie contre un robot, tapez 1");
		System.out.println("Pour démarrer une partie 2 joueur, tapez 2");
	}
}
