package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;

/**
 * Définition pour un calque de fond de l'éditeur de graphes
 * @author Jean-Baptiste Voron
 */
public class BackgroundLayer extends FreeformLayer {
	public static final String BACKGROUND_LAYER = "Background Palm Layer"; //$NON-NLS-1$
	private Image palm = new Image(Coloane.getParent().getDisplay(), Coloane.class.getResourceAsStream("/resources/icons/coloane_transparent.png")); //$NON-NLS-1$

	/**
	 * Constructeur de calcque de fond
	 */
	public BackgroundLayer() {
		setOpaque(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paintFigure(Graphics graphics) {
		if (isOpaque()) {
			graphics.drawImage(palm, new Point(5, 5));
		}
	}
}
