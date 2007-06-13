package fr.lip6.move.coloane.ui.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

public interface IArcFigure extends IFigure, Connection {
    /**
     * Indique la valeur associee a l'arc
     * @param text La valeur de l'arc
     */
	public void setLabelValue(String text);
	
	public void setLabelLabel(String text);
}
