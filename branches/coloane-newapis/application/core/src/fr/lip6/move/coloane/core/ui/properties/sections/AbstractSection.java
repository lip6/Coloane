package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IAbstractPropertyChange;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	private ArrayList<T> elements = new ArrayList<T>();
	private boolean isDisposed = false;


	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		isDisposed = true;
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

		Iterator<Object> it = (Iterator<Object>) ((IStructuredSelection) getSelection()).iterator();
		while (it.hasNext()) {
			EditPart editPart = (EditPart) it.next();
			T element = (T) editPart.getModel();
			element.addPropertyChangeListener(this);
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
}
