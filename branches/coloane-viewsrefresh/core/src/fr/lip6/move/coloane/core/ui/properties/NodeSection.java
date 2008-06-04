package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

public class NodeSection extends AbstractElementSection<INodeImpl> {
	@Override
	public final void refresh() {
		refreshControls(
				getElement().getElementBase().getName(),
				getElement().getElementBase().getListOfAttribute());

		refreshContent();
		redraw();
	}

}
