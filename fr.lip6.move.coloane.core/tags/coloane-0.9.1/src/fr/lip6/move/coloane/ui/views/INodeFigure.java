package fr.lip6.move.coloane.ui.views;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

public interface INodeFigure extends IFigure {
	
	/**
	 * Indique les bornes reelles de la figure.
	 */
	public Rectangle getHandleBounds();
	
	/**
	 * Retourne le symbole de la figure.<br>
	 * Note: Il faut considerer ce symbole lorsqu'on essaie de creer des connexions.
	 */
	public IFigure getSymbol();
	
	/**
	 * Modifie la valeur du label nom attache
	 * @param name La chaine caracterisant le nom a afficher
	 */
	public void setNodeName(String name);
	
	/**
	 * Modifie la valeur du label valeur attache
	 * @param value La chaine caracterisant la valeur a afficher
	 */
	public void setNodeValue(String value);
	
	/**
	 * Modifie la valeur du label domaine attache
	 * @param domain La chaine caracterisant le domaine a afficher
	 */
	public void setNodeDomain(String domain);
	
	/**
	 * Modifie la figure lorsqu'elle est selectionee
	 * On definit ici le feedback visuel lors de la selection d'un objet Noeud
	 */
	public void setSelect();
	
	public void setSelectSpecial();
	
	public void unsetSelectSpecial();
	
	/**
	 * Modifie la figure lorsqu'elle est deselectionee
	 * Annulation du feedback visuel du a la selection d'un objet Noeud
	 */
	public void setUnselect();

}
