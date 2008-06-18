package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

/**
 * Section qui affiche les attributs du noeud.
 */
public class NodeSection extends AbstractElementSection<INodeImpl> {
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElement().getElementBase().getName(),
					getElement().getElementBase().getListOfAttribute());

			refreshContent();
			redraw();
		}
	}

}
