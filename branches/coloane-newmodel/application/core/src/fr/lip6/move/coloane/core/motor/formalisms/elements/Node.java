package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;

/**
 * Formalisme d'un Noeud
 */
public class Node extends ElementFormalism {

	/**
	 * Constructeur
	 * @param name Nom de l'element de base.
	 * @param paletteName Nom de l'element de base affiche dans la palette.
	 * @param formalism Le formalisme associe au noeud
	 * @param numFigure	Indication sur la forme du dessin de l'element
	 * @param width	Largeur du dessin de l'element de base.
	 * @param height Hauteur du dessin de l'element de base.
	 * @param isFilled Flag indiquant si la figure sera remplie.
	 */
	public Node(String name, String paletteName, Formalism formalism, int numFigure, int width, int height, boolean isFilled) {
		super(name, paletteName, formalism, numFigure, width, height, isFilled);
	}
}
