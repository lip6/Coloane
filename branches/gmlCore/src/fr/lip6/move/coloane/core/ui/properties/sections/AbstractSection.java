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
package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IAbstractPropertyChange;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * Section de base adaptée à un objet du model.
 * @param <T>
 */
public abstract class AbstractSection<T extends IAbstractPropertyChange> extends AbstractPropertySection implements PropertyChangeListener {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private List<T> elements = new ArrayList<T>();
	private boolean isDisposed = false;

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		isDisposed = true;
		LOGGER.finest("Dispose the section " + this.getClass()); //$NON-NLS-1$
		internalDispose();
	}

	/**
	 * @see AbstractSection.dispose() method
	 */
	protected void internalDispose() {
	}

	/**
	 * @return true si la section n'est plus visible.
	 */
	public final boolean isDisposed() {
		return isDisposed;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public final void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		// Suppression des anciens listeners
		for (T oldElement : elements) {
			oldElement.removePropertyChangeListener(this);
		}

		// On réinitialise la liste des éléments sélectionnés.
		elements.clear();

		Iterator<Object> it = (Iterator<Object>) ((IStructuredSelection) getSelection()).iterator();
		while (it.hasNext()) {
			EditPart editPart = (EditPart) it.next();
			T element;
			Object elt = editPart.getModel();
			if (elt instanceof IAttribute) {
				IAttribute att = (IAttribute) elt;
				att.addPropertyChangeListener(this);
				element = (T) att.getReference();
			} else {
				element = (T) elt;
				element.addPropertyChangeListener(this);
				for (IAttribute a : ((IElement)element).getAttributes()){
					a.addPropertyChangeListener(this);
				}
			}
			elements.add(element);
		}
	}

	/**
	 * @return le modèle de l'élément séléctionné
	 */
	public final List<T> getElements() {
		return Collections.unmodifiableList(elements);
	}

	/**
	 * @return un CommandStack permettant d'executer des Command.
	 */
	public final CommandStack getCommandStack() {
		EditPart o = (EditPart) ((IStructuredSelection) getSelection()).getFirstElement();
		CommandStack cs = o.getParent().getViewer().getEditDomain().getCommandStack();
		return cs;
	}

	/** {@inheritDoc} */
	public void propertyChange(PropertyChangeEvent evt) {
	}

}
