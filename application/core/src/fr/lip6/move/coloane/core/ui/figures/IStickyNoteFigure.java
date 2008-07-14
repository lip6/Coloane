package fr.lip6.move.coloane.core.ui.figures;

public interface IStickyNoteFigure {

	/** The default amount of pixels subtracted from the figure's height and width to determine the size of the corner. */
	int DEFAULT_CORNER_SIZE = 10;

	void setCornerSize(int cornerSize);
}
