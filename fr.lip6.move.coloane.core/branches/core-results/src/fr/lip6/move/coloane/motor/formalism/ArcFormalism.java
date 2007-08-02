package fr.lip6.move.coloane.motor.formalism;

/**
 * Formalisme associe a un arc
 */
public class ArcFormalism extends ElementBase {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Constante representant le dessin d'une fleche au bout de l'arc. */
	public static final int NOTHING = 0;

	/** Constante representant le dessin d'une fleche au bout de l'arc. */
	public static final int ARROW = 1;

	/** Constante representant le dessin d'une fleche au bout de l'arc. */
	public static final int SQUARE = 2;

	/** Constante representant le dessin d'une fleche au bout de l'arc. */
	public static final int CIRCLE = 3;


	/**
	 * Constructeur
	 * 
	 * @param name Nom de l'element de base.
	 * @param paletteName Nom de l'element de base affiche dans la palette
	 * @param numFigure Indication sur la forme du dessin de l'element
	 * @param width Largeur du dessin de l'element de base.
	 * @param height Hauteur du dessin de l'element de base.
	 * @param isFilled Flag indiquant si la figure sera remplie.
	 */
	public ArcFormalism(String name, String paletteName, int numFigure, int width, int height, boolean isFilled) {
		super(name, paletteName, numFigure, width, height, isFilled);
	}   
}
