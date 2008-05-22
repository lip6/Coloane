package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.IArcImpl;

public class ArcSection extends AbstractSection<IArcImpl> {

	@Override
	public final void refresh() {
		refreshControls(
				getElement().getElementBase().getName(),
				getElement().getElementBase().getListOfAttribute());

		refreshContent();
		redraw();
	}

}
