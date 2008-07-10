package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractArcFigure extends PolylineConnection implements IArcFigure {

	private IArcGraphicInfo graphicInfo;
	private boolean isSelected = false;

	public AbstractArcFigure(IArcGraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;

		setForegroundColor(graphicInfo.getColor());

		// La liste des points d'inflexion de l'arc
		this.setConnectionRouter(new BendpointConnectionRouter());

		// Epaisseur de la ligne
		setLineWidth(1);
	}

	public final void setHighlight() {
		super.setForegroundColor(ColorsPrefs.setColor("COLORARC_HIGHLIGHT")); //$NON-NLS-1$
		this.setLineWidth(2);
		isSelected = true;
	}

	public final void setSelect() {
		super.setForegroundColor(ColorsPrefs.setColor("COLORARC")); //$NON-NLS-1$
		this.setLineWidth(2);
		isSelected = true;
	}

	public final void setSelectSpecial() {
		this.setForegroundColor(ColorConstants.red);
		this.setLineWidth(2);
		isSelected = true;
	}

	public final void setUnselect() {
		if (!isSelected) {
			return;
		}
		isSelected = false;
		super.setForegroundColor(graphicInfo.getColor());
		setLineWidth(1);
	}

	@Override
	public final void setForegroundColor(Color fg) {
		if (!isSelected) {
			super.setForegroundColor(fg);
		}
	}
}
