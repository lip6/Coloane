package fr.lip6.move.coloane.core.ui.figures;

/**
 * Interface représentant une note
 */
public interface IStickyNoteFigure {

	/** The default amount of pixels subtracted from the figure's height and width to determine the size of the corner. */
	int DEFAULT_CORNER_SIZE = 10;

	/**
	 * Permet de modifier la taille du coin en haut à droite
	 * @param cornerSize taille
	 */
	void setCornerSize(int cornerSize);
}
