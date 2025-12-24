package com.schottenTotten.model;

import java.util.List;

public class CarteTactique extends Carte {

    private String type;
    
   // Attributs temporaires, seront modifiés lors de l'utilisation de la carte (c'est la cas pour un Joker, mais dépend de la carte)
    private int valeurImitee = -1; 
    private String couleurImitee = "undefined";

    public CarteTactique(String type) {
        super(-1, "undefined"); // Valeur par défaut
        this.type = type;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// Méthode pour modifier la carte, fonctionne en fait comme un setter (Joker)
    public void setImitation(int valeur, String couleur) {
        this.valeurImitee = valeur;
        this.couleurImitee = couleur;
    }

    public String getNomTactique() {
        return type;
    }
    
    @Override
    public String getDetails() {
    	if (this.getValeur() != -1) {
            return this.getNomTactique() + " " + this.getValeur();
        }
        return this.getNomTactique();
	}
    
    @Override
    public int getValeur() {
        if (valeurImitee != -1) {
        	return valeurImitee;        	
        }
        if (type.equals("Espion")) {
        	return 7; // L'espion vaut toujours 7
        }
        return -1; // Valeur par défaut si ce n'est pas une carte à points
    }
    
    public static void piocheTactique(List<Carte> mainJoueur, List<CarteTactique> PiocheTactique) {
    	//List<Carte> mainJoueur = J.getPaquetJoueur();
		mainJoueur.add(PiocheTactique.get(PiocheTactique.size()-1));
		PiocheTactique.removeLast();
    }

    @Override
    public String getCouleur() {
        if (couleurImitee != null) return couleurImitee;
        return "Tactique";
    }
    
    @Override
    public String toString() {
        return "Tactique [" + type + "]";
    }
}