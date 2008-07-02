package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IAbstractPropertyChange;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeListener;

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
	private T element;
	private boolean isDisposed = false;


	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
	 */
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

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		EditPart editPart = (EditPart) ((IStructuredSelection) getSelection()).getFirstElement();
		T oldElement = element;
		element = (T) editPart.getModel();

		if (oldElement != null) {
			oldElement.removePropertyChangeListener(this);
			if (oldElement instanceof IElement) {
				IElement tmp = (IElement) oldElement;
				if (tmp.getAttributes() != null) {
					for (IAttribute attr : tmp.getAttributes()) {
						attr.removePropertyChangeListener(this);
					}
				}
			}
		}
		if (element != null) {
			element.addPropertyChangeListener(this);
			if (element instanceof IElement) {
				IElement tmp = (IElement) element;
				if (tmp.getAttributes() != null) {
					for (IAttribute attr : tmp.getAttributes()) {
						attr.removePropertyChangeListener(this);
					}
				}
			}
		}
	}

	/**
	 * @return le modèle de l'élément séléctionné
	 */
	public final T getElement() {
		return element;
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
