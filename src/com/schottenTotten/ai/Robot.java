package com.schottenTotten.ai;

import java.util.List;

import com.schottenTotten.controller.Jeu;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public class Robot extends Joueur{

	public Robot(List<Carte> paquetJoueur, int[] bornes, String pseudo) {
		super();
	}
	

	@Override
	public boolean prochainTour(List<Borne> bornes, Jeu game) {
		// On définit l'amplitude sur laquelle on tire une carte au hasard
				int rangeCard = (this.getPaquetJoueur().size() - 1 ) + 1;
				// On définit l'amplitude sur laquelle on choisit une borne
				int rangeBorne = (9 - 1 ) + 1;
				
				int randomCard = (int) ((rangeCard * Math.random()) + 1);
				int randomBorne = (int) ((rangeBorne * Math.random()) + 1);
				System.out.println("Carte: " + randomCard + " Borne: " + randomBorne);

				Carte carteAPlacer = this.getPaquetJoueur().get(randomCard-1);
				List<Carte> borne = bornes.get(randomBorne-1).getCartesJ2();
				if (borne.size() < 3) {
					borne.add(carteAPlacer);
					this.getPaquetJoueur().remove(randomCard-1);
					if (game.getPioche().size() > 0) {
						Carte.pioche(this, game.getPioche());
					} else {
						System.out.println("Il n'y a plus de cartes à piocher !");
					}
				} else {
					System.out.println("Il y a eu un problème");
					return false;
				} return true; // Le tour s'est bien passé
			}
}

