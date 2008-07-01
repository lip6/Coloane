package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

/**
 * Section qui affiche les attributs du noeud.
 */
public class NodeSection extends AbstractElementSection<INode> {
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElement().getNodeFormalism().getName(),
					getElement().getNodeFormalism().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
