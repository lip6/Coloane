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

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

/**
 * Listener for the result tree viewer used to display results from external tools.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Demoulins
 * @author Florian David
 */
public class CheckStateListener implements ICheckStateListener {
	/** Map containing for each node/arc highlighted how many checked results about it there is in the tree. */
	private Map<ISpecialState, Integer> checkStateMap;

	/** Result tree viewer. */
	private CheckboxTreeViewer viewer;

	/**
	 * Constructor
	 * @param viewer the checkboxTreeViewer to listen.
	 * @param map the map containing for each node/arc highlighted how many checked results about it there is in the tree.
	 */
	CheckStateListener(CheckboxTreeViewer viewer, Map<ISpecialState, Integer> map) {
		this.viewer = viewer;
		this.checkStateMap = map;
	}

	/**
	 * Mise a jour de l'état check d'une ligne de résultat ainsi que de
	 * tous les fils (récursivement)
	 * @param session session courante
	 * @param result sous arbre de résultat
	 * @param wasCheck ancienne état de result
	 * @param toCheck nouvel état
	 */
	private void checkResult(ISession session, IResultTree result, boolean wasCheck, boolean toCheck) {
		// On vérifie qu'on passe de true à false ou inversement
		if (wasCheck != toCheck) {
			// On traite tous les éléments devant être mis en valeur
			for (int id : result.getHighlighted()) {
				ISpecialState element = (ISpecialState) session.getGraph().getObject(id);
				if (element != null) {
					// On compte le nombre de fois qu'un élément a été
					// demandé a être mis en valeur.
					Integer value = checkStateMap.get(element);
					if (toCheck) {
						if (value == null) {
							value = 0;
						}
						element.setSpecialState(true);
						value++;
					} else {
						value--;
						if (value == 0) {
							element.setSpecialState(false);
						}
					}
					checkStateMap.put(element, value);
				}
			}


			// On traite ensuite tous les attributs devant être mis en valeur
			Map<Integer, List<String>> attributesMap = result.getAttributesOutline();
			Iterator<Integer> it = attributesMap.keySet().iterator();

			while (it.hasNext()) {
				int objectId = it.next();

				IElement element = session.getGraph().getObject(objectId);
				if (element != null) {
					for (String str : attributesMap.get(objectId)) {
						ISpecialState attribute = (ISpecialState) element.getAttribute(str);

						if (attribute != null) {
							// On compte le nombre de fois qu'un attribut a été
							// demandé a être mis en valeur.
							Integer value = checkStateMap.get(attribute);
							if (toCheck) {
								if (value == null) {
									value = 0;
								}
								attribute.setSpecialState(true);
								value++;
							} else {
								value--;
								if (value == 0) {
									attribute.setSpecialState(false);
								}
							}
							checkStateMap.put(attribute, value);
						}
					}
				}
			}
		}

		// Gestion des Tips
		if (toCheck) {
			if (result.getHighlighted().size() > 0) {
				session.removeTips(result.getTips(result.getHighlighted()));
				session.addAllTips(result.getTips(result.getHighlighted()));
			} else {
				session.removeTips(result.getTips());
				session.addAllTips(result.getTips());
			}
		} else {
			if (result.getHighlighted().size() > 0) {
				session.removeTips(result.getTips(result.getHighlighted()));
			} else {
				session.removeTips(result.getTips());
			}
		}

		// Appel récursif sur tous les fils
		for (IResultTree child : result.getChildren()) {
			checkResult(session, child, viewer.getChecked(child), toCheck);
		}
	}
	
	
	/**
	 * Forbid to check textual results.
	 * @param result the checked subResult in the tree.
	 * @param checked represent the new checked state in the tree : <code>true</code> if it's checked, <code>false</code> otherwise.
	 * @return <code>true</code> if the result is checked or if at least one of its subresults is checked, <code>false</code> otherwise.
	 */
	private boolean disableTextualResults(IResultTree result, boolean checked) {
		boolean bool = false;
		// Recursive call on all results children.
		for (IResultTree child : result.getChildren()) {
			// If at least one of its children is checked durring the recursive call, bool will be 'true' at the end of the 'for'.
			bool = disableTextualResults(child, checked) || bool;
		}
		// If at least one of its children was checked, we check the current result (the parent of the checked subResult).
		// We go through this 'if' only if the current result got subResult.
		if (bool) {
			viewer.setChecked(result, checked);
			return true;
		}
		// If the result don't have subResult, we look if there is graph element to highlight in the current result.
		// If not : this is a textual result and we uncheck it.
		// Otherwise we don't touch it because it state has already been modify before the call of the method.
		if ((result.getHighlighted().size() == 0) && result.getAttributesOutline().isEmpty()) {
			viewer.setChecked(result, false);
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	public final void checkStateChanged(CheckStateChangedEvent event) {
		IResultTree result = (IResultTree) event.getElement();
		checkResult(SessionManager.getInstance().getCurrentSession(), result, !event.getChecked(), event.getChecked());
		viewer.setSubtreeChecked(event.getElement(), event.getChecked());
		disableTextualResults(result, event.getChecked());
	}
}


