/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.List;
import java.util.Map;

/**
 * Describe a result tree.<br>
 * Each result has a set of details to specify.<br>
 * Each of these details is described in a special column.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Demoulins
 * @author Florian David
 */
public interface IResultTree {

	/**
	 * @return root Return the parent
	 */
	IResultTree getParent();
	
	/**
	 *
	 * Set the parent of this result tree.<br>
	 * Be careful! Only the link son -> dad is made.<br>
	 * Use the method {@link #addChild(IResultTree)} to add some results
	 * @param parent The parent tree
	 */
	void setParent(IResultTree parent);

	/**
	 * @return the list of all children
	 */
	List<IResultTree> getChildren();

	/**
	 * Add a child to this results tree
	 * @param child the child to add
	 */
	void addChild(IResultTree child);

	/**
	 * @return the elements that compose this result
	 */
	List<Object> getElement();

	/**
	 * @return the elements to highlight when this result is selected
	 */
	List<Integer> getHighlighted();
	
	/**
	 * Add elements to the list of element to highlight when the result is selected
	 * @param toHighlight the element to highlight
	 */
	void addHighlighted(int... toHighlight);

	/**
	 * @return attributes list which must be highlighted.
	 */
	Map<Integer, List<String>> getAttributesOutline();
	
	/**
	 * Remove the result from the view.
	 */
	void remove();

	/**
	 * Associe un gestionnaire de session avec le sous-arbre de resultats.<br>
	 * Cette methode doit etre appelé sur le noeud pere. Les fils trouveront le gestionnaire par parcours d'arbre
	 * @param sessionManager Le gestionnaire de session
	 * @see {@link SessionManager#getInstance()}
	 */
	void setSessionManager(ISessionManager sessionManager);

	/**
	 * Retourne le gestionnaire de session enregistre pour cet arbre de resultats.<br>
	 * La recherche est resursive (en partant des fils... jusqu'au pere).
	 * @return le gestionnaire de session ou null si il est introuvable.
	 */
	ISessionManager getSessionManager();

	/**
	 * @return la liste des tips à afficher quand ce résultat est sélectionné.
	 */
	List<ICoreTip> getTips();

	/**
	 * @param mayHaveTips ? TODO: Documenter
	 * @return la liste des tips en fonction des identifiants d'objets
	 */
	List<ICoreTip> getTips(List<Integer> mayHaveTips);

	/**
	 * Add tips list to the result.
	 * @param map the map containing graph element ids associated to a tips list.
	 * @param objectIds graph element ids of whom tips will be added to the result.
	 */
	void setTips(Map<Integer, List<ITip>> map, Integer... objectIds);
}
