package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Section qui affiche les attributs d'un arc.
 */
public class ArcSection extends AbstractElementSection<IArc> {

	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElement().getArcFormalism().getName(),
					getElement().getArcFormalism().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
