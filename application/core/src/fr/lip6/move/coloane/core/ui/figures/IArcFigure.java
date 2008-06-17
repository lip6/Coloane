package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

public interface IArcFigure extends IFigure, Connection {

	/**
	 * Modifie l'apparence de la figure lorsque l'element est selectionne
	 */
	void setSelect();

	/**
	 * Modifie l'apparence de la figure lorsque l'element est mis en valeur
	 */
	void setHighlight();

	/**
	 * Modifie l'apparence de la figure pour un retour a la normale
	 */
	void setUnselect();

}
