package com.schottenTotten.controller;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.schottenTotten.ai.Humain;
import com.schottenTotten.model.*; // Importe tout
import com.schottenTotten.view.Affichage;

public class JeuTactique extends Jeu {

	private List<CarteTactique> piocheTactique;
	
    //public JeuTactique(Joueur J1, Joueur J2) {
    //    super(J1, J2);
    //}
    
    public JeuTactique(Joueur J1, Joueur J2, List<CarteTactique> piocheTactique) {
		super(J1, J2);
		this.piocheTactique = piocheTactique;
	}

	public List<CarteTactique> getPiocheTactique() {
		return piocheTactique;
	}

	public void setPiocheTactique(List<CarteTactique> piocheTactique) {
		this.piocheTactique = piocheTactique;
	}

	// Méthode pour initiliser le paquet normal (54 cartes) et les cartes tactiques (10 au max)
    @Override
    public void initialiserPaquet() {
        // Création d'un paquet normal
        this.setPioche(Carte.CreationPaquet());

        // Ajout des cartes tactiques manuellement (on se le permet car les cartes sont au max de 10)
        this.getPiocheTactique().add(new CarteTactique("Joker"));
        this.getPiocheTactique().add(new CarteTactique("Joker"));
        this.getPiocheTactique().add(new CarteTactique("Espion"));
        // extension ici...
        
        // On mélange le paquet de cartes tactiques
        Collections.shuffle(piocheTactique);
    }
    
    // Méthode pour laisser le joueur piocher une carte normale (1) ou tactique (2)
    @SuppressWarnings("resource")
	@Override
    public void piocher(Jeu game, Joueur J) {
    	if (game.getPioche().size() <= 0 && ((JeuTactique) game).getPiocheTactique().size() <= 0) {
    		System.out.println("Il n'y a plus de cartes à piocher !");
    		return ;
    	}
		while (true) {
	    	System.out.println("Tapez 1 pour piocher une carte normale, et 2 pour une carte tactique");
	    	Scanner scEntry = new Scanner(System.in);
			String strEntry = scEntry.nextLine();
			if (strEntry.equals("1")) {
				if (game.getPioche().size() > 0) {
					Carte.pioche(J.getPaquetJoueur(), game.getPioche());
					return ;
			    }
			} else if (strEntry.equals("2")) {
				if (((JeuTactique) game).getPiocheTactique().size() > 0) {
					CarteTactique.piocheTactique(J.getPaquetJoueur(), ((JeuTactique) game).getPiocheTactique());	
					return ;
				} else {
					System.out.println("Il n'y a plus de cartes tactiques disponibles.");
				}
			}
		}
    }

    @SuppressWarnings("resource")
	@Override
	public boolean jouerCarte(Carte carte, Joueur joueur, int indexBorne, int indexCarteMain, List<Borne> bornes) {
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
    
    private static boolean estCouleurValide(String c) {
        String[] couleursValides = {"Red", "Blue", "Green", "Yellow", "Purple", "Brown"};
        for (String valide : couleursValides) {
            if (valide.equals(c)) {
                return true;
            }
        }
        return false;
    }
    
	public static CarteTactique choixJoker(CarteTactique carte, Jeu game, int indexBorne) {
    	Scanner sc = new Scanner(System.in);
        boolean madeChoice = false;
        int valeur = -1;
        String couleur = "";

        while (!madeChoice) {
        	Affichage.AfficherPlateau(game.bornes, game.J1, game.J2, game);
            System.out.println("Choississez votre couleur et votre valeur pour la carte Joker sur la borne " + (indexBorne + 1));
            System.out.println("Format attendu : 'valeur couleur' (ex: 6 Red)");
            System.out.println("Couleurs possibles : Red, Blue, Green, Yellow, Purple, Brown");
            
            String saisie = sc.nextLine();
            String[] parties = saisie.split(" ");

            // On vérifie le format (2 mots)
            if (parties.length != 2) {
                System.out.println("Erreur : Format invalide. Veuillez taper un chiffre suivi d'une couleur.");
                continue;
            }

            // On vérifie la valeur numérique
            try {
                valeur = Integer.parseInt(parties[0]);
                if (valeur < 1 || valeur > 9) {
                    System.out.println("Erreur : La valeur doit être comprise entre 1 et 9.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Le premier paramètre doit être un nombre.");
                continue;
            }

            // On vérifie la couleur
            couleur = parties[1];
            if (!estCouleurValide(couleur)) {
                System.out.println("Erreur : Couleur '" + couleur + "' non reconnue.");
                continue;
            }
            madeChoice = true;
        }
        
        // On applique les changements à la carte
        ((CarteTactique) carte).setImitation(valeur, couleur);
    	
    	return carte;
    }
	
	public static CarteTactique choixEspion(CarteTactique carte, Jeu game, int indexBorne) {
    	Scanner sc = new Scanner(System.in);
        boolean madeChoice = false;
        int valeur = -1;
        String couleur = "";

        while (!madeChoice) {
        	Affichage.AfficherPlateau(game.bornes, game.J1, game.J2, game);
            System.out.println("Choississez votre couleur pour la carte Espion sur la borne " + (indexBorne + 1));
            System.out.println("Format attendu : couleur (ex: Red)");
            System.out.println("Couleurs possibles : Red, Blue, Green, Yellow, Purple, Brown");
            
            String saisie = sc.nextLine();
            
            // On vérifie la couleur
            if (!estCouleurValide(saisie)) {
                System.out.println("Erreur : Couleur '" + saisie + "' non reconnue.");
                continue;
            }
            madeChoice = true;
        }
        
        // On applique les changements à la carte
        ((CarteTactique) carte).setCouleur(couleur);
    	
    	return carte;
    }
}