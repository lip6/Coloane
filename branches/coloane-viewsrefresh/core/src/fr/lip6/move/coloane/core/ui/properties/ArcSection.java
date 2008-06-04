package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.IArcImpl;

/**
 * Section qui affiche les attributs d'un arc.
 */
public class ArcSection extends AbstractElementSection<IArcImpl> {

	@Override
	public final void refresh() {
		refreshControls(
				getElement().getElementBase().getName(),
				getElement().getElementBase().getListOfAttribute());

		refreshContent();
		redraw();
	}

}
