package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;

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
public abstract class AbstractSection<T extends IElement> extends AbstractPropertySection implements PropertyChangeListener {
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
		if (element != null) {
			element.removePropertyChangeListener(this);
			for (IAttributeImpl attr : element.getAttributes()) {
				attr.removePropertyChangeListener(this);
			}
		}
		element = (T) editPart.getModel();
		element.addPropertyChangeListener(this);
		for (IAttributeImpl attr : element.getAttributes()) {
			attr.addPropertyChangeListener(this);
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
