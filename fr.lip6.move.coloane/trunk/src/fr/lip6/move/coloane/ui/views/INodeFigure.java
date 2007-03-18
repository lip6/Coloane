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
	 * Il faut considérer ce symbole lorsqu'on essaie de creer des connexions
	 */
	public IFigure getSymbol();
	
	/**
	 * Modifie la valeur du label nom attache
	 * @param name
	 */
	public void setNodeName(String name);
	
	/**
	 * Modifie la valeur du label valeur attache
	 * @param value
	 */
	public void setNodeValue(String value);
	
	/**
	 * Modifie la valeur du label domaine attache
	 * @param domain
	 */
	public void setNodeDomain(String domain);

}
