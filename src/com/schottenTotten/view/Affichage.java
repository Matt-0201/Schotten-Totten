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
	}
	
	// Méthode pour afficher l'état du jeu actuel
	/*public static void AfficherPlateau(List<Borne> bornes, Joueur JoueurActif, Joueur JoueurPassif, Jeu game) {
		// On prépare le terrain
		System.out.print("\n");
		String strPlayer; 
		if (JoueurActif == game.getJ1()) {
			strPlayer = "J2";			
		} else {
			strPlayer = "J1";
		}
		System.out.print("\t");
		// On affiche le numéro des bornes pour plus de lisibilité
		for (int colonne = 0; colonne < 9; colonne++) {
			System.out.printf("    Borne %s ", colonne+1);
		}
		System.out.print("\n");
		System.out.print("\t");
	
		// On affiche les bornes remportées du premier joueur s'il y en a
		for (int colonne = 0; colonne < 9; colonne++) {
			if (JoueurPassif.getBornes()[colonne] == 1) {
				System.out.print("   =+=+=+=+=");					
			} else {
				System.out.print("            ");
			}
		} 
		System.out.print("\n");

		// On affiche les 3 cartes du premier joueur
		for (int ligne = 0; ligne < 3; ligne++) {
			System.out.print("\t" + strPlayer);
			for (int colonne = 0; colonne < 9; colonne++) {
				Carte carte = null;
				String strCarte;
				String strFormat = "| %-10s";
				int sizeLine = 0;
		
				if (JoueurPassif == game.getJ2()) {
					sizeLine = bornes.get(colonne).getCartesJ2().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ2().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				} else {
					sizeLine = bornes.get(colonne).getCartesJ1().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ1().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				}
			}
			System.out.print("\n");
			// On affiche la démarcation
			if (ligne == 2) {
				if (JoueurActif == game.getJ1()) {
					strPlayer = "J1";
				} else {
					strPlayer = "J2";
				}
				System.out.print("\t  ");
				System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=++=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
			}
		}		
			// On affiche les 3 cartes du joueur suivant
			for (int ligne = 0; ligne < 3; ligne++) {
				System.out.print("\t" + strPlayer);
				for (int colonne = 0; colonne < 9; colonne++) {
					Carte carte = null;
					String strCarte;
					String strFormat = "| %-10s";
					int sizeLine = 0;
					if (JoueurActif == game.getJ1()) {
						sizeLine = bornes.get(colonne).getCartesJ1().size();
						if (ligne < sizeLine) {
							carte = bornes.get(colonne).getCartesJ1().get(ligne);
							String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
							String colorStrCarte = AfficherCarte(carte, strFormatVisible);
							System.out.print("| " + colorStrCarte);
						} else {
							strCarte = "--------  ";
							System.out.printf(strFormat, strCarte);
						}
					} else {
						sizeLine = bornes.get(colonne).getCartesJ2().size();
						if (ligne < sizeLine) {
							carte = bornes.get(colonne).getCartesJ2().get(ligne);
							String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
							String colorStrCarte = AfficherCarte(carte, strFormatVisible);
							System.out.print("| " + colorStrCarte);
						} else {
							strCarte = "--------  ";
							System.out.printf(strFormat, strCarte);
						}
					}
					
				}
				System.out.print("\n");
			}
			System.out.print("\t");
			// On affiche les bornes du second joueur remportées s'il y en a
			if (JoueurActif == game.getJ2()) {
				for (int colonne = 0; colonne < 9; colonne++) {
					if (game.getJ2().getBornes()[colonne] == 1) {
						System.out.print("   =+=+=+=+=");					
					} else {
						System.out.print("            ");
					}
				} 
			} else {
				for (int colonne = 0; colonne < 9; colonne++) {
					if (game.getJ1().getBornes()[colonne] == 1) {
						System.out.print("   =+=+=+=+=");					
					} else {
						System.out.print("            ");
					}
				} 
			}
		}
	
	
	// Méthode pour afficher l'état du jeu actuel
	/*public static void AfficherPlateau(List<Borne> bornes, int player, Joueur J1, Joueur J2) {
		// On prépare le terrain
		System.out.print("\n");
		String strPlayer; 
		if (player == 1) {
			strPlayer = "J2";			
		} else {
			strPlayer = "J1";
		}
		System.out.print("\t");
		// On affiche le numéro des bornes pour plus de lisibilité
		for (int colonne = 0; colonne < 9; colonne++) {
			System.out.printf("    Borne %s ", colonne+1);
		}
		System.out.print("\n");
		System.out.print("\t");
		
		// On affiche les bornes remportées du premier joueur s'il y en a
		if (player == 1) {
			for (int colonne = 0; colonne < 9; colonne++) {
				if (J2.getBornes()[colonne] == 1) {
					System.out.print("   =+=+=+=+=");					
				} else {
					System.out.print("            ");
				}
			} 
			System.out.print("\n");
		} else {
			for (int colonne = 0; colonne < 9; colonne++) {
				if (J1.getBornes()[colonne] == 1) {
					System.out.print("   =+=+=+=+=");					
				} else {
					System.out.print("            ");
				}
			} 
			System.out.print("\n");
		}
		// On affiche les 3 cartes du premier joueur
		for (int ligne = 0; ligne < 3; ligne++) {
			System.out.print("\t" + strPlayer);
			for (int colonne = 0; colonne < 9; colonne++) {
				Carte carte = null;
				String strCarte;
				String strFormat = "| %-10s";
				int sizeLine = 0;
				if (player == 1) {
					sizeLine = bornes.get(colonne).getCartesJ2().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ2().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				} else {
					sizeLine = bornes.get(colonne).getCartesJ1().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ1().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				}
			}
			System.out.print("\n");
			// On affiche la démarcation
			if (ligne == 2) {
				if (player == 1) {
					strPlayer = "J1";
				} else {
					strPlayer = "J2";
				}
				System.out.print("\t  ");
				System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=++=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
			}
		}
		// On affiche les 3 cartes du joueur suivant
		for (int ligne = 0; ligne < 3; ligne++) {
			System.out.print("\t" + strPlayer);
			for (int colonne = 0; colonne < 9; colonne++) {
				Carte carte = null;
				String strCarte;
				String strFormat = "| %-10s";
				int sizeLine = 0;
				if (player == 1) {
					sizeLine = bornes.get(colonne).getCartesJ1().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ1().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				} else {
					sizeLine = bornes.get(colonne).getCartesJ2().size();
					if (ligne < sizeLine) {
						carte = bornes.get(colonne).getCartesJ2().get(ligne);
						String strFormatVisible = String.format("%-10s", carte.getValeur() + " " + carte.getCouleur());
						String colorStrCarte = AfficherCarte(carte, strFormatVisible);
						System.out.print("| " + colorStrCarte);
					} else {
						strCarte = "--------  ";
						System.out.printf(strFormat, strCarte);
					}
				}
				
			}
			System.out.print("\n");
		}
		System.out.print("\t");
		// On affiche les bornes du second joueur remportées s'il y en a
		if (player == 2) {
			for (int colonne = 0; colonne < 9; colonne++) {
				if (J2.getBornes()[colonne] == 1) {
					System.out.print("   =+=+=+=+=");					
				} else {
					System.out.print("            ");
				}
			} 
		} else {
			for (int colonne = 0; colonne < 9; colonne++) {
				if (J1.getBornes()[colonne] == 1) {
					System.out.print("   =+=+=+=+=");					
				} else {
					System.out.print("            ");
				}
			} 
		}
	}*/
	
	
	public static void AfficherPlateau(List<Borne> bornes, Joueur actif, Joueur passif, Jeu game) {
	    // On détermine les numéros pour savoir quelle liste (J1 ou J2) appeler dans Borne
		/*int numActif;
		int numPassif;
		if (actif == game.getJ1()) {
			numActif = 1;
			numPassif = 2;
		} else {
			numActif = 2;
			numPassif = 1;
		}*/
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
