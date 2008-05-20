package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.PointList;

public class ArcFigure extends PolylineConnection implements IArcFigure {

	private static final double SCALE1 = 0.8;
	private static final double SCALE2 = 0.8;

	public ArcFigure(IArcImpl arc) {
		PolygonDecoration decoration = null;

		// Choix de la decoration de l'arc
		if (arc.getGraphicInfo().getFigureStyle() == IArcGraphicInfo.FIG_ARC_INHIBITOR) {
			decoration = new PolygonDecoration();

			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(3, 3);
			decorationPointList.addPoint(4, 1);
			decorationPointList.addPoint(4, -1);
			decorationPointList.addPoint(3, -3);
			decorationPointList.addPoint(1, -4);
			decorationPointList.addPoint(-1, -4);
			decorationPointList.addPoint(-3, -3);
			decorationPointList.addPoint(-4, -1);
			decorationPointList.addPoint(-4, 1);
			decorationPointList.addPoint(-3, 3);
			decorationPointList.addPoint(-1, 4);
			decorationPointList.addPoint(1, 4);

			decoration.setTemplate(decorationPointList);
			decoration.setFill(true);
			decoration.setBackgroundColor(ColorConstants.white);
			decoration.setScale(SCALE1, SCALE2);
		} else {
			decoration = new PolygonDecoration();
			decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
			decoration.setScale(3, 3);
		}
		this.setTargetDecoration(decoration);

		// La liste des points d'inflexion de l'arc
		this.setConnectionRouter(new BendpointConnectionRouter());

		// Epaisseur de la ligne
		setLineWidth(1);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.IArcFigure#setHighlight()
	 */
	public final void setHighlight() {
		this.setForegroundColor(ColorsPrefs.setColor("COLORARC_HIGHLIGHT")); //$NON-NLS-1$
		this.setLineWidth(2);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.IArcFigure#setSelect()
	 */
	public final void setSelect() {
		this.setForegroundColor(ColorsPrefs.setColor("COLORARC")); //$NON-NLS-1$
		this.setLineWidth(2);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.figures.IArcFigure#setSelectSpecial()
	 */
	public final void setSelectSpecial() {
		this.setForegroundColor(ColorConstants.red);
		this.setLineWidth(2);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.IArcFigure#unsetSelect()
	 */
	public final void unsetSelect() {
		this.setForegroundColor(ColorConstants.black);
		this.setLineWidth(1);
	}
}
