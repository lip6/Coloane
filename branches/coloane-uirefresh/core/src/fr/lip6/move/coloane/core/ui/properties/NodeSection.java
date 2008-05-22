package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

public class NodeSection extends AbstractSection<INodeImpl> {
	@Override
	public final void refresh() {
		refreshControls(
				getElement().getElementBase().getName(),
				getElement().getElementBase().getListOfAttribute());

//		long t1 = System.currentTimeMillis();
//		System.err.println((System.currentTimeMillis() - t1) / (1));
		refreshContent();
		redraw();
	}

}
