package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

public interface IArcFigure extends IFigure, Connection {
	/** L'epaisseur des lignes lors de la selection */
	int LINE_WIDTH = 3;

	/**
	 * Modifie la figure lorsqu'elle est selectionee
	 * On definit ici le feedback visuel lors de la selection d'un objet Noeud
	 */
	void setSelect();

	/**
	 * Modifie la figure lorsqu'elle est mise en valeur par des retours de services
	 * On definit ici le feedback visuel lors de la selection d'un resultat qui met en valeur le noeud
	 */
	void setSelectSpecial();

	/**
	 * Mise en valeur du noeud (selection d'un attribut referent)
	 */
	void setHighlight();

	/**
	 * Modifie la figure lorsqu'elle est deselectionee
	 * Annulation du feedback visuel du a la selection d'un objet Noeud
	 */
	void setUnselect();
}
