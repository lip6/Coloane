package fr.lip6.move.coloane.motor.formalism;

/**
 * Formalisme d'un Noeud
 */
public class NodeFormalism extends ElementBase {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 *
	 * @param name Nom de l'element de base.
	 * @param paletteName Nom de l'element de base affiche dans la palette.
	 * @param numFigure	Indication sur la forme du dessin de l'element
	 * @param width	Largeur du dessin de l'element de base.
	 * @param height Hauteur du dessin de l'element de base.
	 * @param isFilled Flag indiquant si la figure sera remplie.
	 */
	public NodeFormalism(String name, String paletteName, int numFigure, int width, int height, boolean isFilled) {
		super(name, paletteName, numFigure, width, height, isFilled);
	}
}
