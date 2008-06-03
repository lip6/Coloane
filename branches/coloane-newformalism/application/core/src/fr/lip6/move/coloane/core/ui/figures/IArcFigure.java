package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

public interface IArcFigure extends IFigure, Connection {

	/**
	 * Modifie l'arc lorsqu'elle est mise en valeur par des retours de services
	 * On definit ici le feedback visuel lors de la selection d'un resultat qui met en valeur le noeud
	 */
	void setSelectSpecial();

	/**
	 * Modifie l'apparence de la figure lorsque l'element est selectionne
	 */
	void setSelect();

	/**
	 * Mise en valeur de l'arc (selection d'un attribut referent)
	 */
	void setHighlight();

	/**
	 * Modifie l'apparence de la figure pour un retour a la normale
	 */
	void unsetSelect();

}
