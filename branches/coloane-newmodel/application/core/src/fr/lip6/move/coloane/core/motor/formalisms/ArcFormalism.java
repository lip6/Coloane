package fr.lip6.move.coloane.core.motor.formalisms;

/**
 * Formalisme associe a un arc
 */
public class ArcFormalism extends ElementFormalism {
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
	 * @param formalism Le formalism attache a l'arc
	 * @param numFigure Indication sur la forme du dessin de l'element
	 */
	public ArcFormalism(String name, String paletteName, Formalism formalism, int numFigure) {
		super(name, paletteName, formalism, numFigure, -1, -1, false);
	}
}
