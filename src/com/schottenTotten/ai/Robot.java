package com.schottenTotten.ai;

import java.util.List;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class Robot extends Joueur{

	public Robot(List<Carte> paquetJoueur, int[] bornes) {
		super(paquetJoueur, bornes);
	}
	
	public static boolean placementRandom(Joueur J, List<Borne> bornes, List<Carte> pioche) {
		// On définit l'amplitude sur laquelle on tire une carte au hasard
		int rangeCard = (J.getPaquetJoueur().size() - 1 ) + 1;
		// On définit l'amplitude sur laquelle on choisit une borne
		int rangeBorne = (9 - 1 ) + 1;
		
		int randomCard = (int) ((rangeCard * Math.random()) + 1);
		int randomBorne = (int) ((rangeBorne * Math.random()) + 1);
		System.out.println("Carte: " + randomCard + " Borne: " + randomBorne);

		Carte carteAPlacer = J.getPaquetJoueur().get(randomCard-1);
		List<Carte> borne = bornes.get(randomBorne-1).getCartesJ2();
		if (borne.size() < 3) {
			borne.add(carteAPlacer);
			J.getPaquetJoueur().remove(randomCard-1);
			if (pioche.size() > 0) {
				Carte.pioche(J, pioche);
			} else {
				System.out.println("Il n'y a plus de cartes à piocher !");
			}
		} else {
			System.out.println("Il y a eu un problème");
			return false;
		} return true; // Le tour s'est bien passé
	}
}

