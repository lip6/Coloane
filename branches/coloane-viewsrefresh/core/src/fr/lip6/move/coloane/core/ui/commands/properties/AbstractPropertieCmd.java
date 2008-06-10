package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.IElement;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractPropertieCmd extends Command {

	/**
	 * @return vrai si le changement de couleur affecte le noeud sélectionné dans l'éditeur.
	 */
	protected final boolean isSelected(IElement element) {
		ColoaneEditor ce = (ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		ISelection selection = ce.getSite().getSelectionProvider().getSelection();
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (obj instanceof EditPart) {
			Object model = ((EditPart) obj).getModel();
			if (model instanceof IElement) {
				IElement elementSelected = (IElement) model;
				if (element != null && elementSelected != null) {
					return element.getId() == elementSelected.getId();
				}
			}
		}
		return false;
	}

}
