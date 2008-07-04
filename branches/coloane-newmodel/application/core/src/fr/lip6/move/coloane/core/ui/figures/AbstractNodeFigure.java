package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractNodeFigure extends Figure implements IElementFigure {
	public AbstractNodeFigure(INodeGraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;
		createFigure(graphicInfo);

		// Ecoute des evenements ENTER et EXIT de la souris
		MouseMotionListener listener = new MouseMotionListener.Stub() {

			/** Sauvegarde de la couleur de fond */
			private Color previousBackgroundColor;

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.draw2d.MouseMotionListener$Stub#mouseEntered(org.eclipse.draw2d.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent me) {
				IFigure currentFigure = (IFigure) me.getSource();
				previousBackgroundColor = currentFigure.getBackgroundColor();
				currentFigure.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_MOUSE")); //$NON-NLS-1$
			}
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.draw2d.MouseMotionListener$Stub#mouseExited(org.eclipse.draw2d.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent me) {
				IFigure currentFigure = (IFigure) me.getSource();
				currentFigure.setBackgroundColor(previousBackgroundColor);
			}
		};

		addMouseMotionListener(listener);
	}

	/**
	 * Méthode qui créer et initialise la figure.
	 * @param graphicInfo
	 */
	protected abstract void createFigure(INodeGraphicInfo graphicInfo);
}
