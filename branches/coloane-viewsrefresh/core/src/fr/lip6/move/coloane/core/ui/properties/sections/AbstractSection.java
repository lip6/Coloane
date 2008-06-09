package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.IElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

public class AbstractSection<T extends IElement> extends AbstractPropertySection implements PropertyChangeListener {
	private T element;


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
		}
		element = (T) editPart.getModel();
		element.addPropertyChangeListener(this);
	}


	/**
	 * @return le modèle de l'élément séléctionné
	 */
	public final T getElement() {
		return element;
	}

	public final CommandStack getCommandStack() {
		EditPart o = (EditPart) ((IStructuredSelection) getSelection()).getFirstElement();
		CommandStack cs = o.getParent().getViewer().getEditDomain().getCommandStack();
		return cs;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Les sections qui veulent être averti des évenements doivent implementer cette méthode
	}
}
