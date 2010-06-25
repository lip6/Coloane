package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Section qui affiche les attributs d'un arc.
 */
public class ArcSection extends AbstractElementSection<IArc> {

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElements().get(0).getArcFormalism().getName(),
					getElements().get(0).getArcFormalism().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
