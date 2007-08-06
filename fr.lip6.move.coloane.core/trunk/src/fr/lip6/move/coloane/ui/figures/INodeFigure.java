package fr.lip6.move.coloane.ui.figures;

import org.eclipse.draw2d.IFigure;

public interface INodeFigure extends IFigure {

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
	 * Modifie la figure lorsqu'elle est deselectionee
	 * Annulation du feedback visuel du a la selection d'un objet Noeud
	 */
	void setUnselect();

	/**
	 * Annulation de la mise en valeur provoquee par un retour de service
	 */
	void unsetSelectSpecial();
}
